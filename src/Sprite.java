import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

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
    /**Method signature: public Sprite(Sprite copy);
     *
     * @param copy The reference to copy
     *
     * Description: This a copy constructor for Sprite
     * */
    public Sprite(Sprite copy){
        image = copy.getImage();
        position = copy.getPosition();
        bb = copy.getBoundingBox();
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


    /**
     * Method signature: public boolean contactSprite(Sprite other);
     *
     * @param other => another Sprite objects to test contacting
     *
     * This method returns boolean value.
     * If the two Sprite's bounding box contacted then return true.
     * Otherwise, return false.
     * */
    public boolean contactSprite(Sprite other) {
        // Should be called when one sprite makes contact with another.
        if (this.bb.intersects(other.bb)) {
            return true;
        }
        return false;
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