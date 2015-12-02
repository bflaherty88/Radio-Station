package radiogaga;

public class Driver
{

	public static void main(String[] args)
	{
		Playlist p = new Playlist();
		p.readSongsFromFile("Hotlist.txt");
		new RadioGUI_2(p);
	}
	
	public static void generateOldSongs()
	{
		oldSongOne = new OldSong();
		oldSongTwo = new OldSong();
		oldSongThree = new OldSong();
		oldSongFour = new OldSong();
		oldSongFive = new OldSong();
		oldSongSix = new OldSong();
		oldSongSeven = new OldSong();
		oldSongEight = new OldSong();
		oldSongNine = new OldSong();
		oldSongTen = new OldSong();
	}

}
