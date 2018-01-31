package jpspackage;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Set;
import java.util.ArrayList;

public class PageScraper {
	private Set<String> scrapedElements;
	public static final String LINK = "a";   //TODO: why static??
	public static final String IMAGE = "img";
	private static final String ATTR_HREF = "href";
	private static final String ATTR_SRC = "src";
	
	public PageScraper() {
		this.scrapedElements = new HashSet<String>();
	}
	
	public void ScrapeLinks(Elements scrapedLinksRaw) {
		for(Element elem: scrapedLinksRaw) {
			this.scrapedElements.add(elem.attr(ATTR_HREF));
		}
	}
	
	public void ScrapeImages(Elements scrapedImagesRaw) {
		for(Element elem: scrapedImagesRaw) {
			this.scrapedElements.add(elem.attr(ATTR_SRC));
		}
	}	
	
	
	public void ParseURL(String url, ArrayList<String> tagNames) {		
		Document doc = null;
		try {
			doc = Jsoup.connect(url).maxBodySize(0).get();		
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not connect to the specified url.");
			return;
		}
		 // Iterate over requested tag names and scrape corresponding elements
		for(String s: tagNames) {
			switch(s) {
			case LINK:
				this.ScrapeLinks(doc.getElementsByTag(LINK));
				break;
			case IMAGE:
				this.ScrapeImages(doc.getElementsByTag(IMAGE));
				break;
			default:
				this.ScrapeLinks(doc.getElementsByTag(LINK));
				this.ScrapeImages(doc.getElementsByTag(IMAGE));
				break;
			}
		}
		
			
		
		System.out.println(this.scrapedElements.size());
		for(String s: this.scrapedElements) {
			System.out.println(s);
		}
	}
	
	public void WriteParsedDataToFile(String fileName) {
		
	}

	

}
