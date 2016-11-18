package njine.render;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class RenderSurface extends JPanel
{
	private BufferedImage framebuffer;
	
	public void paint(Graphics g)
	{
		g.drawImage(framebuffer, 0, 0, null);
	}
	
	public void setFramebuffer(BufferedImage fb)
	{
		framebuffer = fb;
	}
}
