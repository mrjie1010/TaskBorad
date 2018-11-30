package taskboard;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginDialog extends JDialog {
	
	private Container c = this.getContentPane();
	private JLabel a1 = new JLabel("Username");
	private JTextField username = new JTextField();
	private JLabel a2 = new JLabel("Password");
	private JPasswordField password = new JPasswordField();
	private JButton okbtn = new JButton("Login");
	private JButton cancelbtn = new JButton("Cancel");
	private MainScreen parent;

	public LoginDialog(MainScreen main) {
		super(main);
		parent=main;
		this.setTitle("Login");
		this.setBounds(600, 200, 300, 220);

		c.setLayout(new BorderLayout());

		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(new JLabel("Task Board Login"));
		c.add(titlePanel, "North");

		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		a1.setBounds(50, 20, 100, 20);
		a2.setBounds(50, 60, 100, 20);
		fieldPanel.add(a1);
		fieldPanel.add(a2);
		username.setBounds(130, 20, 120, 20);
		password.setBounds(130, 60, 120, 20);
		fieldPanel.add(username);
		fieldPanel.add(password);
		c.add(fieldPanel, "Center");

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(okbtn);
		buttonPanel.add(cancelbtn);
		c.add(buttonPanel, "South");
		listener();

		this.setVisible(true);
	}


	public void listener() {

		okbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = username.getText();
				String pw = String.valueOf(password.getPassword());
				if (name.equals(parent.board.username) && pw.equals(parent.board.passward)) {
					dispose();
					parent.setVisible(true);
				} else {
					Object[] options = { "OK" };

					JOptionPane.showOptionDialog(null, "Login Incorrect, please try again ", "Message",
							JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				}
			}
		});

		cancelbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
			}
		});
	}

	
}
