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


public class SignOut extends javax.swing.JFrame {
    public JFrame frame = new JFrame("Voting System");
    public static JFrame O_frame;
    SignOut(JFrame outer_frame){
        O_frame = outer_frame;
        frame.setSize(300, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);

		frame.setVisible(true);
    }
	private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        
        JLabel leaveMessage = new JLabel("Are you sure want to sign out?", JLabel.CENTER);
        leaveMessage.setSize(200,30);
        leaveMessage.setLocation(10,10);
        panel.add(leaveMessage);

		JButton signoutButton = new JButton("Sign Out");
		signoutButton.setBounds(10, 80, 80, 25);
		panel.add(signoutButton);
        //sign out will be linked to the Login page
        ActionListener signoutButtonListener = new SignoutButtonListener();
        signoutButton.addActionListener(signoutButtonListener);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(100, 80, 80, 25);
        panel.add(cancelButton);

        ActionListener cancelButtonListener = new CancelButtonListener();
        cancelButton.addActionListener(cancelButtonListener);
    }
    
    public class SignoutButtonListener extends javax.swing.JFrame implements ActionListener  {
        @Override
        public void actionPerformed(ActionEvent e) {
			Login signout = new Login();
            frame.setVisible(false);
            O_frame.setVisible(false);
        }
    }

    public class CancelButtonListener extends javax.swing.JFrame implements ActionListener  {
        @Override
        public void actionPerformed(ActionEvent e) {
			// JOptionPane.showMessageDialog(null, "success");
			
			frame.setVisible(false);
        }
    }


}