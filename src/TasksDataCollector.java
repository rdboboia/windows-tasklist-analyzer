import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class TasksDataCollector {
	private int nFields;
	private String[] fieldNames;
	
	/**
	 * Executes the "TASKLIST /V /FO LIST" command and collects its output.
	 * Provides methods to analyze that data from different perspectives like
	 * sorting by PID, memory usage or name.
	 * 
	 * Note: generic; puts effort to avoid failing at the slightest input change.
	 * @throws Exception
	 */
	public TasksDataCollector() throws Exception {
		Process proc = Runtime.getRuntime().exec("TASKLIST /V /FO LIST");

		BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		BufferedReader error = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		String buffer = "";
		String output = input.readLine();
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
				
		} else {
			ArrayList<String> tempData = new ArrayList<String>();
			buffer = output;
			
			/* Gets rid of empty lines at the beggining (if exists) */
			while (buffer.equals("")) {
				buffer = input.readLine();
			}
			
			while (!buffer.equals("")) {
				tempData.add(buffer);
				buffer = input.readLine();
			}
			
			nFields = 0;
			Iterator<String> it = tempData.iterator();
			while (it.hasNext()) {
				it.next();
				nFields++;
			}
			
			fieldNames = new String[nFields];
			String temp;
			int i = 0;
			it = tempData.iterator();
			while(it.hasNext()) {
				temp = it.next();
				fieldNames[i] = temp.substring(0, temp.indexOf(":"));
			}
		}
	}
}