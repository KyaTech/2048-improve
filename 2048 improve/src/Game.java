
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game {
	
	private static final int HEIGHT = 4;
	private static final int WIDTH = 4;	
	private static final int MAX_FPS = 60;
	private List<Tile> tiles = new ArrayList<Tile>();
	
	private int score = 0;
	private int highscore = 0;
	
	private boolean console = false;
	
	private Random random;
	
	private boolean loose;
	
	public Game() {
		
		for (int y = 0; y < HEIGHT; y++) {
		
			for (int x = 0; x <  WIDTH; x++) {
				
				tiles.add(new Tile(x, y));
				
			}
			
		}
		
		random = new Random();
		
		spawnNewTile(tiles);
		spawnNewTile(tiles);
		
	}
	
	public void restartGame() {
		
		loose = false;
		score = 0;
		
		for (Tile tile : tiles) {
			tile.setValue(0);
		}
		
		spawnNewTile(tiles);
		spawnNewTile(tiles);
		
		
	}
	
	public static int getMaxFps() {
		return MAX_FPS;
	}
	
	public static int getHeight() {
		return HEIGHT;
	}
	
	public static int getWidth() {
		return WIDTH;
	}
	
	public void update(float timeSinceLastFrame) {
		
		if (Keyboard.isKeyDown(KeyEvent.VK_ESCAPE))
			restartGame();
		
		if (Keyboard.isKeyDown(KeyEvent.VK_0)) {
			console = !console;
			if (console) System.out.println("Console activated.");
		}
		
		if (loose) return; 
		
		if (!canMove()) {
			loose = true;
			return;
			
		}
		
		tiles = move();
		

		
	}
	
	private List<Tile> move() {
		
		List<Tile> newTiles = clone(tiles);
		
		List<Tile> beforeTiles = clone(tiles);
		
		int keycode = 0;
		
		if (Keyboard.isKeyDown(KeyEvent.VK_UP)) {
			keycode = KeyEvent.VK_UP;
		}
		else if (Keyboard.isKeyDown(KeyEvent.VK_DOWN)) {
			keycode = KeyEvent.VK_DOWN;
		}
		else if (Keyboard.isKeyDown(KeyEvent.VK_RIGHT)) {
			keycode = KeyEvent.VK_RIGHT;
		}
		else if (Keyboard.isKeyDown(KeyEvent.VK_LEFT)) {
			keycode = KeyEvent.VK_LEFT;
		}
			

		
		
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
			return this.tiles;
			
	
		}	
		
		
		
		if (isSame(newTiles, beforeTiles)) return newTiles;
		
		if (score > highscore) 
			highscore = score;
		
		spawnNewTile(newTiles);
		if (console) System.out.println("Yeah im here");
		
		if (console) System.out.println("--------------------------");
		

		return newTiles;

		
		
		
	}
	
	private boolean isSame(List<Tile> firstList, List<Tile> secondList) {

		
		boolean isSame = true;
		
		if (firstList.size() == secondList.size()) {
			
			for (int i = 0; i < firstList.size(); i++) {
				
				Tile t1 = firstList.get(i);
				Tile t2 = secondList.get(i);
				
				if (t1.getValue() != t2.getValue()) {
					isSame = false;
					return isSame;
				}
				
			}
			
			
			
		} else {
			
			isSame = false;
			
		}
		
		return isSame;
	}

