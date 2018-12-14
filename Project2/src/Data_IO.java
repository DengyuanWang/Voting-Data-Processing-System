/** Data I/O
 * Code for process data I/O operations
 *@author Yuanli Wang
 *@version V1.0
 */

import java.io.*;

public class Data_IO implements Serializable {
	//default serialVersion id
	private static final long serialVersionUID = 1L;

	public String Path_in = "";//input file path
	public String Path_out = "";//output file path
	public boolean Error_tag;//true for any error occur
	public int ballot_num = 0;//number of ballot
	public Ballot[] data;//data from vote ballot
	private BufferedReader bufferedReader;
	/**
	 class of Data I/O
	 */
	public Data_IO(){
	    Path_in = "";//input file path
		Path_out = "";//output file path
		Error_tag = false;//true for any error occur
		ballot_num = 0;//number of ballot
		data = null;
	}

	/**
	 load data
	 @return status
	 */
	public boolean load_data() throws IOException
	{   if(data!=null)	{	data = null;    System.gc();	}
		String type="", st="";		int voter_index = 0, candidatenum = 0, vote_num = 0, seats_num = 0, linecount = 0;
		String[] candidates_in = null;    int[] vote = null;
		if(Path_in=="") 	return false;
		else {	File file = new File(Path_in);
			bufferedReader = new BufferedReader(new FileReader(file));
			while ((st = bufferedReader.readLine()) != null) 
			{   linecount++;
				switch(linecount) {
				case 1://vote type
					type = st;
					break;
				case 2://candidates num
					candidatenum = Integer.parseInt(st);
					break;
				case 3://candidates name
					candidates_in = st.split(",");
					break;
				case 4://vote num or number of seats
					if(new String("IR").equals(type))//IR
						vote_num = Integer.parseInt(st);
					else seats_num = Integer.parseInt(st);//OPL
					break;
				case 5://maybe vote ballots or seats
					if(!new String("IR").equals(type)) {//OPL
						vote_num = Integer.parseInt(st);
						break;
					}//continue for IR
				default://vote ballots
					String[] tmpst = st.split(",");
					if(data == null) {
						data = new Ballot[vote_num];
						ballot_num = vote_num;
					}
					if(vote == null)
						vote = new int[candidatenum];
					for(int i=0;i<candidatenum;i++)	{
						if(tmpst.length<=i||new String("").equals(tmpst[i]))		vote[i] = 0;
						else 	vote[i] = Integer.parseInt(tmpst[i]);
					}
					Ballot tmp = new Ballot(type, seats_num, voter_index, candidatenum, candidates_in, vote);
					data[voter_index] = tmp;		voter_index++;
					break;
				}
			}
			return true;
		}
	}

	/**
	 save the result
	 @param content string to be saved
	 @return status
	 */
	public boolean save_result(String content)
	{
		if(Path_out=="") return false;
		try {
			String fileName = Path_out + "audit.txt"; 
			String str = content;
		    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		    writer.write(str);
		     
		    writer.close();
		}
		catch (Exception ex) {
				ex.printStackTrace();
		}
		return true;
	}

	/**
	 save the result
	 @param content string to be saved
	 @return status
	 */
	public boolean save_result(String content,String path)
	{
		String fileName;
		if(path=="")
			fileName = "./" + "audit.txt"; 
		else fileName =  path;
		try {
			String str = content;
		    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		    writer.write(str);
		     
		    writer.close();
		}
		catch (Exception ex) {
				ex.printStackTrace();
		}
		return true;
	}

	/**
	 specify_path_input
	 @param path specify_path_input
	 @return status
	 */
	public boolean specify_path_in(String path)
	{
		if(path.length()<4) 
		{
			System.out.print("wrong path input\n");
			return false;}
		if(new String(".csv").equals(path.substring(path.length()-4)) )
		{
			Path_in = path;
			return true;
		}
		else {
			System.out.print(path.substring(path.length()-4));
			return false;
		}
	}

	/**
	 specify_path_output
	 @param path specify_path_output
	 @return status
	 */
	public boolean specify_path_out(String path)
	{
		if(path.substring(path.length()-4)==".csv")
			return true;
		else return false;
	}
}
