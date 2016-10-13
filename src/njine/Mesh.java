package njine;
import java.util.ArrayList;


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
