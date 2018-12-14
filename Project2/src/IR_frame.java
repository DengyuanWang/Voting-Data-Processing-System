/** IR_frame
 * data structure for IR voting
 *@author Yizhe Wang DengYuan Wang
 *@version V1.0
 */
public class IR_frame extends frames { 
	public int[] candidates_rank;
	public int[] candidates_votes;

	public boolean islastterm;		//false: don't have winner, have candiate_fail
									// true:  don't have candiate_fail, have winner
	public int[][] Cand_Ballot;     //Cand_Ballot[i][j]: the number of ballots received by candidate#i with ranking#j
	public int candidate_fail;	//index of the one defeated
	public int Winner;//index of winner
}
