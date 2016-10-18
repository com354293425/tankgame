package com.test0;

interface Selector {
	boolean end();
	Object current();
	void next();
}

public class Demo8 {
	private Object[] o;
	private int next = 0;
	public Demo8(int size) {
		o = new Object[size];
	}
	public void add(Object x) {
		if(next < o.length) {
			o[next] = x;
			next++;
		}
	}
	private class SSlector implements Selector {
		int i = 0;
		public boolean end() {
			return i == o.length;
		}

		public Object current() {
			return o[i];
		}

		public void next() {
			if(i < o.length) i++;
		}
	}
	public Selector getSelector() {
		return new SSlector();
	}
	public static void main(String[] args) {
		Demo8 demo8 = new Demo8(10);
		for(int i = 0; i < 10; i++)
			demo8.add(Integer.toString(i));
		Selector s1 = demo8.getSelector();
		while(!s1.end()) {
			System.out.println(s1.current());
			s1.next();
		}
	}
}
