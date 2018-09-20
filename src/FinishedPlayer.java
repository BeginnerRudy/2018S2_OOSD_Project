import org.newdawn.slick.SlickException;

public class FinishedPlayer extends Sprite implements Solidable{
    public FinishedPlayer(String imageSrc, float x, float y){
        super(imageSrc, x, y);
    }

    /** Method signature: public void update(Player player);
     *
     * @param player => The reference to player
     *
     * Description: This method update the player.isContactWithSolidSprite
     *              according to whether finishedPlayer contact with the player.
     *              If it does, set this attribute to true.
     *              Otherwise, do nothing.
     * */
    public void update(Player player){
        this.solidPlayer(player, this);
    }

}
