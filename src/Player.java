import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

public class Player extends Sprite{
    public static final float STEP_SIZE = 48;

    // The constructor of Player
    public Player(String imgSrc, float x, float y) throws SlickException{
        super(imgSrc, x, y);
    }

    // overloading the render method here
    // to draw the player image on the screen
    @Override
    public void render(){
        super.getImage().drawCentered(super.getX(),super.getY());
    }

    /** The method to update the player's position according which direction key the player pressed
     * @param input -> the input from keyboard
     * */
    public void update (Input input){
        // Move the player stepSize up when the Up key is pressed
        float playerHeadPositionY = super.getY() - super.getImageHeight()/2; // the y coordinates of player's head
        if (input.isKeyPressed(Input.KEY_UP) && (playerHeadPositionY >= STEP_SIZE)) {
            // if there are at least one STEP_SIZE distance from player's head to the upper bound of the screen
            // take a step of STEP_SIZE
            super.setY(super.getY() - STEP_SIZE);
        }


        // Move the player stepSize down when the Down key is pressed
        float playerFeetPositionY = super.getY() + super.getImageHeight()/2; // the y-coordinates of player's feet
        if (input.isKeyPressed(Input.KEY_DOWN) && (App.SCREEN_HEIGHT - playerFeetPositionY >= STEP_SIZE)) {
            // if there are at least one STEP_SIZE distance from player's feet to the lower bound of the screen
            // take a step of STEP_SIZE
            super.setY(super.getY()+STEP_SIZE);
        }

        // Move the player stepSize left when the Left key is pressed
        float playerLeftHandPositionX = super.getX() - super.getImageWidth()/2; // the x coordinate of player's left hand
        if (input.isKeyPressed(Input.KEY_LEFT) && (playerLeftHandPositionX >= STEP_SIZE)){
            // if the distance from player's left hand to the left side of the screen is at least one STEP_SIZE, take
            // one STEP_SIZE left
            super.setX(super.getX()-STEP_SIZE);
        }

        // Move the player stepSize right when the Right key is pressed
        float playerRightHandPositionX = super.getX()+ super.getImageWidth()/2; // the x coordinate of player's right hand
        if (input.isKeyPressed(Input.KEY_RIGHT) && (App.SCREEN_WIDTH - playerRightHandPositionX >= STEP_SIZE)){
            // if the distance from player's right hand to the right side of the screen is at least one STEP_SIZE, take
            // one STEP_SIZE right
            super.setX(super.getX()+STEP_SIZE);
        }

        // update the bounding box
        super.getBoundingBox().setX(super.getX());
        super.getBoundingBox().setY(super.getY());
    }
}
