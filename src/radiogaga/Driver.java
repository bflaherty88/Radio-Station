package radiogaga;

public class Driver
{

	public static void main(String[] args)
	{
		Playlist p = new Playlist();
		p.readSongsFromFile("Hotlist.txt");
		new RadioGUI_2(p);
	}

}
