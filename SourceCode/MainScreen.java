package taskboard;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ItemSelectable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class MainScreen extends JFrame {

	private Container c = this.getContentPane();

	private JLabel l1 = new JLabel("Select Project");
	DefaultComboBoxModel<String> model= new DefaultComboBoxModel<String>(new String[0]);
	JComboBox<String> comboBox = new JComboBox<String>(model);
	
	private JButton editbtn = new JButton("Edit");
	private JButton savebtn = new JButton("Save Board");
	private JButton deletebtn = new JButton("Delete");
	private JButton loadbtn = new JButton("Load ");
	private JButton createbtn = new JButton("Create project");
	private JButton logoutbtn = new JButton("Logout");

	private JButton addbtn = new JButton("Add Task");
	private JButton deleteTaskbtn = new JButton("RemoveTask");

	private boolean empty = true;



	JPanel tablePanel = new JPanel();
	TaskBoard board = new TaskBoard(this);
	TaskTable TaskTable = new TaskTable();

	public MainScreen() {
		this.setTitle("Main");
		LoginDialog login = new LoginDialog(this);
		login.setVisible(true);

		this.setBounds(200, 100, 1000, 600);
		c.setLayout(new GridLayout(3, 1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize();
		listener();

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initialize() {

		JPanel header = new JPanel();
		header.setLayout(null);
		l1.setBounds(50, 20, 100, 20);
		header.add(l1);

		

		comboBox.setEditable(false);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index =comboBox.getSelectedIndex();
				if(index>0&&index<board.list.size())
				board.setCurrent(comboBox.getSelectedIndex());
			}
		});

		ItemListener itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
			}
		};
		comboBox.addItemListener(itemListener);
		comboBox.setBounds(150, 20, 80, 20);
		header.add(comboBox);

		editbtn.setBounds(250, 20, 120, 20);
		header.add(editbtn);
		savebtn.setBounds(370, 20, 120, 20);
		header.add(savebtn);
		deletebtn.setBounds(490, 20, 120, 20);
		header.add(deletebtn);
		loadbtn.setBounds(610, 20, 120, 20);
		header.add(loadbtn);
		createbtn.setBounds(730, 20, 120, 20);
		header.add(createbtn);
		logoutbtn.setBounds(850, 20, 120, 20);
		header.add(logoutbtn);
		c.add(header);
		tablePanel.setBounds(150, 40, 970, 600);
		tablePanel.add(TaskTable.getPanel());
		c.add(tablePanel);
		
		JPanel footer = new JPanel();
		footer.setLayout(new FlowLayout());
		footer.add(addbtn);
		footer.add(deleteTaskbtn);
		addbtn.setVisible(true);
		deleteTaskbtn.setVisible(true);
		c.add(footer);
		repaint();
	}

	private void listener() {
		// TODO Auto-generated method stub
		createbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ProjectDialog pd = new ProjectDialog(MainScreen.this);
				pd.setVisible(true);

			}
		});
		if (empty == true) {
			addbtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

					TaskDialog td = new TaskDialog(MainScreen.this);
					td.setVisible(true);



				}

			});
			deleteTaskbtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int row = TaskTable.getColumn();
					// System.out.println(row);
					if (row == -1) {
						JOptionPane.showMessageDialog(MainScreen.this, "Choose the row that you want to delete!");
					} else {

						TaskTable.removeRow(row);
						tablePanel.removeAll();
						tablePanel.add(TaskTable.getPanel());
						repaint();
					}

				}
			});
		}

		editbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditProject ep = new EditProject(MainScreen.this);
				ep.setVisible(true);
				
					board.current = ep.getProject();


				}
			}
		);

		savebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.showDialog(new JLabel(), "Save");

				File file = chooser.getSelectedFile();
				if (file.isDirectory()) {
					System.out.println("Directory:" + file.getAbsolutePath());
				} else if (file.isFile()) {
					System.out.println("File:" + file.getAbsolutePath());
				}
				System.out.println(chooser.getSelectedFile().getName());

			}
		});

		deletebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				board.list.remove(board.current);

				JOptionPane.showMessageDialog(MainScreen.this, "Has deleted this project!");
			}
		});

		loadbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(new JLabel(), "Choose");
				File file = jfc.getSelectedFile();
				if (file.isDirectory()) {
					System.out.println("Directory:" + file.getAbsolutePath());
				} else if (file.isFile()) {
					System.out.println("File:" + file.getAbsolutePath());
				}
				System.out.println(jfc.getSelectedFile().getName());
			}
		});

		logoutbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				LoginDialog login = new LoginDialog(MainScreen.this);
				login.setVisible(true);

			}
		});
	}

	public void updateProject(Project x) {
		TaskTable = new TaskTable();
		for (Task task : x.Tasks) {
			TaskTable.addrow(task);
		}
		tablePanel.removeAll();
		tablePanel.add(TaskTable.getPanel());
		
		sop("" + TaskTable.table.getModel().getRowCount());
		repaint();
	}

	public void updateTask(Task x) {
		TaskTable.addrow(x);
		tablePanel.removeAll();
		tablePanel.add(TaskTable.getPanel());
		sop("" + TaskTable.table.getModel().getRowCount());
		repaint();
	}
	
	public void updateBox(Project[] x)
	{
		model.removeAllElements();
		for(int i=0; i<x.length;i++)
		{
		model.addElement(x[i].toString());
		}
	}

	public static void sop(String x) {
		System.out.println(x);
	}

	static private String selectedString(ItemSelectable is) {
		Object selected[] = is.getSelectedObjects();
		return ((selected.length == 0) ? "null" : (String) selected[0]);
	}

	public Project createNullProject() {
		Project p = new Project("", null, null);
		return p;

	}

	public static void main(String[] args) {

		MainScreen main = new MainScreen();

	}
}
