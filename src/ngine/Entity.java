package ngine;

import java.util.ArrayList;

public abstract class Entity
{
	public Vec3 pos = new Vec3(0,0,0);
	public Mesh mesh;
	
	//public static ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public Entity(){}
	
	public abstract void setup();
	
	public abstract void update();
}
