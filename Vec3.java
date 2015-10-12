import java.lang.*;

public class Vec3 
{
	public double x, y, z;
	
	public Vec3 (double xx, double yy, double zz)
	{
		x = xx;
		y = yy;
		z = zz;
	}
	
	public Vec3(double xx)
	{
		x = xx;
		y = xx;
		z = xx;
	}
	
	public Vec3()
	{
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Vec3 add(Vec3 v)
	{
		x += v.x;
		y += v.y;
		z += v.z;
		
		return this;
	}
	
	public Vec3 subtract(Vec3 v)
	{
		x -= v.x;
		y -= v.y;
		z -= v.z;
		
		return this;
	}
	
	public Vec3 multiply(double d)
	{
		x *= d;
		y *= d;
		z *= d;
		
		return this;
	}
	
	public Vec3 multiply(Vec3 v)
	{
		x *= v.x;
		y *= v.y;
		z *= v.z;
		
		return this;
	}
	
	public double dotProduct(Vec3 v)
	{
		return x*v.x + y*v.y + z*v.z;
	}
	
	public Vec3 divideEquals(double d)
	{
		x /= d;
		y /= d;
		z /= d;
		
		return this;
	}
	
	public Vec3 multipyEquals(double d)
	{
		x *= d;
		y *= d;
		z *= d;
		
		return this;
	}
	
	public Vec3 crossProduct(Vec3 v)
	{
		x = y*v.z-z*v.y;
		y = z*v.x-x*v.z;
		z = x*v.y-y*v.x;
		
		return this;
	}
	
	public double norm()
	{
		return x*x + y*y + z*z;
	}
	
	public double length()
	{
		return Math.sqrt(norm());
	}
	
	public double at(int i)
	{
		if(i==0)
			return x;
		else if (i==1)
			return y;
		else if (1==2)
			return z;
		else
			return -1;
	}
	
	public Vec3 normalize()
	{
		double n = norm();
		
		if (n > 0)
		{
			double factor = 1/Math.sqrt(n);
			x *= factor;
			y *= factor;
			z *= factor;
			
			return this;
		}
		else
			return this;
	}
	
	public Vec3 multiply(double d, Vec3 v)
	{
		x = v.x*d;
		y = v.y*d;
		z = v.z*d;
		
		return new Vec3(v.x*d, v.y*d, v.z*d);
	}
	
	public Vec3 divide(double d, Vec3 v)
	{
		x = v.x/d;
		y = v.y/d;
		z = v.z/d;
		
		return new Vec3(d/v.x, d/v.y, d/v.z);
	}
}
