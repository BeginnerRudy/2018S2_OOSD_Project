import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;
import org.newdawn.slick.GameContainer;

public abstract class Sprite {
    private Image image;
    private Position position;
    private BoundingBox bb;

    public Sprite(String imageSrc, float x, float y) throws SlickException {
        // Why would the constructor need a path to an image, and a coordinate?
        image = new Image(imageSrc);
        this.position = new Position(x, y);
        bb = new BoundingBox(image, x, y);

    }

    // I am going to override render in concrete class, respectively
    public void render(){
        this.image.drawCentered(this.position.getX(), this.position.getY());
    }

    //	I am going to create an interface called Updatable instead of this update method.
    public void update(Input input, int delta) {
        // How can this one method deal with different types of sprites;
        // By overloading
        // update the bounding box
        this.bb.setX(this.position.getX());
        this.bb.setY(this.position.getY());
    }


    public void contactSprite(Sprite other) {
        // Should be called when one sprite makes contact with another.
        if (this.bb.intersects(other.bb)) {
            System.exit(0);
        }
    }

    /**
     * function: return the BoundingBox
     */
    public BoundingBox getBoundingBox() {
        return this.bb;
    }

    /**
     * function: return the image
     */
    public Image getImage() {
        return this.image;
    }

    public Position getPosition() {
        return position;
    }
}