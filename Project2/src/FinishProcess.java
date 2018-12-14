/** Finish Process
 * code for Finish Process GUI
 *@author Yizhe Wang DengYuan Wang
 *@version V1.0
 */

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



public class FinishProcess extends javax.swing.JFrame{
    //the frame name is called Voting System
    public JFrame frame = new JFrame("Voting System");
    //constructor for FinishProcess class
    FinishProcess(){
        //set size
		frame.setSize(800, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set panel
		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);
        //set visible
		frame.setVisible(true);
    }

	private void placeComponents(JPanel panel) {

        panel.setLayout(null);
        
        JLabel finishMessage = new JLabel("Finished Processing", JLabel.CENTER);
        finishMessage.setSize(300,30);
        finishMessage.setLocation(10,10);
        panel.add(finishMessage);

        // panel.addSeparator();

		JButton auditButton = new JButton("Export Audit File");
		auditButton.setBounds(50, 175 , 200 , 25);
        panel.add(auditButton);

        JButton screenButton = new JButton("Overview(Screen)");
		screenButton.setBounds(260, 175 , 200 , 25);
        panel.add(screenButton);

        JButton mediaButton = new JButton("Export Media File");
		mediaButton.setBounds(470, 175 , 200 , 25);
        panel.add(mediaButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(680,175,100,25);
        panel.add(backButton);
        //add listener to button to perform output audit
        ActionListener auditButtonListener = new auditButtonListener();
        auditButton.addActionListener(auditButtonListener);
        //add listener to button to perform output screen message
        ActionListener screenButtonListener = new screenButtonListener();
        screenButton.addActionListener(screenButtonListener);
        //add listener to button to perform output media
        ActionListener mediaButtonListener = new mediaButtonListener();
        mediaButton.addActionListener(mediaButtonListener);
        //add listener to button to perform back to previous stage
        ActionListener backButtonListener = new backButtonListener();
        backButton.addActionListener(backButtonListener);
    }
    
    public class auditButtonListener extends javax.swing.JFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg) {
            //save data
            Global.SC.SaveData();
            Global.SC.SaveInvData();
        }
    }

    public class screenButtonListener extends javax.swing.JFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //move to screen interface
            ScreenView screen = new ScreenView();
            frame.setVisible(false);
        }
    }

    public class mediaButtonListener extends javax.swing.JFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           //save data to media.txt in the current directory
        	Global.SC.SaveData("media.txt");
        }
    }

    public class backButtonListener extends javax.swing.JFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //move to the LoadFile interface
            LoadFile back = new LoadFile();
            frame.setVisible(false);
        }
    }
}