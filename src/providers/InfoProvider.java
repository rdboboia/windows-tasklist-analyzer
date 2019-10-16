package providers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InfoProvider {
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	public InfoProvider() {
		try {
			LOGGER.log(Level.INFO, "Retrieving process list details...");
			Process processDetails = Runtime.getRuntime().exec("TASKLIST /V /FO CSV");
			
			LOGGER.log(Level.INFO, "Retrieving service list details...");
			Process serviceDetails = Runtime.getRuntime().exec("TASKLIST /SVC /FO CSV");
			
			BufferedReader processInput = new BufferedReader(new InputStreamReader(processDetails.getInputStream()));
			BufferedReader processError = new BufferedReader(new InputStreamReader(processDetails.getErrorStream()));
			
			BufferedReader serviceInput = new BufferedReader(new InputStreamReader(serviceDetails.getInputStream()));
			BufferedReader serviceError = new BufferedReader(new InputStreamReader(serviceDetails.getErrorStream()));
			
			String processBuffer = "";
			String processOutput = processInput.readLine();
			
			/* Manage command error */
			if (processOutput == null) {
				processBuffer = processError.readLine();

				if (processBuffer == null)
					throw new Exception("An unexpected error has ocurred.");

				String errorMsg = "";
				while (processBuffer != null) {
					errorMsg += processBuffer;
					processBuffer = processError.readLine();
				}

				throw new Exception("An error ocurred during command execution. Received error message:\n" + errorMsg);

			} else {
				System.out.println(processOutput);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getTasklistDetailsStream() {
		
	}
}