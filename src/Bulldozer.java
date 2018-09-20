import org.newdawn.slick.SlickException;

public class Bulldozer extends SolidableVehicle {
    private boolean isContactWithPlayer;
    public Bulldozer(String imageSrc, float x, float y, float speed, boolean isMoveToRight)  {
        super(imageSrc, x, y, speed, isMoveToRight);
        isContactWithPlayer = false;
    }
}

