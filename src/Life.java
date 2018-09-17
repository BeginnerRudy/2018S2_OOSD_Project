import org.newdawn.slick.SlickException;

public class Life extends Sprite{
    private boolean isDead;

    public Life(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
        this.isDead = false;
    }
}
