package njine;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class RendererThread extends Thread
{
	private Renderer renderer;
	private RenderSurface surface;
	private JFrame window;
	
	
	public RendererThread(Renderer renderer, RenderSurface surface, JFrame window)
	{
		this.renderer = renderer;
		this.surface = surface;
		this.window = window;
	}
	
	public void run()
	{
		while(window.isActive())
		{
			BufferedImage framebuffer = renderer.render();
			surface.setFramebuffer(framebuffer);
			window.repaint();
		}
	}
}
