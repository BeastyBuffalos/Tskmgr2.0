import Structures.OrderedPQ;
import Structures.PQEntry;
import model.*;
public class TaskSort<K, V> {

	public OrderedPQ<K, V> insertTask(Task task){
		OrderedPQ pq = Singleton.INSTANCE.getPQ();
		pq.insert(Weighting.alg(task.getDifficulty(), task.getDue(), task.getHours(), task.getType(), task.getComplete()),task);
		Singleton.INSTANCE.setPQ(pq);
		return pq;
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
				pq.insert(Weighting.alg(task.getDifficulty(), task.getDue(), task.getHours(), task.getType(), task.getComplete()),task);
			}
		}
		Singleton.INSTANCE.setPQ(pq2);
		return pq2;
	}
}