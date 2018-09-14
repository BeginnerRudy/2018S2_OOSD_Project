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
            super.setX(super.getX() + dealt * SPEED);
            if (super.getX() > App.SCREEN_WIDTH + super.getImageWidth()/2){
                super.setX(-super.getImageWidth()/2);
            }
            // move left if the direction is LEFT
        }else if (direction.equals(LEFT)) {
            super.setX(super.getX() - dealt * SPEED);
            if (super.getX() < -super.getImageWidth()/2){
                super.setX(App.SCREEN_WIDTH + super.getImageWidth()/2);
            }
        }

        // update the bounding box
        super.getBoundingBox().setX(super.getX());
        super.getBoundingBox().setY(super.getY());
    }


    // overriding the render method here
    @Override
    public void render(){
        super.getImage().drawCentered(super.getX(),super.getY());
    }

}
