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

    /**
     * Method signature: public void behaviour(Player player);
     * Description: This is an empty method, since the grass does not interact with the player
     *
     * @param player The Player object to make behaviour on
     * @param delta The milliseconds since last frame is passed. It does not make se here,
     *                   but may be used in future development.
     * */
    @Override
    public void behaviour(Player player, int delta){
        // empty, do nothing.
    }
}
