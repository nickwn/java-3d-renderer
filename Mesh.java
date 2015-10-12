import chn.util.*;
import java.io.*;

public class Mesh 
{
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	
	private Vec3[] verts;
	private int[] tris;
	private int numTris;
	
	public Mesh(String fileName)
	{
	}
	
	public Mesh(Vec3[] vs, int[] ts, int nt)
	{
		verts = vs;
		tris = ts;
		numTris = nt;
	}
	
	public Mesh(){}
	
	
	
	public void setVerts(Vec3[] vs)
	{
		verts = vs;
	}
	
	public void setTris(int[] ts)
	{
		tris = ts;
	}
	
	public void setNumTris(int nt)
	{
		numTris = nt;
	}
	
	public Vec3[] getVerts()
	{
		return verts;
	}
	
	public int[] getTris()
	{
		return tris;
	}
	
	public int getNumTris()
	{
		return numTris;
	}
	
	public void rotateGlobalY(double radians)
	{
		Matrix44 r = new Matrix44(Math.cos(radians), 0, -Math.sin(radians), 0, 0, 1, 0, 0, Math.sin(radians), 0, Math.cos(radians), 0, 0, 0, 0, 1);
		for(int i = 0; i < 8; i++)
		{
			System.out.println(i);
			verts[i] = r.multVecMatrix(verts[i]);
		}
	}
}
