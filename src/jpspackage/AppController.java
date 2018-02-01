package jpspackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppController{
	private PageScraper pScraper;
	public AppController(PageScraper pageScraper) {
		this.pScraper = pageScraper;
	}
	
	public void scrapePage(String url, String saveDir, boolean links, boolean images) {
		String tagType;
		if(!links && !images) return;
		if(links) {
			if(images) {
				tagType = PageScraper.LINK_AND_IMAGE;
			} else {
				tagType = PageScraper.LINK;
			}
		} else {
			tagType = PageScraper.IMAGE;
		}
		
		pScraper.ScrapeURL(url, tagType);
	}

}
