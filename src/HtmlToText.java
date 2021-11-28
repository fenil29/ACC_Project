import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class HtmlToText {
    // read and return the content of given file
	public static String readFileContent(String fileName) throws IOException {
		String content = readFile("./HTML/" + fileName, StandardCharsets.US_ASCII);
		return content;
	}
	
	//  clean the given string and return it.
	public static String cleanHtml(String htmlString) {
		// Cleaning file using Jsoup		
		Document doc = Jsoup.parse( htmlString );
		return doc.text();
	}

	//  return the list of all files of given folder
	public static String[] getAllFileName(String folderName) {

		// Reading given file
		File directoryPath = new File(folderName);

		// List of all files and directories
		String fileNames[] = directoryPath.list();
		System.out.println("List of files and directories in the specified directory:");
		return fileNames;

	}

	//	read the file from the given path and return the content
	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	// write array content into the given file
	public static void writeToFile(String fileName, String content) throws IOException {

		FileWriter writer = new FileWriter("./cleanedFile/" + fileName.substring(0, fileName.lastIndexOf(".")) + ".txt");
		writer.write(content);
		writer.close();
	}

	public static void main(String[] args) throws IOException {
		// get the list of all available files
		String htmlFileList[] = getAllFileName("HTML");

		for (int i = 0; i < htmlFileList.length; i++) {
			String fileName = htmlFileList[i];

			System.out.println("Processing file : " + fileName);

			// reading from the file
			String fileContent = readFileContent(fileName);

			// cleaning the content of the file
			System.out.println("Cleaning...");
			String clenedfileContent = cleanHtml(fileContent);

			// Storing the clean content into the file
			System.out.println("Storing...");
			writeToFile(fileName, clenedfileContent);
		}
	}
}
