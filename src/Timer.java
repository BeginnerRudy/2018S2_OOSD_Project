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
     * Method signature: public Timer(long time);
     *
     * Description: This is the constructor for the Time class.
     *
     * @param time The given time to initialize.
     * */
    public Timer(long time) {
        // The time starts from 0.
        this.time = time;
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
     * Method signature: public boolean isLargerThan(Timer other)
     *
     * Description: This method returns true if this time is larger than the other time.
     *
     * @param other Another timer object for comparing.
     *
     * */
    public boolean isLargerThan(Timer other){
        return this.time > other.getTime();
    }
}
