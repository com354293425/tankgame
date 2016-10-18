package com.test0;

import java.io.*;

public class SortedDirList {
	private File path;
	private String[] list;
	public SortedDirList(final String afn) {
		path = new File(".");
		if(afn == null)
			list = path.list();
		else
			list = path.list(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					String f = new File(name).getName();
					return f.indexOf(afn) != -1;
				}
			});
		sort();
	}
	void print() {
		for(int i = 0; i < list.length; i++)
			System.out.println(list[i]);
	}
	private void sort() {
		StrSortVector sv = new StrSortVector();
		for(int i = 0; i < list.length; i++)
			sv.addElement(list[i]);
		for(int i = 0; i < list.length; i++)
			list[i] = sv.elementAt(i);
	}
	
	public static void main(String[] args) {
		SortedDirList sd;
		if(args.length == 0)
			sd = new SortedDirList(null);
		else
			sd = new SortedDirList(args[0]);
		sd.print();
	}
}
