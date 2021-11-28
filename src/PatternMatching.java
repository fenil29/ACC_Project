import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

public class PatternMatching {

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

	public static void main(String[] args) throws IOException {
		
		Scanner sc= new Scanner(System.in); 
		ArrayList<String> files = new ArrayList<String>();
		String joinedText = "";
		System.out.println("Enter the pattern you want to find: ");
		
		String pat = sc.nextLine();
		// get the list of all available files
		String htmlFileList[] = getTheNameOfFile("cleanedFile");
		

		for (int i = 0; i < htmlFileList.length; i++) {
			String fileName = htmlFileList[i];

			//System.out.println("Reading File : " + fileName);

			// reading from the file
			joinedText = joinedText + readContentsOfFile(fileName);
			// Create a Pattern object
			Pattern p1 = Pattern.compile(pat);

			// Now create matcher object.
			Matcher m = p1.matcher(joinedText);
			while (m.find()) {
			//	System.out.println("Found Pattern at: " + m.group(0) + " in " + fileName);
				if(!files.contains(fileName)) {
					files.add(fileName);
				}
			}
		}	
		if(files.size()==0) {
			System.out.println("The pattern "+ pat + ", you entered is not found.");
		}else {
			System.out.println("The pattern "+ pat + ", is found in "+files.size()+" web pages below: ");
			for(int i = 0; i < files.size(); i++) {   
			    System.out.println(files.get(i));
			}  
		}
	}
}

