package rs.pedjaapps.cpuctrllibrary;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;




public final class CPUInfo {
	static List<Integer> freqList;

	private static final String FREQS_FILE_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_available_frequencies";
	private static final String TIMES_IN_STATE_FILE_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/stats/time_in_state";
	private static final String CPU0_CURRENT_MAX_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq";
	private static final String CPU1_CURRENT_MAX_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu1/cpufreq/scaling_max_freq";
	private static final String CPU2_CURRENT_MAX_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu2/cpufreq/scaling_max_freq";
	private static final String CPU3_CURRENT_MAX_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu3/cpufreq/scaling_max_freq";

	private static final String CPU0_CURRENT_MIN_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq";
	private static final String CPU1_CURRENT_MIN_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu1/cpufreq/scaling_min_freq";
	private static final String CPU2_CURRENT_MIN_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu2/cpufreq/scaling_min_freq";
	private static final String CPU3_CURRENT_MIN_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu3/cpufreq/scaling_min_freq";

	private static final String CPU0_MAX_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
	private static final String CPU1_MAX_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_max_freq";
	private static final String CPU2_MAX_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_max_freq";
	private static final String CPU3_MAX_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq";

	private static final String CPU0_MIN_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq";
	private static final String CPU1_MIN_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu1/cpufreq/cpuinfo_min_freq";
	private static final String CPU2_MIN_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu2/cpufreq/cpuinfo_min_freq";
	private static final String CPU3_MIN_FREQ_FILE_PATH = "/sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_min_freq";
	private static final String GOVERNORS_FILE_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_available_governors";
	private static final String CPU0_CURR_GOV_FILE_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_governor";
	private static final String CPU1_CURR_GOV_FILE_PATH = "/sys/devices/system/cpu/cpu1/cpufreq/scaling_governor";
	private static final String CPU2_CURR_GOV_FILE_PATH = "/sys/devices/system/cpu/cpu2/cpufreq/scaling_governor";
	private static final String CPU3_CURR_GOV_FILE_PATH = "/sys/devices/system/cpu/cpu3/cpufreq/scaling_governor";

