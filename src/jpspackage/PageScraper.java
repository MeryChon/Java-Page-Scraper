package jpspackage;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;

public class PageScraper {
	public List<String> scrapedElements;
	public static final String LINK = "a";   
	public static final String IMAGE = "img";
	public static final String LINK_AND_IMAGE = "a.img";
	private static final String ATTR_HREF = "href";
	private static final String ATTR_SRC = "src";
	
	public PageScraper() {
		
	}
	
	/*
	 * Scrape all the links from the page and save their hyperlink references.
	 * */
	public void ScrapeLinks(Elements scrapedLinksRaw) {
		for(Element elem: scrapedLinksRaw) {
			if(elem.hasAttr(ATTR_HREF))
				this.scrapedElements.add(elem.absUrl(ATTR_HREF));
		}
	}
	
	/*
	 * Scrape all the images from the page and save their source urls; 
	 * */
	public void ScrapeImages(Elements scrapedImagesRaw) {
		for(Element elem: scrapedImagesRaw) {
			if(elem.hasAttr(ATTR_SRC))
				this.scrapedElements.add(elem.absUrl(ATTR_SRC));
		}
	}	
	
	/*
	 * Separate method for scraping both links and images.
	 * This way we get to save links and images in order of 
	 * their appearance on the page.
	 * */
	public void ScrapeLinksAndImages(Elements scrapedLinksAndImagesRaw) {
		int counterl = 0;
		int counteri = 0;
		for (Element e: scrapedLinksAndImagesRaw) {
			
			if(e.hasAttr("href")) {
				counterl++;
				this.scrapedElements.add(e.absUrl(ATTR_HREF));
			} else if(e.hasAttr("src")) {
				counteri++;
				this.scrapedElements.add(e.absUrl(ATTR_SRC));
			} 
		}
		System.out.println("LINKS "+counterl+ "IMAGES "+counteri);
	}
	
		
	public void ScrapeURL(String url, String tagType) {		
		this.scrapedElements = new ArrayList<String>();
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(60000).maxBodySize(0).get();		
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not connect with the specified URL."); //TODO error log
			return;
		}
		
		switch(tagType) {
		case LINK:
			System.out.println("ONLY LINK");
			this.ScrapeLinks(doc.getElementsByTag(LINK));
			break;
		case IMAGE:
			System.out.println("ONLY IMAGE");
			this.ScrapeImages(doc.getElementsByTag(IMAGE));
			break;
		case LINK_AND_IMAGE:
			System.out.println("SCRAPIN!!!!!!!!1");
			this.ScrapeLinksAndImages(doc.select(IMAGE+", "+LINK));
			break;
		default:
			System.out.println("This app does not support scraping for given tag"); //TODO error log?
			return;
		}
		
		
//		int count = 0;
//		System.out.println(this.scrapedElements.size());
//		for(String s: this.scrapedElements) {
//			System.out.println(++count + s);
//		}
	}
	
	
	public ArrayList<String> GetScrapedElements() {
		return new ArrayList<String>(this.scrapedElements);
	}
	
	public void WriteParsedDataToFile(String fileName) {
		
	}

	

}
