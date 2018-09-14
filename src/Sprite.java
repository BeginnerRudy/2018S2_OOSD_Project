import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;
import org.newdawn.slick.GameContainer;

public abstract class Sprite {
    private Image image;
    private float x;
    private float y;
    private float imageHeight;
    private float imageWidth;
    private BoundingBox bb;

	public Sprite(String imageSrc, float x, float y) throws SlickException {
		// Why would the constructor need a path to an image, and a coordinate?
        image = new Image(imageSrc);
        this.x = x;
        this.y = y;
        this.imageHeight = image.getHeight();
        this.imageWidth = image.getWidth();
        bb = new BoundingBox(image, x, y);

	}

	// I am going to override render in concrete class, respectively
	public abstract void render();

//	I am going to create an interface called Updatable instead of this update method.
	public void update (Input input, int delta) {
        // How can this one method deal with different types of sprites;
        // By overloading
        // update the bounding box
        this.bb.setX(this.x);
        this.bb.setY(this.y);
    }

	
	public void contactSprite(Sprite other) {
		// Should be called when one sprite makes contact with another.
        if (this.bb.intersects(other.bb)){
            System.exit(0);
        }
	}

	/**
     * function: return the x value
     *
     * */
	public float getX(){
	    return this.x;
    }

    /**
     * function: return the y value
     *
     * */
    public float getY(){
        return this.y;
    }

    /**
     * function: return the height of image
     *
     * */
    public float getImageHeight(){
        return this.imageHeight;
    }

    /**
     * function: return the width of image
     *
     * */
    public float getImageWidth(){
        return this.imageWidth;
    }

    /**
     * function: return the BoundingBox
     *
     * */
    public BoundingBox getBoundingBox(){
        return this.bb;
    }

    /**
     * function: return the image
     *
     * */
    public Image getImage(){
        return this.image;
    }

    /**
     * setter to modify the value of x
     * */

    public void setX(float x){
        this.x = x;
    }

    /**
     * setter to modify the value of x
     * */

    public void setY(float y){
        this.y = y;
    }
}
