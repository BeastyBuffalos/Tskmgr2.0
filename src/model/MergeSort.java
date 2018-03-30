package model;

import java.util.ArrayList;
import java.util.Arrays;


public class MergeSort {
	
	private MergeSort() {}

	public static <E extends Comparable<E>> void sort(ArrayList<E> array) {

		int n = array.size();
		
		if ( n < 2 ) {
			return;
		}
		
		int mid = n/2;
		
		ArrayList<E> s1 = new ArrayList<E>(array.subList(0, mid)); //new ArrayList<String>(al.subList(1, 4));
		ArrayList<E> s2 = new ArrayList<E>(array.subList(mid, n));
		
		sort(s1);
		sort(s2);
		
		merge(s1, s2, array);
			}

	public static <E extends Comparable<E>> void merge(ArrayList<E> s1, ArrayList<E> s2, ArrayList<E> array) {
		
		int i = 0; int j = 0;
		
		while( i + j < array.size()) {
			if ( j == s2.size() || ( i < s1.size() && s1.get(i).compareTo(s2.get(j)) < 0))
				array.add(i+j, s1.get(i++));
			else
				array.add(i+j, s2.get(j++));
		}
	}
	
}