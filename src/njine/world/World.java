package njine.world;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import njine.assets.AssetManager;
import njine.assets.OBJLoader;
import njine.camera.Camera;
import njine.entity.Entity;
import njine.entity.Mesh;
import njine.math.Mat44;
import njine.math.Vec3;
import njine.render.RenderSurface;
import njine.render.Renderer;
import njine.render.RendererThread;
import njine.util.Input;


public class World 
{
	private ArrayList<Entity> entities;
	private Camera camera;
	private AppConfig config;
	private Renderer renderer;
	private Mesh worldMesh;
	
	public World(AppConfig appConfig)
	{
		entities = new ArrayList<Entity>();
		camera = null;
		worldMesh = null;
		config = appConfig;
		renderer = new Renderer();
	}
	
	public void begin()
	{
		if(camera == null)
			throw new IllegalStateException("must begin world with at least one camera");
		if(entities.size() == 0)
			throw new IllegalStateException("must begin world with at least one entity");
		
		// window setup
		JFrame window = new JFrame(config.winName);
		window.setSize(config.winWidth, config.winHeight);
		window.setVisible(true);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addMouseMotionListener(Input.getInstance());
		window.addKeyListener(Input.getInstance());
		RenderSurface surface = new RenderSurface();
		window.add(surface);
		
		setupEntities();
		camera.setup();
		
		RendererThread rendererThread = new RendererThread(renderer, surface, window);
		rendererThread.start();
		
		updateEntities();
	}
	
	public void setCamera(Camera camera)
	{
		this.camera = camera;
		Mat44 wToC = camera.getTransform().getGlobalMat().invert().multiply(camera.getTransform().getLocalMat().invert());
		renderer.init(config.winHeight, config.winWidth, wToC, camera.getFOV(), camera.getNearClip(), camera.getFarClip());
	}
	
	public void addEntity(Entity e)
	{
		entities.add(e);
	}
	
	
	private Mesh entitiesToMesh()
	{
		ArrayList<Vec3> verts = new ArrayList<Vec3>();
		ArrayList<Integer> tris = new ArrayList<Integer>();
		int vertSize = 0;
		
		for(Entity e: entities)
		{
			for(Vec3 v: e.getModel().getMesh().getVerts())
			{
				Vec3 local = e.getTransform().getLocalMat().multVecMat(v);
				Vec3 world = e.getTransform().getGlobalMat().multVecMat(local);
				verts.add(world);
			}
			
			for(Integer i: e.getModel().getMesh().getTris())
			{
				tris.add(vertSize + i);
			}
			
			vertSize = verts.size();
		}
		
		Vec3[] vertsArr = new Vec3[verts.size()];
		int[] trisArr = new int[tris.size()];
		for(int i = 0; i < verts.size(); i++)
			vertsArr[i] = verts.get(i);
		for(int j = 0; j < tris.size(); j++)
			trisArr[j] = tris.get(j);
			
		
		return new Mesh(vertsArr, trisArr);
	}
	
	private void setupEntities()
	{
		worldMesh = entitiesToMesh();
		renderer.setMesh(worldMesh);
		
		for(Entity e: entities)
			e.setup();
	}
	
	private void updateEntities()
	{
		Runnable updateRunnable = 
		new Runnable() 
		{
			public void run() 
			{				
				camera.update();
				Mat44 wToC = camera.getTransform().getGlobalMat().invert().multiply(camera.getTransform().getLocalMat().invert());
				renderer.setWorldToCam(wToC);
				for(Entity e: entities)
					e.update();
				
				worldMesh = entitiesToMesh();
				renderer.setMesh(worldMesh);
			}
		};
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(updateRunnable, 0, 30, TimeUnit.MILLISECONDS);
	}
}
