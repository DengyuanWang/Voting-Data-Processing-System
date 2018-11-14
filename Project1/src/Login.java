//login page
//Author Yizhe Wang DengYuan Wang
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//class should extends the JFrame to normally perform the transaction
public class Login extends javax.swing.JFrame {
	//the frame name is called Voting System
	public JFrame frame = new JFrame("Voting System");
	//constructor for Login function
	Login(){
		//setting up the frame size and default close operation
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 150);
		//create a panel on the frame to act like container
		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);
		//set visible to show the frame
		frame.setVisible(true);
		frame.setSize(300, 150);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel2 = new JPanel();
		frame.add(panel2);
		placeComponents(panel2);
		frame.setVisible(true);
		
	}

	private void placeComponents(JPanel panel) {

		panel.setLayout(null);
		//user label
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);
		//user input
		JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);
		//password label
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);
		//password input
		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);
		//login button 
		JButton loginButton = new JButton("login");
		loginButton.setBounds(10, 80, 80, 25);
		panel.add(loginButton);
        
        
		loginButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				String username = userText.getText();
				String password = passwordText.getText();
				//match the username and password with the Log_in function
				if(Global.SC.Log_in(username, password)) 
				{
					//moving to the LoadFile interface
					LoadFile loading = new LoadFile();
					frame.setVisible(false);
				}
				else
				{
					//warming for unmatched username and password
					JOptionPane.showMessageDialog(frame, "Invalid username or password");
				}
			}
		}
		);	

    }
    
    // public class LoginButtonListener extends javax.swing.JFrame implements ActionListener  {
    //     @Override
    //     public void actionPerformed(ActionEvent e) {
	// 		// JOptionPane.showMessageDialog(null, "success");
		// 	String username = userText.getText();
		// 	String password = passwordText.getText();

        // 	if(username.equals("username") && password.equals("password")) 
        // 	{
        // 		LoadFile loading = new LoadFile();
    	// 		frame.setVisible(false);
        // 	}
        // }
    // }
}