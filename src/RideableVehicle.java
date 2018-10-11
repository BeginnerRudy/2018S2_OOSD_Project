public class RideableVehicle extends Vehicle{
    // define the image reference of log
    private static final String LOG_REFERENCE = "assets/log.png";
    // define the image reference of longlog
    private static final String LONGLOG_REFERENCE = "assets/longlog.png";
    // define the image reference of turtles
    private static final String TURTLES_REFERENCE = "assets/turtles.png";
    // The speed of vehicle log
    private static final float LOG_SPEED = 0.1f;
    // The speed of vehicle longlog
    private static final float LONGLOG_SPEED = 0.07f;
    // The speed of vehicle turtle
    private static final float TURTLE_SPEED = 0.085f;

    public RideableVehicle(String imageSrc, float x, float y, float speed, boolean isMoveToRight)  {
        super(imageSrc, x, y, speed, isMoveToRight);
    }

    /** Method signature: public void behaviour(Player player, int delta);
     *
     * @param player The reference to player
     * @param delta The milliseconds since last frame passed.
     *
     * Description: This method update the player.isKilled and player.isRidden
     *              according to whether rideableVehicle contact with the player.
     *              If it does, set player.isKilled to false, set player.isRidden to true.
     *              As well as update player's next move.
     *              Otherwise, do nothing.
     * */
    public void behaviour(Player player, int delta){
        playerRide(player, delta);
    }

    /**Method signature: void playerRide(Player player, Sprite ridableSprite){
     *
     * @param player  The reference to player
     * @param delta The milliseconds since last frame passed.
     *
     * Description: This default would check whether ridableSprite contacts with player.
     *                    If it does, set player.isRidden to true, and update player.position.
     *                    Otherwise, do nothing.
     * */


    private void playerRide(Player player, int delta){
        // if the rideableSprite contacts with the BoundingBox of the next position of the player, then make player to ride.
        // And, set the player.isRidden to true to represent it.
        if (super.getBoundingBox().intersects(player.getNextStepBB())){
            player.setRidden(true); // make player to ride
        }

        // give player the speed of this rideable vehicle if player rides on a vehicle
        if (player.isRidden()){
            // if the rideable vehicle will not make player off screen, then update it's next step
            if (this.isMoveToRight() &&
                    (player.getNextStep().getX() + (float) player.getImage().getWidth()/2 + this.getSpeed() * delta)<App.SCREEN_WIDTH) {
                player.getNextStep().setX(player.getNextStep().getX() + this.getSpeed() * delta);
            } else if (!this.isMoveToRight() &&
                    (player.getNextStep().getX() - (float) player.getImage().getWidth()/2 - this.getSpeed() * delta) > 0)
            {
                player.getNextStep().setX(player.getNextStep().getX() - this.getSpeed() * delta);
            }
        }
    }

    /**Method signature: public Rideable createALog(float x, float y, float speed, boolean isMoveToRight);
     *
     * @param x The given x-coordinate
     * @param y The given y-coordinate
     * @param isMoveToRight The given moving direction
     *
     * Description: This methods returns a Rideable Vehicle called log.
     * */
    public static RideableVehicle createALog(float x, float y, boolean isMoveToRight){
        return new RideableVehicle(LOG_REFERENCE, x, y, LOG_SPEED, isMoveToRight);
    }

    /**Method signature: public Rideable createALongLog(float x, float y, float speed, boolean isMoveToRight);
     *
     * @param x The given x-coordinate
     * @param y The given y-coordinate
     * @param isMoveToRight The given moving direction
     *
     * Description: This methods returns a Rideable Vehicle called longlog.
     * */
    public static RideableVehicle createALongLog(float x, float y, boolean isMoveToRight){
        return new RideableVehicle(LONGLOG_REFERENCE, x, y, LONGLOG_SPEED, isMoveToRight);
    }

    /**Method signature: public Rideable createATurtle(float x, float y, float speed, boolean isMoveToRight);
     *
     * @param x The given x-coordinate
     * @param y The given y-coordinate
     * @param isMoveToRight The given moving direction
     *
     * Description: This methods returns a Rideable Vehicle called turtle.
     * */
    public static RideableVehicle createATurtle(float x, float y, boolean isMoveToRight){
        return new RideableVehicle(TURTLES_REFERENCE, x, y, TURTLE_SPEED, isMoveToRight);
    }

}
