import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class Data_IOTest {

	@Test
	public void testData_IO() {
		/*
	    Path_in = "";//input file path
		Path_out = "";//output file path
		Error_tag = false;//true for any error occur
		ballot_num = 0;//number of ballot
		data = null;*/
		Data_IO Data_IO_ = new Data_IO();
		Ballot[] rst=null;
		assertEquals("", Data_IO_.Path_in);
		assertEquals("", Data_IO_.Path_out);
		assertEquals(false, Data_IO_.Error_tag);
		assertEquals(0, Data_IO_.ballot_num);
		assertEquals(rst, Data_IO_.data);
	}

	@Test
	public void testLoad_data() throws IOException {
		Data_IO Data_IO_ = new Data_IO();
		assertEquals(false, Data_IO_.load_data());
	}

	@Test
	public void testSave_resultString() {
		Data_IO Data_IO_ = new Data_IO();
		Data_IO_.Path_out="./";
		assertEquals(true,Data_IO_.save_result("Saver test"));
	}

	@Test
	public void testSave_resultStringString() {
		Data_IO Data_IO_ = new Data_IO();
		assertEquals(true,Data_IO_.save_result("Saver test", "./" + "audit.txt"));
	}

	@Test
	public void testSpecify_path_in() {
		String[] tests = {"1.csv","1"};
		boolean[] result = {true,false};
		Data_IO Data_IO_ = new Data_IO();
		for(int i=0;i<tests.length;i++)
		{
			assertEquals(result[i],	Data_IO_.specify_path_in(tests[i]));
		}
			
	}

	@Test
	public void testSpecify_path_out() {
		String[] tests = {"1.csv","1"};
		boolean[] result = {true,false};
		Data_IO Data_IO_ = new Data_IO();
		for(int i=0;i<tests.length;i++)
		{
			assertEquals(result[i],	Data_IO_.specify_path_in(tests[i]));
		}
	}

}
