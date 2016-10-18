package com.test0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Classs {
	static String ss = "link";
	public static void traverseFloder(String path) {
		File file = new File(path);
		if(file.exists()) {
			File[] files = file.listFiles();
			if(files.length == 0)
				return;
			else {
				for(File file2 : files) {
					if(file2.isDirectory()) 
						traverseFloder(file2.getAbsolutePath()); else 
						readFile(file2);
				}
			}
		}
	}
	
	public static void readFile(File file) {
		BufferedReader b = null;
		try {
			b = new BufferedReader(
					new InputStreamReader(
						new FileInputStream(file)));
			String string = null;
			while(true) {
				string = b.readLine();
				if(string != null) {
					int i = string.indexOf(ss);
					if(i != -1) 
						System.out.println("找到了" + file.getAbsolutePath());
				} else 
					break;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				b.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		traverseFloder("D:/OA/");
//		String str = "1,2,3,4,5";
//		String[] strings = str.split(",");
//		int sum = 0;
//		int[] is = new int[strings.length];
//		for (int i = 0; i < strings.length; i++) {
//			is[i] = Integer.parseInt(strings[i]);
//			sum += is[i];
//		}
//		System.out.println(sum);
	}
}
