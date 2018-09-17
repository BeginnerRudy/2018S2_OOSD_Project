import org.newdawn.slick.SlickException;


public class Tile extends Sprite {

    /**
     * tile size, in pixels
     */
    public static final float TILE_SIZE = 48;

    public Tile(String imgSrc, float x, float y) throws SlickException {
        super(imgSrc, x, y);
    }

}
