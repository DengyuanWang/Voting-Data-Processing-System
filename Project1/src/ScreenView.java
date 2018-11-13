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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;



public class ScreenView extends javax.swing.JFrame {

    public JFrame frame = new JFrame("Voting System");
	ScreenView() {
		frame.setSize(430, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);

		frame.setVisible(true);
	}

	private void placeComponents(JPanel panel) {

        panel.setLayout(null);
        
        JLabel screenMessage = new JLabel("The Result", JLabel.CENTER);
        screenMessage.setSize(300,30);
        screenMessage.setLocation(10,10);
        panel.add(screenMessage);

        // panel.addSeparator();

        JTextArea jta = new JTextArea();
        jta.setSize(400,300);
        jta.setLocation(10,40);
        jta.setText("ha");
        panel.add(jta);

        JButton backButton = new JButton("Back");
        backButton.setBounds(200,355,100,25);
        panel.add(backButton);

        ActionListener backButtonListener = new backButtonListener();
        backButton.addActionListener(backButtonListener);
    }

    public class backButtonListener extends javax.swing.JFrame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FinishProcess back = new FinishProcess();
            frame.setVisible(false);
        }
    }
}