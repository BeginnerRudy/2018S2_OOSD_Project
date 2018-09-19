import org.newdawn.slick.SlickException;


public class Tile extends Sprite {

    /**
     * tile size, in pixels
     */
    public static final float TILE_SIZE = 48;

    public Tile(String imgSrc, float x, float y) throws SlickException {
        super(imgSrc, x, y);
    }

    /**Method signature: public Tile(Tile copy);
     *
     * @param copy The reference to copy
     *
     * Description: This a copy constructor for Tile
     * */
    public Tile(Tile copy){
        super(copy);
    }

}
