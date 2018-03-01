/**
 * 
 */
package controller;
import model.TaskList;
import model.Task;
import view.View;

/**
 * @author ejoverwe
 *
 */
public class TaskMgrDriver {
	private TaskList tasks;
	private View ui;
	
	/**
	 * 
	 */
	private TaskMgrDriver() {
		tasks = new TaskList();
		ui = new View(this);
		//TODO remainder of initialization code
		ui.starter(ui);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TaskMgrDriver t = new TaskMgrDriver();
	}
	
	public Iterable<Task> getTasks()
	{
		return () -> {System.out.println("sorry, not finished yet"); return null;};
	}
	
	public Iterable<Task> editTask(Task task, 
			String name1, String type1, int due, int hours, boolean comp, int diff)
	{
		tasks.editTask(task, name1, type1, due, hours, comp, diff);
		return getTasks();
	}
}
