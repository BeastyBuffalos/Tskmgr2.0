package model;

import java.util.*;
import Structures.OrderedPQ;
import Structures.PQEntry;

/**
 * 
 * @author ejoverwe
 *
 */
public class TaskList {
	
	private final Comparator<Task> c = (Task t1, Task t2) -> 
	{
		return (weight(t1) < weight(t2)) ? -1 : ((weight(t1) == weight(t2)) ? 0 : -1);
	};
	private OrderedPQ<Task, TaskWrapper> tasks = new OrderedPQ<Task, TaskWrapper>(c);
	
	public TaskList() {
		//TODO
	}
	
	public void insertTask(Task task){
		tasks.insert(task, new TaskWrapper(task));
	}
	
	public Task removeTask(Task task){
		int size1 = tasks.size();
		OrderedPQ<Task,TaskWrapper> pq2 = new OrderedPQ<Task, TaskWrapper>(c);
		while(!(tasks.isEmpty())){
			PQEntry<Task,TaskWrapper> removed = tasks.removeMin();
			if (removed.getValue().get() != task)
				pq2.insert(removed.getKey(), removed.getValue());
		}
		if (tasks.size() == size1)
			System.out.println("Invalid task to remove.");
		tasks = pq2;
		return task;
	}
	
	public Task editTask(Task task, String name1, String type1, int due, int hours, boolean comp, int diff){
		OrderedPQ pq = Singleton.INSTANCE.getPQ();
		OrderedPQ<Task,TaskWrapper> pq2 = new OrderedPQ<Task,TaskWrapper>(c);
		while(!(pq.isEmpty())){
			PQEntry<Task,TaskWrapper> removed = pq.removeMin();
			if (removed.getValue().get() != task)
				pq2.insert(removed.getKey(), removed.getValue());
			else{
				tasks.insert(task,new TaskWrapper(task));
			}
		}
		Singleton.INSTANCE.setPQ(pq2);
		return task;
	}

	private double weight(Task task){
		int difficulty = task.getDifficulty();
		int duedate = task.getDue();
		int time = task.getHours();
		String tasktype = task.getType();
		boolean isComplete = task.getComplete();
		
		if (task.getComplete()){
			return 0;
		}

		//Temporary weighting, to be adjusted at a later date or possibly by users convienience
		return (difficulty * 2) + (10 / duedate) + (time * 2);
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
