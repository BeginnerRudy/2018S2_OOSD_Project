public class KillableAndReversableVehicle extends KillableVehicle{
    // The speed of vehicle bike
    public static final float BIKE_SPEED = 0.2f;
    // define the image reference of bike
    private static final String BIKE_REFERENCE = "assets/bike.png";
    // The x-coordinate for bike to reverse direction
    public static final float BIKE_REVERSE_24 = 24f;
    public static final float BIKE_REVERSE_1000 = 1000f;

    public KillableAndReversableVehicle(String imageSrc, float x, float y, float speed, boolean isMoveToRight) {
        super(imageSrc, x, y, speed, isMoveToRight);
    }




    /**Method signature: public KillableAndReversableVehicle createABike(float x, float y, float speed, boolean isMoveToRight);
     *
     * @param x The given x-coordinate
     * @param y The given y-coordinate
     * @param isMoveToRight The given moving direction
     *
     * Description: This methods returns a Killable Vehicle called bike.
     * */
    public static KillableAndReversableVehicle createABike(float x, float y, boolean isMoveToRight){
        return new KillableAndReversableVehicle(BIKE_REFERENCE, x, y, BIKE_SPEED, isMoveToRight);
    }

    /**
     * Method signature: public void update(int delta)
     *
     * Description: This is a method that override the one in its superclass Sprite.
     * This method update the position of Vehicle with according to delta
     *
     * @param delta The milliseconds since last frame passed
     * */
    @Override
    public void update(int delta){

        // update the reversable behaviour of killable and reversable vehicle
        // if the killable and reversable vehicle is less than 24, make it move to right
        if (super.getPosition().getX() < BIKE_REVERSE_24){
            super.setMoveToRight(true);
        }// if the killable and reversable vehicle is larger than 1000, make it move to left
        else if (super.getPosition().getX() > BIKE_REVERSE_1000){
            super.setMoveToRight(false);
        }

        if (super.isMoveToRight()){
            float tailOfVehicle = super.getPosition().getX()-(float)super.getImage().getWidth()/2;
            if (tailOfVehicle > App.SCREEN_WIDTH){// If the vehicle is out of right side screen, make it appear at left side of screen
                super.getPosition().setX(-(float)super.getImage().getWidth()/2);
            }else {// update the x-coordinate according to the speed and delta
                super.getPosition().setX(super.getPosition().getX() + super.getSpeed()*delta);
            }
        }else{
            float headOfVehicle = this.getPosition().getX()+(float)this.getImage().getWidth()/2;
            if (headOfVehicle < App.SCREEN_LEFT_BOUND){// If the vehicle is out of left side screen, make it appear at right side of screen
                super.getPosition().setX(App.SCREEN_RIGHT_BOUND+(float)this.getImage().getWidth()/2);
            }else {// update the x-coordinate according to the speed and delta
                super.getPosition().setX(this.getPosition().getX() - super.getSpeed()*delta);
            }
        }


        // update the bounding box
        this.getBoundingBox().setX(this.getPosition().getX());
        this.getBoundingBox().setY(this.getPosition().getY());
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
     *              As well as, reverse the direction of vehicle, when it reaches 24 or 1000.
     *
     * */
    @Override
    public void behaviour(Player player, int delta){
        // update the killable behaviour of killable and reversable vehicle
        // The reversable behaviour has already be handled in the update method.
        killPlayer(player, this);

    }
}
