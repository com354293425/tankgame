package com.test0;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SetPerformance {
	private static final int REPS = 200;
	private abstract static class Tester {
		String name;
		Tester(String name) { this.name = name; }
		abstract void test(Set s, int size);
	}
	private static Tester[] tests = {
		new Tester("add") {
			void test(Set s, int size) {
				for(int i = 0; i < REPS; i++)
					s.clear();
				Demo15.file(s, size);
			}
		},
		new Tester("contains") {
			void test(Set s, int size) {
				for(int i = 0; i < REPS; i++)
					for(int j = 0; j < size; j++)
						s.contains(Integer.toString(j));
			}
		},
		new Tester("iteration") {
			void test(Set s, int size) {
				for(int i = 0; i < REPS * 10; i++){
					Iterator it = s.iterator();
					while(it.hasNext())
						it.next();
				}
			}
		},
	};
	public static void test(Set s, int size) {
		System.out.println("testing " + s.getClass().getName() + " size " + size);
		Demo15.file(s, size);
		for(int i = 0; i < tests.length; i++) {
			System.out.println(tests[i].name);
			long l1 = System.currentTimeMillis();
			tests[i].test(s, size);
			long l2 = System.currentTimeMillis();
			System.out.println(": " + (double)(l2 - l1)/(double)size);
		}
	}
	
	public static void main(String[] args) {
		//small
		test(new HashSet(), 10);
		test(new TreeSet(), 10);
		//medium
		test(new HashSet(), 100);
		test(new TreeSet(), 100);
		//big
		test(new HashSet(), 1000);
		test(new TreeSet(), 1000);
	}
}
