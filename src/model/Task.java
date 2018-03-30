package model;

import java.io.Serializable;
public class Task implements Serializable {

	int duedate = 0; //https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html
	//https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.Builder.html

	private int difficulty = 0;

	private String name = " ";

	String type = " ";

	private int hourstodo = 0;
	boolean complete = false;
	private boolean isOverride = false;
	private int newHours;
	private int newDifficulty;
	private int newDueDate;
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
		if(isOverride()){
			return newDueDate;
		}
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
		if(isOverride()){
			return newHours;
		}
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
		if(isOverride()){
			return newDifficulty;
		}
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
	
	
	public boolean isOverride(){
		return isOverride;
	}

	public void setOverride(boolean override){
		isOverride = override;
	}
	public void setHrsOverride(int newHrs){
		newHours = newHrs;
	}
	public void setDifficultyOverride(int newDiff){
		newDifficulty = newDiff;
	}
	public void setDueDateOverride(int newDue){
		newDueDate = newDue;
	}
}
