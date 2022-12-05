public class ServiceProvider {
	
	private String serviceProviderName;
	private TemplateForm form;
	
	ServiceProvider(String name){this.serviceProviderName=name;}
	public void setForm(TemplateForm form){this.form = form;}
	public TemplateForm getForm(){return this.form;}
	public String getName(){return this.serviceProviderName;}
}
