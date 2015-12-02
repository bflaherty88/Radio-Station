package radiogaga;

public class Driver
{

	public static void main(String[] args)
	{
		generateOldSongs();
		Playlist p = new Playlist();
		p.readSongsFromFile("Hotlist.txt");
		new RadioGUI_2(p);
	}
	
	public static void generateOldSongs()
	{
		OldSong oldSongOne = new OldSong("Bohemian Rhapsody", "Rock", 5:55, "Queen", "A Night at the Opera", 10/31/1975);
		OldSong oldSongTwo = new OldSong("Stairway to Heaven", "Rock", 8:03, "Led Zeppelin", "Led Zeppelin IV", 12/05/1970);
		OldSong oldSongThree = new OldSong("Imagine", "Soft Rock", 3:03, "John Lennon", "Imagine", 10/11/1971);
		OldSong oldSongFour = new OldSong("Smells Like Teen Spirit", "Alternative Rock", 5:01, "Nirvana", "Nevermind", 09/10/1991);
		OldSong oldSongFive = new OldSong("Hotel California", "Soft Rock", 6:30, "Eagles", "Hotel California", 02/01/1977);
		OldSong oldSongSix = new OldSong("Sweet Child O'Mine", "Hard Rock", 5:55, "Guns N' Roses", "Appetite for Destruction", 08/15/1988);
		OldSong oldSongSeven = new OldSong("One", "Thrash Metal", 7:27, "Metallica", "...And Justice for All", 01/10/1989);
		OldSong oldSongEight = new OldSong("It's Not Unusual","Pop", 2:03, "Tom Jones", "Along Came Jones", 11/11/1964);
		OldSong oldSongNine = new OldSong("Like a Rolling Stone", "Rock", 6:13, "Bob Dylan", "Highway 61 Revisited", 07/20/1965);
		OldSong oldSongTen = new OldSong("Hey Jude", "Rock", 7:11, "The Beatles", "Single", 08/26/1968);
	}
	
	public static void generateCommercials()
	{
		
	}
	
	public static void generateNews()
	{
		
	}
	
	public static void generateWeather()
	{
		
	}
	
	public static void generateTraffic()
	{
		
	}
	
	public static void generateRadioSpot()
	{
		
	}

}
