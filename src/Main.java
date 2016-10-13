import njine.AppConfig;
import njine.Camera;
import njine.Mesh;
import njine.RenderSurface;
import njine.Renderer;
import njine.Vec3;
import njine.World;


public class Main					
{
	public static void main(String[] args)
	{
		///* commenting out a comment
		Vec3[] verts = {new Vec3(-1, -1, 1), 
						new Vec3(-1, -1, -1),
						new Vec3(-1, 1, 1),
						new Vec3(-1, 1, -1),
						new Vec3(1, -1, 1),
						new Vec3(1, -1, -1),
						new Vec3(1, 1, 1),
						new Vec3(1, 1, -1)};
		
		int[] tris = {0, 1, 5, 0, 5, 4,
					  0, 1, 2, 1, 3, 2,
					  1, 5, 7, 1, 3, 7};
		//*/
		
		/*
		Vec3 verts[] = { 
			    new Vec3(  -2.5703,   0.78053,  -2.4e-05), new Vec3( -0.89264,  0.022582,  0.018577), 
			    new Vec3(   1.6878, -0.017131,  0.022032), new Vec3(   3.4659,  0.025667,  0.018577), 
			    new Vec3(  -2.5703,   0.78969, -0.001202), new Vec3( -0.89264,   0.25121,   0.93573), 
			    new Vec3(   1.6878,   0.25121,    1.1097), new Vec3(   3.5031,   0.25293,   0.93573), 
			    new Vec3(  -2.5703,    1.0558, -0.001347), new Vec3( -0.89264,    1.0558,    1.0487), 
			    new Vec3(   1.6878,    1.0558,    1.2437), new Vec3(   3.6342,    1.0527,    1.0487), 
			    new Vec3(  -2.5703,    1.0558,         0), new Vec3( -0.89264,    1.0558,         0), 
			    new Vec3(   1.6878,    1.0558,         0), new Vec3(   3.6342,    1.0527,         0), 
			    new Vec3(  -2.5703,    1.0558,  0.001347), new Vec3( -0.89264,    1.0558,   -1.0487), 
			    new Vec3(   1.6878,    1.0558,   -1.2437), new Vec3(   3.6342,    1.0527,   -1.0487), 
			    new Vec3(  -2.5703,   0.78969,  0.001202), new Vec3( -0.89264,   0.25121,  -0.93573), 
			    new Vec3(   1.6878,   0.25121,   -1.1097), new Vec3(   3.5031,   0.25293,  -0.93573), 
			    new Vec3(   3.5031,   0.25293,         0), new Vec3(  -2.5703,   0.78969,         0), 
			    new Vec3(   1.1091,    1.2179,         0), new Vec3(    1.145,     6.617,         0), 
			    new Vec3(   4.0878,    1.2383,         0), new Vec3(  -2.5693,    1.1771, -0.081683), 
			    new Vec3(  0.98353,    6.4948, -0.081683), new Vec3( -0.72112,    1.1364, -0.081683), 
			    new Vec3(   0.9297,     6.454,         0), new Vec3(  -0.7929,     1.279,         0), 
			    new Vec3(  0.91176,    1.2994,         0)};
			    
			int tris[] = { 
				    4,   0,   5,   0,   1,   5,   1,   2,   5,   5,   2,   6,   3,   7,   2, 
				    2,   7,   6,   5,   9,   4,   4,   9,   8,   5,   6,   9,   9,   6,  10, 
				    7,  11,   6,   6,  11,  10,   9,  13,   8,   8,  13,  12,  10,  14,   9, 
				    9,  14,  13,  10,  11,  14,  14,  11,  15,  17,  16,  13,  12,  13,  16, 
				    13,  14,  17,  17,  14,  18,  15,  19,  14,  14,  19,  18,  16,  17,  20, 
				    20,  17,  21,  18,  22,  17,  17,  22,  21,  18,  19,  22,  22,  19,  23, 
				    20,  21,   0,  21,   1,   0,  22,   2,  21,  21,   2,   1,  22,  23,   2, 
				    2,  23,   3,   3,  23,  24,   3,  24,   7,  24,  23,  15,  15,  23,  19, 
				    24,  15,   7,   7,  15,  11,   0,  25,  20,   0,   4,  25,  20,  25,  16, 
				    16,  25,  12,  25,   4,  12,  12,   4,   8,  26,  27,  28,  29,  30,  31, 
				    32,  34,  33 };
		*/
		
		Box b = new Box();
		b.getModel().setMesh(new Mesh(verts, tris));
		b.getTransform().setGlobalPosition(new Vec3(0, 0, -5));
		
		Camera cam = new Camera(90, .1, 1000);
		cam.getTransform().setGlobalPosition(new Vec3(0, 2, 0));
		cam.getTransform().setGlobalRotation(new Vec3(30, 0, 0));
		
		AppConfig config = new AppConfig();
		World world = new World(config);		
		world.addEntity(b);
		world.setCamera(cam);
		world.begin();
	}
}
	
