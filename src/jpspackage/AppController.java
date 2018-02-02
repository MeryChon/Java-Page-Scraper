package jpspackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppController{
	private PageScraper pScraper;
	public static final int TAGS_NOT_SELECTED = -1;
	public AppController(PageScraper pageScraper) {
		this.pScraper = pageScraper;
	}
	
	public int  scrapePage(String url, String saveDir, boolean links, boolean images) {
		String tagType;
		if(links) {
			if(images) {
				tagType = PageScraper.LINK_AND_IMAGE;
			} else {
				tagType = PageScraper.LINK;
			}
		} else {
			tagType = PageScraper.IMAGE;
		}
		
		return pScraper.ScrapeURL(url, tagType);
	}

}
