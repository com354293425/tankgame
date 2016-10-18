package com.test0;

import java.util.Scanner;

public class Mathing {
	
	public static int maxNumber(int m, int n) {
		if(m > n) {
			int tmp = m;
			m = n;
			n = tmp;
		}
		if(n % m == 0) 
			return m;
		else 
			return maxNumber(m, n - m);
	}
	
	public static int minNumber(int m, int n) {
		if(m < n) {
			int tmp = m;
			m = n;
			n = tmp;
		}
		int min = 0;
		for(int i = 2; i <= m * n; i++) {
			if(i % m == 0 && i % n == 0) {
				min = i;
				break;
			}
		}
		return min;
	}
	
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		int m = s.nextInt();
		int n = s.nextInt();
		
		int count = 0;
		for(int a = 1; a < n; a++){
			for(int b = 1; b < n; b++){
				if(m == maxNumber(a, b) && n == minNumber(a, b))
					count++;
			}
		}
		System.out.println("一共有: " + count + "个" );
//		System.out.println("最大公约数为: " + maxNumber(m, n) );
//		System.out.print("最小公倍数为: " + minNumber(m, n));
	}
}
