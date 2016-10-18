package com.test0;

interface Contents {
	int value();
}

interface Destination {
	String readLabel();
} 

public class Demo6 {
	public Destination dest(String s) {
		class PDestination implements Destination {
			private String label;
			private PDestination(String whereTo) { label = whereTo; }
			public String readLabel() {
				return label;
			}
		}
		return new PDestination(s);
	}
	
	public static void main(String[] args) {
		Demo6 demo6 = new Demo6();
		Destination dest = demo6.dest("sss");
	}
}
