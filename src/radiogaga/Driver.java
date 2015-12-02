package radiogaga;

public class Driver
{

	public static void main(String[] args)
	{
		generateOldSongs();
		generateCommercials();
		generateReports();
		generateRadioSpots();
		Playlist p = new Playlist();
		p.readSongsFromFile("Hotlist.txt");
		new RadioGUI_2(p);
	}
	
	public static void generateOldSongs()
	{
		OldSong oldSongOne = new OldSong("Bohemian Rhapsody", "Rock", 355, "Queen", "A Night at the Opera", 10/31/1975);
		OldSong oldSongTwo = new OldSong("Stairway to Heaven", "Rock", 483, "Led Zeppelin", "Led Zeppelin IV", 12/05/1970);
		OldSong oldSongThree = new OldSong("Imagine", "Soft Rock", 183, "John Lennon", "Imagine", 10/11/1971);
		OldSong oldSongFour = new OldSong("Smells Like Teen Spirit", "Alternative Rock", 301, "Nirvana", "Nevermind", 09/10/1991);
		OldSong oldSongFive = new OldSong("Hotel California", "Soft Rock", 390, "Eagles", "Hotel California", 02/01/1977);
		OldSong oldSongSix = new OldSong("Sweet Child O'Mine", "Hard Rock", 355, "Guns N' Roses", "Appetite for Destruction", 08/15/1988);
		OldSong oldSongSeven = new OldSong("One", "Thrash Metal", 447, "Metallica", "...And Justice for All", 01/10/1989);
		OldSong oldSongEight = new OldSong("It's Not Unusual","Pop", 123, "Tom Jones", "Along Came Jones", 11/11/1964);
		OldSong oldSongNine = new OldSong("Like a Rolling Stone", "Rock", 373, "Bob Dylan", "Highway 61 Revisited", 07/20/1965);
		OldSong oldSongTen = new OldSong("Hey Jude", "Rock", 431, "The Beatles", "Single", 08/26/1968);
	}
	
	public static void generateCommercials()
	{
		Commercial comOne = new Commercial("Bose Audio", "Better Sound Through Research", 30, "Bose");
		Commercial comTwo = new Commercial("Axe Body Spray", "'Nice' women become 'Naughty'", 30, "Axe");
		Commercial comThree = new Commercial("Captain Morgan", "To Life, Love and Loot." , 30, "Diageo");
		Commercial comFour = new Commercial("Samsung Galaxy S6 Edge", "Next is Now", 30, "Samsung");
		Commercial comFive = new Commercial("iPhone 6S", "The only thing that's changed is everything.", 30, "Apple");
		Commercial comSix = new Commercial("Xbox One", "All for one. Input one.", 30, "Microsoft");
		Commercial comSeven = new Commercial("Playstation 4", "Where the Greatest Play", 30, "Sony Computer Entertainment");
		Commercial comEight = new Commercial("Fallout 4", "Welcome Home", 30, "Bethesda Game Studios");
		Commercial comNine = new Commercial("Kia Optima", "The Power to Surprise", 30, "Kia Motors");
		Commercial comTen = new Commercial("Colegate Toothpaste", "Improve mouth health simply by brushing.", 30, "Colgate");
		Commercial comEleven = new Commercial("Some Non-Prescription Drug", "We have so many side effects, why bother?!", 30, "Drug Company");
		Commercial comTwelve = new Commercial("Commercial Block", "Smorgasbord of Commercials", 300, "Various Sponsors");
	}
	
	public static void generateReports()
	{
		//News Report
		Report newsReport = new Report("News Report", "Update about the local news.", 60, NEWS);
		
		//Weather Report
		Report weatherReport = new Report("Weather Report", "Update about the local weather.", 30, WEATHER);
		
		//Traffic Report
		Report trafficeReport = new Report("Traffic Report", "Update about the current local traffic.", 60, TRAFFIC);
	}
	
	public static void generateRadioSpots()
	{
		Spot radioSpot = new Spot("Radio Spot", "Timeslot reserved for the DJ to talk about events, shows, etc.", 120);
	}

}
