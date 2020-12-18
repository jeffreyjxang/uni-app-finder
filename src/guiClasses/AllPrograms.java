package guiClasses;

import objects.UniversitiesInformation;
import objects.University;
import objects.User;
import tools.Colour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import jdk.dynalink.linker.ConversionComparator.Comparison;

import static java.awt.Color.*;

public class AllPrograms extends JPanel implements ActionListener {

	private JLabel title = new JLabel("All Programs");
	private JButton nextBtn = new JButton();
	private JButton backBtn = new JButton();
	private JComboBox<String> combobox1 = new JComboBox<>();
	private JComboBox<String> combobox2 = new JComboBox<>();
	private JLabel sortBy = new JLabel("Sort by:");
	private JLabel picture = new JLabel();
	private ArrayList<University> uniArrayCopy;
	private int currentPage = 0;
	private boolean reversed = false;
	UniversitiesInformation uniClass = new UniversitiesInformation();
	private JTextField keyword = new JTextField();
	private JButton searchButton = new JButton("Search");
	private JLabel searchLabel = new JLabel("Search");
	private JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL, 0, 1, 0, 14);
	private String[] sorting = new String[] { "Alpha: A-Z", "Alpha: Z-A", "Average: Low to High",
			"Average: High to Low", "Bookmarked Universities" };
	private JPanel uniPanel;
	private ArrayList<University> customList = new ArrayList<>(0);
	private int maxIndex = 14;
	private JLabel searchFailed = new JLabel();
	private boolean searchFailedTF;
	private JButton resetButton = new JButton();
	public static JPanel overallPanel = new JPanel();
	private JLabel currentLocation = new JLabel();
	private JButton website = new JButton();
	private JButton bookmark = new JButton();
	private int totalMoves = 0;

	// constructor to setup the GUI screen
	public AllPrograms() {
		overallPanel = new JPanel();
		overallPanel.setBounds(0, 0, 920, 610);
		overallPanel.setLayout(null);
		overallPanel.setVisible(true);
		overallPanel.setBackground(Colour.bg);
		// Setting up the GUI panel
		UniversitiesInformation.setUniversities();
		uniArrayCopy = new ArrayList<>(uniClass.getUniversities());
		setLayout(null);

		uniPanel = createUniPanel(uniArrayCopy.get(0));
		uniPanel.setBounds(590, 0, 400, 700);
		uniPanel.setBackground(Colour.bg);
		overallPanel.add(uniPanel);
		uniPanel.setVisible(true);

		currentPage = 0;

		combobox1.addItem("Select a Program");
		// setting up combobox1, with the names of all the unis
		for (University uni : uniArrayCopy) {
			combobox1.addItem(uni.getName());
		}
		// setting up the picture
		picture.setIcon(uniArrayCopy.get(0).getIcon());
		picture.setBounds(20, 200, 700, 500);
		picture.setForeground(white);
		overallPanel.add(picture);

		// setting up the combobox
		combobox1.setBounds(20, 80, 200, 40);
		combobox1.addActionListener(this);
		combobox1.setVisible(true);
		combobox1.setBackground(Colour.strike);
		combobox1.setBorder(BorderFactory.createLineBorder(Colour.strike));
		overallPanel.add(combobox1);

		// setting up the sorting combobox(adding the options)
		for (String sorting : sorting) {
			combobox2.addItem(sorting);
		}
		combobox2.setBounds(300, 80, 200, 40);
		combobox2.addActionListener(this);
		combobox2.setVisible(true);
		combobox2.setBackground(Colour.strike);

		combobox2.setBorder(BorderFactory.createLineBorder(Colour.strike));
		overallPanel.add(combobox2);

		sortBy.setBounds(300, 50, 100, 30);
		sortBy.setVisible(true);
		sortBy.setForeground(Colour.strongHighlight);
		overallPanel.add(sortBy);

		title.setBounds(20, 10, 500, 50);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 40));
		title.setForeground(Colour.strongHighlight);
		overallPanel.add(title);

		// setting up the buttons for forward and back
		nextBtn.setText("Next");
		nextBtn.setBounds(400, 180, 100, 50);
		nextBtn.addActionListener(this);
		nextBtn.setVisible(true);
		nextBtn.setBackground(Colour.strike);

		nextBtn.setBorder(BorderFactory.createLineBorder(Colour.lightBg));
		overallPanel.add(nextBtn);

		backBtn.setText("Back");
		backBtn.setBounds(300, 180, 100, 50);
		backBtn.addActionListener(this);
		backBtn.setVisible(true);
		backBtn.setBackground(Colour.strike);

		backBtn.setBorder(BorderFactory.createLineBorder(Colour.lightBg));
		overallPanel.add(backBtn);

		resetButton.setText("Reset");
		resetButton.setBounds(150, 210, 70, 35);
		resetButton.addActionListener(this);
		resetButton.setVisible(true);
		resetButton.setBackground(Colour.strike);

		resetButton.setBorder(BorderFactory.createLineBorder(Colour.lightBg));
		overallPanel.add(resetButton);

		keyword.setBounds(20, 180, 200, 30);
		keyword.addActionListener(this);
		keyword.setBackground(Colour.strike);
		keyword.setBorder(BorderFactory.createLineBorder(Colour.lightBg));
		overallPanel.add(keyword);

		searchLabel.setBounds(20, 150, 50, 30);
		searchLabel.setForeground(Colour.strongHighlight);
		overallPanel.add(searchLabel);

		searchButton.setBounds(20, 210, 100, 35);
		searchButton.addActionListener(this);
		searchButton.setBackground(Colour.strike);
		searchButton.setBorder(BorderFactory.createLineBorder(Colour.lightBg));

		currentLocation.setText(currentPage + 1 + "/" + uniArrayCopy.size());
		currentLocation.setBounds(500, 15, 90, 50);
		currentLocation.setFont(new Font(title.getFont().getName(), Font.PLAIN, 24));
		currentLocation.setForeground(Colour.strongHighlight);
		overallPanel.add(currentLocation);
		overallPanel.add(searchButton);

		website.setText("Website");
		website.setBounds(470, 285, 100, 30);

		website.setBackground(Colour.strike);
		website.setBorder(BorderFactory.createLineBorder(Colour.lightBg));
		website.addActionListener(this);
		overallPanel.add(website);
		setVisible(true);
	}

	// Override Method
	@Override
	public void actionPerformed(ActionEvent event) {
		// COMBOBOX2 is the Sorting combobox, changes the uniarraycopy based on which
		// sorting the user chose
		if (event.getSource() == combobox2) {

			if (combobox2.getSelectedIndex() == 0) { // if its A-Z
				alphaSort();
				reversed = false;
			} else if (combobox2.getSelectedIndex() == 1) { // if its Z-A
				alphaSort();
				reverse();
				reversed = true;
				currentPage = 0;
			} else if (combobox2.getSelectedIndex() == 2) { // Low to High, Average
				insertionSort();
			} else if (combobox2.getSelectedIndex() == 3) { // high to low, average
				insertionSort();
				reverse();
				reversed = true;
			} else if (combobox2.getSelectedIndex() == 4) {
				uniArrayCopy.clear();
				if (User.bookmarked.size() <= 0) {
					JOptionPane.showMessageDialog(overallPanel, "Bookmark some universities first!");
				} else {
					for (int x = 0; x < User.bookmarked.size(); x++) {
						uniArrayCopy.add(x, User.bookmarked.get(x)); // adds all the users bookmarked universities into
						// the uniArrayCopy
					}
				}
			}

			currentPage = 0;
			overallPanel.remove(uniPanel);

			// creates a new unipanel(top right corner), and changes the picture
			uniPanel = createUniPanel(uniArrayCopy.get(currentPage));

			uniPanel.setBounds(590, 0, 400, 700);
			overallPanel.add(uniPanel);
			currentLocation.setText(currentPage + 1 + "/" + uniArrayCopy.size());

			overallPanel.repaint();
		}

		// COMBOBOX1 is the combobox where you can individually select a university to
		// look at
		if (event.getSource() == combobox1) {

			if (combobox1.getSelectedIndex() != 0) {
				reversed = false;
				uniArrayCopy = new ArrayList<>(uniClass.getUniversities());
				alphaSort();
				int comboboxIndex = combobox1.getSelectedIndex();
				overallPanel.remove(uniPanel);
				currentPage = comboboxIndex - 1;
				uniPanel = createUniPanel(uniArrayCopy.get(comboboxIndex - 1));
				uniPanel.setBounds(590, 0, 400, 700);
				scrollBar.setValue(comboboxIndex);

				currentLocation.setText(currentPage + 1 + "/" + uniArrayCopy.size());
				overallPanel.add(uniPanel);

				overallPanel.repaint();
			}
		}

		// "next" button
		if (event.getSource() == nextBtn) {
			maxIndex = uniArrayCopy.size();
			if (uniArrayCopy.size() != 1) {
				totalMoves += 1;
				currentPage += 1;
				if (currentPage == maxIndex) {
					currentPage = 0;
				}
				if (currentPage == -1) {
					currentPage = maxIndex;
				}

				overallPanel.remove(uniPanel);

				uniPanel = createUniPanel(uniArrayCopy.get(currentPage));
				uniPanel.setBounds(590, 0, 400, 700);
				combobox1.setSelectedIndex(0);
				currentLocation.setText(currentPage + 1 + "/" + uniArrayCopy.size());

				overallPanel.add(uniPanel);
				currentLocation.repaint();
				combobox1.repaint();
			}

			overallPanel.repaint();
		}
		// "back" button
		if (event.getSource() == backBtn) {
			maxIndex = uniArrayCopy.size();
			if (uniArrayCopy.size() != 1) {
				currentPage -= 1;
				if (currentPage == -1) {
					currentPage = maxIndex - 1;
				}
				if (currentPage == maxIndex) {
					currentPage = 0;
				}
				overallPanel.remove(uniPanel);

				uniPanel = createUniPanel(uniArrayCopy.get(currentPage));

				uniPanel.setBounds(590, 0, 400, 700);

				overallPanel.add(uniPanel);
				combobox1.repaint();
				currentLocation.setText(currentPage + 1 + "/" + uniArrayCopy.size());
				currentLocation.repaint();
				overallPanel.repaint();
			}
		}
		if (event.getSource() == resetButton) {
			maxIndex = 14;
			uniArrayCopy = new ArrayList<>(uniClass.getUniversities());
			keyword.setText("");
			currentPage = 0;
			overallPanel.remove(uniPanel);

			// creates a new unipanel(top right corner), and changes the picture
			uniPanel = createUniPanel(uniArrayCopy.get(currentPage));

			uniPanel.setBounds(590, 0, 400, 700);
			overallPanel.add(uniPanel);

			combobox2.setSelectedIndex(0);
			currentLocation.setText(currentPage + 1 + "/" + uniArrayCopy.size());

			currentLocation.repaint();
			overallPanel.repaint();

			nextBtn.setBackground(Colour.strike);
			backBtn.setBackground(Colour.strike);

			nextBtn.setEnabled(true);
			backBtn.setEnabled(true);

		}

		if (event.getSource() == website) {
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				try {
					Desktop.getDesktop().browse(new URI(uniArrayCopy.get(currentPage).getLink()));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			}
		}

		// button to start search
		if (event.getSource() == searchButton) {
			customList.clear();
			// tempnames is the names of the university without the "university of" and
			// lowercase, ex. University of Toronto --> toronto
			ArrayList<String> tempNames = new ArrayList<>();
			for (University uni : uniClass.getUniversities()) {
				tempNames.add(uni.getName());
			}
			for (int x = 0; x < tempNames.size(); x++) {
				tempNames.set(x, replaceFiller(tempNames.get(x)));
			}
			currentPage = 0;

			String text = keyword.getText();
			text = replaceFiller(text);

			// checks if what the user entered is the university name
			for (int i = 0; i < tempNames.size(); i++) {
				if (text.equalsIgnoreCase(tempNames.get(i))) {
					customList.add(uniClass.getUniversities().get(i));

				}
			}
			// shows a no matches label if there are no matches
			if (customList.size() == 0) {
				searchFailed.setText("No Matches");
				searchFailed.setBounds(20, 300, 100, 35);
				searchFailed.setForeground(Colour.strongHighlight);
				searchFailed.setVisible(true);
				overallPanel.add(searchFailed);
				searchFailedTF = true;
				uniArrayCopy = new ArrayList<>(uniClass.getUniversities());
			} else if (text.equalsIgnoreCase("")) {

				maxIndex = 14;
				uniArrayCopy = new ArrayList<>(uniClass.getUniversities());
			}

			// updates the uriarraycopy with the matching universities
			else {

				if (searchFailedTF)
					overallPanel.remove(searchFailed);
				searchFailedTF = false;
				currentPage = 0;
				uniArrayCopy = new ArrayList<>(customList);

				maxIndex = customList.size();
				overallPanel.remove(uniPanel);
				uniPanel = createUniPanel(uniArrayCopy.get(currentPage));
				uniPanel.setBounds(590, 0, 400, 700);
				combobox2.setSelectedIndex(0);
				combobox2.repaint();
				nextBtn.setBackground(gray);
				backBtn.setBackground(gray);

				nextBtn.setEnabled(false);
				backBtn.setEnabled(false);
				overallPanel.add(uniPanel);

			}
			overallPanel.repaint();

		}

		if (event.getSource() == bookmark) {
			System.out.println(uniArrayCopy.get(currentPage).getName());
			if (!uniArrayCopy.get(currentPage).getisBookmarked()) {
				System.out.println("here 1");
				User.bookmarked.add(uniArrayCopy.get(currentPage));
				bookmark.setText("Bookmarked!");
			} else {
				System.out.println("here 2");
				User.bookmarked.remove(uniArrayCopy.get(currentPage));
				bookmark.setText("Bookmark University");
			}
		}
	}

	// reverses the uniarraycopy
	public void reverse() {
		ArrayList<University> temp = new ArrayList<>(14);

		for (int i = uniArrayCopy.size(); i > 0; i--) {
			temp.add(uniArrayCopy.get(i - 1));
		}
		uniArrayCopy = temp;
	}

	// creates the text info and the pictures of each uni
	public JPanel createUniPanel(University uni) {

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(400, 500);
		JLabel nameLabel = new JLabel();
		JLabel infoLabel = new JLabel("<html>" + uni.getDescription() + "<html>");
		JLabel nationalRankLabel = new JLabel("Rank: " + uni.getNationalRank());
		JLabel logo = new JLabel();
		JLabel comparison = new JLabel("Comparison");
		JLabel comparisonStatement = new JLabel();

		logo.setIcon(new ImageIcon(uni.getImage()));
		logo.setBounds(0, 70, 300, 200);

		nameLabel.setText(uni.getName());
		nameLabel.setBounds(0, 0, 400, 80);
		nameLabel.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
		nameLabel.setForeground(Colour.strongHighlight);
		nationalRankLabel.setBounds(0, 30, 500, 80);
		nationalRankLabel.setFont(new Font(title.getFont().getName(), Font.PLAIN, 14));
		nationalRankLabel.setForeground(Colour.strongHighlight);
		infoLabel.setBounds(0, 120, 300, 375);
		infoLabel.setFont(new Font(title.getFont().getName(), Font.PLAIN, 12));
		infoLabel.setForeground(Colour.strongHighlight);

		if (User.bookmarked.contains(uni)) {

			bookmark.setText("Bookmarked!");
			bookmark.setBounds(0, 380, 170, 50);
			bookmark.setBackground(Colour.strike);
			bookmark.setBorder(BorderFactory.createLineBorder(Colour.lightBg));
			bookmark.addActionListener(this);
		} else {
			bookmark.setText("Bookmark University");
			bookmark.setBounds(0, 380, 170, 50);
			bookmark.setBackground(Colour.strike);
			bookmark.setBorder(BorderFactory.createLineBorder(Colour.lightBg));
			bookmark.addActionListener(this);
		}

		uni.setBookmarked(User.bookmarked.contains(uni));
		bookmark.setBounds(0, 380, 170, 50);
		bookmark.setBackground(Colour.strike);
		bookmark.setBorder(BorderFactory.createLineBorder(Colour.lightBg));
		bookmark.addActionListener(this);

		comparison.setBounds(0, 450, 150, 30);
		comparison.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
		comparison.setForeground(Colour.strongHighlight);

		if (!(UniMatchmakerInfoEdit.save)) {
			comparisonStatement
					.setText("<html>Go to preferences tab to see how your averages compare to the universities.<html>");
			comparisonStatement.setBounds(0, 500, 300, 100);
			comparisonStatement.setFont(new Font(title.getFont().getName(), Font.PLAIN, 14));
			comparisonStatement.setForeground(Colour.strongHighlight);

		} else if (UniMatchmakerInfoEdit.save) {
			double average = 0;
			for (int x = 0; x < 6; x++) {
				average += Integer.parseInt(UniMatchmakerInfoEdit.gradeTextField[x].getText());
			}
			average = average / 6;
			if (uni.getCutoff() > average) {
				comparisonStatement.setText("<html>As of now, you do not meet the cutoff for this university.<html>");
				comparisonStatement.setBounds(0, 470, 300, 125);
				comparisonStatement.setFont(new Font(title.getFont().getName(), Font.PLAIN, 14));
				comparisonStatement.setForeground(Colour.strongHighlight);

			} else if (uni.getCutoff() < average && average < uni.getAverage()) {
				comparisonStatement.setText(
						"<html>You meet the cutoff requirement, however, you do not meet the admission average (2016).<html>");
				comparisonStatement.setBounds(0, 470, 300, 125);
				comparisonStatement.setFont(new Font(title.getFont().getName(), Font.PLAIN, 14));
				comparisonStatement.setForeground(Colour.strongHighlight);

			} else {
				comparisonStatement.setText(
						"<html>Your current average surpasses the cutoff requirement and the admission average (2016).<html>");
				comparisonStatement.setBounds(0, 470, 300, 125);
				comparisonStatement.setFont(new Font(title.getFont().getName(), Font.PLAIN, 14));
				comparisonStatement.setForeground(Colour.strongHighlight);

			}

		}

		panel.add(comparisonStatement);

		panel.add(bookmark);
		panel.add(nameLabel);
		panel.add(logo);
		panel.add(nationalRankLabel);
		panel.add(infoLabel);
		panel.add(comparison);
		panel.add(comparisonStatement);
		picture.setIcon(uniArrayCopy.get(currentPage).getIcon());
		picture.setVisible(true);
		panel.setBackground(Colour.bg);
		panel.repaint();

		return panel;
	}

	public void insertionSort() {
		// Runs for every index of the array except the first
		for (int x = 1; x < uniArrayCopy.size(); x++) {
			/*
			 * In the for loop below it will... take the value in the index x (which is one
			 * greater in each iteration) and continue to "shift" it until the value is
			 * greater than the value of the contents in index y thus moving it into its
			 * sorted place in the array
			 */
			for (int y = x - 1; y >= 0; y--) {
				if (uniArrayCopy.get(x).getAverage() < uniArrayCopy.get(y).getAverage()) {
					swap(uniArrayCopy, x--, y);
				} else {
					break;
				}
			}
		}
	}

	public void swap(ArrayList<University> universities, int x, int smallest) {
		University temp = universities.get(x);
		universities.set(x, universities.get(smallest));
		universities.set(smallest, temp);
	}

	public void alphaSort() {
		University temp;
		for (int i = 0; i < uniArrayCopy.size(); i++) {
			for (int j = i + 1; j < uniArrayCopy.size(); j++) {
				if (uniArrayCopy.get(i).getName().compareTo(uniArrayCopy.get(j).getName()) > 0) {
					temp = uniArrayCopy.get(i);
					uniArrayCopy.set(i, uniArrayCopy.get(j));
					uniArrayCopy.set(j, temp);
				}
			}
		}
	}

	public String replaceFiller(String text) {
		text = text.toLowerCase();
		text = text.replace("university", "");
		text = text.replace("of", "");
		while (text.contains(" ")) {
			text = text.replace(" ", "");
		}
		return text;
	}

}