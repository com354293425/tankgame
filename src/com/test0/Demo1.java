package com.test0;

class Insect {
	int i = 9;
	int j;
	Insect() {
		int m = prt("i = " + i + ", j = " + j);
		j = 39;
		System.out.println("Insect.Insect() " + m);
	}
	
	static int x1 = prt("static Insect.x1 initialized");
	
	static int prt(String s) {
		System.out.println(s + " 1");
		return 47;
	}
}

public class Demo1 extends Insect{
	int k = prt("Beetle.k initialized");
	Demo1() {
		prt("k = " + k);
		prt("j = " + j);
	}
	static int x2 =
			prt("static Beetle.x2 initialized");
	static int prt(String s) {
		System.out.println(s + " 2");
		return 63;
	}
	public static void main(String[] args) {
		prt("Beetle constructor");
		Demo1 demo1 = new Demo1();
	}
}
