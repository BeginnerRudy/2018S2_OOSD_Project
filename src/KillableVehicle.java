/**
 * Class: public class KillableVehicle extends Vehicle implements Killable
 *
 * Description: This KllableVehicle class represent the killable vehicle in the game, that are the bus, racecar.
 *              This class is inheriting the Vehicle class and implement the Killable interface.
 *              This class take the responsibility of updating the position and the behaviour of the killable vehicles.
 *
 * */
public class KillableVehicle extends Vehicle implements Killable {
    /** define the image reference of bus*/
    private static final String BUS_REFERENCE = "assets/bus.png";
    /** define the image reference of racecar*/
    private static final String RACECAR_REFERENCE = "assets/racecar.png";
    /** The speed of vehicle bus*/
    public static final float BUS_SPEED = 0.15f;
    /** The speed of vehicle racecar*/
    public static final float RACECAR_SPEED = 0.5f;


    /**
     * Method signature: public KillableVehicle(String imageSrc, float x, float y, float speed, boolean isMoveToRight)
     *
     * @param imageSrc The reference path of image
     * @param x The x position of the killable vehicle
     * @param y The y position of the killable vehicle
     * @param isMoveToRight The boolean indicates whether the killable vehicle is move to right.
     * @param speed The given speed of the killable vehicle.\
     *
     * Description: This is the constructor of the class KillableVehicle.
     * */
    public KillableVehicle(String imageSrc, float x, float y, float speed, boolean isMoveToRight) {
        super(imageSrc, x, y, speed, isMoveToRight);
    }

    /**Method signature: public KillableVehicle createABus(float x, float y, float speed, boolean isMoveToRight);
     *
     * @param x The given x-coordinate
     * @param y The given y-coordinate
     * @param isMoveToRight The given moving direction
     * @return A bus object
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
     * @return A racecar object.
     *
     * Description: This methods returns a Killable Vehicle called racecar.
     * */
    public static KillableVehicle createARacecar(float x, float y, boolean isMoveToRight){
        return new KillableVehicle(RACECAR_REFERENCE, x, y, RACECAR_SPEED, isMoveToRight);
    }

    /** Method signature: public void behaviour(Player player);
     *
     * @param player  The reference to player
     * @param delta The milliseconds since last frame is passed. It does not make se here,
     *                   but may be used in future development.
     *
     * Description: This method update the player.isKilled
     *              according to whether killableVehicle contact with the player.
     *              If it does, set this attribute to true.
     *              Otherwise, do nothing.
     *
     * */
    @Override
    public void behaviour(Player player, int delta){
        killPlayer(player, this);
    }
}