//	private void printTiles(List<Tile> newTiles) {
//		for (int i = 0; i < newTiles.size(); i++) {
//			
//			Tile t = newTiles.get(i);
//			
//			if (console) System.out.format("Tile %d, x: %d y: %d, value: %d \n",i,t.getXGrid(),t.getYGrid(),t.getValue());
//			
//		}
//	}
	
	
	private List<Tile> clone(List<Tile> tilesToClone) {
		
		List<Tile> newTiles = new ArrayList<Tile>();
		
		for (Tile tile : tilesToClone) {
			newTiles.add(new Tile(tile.getXGrid(), tile.getYGrid(), tile.getValue()));
		}
		
		return newTiles;
		
	}
	
	private boolean canMove() {
		
		if (getEmptySpace(tiles).size() > 0)
			return true;
		
		for (int x = 0; x < 4; x++) {
		      for (int y = 0; y < 4; y++) {
		        if ((x < 3 && getTile(x, y).getValue() == getTile(x+1, y).getValue()) || (y < 3 && getTile(x, y).getValue() == getTile(x, y+1).getValue())) {
		          return true;
		        }
		      }
		    }
	    return false;
		
	}
	
	private int move_extract(int x, int y, int last, int keycode, List<Tile> newTiles, List<Tile> beforeTiles) {
		
		
		
		if (!getTile(x, y, newTiles).isEmpty()){
			
			if(keycode == KeyEvent.VK_UP || keycode == KeyEvent.VK_DOWN) {
				
				if(last != -1 && last != WIDTH && getTile(x, last, newTiles).getValue() == getTile(x, y, newTiles).getValue()) {
					
					getTile(x, last, newTiles).setValue(getTile(x, last, newTiles).getValue() * 2);
					getTile(x, y, newTiles).setValue(0);
					
					score+= getTile(x, last, newTiles).getValue();
					
					if (console) System.out.format("%d/%d and %d/%d\n", x,last,x,y);
					
					
				} else {
					
					if (keycode == KeyEvent.VK_UP)
						last++;
					else
						last--;
					
					if ((y - last) != 0) {
					
						getTile(x, last, newTiles).setValue(getTile(x, y, newTiles).getValue());
						getTile(x, y, newTiles).setValue(0);
	
						if (console) System.out.format("%d/%d moved to %d/%d\n", x,y,x,last);
						
					
					}
					

				}
									
			} else {
				
				if(last != -1 && last != HEIGHT && getTile(last, y, newTiles).getValue() == getTile(x, y, newTiles).getValue()) {
					
					getTile(last, y, newTiles).setValue(getTile(last, y, newTiles).getValue() * 2);
					getTile(x, y, newTiles).setValue(0);
					
					score+= getTile(last, y, newTiles).getValue();
					
					if (console) System.out.format("%d/%d and %d/%d\n", x,last,x,y);
					
					
					
				} else {
					
					if (keycode == KeyEvent.VK_LEFT)
						last++;
					else
						last--;
					
					if ((x - last) != 0) {
					
						getTile(last, y, newTiles).setValue(getTile(x, y, newTiles).getValue());
						getTile(x, y, newTiles).setValue(0);
	
						if (console) System.out.format("%d/%d moved to %d/%d\n", x,y,last,y);
						
					
					}
					
				}
				
			}		
		
		}
		
		return last;
	}
	
	private void spawnNewTile(List<Tile> newTiles) {
		
		List<Tile> emptyTiles = getEmptySpace(newTiles);
		
		Tile tile = emptyTiles.get(random.nextInt(emptyTiles.size()));
		
		int percentage = random.nextInt(10);
		
		
		int value = percentage < 6 ? 2 : 4;
		tile.setValue(value);
			
		if (console) System.out.format("New Tile Spawn at (%d/%d)\n",tile.getXGrid(), tile.getYGrid());
		
	
	}

	private List<Tile> getEmptySpace(List<Tile> newTiles) {
		List<Tile> emptyTiles = new ArrayList<Tile>();
		
		for (Tile tile : newTiles) {
			if (tile.isEmpty()) emptyTiles.add(tile);
		}
		return emptyTiles;
	}
	
	public Tile getTile(int x, int y) {
		
		if (x <4 && y <4) return this.tiles.get(y*4+x);
		else return null;
		
	}
	
	private Tile getTile(int x, int y, List<Tile> tiles) {
		
		if (x <WIDTH && y <HEIGHT) return tiles.get(y*4+x);
		else return null;
		
	}
	
	public boolean isLost() {
		return loose;
	}
	
	public int getHighscore() {
		return highscore;
	}
	
	public int getScore() {
		return score;
	}
			
			
}
