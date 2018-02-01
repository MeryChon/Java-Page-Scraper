package jpspackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ApplicationGUI {

	private JFrame frame;
	private JTextField urlTxtField;
	private JTextField txtFieldSaveDir;
	private String urlToScrape;
	private boolean scrapeLinks;
	private boolean scrapeImages;
	private String saveDirectory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationGUI window = new ApplicationGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApplicationGUI() {
		initialize();
	}
	
	/*
	 * Creates new JLabel with given parameters
	 * */
	private JLabel createLabel(String labelText, int fontSize, boolean isBold, int x, int y, int width, int height) {
		JLabel newCenteredLabel = new JLabel(labelText);
		if(isBold) {
			newCenteredLabel.setFont(new Font("Monospaced", Font.BOLD, fontSize));
		} else {
			newCenteredLabel.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
		}		
		newCenteredLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newCenteredLabel.setBounds(x, y, width, height);
		return newCenteredLabel;
	}
	
	private JTextField createTextField(int x, int y, int width, int height, int numColumns) {
		JTextField newTextField = new JTextField();
		newTextField.setBounds(x, y, width, height);
		newTextField.setColumns(numColumns);
		return newTextField;
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		urlTxtField = createTextField(73, 58, 230, 20, 20);
//		urlTxtField = new JTextField();
//		urlTxtField.setBounds(73, 58, 230, 20);
		frame.getContentPane().add(urlTxtField);
//		urlTxtField.setColumns(10);
		
		JCheckBox chckbxLinks = new JCheckBox("Links");
		chckbxLinks.setFont(new Font("Monospaced", Font.PLAIN, 15));
		chckbxLinks.setBounds(10, 95, 100, 25);
		frame.getContentPane().add(chckbxLinks);
		
		JCheckBox chckbxImages = new JCheckBox("Images");
		chckbxImages.setFont(new Font("Monospaced", Font.PLAIN, 15));
		chckbxImages.setBounds(10, 120, 100, 25);
		frame.getContentPane().add(chckbxImages);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				urlToScrape = urlTxtField.getText();
				scrapeLinks = chckbxLinks.isSelected();
				scrapeImages = chckbxImages.isSelected();
				saveDirectory = txtFieldSaveDir.getText();
				System.out.println(urlToScrape + scrapeLinks + scrapeImages+ saveDirectory);
			}
		});
		btnSubmit.setFont(new Font("Monospaced", Font.PLAIN, 16));
		btnSubmit.setBounds(165, 200, 110, 40);
		frame.getContentPane().add(btnSubmit);
		
//		txtFieldSaveDir = new JTextField();
//		txtFieldSaveDir.setColumns(10);
//		txtFieldSaveDir.setBounds(73, 158, 230, 20);
		txtFieldSaveDir = createTextField(73, 158, 230, 20, 20);
		frame.getContentPane().add(txtFieldSaveDir);
		
		
		JLabel lblUrl = createLabel("URL: ", 12, false, 15, 60, 45, 15);
		frame.getContentPane().add(lblUrl);
//		JLabel lblUrl = new JLabel("URL:");
//		lblUrl.setFont(new Font("Monospaced", Font.PLAIN, 13));
//		lblUrl.setHorizontalAlignment(SwingConstants.CENTER);
//		lblUrl.setBounds(15, 60, 45, 15);
//		frame.getContentPane().add(lblUrl);
		
		JLabel lblSaveTo = createLabel("Save to:", 12, false, 10, 160, 60, 15);
		frame.getContentPane().add(lblSaveTo);
		
		JLabel lblAppTitle = createLabel("Page Scraper", 22, true, 140, 10, 160, 35);
		frame.getContentPane().add(lblAppTitle);
//		JLabel lblAppTitle = new JLabel("Page Scraper");
//		lblAppTitle.setFont(new Font("Monospaced", Font.BOLD, 22));
//		lblAppTitle.setHorizontalAlignment(SwingConstants.CENTER);
//		lblAppTitle.setBounds(140, 10, 160, 35);
//		frame.getContentPane().add(lblAppTitle);
	}
}
