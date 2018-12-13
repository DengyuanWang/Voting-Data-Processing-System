import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void testUserStringString() {
		User u = new User("3WUser","tester");
		assertEquals("3WUser",u.Account);
	}

	@Test
	public void testLoad_script() {
		User u = new User("3WUser","tester");
		assertEquals("3WUser",u.Account);
		assertEquals(true,u.load_script(""));
	}

	@Test
	public void testGet_auth_Level() {
		User u = new User("3WUser","tester");
		assertEquals("3WUser",u.Account);
		assertEquals("tester",u.get_auth_Level());
	}

}
