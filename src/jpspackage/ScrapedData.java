package jpspackage;

import java.util.HashSet;
import java.util.Set;

public class ScrapedData {
	private String url;
	private Set<String> links;
	private Set<String> imageSources;
	
	public ScrapedData(String URL) {
		this.url = URL;
		this.links = new HashSet<String>();
		this.imageSources = new HashSet<String>();
	}
	
	public boolean addLink(String link) {
		return this.links.add(link);
	}
	
	public boolean addImageSource(String imageSrc) {
		return this.imageSources.add(imageSrc);
	}
	
	public String getURL() {
		return this.url;
	}
	
	public Set<String> getLinks() {
		return new HashSet<String>(this.links);
	}
	
	public Set<String> getImageSources() {
		return new HashSet<String>(this.imageSources);
	}
	
	
}
