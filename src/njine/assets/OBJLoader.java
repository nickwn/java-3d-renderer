package njine.assets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import njine.entity.Material;
import njine.entity.Mesh;
import njine.entity.Model;
import njine.math.Vec3;

public class OBJLoader implements AssetLoader
{

	public Object load(String filename) throws FileNotFoundException, IOException 
	{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		ArrayList<Vec3> verts = new ArrayList<Vec3>();
		ArrayList<Integer> tris = new ArrayList<Integer>();
		
		String line = br.readLine();
		while(line != null)
		{
			String[] words = line.split(" ");
			if (words.length != 0)
			{
				switch(words[0])
				{
				case "v":
					double x = Double.parseDouble(words[1]);
					double y = Double.parseDouble(words[2]);
					double z = Double.parseDouble(words[3]);
					verts.add(new Vec3(x, y, z));
					break;
				case "f":
					tris.add(Integer.parseInt(words[1].split("/")[0])-1);
					tris.add(Integer.parseInt(words[2].split("/")[0])-1);
					tris.add(Integer.parseInt(words[3].split("/")[0])-1);
					break;
				default:
					break;
				}
			}
			line = br.readLine();
		}
		
		br.close();
		
		int[] trisArr = new int[tris.size()];
		for(int i = 0; i < tris.size(); i++)
			trisArr[i] = tris.get(i);
		
		Mesh mesh = new Mesh(verts.toArray(new Vec3[verts.size()]), trisArr);
		
		return mesh;
	}

}
