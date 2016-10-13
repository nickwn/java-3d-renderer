import njine.Entity;
import njine.Model;
import njine.Transform;
import njine.Vec3;


public class Box extends Entity
{
	public void setup() 
	{
		
	}

	public void update() 
	{
		getTransform().setLocalRotation(new Vec3(0, getTransform().getLocalRotation().y+2, 0));
	}
}
