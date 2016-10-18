package com.test0;

import java.util.*;

public class ListPerformance {
	private static final int REPS = 100;
	private abstract static class Tester {
		String name;
		int size;
		public Tester(String name,int size) {
			this.name = name;
			this.size = size;
		}
		abstract void test(List list);
	}
	private static Tester[] tests = {
		new Tester("get", 300) {
			void test(List list) {
				for(int i = 0; i < REPS; i++) {
					for(int j = 0; j < list.size(); j++)
						list.get(j);
				}
			}
		},
		new Tester("iteration", 300) {
			void test(List list) {
				for(int i = 0; i < REPS; i++){
					Iterator it= list.iterator();
					while(it.hasNext())
						it.next();
				}
			}
		},
		new Tester("insert", 1000) {
			void test(List list) {
				int half = list.size()/2;
				String string = "test";
				ListIterator lit = list.listIterator(half);
				for(int i = 0; i < size * 10; i++)
					lit.add(string);
			}
		},
		new Tester("remove", 5000) {
			void test(List list) {
				ListIterator lit = list.listIterator(3);
				while(lit.hasNext()){
					lit.next();
					lit.remove();
				}
			}
		},
	};
	
	public static void test(List a) {
		System.out.println("Testing " + a.getClass().getName());
		for(int i = 0; i < tests.length; i ++) {
			Demo15.file(a, tests[i].size);
			System.out.println(tests[i].name);
			long l1 = System.currentTimeMillis();
			tests[i].test(a);
			long l2 = System.currentTimeMillis();
			System.out.println(": " + (l2 - l1));
		}
	}
	
	public static void main(String[] args) {
		test(new ArrayList());
		test(new LinkedList());
	}
}
