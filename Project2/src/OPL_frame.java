/** OPL_frame
 *  data structure for OPL voting
 *  @author Yizhe Wang DengYuan Wang
 *  @version V1.0
 */
import java.util.HashMap;

public class OPL_frame extends frames{
	public boolean partyFinish;//false: still rank party || true: give seats to candidates within party

	public int[] party_rank;
	public int[] party_seats;
	public int[] canditates_rank_withinParty;
	public int[] candidates_get_seats;

	public HashMap<String, Integer> PartyVotes;		//key=party, val=num of votes
													//frames[0]: 1st allocation
													//frames[1]: remain votes
	public HashMap<String, Integer> PartySeats;		//key=party, val=num of seats
	public HashMap<String, Integer> CandSeats;		//key=name, val=rank in its party
	public HashMap<String, String> CandParty;		//key=name, val=its party
}
