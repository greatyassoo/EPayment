import java.util.*;

public class Form {

    protected LinkedList <Pair> textBoxes;
    protected LinkedList <Pair> dropDowns;

    public Form(){
         textBoxes = new LinkedList <Pair>();
         Pair amount = new Pair("Amount:");
         amount.setValue("0");
         textBoxes.addFirst(amount);
         dropDowns = new LinkedList <Pair>();
    }

    public boolean addTextBox(String tName) {
    	textBoxes.addLast(new Pair(tName));
    	return true;
    }
    
    public boolean addDropDown(String dName) {
    	dropDowns.addLast(new Pair(dName));
    	return true;
    }

    public Pair getFirst(){
        return textBoxes.get(0);
    }
    
    public int getTSize()
    {
    	return textBoxes.size();
    }
    public int getDSize()
    {
    	return dropDowns.size();
    }
}