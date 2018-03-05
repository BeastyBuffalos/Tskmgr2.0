package view;
import controller.TaskMgrDriver;
import model.Task;

import java.util.Scanner;
public class View {

	//testingme thetest = new testingme();
	private TaskMgrDriver controller;
	private Iterable<Task> tasks;
	
	public View ( TaskMgrDriver controller)
	{
		this.controller = controller;
		tasks = controller.getTasks();
	}
	
	//remove this method, call starter through 
	
	
//	public static void main(String[] args) {
//
//		View thetest = new View();
//		thetest.starter(thetest// , the tasklist
//		);
//
//	}
	

	public void starter( View thetest) {
		Scanner scanin = new Scanner(System.in);
		 System.out.println("Welcome to TaskManager 2.0, would you like to make a new task,"
		 		+ " or would you like to see your existing tasks?");
		 String pref = scanin.nextLine();
		 
		 if( pref.contains("Existing") || pref.contains("existing")) {
			 thetest.Exist(pref, thetest);
		 } else if ( pref.contains("New") || pref.contains("new")) {
			 thetest.NewT(pref, thetest);
		 } else {
			 System.out.println("I'm sorry that is not a valid request. This program will now exit.");
		 }
		 
		 scanin.close();
	}
	
	 protected void Exist( String preference, View thetest) {
		 //System.out.println("Good job, existing stuff");
		 Scanner scanin = new Scanner(System.in);
		 System.out.println("Here are the current existing tasks:");
		 //print out the current tasks
		 for( int i = 0; i < 5; i++ ) {
			 System.out.println("Task " + (i+1));
		 }
		 
		 System.out.println("Would you like to edit a certain task? Or see "
		 		+ "more information about a task?");
		 
		 String editinfo = scanin.nextLine();
		 
		 if( editinfo.contains("Edit") || editinfo.contains("edit")) {
			edit(editinfo, thetest);
		 } else if ( editinfo.contains("Info") || editinfo.contains("info")) {
			 info(editinfo, thetest);
		 } else {
			 System.out.println("I'm sorry that is not a valid request. We will return you "
			 		+ "to the beginning of the program.");
			 starter(thetest);
		 }
		 
		 scanin.close();
	 }
	 
	 protected void NewT ( String preference, View thetest) {
		 System.out.println("Hey new task, awesome!");
		 
		 Scanner scanin = new Scanner(System.in);
		 
		 System.out.println("What will the task's name be?");
		 String taskName = scanin.nextLine();
		 
		 System.out.println("What type of task is is?");
		 String taskType = scanin.nextLine();
		 
		 System.out.println("What is the due date?");
		 String d = scanin.nextLine();
		 int taskDue = Integer.parseInt(d);
		 
		 System.out.println("How many hours do you expect the task to take?");
		 String h = scanin.nextLine();
		 int taskHours = Integer.parseInt(h);
		 
		 System.out.println("What is the difficulty on a scale of 1 to 10, 10 being most difficult?");
		 String di = scanin.nextLine();
		 int taskDiff = Integer.parseInt(di);
		 
		 boolean comp = false;
		 
		 
	 }
	 
	 
	 protected void edit( String edit, View thetest) {
		
		 System.out.println("Which Task would you like to edit?");
		 
		 Scanner scanin = new Scanner(System.in);
		 
		 String wtask = scanin.nextLine();
		 
		 for(int i = 0; i < 25; //the tasklist.size
				 i++) {
			
			 if( wtask.contains("w") //tasklist.getname
					 ) {
				 Task task = new Task(wtask, wtask, i, i, false, i);
				 String name = task.getName();
				 String type = task.getType();
				 int due = task.getDue();
				 int hours = task.getHours();
				 boolean comp = task.getComplete();
				 int diff = task.getDifficulty();
				 
				 System.out.println("What would you like to change about it? Name, Date, Time Expected");
				 
				 String ctask = scanin.nextLine();
				 
				 if( ctask.contains("name") || ctask.contains("Name")) {
					 System.out.println("What will the new name be?");
					 String newName = scanin.nextLine();
					 controller.editTask(task, newName, type, due, hours, comp, diff);
					 System.out.println("Name updated");
					 
				 } else if( ctask.contains("date") || ctask.contains("Date")) {
					 System.out.println("What will the new date be?");
					 String date = scanin.nextLine();
					 int newDate = Integer.parseInt(date);
					 controller.editTask(task, name, type, newDate, hours, comp, diff);
					 System.out.println("Date updated");
					 
				 } else if( ctask.contains("Time expected") || ctask.contains("time expected") || ctask.contains("Time Expected")) {
					 System.out.println("What is the new time expected?");
					 String time = scanin.nextLine();
					 int newTime = Integer.parseInt(time);
					 controller.editTask(task, name, type, due, newTime, comp, diff);
					 System.out.println("Expected time updated");
					 
				 } else if( ctask.contains("Difficulty") || ctask.contains("difficulty")) {
					 System.out.println("What is the new difficulty?");
					 String difficulty = scanin.nextLine();
					 int newDifficulty = Integer.parseInt(difficulty);
					 controller.editTask(task, name, type, due, hours, comp, newDifficulty);
					 System.out.println("Difficulty updated");
					 
				 } else if( ctask.contains("Type") || ctask.contains("type")) {
					 System.out.println("What is the new task type?");
					 String newType = scanin.nextLine();
					 controller.editTask(task, name, newType, due, hours, comp, diff);
					 System.out.println("Type updated");
					 
				 } else if( ctask.contains("Complete") || ctask.contains("complete")) {
					 System.out.println("Is the task completed?");
					 String completed = scanin.nextLine();
					 if( completed.contains("Yes") || completed.contains("yes") ) {
						 boolean complete = true;
						 controller.editTask(task, name, type, due, hours, complete, diff);
						 System.out.println("Task marked as completed");
					 } else {
						 boolean complete = false;
						 controller.editTask(task, name, type, due, hours, complete, diff);
						 System.out.println("Task marked as not completed");
					 }
					 
				 } else {
					 System.out.println("I'm sorry that is not a valid request. We will return you "
						 		+ "to the beginning of the program.");
						 starter(thetest);
				 }
				 
				 break;
			 }
			 
		 }
	 }
	 
	 protected void info( String info, View thetest) {
		 
	 }
}
