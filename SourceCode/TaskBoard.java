package taskboard;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class TaskBoard {
 static final String username="admin";
 static final String passward="passward";
 List<Project> list;
 Project current=new Project("",new LinkedList<String>(),new LinkedList<Task>());
 MainScreen viewer;


public TaskBoard(MainScreen viewer)
{
	this.viewer=viewer;
	list=new LinkedList<Project>();
}

public void addProj(Project x)
{
	
	list.add(x);
	current=x;
	System.out.println(x.toString());
	viewer.updateBox(list.toArray(new Project[0]));
	viewer.updateProject(x);
}

public void addCurrentTask(Task x)
{
	current.Tasks.add(x);
	System.out.println(x.toString());
	viewer.updateTask(x);
}

public void setCurrent(int x)
{
	if(x<0&&x>list.size()) return;
	current = list.get(x);
	viewer.updateProject(current);
}



public String toString()
{
	return list.toString();
}
}
