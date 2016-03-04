package ngine;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Camera extends Frame implements ActionListener
{
	private Renderer renderer[];
	private int i;
	
	public Camera(String name, RenderMethod rm)
	{
		super(name);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
		
		i = 0;
		
		switch (rm)
		{
		case RASTERIZE:
			renderer = new Renderer[]{new Rasterizer(), new Rasterizer()};
			System.out.println("Creating rasterizer...");
			break;
			
		case RAYTRACE:
			renderer = new Renderer[]{new Raytracer(), new Raytracer()};
			System.out.println("Creating raytracer...");
			break;
		}
		
	}
	
	public void setup(Vec3 camPos, int width, int height, double nearClip, double farClip, int  fl, double faw, double fah, fitFilm ff)
	{
		renderer[0].setup(camPos, width, height, nearClip, farClip, fl, faw, fah, ff);
		renderer[1].setup(camPos, width, height, nearClip, farClip, fl, faw, fah, ff);
		setVisible(true);
		setSize(width, height);
		add(renderer[0]);
		add(renderer[1]);
	}
	
	public void setupDefault(Vec3 camPos)
	{
		setup(camPos, 512, 512, 0.1, 1000, 35, 0.825, 0.446, fitFilm.kFILL);
	}
	
	public void render(ArrayList<Entity> ents)
	{
		Entity[] es = ents.toArray(new Entity[ents.size()]);
		//System.out.println(es.length);
		renderer[i].setVisible(true);
		i = (i == 0)?1:0;
		renderer[i].setVisible(true);
		renderer[i].render(es);
	}
	
	public void repaintCanvas()
	{
		renderer[(i == 0)?0:1].repaint();
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
}