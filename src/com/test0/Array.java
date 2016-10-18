package com.test0;

import java.util.Random;

class Soup {
	private Soup() {}
	public static Soup makeSoup() {
		return new Soup();
	}
	
	private static Soup soup = new Soup();
	public static Soup getSoup() {
		return soup;
	}
}

public class Array {
	static Random rand = new Random();
	static int pRand(int mod) {
		return Math.abs(rand.nextInt()) % mod + 1;	
	}
	
	public static void main(String[] args) {
		int[][] a1 = {
				{1,2,3},{4,5,6},
		};		
		for(int i = 0; i < a1.length; i++){
			for(int j = 0; j < a1[i].length; j++)
				System.out.println("a1[" + i + "][" + j + "] = " + a1[i][j]);
		}
		
		int[][][] a2 = new int[2][2][4];
		for(int i = 0; i < a2.length; i++){
			for(int j = 0; j < a2[i].length; j++){
				for(int k = 0; k < a2[i][j].length; k++)
					System.out.println("a2[" + i + "][" + j + "][" + k + "] = " + a2[i][j][k]);
			}
		}
		
		int[][][] a3 = new int[pRand(7)][][];
		for(int i = 0; i <a3.length; i++){
			a3[i] = new int[pRand(5)][];
			for(int j = 0; j < a3[i].length; j++)
				a3[i][j] = new int[pRand(5)];
		}
		for(int i = 0; i < a3.length; i++){
			for(int j = 0; j < a3[i].length; j++){
				for(int k = 0; k < a3[i][j].length; k++)
					System.out.println("a3[" + i +"][" + j +"][" + k + "] = " + a3[i][j][k]);
			}
		}
		
		Integer[][] a4 = {
				{new Integer(1),new Integer(2)},
				{new Integer(3),new Integer(4)},
				{new Integer(5),new Integer(6)},
		};
		for(int i = 0; i < a4.length; i++)
			for(int j = 0; j < a4[i].length; j++)
				System.out.println("a4[" + i + "][" + j +"] = " + a4[i][j]);
		
		Integer[][] a5;
		a5 = new Integer[3][];
		for(int i = 0; i < a5.length; i++){
			a5[i] = new Integer[3];
			for(int j = 0; j < a5[i].length; j++)
				a5[i][j] = new Integer(i * j);
		}
		for(int i = 0; i < a5.length; i++)
			for(int j = 0; j < a5[i].length; j++)
				System.out.println("a5[" + i + "][" + j + "] = " + a5[i][j]);
	}
}
 