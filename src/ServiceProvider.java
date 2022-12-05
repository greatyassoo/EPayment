public class ServiceProvider {
	
	protected String spName;
	protected TemplateForm form;
	
	ServiceProvider(String name)
	{
		this.spName=name;
	}
	public void setForm(TemplateForm form)
	{
		this.form = form;
	}
	public TemplateForm getForm()
	{
		return this.form;
	}
	public String getName()
	{
		return this.spName;
	}

}
