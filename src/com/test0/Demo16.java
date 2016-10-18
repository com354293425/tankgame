package com.test0;

import java.util.*;

public class Demo16 {
	public static List file(List a) {
		return (List)Demo15.file(a);
	}
	
	public static void print(List a) {
		for(int i = 0; i < a.size(); i++)
			System.out.println("a.get(" + i + ") = " + a.get(i));
		System.out.println();
	}
	
	static boolean b;
	static Object o;
	static int i;
	static Iterator it;
	static ListIterator lit;
	
	public static void basicTest(List a) {
		a.add(0, "a");	//add at first
		a.add("z"); 	//add at end
		
		a.addAll(file(new ArrayList()));
		a.addAll(3, file(new ArrayList()));
		print(a);
		//b = a.contains("1");
		b = a.containsAll(file(new ArrayList()));
		
		if(a.contains("1"))
			i = a.indexOf("1");
		o = a.get(i);
		it = a.iterator();
		lit = a.listIterator(3);
		
		System.out.println("i = " + a.indexOf("1"));
		
		a.remove(2);
		print(a);
		
		a.set(1, "y");		// Set location 1 to "y"
		print(a);
	}
	
	public static void iterMotion(List a) {
		ListIterator lit = a.listIterator();
	}
	
	public static void iterManipulation(List a) {
		ListIterator lit = a.listIterator();
		lit.add("38");		
		lit.next();
		
		lit.remove();
		lit.next();
	}
	
	public static void testVisual(List a) {
		System.out.println("-------------------------");
		print(a);
		List b = new ArrayList();
		file(b);
		System.out.println("b = ");
		print(b);
//		a.addAll(b);
//		a.addAll(file(new ArrayList()));
		print(a);
		System.out.println("a.size() = " + a.size());
		System.out.println("a.size() / 2 = " + a.size()/2);
		
		ListIterator lit = a.listIterator();
		lit.add("x");
		print(a);
		System.out.println(lit.next());
		lit.remove();
		System.out.println(lit.next());
		print(a);
	}
	
	public static void testLinkedList() {
		LinkedList ll = new LinkedList();
		Demo15.file(ll, 5);
		print(ll);
		ll.addFirst("one");
		ll.addFirst("two");
		print(ll);
		
		System.out.println(ll.removeFirst());
		System.out.println(ll.removeFirst());
		System.out.println(ll.getFirst());
		System.out.println(ll.removeLast());
		print(ll);
	}
	
	public static void testVisual(Set a) {
		Demo15.file(a);
		Demo15.file(a);
		Demo15.file(a);
		Demo15.print(a);
		
		a.addAll(a);
		Demo15.print(a);
		a.add("one");
		a.add("one");
		Demo15.print(a);
	}
	
	public static void main(String[] args) {
		//basicTest(new LinkedList());
		//basicTest(new ArrayList());
		//testVisual(file(new ArrayList()));
		//testLinkedList();
		//testVisual(new HashSet());
		testVisual(new TreeSet());
 	}
}
