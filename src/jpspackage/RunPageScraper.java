package jpspackage;

import java.util.ArrayList;
import java.util.Scanner;

public class RunPageScraper {
	public static void main(String[] args) {
		PageScraper ps = new PageScraper();
		System.out.println("Enter webpage URL and hit enter.");
		Scanner scin = new Scanner(System.in);  // TODO : fix the warning
		String webPageName = scin.nextLine();
		ArrayList<String> tags = new ArrayList<String>();
		tags.add(ps.LINK);
		tags.add(ps.IMAGE);
		ps.ParseURL(webPageName, tags);		
	}
}
