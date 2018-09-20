import org.newdawn.slick.SlickException;

public class Turtle extends RideableVehicle{
    public Turtle(String imageSrc, float x, float y, float speed, boolean isMoveToRight) throws SlickException {
        super(imageSrc, x, y, speed, isMoveToRight);
    }
}
