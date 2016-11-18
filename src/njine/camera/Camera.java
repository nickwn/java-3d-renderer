package njine.camera;

import njine.entity.Transform;
import njine.math.Vec3;


public abstract class Camera 
{
	private Transform transform;
	private double nearClip, farClip, fov;
	
	public Camera(double fov, double nearClip, double farClip)
	{
		this.transform = new Transform(Vec3.ZERO, Vec3.ZERO, Vec3.ZERO, Vec3.ZERO);
		this.fov = fov;
		this.nearClip = nearClip;
		this.farClip = farClip;
	}
	
	public Transform getTransform()
	{
		return transform;
	}
	
	public double getFOV()
	{
		return fov;
	}
	
	public double getNearClip()
	{
		return nearClip;
	}
	
	public double getFarClip()
	{
		return farClip;
	}
	
	public abstract void setup();
	
	public abstract void update();
	
}
