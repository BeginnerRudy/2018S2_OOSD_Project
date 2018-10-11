
public class FinishedPlayer extends Sprite implements Solidable{
    public FinishedPlayer(float x, float y){
        super(Player.PLAYER_REFERENCE, x, y);
    }

    /** Method signature: public void behaviour(Player player);
     *
     * @param player The reference to player
     * @param delta The milliseconds since last frame is passed. It does not make se here,
     *             but may be used in future development.
     *
     * Description: This method update the player.isContactWithSolidSprite
     *              according to whether finishedPlayer contact with the player.
     *              If it does, set this attribute to true.
     *              Otherwise, do nothing.
     * */
    @Override
    public void behaviour(Player player, int delta){
        this.solidPlayer(player, this);
    }
}
