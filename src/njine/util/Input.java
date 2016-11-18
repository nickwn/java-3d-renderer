package njine.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

public class Input implements MouseMotionListener, KeyListener
{
	private enum KeyStatus
	{
		PRESSED, NOT_PRESSED;
	}
	
	private int mouseX;
	private int mouseY;
	private static Input instance = new Input();
	
	private HashMap<Integer, KeyStatus> keyMap;
	
	private Input()
	{
		keyMap = new HashMap<Integer, KeyStatus>();
	}
	
	public void keyPressed(KeyEvent e) 
	{
		keyMap.put(e.getKeyCode(), KeyStatus.PRESSED);
	}

	public void keyReleased(KeyEvent e) 
	{
		keyMap.put(e.getKeyCode(), KeyStatus.NOT_PRESSED);
	}

	public void keyTyped(KeyEvent e) 
	{
		
	}
	
	public void mouseDragged(MouseEvent e) 
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseMoved(MouseEvent e) 
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	public static boolean getKeyPressed(int keyCode)
	{
		return instance.keyMap.get(keyCode) == KeyStatus.PRESSED;
	}
	
	public static int getMouseX()
	{
		return instance.mouseX;
	}
	
	public static int getMouseY()
	{
		return instance.mouseY;
	}
	
	public static Input getInstance()
	{
		return instance;
	}

}
