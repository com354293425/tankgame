package com.test0;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

class Ticker extends Thread {
	private Button button = new Button("Toggle");
	private TextField t = new TextField(10);
	private int count = 0;
	private boolean runFlag = true;
	public Ticker(Container a) {
		button.addActionListener(new Toggle());
		Panel p = new Panel();
		p.add(t);
		p.add(button);
		a.add(p);
	}
	class Toggle implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			runFlag = !runFlag;
		}
	}
	public void run() {
		while(true) {
			if(runFlag)
				t.setText(Integer.toString(count++));
			try {
				sleep(100);
			} catch (InterruptedException e) {}
		}
	}
}

public class Counter4 extends Applet{
	private Button start = new Button("start");
	private boolean started = false;
	private Ticker[] s;
	private boolean isApplet = true;
	private int size;
	public void init() {
		//get parameter "size" from web page
		if(isApplet)
			size = Integer.parseInt(getParameter("size"));
		s = new Ticker[size];
		for(int i = 0; i < s.length; i++)
			s[i] = new Ticker(this);
		start.addActionListener(new StartL());
		add(start);
	}
	class StartL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(!started) {
				started = true;
				for(int i = 0; i < s.length; i++)
					s[i].start();
			}
		}
	}
	public static void main(String[] args) {
		Counter4 applet = new Counter4();
		//this isn't an applet, so set the flag and produce the parameter values from args
		applet.isApplet = false;
		applet.size = (args.length == 0 ? 5 : Integer.parseInt(args[0]));
		Frame aFrame = new Frame("counter4");
		aFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		aFrame.add(applet, BorderLayout.CENTER);
		aFrame.setSize(200, applet.size * 50);
		applet.init();
		applet.start();
		aFrame.setVisible(true);
	}
}
