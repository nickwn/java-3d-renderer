package njine;


public abstract class Entity 
{
	private Transform transform;
	private Model model;
	
	public Entity()
	{
		this.transform = new Transform(Vec3.ZERO, Vec3.ZERO, Vec3.ZERO, Vec3.ZERO);
		this.model = new Model(new Mesh(new Vec3[]{}, new int[]{}), new Material());
	}
	
	public abstract void setup();
	
	public abstract void update();
	
	public Transform getTransform()
	{
		return transform;
	}
	
	public Model getModel()
	{
		return model;
	}
}
