public class RideableVehicle extends Vehicle implements Rideable{
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

    /** Method signature: public void updateRideableBehaviour(Player player, int delta);
     *
     * @param player => The reference to player
     * @param delta The milliseconds since last frame passed.
     *
     * Description: This method update the player.isKilled and player.isRidden
     *              according to whether rideableVehicle contact with the player.
     *              If it does, set player.isKilled to false, set player.isRidden to true.
     *              As well as update player's next move.
     *              Otherwise, do nothing.
     * */
    public void updateRideableBehaviour(Player player, int delta){
        playerRide(player, this, delta);
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
