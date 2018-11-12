import java.io.IOException;

public class Tester_main {


	public static void main(String[] args) throws IOException {
		System.out.print("hello world\n");
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		/*
		Data_IO D_IO = new Data_IO();
		if(D_IO.specify_path_in("./test.csv")==false)
		{
			System.out.print("Specify path fail\n");
		}
		
		if(D_IO.load_data()==false)
			System.out.print("Load data fail\n");
		*/
		Status_Controller SC = new Status_Controller();
		SC.Log_in();
		SC.LoadData("./test.csv");
		boolean ans=SC.ProcessData();
		SC.SaveData();
	}

}
