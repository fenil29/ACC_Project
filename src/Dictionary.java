import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Dictionary {
	
	public static void createDictionary() {
		String line, token;
		
		File folder = new File("cleanedFile/");
		File[] files = folder.listFiles();
		Set<String> set = new HashSet<>();
		ArrayList<String> dictionary = new ArrayList<String>();
		
		for (File file : files)
		{
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				while((line = reader.readLine()) != null) {
					@SuppressWarnings("resource")
					Scanner scan = new Scanner(line);
						while (scan.hasNext()) {
							token = scan.next().replaceAll("[^a-zA-Z ]", "").toLowerCase();
							set.add(token);
						}
					}
					reader.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		dictionary.addAll(set);
		Collections.sort(dictionary);
		
		try {
			FileWriter writer = new FileWriter("dictionary.txt"); 
			for(String word: dictionary) 
				writer.write(word + System.lineSeparator());	
			writer.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Dictionary.createDictionary();
		System.out.println("Dictionary created.");
	}
}
