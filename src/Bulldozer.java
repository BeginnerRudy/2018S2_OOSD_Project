import org.newdawn.slick.SlickException;

public class Bulldozer extends Vehicle implements Solidable{
    public Bulldozer(String imageSrc, float x, float y, float speed, boolean isMoveToRight) throws SlickException {
        super(imageSrc, x, y, speed, isMoveToRight);
    }

    /** Method signature: public void updateSolidBehaviour(Player player);
     *
     * @param player => The reference to player
     *
     * Description: This method update the player.isContactWithSolidSprite
     *              according to whether bulldozer contact with the player.
     *              If it does, set this attribute to true.
     *              Otherwise, do nothing.
     * */

    public void updateSolidBehaviour(Player player){
        this.solidPlayer(player, this);
    }
}

