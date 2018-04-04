/**
 * 
 */
package controller;
import model.TaskList;
import model.Task;
import view.GraphicalView;
import java.util.ListIterator;
import java.io.File;
import java.io.IOException;

/**
 * @author ejoverwe
 *
 */
public class TaskMgrDriver {
	private TaskList tasks = new TaskList();
	private GraphicalView ui;
	
	private final String taskpath = "\\Tskmgr2.0\\tasks";
	private final String macpath = "/Library/Application Support";
	private final String nuxpath = "~/.";
	private String filepath;
	
	{ //filepath initalization
		String os = System.getProperty("os.name").toLowerCase();
		//determine location of tasklist based on underlying OS
		filepath = (os.contains("win")) ? System.getenv("APPDATA") : 
			(os.contains("mac") || os.contains("darwin")) ? macpath : nuxpath;
		filepath += taskpath;
	}
	
	/**
	 * 
	 */
	private TaskMgrDriver() {
		File f = new File(filepath);
		if(!f.exists()) 
		{
			try
			{
				f.getParentFile().mkdir();
				f.createNewFile();
			}catch(IOException e) 
			{
				e.printStackTrace();
			}
			saveTaskList();
		}
		else
		{
			loadTaskList();
		}
		ui = new GraphicalView(this);
	}
	
	private void loadTaskList()
	{
		tasks.load(filepath);
	}
	
	private void saveTaskList()
	{
		tasks.save(filepath);
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
		saveTaskList();
		return getTasks();
	}
	
	public ListIterator<Task> overrideTask(int placement){
		tasks.overrideOrder(placement);
		saveTaskList();
		return getTasks();
	}
	
	public ListIterator<Task> addTask(String name, String type, int due, int hours, boolean comp, int diff) 
	{
		Task newTask = new Task(name, type, due, hours, comp, diff);
		tasks.insertTask(newTask);
		saveTaskList();
		return getTasks();
	}
	
	public ListIterator<Task> removeTask(Task task)
	{
		tasks.removeTask(task);
		saveTaskList();
		return getTasks();
	}
}
