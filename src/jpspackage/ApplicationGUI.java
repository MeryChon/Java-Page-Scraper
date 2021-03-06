package jpspackage;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;

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
	
	private AppController controller;
	private PageScraper pageScraper;


	/**
	 * Create the application.
	 */
	public ApplicationGUI() {
		initialize();
	}
	
	public void setFrameVisibility(boolean isVisible) {
		this.frame.setVisible(isVisible);
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
	
	/*
	 * 
	 * */
	private JTextField createTextField(int x, int y, int width, int height, int numColumns) {
		JTextField newTextField = new JTextField();
		newTextField.setBounds(x, y, width, height);
		newTextField.setColumns(numColumns);
		return newTextField;
	}
	
	/*
	 * 
	 */
	private JCheckBox createCheckbox(String text, int fontSize, int x, int y, int width, int height, boolean isChecked) {
		JCheckBox newCheckbox = new JCheckBox(text);
		newCheckbox.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
		newCheckbox.setBounds(x, y, 120, 25);
		newCheckbox.setSelected(isChecked);
		return newCheckbox;
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		pageScraper = new PageScraper();
		controller = new AppController(pageScraper);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		urlTxtField = createTextField(73, 58, 230, 20, 20);
		frame.getContentPane().add(urlTxtField);
		
		JCheckBox chckbxLinks = createCheckbox("Links", 14, 10, 95, 100, 25, false);
		frame.getContentPane().add(chckbxLinks);
		
		JCheckBox chckbxImages = createCheckbox("Images", 14, 10, 120, 100, 25, false);
		frame.getContentPane().add(chckbxImages);
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				urlToScrape = urlTxtField.getText();
				scrapeLinks = chckbxLinks.isSelected();
				scrapeImages = chckbxImages.isSelected();
				if((!scrapeImages && !scrapeLinks) || urlToScrape.equals("")) return;
				int response = controller.scrapePage(urlToScrape, scrapeLinks, scrapeImages);
				switch(response) {
				case PageScraper.CONNECTION_STATUS_OK:	
					ResultsWindow rWindow = new ResultsWindow(pageScraper, scrapeLinks, scrapeImages);
					rWindow.setVisible(true);
//					System.out.println(pageScraper.scrapedElements.size());
					System.out.println(urlToScrape + scrapeLinks + scrapeImages+ saveDirectory);
					break;
				case PageScraper.CONNECTION_STATUS_FAILED:
					ErrorWindow connectionErrorWindow = new ErrorWindow("Connection failed due to a malformed url.");
					connectionErrorWindow.setVisible(true);
					break;
				case PageScraper.ERR_UNSUCCSESSFULL_GET:
					ErrorWindow getErrorWindow = new ErrorWindow("Couldn't get the requested page.");
					getErrorWindow.setVisible(true);
					break;
				default:
					ErrorWindow errorWindow = new ErrorWindow("Something went wrong.");
					errorWindow.setVisible(true);
					break;
				}				
			}
		});
		btnSubmit.setFont(new Font("Monospaced", Font.PLAIN, 16));
		btnSubmit.setBounds(165, 200, 110, 40);
		frame.getContentPane().add(btnSubmit);
		
		JLabel lblUrl = createLabel("URL: ", 12, false, 15, 60, 45, 15);
		frame.getContentPane().add(lblUrl);

		JLabel lblAppTitle = createLabel("Page Scraper", 22, true, 140, 10, 160, 35);
		frame.getContentPane().add(lblAppTitle);

	}
}
