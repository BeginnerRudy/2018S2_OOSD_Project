public class ExtraLife extends Player{
    // The speed of Extra life
    private static final float EXTRA_LIFE_SPEED = Tile.TILE_SIZE;
    // The initial direction of Extra Life
    private static final boolean EXTRA_LIFE_IS_MOVE_TO_RIGHT_INITIAL = true;
    // define the image reference of extra life
    private static final String EXTRALIFE_REFERENCE = "assets/extralife.png";
    private float speed;
    private boolean extraLifeIsMoveToRight;
    private RideableVehicle theLog;
    private float extraLifePositionRelativeToTheLogCenter;

    public ExtraLife(RideableVehicle rideableVehicle) {
        // since the extra life is relatively static to its log
        // In y direction, it is always static, thus, set its y to the log's y
        // In x direction, it may not always static, thus set its x to log's, to represent
        // that at the beginning of the game the extra life is static in x direction.
        super(EXTRALIFE_REFERENCE, rideableVehicle.getPosition().getX(), rideableVehicle.getPosition().getY());
        // since at the beginning,  the extra life cannot move, thus it is relatively static to log's center in x direction
        // Therefore, set it to 0.
        this.extraLifePositionRelativeToTheLogCenter = 0f;
        this.speed = EXTRA_LIFE_SPEED;
        this.extraLifeIsMoveToRight = EXTRA_LIFE_IS_MOVE_TO_RIGHT_INITIAL;
        this.theLog = rideableVehicle;
    }

    /** Method signature: public void updatePlayNextMove(Input input);
     *
     * @param time => The time of the world.
     * @param delta The milliseconds since last frame passed.
     *
     * Description: This methods only updates the extraLifePositionRelativeToTheLogCenter.
     * */
    public void updateExtraLifeRelativeXPosition(int time, int delta){
        float timeOfExtraLifeAppeared = (time%(World.TIME_OF_EXTRA_LIFE_APPEAR+World.TIME_OF_EXTRALIFE_DISAPPEAR) - World.TIME_OF_EXTRA_LIFE_APPEAR);
        // check whether it is the time to let the extra life to take a step on the log
        if ((timeOfExtraLifeAppeared > World.TIME_OF_EXTRALIFE_MOVE) // The extra life has appear more than one move time
                // each time the extra life has appeared more than 2 s, handle the error by delta
                &&((timeOfExtraLifeAppeared % World.TIME_OF_EXTRALIFE_MOVE) < delta)){
            if (this.extraLifeIsMoveToRight){//move to right
                this.setExtraLifePositionRelativeToTheLogCenter(this.getExtraLifePositionRelativeToTheLogCenter()+this.speed);
            }else{// move to left
                this.setExtraLifePositionRelativeToTheLogCenter(this.getExtraLifePositionRelativeToTheLogCenter()-this.speed);
            }
        }
    }


    /**Method signature:  public boolean extraLifeIsMoveToRight();
     *
     * none arguments
     *
     * Description: The getter of extraLifeIsMoveToRight
     * */
    public boolean extraLifeIsMoveToRight() {
        return extraLifeIsMoveToRight;
    }

    /**Method signature: public float getExtraLifePositionRelativeToTheLogCenter();
     *
     * none arguments
     *
     * Description: The getter of extraLifePositionRelativeToTheLogCenter
     * */
    public float getExtraLifePositionRelativeToTheLogCenter() {
        return extraLifePositionRelativeToTheLogCenter;
    }

    /**Method signature:  public RideableVehicle getTheLog();
     *
     * none arguments
     *
     * Description: The getter of theLog
     * */
    public RideableVehicle getTheLog() {
        return theLog;
    }

    /**Method signature:  public void setExtraLifeIsMoveToRight(boolean extraLifeIsMoveToRight);
     *
     * @param extraLifeIsMoveToRight The given boolean value of extraLifeIsMoveToRight
     *
     *
     * Description: The setter of extraLifeIsMoveToRight
     * */
    public void setExtraLifeIsMoveToRight(boolean extraLifeIsMoveToRight) {
        this.extraLifeIsMoveToRight = extraLifeIsMoveToRight;
    }

    /**Method signature:  public void setExtraLifePositionRelativeToTheLogCenter(boolean extraLifePositionRelativeToTheLogCenter);
     *
     * @param extraLifePositionRelativeToTheLogCenter The given boolean value of extraLifePositionRelativeToTheLogCenter
     *
     *
     * Description: The setter of extraLifePositionRelativeToTheLogCenter
     * */
    public void setExtraLifePositionRelativeToTheLogCenter(float extraLifePositionRelativeToTheLogCenter) {
        this.extraLifePositionRelativeToTheLogCenter = extraLifePositionRelativeToTheLogCenter;
    }

    /**
     *
     *
     */
    public void updateNextStep(){}

    /**
     *
     *
     * */
    public void move(float time){

    }
}
