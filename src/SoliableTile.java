public class SoliableTile extends Tile implements Solidable{
    // define the image reference of tree
    private static final String TREE_REFERENCE = "assets/tree.png";
    public SoliableTile(String imgSrc, float x, float y) {
        super(imgSrc, x, y);
    }
    /**Method signature: public static SoliableTile createATree(float x, float y);
     *
     * @param x The given x-coordinate
     * @param y The given y-coordinate
     *
     * Description: This methods returns a Solidable Tile called water.
     * */
    public static SoliableTile createATree(float x, float y){
        return new SoliableTile(TREE_REFERENCE, x, y);
    }


    /** Method signature: public void behaviour(Player player);
     *
     * @param player  The reference to player
     * @param delta The milliseconds since last frame is passed. It does not make se here,
     *                  but may be used in future development.
     *
     * Description: This method update the player.isContactWithSolidSprite
     *              according to whether solidable Tile contact with the player.
     *              If it does, set this attribute to true.
     *              Otherwise, do nothing.
     * */
    @Override
    public void behaviour(Player player, int delta){
        this.solidPlayer(player, this);
    }

}
