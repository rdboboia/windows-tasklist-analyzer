import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Mostly for testing purposes.
 */
public class GetMoreDetailedInfoFromCMD {

	public static void main(String[] args) {
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("tasklist /V /FO LIST").getInputStream()));
			String s = "";
			String buffer = r.readLine();
			
			/* Getting rid of the first empty line */
			buffer = r.readLine();
			
			while(buffer != null) {
				s += buffer + "\n";
				System.out.println(buffer);
				System.out.println(buffer.equals(""));
				System.out.println(buffer.indexOf(":"));
				System.out.println(buffer.substring(0, buffer.indexOf(":")));
				System.out.println(buffer.substring(buffer.indexOf(':') + 1).trim());				
				System.out.println("=====================================");
				
				buffer = r.readLine();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			/*
			 * Leer como lista
			 * 
			 * Crear un buffer de 20 para guardar el primer bloque de elementos (Se mide el 
			 * tamaño hasta el siguiente espacio para saber cuántos elementos hay)
			 * 
			 * Usar una matriz para guardar todos los datos y poder mostrarlos de diferentes
			 * formas posteriormente (ordenador por memoria, tiempo de cpu, etc)
			 */
			
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
