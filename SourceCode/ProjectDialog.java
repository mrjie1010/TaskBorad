package taskboard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class ProjectDialog extends JDialog {
	private JLabel jl1 = new JLabel("Colums                                                           Task Name");
	private JTextField name = new JTextField();

	private JList<String> booklist;
	private DefaultListModel<String> bookModel = new DefaultListModel<String>();
	private JTextField bookName = new JTextField(20);
	private JButton addbtn = new JButton("add col");
	private JButton deletebtn = new JButton("Delete col");

	private JButton createbtn = new JButton("Create");
	private JButton cancelbtn = new JButton("Cancel");


	private boolean iscreated = false;
	MainScreen parent;

	public ProjectDialog(MainScreen mainScreen) {
		this.parent=mainScreen;
		JPanel inputPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		inputPanel.setLayout(new GridLayout(1, 1));
		inputPanel.add(jl1);
		inputPanel.add(name);

		inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

		booklist = new JList<>(bookModel);
		booklist.setVisibleRowCount(4);
		booklist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		addbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!bookName.getText().trim().equals("")) {
					bookModel.addElement(bookName.getText());
				}
				bookName.setText("");
			}
		});
		deletebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (booklist.getSelectedIndex() >= 0) {
					bookModel.removeElementAt(booklist.getSelectedIndex());
				}
			}
		});

		createbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				iscreated = true;
				List<String> list = new ArrayList<>();

				Object[] arr = bookModel.toArray();
				Arrays.sort(arr);
				for (Object item : arr) {
					
					list.add((String)item);
				}
				String n = name.getText();
				Project tmp =new Project(n,list,new LinkedList<Task>());
				mainScreen.board.addProj(tmp); 
				mainScreen.repaint();
				setVisible(false);
			}
		});
		cancelbtn = new JButton("Cancel");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		buttonPanel.add(bookName);
		buttonPanel.add(addbtn);
		buttonPanel.add(deletebtn);
		buttonPanel.add(createbtn);
		buttonPanel.add(cancelbtn);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

		setLayout(new BorderLayout());
		getContentPane().add(inputPanel, BorderLayout.NORTH);
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		add(new JScrollPane(booklist));
		setTitle("Create New Project");
		setLocationRelativeTo(inputPanel);
		setPreferredSize(new Dimension(600, 400));
		pack();
	}


	public boolean isCreated() {
		return iscreated;
	}

}
