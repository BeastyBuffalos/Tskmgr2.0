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
	OrderedPQ tasks = new OrderedPQ();
	
	public TaskList() {
		//TODO
	}
	
	public void insertTask(Task task){
		tasks.insert(weight(task),task);
	}
	
	public OrderedPQ<K, V> removeTask(Task task){
		OrderedPQ pq = Singleton.INSTANCE.getPQ();
		int size1 = pq.size();
		OrderedPQ<K,V> pq2 = new OrderedPQ<K,V>();
		while(!(pq.isEmpty())){
			PQEntry<K,V> removed = pq.removeMin();
			if (removed.getValue() != task)
				pq2.insert(removed.getKey(), removed.getValue());
		}
		if (pq.size() == size1)
			System.out.println("Invalid task to remove.");
		Singleton.INSTANCE.setPQ(pq2);
		return pq2;
	}
	
	public OrderedPQ<K, V> editTask(Task task, String name1, String type1, int due, int hours, boolean comp, int diff){
		OrderedPQ pq = Singleton.INSTANCE.getPQ();
		OrderedPQ<K,V> pq2 = new OrderedPQ<K,V>();
		while(!(pq.isEmpty())){
			PQEntry<K,V> removed = pq.removeMin();
			if (removed.getValue() != task)
				pq2.insert(removed.getKey(), removed.getValue());
			else{
				tasks.insert(weight(task),task);
			}
		}
		Singleton.INSTANCE.setPQ(pq2);
		return pq2;
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
