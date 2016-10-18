package cqu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rect extends Shape{
	public int x;
	public int y;
	public int w;
	public int h;
	public int px;
	public Color colorPre;
	public Color colorLas;
	public boolean isFill;
	public void draw(Graphics g)
	{
		Graphics2D g2D;
		g2D = (Graphics2D)g;
		g2D.setStroke(new BasicStroke(px, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		if(isFill==true)
		{
		g.setColor(colorLas);
		g.fillRect(x, y, w, h);
		}
		g.setColor(colorPre);
		g.drawRect(x, y, w, h);
	}
}
