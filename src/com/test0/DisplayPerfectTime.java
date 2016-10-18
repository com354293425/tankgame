package com.test0;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class DisplayPerfectTime {
	public static void main(String[] args) {
		System.setSecurityManager(new RMISecurityManager());
		try {
			PerfectTimeI t = (PerfectTimeI)Naming.lookup("//colossus:2005/PerfectTime");
			for(int i = 0; i < 10; i++)
				System.out.println("Perfect time = " + t.getPerfecTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
