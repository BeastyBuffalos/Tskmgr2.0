package model;

import java.util.*;

import structures.OrderedPQ;
import structures.PQEntry;

/**
 * 
 * @author ejoverwe
 *
 */
public class TaskList {

	private final Comparator<Task> c = (Task t1, Task t2) -> 
	{
		return (weight(t1) < weight(t2)) ? -1 : ((weight(t1) == weight(t2)) ? 0 : 1);
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

	public Task editTask(Task task, 
			String name1, String type1, int due, int hours, boolean comp, int diff){
		OrderedPQ<Task,TaskWrapper> pq2 = new OrderedPQ<Task,TaskWrapper>(c);
		while(!(tasks.isEmpty())){
			PQEntry<Task,TaskWrapper> removed = tasks.removeMin();
			if (removed.getValue().get() != task)
				pq2.insert(removed.getKey(), removed.getValue());
				
			else{
				Task newTask = new Task(name1, type1, due, hours, comp, diff);
				tasks.insert(newTask,new TaskWrapper(newTask)); 
			}
		}
		return task;
	}

	private double weight(Task task){
		int difficulty = task.getDifficulty();
		int duedate = task.getDue();
		int time = task.getHours();
		String tasktype = task.getType();
		boolean isComplete = task.getComplete();

		if (task.isOverride()){
			return task.getWeightOverride();
		}
		if (task.getComplete()){
			return 0;
		}
		//Temporary weighting, to be adjusted at a later date or possibly by users convienience
		return (difficulty * 2) + (10 / duedate) + (time * 2);
	}
	
	public OrderedPQ<Task, TaskWrapper> overrideOrder(OrderedPQ<Task, TaskWrapper> pq, Task task){
		OrderedPQ<Task, TaskWrapper> tempPQ = pq;
		OrderedPQ<Task, TaskWrapper> finalPQ =  new OrderedPQ<Task, TaskWrapper>();
		
		PQEntry<Task, TaskWrapper> previous = null;
		PQEntry<Task, TaskWrapper> current = tempPQ.removeMin();
		PQEntry<Task, TaskWrapper> next = tempPQ.removeMin();
		
		
		
		if(current.getKey().equals(task))
			task.setWeightOverride(weight(next.getKey())/2);
		while(!tempPQ.isEmpty()){
			finalPQ.insert(previous.getKey(), previous.getValue());
			previous = current;
			current = next;
			next = tempPQ.removeMin();
			if(current.getKey().equals(task))
				task.setWeightOverride((weight(next.getKey())+weight(previous.getKey()))/2);
		}
		
		finalPQ.insert(current.getKey(), current.getValue());
		finalPQ.insert(next.getKey(), next.getValue());
		
		return finalPQ;
	}
	/**
	 * 
	 * @author ejoverwe
	 *
	 */
	private class TaskWrapper{
		private Task t;
		private boolean moved = false;
		public TaskWrapper(Task t){
			this.t = t;
		}

		public Task get() { return t; }

		public void setMoved(boolean moved) { this.moved = moved; }
	}
}
