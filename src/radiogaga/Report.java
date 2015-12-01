package radiogaga;

public class Report extends Broadcast
{
	enum Type
	{
		NEWS, TRAFFIC;
	}

	public Report(String name, String description, long runtime, Type type)
	{
		super(name, description, runtime);
		this.type = type;
	}

	protected Type type;

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

}
