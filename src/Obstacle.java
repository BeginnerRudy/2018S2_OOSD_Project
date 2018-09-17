import org.newdawn.slick.SlickException;

public class Obstacle extends Sprite{
    public static final float SPEED = 0.15f;
    public static final char LEFT = 'L';
    public static final char RIGHT = 'R';


    // The constructor of Obstacle
    public Obstacle(String imgSrc, float x, float y) throws SlickException {
        super(imgSrc, x, y);
    }

    // overloading the update method here
    public void update(int dealt, Character direction){
        // move right if the direction is RIGHT
        if (direction.equals(RIGHT)) {
            super.getPosition().setX(super.getPosition().getX() + dealt * SPEED);
            if (super.getPosition().getX() > App.SCREEN_WIDTH + super.getImage().getHeight()/2){
                super.getPosition().setX(-(float)super.getImage().getHeight()/2);
            }
            // move left if the direction is LEFT
        }else if (direction.equals(LEFT)) {
            super.getPosition().setX(super.getPosition().getX() - dealt * SPEED);
            if (super.getPosition().getX() < -(float)super.getImage().getHeight()/2){
                super.getPosition().setX(App.SCREEN_WIDTH + (float)super.getImage().getHeight()/2);
            }
        }

        // update the bounding box
        super.getBoundingBox().setX(super.getPosition().getX());
        super.getBoundingBox().setY(super.getPosition().getY());
    }


    // overriding the render method here
    @Override
    public void render(){
        super.getImage().drawCentered(super.getPosition().getX(),super.getPosition().getY());
    }

}
