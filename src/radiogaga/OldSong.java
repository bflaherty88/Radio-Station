package radiogaga;

import java.util.Date;

public class OldSong extends Song
{
	protected Date lastAired;
	
	public OldSong(String name, String description, long runtime, String artist, String album, Date lastAired)
	{
		super(name, description, runtime, artist, album);
		this.lastAired = lastAired;
	}
}
