import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

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
		setSize(340, 440);
		
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
		    
			int highscore = game.getHighscore();
			
			
		    
		    final int xRRect1 = Tile.getSMargin();
		    final int yRRect1 = Tile.offsetCoors(game.getWidth());
		    final int wRRect1 = (Tile.getSSize()*2 + Tile.getSMargin());
		    final int hRRect1 = Tile.getSSize();
		    
		    
		    g2d.setColor(Tile.getColor(0));
		    g2d.fillRoundRect(xRRect1, yRRect1, wRRect1, hRRect1, 7, 7);
		    
		    g2d.setColor(new Color(0x776e65));
		    
		    final int RRect1Margin = Tile.getSMargin() / 4;
		    
		    final Font font = new Font(FONT_NAME, Font.BOLD, 20);
		    g2d.setFont(font);
		    
		    String strHeader1 = "Highscore:";
		    final FontMetrics fm = getFontMetrics(font);

		    
		    
		    final int xSHRRect1 = xRRect1 + RRect1Margin;
		    final int ySHRRect1 = yRRect1 + ((hRRect1 - 3*RRect1Margin)/ 2);
		    final int wSHRRect1 = fm.stringWidth(strHeader1);
		    final int hSHRRect1 = -(int) fm.getLineMetrics(strHeader1, g2d).getBaselineOffsets()[2];
		    
	    	g2d.drawString(strHeader1, xSHRRect1, ySHRRect1);
	    	
		    String strScore1 = String.valueOf(highscore);
	    	
	    	final int xSVRRect1 = xRRect1 + RRect1Margin;
		    final int ySVRRect1 = yRRect1 + hRRect1 - (3*RRect1Margin);
		    
		    g2d.drawString(strScore1, xSVRRect1, ySVRRect1);
			
		    
			int score = game.getScore();
			
			
		    
		    final int xRRect2 = (Tile.offsetCoors(game.getWidth()) + Tile.getSMargin()) / 2;
		    final int yRRect2 = Tile.offsetCoors(game.getHeight());
		    final int wRRect2 = (Tile.getSSize()*2 + Tile.getSMargin());
		    final int hRRect2 = Tile.getSSize();
		    
		    
		    g2d.setColor(Tile.getColor(0));
		    g2d.fillRoundRect(xRRect2, yRRect2, wRRect2, hRRect2, 7, 7);
		    
		    g2d.setColor(new Color(0x776e65));
		    
		    final int RRect2Margin = Tile.getSMargin() / 4;
		    
		    
		    String strHeader = "Score:";

		    
		    
		    final int xSHRRect2 = xRRect2 + RRect2Margin;
		    final int ySHRRect2 = yRRect2 + ((hRRect2 - 3*RRect2Margin)/ 2);
		    final int wSHRRect2 = fm.stringWidth(strHeader);
		    final int hSHRRect2 = -(int) fm.getLineMetrics(strHeader, g2d).getBaselineOffsets()[2];
		    
	    	g2d.drawString(strHeader, xSHRRect2, ySHRRect2);
	    	
		    String strScore = String.valueOf(score);
	    	
	    	final int xSVRRect2 = xRRect2 + RRect2Margin;
		    final int ySVRRect2 = yRRect2 + hRRect2 - (3*RRect2Margin);
		    
		    g2d.drawString(strScore, xSVRRect2, ySVRRect2);
		
			if (game.isLost()) {
				
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
				g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
				
				
				g2d.setColor(Color.WHITE);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
				
				String s = ("You have lost\n with a Score of\n " + game.getScore());
				
	            TextLayout textLayout = new TextLayout(s,new Font(FONT_NAME, Font.BOLD, 20), g2d.getFontRenderContext());
	            Rectangle2D bounds = textLayout.getBounds();
	            int x = (int)((getWidth() - bounds.getWidth())/2-bounds.getX());
	            int y = (int)((getHeight() - bounds.getHeight())/2-bounds.getY());
	            textLayout.draw(g2d, x, y);
				
				
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
