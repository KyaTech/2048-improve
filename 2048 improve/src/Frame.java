import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame {
	
	int posx = 300;
	int posy = 300;
	
	public Frame () {
		
		super("2048");
		
		Screen sc = new Screen();
		sc.setBounds(0, 0, 800, 600);
		add(sc);
		
		addKeyListener(new Keyboard());
		
	}
	
	
	
	private class Screen extends JLabel {
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.setColor(Color.red);
			g.fillRect(posx, posy, 50, 50);
			
			
			
		}
		
		
	}
	
	
	public void repaintFrame() {
		
		repaint();
		
	}

	
	
	
}
