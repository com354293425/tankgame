package com.test0;

import java.util.*;

public class Demo18{
	static Random random = new Random();
	static String ssource = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz";
	static char[] src = ssource.toCharArray();
	public static String randString(int length) {
		char[] chars = new char[length];
		int rnd;
		for(int i = 0; i < length; i++) {
			rnd = Math.abs(random.nextInt()) % src.length;
			chars[i] = src[rnd];
		}
		return new String(chars);
	}

	public static String[] randStrings(int length, int size) {
		String[] s = new String[size];
		for(int i = 0; i < size; i++)
			s[i] = randString(length);
		return s;
	}
	
	public static void print(byte[] b) {
		for(int i = 0; i < b.length; i++)
			System.out.print(b[i] + " ");
		System.out.println();
	}
	
	public static void print(String[] s) {
		for(int i = 0; i < s.length; i++)
			System.out.print(s[i] + " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		byte[] b = new byte[15];
		random.nextBytes(b);
		print(b);
		Arrays.sort(b);
		print(b);
		int ioc = Arrays.binarySearch(b, b[10]);
		System.out.println(b[10] + ": " + ioc);
		
		String[] s = randStrings(4, 10);
		print(s);
		Arrays.sort(s);
		print(s);
		int soc = Arrays.binarySearch(s, s[3]);
		System.out.println(s[3] + ": " + soc);
	}

	
}
