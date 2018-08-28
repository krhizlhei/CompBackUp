package Model;

public class User 
{
	public String username;
	public String password;
	
	public String gUsername()
	{
		return username;
	}
	public String gPassword()
	{
		return password;
	}
	public void sUsername(String username)
	{
		this.username=username;
	}
	public void sPassword(String password)
	{
		this.password=password;
	}

}
