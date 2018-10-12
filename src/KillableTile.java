/**
 * Class: public class KillableTile extends Tile implements Killable
 *
 * Description: This class represents the Killable tile in the game, that is the water tile.
 *              This class takes the responsibility of updating the behaviour of the Killable Tile.
 * */
public class KillableTile extends Tile implements Killable{
    /** define the image reference of water*/
    private static final String WATER_REFERENCE = "assets/water.png";

    /**Method signature: public KillableTile(String imgSrc, float x, float y)
     *
     * @param imgSrc The image reference.
     * @param x The given x-coordinate
     * @param y The given y-coordinate
     *
     * Description: This methods returns a Killable Vehicle called bike.
     * */
    public KillableTile(String imgSrc, float x, float y) {
        super(imgSrc, x, y);
    }

    /**Method signature: public static KillableTile createAWater(float x, float y);
     *
     * @param x The given x-coordinate
     * @param y The given y-coordinate
     * @return A water object
     * Description: This methods returns a Killable Tile called water.
     * */
    public static KillableTile createAWater(float x, float y){
        return new KillableTile(WATER_REFERENCE, x, y);
    }


    /** Method signature: public void behaviour(Player player);
     *
     * @param player  The reference to player
     * @param delta The milliseconds since last frame is passed. It does not make se here,
     *                         but may be used in future development.
     *
     * Description: This method update the player.isKilled
     *              according to whether killable Tile contact with the player.
     *              If it does, set this attribute to true.
     *              Otherwise, do nothing.
     * */
    @Override
    public void behaviour(Player player, int delta){
        killPlayer(player, this);
    }
}
