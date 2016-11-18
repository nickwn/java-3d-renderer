package njine.entity;

import java.awt.image.BufferedImage;

public class Material 
{
	private BufferedImage texture;
	
	public Material(BufferedImage tex)
	{
		texture = tex;
	}
	
	public Material()
	{
		texture = new BufferedImage(0, 0, BufferedImage.TYPE_INT_RGB);
	}
}
