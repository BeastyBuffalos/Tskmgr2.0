package model;
import Structures.OrderedPQ;

public enum Singleton{

	INSTANCE;

	OrderedPQ pq = new OrderedPQ();

	public OrderedPQ getPQ(){
		return pq;
	}
	public void setPQ(OrderedPQ PriorityQueue){
		pq = PriorityQueue;
	}

}