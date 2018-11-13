import java.io.IOException;

/**
 * 
 */

/**
 * @author wangdengyuan
 *
 */
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
		Audit = new Audit();
		Data_IO_ = new Data_IO();
	}
	public boolean Log_in()//during testing, we assume anyone could log_in
	{
		Login_status = true;
		return true;
	}
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
		}
		
		if(Data_IO_.load_data()==false)
			System.out.print("Load data fail\n");
		return false;
	}
	public boolean ProcessData()
	{
		if(Login_status==false)
		{
			System.out.print("please log in\n");
			return false;
		}
		boolean voting_res = Voting_sys.process_voting(Data_IO_);
		Audit=Voting_sys.auditfile;
		return voting_res;
	}
	public boolean DisplayData()
	{
		if(Login_status==false)
		{
			System.out.print("please log in\n");
			return false;
		}
		return false;
	}
	public boolean SaveData()
	{
		if(Login_status==false)
		{
			System.out.print("please log in\n");
			return false;
		}
		Data_IO_.save_result(Audit, "./");
		return false;
	}
	public boolean Sign_out()
	{
		if(Login_status==false)
		{
			System.out.print("please log in\n");
			return false;
		}
		return false;
	}

}
