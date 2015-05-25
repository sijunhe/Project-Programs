import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class loadData {
	private static String fileName;

	public loadData(String fileName) {
		this.fileName = fileName;
	}

	static ArrayList<Integer> readFile() throws FileNotFoundException {
		ArrayList<Integer> data = new ArrayList();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line;
			while((line = br.readLine())!=null){
				String num = line;
				data.add(Integer.valueOf(num));
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return data;
	}
}
