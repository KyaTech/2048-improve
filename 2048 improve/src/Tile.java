import java.awt.Color;

public class Tile {
	
	private static final int TILE_SIZE = 64;
	private static final int TILES_MARGIN = 16;
	private static final int TILES_ROUND = 3;

	
	private static int offsetCoors(int d) {
		return d * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN;
	}
	
	
	private int value;
	private int x;
	private int y;
	private int size;
	private boolean inAnimation = false;
	
	public Tile() {
		this.x = 0;
		this.y = 0;
		this.value = 0;
	}
	
	public Tile(int value) {
		this.value = value;
	}
	
	public Tile(int x,int y,int value) {
		this.value = value;
		this.x = x;
		this.y = y;
	}
	
	public Tile(int x,int y) {
		this.x = x;
		this.y = y;
		this.value = 0;
	}
	
	
	
	public int getValue(){
		return value;
	}
	
	protected void setValue(int value) {
		this.value = value;
		
	}
	
	public boolean isEmpty() {
		return value == 0;
	}
		
	public Color getFGColor() {
      return value < 16 ? new Color(0x776e65) :  new Color(0xf9f6f2);
    }
	
	public Color getColor(){	
		return getColor(this.value);
	}
	public static Color getColor(int value){	
		switch (value) {
        case 2:    return new Color(0xeee4da);
        case 4:    return new Color(0xede0c8);
        case 8:    return new Color(0xf2b179);
        case 16:   return new Color(0xf59563);
        case 32:   return new Color(0xf67c5f);
        case 64:   return new Color(0xf65e3b);
        case 128:  return new Color(0xedcf72);
        case 256:  return new Color(0xedcc61);
        case 512:  return new Color(0xedc850);
        case 1024: return new Color(0xedc53f);
        case 2048: return new Color(0xedc22e);
      }
      return new Color(0xcdc1b4);
	}
	
	public int getX() {
		return offsetCoors(this.x);	
	}
	public void setX(int x) {
		this.x = x;	
	}
	public int getY() {
		return offsetCoors(this.y);		
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public boolean isInAnimation() {
		return inAnimation;
	}
	
	public void setInAnimation() {
		inAnimation = true;
	}
	
	public void setNotInAnimation() {
		inAnimation = false;
	}
}
