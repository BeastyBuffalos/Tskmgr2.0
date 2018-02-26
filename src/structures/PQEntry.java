package structures;

public class PQEntry<K,V>{

	private K k;
	private V v;
	public PQEntry(K key, V value){
		k = key;
		v = value;
	}

	public K getKey(){
		return k;
	}
	
	public V getValue(){
		return v;
	}

	protected void setKey(K key){ 
		k = key;
	}
	protected void setValue(V value){
		v = value;
		}
}
