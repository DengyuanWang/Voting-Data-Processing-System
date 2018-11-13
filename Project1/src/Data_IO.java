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
	public Data_IO(){
	    Path_in = "";//input file path
		Path_out = "";//output file path
		Error_tag = false;//true for any error occur
		ballot_num = 0;//number of ballot
		data = null;
	}
	public boolean load_data() throws IOException
	{
		String type="";
		int voter_index = 0;
		int candidatenum = 0;
		int vote_num = 0;
		int seats_num = 0;
		String[] candidates_in = null;
		int[] vote = null;
		
		
		
		if(Path_in=="")
			return false;
		else {
			File file = new File(Path_in); 
			String st; 
			int linecount = 0;
			bufferedReader = new BufferedReader(new FileReader(file));
			while ((st = bufferedReader.readLine()) != null) 
			{//  System.out.println(st); 
				linecount++;
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
					if(!new String("IR").equals(type))//OPL
					{
						vote_num = Integer.parseInt(st);
						break;
					}//continue for IR
				default://vote ballots
					String[] tmpst = st.split(",");
					if(data == null)
					{
						data = new Ballot[vote_num];
						ballot_num = vote_num;
					}
					if(vote == null)
						vote = new int[candidatenum];
					for(int i=0;i<candidatenum;i++)
					{
						if(tmpst.length<=i||new String("").equals(tmpst[i]))
							vote[i] = 0;
						else {
							vote[i] = Integer.parseInt(tmpst[i]);
						}
					}
					Ballot tmp = new Ballot(type, seats_num, voter_index, candidatenum, candidates_in, vote);
					data[voter_index] = tmp;
					
					for(int i=0;i<candidatenum;i++)
					{
						System.out.print(data[voter_index].get_vote(i)+"__");
					}
					System.out.print("\n");
					voter_index++;
					
					break;
				}
			}
			return true;
		}
	}
	public boolean save_result(Object serObj)
	{
		if(Path_out=="") return false;
		try {
			String fileName = Path_out + "audit.txt"; 
			String str = "Hello";
		    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		    writer.write(str);
		     
		    writer.close();
		}
		catch (Exception ex) {
				ex.printStackTrace();
		}
		return true;
	}
	public boolean save_result(Object serObj,String path)
	{
		String fileName = path + "audit.txt"; 
		try {
			String str = "Hello";
		    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		    writer.write(str);
		     
		    writer.close();
		}
		catch (Exception ex) {
				ex.printStackTrace();
		}
		return true;
	}

	public boolean specify_path_in(String path)
	{
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
	public boolean specify_path_out(String path)
	{
		if(path.substring(path.length()-4)==".csv")
			return true;
		else return false;
	}
}