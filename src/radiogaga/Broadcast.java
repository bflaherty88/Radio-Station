package radiogaga;

import java.io.Serializable;

public abstract class Broadcast implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 847311827330144504L;
	protected String name, description;
	protected long runtime;
	
	public Broadcast(String name, String description, long runtime)
	{
		this.name = name;
		this.description = description;
		this.runtime = runtime;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return description;
	}

	public long getRuntime()
	{
		return runtime;
	}
	
	public String toString()
	{
		return name;
	}
}
