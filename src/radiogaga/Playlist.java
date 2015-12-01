package radiogaga;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Playlist implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4711570136374322312L;
	private static final String FILE_DELIMITER = "/";
	private List<Broadcast> playlist = new LinkedList<Broadcast>();
	private long runtime;

	public Playlist()
	{
		super();
	}

	public Playlist(Collection<Broadcast> playlist)
	{
		super();
		this.playlist.addAll(playlist);
	}

	public Playlist(Playlist other)
	{
		this.playlist.addAll(other.playlist);
		this.runtime = other.runtime;
	}

	public void add(Broadcast bc)
	{
		playlist.add(bc);
		runtime += bc.getRuntime();
	}

	public void insert(Broadcast bc, int index)
	{
		playlist.add(index, bc);
		runtime += bc.getRuntime();
	}

	public void remove(Broadcast bc)
	{
		playlist.remove(bc);
		runtime -= bc.getRuntime();
	}

	public void remove(int index)
	{
		runtime -= playlist.get(index).getRuntime();
		playlist.remove(index);
	}

	public void clear()
	{
		playlist.clear();
		runtime = 0;
	}

	public Broadcast getBroadcast(int index)
	{
		return playlist.get(index);
	}

	public int getLength()
	{
		return playlist.size();
	}

	public String getRuntimeString()
	{
		return String.format("%01d:%02d:%02d", runtime / 3600, (runtime / 60) % 60, runtime % 60);
	}

	public long getRuntime()
	{
		return runtime;
	}

	public void readSongsFromFile(String filename)
	{
		try (Scanner in = new Scanner(new BufferedReader(new FileReader(filename))))
		{
			String[] line;
			long runtime = 0;

			in.nextLine();
			while (in.hasNextLine())
			{
				line = in.nextLine().split(FILE_DELIMITER, -1);

				if (line[0].startsWith(";") || line.length < 5)
					continue;

				String[] times = line[3].split(":");
				runtime = Long.parseLong(times[0]) * 60;
				runtime += Long.parseLong(times[1]);

				line[4] = line[4].equals("") ? "Misc" : line[4];
				this.add(new Song(line[0], line[4], runtime, line[1], line[2]));
			}

		}
		catch (FileNotFoundException e)
		{
			System.err.printf("The file (%s) does not exist!\n", filename);
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.err.println("There was an error reading songs from file!");
			e.printStackTrace();
		}
	}

	public void writeToFile(String filename)
	{
		try (BufferedWriter out = new BufferedWriter(new FileWriter(filename)))
		{
			for (Broadcast bc : playlist)
				out.append(bc.toString() + System.lineSeparator());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] getTitles()
	{
		String[] titles = new String[playlist.size()];
		for (int i = 0; i < titles.length; i++)
			titles[i] = playlist.get(i).getName();

		return titles;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		for (Broadcast bc : playlist)
			sb.append(bc.toString() + "\n");

		return sb.toString();
	}

	public List<Broadcast> getPlaylist()
	{
		return playlist;
	}

}
