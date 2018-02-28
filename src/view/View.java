package view;
import java.util.Scanner;
public class View {

	//testingme thetest = new testingme();
	
	public View ()
	{
		
	}
	
	 public static void main( String [ ] args ) {
		
		 View thetest = new View();
		 thetest.starter(thetest//, the tasklist
				 );
		 
	 }
	
	protected void starter( View thetest//, the tasklist
			) {
		Scanner scanin = new Scanner(System.in);
		 System.out.println("Welcome to TaskManager 2.0, would you like to make a new task,"
		 		+ " or would you like to see your existing tasks?");
		 String pref = scanin.nextLine();
		 
		 if( pref.contains("Existing") || pref.contains("existing")) {
			 thetest.Exist(pref, thetest//, the tasklist
					 );
		 } else if ( pref.contains("New") || pref.contains("new")) {
			 thetest.NewT(pref, thetest);
		 } else {
			 System.out.println("I'm sorry that is not a valid request. This program will now exit.");
		 }
		 
		 scanin.close();
	}
	
	 protected void Exist( String preference, View thetest//, the tasklist
			 ) {
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
			edit(editinfo, thetest//, the tasklist
					);
		 } else if ( editinfo.contains("Info") || editinfo.contains("info")) {
			 info(editinfo, thetest//, the tasklist
					 );
		 } else {
			 System.out.println("I'm sorry that is not a valid request. We will return you "
			 		+ "to the beginning of the program.");
			 starter(thetest);
		 }
		 
		 scanin.close();
	 }
	 
	 protected void NewT ( String preference, View thetest) {
		 System.out.println("Hey new task, awesome!");
	 }
	 
	 
	 protected void edit( String edit, View thetest//, the tasklist
			 ) {
		
		 System.out.println("Which Task would you like to edit?");
		 
		 Scanner scanin = new Scanner(System.in);
		 
		 String wtask = scanin.nextLine();
		 
		 for(int i = 0; i < 25; //the tasklist.size
				 i++) {
			 
		 }
	 }
	 
	 protected void info( String info, View thetest) {
		 
	 }
}
