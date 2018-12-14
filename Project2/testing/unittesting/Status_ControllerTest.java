import static org.junit.Assert.*;

import org.junit.Test;

public class Status_ControllerTest {

	@Test
	public void testLog_in() {
		Status_Controller sc=new Status_Controller();
		assertEquals(true,sc.Log_in("3WUser", "5801"));
	}

	@Test
	public void testLoadData() {
		Status_Controller sc=new Status_Controller();
		assertEquals(true,sc.LoadData("./unittesting/test1.csv"));
	}

	@Test
	public void testProcessData() {
		Status_Controller sc=new Status_Controller();
		sc.LoadData("./unittesting/test1.csv");
		assertEquals(true,sc.ProcessData());
	}

	@Test
	public void testDisplayData() throws IOException {
		Status_Controller sc=new Status_Controller();
		sc.LoadData("./unittesting/test1.csv");
		sc.ProcessData();
		assertEquals(true,sc.DisplayData());
	}

	@Test
	public void testSaveData() throws IOException {
		Status_Controller sc=new Status_Controller();
		sc.LoadData("./unittesting/test1.csv");
		sc.ProcessData();
		assertEquals(true,sc.DisplayData());
	}

	@Test
	public void testSaveInvData() throws IOException {
		Status_Controller sc=new Status_Controller();
		sc.LoadData("./unittesting/test1.csv");
		sc.ProcessData();
		assertEquals(true,sc.DisplayData());
	}

	@Test
	public void testSign_out() throws IOException {
		Status_Controller sc=new Status_Controller();
		sc.LoadData("./unittesting/test1.csv");
		sc.ProcessData();
		assertEquals(true,sc.DisplayData());
	}

}
