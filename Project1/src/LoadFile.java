//load page
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class LoadFile extends javax.swing.JFrame {
    public JFrame frame = new JFrame("Voting System");
    // LoadFile lf = new LoadFile();

    LoadFile(){
        
		frame.setSize(600, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);

		frame.setVisible(true);
    }

	private void placeComponents(JPanel panel) {

        panel.setLayout(null);
        
        JLabel welcomeMessage = new JLabel("Welcome to 3W Voting System", JLabel.CENTER);
        welcomeMessage.setSize(300,30);
        welcomeMessage.setLocation(10,10);
        panel.add(welcomeMessage);

        // panel.addSeparator();

		JButton openfileButton = new JButton("Open an input file");
		openfileButton.setBounds(100, 175 , 200 , 25);
        panel.add(openfileButton);

        JButton startButton = new JButton("Start");
		startButton.setBounds(300, 175 , 100 , 25);
        panel.add(startButton);

        JButton signoutButton = new JButton("Sign out");
		signoutButton.setBounds(400, 175 , 100 , 25);
        panel.add(signoutButton);

        ActionListener openfileButtonListener = new openfileButtonListener();
        openfileButton.addActionListener(openfileButtonListener);

        ActionListener startButtonListener = new startButtonListener();
        startButton.addActionListener(startButtonListener);

        ActionListener signoutButtonListener = new signoutButtonListener();
        signoutButton.addActionListener(signoutButtonListener);
    }
    
    public class openfileButtonListener extends javax.swing.JFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg) {
            // JOptionPane.showMessageDialog(null, "success");
            OpenFile of = new OpenFile();
            
            try{
                of.PickMe();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public class startButtonListener extends javax.swing.JFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FinishProcess finish = new FinishProcess();
            frame.setVisible(false);
        }
    }

    public class signoutButtonListener extends javax.swing.JFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SignOut out = new SignOut(frame);
            // frame.setVisible(false);
        }
    }
}