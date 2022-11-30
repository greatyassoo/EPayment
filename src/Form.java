import java.util.ArrayList;

public class Form {

    private int nTextBox;
    private int nDropDown;
    // private ArrayList <Pair<String,Integer>> textBoxes;
    // private ArrayList <Pair<String,Integer>> dropDowns;

    // public Form(){
    //     this.nTextBox =0;
    //     this.nDropDown =0;
    //     textBoxes = new ArrayList <Pair<>>();
    //     dropDowns = new ArrayList <Pair<>>();
    // }

    public void setnTextBox(int n)
    {nTextBox = n;}
    public void setnDropDown(int n)
    {nDropDown = n;}

    public int getnTextbox()
    {return this.nTextBox;}
    public int getnDropDown()
    {return this.nDropDown;}
    
}
