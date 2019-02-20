
public class PruebaJavaSleepTimer {

	public static void main(String[] args) {
		System.out.println(Long.MAX_VALUE);
		
		try {
			while(true) {
				new Thread() {
					public void run() {
						int i = 0;
						while (i < 1000000) {
							i++;
							
							if (i % 999999 == 0)
								i += 2;
						}
					}
				}.start();
				
				System.out.println(System.currentTimeMillis());
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}