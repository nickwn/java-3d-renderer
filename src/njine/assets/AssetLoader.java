package njine.assets;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface AssetLoader {

	public Object load(String filename) throws FileNotFoundException, IOException;

}
