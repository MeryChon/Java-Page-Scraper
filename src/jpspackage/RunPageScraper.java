package jpspackage;

import java.awt.EventQueue;
import java.util.Scanner;

public class RunPageScraper {
	private static Scanner scin;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationGUI window = new ApplicationGUI();
					window.setFrameVisibility(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
//		PageScraper ps = new PageScraper();
//		scin = new Scanner(System.in);
//		
//		System.out.println("Enter webpage URL and hit enter");
//		String webPageURL = scin.nextLine();
//		System.out.println("What kind of elements do you want to get?");
//		System.out.println("Enter l for links, i for images and b for both");
//		System.out.println("Enter any other key to terminate the program");
//		String typeOfElements = scin.nextLine();
//		
//		switch(typeOfElements) {
//		case "l":
//			ps.ScrapeURL(webPageURL, PageScraper.LINK);
//			break;
//		case "i":
//			ps.ScrapeURL(webPageURL, PageScraper.IMAGE);
//			break;
//		case "b":
//			ps.ScrapeURL(webPageURL, PageScraper.LINK_AND_IMAGE);
//			break;
//		default:
//			return;
//		}	
		
	}
}
