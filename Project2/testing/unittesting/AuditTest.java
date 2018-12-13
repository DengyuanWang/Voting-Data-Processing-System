import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class AuditTest {


	@Test
	public void testWriteInvBallots() throws IOException {
		Data_IO d=new Data_IO();
		d.Path_in = "./unittesting/test1.csv";
		d.load_data();
		Audit y = new Audit(d);
		assertEquals(true,y.WriteInvBallots());
	}

	@Test
	public void testDisplay() throws IOException {
		Data_IO d=new Data_IO();
		d.Path_in = "./unittesting/test2.csv";
		d.load_data();
		Voting_System Voting_sys = new Voting_System();
	    Voting_sys.process_voting(d);
		Audit y=Voting_sys.auditfile;
		assertEquals(false,y.display());
		
	}

}
