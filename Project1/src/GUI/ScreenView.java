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



public class ScreenView {

    public JFrame frame = new JFrame("Voting System");
	ScreenView() {
		frame.setSize(420, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);

		frame.setVisible(true);
	}

	private static void placeComponents(JPanel panel) {

        panel.setLayout(null);
        
        JLabel screenMessage = new JLabel("The Result", JLabel.CENTER);
        screenMessage.setSize(300,30);
        screenMessage.setLocation(10,10);
        panel.add(screenMessage);

        // panel.addSeparator();

        JTextArea jta = new JTextArea();
        jta.setSize(400,400);
        jta.setLocation(10,40);
        jta.setText("test success");
        panel.add(jta);
    }
}