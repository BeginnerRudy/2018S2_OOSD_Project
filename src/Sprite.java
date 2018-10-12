import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

public abstract class Sprite {
    private Image image;
    private Position position;
    private BoundingBox bb;

    public Sprite(String imageSrc, float x, float y){
        // Why would the constructor need a path to an image, and a coordinate?
        setupSprite(imageSrc, x, y);

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

    /**Method signature: private void setupSprite(String imageSrc, float x, float y);
     *
     * @param x The given x-coordinate.
     * @param y The given y-coordinate.
     * @param imageSrc The given image source.
     *
     * Description: This method create a image and set the player's position by the given x, y as well as
     *                 create a BoundingBox for it.
     *
     * */
    private void setupSprite(String imageSrc, float x, float y) {
        try {
            image = new Image(imageSrc);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        this.position = new Position(x, y);

        this.bb = new BoundingBox(image, (int)x, (int)y);
    }

    // I am going to override render in concrete class, respectively
    public void render(){
        this.image.drawCentered(this.position.getX(), this.position.getY());
    }


    /**
     * Method signature: public abstract void behaviour(Player player);
     * Description: This is an abstract method, it body part would be overridden in each concrete class.
     *
     * @param player The Player object to make behaviour on.
     * @param delta The milliseconds since last frame is passed.
     * */
    public abstract void behaviour(Player player, int delta);

    /**
     * Method signature: public void update(int delta)
     *
     * Description: This method is to update the position of sprites, by default it do nothing to the sprites
     * */
    public void update(int delta) {
        // How can this one method deal with different types of sprites;
        // By overloading

        // By default, do nothing for those object in the world that is not movable
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