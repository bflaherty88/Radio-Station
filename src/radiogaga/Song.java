package radiogaga;

public class Song extends Broadcast
{
	protected String artist, album;

	public Song(String name, String description, long runtime, String artist, String album)
	{
		super(name, description, runtime);
		this.artist = artist;
		this.album = album;
	}

	public String getArtist()
	{
		return artist;
	}
	
	public String getAlbum()
	{
		return album;
	}

	public String getRuntimeString()
	{
		return String.format("%d:%02d", runtime / 60, runtime % 60);
	}
}
