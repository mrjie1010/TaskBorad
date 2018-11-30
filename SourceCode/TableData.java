package taskboard;

public class TableData {
	public String[][] data;

	public TableData(String[][] data) {
		this.data = data;
	}

	public String[][] getData() {
		return data;
	}

	public void setData(String[][] data) {
		this.data = data;
	}
	public void Output(){
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.print(data[i][j]+" ");
			}
			System.out.println();
		}
	}
}
