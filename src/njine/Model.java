package njine;

public class Model 
{
	private Mesh mesh;
	private Material material;
	
	public Model(Mesh mesh, Material material)
	{
		this.mesh = mesh;
		this.material = material;
	}
	
	public Mesh getMesh()
	{
		return mesh;
	}
	
	public Material getMaterial()
	{
		return material;
	}
	
	public void setMesh(Mesh mesh)
	{
		this.mesh = mesh;
	}
	
	public void setMaterial(Material material)
	{
		this.material = material;
	}
}
