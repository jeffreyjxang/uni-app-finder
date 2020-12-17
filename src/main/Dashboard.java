package main;

import guiClasses.AllPrograms;
import guiClasses.MapScreen;
import objects.UniversitiesInformation;
import tools.Colour;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Dashboard extends JPanel {

	public static JPanel dashboardPanel;
	public static JPanel taskbarPanel;
	public static JPanel displayPanel;
	public static JPanel introPanel;

	private static UniversitiesInformation universities = new UniversitiesInformation();
	private static MapScreen mapScreen = new MapScreen(universities);

	public static void CreateDashboard() {

		// Create dashboard panel
		dashboardPanel = new JPanel();
		Welcome.GUI.getContentPane().add(dashboardPanel);
		dashboardPanel.setLayout(null);

		// Create taskbar panel
		taskbarPanel = new JPanel();
		taskbarPanel.setBounds(0, 0, 210, 610);
		taskbarPanel.setBackground(Colour.lightBg);
		dashboardPanel.add(taskbarPanel);
		taskbarPanel.setLayout(null);

		// Create display panel
		displayPanel = new JPanel();
		displayPanel.setBounds(210, 0, 930, 610);
		dashboardPanel.add(displayPanel);
		displayPanel.setLayout(new CardLayout(0, 0));

		// Create introduction panel
		introPanel = new JPanel();
		displayPanel.add(introPanel);
		introPanel.setLayout(null);
		introPanel.setBackground(Colour.bg);

		new AllPrograms();
		displayPanel.add(AllPrograms.overallPanel);

		new AllPrograms();
		displayPanel.add(AllPrograms.overallPanel);

		mapScreen.getMapPanel().setBounds(210, 0, 920, 610);
		displayPanel.add(mapScreen.getMapPanel());

		mapScreen.getDistancePanel().setBounds(210, 0, 920, 610);
		displayPanel.add(mapScreen.getDistancePanel());

		// Create logo label
		JLabel logoLabel = new JLabel(new ImageIcon("resources/misc/program-logo-fill(s).png"));
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				hidePanel();
				introPanel.setVisible(true);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		logoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logoLabel.setBounds(0, 20, 210, 100);
		taskbarPanel.add(logoLabel);

		// Create taskbar buttons
		JButton uniInfoButton = new JButton("University Information");
		uniInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hidePanel();
				AllPrograms.overallPanel.setVisible(true);

			}});
		uniInfoButton.setBounds(25, 150, 160, 25);
		taskbarPanel.add(uniInfoButton);

		JButton accountButton = new JButton("My Account");
		accountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hidePanel();
				UniMatchmaker.accountPanel.setVisible(true);
			}
		});
		accountButton.setBounds(25, 210, 160, 25);
		taskbarPanel.add(accountButton);

		JButton quizButton = new JButton("Take Quiz");
		quizButton.setBounds(25, 270, 160, 25);
		taskbarPanel.add(quizButton);

		JButton mapButton = new JButton("View Map");
		mapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hidePanel();
				mapScreen.getMapPanel().setVisible(true);
			}
		});
		mapButton.setBounds(25, 330, 160, 25);
		taskbarPanel.add(mapButton);

		JButton helpButton2 = new JButton("Help");
		helpButton2.setBounds(45, 500, 120, 25);
		taskbarPanel.add(helpButton2);

		JButton signOutButton = new JButton("Sign Out");
		signOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hidePanel();
				Welcome.welcomePanel.setVisible(true);
			}
		});
		signOutButton.setBounds(45, 550, 120, 25);
		taskbarPanel.add(signOutButton);

		// Create intro screen
		JLabel titleLabel2 = new JLabel("App Name");
		titleLabel2.setFont(new Font("Tahoma", Font.PLAIN, 36));
		titleLabel2.setForeground(Colour.strongHighlight);
		titleLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel2.setBounds(0, 30, 925, 90);
		introPanel.add(titleLabel2);

		JLabel quoteLabel = new JLabel("Quote");
		quoteLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		quoteLabel.setForeground(Colour.strongHighlight);
		quoteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quoteLabel.setBounds(0, 155, 925, 75);
		introPanel.add(quoteLabel);

		JLabel creatorLabel = new JLabel("Created By: Brittany, Jeffrey, Jordan, Michael and Mithun");
		creatorLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		creatorLabel.setForeground(Colour.strongHighlight);
		creatorLabel.setBounds(20, 550, 895, 60);
		introPanel.add(creatorLabel);

		// Create all other screens
		UniMatchmakerInfoEdit.EditAccount();
		UniMatchmaker.CreateAccount();
		hidePanel();

	}

	public static void hidePanel() {
		introPanel.setVisible(false);
//		dashboardPanel.setVisible(false);
		mapScreen.getMapPanel().setVisible(false);
		mapScreen.getDistancePanel().setVisible(false);

		UniMatchmaker.accountPanel.setVisible(false);
		UniMatchmakerInfoEdit.accountEditPanel.setVisible(false);
		AllPrograms.overallPanel.setVisible(false);

	}

}
