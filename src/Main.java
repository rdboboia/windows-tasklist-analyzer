public class Main {
	public static void main(String[] args) {
		try {
//			new TasksDataCollector();
			new SimplifiedTasksDataCollector(FieldLanguage.ENGLISH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}