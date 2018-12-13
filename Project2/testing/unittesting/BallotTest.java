import static org.junit.Assert.*;

import org.junit.Test;

public class BallotTest {
	String[] type = {"","IR","OPL","ISS"};
	String[] IRcandidates_in = {"W","A","S","D"};
	String[] OPLcandidates_in = {"[W","P1]","[A","P2]","[S","P3]","[D","P4]"};
	int candidatenum = IRcandidates_in.length;
	int seats_num = candidatenum-2;
	int voter_index = 0;
	int[] vote = {0,1,2,3};
	@Test
	public void testBallot() {
		String[] result = {"","IR","OPL",""};
		for(int i=0;i<type.length;i++)
		{
			Ballot tmp = new Ballot(type[i], seats_num, voter_index, candidatenum, IRcandidates_in, vote);
			Ballot tmp2 = new Ballot(type[i], seats_num, voter_index, candidatenum, OPLcandidates_in, vote);
			assertEquals("Ballot_type_error",result[i], tmp.Ballot_type);
			assertEquals("voter_index",voter_index, tmp.voter_index);
			assertEquals("Ballot_type_error",result[i], tmp2.Ballot_type);
			assertEquals("voter_index",voter_index, tmp2.voter_index);
		}
	}

	@Test
	public void testBallot2String() {
		Ballot tmp = new Ballot(type[1], seats_num, voter_index, candidatenum, IRcandidates_in, vote);
		assertEquals("Ballot2String_IR_error"," 0 1 2 3", tmp.Ballot2String());
		Ballot tmp2 = new Ballot(type[2], seats_num, voter_index, candidatenum, OPLcandidates_in, vote);
		assertEquals("Ballot2String_OPL_error"," 0 1 2 3", tmp2.Ballot2String());
	}

	@Test
	public void testGet_candidatesnum() {
		Ballot tmp = new Ballot(type[1], seats_num, voter_index, candidatenum, IRcandidates_in, vote);
		assertEquals("get_candidatesnum_IR",candidatenum, tmp.get_candidatesnum());
		Ballot tmp2 = new Ballot(type[2], seats_num, voter_index, candidatenum, OPLcandidates_in, vote);
		assertEquals("get_candidatesnum_OPL",candidatenum, tmp2.get_candidatesnum());
	}

	@Test
	public void testGet_rank_from_cand() {
		int[] indices = {0,1,2,3,4,5,6};
		int[] results = {-1,1,2,3,-1,-1,-1};
		Ballot tmp1 = new Ballot(type[1], seats_num, voter_index, candidatenum, IRcandidates_in, vote);
		Ballot tmp2 = new Ballot(type[2], seats_num, voter_index, candidatenum, OPLcandidates_in, vote);
		for(int i=0;i<indices.length;i++)
		{
			assertEquals("get_rank_from_cand_IR",results[i], tmp1.get_rank_from_cand(i));
			assertEquals("get_rank_from_cand_OPL",results[i], tmp2.get_rank_from_cand(i));
		}
	}

	@Test
	public void testGet_cand_from_rank() {
		Ballot tmp1 = new Ballot(type[1], seats_num, voter_index, candidatenum, IRcandidates_in, vote);
		Ballot tmp2 = new Ballot(type[2], seats_num, voter_index, candidatenum, OPLcandidates_in, vote);
		assertEquals("get_cand_from_rank_IR",-1, tmp1.get_cand_from_rank(vote[0]));
		assertEquals("get_cand_from_rank_OPL",-1, tmp2.get_cand_from_rank(vote[0]));
		for(int i=1;i<IRcandidates_in.length;i++)
		{
			assertEquals("get_cand_from_rank_IRi",i, tmp1.get_cand_from_rank(vote[i]));
			assertEquals("get_cand_from_rank_OPLi",i, tmp2.get_cand_from_rank(vote[i]));
		}
	}

	@Test
	public void testGet_vote() {
		Ballot tmp1 = new Ballot(type[1], seats_num, voter_index, candidatenum, IRcandidates_in, vote);
		Ballot tmp2 = new Ballot(type[2], seats_num, voter_index, candidatenum, OPLcandidates_in, vote);
		int[] test = {-1,0,1,2,3,4,5};
		int[] result = {-1,vote[0],vote[1],vote[2],vote[3],-1};
		for(int i=1;i<IRcandidates_in.length;i++)
		{
			assertEquals("get_vote_IRi",result[i], tmp1.get_vote(test[i]));
			assertEquals("get_vote_OPLi",result[i], tmp2.get_vote(test[i]));
		}
	}

	@Test
	public void testGet_candidate() {
		Ballot tmp1 = new Ballot(type[1], seats_num, voter_index, candidatenum, IRcandidates_in, vote);
		Ballot tmp2 = new Ballot(type[2], seats_num, voter_index, candidatenum, OPLcandidates_in, vote);
		int[] test = {-1,0,1,2,3,4,5};
		String[] result = {"",IRcandidates_in[0],IRcandidates_in[1],IRcandidates_in[2],IRcandidates_in[3],""};
		for(int i=1;i<IRcandidates_in.length;i++)
		{
			assertEquals("get_candidate_IRi",result[i], tmp1.get_candidate(test[i]));
			assertEquals("get_candidate_OPLi",result[i], tmp2.get_candidate(test[i]));
		}
	}

	@Test
	public void testGet_party() {
		Ballot tmp2 = new Ballot(type[2], seats_num, voter_index, candidatenum, OPLcandidates_in, vote);
		int[] test = {-1,0,1,2,3,4,5};
		String[] result = {"",OPLcandidates_in[1],OPLcandidates_in[3],OPLcandidates_in[5],OPLcandidates_in[7],""};
		for(int i=1;i<IRcandidates_in.length;i++)
		{
			
			assertEquals("get_candidate_OPLi",result[i].substring(0, result[i].length()-1), tmp2.get_party(test[i]));
		}
	}

	@Test
	public void testGet_numofseat() {
		Ballot tmp2 = new Ballot(type[2], seats_num, voter_index, candidatenum, OPLcandidates_in, vote);
		int result = seats_num;
		assertEquals("get_candidate_OPLi",result, tmp2.get_numofseat());
	}

}
