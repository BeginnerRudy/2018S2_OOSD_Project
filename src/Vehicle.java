import org.newdawn.slick.SlickException;

public class Vehicle extends Sprite{
    private static final float RIGHT_EDGE_OF_SCREEN = 0;
    private float speed;
    private boolean isMoveToRight;

    public Vehicle(String imageSrc, float x, float y, float speed, boolean isMoveToRight){
        super(imageSrc, x, y);
        this.speed = speed;
        this.isMoveToRight = isMoveToRight;
    }

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
            if (headOfVehicle < RIGHT_EDGE_OF_SCREEN){// If the vehicle is out of left side screen, make it appear at right side of screen
                super.getPosition().setX(App.SCREEN_WIDTH+(float)this.getImage().getWidth()/2);
            }else {// update the x-coordinate according to the speed and delta
                super.getPosition().setX(this.getPosition().getX() - speed*delta);
            }
        }


        // update the bounding box
        this.getBoundingBox().setX(this.getPosition().getX());
        this.getBoundingBox().setY(this.getPosition().getY());
    }

    /** Method signature: public void setSpeed(float speed);
     *
     * @param speed The given speed value to set
     *
     * Description: The setter for the attribute speed
     * */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

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
     * none
     *
     * Description: The getter for the attribute speed
     * */
    public float getSpeed() {
        return speed;
    }

    /** Method signature: public boolean isMoveToRight();
     *
     * none
     *
     * Description: The getter for the attribute isMoveToRight
     * */
    public boolean isMoveToRight() {
        return isMoveToRight;
    }
}