	/**
	 * Get List of frequencies from /sys filesystem
	 * @return list of frequencies as List<Integer>*/
	public static List<Integer> getFrequencyList()
	{
		
		
		freqList = new ArrayList<Integer>();
		/*
		 * try to read all frequencies from "scaling_available_frequencies"
		 */
		try
		{

			File myFile = new File(FREQS_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			
			for(String s: Arrays.asList(aBuffer.split("\\s"))){
				freqList.add(Integer.parseInt(s.trim()));
 				
			}
			myReader.close();

		}
		catch(NumberFormatException ne){
			
		}
		catch (FileNotFoundException e)
		{
			/*
			 * if file "scaling_available_frequencies" isnt available
			 * try from "times_in_state"
			 */
			try
			{

	 			FileInputStream fstream = new FileInputStream(TIMES_IN_STATE_FILE_PATH);

	 			DataInputStream in = new DataInputStream(fstream);
	 			BufferedReader br = new BufferedReader(new InputStreamReader(in));
	 			String strLine;


	 			while ((strLine = br.readLine()) != null)
				{

	 				String[] delims = strLine.split(" ");
	 				freqList.add(Integer.parseInt(delims[0].trim()));
	 				

	 			}
	 			/*
	 			 * Sort list so that biggest freq goes last
	 			 */
	 				Collections.sort(freqList,new MyComparator());
	 			in.close();
			}
			catch (Exception ee)
			{
				
 				
			}
		} catch (IOException e) {
		}
		return freqList;

	}
	/**
	 * Get current min frequency of the CPU0
	 * @return Integer
	 * */
	public static Integer getCpu0CurrentMinFreq()
	{
		Integer freq = null;
		
		try
		{

			File myFile = new File(CPU0_CURRENT_MIN_FREQ_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			freq = Integer.parseInt(aBuffer.trim());
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return freq;

	}
	/**
	 * Get current min frequency of the CPU1
	 * @return Integer
	 * */
	public static Integer getCpu1CurrentMinFreq()
	{
		Integer freq = null;
		
		try
		{

			File myFile = new File(CPU1_CURRENT_MIN_FREQ_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			freq = Integer.parseInt(aBuffer.trim());
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return freq;

	}
	/**
	 * Get current min frequency of the CPU2
	 * @return Integer
	 * */
	public static Integer getCpu2CurrentMinFreq()
	{
		Integer freq = null;
		
		try
		{

			File myFile = new File(CPU2_CURRENT_MIN_FREQ_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			freq = Integer.parseInt(aBuffer.trim());
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return freq;

	}
	
	/**
	 * Get current min frequency of the CPU3
	 * @return Integer
	 * */
	public static Integer getCpu3CurrentMinFreq()
	{
		Integer freq = null;
		
		try
		{

			File myFile = new File(CPU3_CURRENT_MIN_FREQ_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			freq = Integer.parseInt(aBuffer.trim());
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return freq;

	}
	/**
	 * Get min Possible frequency of the CPU0
	 * @return Integer
	 * */
	public static Integer getCpu0MinPossibleFreq()
	{
		Integer freq = null;
		
		try
		{

			File myFile = new File(CPU0_MIN_FREQ_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			freq = Integer.parseInt(aBuffer.trim());
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return freq;

	}
	/**
	 * Get min possible frequency of the CPU1
	 * @return Integer
	 * */
	public static Integer getCpu1MinPossibleFreq()
	{
		Integer freq = null;
		
		try
		{

			File myFile = new File(CPU1_MIN_FREQ_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			freq = Integer.parseInt(aBuffer.trim());
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return freq;

	}
	/**
	 * Get min possible frequency of the CPU2
	 * @return Integer
	 * */
	public static Integer getCpu2MinPossibleFreq()
	{
		Integer freq = null;
		
		try
		{

			File myFile = new File(CPU2_MIN_FREQ_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			freq = Integer.parseInt(aBuffer.trim());
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return freq;

	}
	
	/**
	 * Get min possible frequency of the CPU3
	 * @return Integer
	 * */
	public static Integer getCpu3MinPossibleFreq()
	{
		Integer freq = null;
		
		try
		{

			File myFile = new File(CPU3_MIN_FREQ_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			freq = Integer.parseInt(aBuffer.trim());
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return freq;

	}
	
	/**
	 * Get current max frequency of the CPU0
	 * @return Integer
	 * */
	public static Integer getCpu0CurrentMaxFreq()
	{
		Integer freq = null;
		
		try
		{

			File myFile = new File(CPU0_CURRENT_MAX_FREQ_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			freq = Integer.parseInt(aBuffer.trim());
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return freq;

	}
	/**
	 * Get current max frequency of the CPU1
	 * @return Integer
	 * */
	public static Integer getCpu1CurrentMaxFreq()
	{
		Integer freq = null;
		
		try
		{

			File myFile = new File(CPU1_CURRENT_MAX_FREQ_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			freq = Integer.parseInt(aBuffer.trim());
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return freq;

	}
	/**
	 * Get current max frequency of the CPU2
	 * @return Integer
	 * */
	public static Integer getCpu2CurrentMaxFreq()
	{
		Integer freq = null;
		
		try
		{

			File myFile = new File(CPU2_CURRENT_MAX_FREQ_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			freq = Integer.parseInt(aBuffer.trim());
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return freq;

	}
	
	/**
	 * Get current max frequency of the CPU3
	 * @return Integer
	 * */
	public static Integer getCpu3CurrentMaxFreq()
	{
		Integer freq = null;
		
		try
		{

			File myFile = new File(CPU3_CURRENT_MAX_FREQ_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			freq = Integer.parseInt(aBuffer.trim());
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return freq;

	}
	/**
	 * Get max Possible frequency of the CPU0
	 * @return Integer
	 * */
	public static Integer getCpu0MaxPossibleFreq()
	{
		Integer freq = null;
		
		try
		{

			File myFile = new File(CPU0_MAX_FREQ_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			freq = Integer.parseInt(aBuffer.trim());
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return freq;

	}
	/**
	 * Get max possible frequency of the CPU1
	 * @return Integer
	 * */
	public static Integer getCpu1MaxPossibleFreq()
	{
		Integer freq = null;
		
		try
		{

			File myFile = new File(CPU1_MAX_FREQ_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			freq = Integer.parseInt(aBuffer.trim());
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return freq;

	}
	/**
	 * Get max possible frequency of the CPU2
	 * @return Integer
	 * */
	public static Integer getCpu2MaxPossibleFreq()
	{
		Integer freq = null;
		
		try
		{

			File myFile = new File(CPU2_MAX_FREQ_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			freq = Integer.parseInt(aBuffer.trim());
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return freq;

	}
	
	/**
	 * Get max possible frequency of the CPU3
	 * @return Integer
	 * */
	public static Integer getCpu3MaxPossibleFreq()
	{
		Integer freq = null;
		
		try
		{

			File myFile = new File(CPU3_MAX_FREQ_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			freq = Integer.parseInt(aBuffer.trim());
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return freq;

	}
	/**
	 * Get list of all available governors
	 * @return list of governors as List<String>*/
	public static List<String> getGovernorList()
	{
		List<String> governors = new ArrayList<String>();


		try
		{

			File myFile = new File(GOVERNORS_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			governors = Arrays.asList(aBuffer.split("\\s"));

			myReader.close();

		}
		catch (Exception e)
		{

		}
		return governors;

	}
	
	/**
	 * 
	 * @return current governor of the CPU0 or null
	 * */
	public static String getCpu0CurrentGovernor()
	{
		String gov = null;
		
		try
		{

			File myFile = new File(CPU0_CURR_GOV_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			gov = aBuffer.trim();
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return gov;

	}
	
	/**
	 * 
	 * @return current governor of the CPU1 or null
	 * */
	public static String getCpu1CurrentGovernor()
	{
		String gov = null;
		
		try
		{

			File myFile = new File(CPU1_CURR_GOV_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			gov = aBuffer.trim();
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return gov;

	}
	
	/**
	 * 
	 * @return current governor of the CPU2 or null
	 * */
	public static String getCpu2CurrentGovernor()
	{
		String gov = null;
		
		try
		{

			File myFile = new File(CPU2_CURR_GOV_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			gov = aBuffer.trim();
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return gov;

	}
	
	/**
	 * 
	 * @return current governor of the CPU3 or null
	 * */
	public static String getCpu3CurrentGovernor()
	{
		String gov = null;
		
		try
		{

			File myFile = new File(CPU3_CURR_GOV_FILE_PATH);
			FileInputStream fIn = new FileInputStream(myFile);

			BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null)
			{
				aBuffer += aDataRow + "\n";
			}
			gov = aBuffer.trim();
			myReader.close();

		}
		catch (Exception e)
		{

		}
		return gov;

	}
	
	static class MyComparator implements Comparator<Integer>{
		  public int compare(Integer ob1, Integer ob2){
		   return freqList.get(ob1) - freqList.get(ob2) ;
		  }
		}
	
	
}
