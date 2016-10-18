package com.test0;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class Blockable extends Thread {
	private Peeker peeker;
	protected TextField state = new TextField(40);
	protected int i;
	public Blockable(Container c) {
		c.add(state);
		peeker = new Peeker(this, c);
	}
	public synchronized int read() { return i; }
	protected synchronized void update() {
		state.setText(getClass().getName() + " status: i = " + i);
	}
	public void stopPeeker() {
		//peeker.stop(); //Deprecated in Java 1.2
		peeker.terminate();
	}
}

class Peeker extends Thread {
	private Blockable b;
	private int session;
	private TextField status = new TextField(40);
	private boolean stop = false;
	public Peeker(Blockable b, Container c) {
		c.add(status);
		this.b = b;
		start();
	}
	public void terminate() { stop = true; }
	public void run() {
		while(!stop) {
			status.setText(b.getClass().getName() + " Peeker " + (++session) + ";values: " + b.read());
			try {
				sleep(100);
			} catch (InterruptedException e) {}
		}
	}
}

class Sleeper1 extends Blockable {
	public Sleeper1(Container c) { super(c); }
	public synchronized void run() {
		while(true) {
			i++;
			update();
			try {
				sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
}

class Sleeper2 extends Blockable {
	public Sleeper2(Container c) { super(c); }
	public void run() {
		while(true) {
			change();
			try {
				sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
	public synchronized void change() {
		i++;
		update();
	}
}

class SuspendResume extends Blockable {
	public SuspendResume(Container c) {
		super(c);
		new Resumer(this);
	}
}

class SuspendResume1 extends Blockable {
	public SuspendResume1(Container c) { super(c); }
	public synchronized void run() {
		while(true) {
			i++;
			update();
			suspend();		// Deprecated in Java 1.2
		}
	}
}

class SuspendResume2 extends Blockable {
	public SuspendResume2(Container c) { super(c); }
	public synchronized void chang() {
		i++;
		update();
	}
	public void run() {
		while(true) {
			chang();
			suspend();
		}
	}
}

class Resumer extends Thread {
	private SuspendResume sr;
	public Resumer(SuspendResume sr) {
		this.sr = sr;
		start();
	}
	public void run() {
		while(true) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {}
			sr.resume();		// Deprecated in Java 1.2
		}
	}
}

class WaitNotify1 extends Blockable {
	public WaitNotify1(Container c) { super(c); }
	public synchronized void run() {
		while(true) {
			i++;
			update();
			try {
				wait(1000);
			} catch (InterruptedException e) {}
		}
	}
}

class WaitNotify2 extends Blockable {
	public WaitNotify2(Container c) {
		super(c);
		new Notifier(this);
	}
	public synchronized void run() {
		while(true) {
			i++;
			update();
			try {
				wait();
			} catch (InterruptedException e) {}
		}
	}
}

class Notifier extends Thread {
	WaitNotify2 wn2;
	public Notifier(WaitNotify2 wn2) {
		this.wn2 = wn2;
		start();
	}
	public void run() {
		while(true) {
			try {
				sleep(2000);
			} catch (InterruptedException e) {}
			synchronized (wn2) {
				wn2.notify();
			}
		}
	}
}

class Sender extends Blockable {
	private Writer out;
	public Sender(Container c, Writer out) {
		super(c);
		this.out = out;
	}
	public void run() {
		while(true) {
			for(char c = 'A'; c < 'z'; c++) {
				i++;
				try {
					out.write(c);
					state.setText("Sender sent " + (char)c);
					sleep((int)(3000 * Math.random()));
				} catch (IOException e) {} catch (InterruptedException e) {}
			}
		}
	}
}

class Receiver extends Blockable {
	private Reader in;
	public Receiver(Container c, Reader in) {
		super(c);
		this.in = in;
	}
	public void run() {
		while(true) {
			i++;	// Show peeker it's alive
			try {
				state.setText("Receiver read: " + (char)in.read());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

public class Blocking extends Applet{
	private Button
		start = new Button("start"),
		stopPeekers = new Button("Stop Peekers");
	private boolean started = false;
	private Blockable[] b;
	private PipedReader in;
	private PipedWriter out;
	public void init() {
		out = new PipedWriter();
		try {
			in = new PipedReader(out);
		} catch (IOException e) {}
		b = new Blockable[] {
			new Sleeper1(this),
			new Sleeper2(this),
			new SuspendResume1(this),
			new SuspendResume2(this),
			new WaitNotify1(this),
			new WaitNotify2(this),
			new Sender(this, out),
			new Receiver(this, in)
		};
		start.addActionListener(new StartL());
		add(start);
		stopPeekers.addActionListener(new StopPeekersL());
		add(stopPeekers);
		
	}
	class StartL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(!started) {
				started = true;
				for(int i = 0; i < b.length; i++)
					b[i].start();
			}
		}
	}
	class StopPeekersL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < b.length; i++)
				b[i].stopPeeker();
		}
	}
	public static void main(String[] args) {
		Blocking applet = new Blocking();
		Frame aFrame = new Frame("Blocking");
		aFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		aFrame.add(applet, BorderLayout.CENTER);
		aFrame.setSize(350, 350);
		applet.init();
		applet.start();
		aFrame.setVisible(true);
	}
}
