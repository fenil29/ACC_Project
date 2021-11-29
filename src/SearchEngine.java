
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SearchEngine {
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("------  SEARCH ENGINE  ------\n");
		Long startTime, endTime;
		float totalTime;
		System.out.print("Enter query: ");
		String input = sc.nextLine();
		
		// converting input in lowercase
		//input= input.toLowerCase();
		
		sc.close();
		
		// Search the input pattern
		startTime = System.currentTimeMillis();
		Hashtable<String, Integer> page_rank = PatternMatching.matchPattern(input);
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
			
		// show alternate words if no matches are found
		if(page_rank.size()==0)	
			Suggestion.suggest(input);
			
		
		// display the results according to their occurrences in file
		else {
			// print results according to the frequency of
			System.out.println("Just a sec....\n");
			
			int totalOccurences=0;
			for(int occurences : page_rank.values())
				totalOccurences += occurences;
			
			System.out.println("About "+totalOccurences+" matches ("+totalTime/1000+" seconds)");
			System.out.println("Matches found in "+page_rank.size()+" web pages.\n");
			System.out.println("Matches\t   Page");
			
			// page ranking and displaying results accordingly
			Map<String, Integer> sortedByValueDesc = page_rank.entrySet() 
					.stream() 
					.sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) 
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
			sortedByValueDesc.forEach((key, value) -> System.out.println("  "+value + " ----- " + key));  
		}
	}
}
