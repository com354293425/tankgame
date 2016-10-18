package com.test0;

import java.io.*;
import java.net.*;

class JabberClientThread extends Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private static int counter = 0;
	private int id = counter++;
	private static int threadcount = 0;
	public static int threadCount() {
		return threadcount;
	}
	public JabberClientThread(InetAddress addr) {
		System.out.println("Making client " + id);
		threadcount++;
		try {
			socket = new Socket(addr, MultiJabberServer.PORT);
		} catch (IOException e) {
		}
		try {
			in = new BufferedReader(
					new InputStreamReader(
						socket.getInputStream()));
			out = new PrintWriter(
					new BufferedWriter(
						new OutputStreamWriter(
							socket.getOutputStream())));
			start();
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	public void run() {
		try {
			for(int i = 0; i < 25; i++) {
				out.println("Client " + id + ": " + i);
				String string = in.readLine();
				System.out.println(string);
			}
			out.println("END");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			threadcount--;
		}
	}
}

public class MultiJabberClient {
	static final int MAX_THREADS = 40;
	public static void main(String[] args) throws IOException, InterruptedException {
		InetAddress addr = InetAddress.getByName(null);
		while(true) {
			if(JabberClientThread.threadCount() < MAX_THREADS)
				new JabberClientThread(addr);
			Thread.currentThread().sleep(1000);
		}
	}
}
