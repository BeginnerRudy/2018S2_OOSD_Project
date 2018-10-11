public class ExtraLife extends Sprite{
    // The speed of Extra life
    private static final float EXTRA_LIFE_SPEED = Tile.TILE_SIZE;
    // The initial direction of Extra Life
    private static final boolean EXTRA_LIFE_IS_MOVE_TO_RIGHT_INITIAL = true;

    public static final long TIME_OF_EXTRA_LIFE_APPEAR = 10000;

    public static final long TIME_OF_EXTRALIFE_DISAPPEAR = 14000;

    public static final long TIME_OF_EXTRALIFE_MOVE = 2000;

    // define the image reference of extra life
    private static final String EXTRALIFE_REFERENCE = "assets/extralife.png";
    private float speed;
    private boolean extraLifeIsMoveToRight;
    private RideableVehicle theLog;
    private float extraLifePositionRelativeToTheLogCenter;
    // The boolean value for whether extra life appear
    private boolean ExtraLifeIsAppear = false;
    // indicates whether the extra life is eaten
    private boolean extraLifeIsEaten = false;

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


    /**
     * Method signature: public void behaviour(Player player);
     * Description: This is an empty method, since ... do noting
     *
     * @param player The Player object to make behaviour on
     * @param delta The milliseconds since last frame is passed. It does not make se here,
     *                   but may be used in future development.
     * */
    @Override
    public void behaviour(Player player, int delta){
        // empty, do nothing.
    }

    /** Method signature: public void updatePlayNextMove(Input input);
     *
     * @param time => The time of the world.
     * @param delta The milliseconds since last frame passed.
     *
     * Description: This methods only updates the extraLifePositionRelativeToTheLogCenter.
     * */
    public void updateExtraLifeRelativeXPosition(int time, int delta){
        float timeOfExtraLifeAppeared = (time%(TIME_OF_EXTRA_LIFE_APPEAR+TIME_OF_EXTRALIFE_DISAPPEAR) - TIME_OF_EXTRA_LIFE_APPEAR);
        // check whether it is the time to let the extra life to take a step on the log
        if ((timeOfExtraLifeAppeared > TIME_OF_EXTRALIFE_MOVE) // The extra life has appear more than one move time
                // each time the extra life has appeared more than 2 s, handle the error by delta
                &&((timeOfExtraLifeAppeared % TIME_OF_EXTRALIFE_MOVE) < delta)){
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


    /**Method signature:  public boolean isExtraLifeIsAppear();
     *
     * none arguments
     *
     * Description: The getter of ExtraLifeIsAppear
     *
     * @return ExtraLifeIsAppear The boolean value of ExtraLifeIsAppear
     * */
    public boolean isExtraLifeIsAppear() {
        return ExtraLifeIsAppear;
    }



    /**Method signature:  public boolean isExtraLifeIsEaten();
     *
     * none arguments
     *
     * Description: The getter of extraLifeIsEaten
     *
     * @return extraLifeIsEaten The boolean value of extraLifeIsEaten
     * */
    public boolean isExtraLifeIsEaten() {
        return extraLifeIsEaten;
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
    private void setExtraLifePositionRelativeToTheLogCenter(float extraLifePositionRelativeToTheLogCenter) {
        this.extraLifePositionRelativeToTheLogCenter = extraLifePositionRelativeToTheLogCenter;
    }

    /**Method signature: public void setExtraLifeIsAppear(boolean extraLifeIsAppear)
     *
     * @param extraLifeIsAppear The given boolean value of extraLifeIsAppear
     *
     *
     * Description: The setter of extraLifeIsAppear
     * */
    public void setExtraLifeIsAppear(boolean extraLifeIsAppear) {
        ExtraLifeIsAppear = extraLifeIsAppear;
    }

    /**Method signature:  public void setExtraLifeIsEaten(boolean extraLifeIsEaten);
     *
     * @param extraLifeIsEaten The given boolean value of extraLifeIsEaten
     *
     *
     * Description: The setter of extraLifeIsEaten
     * */
    public void setExtraLifeIsEaten(boolean extraLifeIsEaten) {
        this.extraLifeIsEaten = extraLifeIsEaten;
    }
}
