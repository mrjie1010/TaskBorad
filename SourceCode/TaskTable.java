package taskboard;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class TaskTable{
	
	
	 JTable table;
	 public TaskTable()
	 {
		 Object[][] data=null;
		 DefaultTableModel model= new DefaultTableModel(data,new Object[]{ "Task Name", "Description", "Type", "Time" });
		 table = new JTable(model);
	 }
	 
	 public void newPanel()
	 {
		 DefaultTableModel model =(DefaultTableModel)table.getModel();
		 model.addRow(new Object[]{"","","",""});


	 }
	 
	 public void addrow(Task x)
	 {
		 String[] tmp= new String[4];
		 tmp[0]=x.getName();
		 tmp[1]=x.getDescription();
		 tmp[2]=x.getColType();
		 tmp[3]=x.getTime();
		 DefaultTableModel model =(DefaultTableModel)table.getModel();
		 model.addRow(tmp);
		
		 
	 }
	 
	 public void removeRow(int i)
	 {
		 DefaultTableModel model =(DefaultTableModel)table.getModel();
		 model.removeRow(i);
	 }
	 
	 
	 
	 
	 
	 
	 
	 public int getColumn()
	 {
		 return table.getSelectedColumn();
	 }
	 public JPanel getPanel()
	 {
			JPanel panel= new JPanel();
			panel.add(new JScrollPane(table));
			return panel;
	 }
	 
	
}