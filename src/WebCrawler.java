import java.io.FileWriter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;

public class WebCrawler {

	// write array content into the given file
	public static void writeToFile(String fileName, String content) {
		try {
			FileWriter writer = new FileWriter("./HTML/" + fileName + ".html");
			writer.write(content);
			writer.close();
		} catch (IOException e) {
		}

	}
	// crawl website with the given length upto given depth and save it in folder with title as name of file
	public static void  crawlWebsite(String URL,int crawlingDepth) throws Exception {
		Set<String> visited = new HashSet<String>();
		ArrayList<String> links = new ArrayList<String>();
		ArrayList<Integer> depth = new ArrayList<Integer>();
		// add current website to crawling queue		
		links.add(URL);
		depth.add(0);
		int currnetDepth = 0;
		for (int i = 0; i < links.size(); i++) {
			
			// exit the loop if the given depth is reached			
			if(depth.get(i)>crawlingDepth) {
				break;
			}
			String link = links.get(i);
				
			// skip the loop if the current link is already visited			
			if (visited.contains(link)) {
				continue;
			}
			visited.add(link);
			System.out.println("Crawling Website : " + link);
			try {
				// getting the link				
				Document document = Jsoup.connect(link).get();	
				
				// save the content of website in folder				
				writeToFile(document.title(), document.html());
				Elements linksOnPage = document.select("a[href]");
				currnetDepth++;
				
				// add all hyperlink to the crawling list 				
				for (Element page : linksOnPage) {
					links.add(page.attr("abs:href"));
					depth.add(currnetDepth);
				}
			} catch (IOException e) {
				//System.out.println("Error:" + e);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		int crawlingDepth=1;
		crawlWebsite("https://www.geeksforgeeks.org/",crawlingDepth);
	}
}
