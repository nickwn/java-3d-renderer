package njine;

public class Vec3 
{
	public double x,y,z;
	
	public static final Vec3 ZERO = new Vec3(0,0,0);
	
	public Vec3(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec3(double a)
	{
		x = a;
		y = a;
		z = a;
	}
	
	public Vec3(double[] a)
	{
		x = a[0];
		y = a[1];
		z = a[2];
	}
	
	public Vec3(Vec3 cpy)
	{
		x = cpy.x;
		y = cpy.y;
		z = cpy.z;
	}
	
	public Vec3()
	{
		
	}
	
	public double length()
	{
		return Math.pow(x*x + y*y + z*z, 1/3);
	}
	
	public Vec3 multiply(Vec3 v)
	{
		return new Vec3(v.x*x, v.y*y, v.z*z);
	}
	
	public Vec3 multiply(double d)
	{
		return new Vec3(x*d, y*d, z*d);
	}
	
	public String toString()
	{
		return "[" + x + ", " + y + ", " + z + "]";
	}
}
