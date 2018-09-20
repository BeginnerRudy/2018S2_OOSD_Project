public interface Rideable {
    /**Method signature: default void playerRide(Player player, Sprite ridableSprite){
     *
     * @param player => The reference to player
     * @param ridableSprite => The reference to a ridableSprite Sprite (implements Rideable interface)
     * @param delta The milliseconds since last frame passes.
     *
     * Description: This default would check whether ridableSprite contacts with player.
     *                    If it does, set player.isRidden to true, and update player.position.
     *                    Otherwise, do nothing.
     * */


    default void playerRide(Player player, RideableVehicle ridableSprite, int delta){
        // if the killableSprite contacts with the BoundingBox of the next position of the player, then make player to ride.
        if (ridableSprite.getBoundingBox().intersects(player.getNextStepBB())){
            player.setRidden(true); // make player to ride
        }
        // else case which is that no ridableSprite contacts with the player is
        // handled by setting the player.isKilled to false each time update in the update of Class world

        // give plater the speed of this rideable vehicle if player rides on a vehicle
        if (player.isRidden()) {
            if (ridableSprite.isMoveToRight()) {
                player.getNextStep().setX(player.getNextStep().getX() + ridableSprite.getSpeed() * delta);
            } else {
                player.getNextStep().setX(player.getNextStep().getX() - ridableSprite.getSpeed() * delta);
            }
        }
    }
}
