package njine.camera;
import java.awt.event.KeyEvent;

import njine.math.Vec3;
import njine.util.Input;


public class FPCam extends Camera
{
	public FPCam() 
	{
		super(50, .1, 1000);
	}

	public void setup() 
	{
		
	}

	public void update() 
	{
		getTransform().setLocalRotation(new Vec3(Input.getMouseY()/2, (512-Input.getMouseX()/2), 0));
		if(Input.getKeyPressed(KeyEvent.VK_W))
		{
			Vec3 newPos = getTransform().getLocalMat().multVecMat(new Vec3(0,0,.1)).add(getTransform().getGlobalPosition());
			getTransform().setGlobalPosition(newPos);
		}
		if(Input.getKeyPressed(KeyEvent.VK_S))
		{
			Vec3 newPos = getTransform().getLocalMat().multVecMat(new Vec3(0,0,-.1)).add(getTransform().getGlobalPosition());
			getTransform().setGlobalPosition(newPos);
		}
		if(Input.getKeyPressed(KeyEvent.VK_A))
		{
			Vec3 newPos = getTransform().getLocalMat().multVecMat(new Vec3(.1,0,0)).add(getTransform().getGlobalPosition());
			getTransform().setGlobalPosition(newPos);
		}
		if(Input.getKeyPressed(KeyEvent.VK_D))
		{
			Vec3 newPos = getTransform().getLocalMat().multVecMat(new Vec3(-.1,0,0)).add(getTransform().getGlobalPosition());
			getTransform().setGlobalPosition(newPos);
		}
		if(Input.getKeyPressed(KeyEvent.VK_Q))
		{
			Vec3 newPos = getTransform().getLocalMat().multVecMat(new Vec3(0,.1,0)).add(getTransform().getGlobalPosition());
			getTransform().setGlobalPosition(newPos);
		}
		if(Input.getKeyPressed(KeyEvent.VK_E))
		{
			Vec3 newPos = getTransform().getLocalMat().multVecMat(new Vec3(0,-.1,0)).add(getTransform().getGlobalPosition());
			getTransform().setGlobalPosition(newPos);
		}
	}

}
