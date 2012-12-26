#CPU Control Library for Android
Nothing fancy here, just a set of methods (so that you don't have to write everything by yourself) for retrieving CPU info and also setting CPU options(min frequency, max frequency, governor, etc.)

#Usage
Everything is really simple and self explanatory

##1. Retrieving CPU Information(CPUInfo.class)
Use following methods for retrieving CPU information

1. **getFrequencyList()**

	Gets list of all frequencies either from **scaling_available_frequencies** or **times_in_state**
	
	Returns List<Integer>
		
```java
   ...
   import rs.pedjaapps.cpuctrllibrary.CPUInfo;
   List<Integer> frequencyList = CPUInfo.getFrequencyList();
   ...
```
			
2. **getCpu*CurrentMinFreq()**

	Where "*" is number of cpu core(could be 0,1,2,3)
		
	Gets current min frequency of the cpu from **scaling_min_freq**
	
	Returns Integer
		
```java
	Integer cpu0MinFreq = CPUInfo.getCpu0CurrentMinFreq();
```		


3. **getCpu*CurrentMaxFreq()**

	Where "*" is number of cpu core(could be 0,1,2,3)
		
	Gets current max frequency of the cpu from **scaling_max_freq**
	
	Returns Integer	
		
		
		
4. **getCpu*MinPossibleFreq()**

	Where "*" is number of cpu core(could be 0,1,2,3)
		
	Gets min possible frequency from **cpuinfo_min_freq**
	
	Returns Integer
		
		
		
5. **getCpu*MaxPossibleFreq()**

	Where "*" is number of cpu core(could be 0,1,2,3)
		
	Gets max possible frequency from **cpuinfo_min_freq**
	
	Returns Integer
	
	
	
6. **getGovernorList()**

	Gets list of all governors from **scaling_available_governors**
	
	Returns List<String>
	
	
	
7. **getCpu*CurrentGovernor()**

	Where "*" is number of cpu core(could be 0,1,2,3)
		
	Gets current governor of the cpu from **scaling_governor**
	
	Returns String
		
	
		
##2. Changing CPU Configuration(SetCPU.class)##

This class uses AsyncTask class for executing root commands in the background
	
###Methods:###
	
	
1. **setMin(int frequency, int cpu)**

	frequency - Frequency to set
	
	cpu - cpu core number(0, 1, 2, 3)

		
2. **setMax(int frequency, int cpu)**

	frequency - Frequency to set
	
	cpu - cpu core number(0, 1, 2, 3)		
	
	
1. **setGovernor(int governor, int cpu)**

	governor - Governor to set
	
	cpu - cpu core number(0, 1, 2, 3)
	
	
You can either download pre compiled .jar library or clone this git and use it as library project
	
	
	
	
	
	
	
	
	
	
	
	
	