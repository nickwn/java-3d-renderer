
public class Vec2 
{
	double x,y;
	
	public Vec2()
	{
		x=0;
		y=0;
	}
	
	public Vec2(double xx)
	{
		x=xx;
		y=xx;
	}
	
	public Vec2(double xx, double yy)
	{
		x=xx;
		y=yy;
	}
	
	public Vec2 add(Vec2 v)
	{
		return new Vec2(x+v.x, y+v.y);
	}
	
	public Vec2 subtract(Vec2 v)
	{
		return new Vec2(x-v.x, y-v.y);
	}
	
	public Vec2 multiplyEquals(Vec2 v)
	{
		return new Vec2(x*=v.x, y*=v.y);
	}
	
	public Vec2 divideEquals(Vec2 v)
	{
		return new Vec2(x/=v.x, y/=v.y);
	}
	
	public Vec2 multiply(double d, Vec2 v)
	{
		return new Vec2(v.x*d, v.y*d);
	}
}
