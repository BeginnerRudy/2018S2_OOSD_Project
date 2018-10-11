public class Timer {
    // The acceptable error for comparing equality.
    public static final long EPS = 15;
    // The long int for storing time.
    private long time;
    /**
     * Method signature: public Timer();
     *
     * Description: This is the constructor for the Time class.
     *
     * */
    public Timer() {
        // The time starts from 0.
        this.time = 0;
    }

    /**
     * Method signature: public long getTime()
     *
     * Description: The getter for time attribute.
     *
     * @return time
     * */
    public long getTime() {
        return time;
    }

    /**
     * Method signature: public void setTime(long time)
     *
     * Description: The setter for time attribute.
     *
     * @param time The time given to set.
     *
     * */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * Method signature: public boolean areTurtlesAboveWater()
     *
     * Description: This method returns the boolean value for whether the turtles are above or under water.
     *
     * @return The boolean value to indicate whether the turtles are above or under water.
     * */
    public boolean areTurtlesAboveWater(){
        // By taking module of time with respect to the time of a circle of a turtle's above and under water
        // If the turtle is above water, the result would not larger than it should disappear time.
        if ((this.time % Level.TIME_OF_CIRCLE_OF_TURTLES) <= Level.TIME_OF_TURTLE_SHOULD_DISAPPEAR){
            return true;
        }
        return false;
    }


    /**
     * Method signature: public boolean isExtraLifeAvailable(long timeOfExtraLifeToAppear)
     *
     * Description: This method returns the boolean value for whether the turtles are above or under water.
     *
     * @param timeOfExtraLifeToAppear The waiting time of the extra life should appear
     *
     * @return The boolean value to indicate whether the turtles are above or under water.
     * */
    public boolean isExtraLifeAvailable(long timeOfExtraLifeToAppear){
        // By taking module of time with respect to the time of a circle of the extra life is available and unavailable.
        // If the extra life should appear, the result would  larger than its waiting time to appear.
        long timeCircleForExtraLife = timeOfExtraLifeToAppear + ExtraLife.TIME_OF_EXTRALIFE_DISAPPEAR;
        if ((this.time % timeCircleForExtraLife) > timeOfExtraLifeToAppear){
            return true;
        }
        return false;
    }

    /**
     *
     *
     * */
    public boolean isTimeForExtraLifeToMove(boolean isAvailable, long timeOfExtraLifeToAppear){
        long timeCircleForExtraLife = timeOfExtraLifeToAppear + ExtraLife.TIME_OF_EXTRALIFE_DISAPPEAR;
        long timeOfExtraLifeHasAppeared = this.time % timeCircleForExtraLife - timeOfExtraLifeToAppear;
        // if the extra life is available, then decide whether it is time to move or not.
        if (isAvailable &&
                (       // If the extra life has occur more than the waiting time to move
                        (timeOfExtraLifeHasAppeared  > ExtraLife.TIME_OF_EXTRALIFE_MOVE)
                &&      // and if the extra life has waited enough time to move, them return true
                                (timeOfExtraLifeHasAppeared % ExtraLife.TIME_OF_EXTRALIFE_MOVE)  < EPS
                )
        ){
            return true;
        }
        // else return false;
        return false;
    }
}
