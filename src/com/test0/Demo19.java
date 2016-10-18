package com.test0;

import java.io.*;

public class Demo19 {
	private final static String usage = 
			"Usage:MakeDirectories path1 ...\n" +
			"Creates each path\n" +
			"Usage:MakeDirectories -d path1 ...\n" +
			"Deletes each path\n" +
			"Usage:MakeDirectories -r path1 path2\n" +
			"Renames from path1 to path2\n";
	private static void usage(){
		System.err.println(usage);
		System.exit(1);
	}
	private static void fileData(File f) {
		System.out.println("Absolute path: " + f.getAbsolutePath() +
							"\n Can read: " + f.canRead() +
							"\n Can write: " + f.canWrite() +
							"\n getName: " + f.getName() +
							"\n getParent: " + f.getParent() +
							"\n getPath: " + f.getPath() +
							"\n length: " + f.length() +
							"\n lastModified: " + f.lastModified());
		if(f.isFile())
			System.out.println("it's a file");
		else if(f.isDirectory())
			System.out.println("it's a directory");
	}
	
	public static void main(String[] args) {
		if(args.length < 1) usage();
		if(args[0].equals("-r")) {
			if(args.length != 3) usage();
			File old = new File(args[1]),rname = new File(args[2]);
			old.renameTo(rname);
			fileData(old);
			fileData(rname);
			return;
		}
		int count = 0; 
		boolean del = false;
		if(args[0].equals("-d")) {
			count++;
			del = true;
		}
		for( ; count < args.length; count++) {
			File file = new File(args[count]);
			if(file.exists()) {
				System.out.println(file + " exists");
				if(del) {
					System.out.println("deleting..." + file);
					file.delete();
				}
			} else {
				if(!del) {
					file.mkdir();
					System.out.println("created.." + file);
				}
			}
			fileData(file);
		}
//		Integer a = 128;
//		Integer a1 = 128;
//		int b = 128;
//		int b1 = 128;
//		Integer c = Integer.valueOf(128);
//		Integer d = new Integer(128);
//		System.out.println("1. " + (a == b));
//		System.out.println(a == a1);
//		System.out.println("2. " + (a == c));
//		System.out.println("3. " + (a.equals(c)));
//		System.out.println("4. " + (a == d));
//		System.out.println("5. " + (b == c));
//		System.out.println(b == b1);
//		System.out.println("6. " + (b == d));
//		System.out.println("7. " + (c == d));
	}
}
