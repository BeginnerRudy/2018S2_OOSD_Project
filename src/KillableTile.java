public class KillableTile extends Tile implements Killable{ // define the image reference of water
    private static final String WATER_REFERENCE = "assets/water.png";

    public KillableTile(String imgSrc, float x, float y) {
        super(imgSrc, x, y);
    }

    /**Method signature: public static KillableTile createAWater(float x, float y);
     *
     * @param x The given x-coordinate
     * @param y The given y-coordinate
     *
     * Description: This methods returns a Killable Tile called water.
     * */
    public static KillableTile createAWater(float x, float y){
        return new KillableTile(WATER_REFERENCE, x, y);
    }
    /** Method signature: public void update(Player player);
     *
     * @param player => The reference to player
     *
     * Description: This method update the player.isKilled
     *              according to whether killable Tile contact with the player.
     *              If it does, set this attribute to true.
     *              Otherwise, do nothing.
     * */
    public void update(Player player){
        killPlayer(player, this);
    }
}
