package com.test0;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PerfectTimeI extends Remote{
	long getPerfecTime() throws RemoteException;
}
