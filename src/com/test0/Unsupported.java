package com.test0;

import java.util.*;

public class Unsupported {
	private static String[] s = {
		"one", "two", "three", "four", "five",
		"six", "seven", "eight", "nine", "ten",
	};
	static List a = Arrays.asList(s);
	static List a2 = Arrays.asList(new String[] { s[3], s[4], s[5]});
	public static void main(String[] args) {
		Demo15.print(a);
		System.out.println("a.contains(" + s[0] + ") " + a.contains(s[0]));
		System.out.println("a.indexOf(" + s[3] + ") " + a.indexOf(s[3]));
		
		ListIterator lIterator = a.listIterator(a.size());
		while(lIterator.hasPrevious())
			System.out.print(lIterator.previous() + " ");
		System.out.println();
		for(int i = 0; i < a.size(); i++)
			a.set(i, "33");
		Demo15.print(a);
		System.out.println(a.size());
		System.out.println("a.get()" + a.get(4));
	}
}
