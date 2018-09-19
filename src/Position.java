public class Position {
    private float x;
    private float y;
    private static final float EPSILON = 0.000001f; // EPSILON is the constant for the accepted error between two coordinates
    public Position(float x, float y){
        this.x = x;
        this.y = y;
    }

    // The copy constructor
    public Position(Position copy){
        x = copy.getX();
        y = copy.getY();
    }
    // return x
    public float getX() {
        return x;
    }
    // return y
    public float getY() {
        return y;
    }
    // modify x
    public void setX(float x) {
        this.x = x;
    }
    // modify y
    public void setY(float y) {
        this.y = y;
    }

    /**Method signature: public boolean equalsY(Position other);
     *
     * @param other => The reference to another Position to be check for equality.
     *
     * Description: This method returns true if two y coordinates differ less than EPSILON.
     *              Otherwise, returns false.
     * */
    public boolean equalsY(Position other){
        return (Math.abs(this.getY()-other.getY())) <= EPSILON;
    }

    /** Method signature: public Position midPoint(Position other);
     *
     * @param other => The reference to another Position to be check for equality.
     *
     * Description: This method returns a Position represents the midpoint between this and other Position.
     * */
    public Position midPoint(Position other){
        return (new Position((this.getX()+other.getX())/2, (this.getY()+other.getY())/2));
    }

}
