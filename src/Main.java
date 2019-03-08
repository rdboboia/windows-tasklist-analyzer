public class Main {
	public static void main(String[] args) {
		try {
//			new TasksDataCollector();
			System.out.println(new SimplifiedTasksDataCollector(FieldLanguage.ENGLISH).getTotalMemoryUsage() + " KB.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}