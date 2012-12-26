/*
 * Copyright (C) 2012 Predrag Čokulov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rs.pedjaapps.cpuctrllibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import android.os.AsyncTask;
import android.util.Log;

public final class SetCPU {

	/**
	 * Change min frequency of the cpu
	 * @param frequency Frequency to set
	 * @param cpu Number of the CPU core on which to set freq(0, 1, 2 or 3)*/
	public static void setMin(int frequency, int cpu){
		new CPUChanger().execute(new String[] {String.valueOf(frequency), String.valueOf(cpu), "scaling_min_freq"});
	}
	/**
	 * Change max frequency of the cpu
	 * @param frequency Frequency to set
	 * @param cpu Number of the CPU core on which to set freq(0, 1, 2 or 3)*/
	
	public static void setMax(int frequency, int cpu){
		new CPUChanger().execute(new String[] {String.valueOf(frequency), String.valueOf(cpu),"scaling_max_freq"});
	}
	/**
	 * Change governor of the cpu
	 * @param governor Governor to set
	 * @param cpu Number of the CPU core on which to set freq(0, 1, 2 or 3)*/
	
	public static void setGovernor(String governor, int cpu){
		new CPUChanger().execute(new String[] {governor, String.valueOf(cpu),"scaling_governor"});
	}
	
	private static class CPUChanger extends AsyncTask<String, Void, String>
	{

		@Override
		protected String doInBackground(String... args)
		{


			
			try {
	            String line;
	            Process process = Runtime.getRuntime().exec("su");
	            OutputStream stdin = process.getOutputStream();
	            InputStream stderr = process.getErrorStream();
	            InputStream stdout = process.getInputStream();

	            stdin.write(("chmod 777 /sys/devices/system/cpu/cpu" + args[1] + "/cpufreq/" + args[2] + "\n").getBytes());
	            stdin.write(("echo " + args[0] + " > /sys/devices/system/cpu/cpu" + args[1] + "/cpufreq/" + args[2] + "\n").getBytes());
	            stdin.write(("chmod 444 /sys/devices/system/cpu/cpu" + args[1] + "/cpufreq/" + args[2] + "\n").getBytes());
	            stdin.write(("chown system /sys/devices/system/cpu/cpu" + args[1] + "/cpufreq/scaling_" + args[2] + "\n").getBytes());
	            
	            stdin.flush();

	            stdin.close();
	            BufferedReader brCleanUp =
	                    new BufferedReader(new InputStreamReader(stdout));
	            while ((line = brCleanUp.readLine()) != null) {
	                Log.d("[CCL Output]", line);
	            }
	            brCleanUp.close();
	            brCleanUp =
	                    new BufferedReader(new InputStreamReader(stderr));
	            while ((line = brCleanUp.readLine()) != null) {
	            	Log.e("[CCL Error]", line);
	            }
	            brCleanUp.close();

	        } catch (IOException ex) {
	        }
			return null;
			
			
		}
	}	


	
}
