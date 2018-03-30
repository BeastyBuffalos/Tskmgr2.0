package model;

import java.util.*;

import structures.OrderedPQ;
import structures.PQEntry;
import java.io.Serializable;
import java.io.*;

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
	private ArrayList<Task> tasks = new ArrayList<>();
	private OrderedPQ<Task, TaskWrapper> taskPQ = new OrderedPQ<Task, TaskWrapper>();
	
	public TaskList() {
		//TODO
	}

	public void insertTask(Task task){
		tasks.add(task);
		
		// call sorting algorithm
		
	}

	public Task removeTask(Task task){
		tasks.remove(task);
		
		// call sorting algorithm
		
		return task;
		
//		int size1 = tasks.size();
//		OrderedPQ<Task,TaskWrapper> pq2 = new OrderedPQ<Task, TaskWrapper>(c);
//		while(!(tasks.isEmpty())){
//			PQEntry<Task,TaskWrapper> removed = tasks.removeMin();
//			if (removed.getValue().get() != task)
//				pq2.insert(removed.getKey(), removed.getValue());
//		}
//		if (tasks.size() == size1)
//			System.out.println("Invalid task to remove.");
//		tasks = pq2;
//		return task;
	}

	public Task editTask(Task task, 
			String name1, String type1, int due, int hours, boolean comp, int diff){
		
		Task updated = new Task(name1, type1, due, hours, comp, diff);
		tasks.remove(task);
		tasks.add(updated);
		
		// call sorting algorithm
		
		return updated;
		
//		OrderedPQ<Task,TaskWrapper> pq2 = new OrderedPQ<Task,TaskWrapper>(c);
//		while(!(tasks.isEmpty())){
//			PQEntry<Task,TaskWrapper> removed = tasks.removeMin();
//			if (removed.getValue().get() != task)
//				pq2.insert(removed.getKey(), removed.getValue());
//				
//			else{
//				Task newTask = new Task(name1, type1, due, hours, comp, diff);
//				tasks.insert(newTask,new TaskWrapper(newTask)); 
//			}
//		}
//		return task;
	}

	private double weight(Task task){
		int difficulty = task.getDifficulty();
		int duedate = task.getDue();
		int time = task.getHours();

		if (task.isOverride()){
			return task.getWeightOverride();
		}
		
		int hours = 0;
		
		// set hours to value
		if (time == 0 || time == 1) 
		{
			hours = 1;
		} 
		else if (time == 2) 
		{
			hours = 2;
		} 
		else if (time == 3) 
		{
			hours = 3;
		}
		else {
			hours = 4;
		}
		//Temporary weighting, to be adjusted at a later date or possibly by users convienience
		return (difficulty) + (duedate / 1000000) + (hours);
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
	
	private void save(String path)
	{
		try
		{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)));
		}catch(IOException e)
		{
			
		}
	}
	
	/**
	 * 
	 * @author ejoverwe
	 *
	 */
	private class TaskWrapper implements Serializable{
		private Task t;
		private boolean moved = false;
		public TaskWrapper(Task t){
			this.t = t;
		}

		public Task get() { return t; }

		public void setMoved(boolean moved) { this.moved = moved; }
	}
	
	public ListIterator<Task> getListIterator()
	{
		return new ListIterator<Task>() 
		{
			private ListIterator<PQEntry<Task, TaskWrapper>> it = taskPQ.getListIterator();
			
			@Override
			public void add(Task e) {
				throw new UnsupportedOperationException();
			}

			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public boolean hasPrevious() {
				return it.hasPrevious();
			}

			@Override
			public Task next() {
				return it.next().getKey();
			}

			@Override
			public int nextIndex() {
				return 0;
			}

			@Override
			public Task previous() {
				return it.previous().getKey();
			}

			@Override
			public int previousIndex() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void set(Task e) {
				throw new UnsupportedOperationException();
			}
			
		};
	}

}
