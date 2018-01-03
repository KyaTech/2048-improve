import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	
	private static boolean keys[] = new boolean[100];

	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		if (keycode >= 0 && keycode < keys.length) keys[keycode] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int keycode = e.getKeyCode();
		if (keycode >= 0 && keycode < keys.length) keys[keycode] = false;
		
	}
	
	
	public static boolean isKeyDown(int keycode) {
		
		if (keycode >= 0 && keycode < keys.length) {
			boolean value = keys[keycode];
			keys[keycode] = false;
			return value;
			
		}
		else return false;
		
	}
	
	public static void workedOn (int keycode) {
		if (keycode >= 0 && keycode < keys.length)keys[keycode] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	
	
	
}
