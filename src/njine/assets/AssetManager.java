package njine.assets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class AssetManager 
{
	private static HashMap<String, AssetLoader> loaders = defaultLoaders();
	
	private AssetManager(){}
	
	public static HashMap<String, AssetLoader> defaultLoaders()
	{
		HashMap<String, AssetLoader> loaders = new HashMap<String, AssetLoader>();
		loaders.put("obj", new OBJLoader());
		loaders.put("jpg", new TextureLoader());
		loaders.put("jpeg", new TextureLoader());
		loaders.put("png", new TextureLoader());
		loaders.put("gif", new TextureLoader());
		loaders.put("bmp", new TextureLoader());
		loaders.put("wbmp", new TextureLoader());
		return loaders;
	}
	
	public static void addAssetLoader(String type, AssetLoader loader)
	{
		loaders.put(type.toLowerCase(), loader);
	}
	
	public static void removeAssetLoader(String type)
	{
		loaders.remove(type.toLowerCase());
	}
	
	public static Object loadAsset(String filename) throws FileNotFoundException, IOException
	{
		String[] split = filename.split("\\.");
		String end = split[split.length-1].toLowerCase();
		if(!loaders.containsKey(end))
			throw new IllegalArgumentException("asset of type " + end + " not supported");
		AssetLoader loader = loaders.get(end);
		return loader.load(filename);
	}
}
