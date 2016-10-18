package com.test0;

import java.io.IOException;
import java.net.*;

public class ChatterServer {
	static final int INPORT = 1711;
	private byte[] buf = new byte[1000];
	private DatagramPacket dp = new DatagramPacket(buf, buf.length);
	private DatagramSocket socket;
	
	public ChatterServer() {
		try {
			socket = new DatagramSocket(INPORT);
			System.out.println("Server started");
			while(true) {
				socket.receive(dp);
				String rcvd = Dgram.toString(dp) +
								", from address: " + dp.getAddress() +
								" , port: " + dp.getPort();
				System.out.println(rcvd);
				String echoStirng = "Echoed: " + rcvd;
				DatagramPacket echo = Dgram.toDatagram(echoStirng, dp.getAddress(), dp.getPort());
				socket.send(echo);
			}
		} catch (SocketException e) {
			System.err.println("can't open socket");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Communication error");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ChatterServer();
	}
}
