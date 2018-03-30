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

	public TaskList() {
		//TODO
	}

	public void insertTask(Task task){
		tasks.add(task);

		// call sorting algorithm

		radixsort(tasks);

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

	

	private void radixsort(ArrayList<Task> tasklist) {

		tasklist.get(0).getDue(); //smallest, then this one third
		tasklist.get(0).getHours(); //biggest, then this one second
		tasklist.get(0).getDifficulty(); //biggest, first to be sorted

		ArrayList<Integer> tempdiff = new ArrayList<Integer>(); //first to sort, biggest
		ArrayList<Integer> temphou = new ArrayList<Integer>(); //second to sort, biggest
		ArrayList<Integer> tempdue = new ArrayList<Integer>(); //third to sort, smallest

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

		ArrayList<Task> Rtemp = new ArrayList<Task>();

		MergeSort.sort(temp);

		Collections.sort(temp, Collections.reverseOrder());

		for(int i = 0; i < tasklist.size(); i++) {
			Rtemp.add(tasklist.get(i));
		}

		for(int i = 0; i < temp.size(); i++) {

			for(int j = 0; j < Rtemp.size(); j++) {

				if( temp.get(i) == Rtemp.get(j).getDifficulty() ) {

					tasklist.add(i, Rtemp.get(j));

				}

			}
		}

	}

	private void countsorthour(ArrayList<Task> tasklist, ArrayList<Integer> temp) {

		ArrayList<Task> Rtemp = new ArrayList<Task>();

		MergeSort.sort(temp);

		Collections.sort(temp, Collections.reverseOrder());

		for(int i = 0; i < tasklist.size(); i++) {
			Rtemp.add(tasklist.get(i));
		}

		for(int i = 0; i < temp.size(); i++) {

			for(int j = 0; j < Rtemp.size(); j++) {

				if( temp.get(i) == Rtemp.get(j).getHours() ) {

					tasklist.add(i, Rtemp.get(j));

				}

			}
		}

	}

	private void countsortdue(ArrayList<Task> tasklist, ArrayList<Integer> temp) {

		ArrayList<Task> Rtemp = new ArrayList<Task>();

		MergeSort.sort(temp);

		for(int i = 0; i < tasklist.size(); i++) {
			Rtemp.add(tasklist.get(i));
		}

		for(int i = 0; i < temp.size(); i++) {

			for(int j = 0; j < Rtemp.size(); j++) {

				if( temp.get(i) == Rtemp.get(j).getDue() ) {

					tasklist.add(i, Rtemp.get(j));

				}

			}
		}

	}


	public ArrayList<Task> overrideOrder(ArrayList<Task> list, int placement){
		list.get(placement).setOverride(true);
		Task prior, post;
		if (placement > 0){
			prior = list.get(placement - 1);
			post = list.get(placement + 1);

			int pridue = prior.getDue();
			int prihrs = prior.getHours();
			int pridiff = prior.getDifficulty();

			int postdue = post.getDue();
			int posthrs = post.getHours();
			int postdiff = post.getDifficulty();

			int diffdelta = (postdiff - pridiff) / 2;
			int hrsdelta = (posthrs - prihrs) / 2;
			int duedelta = (postdue - pridue) / 2;

			list.get(placement).setDueDateOverride(postdue - duedelta);
			list.get(placement).setHrsOverride(posthrs - hrsdelta);
			list.get(placement).setDifficultyOverride(postdiff - diffdelta);
		}
		return list;
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

	public void load(String path)
	{
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));)
		{
			tasks = (OrderedPQ<Task, TaskWrapper>) ois.readObject();
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
			private ListIterator<PQEntry<Task, TaskWrapper>> it = tasks.getListIterator();

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
