//login page
//Author Yizhe Wang DengYuan Wang
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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;



public class ScreenView extends javax.swing.JFrame {
    //create frame
    public JFrame frame = new JFrame("Voting System");
    //constructor for ScreenView class
	ScreenView() {
        frame.setSize(800, 800);//increase the size of the frame in order to make space for table
        //setting default close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //craete panel
		JPanel panel = new JPanel();
        frame.add(panel);
        //call function placeComponent to load the component
		placeComponents(panel);
        //set the frame visible
		frame.setVisible(true);
	}

	private void placeComponents(JPanel panel) {

        panel.setLayout(null);
        //show the result
        JLabel screenMessage = new JLabel("The Result", JLabel.CENTER);
        //set up the screenMessage size and location
        screenMessage.setSize(300,30);
        screenMessage.setLocation(10,10);
        //add the message
        panel.add(screenMessage);

        //create textArea called jta
        JTextArea jta = new JTextArea();
        //set the size and location
        jta.setSize(400,300);
        jta.setLocation(10,40);
        Global.SC.DisplayData();
        String text = Global.SC.Audit.audit_txt;
        //display the text
        jta.setText(text);
        //add the jta to panel
        panel.add(jta);
        /*
        This place is for the table
        I need to figure out how to implement tale in java since I only have experience to implement table in html
        I need to figure out how to insert data to specific columns and rows
        */
        JButton backButton = new JButton("Back");
        backButton.setBounds(500,500,100,25);
        panel.add(backButton);

        ActionListener backButtonListener = new backButtonListener();
        backButton.addActionListener(backButtonListener);
        //add the backbutton
        // JButton backButton = new JButton("Back");
        // //set the size and position
        // backButton.setBounds(500,500,100,25);//move button for table
        // //add button
        // panel.add(backButton);
        // //add listener to backButton to perform the back action
        // ActionListener backButtonListener = new backButtonListener();
        // backButton.addActionListener(backButtonListener);
    }

    public class backButtonListener extends javax.swing.JFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //move tothe FinishProcess interface
            FinishProcess back = new FinishProcess();
            frame.setVisible(false);
        }
    }
}
