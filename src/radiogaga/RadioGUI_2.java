package radiogaga;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RadioGUI_2 extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8751946572258782006L;
	private static final String PLAYLIST_FILENAME = "Playlist.txt";
	private final JPanel topButtonPanel = new JPanel();
	private final JList<String> leftList = new JList<String>();

	private JLabel titleLabel, artistLabel, genreLabel, runtimeLabel;

	private final Playlist pList;
	private Playlist workingList = new Playlist();
	private JLabel lblTotalRuntime;
	private JList<String> rightList;
	private boolean hasChanged = false;

	public RadioGUI_2(Playlist p)
	{
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				if (hasChanged)
				{
					switch (JOptionPane.showConfirmDialog(getThis(), "Would you like to save your playlist?"))
					{
					case JOptionPane.YES_OPTION:
						savePlaylist();
					case JOptionPane.NO_OPTION:
						System.exit(0);
						break;
					case JOptionPane.CANCEL_OPTION:
					default:
						break;
					}
				} else
					System.exit(0);
			}
		});
		this.pList = p;

		setTitle("All You Hear is Radio Gaga");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);

		JPanel playlistPanel = new JPanel();
		tabbedPane.addTab("Playlist", null, playlistPanel, null);
		playlistPanel.setLayout(new BoxLayout(playlistPanel, BoxLayout.Y_AXIS));

		JPanel listPanel = new JPanel();
		playlistPanel.add(listPanel);
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.X_AXIS));

		JScrollPane leftScrollPane = new JScrollPane();
		listPanel.add(leftScrollPane);
		leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		leftScrollPane.setViewportView(leftList);

		leftList.setModel(getModel(pList.getTitles()));
		leftScrollPane.setPreferredSize(new Dimension(200, 400));

		JPanel buttonPanel = new JPanel();
		listPanel.add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(0, 1, 0, 0));
		buttonPanel.add(topButtonPanel);
		topButtonPanel.setLayout(new BorderLayout(0, 0));

		JPanel southPanel = new JPanel();
		topButtonPanel.add(southPanel, BorderLayout.SOUTH);

		JButton addButton = new JButton(">");
		southPanel.add(addButton);

		addButton.addActionListener(e ->
		{
			if (leftList.getSelectedIndex() >= 0)
			{
				workingList.add(pList.getBroadcast(leftList.getSelectedIndex()));
				int i = rightList.getSelectedIndex();
				updateList();
				rightList.setSelectedIndex(i);
				updateRuntime();
			}
		});

		JPanel bottomButtonPanel = new JPanel();
		buttonPanel.add(bottomButtonPanel);
		bottomButtonPanel.setLayout(new BorderLayout(0, 0));

		JPanel northPanel = new JPanel();
		bottomButtonPanel.add(northPanel, BorderLayout.NORTH);

		JButton removeButton = new JButton("<");
		northPanel.add(removeButton);

		removeButton.addActionListener(e ->
		{
			int index = rightList.getSelectedIndex();
			if (index >= 0)
			{
				workingList.remove(rightList.getSelectedIndex());
				updateList();
				rightList.setSelectedIndex(workingList.getLength() > index ? index : index - 1);
				updateRuntime();
			}
		});

		JScrollPane rigthScrollPane = new JScrollPane();
		listPanel.add(rigthScrollPane);

		rightList = new JList<String>();
		rightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rigthScrollPane.setViewportView(rightList);
		rightList.setModel(getModel(workingList.getTitles()));
		rightList.setSelectedIndex(0);
		rigthScrollPane.setPreferredSize(new Dimension(200, 400));

		JPanel labelPanel = new JPanel();
		playlistPanel.add(labelPanel);
		labelPanel.setLayout(new GridLayout(3, 3, 0, 0));

		titleLabel = new JLabel("Title:");
		labelPanel.add(titleLabel);

		artistLabel = new JLabel("Artist:");
		labelPanel.add(artistLabel);

		genreLabel = new JLabel("Genre:");
		labelPanel.add(genreLabel);

		runtimeLabel = new JLabel("Runtime:  ");
		labelPanel.add(runtimeLabel);

		lblTotalRuntime = new JLabel("Total Runtime:  0:00");
		labelPanel.add(lblTotalRuntime);

		JPanel textFilePanel = new JPanel();
		labelPanel.add(textFilePanel);

		JButton textFileButton = new JButton("Generate Text File");
		textFileButton.addActionListener(e -> workingList.writeToFile(PLAYLIST_FILENAME));
		textFilePanel.add(textFileButton);

		JPanel timeslotPanel = new JPanel();
		tabbedPane.addTab("Timeslots", null, timeslotPanel, null);

		leftList.addListSelectionListener(e -> updateLabels((Song) pList.getBroadcast(leftList.getSelectedIndex())));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSave);

		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnFile.add(mntmLoad);
		mntmLoad.addActionListener(e -> loadPlaylist());

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		mnFile.add(mntmExit);
		mntmSave.addActionListener(e -> savePlaylist());

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmClear = new JMenuItem("Clear");
		mntmClear.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnEdit.add(mntmClear);
		mntmClear.addActionListener(e ->
		{
			if (JOptionPane.showConfirmDialog(this, "Are you sure you want to clear?", "Confirm Clear",
					JOptionPane.YES_NO_OPTION) == 0)
			{
				workingList = new Playlist();
				updateList();
			}
		});

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmYoureOnYour = new JMenuItem("You're On Your Own");
		mnHelp.add(mntmYoureOnYour);

		this.pack();
		this.setVisible(true);
	}

	private void updateList()
	{
		rightList.setModel(getModel(workingList.getTitles()));
		hasChanged = true;
	}

	private void updateRuntime()
	{
		lblTotalRuntime.setText("Total Runtime:  " + workingList.getRuntimeString());
		if (workingList.getRuntime() > 3600 * 4)
			lblTotalRuntime.setForeground(Color.RED);
		else
			lblTotalRuntime.setForeground(Color.BLACK);
	}

	private void updateLabels(Song song)
	{
		titleLabel.setText("Title:  " + song.getName());
		genreLabel.setText("Genre:  " + song.getDescription());
		artistLabel.setText("Artist:  " + song.getArtist());
		runtimeLabel.setText("Runtime:  " + song.getRuntimeString());
	}

	public void savePlaylist(String filename)
	{
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename)))
		{
			out.writeObject(workingList);
			hasChanged = false;
		}
		catch (NullPointerException e)
		{
			// Thrown with no input
		}
		catch (IOException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Invalid filepath", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void savePlaylist()
	{
		savePlaylist(JOptionPane.showInputDialog(this, "Please enter file path:"));
	}

	public void loadPlaylist(String filename)
	{
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename)))
		{
			workingList = (Playlist) in.readObject();
			updateList();
			updateRuntime();
			hasChanged = false;
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(this, "Invalid filepath", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{

		}
	}

	public void loadPlaylist()
	{
		loadPlaylist(JOptionPane.showInputDialog(this, "Please enter file path:"));
	}

	private RadioGUI_2 getThis()
	{
		return this;
	}

	@SuppressWarnings("serial")
	private AbstractListModel<String> getModel(String[] listValues)
	{
		return new AbstractListModel<String>()
		{
			String[] values = listValues;

			public int getSize()
			{
				return values.length;
			}

			public String getElementAt(int index)
			{
				return values[index];
			}
		};
	}

}
