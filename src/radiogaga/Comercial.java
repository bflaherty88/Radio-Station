package radiogaga;

public class Comercial extends Broadcast
{
	public Comercial(String name, String description, long runtime, String sponser)
	{
		super(name, description, runtime);
		this.sponser = sponser;
	}

	protected String sponser;
}
