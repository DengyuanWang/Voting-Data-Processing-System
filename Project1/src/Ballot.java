public class Ballot {
	public String Ballot_type = "";//IR or OPL, means instant run-off or open party list
	public int voter_index = 0;//or voter index
	private int candidates_num = 0;
	private String[] candidates; 
	private String[] party_of_candidate; 
	private int[] vote;         //vote[i]: rank of candidate #i
	private int seatnum;
	public Ballot(String type,int seat_num, int voter_index,int candidatenum,String[] candidates_in,int[] vote)
	{
		Ballot_type = "";//IS or OPL, means instant run-off or open party list
		candidates_num = 0;
		seatnum = seat_num;
		if(set_Ballot_type(type)==false)
			System.out.print("ballot type error\n");
		else if(set_candidatesnum(candidatenum)==false)
			System.out.print("candidatesnum error\n");
		else if(set_candidate(candidates_in)==false)
			System.out.print("candidates in error\n");
		else if(add_ballot(vote)==false)
			System.out.print("ballot data error\n");
	}
	private boolean set_Ballot_type(String type)
	{
		if(new String("IR").equals(type)) 
		{
			Ballot_type = "IR";
			return true;
		}
		else if(new String("OPL").equals(type))
		{
			Ballot_type = "OPL";
			return true;
		}
		else return false;
	}
	private boolean set_candidatesnum(int num)
	{
		if(candidates_num==0&&num>0)//not initialized
		{
			candidates_num = num;
			return true;
		}
		else return false;
	}
	
	public int get_candidatesnum() {return candidates_num;}
	
	private boolean set_candidate(String[] candidates_in)
	{
		//OPL
		if(Ballot_type.equals("OPL")) {
			if(2*candidates_num!=candidates_in.length)
				return false;
			party_of_candidate = new String[candidates_num];
			candidates = new String[candidates_num];
			for( int i = 0,j=0,k=0; i < candidates_in.length; i++)
			{
				if(i%2==0) candidates[j++] =  candidates_in[i].substring(1);
				else  party_of_candidate[k++] = candidates_in[i].substring(0, candidates_in[i].length()-1);
			}
			return true;
		}
		//IR
		if(candidates_num!=candidates_in.length)
			return false;
		else {
			candidates = candidates_in.clone();
		}
		return true;
	}
	private boolean add_ballot(int[] data_in)//load data into ballot
	{
		if(candidates_num!=data_in.length)
			return false;
		else {
			vote = data_in.clone();
		}
		return true;
	}
	public int get_rank_from_cand(int index)
	{
		if(index>=0&&index<candidates_num) {
            if (vote[index] == 0) return -1;
            return vote[index];
        }
        return -1;
	}

	public int get_cand_from_rank(int rank)
    {
        if(rank==0)
            return -1;
        for(int i=0;i<candidates_num;i++)
            if(vote[i]==rank)
                return i;
        return -1;
    }
	public int get_vote(int index)
	{
		if(index>=0&&index<candidates_num)
			return vote[index];
		return -1;
	}
	public String get_candidate(int index)
	{
		if(index>=0&&index<candidates_num)
			return candidates[index];
		return "";
	}
	public String get_party(int index)
	{
		if(index>=0&&index<candidates_num)
			return party_of_candidate[index];
		return "";
	}
	public int get_numofseat()
	{
		if(Ballot_type.equals("OPL"))
		{
			return seatnum;
		}
		System.out.print("error No seatsnum for IR/n");
		return -1;
	}
}