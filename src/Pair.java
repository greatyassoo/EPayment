public class Pair{
	protected String name;
	protected String value;    
	public Pair(String name) {
		super();
		this.name = name;
		this.value = null;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}