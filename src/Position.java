public class Position {
    private float x;
    private float y;
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
}
