import org.newdawn.slick.SlickException;

public class Tree extends Tile implements Solidable{
    public Tree(String imgSrc, float x, float y) throws SlickException {
        super(imgSrc, x, y);
    }

    /** Method signature: public void update(Player player);
     *
     * @param player => The reference to player
     *
     * Description: This method update the player.isContactWithSolidSprite
     *              according to whether tree contact with the player.
     *              If it does, set this attribute to true.
     *              Otherwise, do nothing.
     * */
    public void update(Player player){
        this.solidPlayer(player, this);
    }

}
