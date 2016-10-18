package com.test0;

import java.util.Vector;

public class Demo11 {
	public String toString() {
		return "CrashJava address: " + this + "\n";
	}
	public static void main(String[] args) {
		Vector v = new Vector();
		for(int i = 0; i < 10; i++)
		v.addElement(new Demo11());
		System.out.println(v);
	}
}
