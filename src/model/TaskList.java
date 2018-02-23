package model;

import java.util.*;

/**
 * 
 * @author ejoverwe
 *
 */
public class TaskList {
	List<TaskWrapper> tasks = new LinkedList<TaskWrapper>(); //serializable?
	HashMap<Task, TaskWrapper> taskMap = new HashMap<Task, TaskWrapper>(); //find different structures to use
	
	public TaskList() {
		//TODO
	}
	
	public void addTask(Task t)
	{
		TaskWrapper wrap = new TaskWrapper(t);
		boolean success = (taskMap.put(t, wrap) == null);
		sort(wrap);
	}
	
	public void moveTask(Task t, Task after)
	{
		TaskWrapper wrapT = taskMap.get(t), wrapAfter = taskMap.get(after);
		
	}
	
	/**
	 * Helper method to place a task in the proper position.
	 * @param t - the task to place
	 */
	private void sort(TaskWrapper t)
	{
		//TODO
	}

	/**
	 * 
	 * @author ejoverwe
	 *
	 */
	private class TaskWrapper
	{
		private Task t;
		private int pos = 0;
		private boolean moved = false; 
		public TaskWrapper(Task t)
		{
			this.t = t;
		}
		
		public Task get() { return t; }
		
		public void setMoved(boolean moved) { this.moved = moved; }
		public void setPos(int pos) { this.pos = pos; }
	}
}
