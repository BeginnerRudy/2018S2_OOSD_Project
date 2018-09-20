import org.newdawn.slick.SlickException;


public class Tile extends Sprite {
    // define the image reference of grass
    private static final String GRASS_REFERENCE = "assets/grass.png";

    /**
     * tile size, in pixels
     */
    public static final float TILE_SIZE = 48;

    public Tile(String imgSrc, float x, float y){
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

    /**Method signature: public static Tile createAGrass(float x, float y);
     *
     * @param x The given x-coordinate
     * @param y The given y-coordinate
     *
     * Description: This methods returns a Tile called grass.
     * */
    public static Tile createAGrass(float x, float y){
        return new Tile(GRASS_REFERENCE, x, y);
    }

}
