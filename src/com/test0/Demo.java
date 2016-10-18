package com.test0;

public class Demo {
	static void prt(String s){
		System.out.println(s);
	}
	
	void f1(char x) { prt("f1(char x)");}
	void f1(byte x) { prt("f1(byte x)");}
	void f1(short x) { prt("f1(short x)");}
	void f1(int x) { prt("f1(int x)");}
	void f1(long x) { prt("f1(long x)");}
	void f1(float x) { prt("f1(float x)");}
	void f1(double x) { prt("f1(double x)");}

	void f2(byte x) { prt("f2(byte x)");}
	void f2(short x) { prt("f2(short x)");}
	void f2(int x) { prt("f2(int x)");}
	void f2(long x) { prt("f2(long x)");}
	void f2(float x) { prt("f2(float x)");}
	void f2(double x) { prt("f2(double x)");}

	void f3(short x) { prt("f3(short x)");}
	void f3(int x) { prt("f3(int x)");}
	void f3(long x) { prt("f3(long x)");}
	void f3(float x) { prt("f3(float x)");}
	void f3(double x) { prt("f3(double x)");}
	
	void f4(int x) { prt("f4(int x)");}
	void f4(long x) { prt("f4(long x)");}
	void f4(float x) { prt("f4(float x)");}
	void f4(double x) { prt("f4(double x)");}

	void f5(long x) { prt("f5(long x)");}
	void f5(float x) { prt("f5(float x)");}
	void f5(double x) { prt("f5(double x)");}
	
	void f6(float x) { prt("f6(float x)");}
	void f6(double x) { prt("f6(double x)");}

	void f7(double x) { prt("f7(double x)");}
	
	void testConstVal(){
		prt("Testing with 5");
		f1(5);f2(5);f3(5);f4(5);f5(5);f6(5);f7(5);
	}
	
	void testChar() {
		char x = 'x';
		prt("char argument:");
		f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);
	}
	
	void testByte() {
		byte x = 0;
		prt("byte argument");
		f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);
	}
	
	void testShort() {
		short x = 0;
		prt("short argument");
		f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);
	}
	
	void testInt() {
		int x = 0;
		prt("int argument:");
		f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);
	}
	
	void testLong() {
		long x = 0;
		prt("long argument:");
		f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);
	}
	
	void testFloat() {
		float x = 0;
		prt("float argument:");
		f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);
	}
	
	void testDouble() {
		double x = 0;
		prt("double argument:");
		f1(x);f2(x);f3(x);f4(x);f5(x);f6(x);f7(x);
	}
	
	public static void main(String[] args) {
		Demo demo = new Demo();
		demo.testConstVal();
		demo.testChar();
		demo.testByte();
		demo.testShort();
		demo.testInt();
		demo.testLong();
		demo.testFloat();
		demo.testDouble();
	}
}
