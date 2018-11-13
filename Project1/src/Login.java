//login page
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


public class Login extends javax.swing.JFrame {
	public JFrame frame = new JFrame("Voting System");

	Login(){
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 150);

		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);

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

		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(10, 80, 80, 25);
		panel.add(loginButton);
        
        // ActionListener loginButtonListener = new LoginButtonListener();
		// loginButton.addActionListener(loginButtonListener);
		loginButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				String username = userText.getText();
				String password = passwordText.getText();
				if(Global.SC.Log_in(username, password)) 
				{
					LoadFile loading = new LoadFile();
					frame.setVisible(false);
				}
				else
				{
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