package com.test0;

public class Testt {
	int arr[];
	
	private void swap(int x, int y) {
		int mid = x;
		x = y;
		y = mid;
	}
	
	public void quickSort(int start, int end) {
		int mid = arr[end];
		int left = start, right = end - 1;
		while(left < right){
			while(arr[left] < mid && left < right)
				left++;
			while(arr[right] >= mid && left < right)
				right--;
			swap(left, right);
		}
		if(arr[left] >= mid)
			swap(left, end);
		else
			left++;
		quickSort(start, left - 1);
		quickSort(left + 1, end);
	}
	
	public static void main(String[] args) {
		int[] myarray = { 6,3,7,1,5,9,2,10 };
		
	}
}
