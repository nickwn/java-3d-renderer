import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;


public class Camera extends JFrame implements ActionListener
{
	
	private Vec2 p1, p2, p3;
	
	private Vec3 pos;
	private Matrix44 camToWorld;
	private Matrix44 worldToCamera;
	
	private Vec3[] verts;
	private int[] tris;
	private int numTris;
	
	private Graphics gg;
	
	private int winHeight, winWidth, canvasHeight, canvasWidth;
	
	public Camera(String name, Vec3 camPos) 
	{
		super(name); 
		pos = camPos;
    }
	
	public void setup(String name, int width, int height, int cw, int ch)
	{
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
		
		setSize(width, height);
		setVisible(true);
        
		winHeight = height;
		winWidth = width;
		canvasHeight = ch;
		canvasWidth = cw;
		
		gg = getGraphics();
		
		createWorldToCamera();
		createCameraToWorld();
	}
	
	public void setupDefault()
	{
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
		
		setSize(512, 512);
		setVisible(true);
        
		winHeight = 512;
		winWidth = 512;
		canvasHeight = 2;
		canvasWidth = 2;
		
		gg = getGraphics();
	}
	
	
	// these functions make the camera point towards the origin
	private void createWorldToCamera()
	{
		worldToCamera = new Matrix44(1, 0, 0, pos.x, 0, 1, 0, pos.y, 0, 0, 1, pos.z, 0, 0, 0, 1);
	}
	
	private void createCameraToWorld()
	{
		worldToCamera = new Matrix44(1, 0, 0, -pos.x, 0, 1, 0, -pos.y, 0, 0, 1, -pos.z, 0, 0, 0, 1);
	}
	
	public void render(Entity es[])
	{
		verts = es[0].mesh.getVerts();
		tris = es[0].mesh.getTris();
		numTris = es[0].mesh.getNumTris();
		
		for (int i = 0; i<numTris; i++)
		{
			p1 = computePoint(verts[tris[i*3]]);
			p2 = computePoint(verts[tris[i*3+1]]);
			p3 = computePoint(verts[tris[i*3+2]]);

			gg.drawLine((int)p1.x, (int)p1.y,(int)p2.x, (int)p2.y);
			gg.drawLine((int)p1.x, (int)p1.y,(int)p3.x, (int)p3.y);
			gg.drawLine((int)p2.x, (int)p2.y,(int)p3.x, (int)p3.y);
		}
	}
	
	public Vec2 computePoint(Vec3 pCamera)
	{
		
		Vec2 pScreen = new Vec2();
		pScreen.x = pCamera.x/-pCamera.z;
		pScreen.y = pCamera.y/-pCamera.z;
		
		Vec2 pNDC = new Vec2();
		pNDC.x = (pScreen.x+canvasWidth*0.5)/canvasWidth;
		pNDC.y = (pScreen.y+canvasHeight*0.5)/canvasWidth;
		
		Vec2 pRaster = new Vec2();
		pRaster.x = pNDC.x*winWidth;
		pRaster.y = (1-pNDC.y)*winHeight;
		
		return pRaster;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
}
