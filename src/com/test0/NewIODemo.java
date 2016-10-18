package com.test0;

import java.io.*;

public class NewIODemo {
	public static void main(String[] args) {
		try {
//			//1.reading input by lines:
//			BufferedReader in = new BufferedReader(new FileReader(args[0]));
//			String s,s2 = new String();
//			while((s = in.readLine()) != null)
//				s2 += s + "\n";
//			in.close();
//			
//			//1b.reading standard input
//			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
//			System.out.print("enter a line");
//			System.out.println(stdin.readLine());
//			
//			//2.input for memory
//			StringReader in2 = new StringReader(s2);
//			int c;
//			while((c = in2.read()) != -1)
//				System.out.println((char)c);
//			in2.close();
//			
//			//3.formatted memory input
//			try {
//				DataInputStream in3 = new DataInputStream(new StringBufferInputStream(s2));
//				while(true)
//					System.out.print((char)in3.readByte());
//			} catch (EOFException e) {
//				System.out.println("end of stream");
//			}
//			
//			//4.Line numbering & file output
//			try {
//				LineNumberReader li = new LineNumberReader(new StringReader(s2));
//				BufferedReader in4 = new BufferedReader(li);
//				PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter("IOdemo.out")));
//				while((s = in4.readLine()) != null)
//					out1.println("Line " + li.getLineNumber() + s);
//				out1.close();
//			} catch (EOFException e) {
//				System.out.println("end of stream");
//			}
			
			//5.storing & recovering data
			try {
				DataOutputStream out2 = new DataOutputStream(
											new BufferedOutputStream(
												new FileOutputStream("data.txt")));
				out2.writeDouble(3.14159);
				out2.writeBytes("that was pi");
				out2.writeBytes("this is a pi/2 \n");
				out2.writeDouble(3.14159/2);
				out2.close();
				DataInputStream in5 = new DataInputStream(
										new BufferedInputStream(
											new FileInputStream("data.txt")));
				BufferedReader in5br = new BufferedReader(new InputStreamReader(in5));
				//must use DataInputStream for data:
				System.out.println(in5.readDouble());
				//can now use the "proper" readline
				System.out.println(in5br.readLine());
				System.out.println(in5br.readLine());
				System.out.println(in5.readDouble());
			} catch (EOFException e) {
				System.out.println("end of stream");
			}
			
			//6.Reading and Writing random access
			//files is the same as before
			//(not repeated here)
		} catch (FileNotFoundException e) {
			System.out.println("can not found: " + args[0]);
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}
}
