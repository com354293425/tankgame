package com.test0;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

class SeparateSubTask extends Thread {
	private int count = 0; 
	private Counter1 c1;
	private boolean runFlag = true;
	public SeparateSubTask(Counter1 counter1) {
		c1 = counter1;
		start();
	}
	public void invertFlag() { runFlag = !runFlag; }
	public void run() {
		while(true) {
			try {
				sleep(100);
			} catch (InterruptedException e) {}
			if(runFlag)
				c1.t.setText(Integer.toString(count++));
		}
	}
}

public class Counter1 extends Applet{
	private Button
		onOff = new Button("Toggle"),
		start = new Button("Start");
	TextField t = new TextField(10);
	private SeparateSubTask sp = null;
	public void init() {
		add(t);
		start.addActionListener(new StartL());
		add(start);
		onOff.addActionListener(new Onoffl());
		add(onOff);
	}

	class StartL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(sp == null)
				sp = new SeparateSubTask(Counter1.this);
		}
	}
	
	class Onoffl implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(sp != null)
				sp.invertFlag();
		}
	}
	
	public static void main(String[] args) {
		Counter1 applet = new Counter1();
		Frame aFrame = new Frame("Counter1");
		aFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		aFrame.add(applet, BorderLayout.CENTER);
		aFrame.setSize(200, 300);
		applet.init();
		applet.start();
		applet.setVisible(true);
	}
}
