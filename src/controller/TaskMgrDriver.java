/**
 * 
 */
package controller;
import model.TaskList;
import model.Task;
import view.CMDView;
import java.util.ListIterator;

/**
 * @author ejoverwe
 *
 */
public class TaskMgrDriver {
	private TaskList tasks;
	private CMDView ui;
	
	/**
	 * 
	 */
	private TaskMgrDriver() {
		tasks = new TaskList();
		ui = new CMDView(this);
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
	
	public ListIterator<Task> getTasks()
	{
		return tasks.getListIterator();
	}
	
	public ListIterator<Task> editTask(Task task, 
			String name1, String type1, int due, int hours, boolean comp, int diff)
	{
		tasks.editTask(task, name1, type1, due, hours, comp, diff);
		return getTasks();
	}
	
	public ListIterator<Task> addTask(String name, String type, int due, int hours, boolean comp, int diff) 
	{
		Task newTask = new Task(name, type, due, hours, comp, diff);
		tasks.insertTask(newTask);
		return getTasks();
	}
	
	public ListIterator<Task> removeTask(Task task) 
	{
		tasks.removeTask(task);
		return getTasks();
	}
}
