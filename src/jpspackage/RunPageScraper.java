package jpspackage;

import java.util.ArrayList;
import java.util.Scanner;

public class RunPageScraper {
	public static void main(String[] args) {
		PageScraper ps = new PageScraper();
		System.out.println("Enter webpage URL and hit enter.");
		Scanner scin = new Scanner(System.in);  // TODO : fix the warning
		
		String webPageName = scin.nextLine();
		System.out.println("What kind of elements do you want to get?");
		System.out.println("Enter l for links, i for images and b for both.");
		String typeOfElements = scin.nextLine();
		
		ArrayList<String> tags = new ArrayList<String>();
		switch(typeOfElements) {
		case "l":
			tags.add(ps.LINK);
			break;
		case "i":
			tags.add(ps.IMAGE);
			break;
		case "b":
			tags.add(ps.LINK);
			tags.add(ps.IMAGE);
			break;
		default:
			return;
		}	
		
		ps.ParseURL(webPageName, tags);		
	}
}
