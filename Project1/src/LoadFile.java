//load page
//Author Yizhe Wang DengYuan Wang
import java.awt.event.ActionListener;
import java.io.IOException;
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


//class should extends the JFrame to normally perform the transaction
public class LoadFile extends javax.swing.JFrame {
    //the frame name is called Voting System
    public JFrame frame = new JFrame("Voting System");
    // LoadFile lf = new LoadFile();
    //constructor for LoadFile function
    LoadFile(){
        //setting up the frame size and default close operation
		frame.setSize(600, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //create a panel on the frame to act like container
		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);
        //set visible to show the frame
		frame.setVisible(true);
    }

	private void placeComponents(JPanel panel) {

        panel.setLayout(null);
        //this is the welcome message showed as label
        JLabel welcomeMessage = new JLabel("Welcome to 3W Voting System", JLabel.CENTER);
        welcomeMessage.setSize(300,30);
        welcomeMessage.setLocation(10,10);
        panel.add(welcomeMessage);
        //open file button
        JButton openfileButton = new JButton("Open an input file");
        //set the size and position of the button
        openfileButton.setBounds(100, 175 , 200 , 25);
        //add the button to panel
        panel.add(openfileButton);
        //start process button
        JButton startButton = new JButton("Start");
        //set the size and position of the button
        startButton.setBounds(300, 175 , 100 , 25);
        //add the button to panel
        panel.add(startButton);
        //add the sign out button
        JButton signoutButton = new JButton("Sign out");
        //set the position and size of button
        signoutButton.setBounds(400, 175 , 100 , 25);
        //add the sign out button
        panel.add(signoutButton);
        //add actionlistener to openfileButton
        ActionListener openfileButtonListener = new openfileButtonListener();
        openfileButton.addActionListener(openfileButtonListener);
        //add actionlistener to startButton
        ActionListener startButtonListener = new startButtonListener();
        startButton.addActionListener(startButtonListener);
        //add actionlistener to signoutButton
        ActionListener signoutButtonListener = new signoutButtonListener();
        signoutButton.addActionListener(signoutButtonListener);
    }
    
    public class openfileButtonListener extends javax.swing.JFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg) {
            //using OpenFile() funciton create instance of
            OpenFile of = new OpenFile();
            //try and catch
            try{
                //call funciton
                of.PickMe();
                //String path = "./testOPL.csv";
                String path = of.s;
                //load the path data
                Global.SC.LoadData(path);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public class startButtonListener extends javax.swing.JFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //if process data then move to FinishProcess interface
        	try {
				if(Global.SC.ProcessData())
				{
					FinishProcess finish = new FinishProcess();
				    frame.setVisible(false);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}   
        }
    }

    public class signoutButtonListener extends javax.swing.JFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //move to SingOut interface
            SignOut out = new SignOut(frame);
            // frame.setVisible(false);
        }
    }
}