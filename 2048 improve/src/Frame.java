import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame {
	
	private static final int TILES_ROUND = 3;
	private static final String FONT_NAME = "Arial";
	private static final Color BG_COLOR = new Color(0xbbada0);
	private static final long serialVersionUID = 1L;
	
	private Game game;

	
	public Frame (Game game) {
		super("2048");
		

		
		addKeyListener(new Keyboard());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(340, 420);
		
		Screen sc = new Screen();
		
		sc.setBounds(0, 0, this.getHeight(), this.getWidth());
		add(sc);
		
		setLocationRelativeTo(null);
		setResizable(false);
		
		setVisible(true);
		
		this.game = game;
		
	}
	
	
	
	private class Screen extends JLabel {
		
		@Override
		protected void paintComponent(Graphics g) {
			
			
			super.paintComponent(g);
		
			Graphics2D g2d = (Graphics2D) g;
			
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		    g2d.setColor(BG_COLOR);
		    g2d.fillRect(0, 0, this.getSize().width, this.getSize().height);
		    
		    for (int x = 0; x < 4; x++) {
		    	
		    	for (int y = 0; y < 4; y++) {
		    		
		    		drawTile(g2d, game.getTile(x, y));
		    		
		    	}
		    	
		    }
		
			
			
			
		}
		
		
	}
	
	
	private void drawTile(Graphics2D g2d, Tile tile) {
		
		int value = tile.getValue();
	    
	    if (tile.isInAnimation())
	    	 g2d.setColor(tile.getColor(0));
	    else 
	    	g2d.setColor(tile.getColor());
	    
	    g2d.fillRoundRect(tile.getX(), tile.getY(), tile.getSize(), tile.getSize(),TILES_ROUND,TILES_ROUND);
	    
	    g2d.setColor(tile.getFGColor());
	    final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
	    
	    final Font font = new Font(FONT_NAME, Font.BOLD, size);
	    g2d.setFont(font);

	    String sValue = String.valueOf(value);
	    final FontMetrics fm = getFontMetrics(font);

	    final int w = fm.stringWidth(sValue);
	    final int h = -(int) fm.getLineMetrics(sValue, g2d).getBaselineOffsets()[2];
	    

	    if (value != 0 && !tile.isInAnimation())
	    	g2d.drawString(sValue, tile.getX() + (tile.getSize() - w) / 2, tile.getY() + tile.getSize() - (tile.getSize() - h) / 2 - 2);
	    
	    
	    
	    
	}
	
	public void repaintFrame() {
		
		repaint();
		
	}

	
	
	
}
