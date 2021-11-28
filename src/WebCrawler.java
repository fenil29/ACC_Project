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

	public static void  crawlWebsite(String URL) throws Exception {
		Set<String> visited = new HashSet<String>();
		ArrayList<String> links = new ArrayList<String>();
		ArrayList<Integer> depth = new ArrayList<Integer>();
		links.add(URL);
		depth.add(0);
		int currnetDepth = 0;
		for (int i = 0; i < links.size(); i++) {
			if(depth.get(i)>1) {
				break;
			}
			String link = links.get(i);
			
			if (visited.contains(link)) {
				continue;
			}
			visited.add(link);
			System.out.println("Crawling Website : " + link);
			try {
				Document document = Jsoup.connect(link).get();				
				writeToFile(document.title(), document.html());
				Elements linksOnPage = document.select("a[href]");
				currnetDepth++;
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
		crawlWebsite("https://www.fenilkaneria.com");
	}
}
