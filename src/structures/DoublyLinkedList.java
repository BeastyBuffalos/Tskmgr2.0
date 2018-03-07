package structures;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A DoublyLinkedList based
 * @author Eli Hovis
 */
abstract interface Position<E> {
	E getElement() throws IllegalStateException;
}

public class DoublyLinkedList<E>{
	private Node<E> head;		//head of list
	private Node<E> tail;		//tail of list
	private int size = 0;		//elements in list

	/**
	 * default constructor for DoublyLinkedList. 
	 * creates head and tail nodes with an empty list as default
	 */
	public DoublyLinkedList() {
		head = new Node<>( null, null, null);
		tail = new Node<>( null, head, null);
		head.setNext(tail);
	}


	/**
	 * returns size of list
	 * @return size of list
	 */
	public int size() {
		return size;
	}


	/**
	 * checks to see if list is empty
	 * @return if the list is empty
	 */
	public boolean isEmpty() {
		return (size == 0);
	}


	/**
	 * gets the first nodes position
	 * @return position of first node
	 */
	public Position<E> first() {
		return toPosition(head.getNext());
	}


	/**
	 * gets position of last node
	 * @return position of last node
	 */
	public Position<E> last() {
		return toPosition(tail.getPrevious());
	}


	/**
	 * gets a position before a position
	 * @param p node that sits after a node needing a position check
	 * @return position of node before the specified position
	 */
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = toNode(p);
		return toPosition(node.getPrevious());
	}


	/**
	 * gets a position after a specified position
	 * @param p node that sits before a node that needs a position checked
	 * @return the position of the node after the specified node
	 */

	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = toNode(p);
		return toPosition(node.getNext());
	}

	/**
	 * creates and links a new node
	 * @param e element in said node
	 * @param prev previous node
	 * @param post next node
	 * @return position of the newly inserted node
	 */
	private Position<E> insert(E e, Node<E> prev, Node<E> post)
	{
		Node<E> newNode = new Node<E>(e, prev, post); 
		post.setPrevious(newNode);
		prev.setNext(newNode);
		size++;
		return newNode;
	}


	/**
	 * adds a node between the head and the previous first node
	 * @param e element to addfirst
	 */
	public Position<E> addFirst(E e) {
		return insert( e, head, head.getNext());
	}


	/**
	 * adds a node between the tail and the previously last node
	 * @param e element to addlast
	 */
	public Position<E> addLast(E e) {
		return insert(e, tail.getPrevious(), tail);
	}


	/**
	 * adds a node before a specified position
	 * @param p position of node to place a node prior
	 * @param e element to place in node
	 * @return position of the new node
	 */
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = toNode(p);
		return insert(e, node.getPrevious(),node);
	}


	/**
	 * adds a node after a specified position
	 * @param p position of node to place node after
	 * @param e element to place in the node
	 * @return position of the new node
	 */
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = toNode(p);
		return insert(e, node, node.getNext());
	}


	/**
	 * sets an element and returns replaced element
	 * @param p position of node to change
	 * @param e element to put into node
	 * @return E element removed
	 */
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = toNode(p);
		E removed = node.getElement();
		node.setElement(e);
		return removed;
	}


	/** 
	 * removes a node from the list and returns its element, while clearing the data of said node
	 * @param p position of node to remove
	 * @return E element removed
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = toNode(p);
		Node<E> prev = node.getPrevious();
		Node<E> post = node.getNext();
		prev.setNext(post);
		post.setPrevious(prev);
		size--;
		E removed = node.getElement();
		node.setElement(null); 
		node.setNext(null);
		node.setPrevious(null);
		return removed;
	}

	/**
	 * removes first node and returns the value
	 * @return element of removed node
	 * @throws IllegalArgumentException
	 */

	public E removeFirst() throws IllegalArgumentException {
		Position<E> temp = first();
		remove(first());
		return temp.getElement();
	}

	/**
	 * removes last node and returns the value
	 * @return element of removed node
	 * @throws IllegalArgumentException
	 */

	public E removeLast() throws IllegalArgumentException {
		Position<E> temp = last();
		remove(last());
		return temp.getElement();
	}


	/**
	 * constructor for element iterator
	 */
	public Iterator<E> iterator() {
		return new ElementIterator();
	}


	/**
	 * constructor for element iterator
	 */
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	/**
	 * internal class to implement position iterator
	 * @author Eli Hovis
	 *
	 */
	private class PositionIterator implements Iterator<Position<E>>
	{
		private Position<E> chosenOne = first();
		private Position<E> lastChosen = null;

		/**
		 * checks if theres another object to iterate
		 * @return if theres another object
		 */

		public boolean hasNext()
		{
			if (chosenOne == null)
				return false;
			return true;
		}

		/**
		 * iterates to next element and returns last one
		 * @return previously chosen element
		 */

		public Position<E> next() throws NoSuchElementException
		{
			if(chosenOne == null)
				throw new NoSuchElementException();
			lastChosen = chosenOne;
			chosenOne = after(chosenOne);
			return lastChosen;
		}

		/**
		 * removes an element from the iterator
		 * @param
		 */

		public void remove() throws IllegalStateException
		{
			if(lastChosen == null)
				throw new IllegalStateException();
			DoublyLinkedList.this.remove(lastChosen);
			lastChosen = null;
		}
	} 
	
	
	
	public ListIterator<E> getListIterator()
	{
		return new listIterator();
	}
	
	private class listIterator implements ListIterator<E> 
	{
			Node<E> node = head;

		@Override
		public void add(Object arg0) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean hasNext() {
			return node.getNext() != tail;
		}

		@Override
		public boolean hasPrevious() {
			return node.getPrevious() != head;
		}

		@Override
		public E next() {
			node = node.getNext();
			return node.getElement();
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public E previous() {
			node = node.getPrevious();
			return node.getElement();
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
		public void set(Object arg0) {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * implements iterable for generic type positions for use in Doublylinkedlists
	 * @author Eli Hovis
	 *
	 */
	private class PositionIterable implements Iterable<Position<E>>
	{
		/**
		 * constructor for creating an iterator
		 * @return position iterator
		 */

		public Iterator<Position<E>> iterator()
		{
			return new PositionIterator();
		}
	}

	/**
	 * 
	 * @author Eli Hovis
	 *
	 *internal class for implementing an elementIterator
	 *
	 */
	private class ElementIterator implements Iterator<E>
	{
		Iterator<Position<E>> positionIterator = new PositionIterator();

		/**
		 * checks if theres another position after the current one
		 * @return if theres another position or not
		 */

		public boolean hasNext()
		{
			return positionIterator.hasNext();
		}

		/**
		 * gets the next element from the iterator
		 * @return the element of the next item in the iterator
		 */

		public E next()
		{
			return positionIterator.next().getElement(); 
		}

		/**
		 * removes an item from the iterator
		 */

		public void remove()
		{
			positionIterator.remove();
		}
	}

	/**
	 * checks to see if a position has both front and tail nodes and returns the position as a node
	 * @param p position in list
	 * @return returns position as a node
	 * @throws IllegalArgumentException
	 */

	private Node<E> toNode(Position<E> p) throws IllegalArgumentException
	{
		if(p instanceof Node == false) 
			throw new IllegalArgumentException();
		Node<E> node = (Node<E>) p;
		if( node.getNext() == null)
			throw new IllegalArgumentException();
		return node;
	}

	/**
	 * turns a node into its position while checking if its head/tail node
	 * @param node node to be checked into a position
	 * @return
	 */

	private Position<E> toPosition(Node<E> node)
	{
		if( node == head || node == tail)
			return null; 
		return node;
	}

	/**
	 * implementation of nodes to use for the doublylinkedlist
	 * @author Eli Hovis
	 *
	 */
	private static class Node<E> implements Position<E>
	{
		private E element;
		private Node<E> previous;
		private Node<E> next;

		/**
		 * Constructor for a node
		 * @param e element of a node
		 * @param p previous node
		 * @param n next node
		 */

		public Node(E e, Node<E> p, Node<E> n)
		{
			element = e;
			previous = p;
			next = n;
		}

		/**
		 * gets element from a node
		 * @return element from a node
		 */

		public E getElement() throws IllegalStateException
		{
			if( next == null)
				throw new IllegalStateException();
			return element;
		}

		/**
		 * gets previous node
		 * @return previous node
		 */

		public Node<E> getPrevious()
		{
			return previous;
		}

		/**
		 * gets next node
		 * @return the next node
		 */

		public Node<E> getNext()
		{
			return next;
		}

		/**
		 * sets the element in a node
		 * @param e element to set
		 */

		public void setElement(E e)
		{
			element = e;
		}

		/**
		 * sets previous node
		 * @param p the node to set
		 */

		public void setPrevious(Node<E> p)
		{
			previous = p;
		}

		/**
		 * sets next node
		 * @param n node to set
		 */

		public void setNext(Node<E> n){
			next = n;
		}
	} 
}