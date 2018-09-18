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
        super.getImage().drawCentered(super.getPosition().getX(),super.getPosition().getY());
    }

    /** The method to update the player's position according which direction key the player pressed
     * @param input -> the input from keyboard
     * */
    public void update (Input input){
        // update the player's move
        this.move(input);

        // update the bounding box
        super.getBoundingBox().setX(super.getPosition().getX());
        super.getBoundingBox().setY(super.getPosition().getY());
    }

    /**
     * The move function is a helper function for the method update above.
     * This function updates the player's position according to the input of directional keys
     * @param input => the input from the keyboard, especially the directional keys
     *
     * */
    private void move(Input input){

        // Move the player stepSize up when the Up key is pressed
        float playerHeadPositionY = super.getPosition().getY()- (float)super.getImage().getHeight()/2; // the y coordinates of player's head
        if (input.isKeyPressed(Input.KEY_UP) && (playerHeadPositionY >= STEP_SIZE)) {
            // if there are at least one STEP_SIZE distance from player's head to the upper bound of the screen
            // take a step of STEP_SIZE
            super.getPosition().setY(super.getPosition().getY() - STEP_SIZE);
        }


        // Move the player stepSize down when the Down key is pressed
        float playerFeetPositionY = super.getPosition().getY() + (float) super.getImage().getHeight()/2; // the y-coordinates of player's feet
        if (input.isKeyPressed(Input.KEY_DOWN) && (App.SCREEN_HEIGHT - playerFeetPositionY >= STEP_SIZE)) {
            // if there are at least one STEP_SIZE distance from player's feet to the lower bound of the screen
            // take a step of STEP_SIZE
            super.getPosition().setY(super.getPosition().getY()+STEP_SIZE);
        }

        // Move the player stepSize left when the Left key is pressed
        float playerLeftHandPositionX = super.getPosition().getX() - (float)super.getImage().getHeight()/2; // the x coordinate of player's left hand
        if (input.isKeyPressed(Input.KEY_LEFT) && (playerLeftHandPositionX >= STEP_SIZE)){
            // if the distance from player's left hand to the left side of the screen is at least one STEP_SIZE, take
            // one STEP_SIZE left
            super.getPosition().setX(super.getPosition().getX()-STEP_SIZE);
        }

        // Move the player stepSize right when the Right key is pressed
        float playerRightHandPositionX = super.getPosition().getX() + (float)super.getImage().getHeight()/2; // the x coordinate of player's right hand
        if (input.isKeyPressed(Input.KEY_RIGHT) && (App.SCREEN_WIDTH - playerRightHandPositionX >= STEP_SIZE)){
            // if the distance from player's right hand to the right side of the screen is at least one STEP_SIZE, take
            // one STEP_SIZE right
            super.getPosition().setX(super.getPosition().getX()+STEP_SIZE);
        }

    }
}
