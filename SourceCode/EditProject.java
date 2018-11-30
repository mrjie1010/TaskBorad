package taskboard;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class EditProject extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Container c = this.getContentPane();
	private JLabel jl1 = new JLabel("Name");
	private JTextField name = new JTextField(20);

	private JList<String> booklist;
	private DefaultListModel<String> bookModel = new DefaultListModel<String>();
	private JTextField bookName = new JTextField(10);
	private JButton addbtn = new JButton("+");
	private JButton deletebtn = new JButton("-");

	private JButton createbtn = new JButton("Edit");
	private JButton cancelbtn = new JButton("Cancel");

	private boolean iscreated = false;
	private Project project;
	private MainScreen viewer;

	public EditProject(MainScreen x) {
		this.setTitle("Edit Project");
		
		viewer=x;
		this.project = x.board.current;
		this.setBounds(400, 200, 600, 400);

		c.setLayout(new BorderLayout());

		init();
		set(project);
	}

	public void init() {
		JPanel inputPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel buttonPanel1 = new JPanel();
		inputPanel.setLayout(new FlowLayout());
		inputPanel.add(jl1);
		inputPanel.add(name);
		c.add(inputPanel, "North");
		
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
				for (Object aaa : arr) {
					String ttt = (String) aaa;
					list.add(ttt);
				}
				String n = name.getText();
				project.setName(n);
				project.setList(list);
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
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(bookName);
		buttonPanel.add(new JScrollPane(booklist));

		buttonPanel.add(addbtn);
		buttonPanel.add(deletebtn);
		
		c.add(buttonPanel,"Center");
		
		buttonPanel1.setLayout(new FlowLayout());
		buttonPanel1.add(createbtn);
		buttonPanel1.add(cancelbtn);
		c.add(buttonPanel1,"South");
	}
	
	public void set(Project p){
		name.setText(p.getName());
		for (int i = 0; i < p.getList().size(); i++) {
			bookModel.addElement(p.getList().get(i));
		}
		
	}
	
	public Project getProject() {
		return project;
	}

	public boolean isCreated() {
		// TODO Auto-generated method stub
		return iscreated;
	}

}
