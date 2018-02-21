import Structures.OrderedPQ;

public final class Singleton<K, V> {
	private static final Singleton INSTANCE = new Singleton();
	OrderedPQ<K,V> pq = new OrderedPQ<K,V>();
	private <K, V> Singleton() {
	}

	public OrderedPQ<K,V> getPQ(){
		return pq;
	}
	public void setPQ(OrderedPQ<K,V> peequeue){
		pq = peequeue;
	}

	public static Singleton getInstance() {
		return INSTANCE;
	}
}