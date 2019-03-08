public class Main {
	public static void main(String[] args) {
		try {
//			new TasksDataCollector();
			System.out.println(new SimplifiedTasksDataCollector(FieldLanguage.ENGLISH).getTotalMemoryUsage() + " KB.");
			new SimplifiedTasksDataCollector(FieldLanguage.ENGLISH).printMemoryUsagePerApp();
			System.out.println(Runtime.getRuntime().totalMemory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}