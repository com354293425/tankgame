package com.test0;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

class CBox extends Canvas{
	private static final Color[] colors = {
		Color.black, Color.blue, Color.cyan,
		Color.darkGray, Color.gray, Color.green,
		Color.lightGray, Color.magenta,
		Color.orange, Color.pink, Color.red,
		Color.white, Color.yellow
	};
	private Color cColor = newColor();
	private static final Color newColor() {
		return colors[(int)(Math.random() * colors.length)];
	}
	void nextColor() {
		cColor = newColor();
		repaint();
	}
	public void paint(Graphics g) {
		g.setColor(cColor);
		Dimension  s = getSize();
		g.fillRect(0, 0, s.width, s.height);
	}
}

class CBoxVector extends Vector implements Runnable {
	private Thread t;
	private int pause;
	public CBoxVector(int pause) {
		this.pause = pause;
		t = new Thread(this);
	}
	public void go() { t.start(); }
	public void run() {
		while(true) {
			int i = (int)(Math.random() * size());
			((CBox)elementAt(i)).nextColor();
			try {
				t.sleep(pause);
			} catch (InterruptedException e) {}
		}
	}
}

public class ColorBoxes extends Frame{
	private CBoxVector[] v;
	public ColorBoxes(int pause, int grid) {
		setTitle("ColorBoxes");
		setLayout(new GridLayout(grid, grid));
		v = new CBoxVector[grid];
		for(int i = 0; i < grid; i++)
			v[i] = new CBoxVector(pause);
		for(int i = 0; i < grid * grid; i++) {
			v[i % grid].addElement(new CBox());
			add((CBox)v[i % grid].lastElement());
		}
		for(int i = 0; i < grid; i++)
			v[i].go();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	public static void main(String[] args) {
		int pause = 50;
		int grid = 8;
		if(args.length > 0)
			pause = Integer.parseInt(args[0]);
		if(args.length > 1)
			pause = Integer.parseInt(args[1]);
		Frame f = new ColorBoxes(pause, grid);
		f.setSize(500, 400);
		f.setVisible(true);
	}
}
