public class LoginHandlerProxy implements LoginHandler{
    
    private boolean validateUser(String uName, String password){
        try{FileManagement.GetInstance().LogIn(uName, password);}
        catch(Exception e) {return true;} // if login throws exception then validate returns true;
        return false; // if no exception happens then false
    }

	@Override
	public boolean SignUp(String uName,String password,String phoneNum,String email) throws Exception{
        if(validateUser(uName, password)){
            FileManagement.GetInstance().SignUp(uName, password, phoneNum, email);
            return true;
        }
        return false;
	}

    @Override
    public Account LogIn(String uName, String password) throws Exception{
        if(!validateUser(uName, password)){
            return null;
        }
        


        return FileManagement.GetInstance().LogIn(uName, password);
    }

}