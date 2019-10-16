package parsers;

public interface InfoParser {
	
	public static int parseMemUsageInteger(String memoryUsage) {
		String temp = memoryUsage.substring(0, memoryUsage.length()-3);
		temp = temp.replace(".", "");
		
		return Integer.parseInt(temp);
	}
	
	public static int parseCpuTime(String s) {
		int seconds = 0;
		
		String temp = s;
		seconds += 3600 * Integer.parseInt(temp.substring(0, temp.indexOf(':')));
		
		temp = temp.substring(temp.indexOf(':') + 1);
		seconds += 60 * Integer.parseInt(temp.substring(0, temp.indexOf(':')));
		
		temp = temp.substring(temp.indexOf(':') + 1);
		seconds += Integer.parseInt(temp);
		
		return seconds;
	}
}