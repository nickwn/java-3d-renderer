package njine.assets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextureLoader implements AssetLoader
{
	public Object load(String filename) throws FileNotFoundException, IOException 
	{
		return ImageIO.read(new File(filename));
	}

}
