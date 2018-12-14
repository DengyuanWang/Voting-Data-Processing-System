import static org.junit.Assert.*;

import org.junit.Test;

public class VotingSystemTest {

	@Test
	public void testprocess_voting_IR() throws IOException {
		Data_IO d=new Data_IO();
		d.Path_in = "./unittesting/test1.csv";
		d.load_data();
		Voting_System Voting_sys = new Voting_System();
		assertEquals(true,Voting_sys.process_voting(d));
	}

	@Test
	public void testprocess_voting_OPL()) throws IOException {
		Data_IO d=new Data_IO();
		d.Path_in = "./unittesting/test2.csv";
		d.load_data();
		Voting_System Voting_sys = new Voting_System();
		assertEquals(true,Voting_sys.process_voting(d));
	}

}
