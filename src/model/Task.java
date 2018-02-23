package model;
public class Task {

	int duedate = 0; //https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html
					//https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.Builder.html
	
	int difficulty = 0;

	String name = " ";

	String type = " ";

	int hourstodo = 0;

	boolean complete = false;

	public Task(String name1, String type1, int due, int hours, boolean comp, int diff) {
		
		name = name1;
		type = type1;
		duedate = due;
		hourstodo = hours;
		complete = comp;
		difficulty = diff;

	}

	public void setDue(int due) {

		duedate = due;

	}

	public int getDue() {

		return duedate;

	}

	public void setName(String name1) {

		name = name1;

	}

	public String getName() {

		return name;

	}

	public void setHours(int hours) {

		hourstodo = hours;

	}

	public int getHours() {

		return hourstodo;

	}

	public void setComplete(boolean isComp) {

		complete = isComp;

	}

	public boolean getComplete() {

		return complete;

	}

	public void setDifficulty(int diff) {

		difficulty = diff;

	}
	
	public int getDifficulty() {

		return difficulty;

	}
	
	public void setType(String typeis) {

		type = typeis;

	}

	public void setTypeHW(String typeis) {

		type = "Homework";

	}

	public void setTypeTSK(String typeis) {

		type = "Task";

	}

	public void setTypeMeeting(String typeis) {

		type = "Meeting";

	}

	public String getType() {

		return type;

	}

}
