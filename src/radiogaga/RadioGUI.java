package radiogaga;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class RadioGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3097717984710978912L;
	private JLabel library = new JLabel("Library: ");
	private JLabel playlist = new JLabel("Playlist: ");
	private JPanel libPanel = new JPanel();
	private JPanel playPanel = new JPanel();
	private JPanel butPanel = new JPanel();
	private JPanel mainPanel = new JPanel();
	private JPanel timePanel = new JPanel();
	private JTabbedPane tabs = new JTabbedPane();
	private JMenuBar menuBar = new JMenuBar();

	Playlist p = new Playlist();

	public RadioGUI() {

		p.readSongsFromFile("HotList.txt");
		
		//CHANGE BORDERLAYOUT TO FIX SIZABLE CENTER
		
		setJMenuBar(menuBar);
		this.setSize(1200, 900);
		this.setTitle("Best of OOPDA Playlist Creator");
		this.setContentPane(tabs);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(libPanel, BorderLayout.WEST);
		mainPanel.add(playPanel, BorderLayout.EAST);
		mainPanel.add(butPanel, BorderLayout.CENTER);
		tabs.addTab("Playlist Creator", mainPanel);
		tabs.addTab("Time Slot", timePanel);

		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		
		butPanel.setBackground(Color.GRAY);
		butPanel.setLayout(new BoxLayout(butPanel, BoxLayout.PAGE_AXIS));
		JPanel innerBut = new JPanel(new GridLayout(0, 1, 0, 5));
		JButton delete = new JButton("<-- delete");
		JButton add = new JButton("add -->");
//		delete.setPreferredSize(new Dimension(40, 40));
//		add.setPreferredSize(new Dimension(40, 40));
		innerBut.add(add);
		innerBut.add(delete);
		innerBut.setOpaque(false);
		innerBut.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
		butPanel.add(Box.createVerticalGlue());
		butPanel.add(innerBut);
		butPanel.add(Box.createVerticalGlue());
		

		libPanel.setBackground(Color.GRAY);
		libPanel.setLayout(new BoxLayout(libPanel, BoxLayout.PAGE_AXIS));
		libPanel.add(library);
		JTextArea lib = new JTextArea();
		lib.setEditable(false);
		lib.setBorder(BorderFactory.createLoweredBevelBorder());
		JList<String> libFile = new JList<String>();
		lib.add(libFile);
		libPanel.add(lib);

		playPanel.setBackground(Color.GRAY);
		playPanel.setLayout(new BoxLayout(playPanel, BoxLayout.PAGE_AXIS));
		playPanel.add(playlist);
		JTextArea play = new JTextArea();
		play.setEditable(false);
		play.setBorder(BorderFactory.createLoweredBevelBorder());
		JList<String> playFile = new JList<String>();
		play.add(playFile);
		playPanel.add(play);

		playFile.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
			}
		});

		libFile.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
			}
		});

//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		setBounds(0,0,screenSize.width, screenSize.height);
//		this.pack();
//		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
