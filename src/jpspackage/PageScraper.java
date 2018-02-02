package jpspackage;

import java.io.IOException;
//import java.sql.Date;
import java.sql.SQLException;
//import java.util.List;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;

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
	
//	private List<String> scrapedElements = null;
//	private Set<DBEntry> dbEntries = null; 
	private Set<String> scrapedLinks = null;
	private Set<String> scrapedImages = null;
//	private java.util.Date currDate;
	private DBObject dbObj;

	
	public PageScraper() {
		dbObj = new DBObject();
//		dbEntries = new HashSet<DBEntry>();
	}
	
	/*
	 * Scrape all the links from the page and save their hyperlink references.
	 * */
	private void ScrapeLinks(Elements scrapedLinksRaw) {
		for(Element elem: scrapedLinksRaw) {
			if(elem.hasAttr(ATTR_HREF)) {
				String href = elem.absUrl(ATTR_HREF);
				scrapedLinks.add(href);
			}
		}
	}
	
	/*
	 * Scrape all the images from the page and save their source urls; 
	 * */
	private void ScrapeImages(Elements scrapedImagesRaw) {
		for(Element elem: scrapedImagesRaw) {
			if(elem.hasAttr(ATTR_SRC)) {
				String src = elem.absUrl(ATTR_SRC);
				scrapedImages.add(src);
			}
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
				String href = e.absUrl(ATTR_HREF);
				scrapedLinks.add(href);
//				dbEntries.add(new DBEntry(DBEntry.INFO_LINK, href));
			} else if(e.hasAttr("src")) {
				counteri++;
				String src = e.absUrl(ATTR_SRC);
				scrapedImages.add(src);
//				dbEntries.add(new DBEntry(DBEntry.INFO_IMAGE, src));
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
			scrapedLinks = new HashSet<String>();
			this.ScrapeLinks(doc.getElementsByTag(LINK));
			break;
		case IMAGE:
			scrapedImages = new HashSet<String>();
			this.ScrapeImages(doc.getElementsByTag(IMAGE));
			break;
		case LINK_AND_IMAGE:
			scrapedLinks = new HashSet<String>();
			scrapedImages = new HashSet<String>();
			this.ScrapeLinksAndImages(doc.select(IMAGE+", "+LINK));
			break;
		default:
			System.out.println("This app does not support scraping for given tag");
			return ERR_INCORRECT_TAG;
		}
		this.WriteScrapedDataToFile(url);
		return CONNECTION_STATUS_OK;
	}
	
	/*
	 * Checks whether url contains protocol prefix : http/https
	 */
	private boolean CheckURL(String url) {
		return (url.startsWith("http://") && url.length() > 7) || (url.startsWith("https://") && url.length() > 8);
	}

	/**
	 * Returns a copy of scraped elements list;
	 * */
	public ArrayList<String> getScrapedElements() {
		ArrayList<String> result = new ArrayList<String>();
		if(scrapedLinks != null) {
			for(String link:scrapedLinks) {
				result.add(link);
			}
		}
		if(scrapedImages != null) {
			for(String img : scrapedImages) {
				result.add(img);
			}
		}		
		return result;
	}

	
	public ArrayList<String> getScrapedImages() {
		return new ArrayList<String>(scrapedImages);
	}
	
	public ArrayList<String> getScrapedLinks() {
		return new ArrayList<>(scrapedLinks);
	}
	/*
	 * 
	 * @param fileName
	 */
	private void WriteScrapedDataToFile(String url) {
		if(scrapedLinks != null) {
			try {
				dbObj.addScrapedData(url, scrapedLinks, DBObject.TABLE_SCRAPED_LINKS);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(scrapedImages != null) {
			try {
				dbObj.addScrapedData(url, scrapedImages, DBObject.TABLE_SCRAPED_IMAGE_SOURCES);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	

}
