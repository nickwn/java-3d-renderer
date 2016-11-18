import njine.entity.Entity;
import njine.entity.Model;
import njine.entity.Transform;
import njine.math.Vec3;


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
