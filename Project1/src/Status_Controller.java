/** Status controller
 * record status in the program
 * @author wangdengyuan
 * @version V1.0
 *
 */

import java.io.IOException;

public class Status_Controller {
	
	public User Usr;
	private boolean Login_status;
	public Voting_System Voting_sys;
	public boolean Data_load_tag;
	public String Data_file_path;
	public String Interface_status;//need to be specified
	public String File_SavePath;
	public boolean Process_finish;
	public Data_IO Data_IO_;
	public Audit Audit;
	public Status_Controller(){
		Usr = new User();
		Login_status = false;
		Voting_sys = new Voting_System();
		Data_load_tag = false;
		Data_file_path = "";
		Interface_status = "";
		File_SavePath = "";
		Process_finish = false;
		Data_IO_ = new Data_IO();
	}
	public boolean Log_in(String username,String password)//during testing, we assume anyone could log_in
	{
		if(new String("3WUser").equals(username)&&new String("5801").equals(password))
		{
			Usr = new User("officer","officer");
			Login_status = true;
			return true;
		}
		Login_status = false;
		return false;
	}
	/**
	 log in
	 @return status
	 */

	public boolean LoadData(String path_in) throws IOException
	{
		if(Login_status==false)
		{
			System.out.print("please log in\n");
			return false;
		}
		if(Data_IO_.specify_path_in(path_in)==false)
		{
			System.out.print("Specify path fail\n");
			return false;
		}
		
		if(Data_IO_.load_data()==false)
			System.out.print("Load data fail\n");
		Data_load_tag = true;
		return true;
	}
	/**
	 load data
	 @return status
	 */

	public boolean ProcessData()
	{
		if(Login_status==false)
		{
			System.out.print("please log in\n");
			return false;
		}
		if(Data_load_tag==false)
		{
			System.out.print("please Load Data\n");
			return false;
		}
		boolean voting_res = Voting_sys.process_voting(Data_IO_);
		Audit=Voting_sys.auditfile;
		Process_finish = voting_res;
		return voting_res;
	}
	/**
	 process data
	 @return status
	 */

	public boolean DisplayData()
	{
		if(Login_status==false||Process_finish==false)
		{
			System.out.print("please log in\n");
			return false;
		}
		return  Audit.display();
	}
	/**
	 display data
	 @return status
	 */

	public boolean SaveData()
	{
		if(Login_status==false||Process_finish==false)
		{
			System.out.print("please log in\n");
			return false;
		}
		Audit.display();
		Data_IO_.save_result(Audit.audit_txt, "");
		return false;
	}
	/**
	 save data
	 @return status
	 */

	public boolean SaveData(String name)
	{
		if(Login_status==false||Process_finish==false)
		{
			System.out.print("please log in\n");
			return false;
		}
		Audit.display();
		Data_IO_.save_result(Audit.audit_txt, "./"+name);
		return false;
	}
	/**
	 save data
	 @return status
	 */

	public boolean Sign_out()
	{
		if(Login_status==false)
		{
			System.out.print("please log in\n");
			return false;
		}
		return false;
	}
	/**
	 sign out
	 @return status
	 */


}
