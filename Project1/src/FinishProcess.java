//login page
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

    public JFrame frame = new JFrame("Voting System");
    FinishProcess(){
		frame.setSize(600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);

		frame.setVisible(true);
    }
	// public static void main(String[] args) {
	// 	JFrame frame = new JFrame("Voting System");
	// 	frame.setSize(600, 300);
	// 	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// 	JPanel panel = new JPanel();
	// 	frame.add(panel);
	// 	placeComponents(panel);

	// 	frame.setVisible(true);
	// }

	private void placeComponents(JPanel panel) {

        panel.setLayout(null);
        
        JLabel finishMessage = new JLabel("Finished Processing", JLabel.CENTER);
        finishMessage.setSize(300,30);
        finishMessage.setLocation(10,10);
        panel.add(finishMessage);

        // panel.addSeparator();

		JButton auditButton = new JButton("Export Audit File");
		auditButton.setBounds(100, 175 , 200 , 25);
        panel.add(auditButton);

        JButton screenButton = new JButton("Overview(Screen)");
		screenButton.setBounds(300, 175 , 100 , 25);
        panel.add(screenButton);

        JButton mediaButton = new JButton("Export Media File");
		mediaButton.setBounds(400, 175 , 100 , 25);
        panel.add(mediaButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(500,175,100,25);
        panel.add(backButton);

        ActionListener auditButtonListener = new auditButtonListener();
        auditButton.addActionListener(auditButtonListener);

        ActionListener screenButtonListener = new screenButtonListener();
        screenButton.addActionListener(screenButtonListener);

        ActionListener mediaButtonListener = new mediaButtonListener();
        mediaButton.addActionListener(mediaButtonListener);

        ActionListener backButtonListener = new backButtonListener();
        backButton.addActionListener(backButtonListener);
    }
    
    public class auditButtonListener extends javax.swing.JFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg) {
            //JOptionPane.showMessageDialog(null, "success");
            // OpenFile of = new OpenFile();
            
            // try{
            //     of.PickMe();
            // }
            // catch (Exception e){
            //     e.printStackTrace();
            // }
            Global.SC.SaveData();
            // TextArea.setText
        }
    }

    public class screenButtonListener extends javax.swing.JFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ScreenView screen = new ScreenView();
            frame.setVisible(false);
        }
    }

    public class mediaButtonListener extends javax.swing.JFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //JOptionPane.showMessageDialog(null, "success");
        	Global.SC.SaveData("media.txt");
        }
    }

    public class backButtonListener extends javax.swing.JFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoadFile back = new LoadFile();
            frame.setVisible(false);
        }
    }
}