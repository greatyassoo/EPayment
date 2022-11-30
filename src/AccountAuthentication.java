public interface AccountAuthentication{
    public Account login(String userName, String password);
    public boolean signUp(Account account);
}