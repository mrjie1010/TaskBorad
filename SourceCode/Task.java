package taskboard;

public class Task {
	private String name;
	private String description;
	private String status;
	private String time;

	public Task(String name, String description, String status, String time) {
		this.name = name;
		this.description = description;
		this.status = status;
		this.time = time;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColType() {
		return status;
	}

	public void setColType(String status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Task [name=" + name + ", description=" + description + ", status=" + status + ", time=" + time + "]";
	}

}
