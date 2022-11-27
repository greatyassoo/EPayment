import java.io.*;
import java.util.Scanner;

public class FileManagement implements LoginHandler{
    private String dir;
    private static FileManagement FileManager = null;

    private FileManagement(){
        this.dir="./Data/Users";
        File theDir = new File(dir);
        if (!theDir.exists()){
            theDir.mkdirs();
            new File(dir+"/Admin").mkdirs();
        }
    }

    public static FileManagement GetInstance(){
        if(FileManager==null){
            FileManager = new FileManagement();
        }
        return FileManager;
    }

    public boolean SignUp(String uName,String password,String phoneNum,String email) throws Exception{
        File userDirectory = new File(dir+"/"+uName);
        if(!userDirectory.exists()){
            userDirectory.mkdirs();

            File userInfo = new File(userDirectory+"/userInfo.txt");
            new File(userDirectory+"/Transactions.txt").createNewFile();
            if (userInfo.createNewFile()){

            } 
            else {
                throw new Exception();
            }

            try{
                FileWriter myWriter = new FileWriter(userDirectory+"/userInfo.txt");
                myWriter.write(uName+"\n"+password+"\n"+phoneNum+"\n"+email);
                myWriter.close();
            }
            catch (IOException e) {
                System.out.println("An error occurred.");
            }

            return true;
        }
        else{
            throw new Exception();
        }
        //return false;
    }

    public Account LogIn(String uName,String Password) throws Exception{
        String userDirectory = dir+"/"+uName;
        File directory = new File(userDirectory);
        Account Temp = new Account();
        
        if(directory.exists()){
            try{  
                File file=new File(userDirectory+"/userInfo.txt");  
                Scanner sc = new Scanner(file);
                Temp.name=sc.nextLine();
                Temp.Password=sc.nextLine();
                Temp.PhoneNumber=sc.nextLine();
                Temp.email=sc.nextLine();
                sc.close();
            }  
            catch(Exception e)  {  
                e.printStackTrace();  
            }  
        }
        return Temp;
    }

    public boolean editAccount(Account userAccount){
        Account tmp = userAccount;
        String userDirectory = dir+"/"+tmp.name;
        File directory = new File(userDirectory);
        if(directory.exists())
        {
            try{            
                FileWriter myWriter = new FileWriter(userDirectory+"/userInfo.txt");
                myWriter.write(tmp.name+"\n"+tmp.Password+"\n"+tmp.PhoneNumber+"\n"+tmp.email+"\n"+tmp.balance);
                myWriter.close();
            }
            catch(Exception e){
                
            }
        }
        return true;
    }
}