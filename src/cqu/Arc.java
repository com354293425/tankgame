package cqu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class Arc extends Shape{
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
		g.fillArc(x, y, w, h,0, 360);
		}
		g.setColor(colorPre);
		g.drawArc(x, y, w, h,0, 360);
	}
}