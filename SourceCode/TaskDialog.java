package taskboard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class TaskDialog extends JDialog {
	private JLabel name = new JLabel("Name");
	private JLabel description= new JLabel("Description");
	private JLabel status= new JLabel("Colum Type");
	private JLabel time= new JLabel("Due Date");
	
	private JButton createbtn = new JButton("Create");
	private JButton cancelbtn = new JButton("Cancel");
	
	private JTextField nametf = new JTextField();
	private JTextArea descriptionta = new JTextArea();
	private JTextField timetf = new JTextField();


	private JComboBox combox = new JComboBox();
	
 boolean flag = false;
	private Task task;
	MainScreen parent;

	public TaskDialog(MainScreen mainScreen) {
		this.parent=mainScreen;
		this.setTitle("Create a new Task");
		JPanel panel = new JPanel();
		panel.setLayout(null);
		name.setBounds(20, 20, 100, 20);
		panel.add(name);
		description.setBounds(20, 70, 100, 20);
		panel.add(description);
		status.setBounds(20, 150, 100, 20);
		panel.add(status);
		time.setBounds(20, 200, 100, 20);
		panel.add(time);
		
		createbtn.setBounds(20, 250, 100, 20);
		panel.add(createbtn);
		cancelbtn.setBounds(140, 250, 100, 20);
		panel.add(cancelbtn);
		
		nametf.setBounds(150, 20,150,20);
		panel.add(nametf);
		descriptionta.setBounds(150, 70, 200, 50);
		descriptionta.setLineWrap(true); 
		panel.add(descriptionta);

		
		combox = new JComboBox(mainScreen.board.current.TypeList.toArray(new String[0]));

		combox.setEditable(true);

		combox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		ItemListener itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				int state = itemEvent.getStateChange();
				ItemSelectable is = itemEvent.getItemSelectable();

			}
		};
		combox.addItemListener(itemListener);
		combox.setBounds(150, 150, 150, 20);
		panel.add(combox);
		
		timetf.setBounds(150, 200, 150, 20);
		panel.add(timetf);
		
		panel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

		createbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				mainScreen.board.addCurrentTask(new Task(nametf.getText(), descriptionta.getText(), combox.getSelectedItem().toString(), timetf.getText()));
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
		setLayout(new BorderLayout());
		getContentPane().add(panel);
		setTitle("Create New Task");
		setLocationRelativeTo(panel);
		setPreferredSize(new Dimension(400, 400));
		pack();
	}
	static private String selectedString(ItemSelectable is) {
		Object selected[] = is.getSelectedObjects();
		return ((selected.length == 0) ? "null" : (String) selected[0]);
	}

	public Task getTask(){
		return task;
	}


}
