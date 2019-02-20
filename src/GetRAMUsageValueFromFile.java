import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class GetRAMUsageValueFromFile {
	// TODO: optimizar
	public static void main(String[] args) {
		try {
			List<String> list = Files.readAllLines(Paths.get("C:\\out.txt"));
			
			Iterator<String> i = list.iterator();
			String line = "";
			String num = "";
			int lastIndexOf = 0;
			int number = 0;
			int total = 0;
			while(i.hasNext()) {
				line = i.next();
				num = "";
				
				for (int j = 0 ; j < line.length() ; j++) {
					if (line.charAt(j) == '0' && line.charAt(j-1) == ' ')
						lastIndexOf = j;
				}
				
				line = line.substring(lastIndexOf + 1).trim();
				line = line.substring(0, line.length()-3);
				
				for (int j = 0 ; j < line.length() ; j++)
					if (line.charAt(j) != '.')
						num += line.charAt(j);
				
				number = Integer.parseInt(num);
				total += number;
				
				System.out.println(total + " KB");
			}
		} catch (IOException e) {
			System.out.println("Can't read input file!");
			e.printStackTrace();
		}
	}

}
