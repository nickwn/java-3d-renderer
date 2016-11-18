package njine.render;
import java.util.ArrayList;


public class VertexAttribs<E>
{
	private ArrayList<E> attribs;
	
	public VertexAttribs(ArrayList<E> attribs)
	{
		this.attribs = attribs;
	}
	
	public ArrayList<E> getAttrib()
	{
		return attribs;
	}
	
	public void setAttrib(ArrayList<E> attribs)
	{
		this.attribs = attribs;
	}
}
