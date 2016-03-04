package ngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class Rasterizer extends Renderer
{
	
	private Vec2 p1, p2, p3;
	
	private Vec3 pos;
	private Matrix44 cameraToWorld;
	private Matrix44 worldToCamera;
	
	private Vec3[] verts;
	private int[] tris;
	private int numTris;
	
	private Graphics gg;
	
	private int winHeight, winWidth;
	
	private double b,l,t,r, near, far, focalLength, filmApertureWidth, filmApertureHeight;
	
	private final double inchToMm = 25.4;
	
	private Vec3[][] framebuffer;
	private int[][] depthbuffer;
	
	public void setup(Vec3 camPos, int width, int height, double nearClip, double farClip, int  fl, double faw, double fah, fitFilm ff)
	{
		setSize(width, height);
		setVisible(true);
		
		pos = camPos;
        
		winHeight = height;
		winWidth = width;
		
		gg = getGraphics();
		
		createWorldToCamera();
		createCameraToWorld();
		
		near = nearClip;
		far = farClip;
		focalLength = 35;
		filmApertureWidth = faw;
		filmApertureHeight = fah; 
		
		framebuffer = new Vec3[width][height];
		depthbuffer = new int[width][height];
		for(int[] i: depthbuffer)
			for(int j: i)
				j = Integer.MAX_VALUE;
		
		setupBounds(ff);
	}
	
	public void setupDefault()
	{
		winHeight = 512;
		winWidth = 512;
		setSize(winWidth, winHeight);
		setVisible(true);
        
		
		gg = getGraphics();
		
		createWorldToCamera();
		createCameraToWorld();
		
		near = 0.1;
		far = 1000;
		focalLength = 35;
		filmApertureWidth = 0.825;
		filmApertureHeight = 0.446; 
		
		setupBounds(fitFilm.kFILL);
		
	}
	
	
	private void setupBounds(fitFilm f)
	{
		double filmAspectRatio = filmApertureWidth / filmApertureHeight; 
	    double deviceAspectRatio = winWidth / (double)winHeight; 
	 
	    t = ((filmApertureHeight * inchToMm / 2) / focalLength) * near; 
	    r = ((filmApertureWidth * inchToMm / 2) / focalLength) * near; 
	 
	    double xscale = 1; 
	    double yscale = 1; 
	 
	    switch (f) { 
	        default: 
	        case kFILL: 
	            if (filmAspectRatio > deviceAspectRatio) { 
	                xscale = deviceAspectRatio / filmAspectRatio; 
	            } 
	            else { 
	                yscale = filmAspectRatio / deviceAspectRatio; 
	            } 
	            break; 
	        case kOVERSCAN: 
	            if (filmAspectRatio > deviceAspectRatio) { 
	                yscale = filmAspectRatio / deviceAspectRatio; 
	            } 
	            else { 
	                xscale = deviceAspectRatio / filmAspectRatio; 
	            } 
	            break; 
	    } 
	 
	    r *= xscale; 
	    t *= yscale; 
	 
	    b = -t; 
	    l = -r;
	}
	
	
	// these functions make the camera face forward - just adds the values to the points
	private void createWorldToCamera()
	{
		worldToCamera = new Matrix44(1, 0, 0, 0,
									 0, 1, 0, 0, 
									 0, 0, 1, 0, 
									 pos.x, pos.y, pos.z, 1);
	}
	
	// doesn't work
	private void createCameraToWorld()
	{
		cameraToWorld = new Matrix44(1, 0, 0, -pos.x, 0, 1, 0, -pos.y, 0, 0, 1, -pos.z, 0, 0, 0, 1);
	}
	
	public void render(Entity es[])
	{
		gg = getGraphics();
		for(int e = 0; e < es.length; e++)
		{
			//System.out.println();
			verts = es[e].mesh.getVerts();
			tris = es[e].mesh.getTris();
			numTris = es[e].mesh.getNumTris();
			
			for (int i = 0; i<numTris; i++)
			{
				p1 = computePoint(verts[tris[i*3]]);
				p2 = computePoint(verts[tris[i*3+1]]);
				p3 = computePoint(verts[tris[i*3+2]]);
				
				if(i%2 == 1) gg.setColor(Color.LIGHT_GRAY);
				else gg.setColor(Color.DARK_GRAY);
				
				int xps[] = {(int)p1.x, (int)p2.x, (int)p3.x};
				int yps[] = {(int)p1.y, (int)p2.y, (int)p3.y};
				gg.fillPolygon(xps, yps, 3);
				//System.out.println("filled a polygon");
			}
		}
	}
	
	
	private int edgeFunction(Vec2 a, Vec2 b, Vec2 c)
	{
		return (int)((b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x));
	}
	
	private Vec2 computePoint(Vec3 pWorld)
	{
		Vec3 pCamera = new Vec3();
		pCamera = worldToCamera.multVecMatrix(pWorld);
		
		Vec2 pScreen = new Vec2();
		pScreen.x = pCamera.x/-pCamera.z*near;
		pScreen.y = -pCamera.y/-pCamera.z*near;
		
		Vec2 pNDC = new Vec2();
		pNDC.x = (pScreen.x+r)/(2*r);
		pNDC.y = (pScreen.y+t)/(2*t);
		
		Vec2 pRaster = new Vec2();
		pRaster.x = pNDC.x*winWidth;
		pRaster.y = (1-pNDC.y)*winHeight;
		
		return pRaster;
	}
	
}
