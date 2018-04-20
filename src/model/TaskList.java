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

		task.setDifficulty(diff);
		task.setDue(due);
		task.setType(type1);
		task.setHours(hours);
		task.setComplete(comp);
		task.setName(name1);

		radixsort(tasks);

		return task;
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


		else if (placement >= tasks.size()-1){
			placement = tasks.size() - 1;
			task.setDueDateOverride(100000000);
			task.setHrsOverride(100000);
			task.setDifficultyOverride(10000);
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
		System.out.println(task.isOverride());
		System.out.println(task.getDifficulty());
		System.out.println(task.getDue());
		System.out.println(task.getHours());

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
