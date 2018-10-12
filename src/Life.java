/**
 * Class: public class Life extends Sprite
 *
 * Description: This Life class represents the life of the player.
 *
 * */
public class Life extends Sprite{
    public static final float INITIAL_X_POSITION = 24;
    public static final float INITIAL_Y_POSITION = 744;
    public static final float THE_GAP = 32;
    /** define the image reference of lives*/
    public static final String LIVES_REFERENCE = "assets/lives.png";
    /**
     * Method signature: public Life(String imageSrc, float x, float y)
     *
     * @param x The given x position
     * @param y The given y position
     * @param imageSrc The image reference
     * */
    public Life(String imageSrc, float x, float y){
        super(imageSrc, x, y);
    }


    /**
     * Method signature: public void behaviour(Player player);
     * Description: This is an empty method, since the life's behaviour is do nothing.
     *
     * @param player The Player object to make behaviour on
     * @param delta The milliseconds since last frame is passed. It does not make se here,
     *                    but may be used in future development.
     * */
    @Override
    public void behaviour(Player player, int delta){
        // empty, do nothing.
    }
}
