package view;
import controller.TaskMgrDriver;
import model.Task;

import java.util.Scanner;
public class View {

	//testingme thetest = new testingme();
	
	private TaskMgrDriver controller;
	
	public View ( TaskMgrDriver controller)
	{
		this.controller = controller;
	}
	
	//remove this method, call starter through 
	
	
//	public static void main(String[] args) {
//
//		View thetest = new View();
//		thetest.starter(thetest// , the tasklist
//		);
//
//	}
	

	public void starter( View thetest, Iterable<Task> taskList) {
		Scanner scanin = new Scanner(System.in);
		 System.out.println("Welcome to TaskManager 2.0, would you like to make a new task,"
		 		+ " or would you like to see your existing tasks?");
		 String pref = scanin.nextLine();
		 
		 if( pref.contains("Existing") || pref.contains("existing")) {
			 thetest.Exist(pref, thetest, taskList);
		 } else if ( pref.contains("New") || pref.contains("new")) {
			 thetest.NewT(pref, thetest);
		 } else {
			 System.out.println("I'm sorry that is not a valid request. This program will now exit.");
		 }
		 
		 scanin.close();
	}
	
	 protected void Exist( String preference, View thetest, Iterable<Task> taskList) {
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
			edit(editinfo, thetest, taskList);
		 } else if ( editinfo.contains("Info") || editinfo.contains("info")) {
			 info(editinfo, thetest, taskList);
		 } else {
			 System.out.println("I'm sorry that is not a valid request. We will return you "
			 		+ "to the beginning of the program.");
			 starter(thetest, taskList);
		 }
		 
		 scanin.close();
	 }
	 
	 protected void NewT ( String preference, View thetest) {
		 System.out.println("Hey new task, awesome!");
	 }
	 
	 
	 protected void edit( String edit, View thetest, Iterable<Task> taskList) {
		
		 System.out.println("Which Task would you like to edit?");
		 
		 Scanner scanin = new Scanner(System.in);
		 
		 String wtask = scanin.nextLine();
		 
		 for(int i = 0; i < 25; //the tasklist.size
				 i++) {
			
			 if( wtask.contains("w") //tasklist.getname
					 ) {
				 
				 //
				 System.out.println("What would you like to change about it? Name, Date, Time Expected");
				 
				 String ctask = scanin.nextLine();
				 
				 if( ctask.contains("name") || ctask.contains("Name")) {
					 
					 
				 } else if( ctask.contains("date") || ctask.contains("Date")) {
					 
					 
				 } else if( ctask.contains("Time expected") || ctask.contains("time expected") || ctask.contains("Time Expected")) {
					 
					 
				 } else if( ctask.contains("Difficulty") || ctask.contains("difficulty")) {
					 
					 
				 } else if( ctask.contains("Type") || ctask.contains("type")) {
					 
					 
				 } else if( ctask.contains("Complete") || ctask.contains("complete")) {
					 
					 
				 } else {
					 System.out.println("I'm sorry that is not a valid request. We will return you "
						 		+ "to the beginning of the program.");
						 starter(thetest, taskList);
				 }
				 
				 break;
			 }
			 
		 }
	 }
	 
	 protected void info( String info, View thetest, Iterable<Task> taskList) {
		 
	 }
}
