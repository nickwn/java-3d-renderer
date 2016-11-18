package njine.math;

public class Vec2 
{
	public double x,y;
	
	public Vec2(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vec2(double a)
	{
		this.x = a;
		this.y = a;
	}
	
	public Vec2(Vec2 cpy)
	{
		x = cpy.x;
		y = cpy.y;
	}
	
	public Vec2()
	{
		
	}
	
	public double length()
	{
		return Math.sqrt(x*x + y*y);
	}
	
	public String toString()
	{
		return "[" + x + ", " + y + "]";
	}
}
