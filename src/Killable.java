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
        // if the killableSprite contacts with the BoundingBox of the next position of the player, then make player killed.
        if (killableSprite.getBoundingBox().intersects(player.getNextStepBB())){
            player.setKilled(true);
        }
        // else case which is that no killableSprite contacts with the player is
        // handled by setting the player.isKilled to false each time update in the update of Class world
    }
}
