package ngine;

public class Matrix44 
{
	private double m[][] = {{1,0,0,0}, {0,1,0,0}, {0,0,1,0}, {0,0,0,1}};

	public Matrix44(){}
	
	public Matrix44(double c11, double c12, double c13, double c14,
					double c21, double c22, double c23, double c24,
					double c31, double c32, double c33, double c34,
					double c41, double c42, double c43, double c44)
	{
		m[0][0] = c11; m[0][1] = c12; m[0][2] = c13; m[0][3] = c14;
		m[1][0] = c21; m[1][1] = c22; m[1][2] = c23; m[1][3] = c24;
		m[2][0] = c31; m[2][1] = c32; m[2][2] = c33; m[2][3] = c34;
		m[3][0] = c41; m[3][1] = c42; m[3][2] = c43; m[3][3] = c44;
	}
	
	public Vec3 multVecMatrix(Vec3 v)
	{
		Vec3 ans = new Vec3();
		ans.x = m[0][0]*v.x + m[1][0]*v.y + m[2][0]*v.z + m[3][0];
		ans.y = m[0][1]*v.x + m[1][1]*v.y + m[2][1]*v.z + m[3][1];
		ans.z = m[0][2]*v.x + m[1][2]*v.y + m[2][2]*v.z + m[3][2];
		double w = m[0][3]*v.x + m[1][3]*v.y + m[2][3]*v.z + m[3][3];
		
		ans.x /= w;
		ans.y /= w;
		ans.z /= w;
		
		//System.out.println("v x " + v.x);
		//System.out.println("v y " + v.y);
		//System.out.println("v z " + v.z);
		//System.out.println("w " + w);
		//System.out.println("ans x " + ans.x);
		//System.out.println("ans y " + ans.y);
		//System.out.println("ans z " + ans.z);
		//System.out.println("m00 " + m[0][3]);
		//System.out.println("m10 " + m[1][3]);
		//System.out.println("m20 " + m[2][3]);
		//System.out.println("m30 " + m[3][3]);
		
		return ans;
	}
	
	public void setMatrix44(double c11, double c12, double c13, double c14,
								double c21, double c22, double c23, double c24,
								double c31, double c32, double c33, double c34,
								double c41, double c42, double c43, double c44)
	{
		m[0][0] = c11; m[0][1] = c12; m[0][2] = c13; m[0][3] = c14;
		m[1][0] = c21; m[1][1] = c22; m[1][2] = c23; m[1][3] = c24;
		m[2][0] = c31; m[2][1] = c32; m[2][2] = c33; m[2][3] = c34;
		m[3][0] = c41; m[3][1] = c42; m[3][2] = c43; m[3][3] = c44;
	}
	
	public double[][] getMatrix44()
	{
		return m;
	}
	
}
