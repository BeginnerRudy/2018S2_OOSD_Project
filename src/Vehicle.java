/**
 * Class: public abstract class Vehicle extends Sprite
 *
 * Description: This Vehicle represent the vehicle in the game.
 *              This class is inheriting from the class Sprite.
 * */
public abstract class Vehicle extends Sprite{
    /**The attribute represents the speed of the vehicle*/
    private float speed;
    /** The attribute represents whether the vehicle is moving to right.*/
    private boolean isMoveToRight;

    /**
     * Method signature: public Vehicle(String imageSrc, float x, float y, float speed, boolean isMoveToRight)
     *
     * @param imageSrc The reference path of image
     * @param x The x position of the vehicle
     * @param y The y position of the vehicle
     * @param isMoveToRight The boolean indicates whether the vehicle is move to right.
     * @param speed The given speed of the vehicle.\
     *
     * Description: This is the constructor of the class KillableVehicle.
     * */
    public Vehicle(String imageSrc, float x, float y, float speed, boolean isMoveToRight){
        super(imageSrc, x, y);
        this.speed = speed;
        this.isMoveToRight = isMoveToRight;
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
        if (this.isMoveToRight){
            float tailOfVehicle = super.getPosition().getX()-(float)super.getImage().getWidth()/2;
            if (tailOfVehicle > App.SCREEN_WIDTH){// If the vehicle is out of right side screen, make it appear at left side of screen
                super.getPosition().setX(-(float)super.getImage().getWidth()/2);
            }else {// update the x-coordinate according to the speed and delta
                super.getPosition().setX(super.getPosition().getX() + speed*delta);
            }
        }else{
            float headOfVehicle = this.getPosition().getX()+(float)this.getImage().getWidth()/2;
            if (headOfVehicle < App.SCREEN_LEFT_BOUND){// If the vehicle is out of left side screen, make it appear at right side of screen
                super.getPosition().setX(App.SCREEN_WIDTH+(float)this.getImage().getWidth()/2);
            }else {// update the x-coordinate according to the speed and delta
                super.getPosition().setX(this.getPosition().getX() - speed*delta);
            }
        }


        // update the bounding box
        this.getBoundingBox().setX(this.getPosition().getX());
        this.getBoundingBox().setY(this.getPosition().getY());
    }

    /**
     * Method signature: public abstract void behaviour(Player player);
     * Description: This is an abstract method, it body part would be overridden in each concrete class.
     *
     * @param player The Player object to make behaviour on.
     * @param delta The milliseconds since last frame is passed. It does not make se here,
     *                         but may be used in future development.
     * */
    public abstract void behaviour(Player player, int delta);

    /** Method signature: public void setMoveToRight(boolean moveToRight);
     *
     * @param moveToRight The given boolean value to set
     *
     * Description: The setter for the attribute isMoveToRight
     * */
    public void setMoveToRight(boolean moveToRight) {
        isMoveToRight = moveToRight;
    }

    /** Method signature: public float getSpeed();
     *
     * @return The spped of the vehicle.
     *
     * Description: The getter for the attribute speed
     * */
    public float getSpeed() {
        return speed;
    }

    /** Method signature: public boolean isMoveToRight();
     *
     * @return The boolean value indicates whether the vehicle is moving to right.
     *
     * Description: The getter for the attribute isMoveToRight
     * */
    public boolean isMoveToRight() {
        return isMoveToRight;
    }
}
