/** Audit
 * Class for audit data
 *@author Yuanli Wang
 *@version V1.0
 */

import java.util.*;
public class Audit {
	public String Type_of_Voting;//IR or OPL
	public int Number_of_Candidates;
	public String[] Candidates;
	public int Number_of_Ballots;
	//public Calculations
	public int[] Candidates_vote;
	public int Winner;//index of winner
	public String audit_txt;
	public String invbal_txt;
	//public Process
	public OPL_frame[] OPLVotingprocess;
	public IR_frame[] IRVotingprocess;

	public Integer[] IRVInvalidBallots; //invalid ballots in IRV
	public int IRVInvalidBallots_size;
	private Data_IO __ballotdat__;
	/**
	 class of audit info
	 */
	public Audit(Data_IO DIO){
		__ballotdat__=DIO;
		Type_of_Voting = DIO.data[0].Ballot_type;//IR or OPL
		Number_of_Candidates = DIO.data[0].get_candidatesnum();
		Candidates = new String[Number_of_Candidates];
		for(int i=0;i<Number_of_Candidates;i++)
			Candidates[i] = DIO.data[0].get_candidate(i);
		Number_of_Ballots = DIO.ballot_num;
		Winner = -1;//index of winner
	}

	/**
	 Write Invalid Ballots into file
	 @return status
	 */
	public boolean WriteInvBallots()
	{
		if(new String("IR").equals(Type_of_Voting))
		{
			invbal_txt="Line - ";
			for(int i=0;i<Number_of_Candidates;i++)
				invbal_txt+=Candidates[i]+" ";
			invbal_txt+="\n";
			for(int i=0;i<IRVInvalidBallots_size;i++)
			{
				int N=IRVInvalidBallots[i];
				invbal_txt+=N+1;
				invbal_txt+=" - ";
				invbal_txt+=__ballotdat__.data[N].Ballot2String();
				invbal_txt+="\n";
			}
		}
		return true;
	}
	/**
	 display audit info
	 @return status
	 */
	public boolean display()
	{
		boolean ans=true;
		if(new String("IR").equals(Type_of_Voting))
			ans=display_IR();
		else if(new String("OPL").equals(Type_of_Voting))
			ans=display_OPL();
		return ans;
	}
	/**
	 display audit info in IR
	 @return status
	 */
	private boolean display_IR() {
		if (new String("IR").equals(Type_of_Voting)) {
			int frame_index = 0;
			while (true) {
				if (frame_index == 0)//first frame
				{
					audit_txt = "CandidatesName: ";
					for (int i = 0; i < Number_of_Candidates; i++)
						audit_txt += Candidates[i] + " ";
					audit_txt += "\n";
					audit_txt += "FirstChoice: ";
					for (int i = 0; i < Number_of_Candidates; i++) {
						int k = IRVotingprocess[frame_index].Cand_Ballot[i][1];
						audit_txt += " " + Integer.toString(k) + " ";
					}
					audit_txt += "\n";
				} else {
					audit_txt += "CandidatesName: ";
					for (int i = 0; i < Number_of_Candidates; i++)
						audit_txt += Candidates[i] + " ";
					audit_txt += "\n";
					audit_txt += " " + Integer.toString(frame_index) + "Changes: ";
					for (int i = 0; i < Number_of_Candidates; i++) {
						int k = IRVotingprocess[frame_index].Cand_Ballot[i][1] - IRVotingprocess[frame_index - 1].Cand_Ballot[i][1];
						if (k < 0)
							audit_txt += " X ";
						else
							audit_txt += " " + Integer.toString(k) + " ";
					}
					audit_txt += "\n";
					audit_txt += " " + Integer.toString(frame_index) + "Choice: ";
					for (int i = 0; i < Number_of_Candidates; i++) {
						int k = IRVotingprocess[frame_index].Cand_Ballot[i][1];
						audit_txt += " " + Integer.toString(k) + " ";
					}
					audit_txt += "\n";
				}
				if (IRVotingprocess[frame_index].islastterm) {
					audit_txt += "Winner: " + Candidates[IRVotingprocess[frame_index].Winner];
					break;
				}
				frame_index++;
			}
		}
		return false;
	}
	/**
	 display audit info in OPL
	 @return status
	 */
	private boolean display_OPL() {
		if (new String("OPL").equals(Type_of_Voting)) {
			int frame_index = 0;
			List<String> parties = new ArrayList<String>();
			while (true) {
				if (frame_index == 0)//first frame
				{
					audit_txt = "PartyName: ";

					for (String party : OPLVotingprocess[frame_index].PartySeats.keySet()) {
						parties.add(party);
						audit_txt += party + " ";
					}
					audit_txt += "\n";
				}
				if (!OPLVotingprocess[frame_index].partyFinish)//
				{
					audit_txt += "Votes num:";
					for (int i = 0; i < parties.size(); i++)
						audit_txt += Integer.toString(OPLVotingprocess[frame_index].PartyVotes.get(parties.get(i))) + " ";
					audit_txt += "\nSeats num:";
					for (int i = 0; i < parties.size(); i++)
						audit_txt += Integer.toString(OPLVotingprocess[frame_index].PartySeats.get(parties.get(i))) + " ";
					audit_txt += "\n";
				} else {
					audit_txt += "CandidateWinSeatsName: ";
					List<String> candiWseats = new ArrayList<String>();
					for (String candi : OPLVotingprocess[frame_index].CandSeats.keySet()) {
						candiWseats.add(candi);
						audit_txt += candi + " ";
					}
					audit_txt += "\nParty name of them:";
					for (int i = 0; i < candiWseats.size(); i++)
						audit_txt += OPLVotingprocess[frame_index].CandParty.get(candiWseats.get(i)) + " ";
					audit_txt += "\n";
					break;
				}
				frame_index++;
			}
		}
		return false;
	}
}
