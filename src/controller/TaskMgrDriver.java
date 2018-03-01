/**
 * 
 */
package controller;
import model.TaskList;
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
}
