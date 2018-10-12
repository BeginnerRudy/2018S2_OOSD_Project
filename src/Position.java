/**
 * Class: public class Position
 *
 * Description: This Position class represents the position of the object in the game world.
 *
 * */
public class Position {
    /**The x position*/
    private float x;
    /**The y position*/
    private float y;
    /** EPSILON is the constant for the accepted error between two coordinates*/
    private static final float EPSILON = 0.000001f;
    /**
     * Method signature: public Position(float x, float y)
     *
     * @param x The given x position.
     * @param y The given y position.
     *
     * Description: This is the constructor of the Position Class.
     * */
    public Position(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Method signature: public Position(Position copy)
     *
     * @param copy The position object to copy
     *
     * Description: The copy constructor of Position class
     * */
    public Position(Position copy){
        x = copy.getX();
        y = copy.getY();
    }

    /**
     * Method signature: public float getX()
     *
     * @return The x position
     *
     * Description: The getter for x position.
     * */
    public float getX() {
        return x;
    }

    /**
     * Method signature: public float getY()
     *
     * @return The y position
     *
     * Description: The getter for y position.
     * */
    public float getY() {
        return y;
    }

    /**
     * Method signature: public void setX(float x)
     *
     * @param x The given x position to set.
     *
     * Description: The setter for x position.
     * */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Method signature: public void setY(float y)
     *
     * @param y The given y position to set.
     *
     * Description: The setter for y position.
     * */
    public void setY(float y) {
        this.y = y;
    }

    /**Method signature: public boolean equalsY(Position other);
     *
     * @param other => The reference to another Position to be check for equality.
     * @return The boolean value that indicates whether two position is equal.
     * Description: This method returns true if two y coordinates differ less than EPSILON.
     *              Otherwise, returns false.
     * */
    public boolean equalsY(Position other){
        return (Math.abs(this.getY()-other.getY())) <= EPSILON;
    }

    /** Method signature: public Position midPoint(Position other);
     *
     * @param other => The reference to another Position to be check for equality.
     * @return The mid point of two positions.
     * Description: This method returns a Position represents the midpoint between this and other Position.
     * */
    public Position midPoint(Position other){
        return (new Position((this.getX()+other.getX())/2, (this.getY()+other.getY())/2));
    }

}
