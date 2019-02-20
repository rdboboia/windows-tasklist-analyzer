import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
	private int[] cpuTime;
	private String[] windowTitle;
	
	public SimplifiedTasksDataCollector(FieldLanguage lang) throws Exception {
		Process proc = Runtime.getRuntime().exec("TASKLIST /V /FO LIST");

		BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		BufferedReader error = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		String buffer = "";
		String output = input.readLine();
		
		/*
		 * TODO: divide the constructor in sub-functions (simplification)
		 */
		
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
			
			buffer = output;
			/* Gets rid of empty lines at the beginning (if exists) */
			while (buffer.equals("")) {
				buffer = input.readLine();
			}
		}
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

}