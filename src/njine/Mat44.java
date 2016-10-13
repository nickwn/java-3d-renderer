package njine;

public class Mat44 
{
	private double[][] m;
	
	public Mat44(double m00, double m01, double m02, double m03,
				 double m10, double m11, double m12, double m13,
				 double m20, double m21, double m22, double m23,
				 double m30, double m31, double m32, double m33)
	{
		m = new double[4][4];
		m[0][0]=m00;m[0][1]=m01;m[0][2]=m02;m[0][3]=m03;
		m[1][0]=m10;m[1][1]=m11;m[1][2]=m12;m[1][3]=m13;
		m[2][0]=m20;m[2][1]=m21;m[2][2]=m22;m[2][3]=m23;
		m[3][0]=m30;m[3][1]=m31;m[3][2]=m32;m[3][3]=m33;
	}
	
	public Mat44(double[][] m)
	{
		this.m = m;
	}
	
	public Mat44()
	{
		m = new double[][] {{1,0,0,0},
						    {0,1,0,0},
						    {0,0,1,0},
						    {0,0,0,1}};
	}
	
	public void setMat(double[][] m)
	{
		this.m = m;
	}
	
	public void setMat(double m00, double m01, double m02, double m03,
					   double m10, double m11, double m12, double m13,
					   double m20, double m21, double m22, double m23,
					   double m30, double m31, double m32, double m33)
	{
		m = new double[4][4];
		m[0][0]=m00;m[0][1]=m01;m[0][2]=m02;m[0][3]=m03;
		m[1][0]=m10;m[1][1]=m11;m[1][2]=m12;m[1][3]=m13;
		m[2][0]=m20;m[2][1]=m21;m[2][2]=m22;m[2][3]=m23;
		m[3][0]=m30;m[3][1]=m31;m[3][2]=m32;m[3][3]=m33;
	}
	
	public double[][] getMat()
	{
		return m;
	}
	
	public Vec3 multVecMat(Vec3 v)
	{
		double w = 1;
		Vec3 ans = new Vec3();
		ans.x = v.x*m[0][0] + v.y*m[1][0] + v.z*m[2][0] + w*m[3][0];
		ans.y = v.x*m[0][1] + v.y*m[1][1] + v.z*m[2][1] + w*m[3][1];
		ans.z = v.x*m[0][2] + v.y*m[1][2] + v.z*m[2][2] + w*m[3][2];
		w 	  = v.x*m[0][3] + v.y*m[1][3] + v.z*m[2][3] + w*m[3][3];
		
		// for projection matricies
		if(w==1||w==0)
			return ans;
		ans.x/=w;
		ans.y/=w;
		ans.z/=w;
		return ans;
	}
	
	public Vec3 multVecMat(double[] v)
	{
		return multVecMat(new Vec3(v));
	}
	
	public Mat44 multiply(Mat44 in)
	{
		/*
		double[][] m2 = in.getMat();
		// assert(width1 == height2)
		if (m[0].length != m2.length)
			throw new ArithmeticException("Invalid matrix height");
		
		int sum = 0;
		double[][] ans = new double[4][4];
		for(int i=0; i < m.length; i++)
		{
			for(int j = 0; j < m[0].length; j++)
			{
				for(int k=0; k < m2.length; k++)
				{
					sum += m[i][k]*m2[k][j];
				}
				ans[i][j] = sum;
				sum = 0;
			}
		}
		*/
		double[][] ans = multiplicar(m, in.getMat());
		
		return new Mat44(ans);
	}
	
	public static double[][] multiplicar(double[][] A, double[][] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        double[][] C = new double[aRows][bColumns];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                C[i][j] = 0.00000;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

	
	public Mat44 invert()
	{
		double[][] a = (new Mat44(m)).getMat();
		int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i) 
            b[i][i] = 1;
 
 // Transform the matrix into an upper triangle
        gaussian(a, index);
 
 // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                    	    -= a[index[j]][i]*b[index[i]][k];
 
 // Perform backward substitutions
        for (int i=0; i<n; ++i) 
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) 
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) 
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return new Mat44(x);
	}
	
	private void gaussian(double a[][], int index[]) 
    {
        int n = index.length;
        double c[] = new double[n];
 
 // Initialize the index
        for (int i=0; i<n; ++i) 
            index[i] = i;
 
 // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i) 
        {
            double c1 = 0;
            for (int j=0; j<n; ++j) 
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
 
 // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j) 
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i) 
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) 
                {
                    pi1 = pi0;
                    k = i;
                }
            }
 
   // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) 	
            {
                double pj = a[index[i]][j]/a[index[j]][j];
 
 // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;
 
 // Modify other elements accordingly
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }
}
