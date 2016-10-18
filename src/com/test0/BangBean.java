package com.test0;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class BangBean extends Canvas implements Serializable{
	private int xm, ym;
	private int cSize = 20; //circle size
	private String text = "Bang!";
	private int fontSize = 48;
	private Color tColor = Color.red;
	private Vector actionListeners = new Vector();
	public BangBean() {
		addMouseListener(new ML());
		addMouseMotionListener(new MM());
	}
	public synchronized int getCircleSize() {
		return cSize;
	}
	public synchronized void setCircleSize(int newsize) {
		cSize = newsize;
	}
	public synchronized String getBangText() {
		return text;
	}
	public synchronized void setBangText(String newText) {
		text = newText;
	}
	public synchronized int getFontSize() {
		return fontSize;
	}
	public synchronized void setFontSize(int newSize) {
		fontSize = newSize;
	}
	public synchronized Color getTextColor() {
		return tColor;
	}
	public synchronized void setTextColor(Color newColor) {
		tColor = newColor;
	}
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawOval(xm - cSize/2, ym - cSize/2, cSize, cSize);
	}
	public synchronized void addActionListener(ActionListener l) {
		actionListeners.addElement(l);
	}
	public synchronized void removeActionListener(ActionListener l) {
		actionListeners.removeElement(l);
	}
	public void notifyListeners() {
		ActionEvent a = new ActionEvent(BangBean.this, ActionEvent.ACTION_PERFORMED, null);
		Vector v = null;
		synchronized (this) {
			v = (Vector)actionListeners.clone();
		}
		for(int i = 0; i < v.size(); i++) {
			ActionListener al = (ActionListener)v.elementAt(i);
			al.actionPerformed(a);
		}
	}
	class ML extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			Graphics g = getGraphics();
			g.setColor(tColor);
			g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
			int width = g.getFontMetrics().stringWidth(text);
			g.drawString(text, (getSize().width - width)/2, getSize().height/2);
			g.dispose();
			notifyListeners();
		}
	}
	class MM extends MouseMotionAdapter {
		public void mouseMoved(MouseEvent e) {
			xm = e.getX();
			ym = e.getY();
			repaint();
		}
	}
	public static void main(String[] args) {
		BangBean bb = new BangBean();
		bb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ActionEvent" + e);
			}
		});
		bb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("bangbean action");
			}
		});
		bb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("More action");
			}
		});
		Frame aFrame = new Frame("bangbean");
		aFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		aFrame.add(bb, BorderLayout.CENTER);
		aFrame.setSize(300, 200);
		aFrame.setVisible(true);
	}
}
