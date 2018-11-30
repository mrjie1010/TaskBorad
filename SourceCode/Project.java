package taskboard;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class Project {
	 String name;
	 List<String> TypeList;
	 List<Task> Tasks;
	public Project(String name, List<String> list2, LinkedList<Task> listTask) {
		this.name = name;
		this.TypeList = list2;
		this.Tasks = listTask;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getList() {
		return TypeList;
	}

	public void setList(List<String> list) {
		this.TypeList = list;
	}

	public List<Task> getListTask() {
		return Tasks;
	}

	public void setListTask(List<Task> listTask) {
		this.Tasks = listTask;
	}

	@Override
	public String toString() {
		return name;
	}
}
