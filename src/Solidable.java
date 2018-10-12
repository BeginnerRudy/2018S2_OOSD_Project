/**
 * Interface: public interface Solidable
 *
 * Description: This interface represent the action of player cannot move into solid object.
 *
 * */
public interface Solidable {
    /**Method signature: default void solidPlayer(Player player, Sprite solidSprite){
     *
     * @param player => The reference to player
     * @param solidSprite => The reference to a solid Sprite (implements Solidable interface)
     *
     * Description: This default would check whether solidSprite contacts with player.
     *                    If it does, set player.isContactWithSolidSprite to true.
     *                    Otherwise, do nothing.
     * */


    default void solidPlayer(Player player, Sprite solidSprite){
        // if the solidSprite contacts with the BoundingBox of the next position of the player,
        // then make player.isContactWithSolidSprite to true to represent it.
        if (solidSprite.getBoundingBox().intersects(player.getNextStepBB())){
            player.setContactWithSolidSprite(true);
        }
        // else => nothing happen
    }
}
