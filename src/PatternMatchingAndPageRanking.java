import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.HashMap; 
import java.util.stream.Collectors;
import java.util.stream.Collectors.*;

public class PatternMatchingAndPageRanking {

	// read and return the text of given file
	public static String readContentsOfFile(String fileName) throws IOException {
		String text = readFile("./cleanedFile/" + fileName, StandardCharsets.US_ASCII);
		return text;
	}

	// return the list of all files of given folder
	public static String[] getTheNameOfFile(String folderName) {

		// Reading given file
		File dirPath = new File(folderName);

		// List of all files and directories
		String fileNames[] = dirPath.list();
		//System.out.println("The specified Path contains directories and folders mentioned here:");
		return fileNames;

	}
	
	// read the file from the given path and return the text
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	public static Hashtable<String, Integer> matchPattern(String pattern) throws IOException{
		Hashtable<String, Integer> page_rank = new Hashtable<String, Integer>();
		
		String joinedText = "";
		
		// get the list of all available files
		String htmlFileList[] = getTheNameOfFile("cleanedFile");
		for (int i = 0; i < htmlFileList.length; i++) {
			String fileName = htmlFileList[i];

			//System.out.println("Reading File : " + fileName);

			// reading from the file
			joinedText = readContentsOfFile(fileName);
			// Create a Pattern object
			Pattern p1 = Pattern.compile(pattern);

			// Now create matcher object.
			Matcher m = p1.matcher(joinedText);
			
			while (m.find()) 
				page_rank.merge(fileName, 1, Integer::sum);
			
		}
		return page_rank;
	}

	public static void main(String[] args) throws IOException {
		
		Scanner sc= new Scanner(System.in); 
		Hashtable<String, Integer> page_rank = new Hashtable<String, Integer>();
		String joinedText = "";
		System.out.println("Enter the pattern you want to find: ");
		
		String pat = sc.nextLine();
		// get the list of all available files
		String htmlFileList[] = getTheNameOfFile("cleanedFile");
		

		for (int i = 0; i < htmlFileList.length; i++) {
			String fileName = htmlFileList[i];

			//System.out.println("Reading File : " + fileName);

			// reading from the file
			joinedText = readContentsOfFile(fileName);
			// Create a Pattern object
			Pattern p1 = Pattern.compile(pat);
			int count=1;
			// Now create matcher object.
			Matcher m = p1.matcher(joinedText);
			int occ = 1;
			while (m.find()) {
			//System.out.println("Found Pattern at: " + m.group(0) + " in " + fileName);
//				if(!page_rank.contains(fileName)) {
//					page_rank.put(fileName,1);
//				} else {
//					page_rank.put(fileName, page_rank.get(fileName) + 1);
//				}
				page_rank.merge(fileName, 1, Integer::sum);
			}
		}	
		if(page_rank.size()==0) {
			System.out.println("The pattern "+ pat + ", you entered is not found.");
		}else {
			System.out.println("The pattern "+ pat + ", is found in "+page_rank.size()+" web pages below: ");
			System.out.println("--------- Page || Occurence ------------");
			//page_rank.forEach((key, value) -> System.out.println(key + " " + value));    
			Map<String, Integer> sortedByValueDesc = page_rank.entrySet() 
					.stream() 
					.sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) 
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
			sortedByValueDesc.forEach((key, value) -> System.out.println(key + " || " + value));   

		}
	}
}
