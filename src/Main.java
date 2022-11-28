public class Main {
    public static void main(String[] args) {
        LoginHandlerProxy px = new LoginHandlerProxy();
        try{
            px.SignUp("Faris","i-likem-wet-69","0123456789","Faris-tneen@fci.lol");
            //Account x = FileManagement.GetInstance().LogIn("Faris","123");
            //System.out.println(x.email);
        }catch(Exception e){};
    }
}