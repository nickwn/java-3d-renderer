package njine.math;
import static java.lang.Math.*;

public final class MatBuilder 
{
	private MatBuilder(){}
	
	private static Mat44 xAxisRotationMatrix(double degrees)
	{
		double r = toRadians(degrees);
		return new Mat44(1,	 	 0,		  0, 0,
						 0, cos(r), -sin(r), 0,
						 0, sin(r),  cos(r), 0,
						 0,		 0,		  0, 1);
	}
	
	private static Mat44 yAxisRotationMatrix(double degrees)
	{
		double r = toRadians(degrees);
		return new Mat44(cos(r),  0,  sin(r), 0,
				 		 0, 	  1, 	   0, 0,
				 		 -sin(r), 0,  cos(r), 0,
				 		 0,		  0,	   0, 1);
	}
	
	private static Mat44 zAxisRotationMatrix(double degrees)
	{
		double r = toRadians(degrees);
		return new Mat44(cos(r), -sin(r), 0, 0,
						 sin(r),  cos(r), 0, 0,
						 0,	           0, 1, 0,
						 0,			   0, 0, 1);
	}
	
	public static Mat44 rotationMatrix(double x, double y, double z)
	{
		Mat44 xMat = xAxisRotationMatrix(x);
		Mat44 yMat = yAxisRotationMatrix(y);
		Mat44 zMat = zAxisRotationMatrix(z);
		Mat44 temp = yMat.multiply(zMat);
		return xMat.multiply(temp);
	}
	
	public static Mat44 translationMatrix(double x, double y, double z)
	{
		return new Mat44(1, 0, 0, 0,
						 0, 1, 0, 0,
						 0, 0, 1, 0,
						 x, y, z, 1);
	}
	
	public static Mat44 scaleMatrix(double x, double y, double z)
	{
		return new Mat44(x, 0, 0, 0,
						 0, y, 0, 0,
						 0, 0, z, 0,
						 0, 0, 0, 1);
	}
	
	public static Mat44 projectionMatrix(double fov, double f, double n)
	{
		double fovRad = toRadians(fov);
		double scale = 1/tan(fovRad/2);
		return new Mat44(scale, 	0, 		  	  0, 0,
						 0, 	scale, 		  	  0, 0,
						 0,			0, 	   -f/(f-n), -1,
						 0,			0, -(f*n)/(f-n), 0);
	}
	
}
