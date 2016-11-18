package njine.entity;
import java.util.ArrayList;

import njine.math.Vec3;


public class Mesh 
{
	private Vec3[] verts;
	private int[] tris;
	
	public Mesh(Vec3[] verts, int[] tris)
	{
		this.verts = verts;
		this.tris = tris;
	}
	
	public Vec3[] getVerts()
	{
		return verts;
	}
	
	public int[] getTris()
	{
		return tris;
	}
	
	public void setVerts(Vec3[] verts)
	{
		this.verts = verts;
	}
	
	public void setTris(int[] tris)
	{
		this.tris = tris;
	}
}
