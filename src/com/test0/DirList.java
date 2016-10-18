package com.test0;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class DirList {
	public static FilenameFilter filter(final String afn) {
		return new FilenameFilter() {
			String fn = afn;
			public boolean accept(File dir, String name) {
				String s = new File(name).getName();
				return s.indexOf(fn) != -1;
			}
		};
	}
	
	public static void main(final String[] args) {
		try {
			File path = new File(".");
			String[] list;
			if(args.length == 0)
				list = path.list();
			else
//				list = path.list(new DirFilter(args[0]));
//				list = path.list(filter(args[0]));
				list = path.list(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						String f = new File(name).getName();
						return f.indexOf(args[0]) != -1;
					}
				});
			for(int i = 0; i < list.length; i++) 
				System.out.println(list[i]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		File file = new File("H:\\test");
//		if(file.isDirectory()){
//			String[] filename = file.list();
//			Arrays.sort(filename,new StringComparator());
//			for(int i = 0; i < filename.length; i++)
//				System.out.println(filename[i]);
//		}
	}
}

class StringComparator implements Comparator<String> {

	public int compare(String o1, String o2) {
		if(StringComparator.returnDouble(o1) < StringComparator.returnDouble(o2))
			return -1;
		else if(StringComparator.returnDouble(o1) > StringComparator.returnDouble(o2))
			return 1;
		else
			return 0;
	}
	
	public static double returnDouble(String str) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < str.length(); i++) {
			if(Character.isDigit(str.charAt(i)))
				sb.append(str.charAt(i));
			else if(str.charAt(i) == '.' && i < str.length() - 1 && Character.isDigit(str.charAt(i+1)))
				sb.append(str.charAt(i));
			else break;
		}
		if(str.toString().isEmpty())
			return 0;
		else
			return Double.parseDouble(sb.toString());
	}
	
}

class DirFilter implements FilenameFilter {
	String afn;
	DirFilter(String safn) { afn = safn; }
	public boolean accept(File dir, String name) {
		String fString = new File(name).getName();
		return fString.indexOf(afn) != -1;
	}
	
}
