import java.util.*;
public class AdminTerminal {
	
	private AdminController aController;
	private Account aAccount;
	
	public void addServiceProvider()
	{
		ServiceProvider tmp = new ServiceProvider();
		Form tmpForm = new Form();
		String providerName;
		int nTextBoxes;
		int nDropDowns;
		
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Enter service provider name:");
		providerName = scanner.nextLine();
		tmp.name=providerName;
		System.out.println("Enter amount of textboxes:");
		nTextBoxes = scanner.nextInt();
		System.out.println("Enter amount of dropdowns:");
		nDropDowns = scanner.nextInt();
		
		while(tmpForm.getTSize()<nTextBoxes)
		{
			String textBoxName;
			System.out.println("Enter name of text box:");
			textBoxName = scanner.nextLine();
			tmpForm.addTextBox(textBoxName);
		}
		while(tmpForm.getDSize()<nDropDowns)
		{
			String dropDownName;
			System.out.println("Enter name of drop down:");
			dropDownName = scanner.nextLine();
			tmpForm.addDropDown(dropDownName);
		}
		
		
		aController.addServiceProvider(tmp);
		
	}
	
	

}
