package com.test0;

final class Singleton {
	private static Singleton s = new Singleton(47);
	private int i;
	private Singleton(int x) { i = x; }
	public static Singleton getHandle() {
		return s;
	}
	public int getValue() { return i; }
	public void setValue(int x) { i = x; } 
}

public class SingletonPattern {
	public static void main(String[] args) {
		Singleton s = Singleton.getHandle();
		System.out.println(s.getValue());
		Singleton s2 = Singleton.getHandle();
		s2.setValue(9);
		System.out.println(s2.getValue());
	}
}
