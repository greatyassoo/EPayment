import java.io.*;
import java.util.Scanner;

public class FileManagement implements LoginHandler{
    private static String dir="./Data";
    private static String userDirectory = dir+"/Accounts/";
    private static FileManagement FileManager = null;
    
    private FileManagement(){
        makeDir(dir);
        makeDir(dir+"/Accounts/Admin");
        try{
            new File(dir+"/Accounts/Admin/userInfo.txt").createNewFile();
        }catch(Exception e){
            System.out.println("Error creating admin account");
        };
        Account Admin = new Account();
        Admin.setName("Admin");
        Admin.setPassword("Admin");
        updateAccount(Admin);
        String tempDir = dir+"/Services";
        makeDir(tempDir+"/MobileRechargeServices");
        makeDir(tempDir+"/InternetPaymentServices");
        makeDir(tempDir+"/LandLineServices");
        makeDir(tempDir+"/Donations");
    }

    private boolean makeDir(String dir){
        File temp = new File(dir);
        if(!temp.exists()){
            temp.mkdirs();
        };
        return true;
    }

    public static FileManagement GetInstance(){
        if(FileManager==null){
            FileManager = new FileManagement();
        }
        return FileManager;
    }

    public boolean SignUp(String uName,String password,String phoneNum,String email) throws Exception{
        File userFile = new File(userDirectory+uName);

        userFile.mkdirs();

        new File(userDirectory+uName+"/Transactions.txt").createNewFile();
        new File(userDirectory+uName+"/userInfo.txt").createNewFile();

        try{
            FileWriter myWriter = new FileWriter(userDirectory+uName+"/userInfo.txt");
            myWriter.write(uName+"\n"+password+"\n"+phoneNum+"\n"+email);
            myWriter.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
        }

        return true;
        //return false;
    }

    public Account LogIn(String uName,String Password) throws Exception{
        File userFile = new File(userDirectory+uName);

        if(!userFile.exists()){
            throw new Exception(); 
        }

        Account Temp = new Account();
        try{  
            File file=new File(userDirectory+uName+"/userInfo.txt");  
            Scanner sc = new Scanner(file);
            Temp.name=sc.nextLine();
            Temp.password=sc.nextLine();
            Temp.phoneNumber=sc.nextLine();
            Temp.email=sc.nextLine();
            sc.close();
        }catch(Exception e)  {  
            e.printStackTrace();  
        }
        return Temp;
    }

    private boolean updateAccount(Account uAccount){
        File directory = new File(userDirectory);
        if(directory.exists()){
            try{            
                FileWriter myWriter = new FileWriter(userDirectory+uAccount.getName()+"/userInfo.txt");
                myWriter.write(uAccount.name+"\n"+uAccount.password+"\n"+uAccount.phoneNumber+"\n"+uAccount.email+"\n"+uAccount.balance);
                myWriter.close();
            }catch(Exception e){}
        }
        return true;
    }

    public boolean updateAll(Account uAccount){
        updateAccount(uAccount);
        return true;
    }
}