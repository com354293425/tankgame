package com.test0;

import java.util.BitSet;
import java.util.Random;
import java.util.Stack;

public class Bits {
	public static void main(String[] args) {
//		Random random = new Random();
//		byte bt = (byte)random.nextInt();
//		BitSet bb = new BitSet();
//		for(int i = 7; i >= 0; i--)
//			if(((1 << i) & bt) != 0)
//				bb.set(i);
//			else
//				bb.clear(i);
//		System.out.println("byte value " + bt);
//		printBitSet(bb);
//		
//		short st = (short)random.nextInt();
//		BitSet bs = new BitSet();
//		for(int i = 15; i >= 0; i--)
//			if(((1 << i) & st) != 0)
//				bs.set(i);
//			else
//				bs.clear(i);
//		System.out.println("short value " + st);
//		printBitSet(bs);
//		
//		int it = random.nextInt();
//		BitSet bi = new BitSet();
//		for(int i = 31; i >= 0; i--)
//			if(((1 << i) & it) != 0)
//				bi.set(i);
//			else
//				bi.clear(i);
//		System.out.println("int value: " + it);
//		printBitSet(bi);
//		
//		BitSet b127 = new BitSet();
//		b127.set(127);
//		System.out.println("bit127 value: " + b127);
//		BitSet b255 = new BitSet(65);
//		b255.set(255);
//		System.out.println("set bit: " + b255);
//		BitSet b1023 = new BitSet(512);
//		b1023.set(1023);
//		System.out.println("set bit: " + b1023);
	
		Stack stack = new Stack();
		for(int i = 0; i < months.length; i++)
			stack.push(months[i] + " ");
		System.out.println("stack = " + stack);
		System.out.println(stack.size());
		stack.addElement("the last line");
		System.out.println("element 5 = " + stack.elementAt(5));
		System.out.println("poping elements");
		System.out.println(stack.size());
//		System.out.println(stack.peek());
		while(!stack.empty())
			System.out.println(stack.pop());
	}
	
	static void printBitSet(BitSet b) {
		System.out.println("bits: " + b);
		String bbits = new String();
		for(int i = 0; i < b.size(); i++)
			bbits += (b.get(i)? "1":"0");
		System.out.println("bit pattern: " + bbits);
	}
	
	static String[] months = {
		"January", "February", "March", "April",
		"May", "June", "July", "August", "September",
		"October", "November", "December"
	};
}
