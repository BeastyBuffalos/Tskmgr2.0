package model;

import java.util.*;
import java.io.Serializable;
import java.io.*;
import java.util.function.IntSupplier;

/**
 * 
 * @author ejoverwe
 *
 */
public class TaskList{

	
	private ArrayList<Task> tasks = new ArrayList<>();

	public TaskList() {
		//TODO
	}
	
	public void insertTask(Task task){
		tasks.add(task);
		radixsort(tasks);
	}

	public Task removeTask(Task task){
		tasks.remove(task);

		radixsort(tasks);
		
		return task;

	}

	public Task editTask(Task task, 
			String name1, String type1, int due, int hours, boolean comp, int diff){

		Task updated = new Task(name1, type1, due, hours, comp, diff);
		tasks.remove(task);
		tasks.add(updated);

		radixsort(tasks);

		return updated;
	}

	public void deleteTask(Task task) {
		
		tasks.remove(task);
		radixsort(tasks);
		
	}

	private void radixsort(ArrayList<Task> tasklist) {

		ArrayList<Task> temp= new ArrayList<Task>(); 

		for(int i = 0; i < tasklist.size(); i++) {

			temp.add(tasklist.get(i));
		}
		sort(temp, this::compareDiff);
		sort(temp, this::compareHour);
		sort(temp, this::compareDue);
		tasks = temp;
	}

	private void sort(ArrayList<Task> temp, Comparator<Task> c) {
		Collections.sort(temp, Collections.reverseOrder(c));
	}
	
	private int taskComparator(Task a, Task b, IntSupplier sa, IntSupplier sb)
	{
		return (sa.getAsInt() > sb.getAsInt()) ? -1 : 
			(sa.getAsInt() < sb.getAsInt()) ? 1 : 0;
	}
	
	private int compareDiff(Task a, Task b)
	{
		return taskComparator(a, b, a::getDifficulty, b::getDifficulty);
	}
	
	private int compareHour(Task a, Task b)
	{
		return taskComparator(a, b, a::getHours, b::getHours);
	}
	private int compareDue(Task a, Task b)
	{
		return taskComparator(a, b, a::getDue, b::getDue);
	}


	public void overrideOrder(int placement, Task task){

		Task prior, post;

		placement -= 1;
		
		if (placement == 0){
			task.setDueDateOverride(0);
			task.setHrsOverride(0);
			task.setDifficultyOverride(0);
		}


		else if (placement >= tasks.size()){
			placement = tasks.size() - 1;
			task.setDueDateOverride(9);
			task.setHrsOverride(0);
			task.setDifficultyOverride(0);
		}

		else{
			prior = tasks.get(placement - 1);
			post = tasks.get(placement + 1);
			
			int pridue = prior.getDue();
			int prihrs = prior.getHours();
			int pridiff = prior.getDifficulty();

			int postdue = post.getDue();
			int posthrs = post.getHours();
			int postdiff = post.getDifficulty();
			
			if(prior.isOverride()){
				pridue = prior.getDueDateOverride();
				prihrs = prior.getHrsOverride();
				pridiff = prior.getDifficultyOverride();
			}
			if(post.isOverride()){
				postdue = post.getDueDateOverride();
				posthrs = post.getHrsOverride();
				postdiff = post.getDifficultyOverride();
			}

			int diffdelta = (postdiff - pridiff) / 2;
			int hrsdelta = (posthrs - prihrs) / 2;
			int duedelta = (postdue - pridue) / 2;

			task.setDueDateOverride(postdue - duedelta);
			task.setHrsOverride(posthrs - hrsdelta);
			task.setDifficultyOverride(postdiff - diffdelta);
		}
		task.setOverride(true);
		radixsort(tasks);
	}

	public void save(String path)
	{
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));)
		{
			oos.writeObject(tasks);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	void printTasks()
	{
		for(Task t: tasks)
		{
			System.out.println(t.getName());
		}
	}

	public void load(String path)
	{
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));)
		{
			tasks = (ArrayList<Task>) ois.readObject();
		}catch(IOException e)
		{
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
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
			private ListIterator<Task> it = tasks.listIterator();

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
				return it.next();
			}

			@Override
			public int nextIndex() {
				return 0;
			}

			@Override
			public Task previous() {
				return it.previous();
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
