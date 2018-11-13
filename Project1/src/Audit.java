
public class Audit {
	public String Type_of_Voting;//IR or OPL
	public int Number_of_Candidates;
	public String[] Candidates;
	public int Number_of_Ballots;
	//public Calculations
	public int[] Candidates_vote;
	public int Winner;//index of winner
	public String audit_txt;
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
	public boolean display()
	{
		if(new String("IR").equals(Type_of_Voting))
		{
			int frame_index=0;
			while(true) {
				if(frame_index==0)//first frame
				{
					audit_txt = "CandidatesName: ";
					for(int i=0;i<Number_of_Candidates;i++)
						audit_txt+=Candidates[i]+" ";
					audit_txt+="\n";
					audit_txt+="FirstChoice: ";
					for(int i=0;i<Number_of_Candidates;i++)
					{
						int k = IRVotingprocess[frame_index].Cand_Ballot[i][1];
						audit_txt += " "+Integer.toString(k)+" ";
					}	
					audit_txt+="\n";
				}
				else
				{
					audit_txt+= "CandidatesName: ";
					for(int i=0;i<Number_of_Candidates;i++)
						audit_txt+=Candidates[i]+" ";
					audit_txt+="\n";
					audit_txt+=" "+Integer.toString(frame_index)+"Changes: ";
					for(int i=0;i<Number_of_Candidates;i++)
					{
						int k = IRVotingprocess[frame_index].Cand_Ballot[i][1]-IRVotingprocess[frame_index-1].Cand_Ballot[i][1];
						audit_txt += " "+Integer.toString(k)+" ";
					}
					audit_txt+="\n";
					audit_txt+=" "+Integer.toString(frame_index)+"Choice: ";
					for(int i=0;i<Number_of_Candidates;i++)
					{
						int k = IRVotingprocess[frame_index].Cand_Ballot[i][1];
						audit_txt += " "+Integer.toString(k)+" ";
					}	
					audit_txt+="\n";
				}
				if(IRVotingprocess[frame_index].islastterm) break;
				frame_index++;
			}
			
		}else if(new String("OPL").equals(Type_of_Voting)){
			
		}
		return false;
	}
}
