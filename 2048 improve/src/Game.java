
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class Game {
	
	private static final int HEIGHT = 4;
	private static final int WIDTH = 4;	
	private List<Tile> tiles = new ArrayList<Tile>();
	
	private int score = 0;
	private int highscore = 0;
	
	public Game() {
		
		for (int y = 0; y < HEIGHT; y++) {
		
			for (int x = 0; x <  WIDTH; x++) {
				
				tiles.add(new Tile(x, y));
				
			}
			
		}
		
		getTile(2,3).setValue(2);
		getTile(2,2).setValue(2);
		
	}
	
	public void update() {
		
		if (Keyboard.isKeyDown(KeyEvent.VK_UP) || Keyboard.isKeyDown(KeyEvent.VK_DOWN) || Keyboard.isKeyDown(KeyEvent.VK_RIGHT) || Keyboard.isKeyDown(KeyEvent.VK_LEFT))
			tiles = move();
		
		
		
	}
	
	private List<Tile> move() {
		
		List<Tile> newTiles = clone(tiles);
		
		List<Tile> beforeTiles = clone(tiles);
		
		int keycode = 0;
		
		if (Keyboard.isKeyDown(KeyEvent.VK_UP)) keycode = KeyEvent.VK_UP;
		else if (Keyboard.isKeyDown(KeyEvent.VK_DOWN)) keycode = KeyEvent.VK_DOWN;
		else if (Keyboard.isKeyDown(KeyEvent.VK_RIGHT)) keycode = KeyEvent.VK_RIGHT;
		else if (Keyboard.isKeyDown(KeyEvent.VK_LEFT)) keycode = KeyEvent.VK_LEFT;
				
		
		
		switch (keycode) {	
		
		case KeyEvent.VK_UP:
			
			for(int x = 0; x < WIDTH; x++) {	
						
				//If "lastY" equals -1 it means there is no last Tile in this row!
				int lastY = -1;
		
				//Start at 0 goes to 3
				for (int y = 0; y < HEIGHT; y++) {
							
					lastY = move_extract(x,y,lastY,keycode,newTiles,beforeTiles);
					
				}	
				
			}
			
			break;
		
		case KeyEvent.VK_DOWN:			
			
			for(int x = 0; x < WIDTH; x++) {
				
				int lastY = HEIGHT;
				
				for (int y = HEIGHT - 1; y >= 0; y--) {
					
					lastY = move_extract(x,y,lastY,keycode,newTiles,beforeTiles);
					
				}
				
			}
			
			break;
			
		case KeyEvent.VK_LEFT:
				
			for (int y = 0; y < HEIGHT; y++) {
				
				int lastX = -1;
				
				for(int x = 0; x < WIDTH; x++) {	
					
					lastX = move_extract(x,y,lastX,keycode,newTiles,beforeTiles);
					
			
				}			
				
			}
			
			break;
			
		case KeyEvent.VK_RIGHT:
				
			for (int y = 0; y < HEIGHT; y++) {
				
				int lastX = WIDTH;
				
				for(int x = HEIGHT - 1; x >= 0 ; x--) {	
					
					lastX = move_extract(x,y,lastX,keycode,newTiles,beforeTiles);
					
					
					
				}			
				
			}
			
			
			break;
		default:
			break;
			
	
		}	
		
		
		if (score > highscore) 
			highscore = score;
		

		return newTiles;

		
		
		
	}

	private void printTiles(List<Tile> newTiles) {
		for (int i = 0; i < newTiles.size(); i++) {
			
			Tile t = newTiles.get(i);
			
			System.out.format("Tile %d, x: %d y: %d, value: %d \n",i,t.getXGrid(),t.getYGrid(),t.getValue());
			
		}
	}
	
	
	private List<Tile> clone(List<Tile> tilesToClone) {
		
		List<Tile> newTiles = new ArrayList<Tile>();
		
		for (int i = 0; i < tilesToClone.size(); i++) {
			
			Tile t = tilesToClone.get(i);
			
			newTiles.add(t);
			
			
		}
		
		return newTiles;
		
	}
	
	private int move_extract(int x, int y, int last, int keycode, List<Tile> newTiles, List<Tile> beforeTiles) {
		
		
		
		if (!getTile(x, y, newTiles).isEmpty()){
			
			if(keycode == KeyEvent.VK_UP || keycode == KeyEvent.VK_DOWN) {
				
				if(last != -1 && last != WIDTH && getTile(x, last, newTiles).getValue() == getTile(x, y, newTiles).getValue()) {
					
					getTile(x, last, newTiles).setValue(getTile(x, last, newTiles).getValue() * 2);
					getTile(x, y, newTiles).setValue(0);
					
					score+= getTile(x, last, newTiles).getValue();
					
					System.out.format("%d/%d and %d/%d\n", x,last,x,y);
					
					
				} else {
					
					if (keycode == KeyEvent.VK_UP)
						last++;
					else
						last--;
					
					if ((y - last) != 0) {
					
						getTile(x, last, newTiles).setValue(getTile(x, y, newTiles).getValue());
						getTile(x, y, newTiles).setValue(0);
	
						System.out.format("%d/%d moved to %d/%d\n", x,y,x,last);
						
					
					} else {
						
						System.out.format("%d/%d and %d/%d same pos\n", x,y,x,last);
						
					}
					

				}
									
			} else {
				
				if(last != -1 && last != HEIGHT && getTile(last, y, newTiles).getValue() == getTile(x, y, newTiles).getValue()) {
					
					getTile(last, y, newTiles).setValue(getTile(last, y, newTiles).getValue() * 2);
					getTile(x, y, newTiles).setValue(0);
					
					score+= getTile(last, y, newTiles).getValue();
					
					System.out.format("%d/%d and %d/%d\n", x,last,x,y);
					
					
					
				} else {
					
					if (keycode == KeyEvent.VK_LEFT)
						last++;
					else
						last--;
					
					if ((x - last) != 0) {
					
						getTile(last, y, newTiles).setValue(getTile(x, y, newTiles).getValue());
						getTile(x, y, newTiles).setValue(0);
	
						System.out.format("%d/%d moved to %d/%d\n", x,y,last,y);
						
					
					} else {
						
						System.out.format("%d/%d and %d/%d same pos\n", x,y,last,y);
						
					}
					
				}
				
			}		
		
		}
		
		return last;
	}
	
	public Tile getTile(int x, int y) {
		
		if (x <4 && y <4) return this.tiles.get(y*4+x);
		else return null;
		
	}
	
	private Tile getTile(int x, int y, List<Tile> tiles) {
		
		if (x <WIDTH && y <HEIGHT) return tiles.get(y*4+x);
		else return null;
		
	}
			
			
}
