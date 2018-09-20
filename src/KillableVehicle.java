import org.newdawn.slick.SlickException;

public class KillableVehicle extends Vehicle implements Killable {
    // define the image reference of bus
    private static final String BUS_REFERENCE = "assets/bus.png";
    // define the image reference of bike
    private static final String BIKE_REFERENCE = "assets/bike.png";
    // define the image reference of racecar
    private static final String RACECAR_REFERENCE = "assets/racecar.png";
    // The speed of vehicle bus
    private static final float BUS_SPEED = 0.15f;
    // The speed of vehicle racecar
    private static final float RACECAR_SPEED = 0.5f;
    // The speed of vehicle bike
    private static final float BIKE_SPEED = 0.2f;

    public KillableVehicle(String imageSrc, float x, float y, float speed, boolean isMoveToRight) {
        super(imageSrc, x, y, speed, isMoveToRight);
    }

    /**Method signature: public KillableVehicle createABus(float x, float y, float speed, boolean isMoveToRight);
     *
     * @param x The given x-coordinate
     * @param y The given y-coordinate
     * @param isMoveToRight The given moving direction
     *
     * Description: This methods returns a Killable Vehicle called bus.
     * */
    public static KillableVehicle createABus(float x, float y, boolean isMoveToRight){
        return new KillableVehicle(BUS_REFERENCE, x, y, BUS_SPEED, isMoveToRight);
    }

    /**Method signature: public KillableVehicle createABus(float x, float y, float speed, boolean isMoveToRight);
     *
     * @param x The given x-coordinate
     * @param y The given y-coordinate
     * @param isMoveToRight The given moving direction
     *
     * Description: This methods returns a Killable Vehicle called racecar.
     * */
    public static KillableVehicle createARacecar(float x, float y, boolean isMoveToRight){
        return new KillableVehicle(RACECAR_REFERENCE, x, y, RACECAR_SPEED, isMoveToRight);
    }

    /**Method signature: public KillableVehicle createABus(float x, float y, float speed, boolean isMoveToRight);
     *
     * @param x The given x-coordinate
     * @param y The given y-coordinate
     * @param isMoveToRight The given moving direction
     *
     * Description: This methods returns a Killable Vehicle called bike.
     * */
    public static KillableVehicle createABike(float x, float y, boolean isMoveToRight){
        return new KillableVehicle(BIKE_REFERENCE, x, y, BIKE_SPEED, isMoveToRight);
    }

    /** Method signature: public void updateKillableBehaviour(Player player);
     *
     * @param player => The reference to player
     *
     * Description: This method update the player.isKilled
     *              according to whether killableVehicle contact with the player.
     *              If it does, set this attribute to true.
     *              Otherwise, do nothing.
     * */
    public void updateKillableBehaviour(Player player){
        killPlayer(player, this);
    }
}
