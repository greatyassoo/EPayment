import java.util.*;

public class Form {

    private LinkedList <Pair> textBoxes;
    private LinkedList <Pair> dropDowns;

    public Form(){
         textBoxes = new LinkedList <Pair>();
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
    
    public int getTSize()
    {
    	return textBoxes.size();
    }
    public int getDSize()
    {
    	return dropDowns.size();
    }
}
