package com.test0;

import java.io.*;
import java.net.*;

public class JabberServer {
	public static final int PORT = 8888;
	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("started: " + s);
		try {
			Socket socket = s.accept();
			try {
				System.out.println("Connection accepted: " + socket);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				
				PrintWriter out = new PrintWriter(
						new BufferedWriter(new OutputStreamWriter(
								socket.getOutputStream())), true);
				while(true) {
					String string = in.readLine();
					if(string.equals("END")) break;
					System.out.println("Echoing" + string);
					out.println(string);
				}
			}
			finally {
				System.out.println("server closing...");
				socket.close();
			}
		}
		finally {
			s.close();
		}
	}
}
