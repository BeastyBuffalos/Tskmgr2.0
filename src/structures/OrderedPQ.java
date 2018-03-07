package structures;
import java.util.Comparator;
<<<<<<< HEAD
import java.util.ListIterator;

import structures.DoublyLinkedList.listIterator;
=======
import java.io.Serializable;
>>>>>>> master
/**
 * A PriorityQueue based on an ordered Doubly Linked List. 
 * @author Eli Hovis
 */

public class OrderedPQ<K,V> implements Serializable {

	protected DoublyLinkedList<PQEntry<K,V>> opq = new DoublyLinkedList<>();
	private Comparator <K> C;

	
	public OrderedPQ() {
		this(new DefaultComparator<K>());
	}

	
	public OrderedPQ(Comparator<K> c) {
		C = c;
	}

	
	public int size() {
		return opq.size();		//returns size
	}

	
	public boolean isEmpty() {
		return opq.isEmpty();	//returns if its empty or not
	}

	
	/**
	 * inserts Entry into the Sorted priority queue
	 * @param key
	 * @param value
	 * @return the added entry
	 * @throws IllegalArgumentException
	 */
	public PQEntry<K, V> insert(K key, V value) throws IllegalArgumentException {
		invalidKeyCheck(key);
		PQEntry<K,V> addition = new PQEntry<>(key,value);	//combines key and value into an entry
		Position <PQEntry<K,V>> cursor = opq.last();		//cursor for iteration through the list
		while(cursor != null && C.compare(addition.getKey(), cursor.getElement().getKey()) < 0)
			cursor = opq.before(cursor);	//iterates backward through list until the cursor is null
		if (cursor == null)
			opq.addFirst(addition);	//if null add to start of OPQ
		else
			opq.addAfter(cursor, addition);	//if not null append to curser
		return addition;
	}

	
	/**
	 * return element of the min entry
	 * @return min entry in list
	 */
	public PQEntry<K, V> min() {
		if(opq.isEmpty())	//null check
			return null;
		return opq.first().getElement(); //return min entry
	}

	
	/**
	 * return and remove min entry
	 * @return entry removed
	 */
	public PQEntry<K, V> removeMin() {
		if(opq.isEmpty())	//null check
			return null;
		return opq.remove(opq.first());		//remove and return min entry
	}

	
/**
 * checks if key is valid
 * @param key
 * @return if key is vaid
 * @throws IllegalArgumentException
 */
	protected boolean invalidKeyCheck(K key) throws IllegalArgumentException{
		try{
			return C.compare(key,key) == 0;
		}
		catch (ClassCastException e){
			throw new IllegalArgumentException();
		} 
	} 
	
	
	private ListIterator<K,V> getListIterator()
	{
		return new listIterator();
	}
}
