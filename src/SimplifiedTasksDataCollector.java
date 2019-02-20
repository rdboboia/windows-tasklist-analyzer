import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SimplifiedTasksDataCollector {
	private final int nFields = 9;
	private final String[] fieldNames;
	
	private int size = 10000;
	
	private String[] imageName;
	private int[] pid;
	private String[] sessionName;
	private int[] sessionNumber;
	private int[] memUsage;
	private String[] state;
	private String[] userName;
	/**
	 * Expressed in seconds.
	 */
	private int[] cpuTime;
	private String[] windowTitle;
	
	private int numElems;
	
	/*
	 * Instead of running the command itself it takes a file as an input and parses it.
	 */
	public SimplifiedTasksDataCollector(String filePath, FieldLanguage lang) throws Exception {
		// TODO: implementation
		
		fieldNames = new String[nFields];
		setFieldNames(lang);
	}
	
	/*
	 * TODO: divide the constructor in sub-functions (simplification)
	 */
	public SimplifiedTasksDataCollector(FieldLanguage lang) throws Exception {
		Process proc = Runtime.getRuntime().exec("TASKLIST /V /FO LIST");

		BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		BufferedReader error = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		String buffer = "";
		String output = input.readLine();
		
		/* Manage command error */
		if (output == null) {
			buffer = error.readLine();

			if (buffer == null)
				throw new Exception("An unexpected error has ocurred.");

			String errorMsg = "";
			while (buffer != null) {
				errorMsg += buffer;
				buffer = error.readLine();
			}

			throw new Exception("An error ocurred during command execution. Received error message:\n" + errorMsg);

		} 
		/* Expected successful path */
		else {
			fieldNames = new String[nFields];
			setFieldNames(lang);
			
			imageName = new String[size];
			pid = new int[size];
			sessionName = new String[size];
			sessionNumber = new int[size];
			memUsage = new int[size];
			state = new String[size];
			userName = new String[size];
			cpuTime = new int[size];
			windowTitle = new String[size];
			
//			/* Gets rid of empty lines at the beginning (if exists) */
//			while (buffer.equals("")) {
//				buffer = input.readLine();
//			}
			
			numElems = 0;
			do {
				imageName[numElems] = parseString(input.readLine());
				pid[numElems] = parseInteger(input.readLine());
				sessionName[numElems] = parseString(input.readLine());
				sessionNumber[numElems] = parseInteger(input.readLine());
				memUsage[numElems] = parseMemUsageInteger(input.readLine());
				state[numElems] = parseString(input.readLine());
				userName[numElems] = parseString(input.readLine());
				cpuTime[numElems] = parseCpuTime(input.readLine());
				windowTitle[numElems] = parseString(input.readLine());
				numElems++;
			} while(input.readLine() != null);
		}
	}
	
	private String parseString(String s) {
		return s.substring(s.indexOf(':') + 1).trim();
	}
	
	private int parseInteger(String s) {
		return Integer.parseInt(s.substring(s.indexOf(':') + 1).trim());
	}
	
	private int parseMemUsageInteger(String s) {
		String temp = s.substring(s.indexOf(':') + 1).trim();
		temp = temp.substring(0, temp.length()-3);
		temp = temp.replace(".", "");
		
		return parseInteger(temp);
	}
	
	private int parseCpuTime(String s) {
		int seconds = 0;
		
		String temp = parseString(s);
		seconds += 3600 * Integer.parseInt(temp.substring(0, temp.indexOf(':')));
		
		temp = temp.substring(temp.indexOf(':') + 1);
		seconds += 60 * Integer.parseInt(temp.substring(0, temp.indexOf(':')));
		
		temp = temp.substring(temp.indexOf(':') + 1);
		seconds += Integer.parseInt(temp);
		
		return seconds;
	}
	
	
	private void setFieldNames(FieldLanguage lang) throws Exception {
		if (lang == FieldLanguage.ENGLISH) {
			fieldNames[0] = "Image name";
			fieldNames[1] = "PID";
			fieldNames[2] = "Session name";
			fieldNames[3] = "Session number";
			fieldNames[4] = "Memory usage";
			fieldNames[5] = "State";
			fieldNames[6] = "User name";
			fieldNames[7] = "CPU time";
			fieldNames[8] = "Window title";
		} else if (lang == FieldLanguage.ESPAÑOL) {
			fieldNames[0] = "Nombre de imagen";
			fieldNames[1] = "PID";
			fieldNames[2] = "Nombre de sesión";
			fieldNames[3] = "Número de sesión";
			fieldNames[4] = "Uso de memoria";
			fieldNames[5] = "Estado";
			fieldNames[6] = "Nombre de usuario";
			fieldNames[7] = "Tiempo de CPU";
			fieldNames[8] = "Título de ventana";
		} else {
			throw new Exception("Language not supported!");
		}
	}
	
	/*
	 * Public methods
	 */
	
	/**
	 * 
	 * @return the total amount of memory used by all the tasks (processes)
	 * expressed in KB.
	 */
	public int getTotalMemoryUsage() {
		int total = 0;
		for (int i = 0 ; i < numElems ; i++)
			total += memUsage[i];
		
		return total;
	}
}