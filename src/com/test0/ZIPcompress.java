package com.test0;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

public class ZIPcompress {
	public static void main(String[] args) {
		try {
			FileOutputStream f = new FileOutputStream("test.zip");
			CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(csum));
			out.setComment("A test of Java Zipping");
			//can't read the above comment, though
			for(int i = 0; i < args.length; i++) {
				System.out.println("writing file" + args[i]);
				BufferedReader in = new BufferedReader(new FileReader(args[i]));
				out.putNextEntry(new ZipEntry(args[i]));
				int c;
				while((c = in.read()) != -1)
					out.write(c);
				in.close();
			}
			out.close();
			//checksum valid only after the file has been closed
			System.out.println("checksum: " + csum.getChecksum().getValue());
			//now extract the files
			System.out.println("reading file");
			FileInputStream fi = new FileInputStream("test.zip");
			CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
			ZipInputStream in2 = new ZipInputStream(new BufferedInputStream(csumi));
			ZipEntry ze;
			System.out.println("checksum: " + csumi.getChecksum().getValue());
			while((ze = in2.getNextEntry()) != null) {
				System.out.println("reading file " + ze);
				int x;
				while((x = in2.read()) != -1)
					System.out.println(x);
			}
			in2.close();
			//alternative way to open and read zip files:
			ZipFile zf = new ZipFile("test.zip");
			Enumeration e = zf.entries();
			while(e.hasMoreElements()) {
				ZipEntry ze2 = (ZipEntry)e.nextElement();
				System.out.println("files: " + ze2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
