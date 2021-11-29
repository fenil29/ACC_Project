
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Suggestion {
	public static void suggest(String input) throws FileNotFoundException, IOException {
		
		File file = new File("dictionary.txt");
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			ArrayList<String> dictionary = new ArrayList<String>();
			String line;
			
			while((line = reader.readLine())!=null)
				dictionary.add(line);
			reader.close();
			
			int editDistance, minED1=10, minED2=10;
			String word1=null, word2=null;
			
			for(String word : dictionary){
				editDistance = EditDistance.editDistance(word, input);

				if(editDistance < minED2) {
					if(editDistance < minED1) {
						minED1 = editDistance;
						word1 = word;
					}
					else {
						minED2 = editDistance;
						word2 = word;
					}
				}
			}
			System.out.println("Did you mean: "+ word1 +" or "+ word2);
		}
	}
}
