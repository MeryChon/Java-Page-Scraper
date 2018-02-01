package jpspackage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ResultsWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ResultsWindow dialog = new ResultsWindow();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ResultsWindow(PageScraper pageScraper) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
//		JTextArea resultArea;
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 430, 225);
		contentPanel.add(scrollPane);
		JTextArea resultArea = new JTextArea();
		resultArea.setEditable(false);
		scrollPane.setViewportView(resultArea);

//		JPanel buttonPane = new JPanel();
//		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		getContentPane().add(buttonPane, BorderLayout.SOUTH);
//		JButton okButton = new JButton("OK");
//		okButton.setActionCommand("OK");
//		buttonPane.add(okButton);
//		getRootPane().setDefaultButton(okButton);
		
		
		ArrayList<String> results = pageScraper.GetScrapedElements();
		for(String el: results) {
			resultArea.append(el + '\n');
		}
	}
}
