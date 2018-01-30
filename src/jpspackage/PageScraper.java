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
	private Set<String> parsedElements;
	public final String LINK = "a";   //TODO: const??
	public final String IMAGE = "image";
	
	public PageScraper() {
		this.parsedElements = new HashSet<String>();
	}
	
	public void ParseURL(String url, ArrayList<String> tagNames) {		
		Document doc;
//		for(String s: tagNames) {
//			System.out.println(s);
//		}
		try {
			doc = Jsoup.connect(url).get();
			for(String s: tagNames) {
				Elements elems = doc.getElementsByTag(s);
				for(Element elem: elems) {
					this.parsedElements.add(elem.text());
				}
			}
			for(String s: this.parsedElements) {
				System.out.println(s);
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}
