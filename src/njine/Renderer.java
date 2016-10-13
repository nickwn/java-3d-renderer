package njine;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Renderer 
{
	private Mat44 projectionMatrix;
	private Mesh worldMesh;
	private int winHeight, winWidth;
	private Mat44 worldToCam;
	
	public void init(int height, int width, Transform camTrans, double fov, double near, double far)
	{
		if(width%2!=0 || height%2!=0)
			throw new IllegalArgumentException("window height and width need to be even");
		
		worldToCam = camTrans.getGlobalMat().invert();;
		projectionMatrix = MatBuilder.projectionMatrix(fov, far, near);
		winHeight = height;
		winWidth = width;
	}
	
	public void setMesh(Mesh m)
	{
		worldMesh = m;
	}
	
	public BufferedImage render()
	{
		BufferedImage framebuffer = new BufferedImage(winWidth, winHeight, BufferedImage.TYPE_INT_RGB);
		Graphics fbGraphics = framebuffer.getGraphics();
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
			
			p1 = worldToCam.multVecMat(p1);
			p2 = worldToCam.multVecMat(p2);
			p3 = worldToCam.multVecMat(p3);
			
			p1 = projectionMatrix.multVecMat(p1);
			p2 = projectionMatrix.multVecMat(p2);
			p3 = projectionMatrix.multVecMat(p3);
			
			Vec2 r1 = cvtNDCToRaster(p1);
			Vec2 r2 = cvtNDCToRaster(p2);
			Vec2 r3 = cvtNDCToRaster(p3);
			
			fbGraphics.drawLine((int) r1.x, (int) r1.y, (int) r2.x, (int) r2.y);
			fbGraphics.drawLine((int) r2.x, (int) r2.y, (int) r3.x, (int) r3.y);
			fbGraphics.drawLine((int) r3.x, (int) r3.y, (int) r1.x, (int) r1.y);
		}
		
		return framebuffer;
	}
	
	double edgeFunction(Vec3 a, Vec3 b, Vec3 c) 
	{ 
		return (c.x - a.x) * (b.y - a.y) - (c.y - a.y) * (b.x - a.x); 
	} 
	
	Vec2 cvtNDCToRaster(Vec3 ndc)
	{
		Vec2 raster = new Vec2();
		raster.x = (ndc.x+1) * .5 * winWidth;
		raster.y = (1-ndc.y) * .5 * winHeight;
		return raster;
	}
}
