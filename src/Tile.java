import org.newdawn.slick.SlickException;


public class Tile extends Sprite{

    /** tile size, in pixels */
    public static final float TILE_SIZE = 48;
    public Tile(String imgSrc, float x, float y) throws SlickException {
        super(imgSrc, x, y);
    }


    /*
    * function to draw the tile horizontally on the screen at the Y-coordinator
    *
    * **/
    @Override
    public void render(){
        // repeatably draw the tile on the screen
        // starts from the X offset which is 0
        // until the entire tile is off screen

        super.getImage().drawCentered(super.getX(), super.getY());
//        // In a sentence, the drawSprite of Tile would draw the Tile image on a given horizontal line
//        for (float i = super.getX() + super.getImageWidth()/2;
//             i - super.getImageWidth()/2< App.SCREEN_WIDTH ; i += super.getImageWidth() ){
//            super.getImage().drawCentered(i, super.getY());
//        }
    }
}
