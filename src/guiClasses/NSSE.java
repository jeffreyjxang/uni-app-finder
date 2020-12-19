package guiClasses;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class NSSE {

	// GUI
	public static JFrame frame;

	public static void CreateNSSE() {

		frame = new JFrame();
		frame.setSize(537, 585);
		frame.setLayout(null);
		frame.setVisible(true);

		JLabel table = new JLabel(new ImageIcon("resources/dataTables/NSSE.png"));
		table.setBounds(0, 0, 517, 542);
		frame.add(table);

	}

}
