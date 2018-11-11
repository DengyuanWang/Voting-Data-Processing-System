
public class User {
	public String Name;
	private int Authority_level=0;//1 for tester, 2 for officer
	//public String Age;
	//public String Address;
	//public int[] Phone;
	//public String Email;
	public String Account="";
	private String File="";
	public User() {
		
	}
	public User(String account,String type){
		
		if(new String("tester").equals(type))
			Authority_level = 1;
		else if(new String("officer").equals(type))
			Authority_level = 2;
		Account = account;
	}
	public boolean load_script(String path_in)
	{
		if (Authority_level!=1)//only works for tester
			return false;
		else {
			File = "";
			return true;
		} 
	}
	public String get_auth_Level() 
	{
		if (Authority_level==1)
			return "tester";
		else if (Authority_level==2)
			return "officer";
		else return "error";
	}
}
