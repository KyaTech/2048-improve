import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class _2048_improve {

	public static void main(String[] args) {
		
		//INIT
		
		Game game = new Game();
		
		Frame f = new Frame(game);

		
		long lastFrameMillis = System.currentTimeMillis();
		
		while(true) {
			
			//UPDATE
			
			long currentFrameMillis = System.currentTimeMillis();
			
			float timeSinceLastFrame = (float)(currentFrameMillis - lastFrameMillis)/1000f;
			
			lastFrameMillis = currentFrameMillis;
			
			game.update(timeSinceLastFrame);
			
			//DRAW
			
			f.repaintFrame();
			
			try {
				Thread.sleep(1000/game.getMaxFps());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}

	}

}
