package model;



import java.util.ArrayList;


public class MergeSort {
	
	private MergeSort() {}

	public static <E extends Comparable<E>> void sort(ArrayList<E> array) {

		int n = array.size();
		
		if ( n < 2 ) {
			return;
		}
		
		int mid = n/2;
		
		ArrayList<E> s1 = new ArrayList<E>(array.subList(0, mid)); 
		ArrayList<E> s2 = new ArrayList<E>(array.subList(mid, n));
		
		sort(s1);
		sort(s2);
		
		merge(s1, s2, array);
		
		
	}

	public static <E extends Comparable<E>> void merge(ArrayList<E> s1, ArrayList<E> s2, ArrayList<E> array) {
		
		int i = 0; int j = 0;
		
		
		while( i < s1.size() && j < s2.size()) {
			if (( s1.get(i).compareTo(s2.get(j)) < 0))
				array.set(i+j, s1.get(i++));
			else
				array.set(i+j, s2.get(j++));
		}
		
		while( i < s1.size() ) {
			
			array.set(i+j, s1.get(i++));
			
		}
		
		while( j < s2.size() ) {
			
			array.set(i+j, s2.get(j++));
			
		}
		
    }
		
	
	public static <E extends Comparable<E>> void sortback(ArrayList<E> array) {

		int n = array.size();
		
		if ( n < 2 ) {
			return;
		}
		
		int mid = n/2;
		
		ArrayList<E> s1 = new ArrayList<E>(array.subList(0, mid)); 
		ArrayList<E> s2 = new ArrayList<E>(array.subList(mid, n));
		
		sortback(s1);
		sortback(s2);
		
		mergeback(s1, s2, array);
			}

	public static <E extends Comparable<E>> void mergeback(ArrayList<E> s1, ArrayList<E> s2, ArrayList<E> array) {
		
		int i = 0; int j = 0;
		
		while( i < s1.size() && j < s2.size()) {
			if ( j == s2.size() || ( i < s1.size() && s1.get(i).compareTo(s2.get(j)) <= 0))
				array.add(i+j, s1.get(i++));
			else
				array.add(i+j, s2.get(j++));
		}
	}
	
	public static void main(String[] args) {
		
		ArrayList<Integer> test1 = new ArrayList<Integer>();
		
		test1.add(5);
		System.out.println(test1.isEmpty() + ", " + test1.size());
		test1.add(4);
		System.out.println(test1.isEmpty() + ", " + test1.size());
		test1.add(89);
		System.out.println(test1.isEmpty() + ", " + test1.size());
		test1.add(10);
		System.out.println(test1.isEmpty() + ", " + test1.size());
		test1.add(8);
		System.out.println(test1.isEmpty() + ", " + test1.size());
		
		sort(test1);
		
		for(int i = 0; i < test1.size(); i++) {
			
			System.out.println(test1.get(i));
		}

	}
	
}