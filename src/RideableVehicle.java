import org.newdawn.slick.SlickException;

public class RideableVehicle extends Vehicle{
    public RideableVehicle(String imageSrc, float x, float y, float speed, boolean isMoveToRight) throws SlickException {
        super(imageSrc, x, y, speed, isMoveToRight);
    }
}
