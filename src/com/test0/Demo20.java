package com.test0;

import java.io.*;

public class Demo20 extends DataInputStream{
	public Demo20(String filename) throws FileNotFoundException{
		super(new BufferedInputStream(new FileInputStream(filename)));
	}
	public Demo20(File file) throws FileNotFoundException {
		this(file.getPath());
	}

	public static void main(String[] args) {
//		try {
//			//1.buffered input file
//			DataInputStream in = new DataInputStream(new BufferedInputStream(
//									new FileInputStream(args[0])));
//			String s,s2 = new String();
//			while((s = in.readLine()) != null)
//				s2 += s + "\n";
//			in.close();
//			
//			//2.input from memory
//			StringBufferInputStream in2 = new StringBufferInputStream(s2);
//			int c;
//			while((c = in2.read()) != -1)
//				System.out.println((char)c);
//			
//			//3.formatted memory input
//			try {
//				DataInputStream in3 = new DataInputStream(new StringBufferInputStream(s2));
//				while(true)
//					System.out.println((char)in3.readByte());
//			} catch (EOFException e) {
//				System.out.println("End of stream encountered");
//			}
//			
//			//4.Line numbering & file output
//			try {
//				LineNumberInputStream li = new LineNumberInputStream(new StringBufferInputStream(s2));
//				DataInputStream in4 = new DataInputStream(li);
//				PrintStream out1 = new PrintStream(new BufferedOutputStream(
//										new FileOutputStream("IODemo.out")));
//				while((s = in4.readLine()) != null)
//					out1.println("Line " + li.getLineNumber() + s);
//				out1.close();
//			} catch (EOFException e) {
//				System.out.println("End of stream encountered");
//			}
//			
//			//5.storing & recovering data
//			try {
//				DataOutputStream out2 = new DataOutputStream(new BufferedOutputStream(
//											new FileOutputStream("Data.txt")));
//				out2.writeBytes("Here's the value of pi: \n");
//				out2.writeDouble(3.14159);
//				out2.close();
//				DataInputStream in5 = new DataInputStream(new BufferedInputStream(
//										new FileInputStream("Data.txt")));
//				System.out.println(in5.readLine());
//				System.out.println(in5.readDouble());
//			} catch (EOFException e) {
//				System.out.println("End of stream encountered");
//			}
//			
//			//6.Reading/writing random access files
//			RandomAccessFile rf = new RandomAccessFile("rtest.dat", "rw");
//			for(int i = 0; i < 10; i++)
//				rf.writeDouble(i * 1.414);
//			rf.close();
//			
//			rf = new RandomAccessFile("rtest.dat", "rw");
//			rf.seek(5 * 8);
//			rf.writeDouble(47.0001);
//			rf.close();
//			
//			rf = new RandomAccessFile("rtest.dat", "r");
//			for(int i = 0; i < 10; i++)
//				System.out.println("Value " + i + ": " + rf.readDouble());
//			rf.close();
//			
//			//7.File input shorthand
//			
//			
//			//8.Formatted file output shorthand
//			
//			
//			//9.Data file output shorthand
//			
//		} catch (FileNotFoundException e) {
//			System.out.println("file not found: " + args[0]);
//		} catch (IOException e) {
//			System.out.println("IO Exception");
//		}
		
//		try {
//			DataInputStream in = new DataInputStream(
//									new BufferedInputStream(new FileInputStream("Demo.java")));
//			while(in.available() != 0)
//				System.out.println((char)in.readByte());
//			in.close();
//		} catch (IOException e) {
//			System.err.println("ioexception");
//		}
		
		DataInputStream in = new DataInputStream(new BufferedInputStream(System.in));
		String string;
		try {
			while((string = in.readLine()).length() != 0) 
				System.out.println(string);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
