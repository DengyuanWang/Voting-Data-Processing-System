/** main class
 * code for main class
 *@author Yizhe Wang DengYuan Wang
 *@version V1.0
 */

import java.io.IOException;

public class Tester_main {


	public static void main(String[] args) throws IOException {
		System.out.print("hello world\n");
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		if(args.length>0)//have argument
		{
			String tmp = args[0];
			System.out.print(tmp);
			Global.SC.Data_file_path = tmp;
		}
		Login lg = new Login();
	}

}
