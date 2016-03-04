package ngine;

import java.awt.Canvas;
import java.awt.event.ActionListener;

public abstract class Renderer extends Canvas
{
	public abstract void setup(Vec3 camPos, int width, int height, double nearClip, double farClip, int  fl, double faw, double fah, fitFilm ff);
	public abstract void render(Entity es[]);
}
