package njine.render;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import njine.entity.Mesh;
import njine.math.Mat44;
import njine.math.MatBuilder;
import njine.math.Vec3;


public class Renderer 
{
	private Mat44 projectionMatrix;
	private Mesh worldMesh;
	private int winHeight, winWidth;
	private Mat44 worldToCam;
	
	public void init(int height, int width, Mat44 wToC, double fov, double near, double far)
	{
		if(width%2!=0 || height%2!=0)
			throw new IllegalArgumentException("window height and width need to be even");
		
		worldToCam = wToC;
		projectionMatrix = MatBuilder.projectionMatrix(fov, far, near);
		winHeight = height;
		winWidth = width;
	}
	
	public void setMesh(Mesh m)
	{
		worldMesh = m;
	}
	
	public void setWorldToCam(Mat44 wToC)
	{
		worldToCam = wToC;
	}
	
	public BufferedImage render()
	{
		BufferedImage framebuffer = new BufferedImage(winWidth, winHeight, BufferedImage.TYPE_INT_RGB);
		double[] depthbuffer = new double[winHeight*winWidth];
		for(int i = 0; i < depthbuffer.length; i++)
			depthbuffer[i] = Double.MAX_VALUE;
		//BufferedImage depthbuffer = new BufferedImage(winWidth, winHeight, BufferedImage.TYPE_BYTE_GRAY);
		Graphics fbGraphics = framebuffer.getGraphics();
		//Graphics dbGraphics = depthbuffer.getGraphics();
		//dbGraphics.setColor(Color.WHITE);
		//dbGraphics.fillRect(0, 0, winWidth, winHeight);
		fbGraphics.setColor(Color.WHITE);
		
		if(worldMesh.getTris().length%3 != 0)
			throw new IllegalArgumentException("Invalid vert array length");
		
		Vec3[] vs = worldMesh.getVerts();
		int[] ts = worldMesh.getTris();
		int numTris = worldMesh.getTris().length/3;
		
		for(int i = 0; i < numTris; i++)
		{
			Vec3 p1 = new Vec3(vs[ts[i*3]]);
			Vec3 p2 = new Vec3(vs[ts[i*3+1]]);
			Vec3 p3 = new Vec3(vs[ts[i*3+2]]);
			
			p1 = cvtNDCToRaster(projectionMatrix.multVecMat(worldToCam.multVecMat(p1)));
			p2 = cvtNDCToRaster(projectionMatrix.multVecMat(worldToCam.multVecMat(p2)));
			p3 = cvtNDCToRaster(projectionMatrix.multVecMat(worldToCam.multVecMat(p3)));
			p1.z = 1 / p1.z; 
			p2.z = 1 / p2.z; 
			p3.z = 1 / p3.z;
			
			int maxx = (int)Math.max(p1.x, Math.max(p2.x, p3.x));
			int minx = (int)Math.min(p1.x, Math.min(p2.x, p3.x));
			int maxy = (int)Math.max(p1.y, Math.max(p2.y, p3.y));
			int miny = (int)Math.min(p1.y, Math.min(p2.y, p3.y));
			
			if (!(minx > winWidth-1) && !(maxx < 0) && !(miny > winHeight-1) && !(maxy < 0))
			{
				int x0 = Math.max(0, minx);
				int x1 = Math.min(winWidth-1, maxx);
				int y0 = Math.max(0,  miny);
				int y1 = Math.min(winHeight-1, maxy);
				
				double area = edgeFunction(p1, p2, p3);
				
				for(int y = y0; y <= y1; y++)
				{
					for(int x = x0; x <= x1; x++)
					{
						//if (x > winWidth-1 || x < 0 || y > winHeight-1 || y < 0) break;
						
						double w0 = edgeFunction(p2, p3, new Vec3(x, y, 0));
						double w1 = edgeFunction(p3, p1, new Vec3(x, y, 0));
						double w2 = edgeFunction(p1, p2, new Vec3(x, y, 0));
						
						if((w0 >= 0 && w1 >= 0 && w2 >= 0) || (w0 <= 0 && w1 <= 0 && w2 <= 0))
						{
							w0 /= area;
							w1 /= area;
							w2 /= area;
							
							double oneOverZ = p1.z * w0 + p2.z * w1 + p3.z * w2; 
		                    double z = 1 / oneOverZ; 
		                    //int rgb = depthbuffer.getRGB(x, y);
		                    if(z < depthbuffer[y*winWidth+x])
		                    {
		                    	depthbuffer[y*winWidth+x] = z;
		                    	fbGraphics.drawLine(x, y, x, y);
		                    }
						}
					}
				}
			}
		}
		
		BufferedImage depthbufferImg = new BufferedImage(winWidth, winHeight, BufferedImage.TYPE_INT_RGB);
		for(int y = 0; y < winWidth; y++)
		{
			for(int x = 0; x < winHeight; x++)
			{
				int grayLevel = (int)(depthbuffer[y*winWidth+x]*100);
				depthbufferImg.setRGB(x, y, (grayLevel << 16) + (grayLevel << 8) + grayLevel);
			}
		}
		
		return depthbufferImg;
	}
	
	double edgeFunction(Vec3 a, Vec3 b, Vec3 c) 
	{ 
		return (c.x - a.x) * (b.y - a.y) - (c.y - a.y) * (b.x - a.x); 
	} 
	
	Vec3 cvtNDCToRaster(Vec3 ndc)
	{
		Vec3 raster = new Vec3();
		raster.x = (ndc.x+1) * .5 * winWidth;
		raster.y = (1-ndc.y) * .5 * winHeight;
		raster.z = ndc.z;
		return raster;
	}
}
