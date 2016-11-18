package njine.entity;

import njine.math.Mat44;
import njine.math.MatBuilder;
import njine.math.Vec3;


public class Transform 
{
	private Vec3 globalPosition;
	private Vec3 globalRotation;
	private Vec3 localPosition;
	private Vec3 localRotation;
	
	private Mat44 local;
	private Mat44 global;
	
	public Transform(Vec3 globalPosition, Vec3 globalRotation, Vec3 localPosition, Vec3 localRotation)
	{
		this.globalPosition = globalPosition;
		this.globalRotation = globalRotation;
		this.localPosition = localPosition;
		this.localRotation = localRotation;
		global = MatBuilder.translationMatrix(globalPosition.x, globalPosition.y, globalPosition.z);
		global = global.multiply(MatBuilder.rotationMatrix(globalRotation.x, globalRotation.y, globalRotation.z));
		local = MatBuilder.translationMatrix(localPosition.x, localPosition.y, localPosition.z);
		local = local.multiply(MatBuilder.rotationMatrix(localRotation.x, localRotation.y, localRotation.z));
	}
	
	public Vec3 getGlobalPosition()
	{
		return globalPosition;
	}
	
	public Vec3 getGlobalRotation()
	{
		return globalRotation;
	}
	
	public Vec3 getLocalPosition()
	{
		return localPosition;
	}
	
	public Vec3 getLocalRotation()
	{
		return localRotation;
	}
	
	public Mat44 getGlobalMat()
	{
		return global;
	}
	
	public Mat44 getLocalMat()
	{
		return local;
	}
	
	public void setGlobalPosition(Vec3 globalPosition)
	{
		this.globalPosition = globalPosition;
		global = MatBuilder.translationMatrix(globalPosition.x, globalPosition.y, globalPosition.z);
		global = global.multiply(MatBuilder.rotationMatrix(globalRotation.x, globalRotation.y, globalRotation.z));
	}
	
	public void setGlobalRotation(Vec3 globalRotation)
	{
		this.globalRotation = globalRotation;
		global = MatBuilder.translationMatrix(globalPosition.x, globalPosition.y, globalPosition.z);
		global = global.multiply(MatBuilder.rotationMatrix(globalRotation.x, globalRotation.y, globalRotation.z));
	}
	
	public void setLocalPosition(Vec3 localPosition)
	{
		this.localPosition = localPosition;
		local = MatBuilder.translationMatrix(localPosition.x, localPosition.y, localPosition.z);
		local = local.multiply(MatBuilder.rotationMatrix(localRotation.x, localRotation.y, localRotation.z));
	}
	
	public void setLocalRotation(Vec3 localRotation)
	{
		this.localRotation = localRotation;
		local = MatBuilder.translationMatrix(localPosition.x, localPosition.y, localPosition.z);
		local = local.multiply(MatBuilder.rotationMatrix(localRotation.x, localRotation.y, localRotation.z));
	}
	
	public String toString()
	{
		return "globalPosition: "+ globalPosition + ", globalRotation: " + globalRotation + ", localPosition: " + localPosition + ", localRotation: " + localRotation;
	}
	
}
