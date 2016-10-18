package com.test0;

import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Demo21 extends Applet implements Runnable{
	private int count = 0;
	private boolean runFlag = true;
	private Thread selfThread = null;
	private Button startb = new Button("start"),endb = new Button("end");
	private TextField t = new TextField(10);
	public void init() {
		add(t);
		startb.addActionListener(new StartL());
		add(startb);
		endb.addActionListener(new EndL());
		add(endb);
	}
	class StartL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(selfThread == null) {
				selfThread = new Thread(Demo21.this);
				selfThread.start();
			}
		}
	}
	class EndL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			runFlag = !runFlag;
		}
	}
	
	public static void main(String[] args) {
		Demo21 demo21 = new Demo21();
		Frame aFrame = new Frame("ad");
		aFrame.addWindowListener(new WindowAdapter() {
			public void windClosing(WindowEvent e){
				System.exit(0);
			}
		});
		aFrame.add(demo21, BorderLayout.CENTER);
		aFrame.setSize(200, 300);
		demo21.init();
		demo21.start();
		aFrame.setVisible(true);
	}

	public void run() {
		while(true) {
			try {
				selfThread.sleep(100);
			} catch (InterruptedException e) {}
			if(runFlag) t.setText(Integer.toString(count++));
		}
	}
}
