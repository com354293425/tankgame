package com.test0;

import java.util.Vector;

interface Compare {
	boolean lessThan (Object lhs, Object rhs);
	boolean lessThanOrEqual (Object lhs, Object rhs);
}

public class Demo14 extends Vector{
	private Compare compare;
	public Demo14(Compare compares) {
		compare = compares;
	}
	public void sort() {
		quickSort(0, size() - 1);
	}
	private void quickSort(int left, int right) {
		if(right > left) {
			Object o1 = elementAt(right);
			int i = left - 1;
			int j = right;
			while(true) {
				while(compare.lessThan(elementAt(++i), o1))
					;
				while(j > 0)
					if(compare.lessThanOrEqual(elementAt(--i), o1))
						break;
				if(i >= j) break;
				swap(i, j);
			}
			swap(i, right);
			quickSort(left, i - 1);
			quickSort(i + 1, right);
		}
	}
	
	private void swap(int local1, int local2){
		Object om = elementAt(local1);
		setElementAt(elementAt(local2), local1);
		setElementAt(om, local2);
	}
}
