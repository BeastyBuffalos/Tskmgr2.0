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



	private void radixsort(ArrayList<Task> tasklist) {

		ArrayList<Integer> tempdiff = new ArrayList<Integer>(); 
		ArrayList<Integer> temphou = new ArrayList<Integer>(); 
		ArrayList<Integer> tempdue = new ArrayList<Integer>(); 

		for(int i = 0; i < tasklist.size(); i++) {

			tempdiff.add(tasklist.get(i).getDifficulty());

		}

		for(int i = 0; i < tasklist.size(); i++) {

			temphou.add(tasklist.get(i).getHours());

		}



		for(int i = 0; i < tasklist.size(); i++) {

			tempdue.add(tasklist.get(i).getDue());

		}
		countsortdiff(tasklist, tempdiff);
		countsorthour(tasklist, temphou);
		countsortdue(tasklist, tempdue);
	}

	private void countsortdiff(ArrayList<Task> tasklist, ArrayList<Integer> temp) {

		ArrayList<Task> Rtemp = tasklist;
		ArrayList<Task> Rtemp2 = new ArrayList<Task>();
		MergeSort.sort(temp);

	

		for(int i = 0; i < temp.size(); i++) {

			for(int j = 0; j < Rtemp.size(); j++) {

				if( temp.get(i) == Rtemp.get(j).getDifficulty() ) {
					Rtemp2.add(Rtemp.get(j));
					break;
				}
			}
		}
		tasklist = Rtemp2;
		
		Collections.sort(tasklist, Collections.reverseOrder(this::compareDiff));
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

	private void countsorthour(ArrayList<Task> tasklist, ArrayList<Integer> temp) {

		ArrayList<Task> Rtemp = tasklist;
		ArrayList<Task> Rtemp2 = new ArrayList<Task>();
		MergeSort.sort(temp);


		for(int i = 0; i < temp.size(); i++) {

			for(int j = 0; j < Rtemp.size(); j++) {

				if(temp.get(i).equals(Rtemp.get(j).getHours())) {
					Rtemp2.add(Rtemp.get(j));
					break;
				}

			}
		}
		tasklist = Rtemp2;

		Collections.sort(tasklist, Collections.reverseOrder(this::compareHour));
		
	}

	private void countsortdue(ArrayList<Task> tasklist, ArrayList<Integer> temp) {

		ArrayList<Task> Rtemp = tasklist;
		ArrayList<Task> Rtemp2 = new ArrayList<Task>();
		MergeSort.sort(temp);


		for(int i = 0; i < temp.size(); i++) {

			for(int j = 0; j < Rtemp.size(); j++) {

				if(temp.get(i).equals(Rtemp.get(j).getDue())) {
					Rtemp2.add(Rtemp.get(j));
					break;
				}

			}
		}
		tasklist = Rtemp2;
	}


	public void overrideOrder(int placement, Task task){

		Task prior, post;

		if (placement <= 1){
			task.setDueDateOverride(0);
			task.setHrsOverride(999);
			task.setDifficultyOverride(999);
		}

		else if (placement >= tasks.size()){
			task.setDueDateOverride(9);
			task.setHrsOverride(0);
			task.setDifficultyOverride(0);
		}

		else {
			
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
	
	public void printTasks()
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
