package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import guiClasses.MapScreen;
import objects.UniversitiesInformation;
import tools.Colour;

public class Results extends JPanel {

	public static JPanel resultsPanel;
	private static JLabel[] nameLabel = new JLabel[2];
	private static JLabel[] admissionLabel = new JLabel[2];
	private static JLabel[] tuitionLabel = new JLabel[2];
	private static JLabel[] distanceLabel = new JLabel[2];
	private static JLabel[] residenceLabel = new JLabel[2];
	private static JLabel[] uniSizeLabel = new JLabel[2];
	private static JLabel[] classSizeLabel = new JLabel[2];
	private static JLabel[] pictureLabel = new JLabel[2];
	private static JPanel[] uniPanel = new JPanel[2];

	public static void CreateResults() {

		Border border = BorderFactory.createLineBorder(Colour.strongHighlight, 3);

		// Create results panel
		resultsPanel = new JPanel();
		Dashboard.displayPanel.add(resultsPanel);
		resultsPanel.setBackground(Colour.bg);
		resultsPanel.setLayout(null);
		resultsPanel.setVisible(true);

		// Create title label
		JLabel titleLabel = new JLabel("Application Name/Logo");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		titleLabel.setForeground(Colour.strongHighlight);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(250, 10, 400, 65);
		resultsPanel.add(titleLabel);

		// Create caption label
		JLabel captionLabel = new JLabel("Your Results");
		captionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		captionLabel.setForeground(Colour.strongHighlight);
		captionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		captionLabel.setBounds(250, 65, 400, 25);
		resultsPanel.add(captionLabel);

		// Create description label
		JLabel descriptionLabel = new JLabel("Based on your answers, the following programs are best suited for you");
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel.setForeground(Colour.strongHighlight);
		descriptionLabel.setBounds(200, 100, 500, 25);
		resultsPanel.add(descriptionLabel);

		// Create back button
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		backButton.setBackground(Colour.strike);
		backButton.setBounds(30, 30, 100, 30);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultsPanel.setVisible(false);
				UniMatchmaker.accountPanel.setVisible(true);
			}
		});
		resultsPanel.add(backButton);

		MatchmakingAlgorithm.Matchmaker();

		int[] num = new int[2];
		num[0] = MatchmakingAlgorithm.greatestIndex;
		num[1] = MatchmakingAlgorithm.secondGreatestIndex;

		String[] uni = new String[2];
		double[] cutoff = new double[2];
		double[] distance = new double[2];
		double[] tuition = new double[2];
		double[] residence = new double[2];
		int[] uniSize = new int[2];
		int[] classSize = new int[2];

		// Add university info label
		for (int x = 0; x < nameLabel.length; x++) {

			uni[x] = UniversitiesInformation.universities.get(num[x]).getName();
			cutoff[x] = UniversitiesInformation.universities.get(num[x]).getCutoff();
			distance[x] = UniversitiesInformation.distances.get(0)[num[x]].getDistance();
			tuition[x] = UniversitiesInformation.universities.get(num[x]).getTuition();
			residence[x] = UniversitiesInformation.universities.get(num[x]).getResidenceCost();
			uniSize[x] = UniversitiesInformation.universities.get(num[x]).getUniSize();
			classSize[x] = UniversitiesInformation.universities.get(num[x]).getClassSize();

			nameLabel[x] = new JLabel("Institution #" + (x + 1) + " Name: " + uni[x]);
			nameLabel[x].setFont(new Font("Tahoma", Font.PLAIN, 14));
			nameLabel[x].setBounds(30 + 450 * x, 150, 300, 25);
			resultsPanel.add(nameLabel[x]);

			distanceLabel[x] = new JLabel("Distance: " + distance[x] + " km");
			distanceLabel[x].setFont(new Font("Tahoma", Font.PLAIN, 14));
			distanceLabel[x].setBounds(30 + 450 * x, 185, 300, 25);
			resultsPanel.add(distanceLabel[x]);

			admissionLabel[x] = new JLabel("Cutoff Average: " + cutoff[x] + "%");
			admissionLabel[x].setFont(new Font("Tahoma", Font.PLAIN, 14));
			admissionLabel[x].setBounds(30 + 450 * x, 220, 300, 25);
			resultsPanel.add(admissionLabel[x]);

			tuitionLabel[x] = new JLabel("Tuition (Annually): $" + tuition[x]);
			tuitionLabel[x].setFont(new Font("Tahoma", Font.PLAIN, 14));
			tuitionLabel[x].setBounds(30 + 450 * x, 255, 300, 25);
			resultsPanel.add(tuitionLabel[x]);
			uniPanel[x] = new JPanel();
			uniPanel[x].setBounds(50 + 455 * x, 150, 375, 450);
			uniPanel[x].setBackground(Colour.bg);
			uniPanel[x].setLayout(null);
			uniPanel[x].setBorder(border);

			nameLabel[x] = new JLabel("Institution #" + (x + 1) + " Name: " + uni[x]);
			nameLabel[x].setFont(new Font("Tahoma", Font.PLAIN, 14));
			nameLabel[x].setForeground(Colour.strongHighlight);
			nameLabel[x].setBounds(10, 10, 300, 25);
			uniPanel[x].add(nameLabel[x]);

			distanceLabel[x] = new JLabel("Distance: " + distance[x] + " km");
			distanceLabel[x].setFont(new Font("Tahoma", Font.PLAIN, 14));
			distanceLabel[x].setForeground(Colour.strongHighlight);
			distanceLabel[x].setBounds(10, 40, 300, 25);
			uniPanel[x].add(distanceLabel[x]);

			admissionLabel[x] = new JLabel("Cutoff Average: " + cutoff[x] + "%");
			admissionLabel[x].setFont(new Font("Tahoma", Font.PLAIN, 14));
			admissionLabel[x].setForeground(Colour.strongHighlight);
			admissionLabel[x].setBounds(10, 70, 300, 25);
			uniPanel[x].add(admissionLabel[x]);

			tuitionLabel[x] = new JLabel("Tuition (Annually): $" + tuition[x]);
			tuitionLabel[x].setFont(new Font("Tahoma", Font.PLAIN, 14));
			tuitionLabel[x].setForeground(Colour.strongHighlight);
			tuitionLabel[x].setBounds(10, 100, 300, 25);
			uniPanel[x].add(tuitionLabel[x]);

			residenceLabel[x] = new JLabel("Residence Cost: $" + residence[x]);
			residenceLabel[x].setFont(new Font("Tahoma", Font.PLAIN, 14));
			residenceLabel[x].setForeground(Colour.strongHighlight);
			residenceLabel[x].setBounds(10, 130, 300, 25);
			uniPanel[x].add(residenceLabel[x]);

			uniSizeLabel[x] = new JLabel("University Population: " + uniSize[x]);
			uniSizeLabel[x].setFont(new Font("Tahoma", Font.PLAIN, 14));
			uniSizeLabel[x].setForeground(Colour.strongHighlight);
			uniSizeLabel[x].setBounds(10, 160, 300, 25);
			uniPanel[x].add(uniSizeLabel[x]);

			classSizeLabel[x] = new JLabel("Average Class Size: " + classSize[x]);
			classSizeLabel[x].setFont(new Font("Tahoma", Font.PLAIN, 14));
			classSizeLabel[x].setForeground(Colour.strongHighlight);
			classSizeLabel[x].setBounds(10, 190, 300, 25);
			uniPanel[x].add(classSizeLabel[x]);

			pictureLabel[x] = new JLabel(new ImageIcon("resources/uniPictures2/" + uni[x] + ".jpg"));
			pictureLabel[x].setBounds(35, 230, 300, 200);
			uniPanel[x].add(pictureLabel[x]);

			resultsPanel.add(uniPanel[x]);

			residenceLabel[x] = new JLabel("Residence Cost: $" + residence[x]);
			residenceLabel[x].setFont(new Font("Tahoma", Font.PLAIN, 14));
			residenceLabel[x].setBounds(30 + 450 * x, 290, 300, 25);
			resultsPanel.add(residenceLabel[x]);

			uniSizeLabel[x] = new JLabel("University Population: " + uniSize[x]);
			uniSizeLabel[x].setFont(new Font("Tahoma", Font.PLAIN, 14));
			uniSizeLabel[x].setBounds(30 + 450 * x, 325, 300, 25);
			resultsPanel.add(uniSizeLabel[x]);

			classSizeLabel[x] = new JLabel("Average Class Size: " + classSize[x]);
			classSizeLabel[x].setFont(new Font("Tahoma", Font.PLAIN, 14));
			classSizeLabel[x].setBounds(30 + 450 * x, 360, 300, 25);
			resultsPanel.add(classSizeLabel[x]);

			pictureLabel[x] = new JLabel(new ImageIcon("resources/uniPictures2/" + uni[x] + ".jpg"));
			pictureLabel[x].setBounds(30 + 450 * x, 400, 300, 200);
			resultsPanel.add(pictureLabel[x]);

		}

	}

}
