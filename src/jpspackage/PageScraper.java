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
	public final String LINK = "a";   //TODO: const??
	public final String IMAGE = "img";
	
	public PageScraper() {
		this.scrapedElements = new HashSet<String>();
	}
	
	public void ScrapeLinks(Elements scrapedLinksRaw) {
		for(Element elem: scrapedLinksRaw) {
			if(this.scrapedElements.contains(elem.attr("href"))) {
				System.out.println("DUPLICATE");
			}
			this.scrapedElements.add(elem.attr("href"));
		}
	}
	
	public void ScrapeImages(Elements scrapedImagesRaw) {
		for(Element elem: scrapedImagesRaw) {
			if(this.scrapedElements.contains(elem.attr("src"))) {
				System.out.println("DUPLICATE" + elem.attr("src"));
			}
			System.out.println("next element " + elem.attr("src"));
			this.scrapedElements.add(elem.attr("src"));
		}
	}	
	
	
	public void ParseURL(String url, ArrayList<String> tagNames) {		
		Document doc;
		for(String s: tagNames) {
			System.out.println(s);
		}
		try {
			doc = Jsoup.connect(url).maxBodySize(10*1024*1024).get();
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
				
		
//				Elements scrapedElementsRaw = doc.getElementsByTag(s);
//				doc.getElementsByAttribute("href");
//				Elements scrapedElementsRaw = doc.getElementsByAttribute("href");
//				for(Element elem: scrapedElementsRaw) {
//					this.scrapedElements.add(elem.attr("href"));
//				}
			}
			System.out.println(this.scrapedElements.size());
			for(String s: this.scrapedElements) {
				System.out.println(s);
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void WriteParsedDataToFile(String fileName) {
		
	}

	

}
