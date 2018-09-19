import org.newdawn.slick.SlickException;

public class Water extends Tile implements Killable{
    public Water(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }


    /** Method signature: public void update(Player player);
     *
     * @param player => The reference to player
     *
     * Description: This method update the player.isKilled
     *              according to whether water contact with the player.
     *              If it does, set this attribute to true.
     *              Otherwise, do nothing.
     * */
    public void update(Player player){
        killPlayer(player, this);
    }
}
