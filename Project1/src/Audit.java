
public class Audit {
	public String Type_of_Voting;//IR or OPL
	public int Number_of_Candidates;
	public String[] Candidates;
	public int Number_of_Ballots;
	//public Calculations
	public int[] Candidates_vote;
	public int Winner;//index of winner
	//public Process
	public OPL_frame[] OPLVotingprocess;
	public IR_frame[] IRVotingprocess;
	public Audit(Data_IO DIO){
		Type_of_Voting = DIO.data[0].Ballot_type;//IR or OPL
		Number_of_Candidates = DIO.data[0].get_candidatesnum();
		Candidates = new String[Number_of_Candidates];
		for(int i=0;i<Number_of_Candidates;i++)
			Candidates[i] = DIO.data[0].get_candidate(i);
		Number_of_Ballots = DIO.ballot_num;
		Winner = -1;//index of winner
	}

}
