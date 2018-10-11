public interface Killable {
    /**Method signature: default void killPlayer(Player player, Sprite killableSprite){
     *
     * @param player => The reference to player
     * @param killableSprite => The reference to a killable Sprite (implements Killable interface)
     *
     * Description: This default would check whether killableSprite contacts with player.
     *                    If it does, set player.isKilled to true.
     *                    Otherwise, do nothing.
     * */

    default void killPlayer(Player player, Sprite killableSprite){
        // if the killableSprite contacts with the BoundingBox of the next position of the player and not ride on any thing,
        // then make player been killed by setting player.isKilled to true.
        if (killableSprite.getBoundingBox().intersects(player.getNextStepBB())&&!player.isRidden()){
            player.setKilled(true);
        }
        // else => no behaviour affect.
    }
}
