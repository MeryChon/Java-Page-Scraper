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
	}
}
