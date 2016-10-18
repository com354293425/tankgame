package com.test0;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Ttest extends Canvas implements Runnable {
	private Thread t;
	private int pause;
	private static final Color[] colors = {
		Color.black, Color.blue, Color.cyan,
		Color.darkGray, Color.gray, Color.green,
		Color.lightGray, Color.magenta,
		Color.orange, Color.pink, Color.red,
		Color.white, Color.yellow
	};
	private Color cColor = newColor();
	public Color newColor() {
		return colors[(int)(Math.random() * colors.length)];
	}
	public void paint(Graphics g) {
		g.setColor(cColor);
		Dimension s = getSize();
		g.fillRect(0, 0, s.width, s.height);
	}
	public Ttest(int pause) {
		this.pause = pause;
		t = new Thread(this);
		t.start();
	}
	public void run() {
		while(true) {
			cColor = newColor();
			repaint();
			try {
				t.sleep(pause);
			} catch (InterruptedException e) {}
		}
	}
}

public class Demo23 extends Frame{
	public Demo23(int pause, int grid) {
		setTitle("test");
		setLayout(new GridLayout(grid, grid));
		for(int i = 0; i < grid * grid; i++)
			add(new Ttest(pause));
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			System.exit(0);
			}
		});
	}
	public static void main(String[] args) {
		int pause = 500;
		int grid = 10;
		Frame demo23 = new Demo23(pause, grid);
		demo23.setSize(600, 500);
		demo23.setVisible(true);
	}
}
