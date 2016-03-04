package ngine;

import java.util.ArrayList;

/**
 * High-level class that contains and runs the world.
 * Some may find it surprising that the Earth
 * can be run on a piece of code 80 lines long. 
 * This creates two threads: one that rotates and applies 
 * other transformations
 * at a fixed rate(ish, every 10 milliseconds or so), and one that grabs the entities and 
 * takes it's sweet time rendering them. The first thread
 * would be for transformations, such as physics or
 * player movement. 
 * @author Nicolas Nebel
 * 
 */
public class World extends Thread
{
	private static ArrayList<Entity> ents = new ArrayList<Entity>();
	private static Camera cam = new Camera("Ngine 0.3", RenderMethod.RASTERIZE);
	
	public World()
	{
		//ents = new ArrayList<Entity>();
	}
	
	public void run()
	{
		System.out.println("Setting up camera in rendering thread...");
		System.out.println("entities: " + ents.size());
		cam.setupDefault(new Vec3(0,-4,50));
		System.out.println("Doing rendering thread loop");
        while (true)
        {
        	cam.repaintCanvas();
        	cam.render(ents);
        	try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
	}
	
	/**
	 * Begins the 3D world. Starts by calling the setup() 
	 * function for all of the current entities. 
	 */
	public void beginWorld()
	{
		System.out.println("Setting up " + ents.size() + " entities...");
		for(Entity e: ents)
			e.setup();
		
		System.out.println("Starting rendering thread...");
		(new World()).start();
		
		System.out.println("doing update cycle");
		while(true)
		{
			for(Entity e : ents)
				e.update();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//cam.repaintCanvas();
		}
	}
	
	/**
	 * Adds and entity to the list. Entities are stored using an ArrayList. 
	 * @param e The entity to add
	 */
	public void addEntity(Entity e)
	{
		ents.add(e);
		System.out.println("added an entity " + e.toString());
	}
}
