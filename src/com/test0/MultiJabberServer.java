package com.test0;

import java.io.*;
import java.net.*;

class ServeOneJabber extends Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	public ServeOneJabber(Socket socket) throws IOException {
		this.socket = socket;
		in = new BufferedReader(
				new InputStreamReader(
					this.socket.getInputStream()));
		out = new PrintWriter(
				new BufferedWriter(
					new OutputStreamWriter(
						this.socket.getOutputStream())), true);
		start();
	}
	public void run() {
		try{
			while(true) {
				String string = in.readLine();
				if(string.equals("END")) break;
				System.out.println("Echoing" + string);
				out.println(string);
			}
			System.out.println("closing...");
		} catch(IOException e) {}
		finally {
			try {
				socket.close();
			} catch (IOException e) {}
		}
	}
}

public class MultiJabberServer {
	static final int PORT = 8888;
	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("server started");
		try{
			while(true) {
				Socket socket = s.accept();
				try {
					new ServeOneJabber(socket);
				} catch (IOException e) {
					socket.close();
				}
			}
		} finally {
			s.close();
		}
	}
}
