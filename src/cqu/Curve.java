package cqu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Curve extends Shape{
	public int[] x;
	public int[] y;
	public int count;
	public Color color;
	public int px;
	public void draw(Graphics g)
	{
		Graphics2D g2D;
		g2D = (Graphics2D)g;
		g2D.setStroke(new BasicStroke(px, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		g.setColor(color);
		g.drawPolyline(x, y, count);
	}
}
