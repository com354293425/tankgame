package cqu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Line extends Shape{
	public Point a;
	public Point b;
	public Color color;
	public int px;
	public void draw(Graphics g)
	{
		Graphics2D g2D;
		g2D = (Graphics2D)g;
		g2D.setStroke(new BasicStroke(px, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		g.setColor(color);
		g.drawLine(a.x, a.y, b.x, b.y);
	}
}