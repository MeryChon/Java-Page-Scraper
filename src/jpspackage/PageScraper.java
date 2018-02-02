package jpspackage;

import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;

public class PageScraper {
	public static final String LINK = "a";   
	public static final String IMAGE = "img";
	public static final String LINK_AND_IMAGE = "a.img";
	private static final String ATTR_HREF = "href";
	private static final String ATTR_SRC = "src";
	public static final int CONNECTION_STATUS_OK = 0;
	public static final int CONNECTION_STATUS_FAILED = 1;
	public static final int ERR_INCORRECT_TAG = 2;
	public static final int ERR_UNSUCCSESSFULL_GET = 3;
	
	public List<String> scrapedElements = null;

	
	public PageScraper() {
		
	}
	
	/*
	 * Scrape all the links from the page and save their hyperlink references.
	 * */
	private void ScrapeLinks(Elements scrapedLinksRaw) {
		for(Element elem: scrapedLinksRaw) {
			if(elem.hasAttr(ATTR_HREF))
				this.scrapedElements.add(elem.absUrl(ATTR_HREF));
		}
	}
	
	/*
	 * Scrape all the images from the page and save their source urls; 
	 * */
	private void ScrapeImages(Elements scrapedImagesRaw) {
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
	private  void ScrapeLinksAndImages(Elements scrapedLinksAndImagesRaw) {
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
	
	/**
	 * Gets page with given url; Returns elements with requested tags (links and/or images);
	 * @param url
	 * @param tagType
	 * @return
	 */
	public int ScrapeURL(String url, String tagType) {		
		if(!CheckURL(url)) return CONNECTION_STATUS_FAILED; 
		this.scrapedElements = new ArrayList<String>();
		Document doc = null;
		Connection conn = null;
		try {
			conn = Jsoup.connect(url);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not connect with the specified URL.");
			return CONNECTION_STATUS_FAILED;
		}
		
		try {
			doc = conn.timeout(60000).maxBodySize(0).get();
		} catch (IOException e) {
			e.printStackTrace();
			return ERR_UNSUCCSESSFULL_GET;
		}
		
		switch(tagType) {
		case LINK:
			this.ScrapeLinks(doc.getElementsByTag(LINK));
			break;
		case IMAGE:
			this.ScrapeImages(doc.getElementsByTag(IMAGE));
			break;
		case LINK_AND_IMAGE:
			this.ScrapeLinksAndImages(doc.select(IMAGE+", "+LINK));
			break;
		default:
			System.out.println("This app does not support scraping for given tag");
			return ERR_INCORRECT_TAG;
		}
		return CONNECTION_STATUS_OK;
	}
	
	private boolean CheckURL(String url) {
		return (url.startsWith("http://") && url.length() > 7) || (url.startsWith("https://") && url.length() > 8);
	}

	/**
	 * Returns a copy of scraped elements list;
	 * */
	public ArrayList<String> GetScrapedElements() {
		return new ArrayList<String>(this.scrapedElements);
	}
	
	/**
	 * 
	 * @param fileName
	 */
	public void WriteParsedDataToFile(String fileName) {
		
	}

	

}
