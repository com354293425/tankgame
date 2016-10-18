package com.test0;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PerfectTime extends UnicastRemoteObject implements PerfectTimeI{

	public PerfectTime() throws RemoteException {
		//super();
	}

	public long getPerfecTime() throws RemoteException {
		return System.currentTimeMillis();
	}

	public static void main(String[] args) {
		System.setSecurityManager(new RMISecurityManager());
		try {
			PerfectTime pt = new PerfectTime();
			Naming.bind("//colossus:2005/PerfectTime", pt);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
