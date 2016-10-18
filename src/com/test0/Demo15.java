package com.test0;

import java.util.*;

public class Demo15 implements Comparator{
	public static Collection file(Collection c, int start, int size) {
		for(int i = start; i < start + size; i++)
			c.add(Integer.toString(i));
		return c;
	}
	
	public static Collection file(Collection c, int size) {
		for(int i = 0; i < size; i++)
			c.add(Integer.toString(i));
		return c;
	}
	
	public static Collection file(Collection c) {
		for(int i = 0; i < 10; i++)
			c.add(Integer.toString(i));
		return c;
	}
	
	public static Collection newcollection() {
		return file(new ArrayList());
	}
	
	public static Collection newcollection(int start, int size) {
		return file(new ArrayList(), start, size);
	}
	
	public static Collection newcollection(int size) {
		return file(new ArrayList(), size);
	}
	
	public static void print(Collection c) {
		for(Iterator iterator = c.iterator();iterator.hasNext();)
			System.out.println(iterator.next() + " ");
		System.out.println();
	}
	
	public int compare(Object o1, Object o2) {
		String s1 = ((String)o1).toLowerCase();
		String s2 = ((String)o2).toLowerCase();
		return s1.compareTo(s2);
	}
	
	public static void main(String[] args) {
		String[] s = Demo18.randStrings(4, 10);
		Demo18.print(s);
		Demo15 demo15 = new Demo15();
		Arrays.sort(s, demo15);
		Demo18.print(s);
		int ioc = Arrays.binarySearch(s, s[5], demo15);
		System.out.println(s[5] + ": " + ioc);
		List list = Collections.nCopies(3, s);
		Iterator iterator = list.iterator();
		while(iterator.hasNext())
			System.out.println(" " + iterator.next());
		
//		Collection c = newcollection();
//		c.add("ten");
//		c.add("eleven");
//		print(c);
//		Object[] array = c.toArray();
//		String[] str = (String[])c.toArray(new String[1]);
//		
//		System.out.println("collections.max(): " + Collections.max(c));
//		System.out.println("collections.min(): " + Collections.min(c));
//		
//		c.addAll(newcollection());
//		print(c);
//		
//		c.remove("3");
//		print(c);
//		c.remove("3");
//		print(c);
//		
//		c.removeAll(newcollection());
//		print(c);
//		c.addAll(newcollection());
//		print(c);
//		
//		System.out.println("c.contains('4'): " + c.contains("4"));
//		System.out.println("containsAll(newcollection()): " + c.containsAll(newcollection()));
//		
//		Collection c2 = newcollection(5, 3);
//		c.retainAll(c2);
//		print(c);
//		
//		c.removeAll(c2);
//		System.out.println("c.isEmpty(): " + c.isEmpty());
//		
//		c = newcollection();
//		print(c);
//		c.clear();
//		System.out.println("after c.clear(): ");
//		print(c);
//		System.out.println("size(): " + c.size());
	}
}
