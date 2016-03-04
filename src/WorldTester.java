import ngine.World;

public class WorldTester 
{
	public static void main(String[] args)
	{
		World world = new World();
		world.addEntity(new Box());
		world.beginWorld();
	}
}
