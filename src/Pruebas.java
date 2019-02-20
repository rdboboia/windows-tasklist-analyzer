
public class Pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str = "600 KB";
		System.out.println(str.substring(0, str.length()-3));
		
		str = "20:44:17";
		System.out.println(str.substring(0, str.indexOf(':')));
		System.out.println(str.substring(str.indexOf(':') + 1));
		
		System.out.println("==========================");
		
		try {
			SimplifiedTasksDataCollector s = new SimplifiedTasksDataCollector(FieldLanguage.ENGLISH);
			System.out.println(s.getTotalMemoryUsage());
			
			/*
			 * Preventing the app from closing for inspection in execution.
			 */
//			Thread.sleep(1000000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
