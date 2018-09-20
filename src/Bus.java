import org.newdawn.slick.SlickException;

public class Bus extends Vehicle implements Killable{
    public Bus(String imageSrc, float x, float y, float speed, boolean isMoveToRight) throws SlickException {
        super(imageSrc, x, y, speed, isMoveToRight);
    }
    /** Method signature: public void updateKillableBehaviour(Player player);
     *
     * @param player => The reference to player
     *
     * Description: This method update the player.isKilled
     *              according to whether bus contact with the player.
     *              If it does, set this attribute to true.
     *              Otherwise, do nothing.
     * */
    public void updateKillableBehaviour(Player player){
        killPlayer(player, this);
    }
}
