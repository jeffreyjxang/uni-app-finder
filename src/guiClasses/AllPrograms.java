package guiClasses;

import objects.UniversitiesInformation;
import objects.University;
import tools.Colour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static java.awt.Color.*;

public class AllPrograms extends JPanel implements ActionListener{

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
    private JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL,0,1,0,14);
    private String[] sorting = new String[]{"Alpha: A-Z", "Alpha: Z-A", "Average: Low to High", "Average: High to Low"};
    private JPanel uniPanel;
    private ArrayList<University> customList = new ArrayList<>(0);
    private int maxIndex = 14;
    private JLabel searchFailed = new JLabel();
    private boolean searchFailedTF;
    private JButton resetButton = new JButton();
    public static JPanel overallPanel = new JPanel();
    private JLabel currentLocation = new JLabel();
    private JButton website = new JButton();

    //constructor to setup the GUI screen
    public AllPrograms() {
        overallPanel = new JPanel();
        overallPanel.setBounds(0,0,920,610);
        overallPanel.setLayout(null);
        overallPanel.setVisible(true);
        overallPanel.setBackground(Colour.bg);
        //Setting up the GUI panel
        UniversitiesInformation.setUniversities();
        uniArrayCopy = new ArrayList<>(uniClass.getUniversities());
        setLayout(null);

        uniPanel = createUniPanel(uniArrayCopy.get(0));
        uniPanel.setBounds(590, 0, 400, 500);
        uniPanel.setBackground(Colour.bg);
        overallPanel.add(uniPanel);
        uniPanel.setVisible(true);


        currentPage = 0;

        //setting up combobox1, with the names of all the unis
        for (University uni : uniArrayCopy) {
            combobox1.addItem(uni.getName());
        }
        //setting up the picture
        picture.setIcon(uniArrayCopy.get(0).getIcon());
        picture.setBounds(20,200,700 ,500);
        overallPanel.add(picture);

        //setting up the combobox
        combobox1.setBounds(20, 80, 200, 40);
        combobox1.addActionListener(this);
        combobox1.setVisible(true);
        overallPanel.add(combobox1);

        //setting up the sorting combobox(adding the options)
        for (String sorting : sorting) {
            combobox2.addItem(sorting);
        }
        combobox2.setBounds(300, 80, 200, 40);
        combobox2.addActionListener(this);
        combobox2.setVisible(true);
        overallPanel.add(combobox2);

        sortBy.setBounds(300,50,100,30);
        sortBy.setVisible(true);
        sortBy.setForeground(Colour.strongHighlight);
        overallPanel.add(sortBy);

        title.setBounds(20, 10, 500, 50);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 40));
        title.setForeground(Colour.strongHighlight);
        overallPanel.add(title);

        //setting up the buttons for forward and back
        nextBtn.setText("Next");
        nextBtn.setBounds(400, 200, 100, 50);
        nextBtn.addActionListener(this);
        nextBtn.setVisible(true);
        overallPanel.add(nextBtn);

        backBtn.setText("Back");
        backBtn.setBounds(300, 200, 100, 50);
        backBtn.addActionListener(this);
        backBtn.setVisible(true);
        overallPanel.add(backBtn);


        resetButton.setText("Reset");
        resetButton.setBounds(450 , 30, 80, 30);
        resetButton.addActionListener(this);
        resetButton.setVisible(true);
        overallPanel.add(resetButton);

        keyword.setBounds(20,180,200,30);
        keyword.addActionListener(this);
        overallPanel.add(keyword);

        searchLabel.setBounds(20,150,50,30);
        searchLabel.setForeground(Colour.strongHighlight);
        overallPanel.add(searchLabel);

        searchButton.setBounds(20,210,100,35);
        searchButton.addActionListener(this);

        currentLocation.setText(currentPage + 1 + "/14");
        currentLocation.setBounds(350, 250, 100,50);
        currentLocation.setForeground(Colour.strongHighlight);
        overallPanel.add(currentLocation);
        overallPanel.add(searchButton);

        website.setText("Website");
        website.setBounds(450,270,100,30);
        website.setForeground(Colour.strongHighlight);
        website.addActionListener(this);
        overallPanel.add(website);
        setVisible(true);
    }

    //Override Method
    @Override
    public void actionPerformed(ActionEvent event) {
        //COMBOBOX2 is the Sorting combobox, changes the uniarraycopy based on which sorting the user chose
        if (event.getSource() == combobox2) {

            if (combobox2.getSelectedIndex() == 0) {            // if its A-Z
                alphaSort();
                reversed = false;
            }
            else if (combobox2.getSelectedIndex() == 1) {                //if its Z-A
                alphaSort();
                reverse();
                reversed = true;
                currentPage = 0;
            }
            else if (combobox2.getSelectedIndex() == 2) {                //Low to High, Average
                insertionSort();
            }

            else if (combobox2.getSelectedIndex() == 3) {               //high to low, average
                insertionSort();
                reverse();
                reversed = true;
            }
            currentPage = 0;
            overallPanel.remove(uniPanel);

            //creates a new unipanel(top right corner), and changes the picture
            uniPanel = createUniPanel(uniArrayCopy.get(currentPage));
            uniPanel.setBounds(590, 0, 400, 500);
            overallPanel.add(uniPanel);
            currentLocation.setText(currentPage + 1 + "/14");
            currentLocation.repaint();

            overallPanel.repaint();
        }

        //COMBOBOX1 is the combobox where you can individually select a university to look at
        if (event.getSource() == combobox1) {
            reversed = false;
            uniArrayCopy = new ArrayList<>(uniClass.getUniversities());
            alphaSort();
            int comboboxIndex = combobox1.getSelectedIndex();
            overallPanel.remove(uniPanel);
            uniPanel = createUniPanel(uniArrayCopy.get(comboboxIndex));
            uniPanel.setBounds(590, 0, 400, 500);
            scrollBar.setValue(comboboxIndex);
            currentPage = comboboxIndex;
            overallPanel.add(uniPanel);
            currentLocation.setText(currentPage + 1 + "/14");
            currentLocation.repaint();
            overallPanel.repaint();
        }

        //"next" button
        if (event.getSource() == nextBtn) {
            currentPage += 1;
            if (currentPage== maxIndex) {
                currentPage = 0;
            }
            if (currentPage == -1) {
                currentPage = maxIndex;
            }
            overallPanel.remove(uniPanel);
            combobox1.setSelectedIndex(currentPage);

            uniPanel = createUniPanel(uniArrayCopy.get(currentPage));
            uniPanel.setBounds(590, 0, 400, 500);
            overallPanel.add(uniPanel);
            currentLocation.setText(currentPage + 1 + "/14");
            currentLocation.repaint();
            combobox1.repaint();
            overallPanel.repaint();
        }
        //"back" button
        if (event.getSource() == backBtn) {

            currentPage -= 1;
            if (currentPage == -1) {
                currentPage = maxIndex-1;
            }
            if (currentPage== maxIndex) {
                currentPage = 0;
            }
            overallPanel.remove(uniPanel);

            combobox1.setSelectedIndex(currentPage);
            uniPanel = createUniPanel(uniArrayCopy.get(currentPage));
            uniPanel.setBounds(590, 0, 400, 500);

            overallPanel.add(uniPanel);
            combobox1.repaint();
            currentLocation.setText(currentPage + 1 + "/14");
            currentLocation.repaint();
            overallPanel.repaint();

        }
        if (event.getSource() == resetButton) {
            maxIndex = 14;
            uniArrayCopy = new ArrayList<>(uniClass.getUniversities());
            keyword.setText("");
            currentPage = 0;
            overallPanel.remove(uniPanel);

            //creates a new unipanel(top right corner), and changes the picture
            uniPanel = createUniPanel(uniArrayCopy.get(currentPage));
            uniPanel.setBounds(590, 0, 400, 500);
            overallPanel.add(uniPanel);


            combobox2.setSelectedIndex(0);
            currentLocation.setText(currentPage + 1 + "/14");
            currentLocation.repaint();
            overallPanel.repaint();
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

        //button to start search
        if (event.getSource() == searchButton) {
            customList.clear();
            //tempnames is the names of the university without the "university of" and lowercase, ex. University of Toronto --> toronto
            ArrayList<String> tempNames = new ArrayList<>();
            for (University uni : uniClass.getUniversities()){
                tempNames.add(uni.getName());
            }
            for (int x = 0; x < tempNames.size(); x++) {
                tempNames.set(x, replaceFiller(tempNames.get(x)));
            }
            currentPage = 0;

            String text = keyword.getText();
            text = replaceFiller(text);

            //checks if what the user entered is the university name
            for (int i = 0; i < tempNames.size(); i++) {
                if (text.equalsIgnoreCase(tempNames.get(i))){
                    customList.add(uniClass.getUniversities().get(i));
                }
            }

            //checks if what the user entered falls into a list of keywords associated with the university
            for (University unis : uniClass.getUniversities()) {
                String keywords;
                keywords = unis.getKeywords();

                if (keywords.contains(text)){
                    if (customList.size() != 0) {
                        for (int i = 0; i < customList.size(); i++) {
                            if ((!unis.getName().equals(customList.get(i).getName()))) {
                                customList.add(unis);
                                break;
                            }
                        }
                    }
                    else {
                        customList.add(unis);
                    }
                }
            }
            //shows a no matches label if there are no matches
            if (customList.size() == 0){
                searchFailed.setText("No Matches");
                searchFailed.setBounds(20,300,100,35);
                searchFailed.setForeground(Colour.strongHighlight);
                searchFailed.setVisible(true);
                overallPanel.add(searchFailed);
                searchFailedTF = true;
                uniArrayCopy = new ArrayList<>(uniClass.getUniversities());
            }
            else if (text.equalsIgnoreCase("")) {
                maxIndex = 14;
                uniArrayCopy = new ArrayList<>(uniClass.getUniversities());
            }

            //updates the uriarraycopy with the matching universities
            else {
                if (searchFailedTF)
                    overallPanel.remove(searchFailed);
                searchFailedTF = false;
                currentPage = 0;
                uniArrayCopy = new ArrayList<>(customList);
                maxIndex = customList.size();
                overallPanel.remove(uniPanel);
                uniPanel = createUniPanel(uniArrayCopy.get(currentPage));
                uniPanel.setBounds(590, 0, 400, 500);
                combobox2.setSelectedIndex(0);
                combobox2.repaint();

                overallPanel.add(uniPanel);

            }
            overallPanel.repaint();

        }
    }

    //reverses the uniarraycopy
    public void reverse() {
        ArrayList<University> temp = new ArrayList<>(14);

        for (int i = uniArrayCopy.size(); i > 0; i--) {
            temp.add(uniArrayCopy.get(i-1));
        }
        uniArrayCopy = temp;
    }

    //creates the text info and the pictures of each uni
    public JPanel createUniPanel(University uni) {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(400, 500);
        JLabel nameLabel = new JLabel(uni.getName());
        JLabel infoLabel = new JLabel("<html>" + uni.getDescription() + "<html>");
        JLabel nationalRankLabel = new JLabel("Rank: " + uni.getNationalRank());
        JLabel logo = new JLabel();
        logo.setIcon(uni.getLogo());
        logo.setBounds(0,70,300,200);

        nameLabel.setBounds(0, 0, 400, 80);
        nameLabel.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
        nameLabel.setForeground(Colour.strongHighlight);
        nationalRankLabel.setBounds(0, 30, 500, 80);
        nationalRankLabel.setFont(new Font(title.getFont().getName(), Font.PLAIN, 14));
        nationalRankLabel.setForeground(Colour.strongHighlight);
        infoLabel.setBounds(0, 120, 300, 500);
        infoLabel.setFont(new Font(title.getFont().getName(), Font.PLAIN, 12));
        infoLabel.setForeground(Colour.strongHighlight);
        panel.add(logo);
        panel.add(nameLabel);
        panel.add(nationalRankLabel);
        panel.add(infoLabel);
        picture.setIcon(uniArrayCopy.get(currentPage).getIcon());
        picture.setVisible(true);
        panel.setBackground(Colour.bg);
        repaint();
        return panel;

    }

    public void insertionSort() {
        //Runs for every index of the array except the first
        for (int x = 1; x < uniArrayCopy.size(); x++) {
            /* In the for loop below it will...
             * take the value in the index x (which is one greater in each iteration)
             * and continue to "shift" it until the value is greater than the value of the contents in index y
             * thus moving it into its sorted place in the array
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
                    uniArrayCopy.set(j,temp);
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