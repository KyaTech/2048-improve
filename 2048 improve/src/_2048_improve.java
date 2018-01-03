import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class _2048_improve {
	
	private static final int MAX_FPS = 60;

	public static void main(String[] args) {
		
		Frame f = new Frame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setSize(800, 600);
		f.setResizable(false);
		
		f.setVisible(true);
		
		//INIT
		
		long lastFrameMillis = System.currentTimeMillis();
		
		while(true) {
			
			long currentFrameMillis = System.currentTimeMillis();
			
			float timeSinceLastFrame = (float)(currentFrameMillis - lastFrameMillis)/1000f;
			
			lastFrameMillis = currentFrameMillis;
			
			if(Keyboard.isKeyDown(KeyEvent.VK_W) || Keyboard.isKeyDown(KeyEvent.VK_UP))
				f.posy -= 500*timeSinceLastFrame;
			if(Keyboard.isKeyDown(KeyEvent.VK_S) || Keyboard.isKeyDown(KeyEvent.VK_DOWN))
				f.posy += 500*timeSinceLastFrame;
			if(Keyboard.isKeyDown(KeyEvent.VK_D) || Keyboard.isKeyDown(KeyEvent.VK_RIGHT))
				f.posx += 500*timeSinceLastFrame;
			if(Keyboard.isKeyDown(KeyEvent.VK_A) || Keyboard.isKeyDown(KeyEvent.VK_LEFT))
				f.posx -= 500*timeSinceLastFrame;			
			
			
			
			f.repaintFrame();
			
			try {
				Thread.sleep(1000/MAX_FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

	}

}
