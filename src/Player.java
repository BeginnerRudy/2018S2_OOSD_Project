import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import utilities.BoundingBox;

public class Player extends Sprite{
    public static final float STEP_SIZE = 48;
    private boolean isContactWithSolidSprite;
    private boolean isInAHole;
    private Position nextStep;
    private BoundingBox nextStepBB;


    // The constructor of Player
    public Player(String imgSrc, float x, float y) throws SlickException{
        super(imgSrc, x, y);
        nextStep = new Position(x, y); // Initialize the next step Position to the start position
                                        // due to that there is no information about next step as game just started
        isContactWithSolidSprite = false;
        isInAHole = false;
        nextStepBB = new BoundingBox(new Image(imgSrc), x, y);
    }

    // overloading the render method here
    // to draw the player image on the screen
    @Override
    public void render(){
        super.getImage().drawCentered(super.getPosition().getX(),super.getPosition().getY());
    }

    /** Method signature: public void updatePlayNextMove(Input input);
     *
     * @param input => The input from keyboard, especially the directional keys.
     *
     * Description: This methods only updates the Position and BoundingBox of the next step.
     * */
    public void updatePlayNextMove(Input input){
        // update the player's next step Position and BoundingBox

        nextStep = this.move(input);
        this.nextStepBB.setX(nextStep.getX());
        this.nextStepBB.setY(nextStep.getY());
    }



    /** Method Signature: public void update (Input input);
     *
     * @param -> none
     *
     * Description: This method update several attributes of Class player which described as followed:
     *              1. The position of the player.
     *                  According to the given input and the contact state with Solid, Killable and Rideable objects in the World.
     *                  a. Solid Objects => Player cannot move into Solid Objects
     *                      i. Tree => When player contacts with a Tree, player cannot move into it.
     *                      ii.  Bulldozer => When player contacts with a Bulldozer, the player cannot go into it as well as it will
     *                          be push to move along with the Bulldoze. Furthermore interested, the Player will die if the Bulldozer
     *                          push the Player out of the Screen.
     * */
    public void update (){
        if (!this.isContactWithSolidSprite && !this.isInAHole) {
            super.getPosition().setX(nextStep.getX());
            super.getPosition().setY(nextStep.getY());
        }//else do nothing, stop here since it is a tree! LOL


        // update the bounding box
        super.getBoundingBox().setX(super.getPosition().getX());
        super.getBoundingBox().setY(super.getPosition().getY());
    }


    // The setter for the attribute isContactWithTree
    public void setContactWithSolidSprite(boolean isContactWithSolidSprite) {
        this.isContactWithSolidSprite = isContactWithSolidSprite;
    }

    // The setter for the attribute isInAHole
    public void setInAHole(boolean inAHole) {
        isInAHole = inAHole;
    }

    /**
     * Method Signature:  private Position move(Input input);
     *
     * @param input => the input from the keyboard, especially the directional keys
     *
     * Description: This methods return the Position of next step of the player depending on the given input.
     * */
    private Position move(Input input){
        Position nextStep = new Position(super.getPosition()); // start with current position

        // Move the player stepSize up when the Up key is pressed
        float playerHeadPositionY = super.getPosition().getY()- (float)super.getImage().getHeight()/2; // the y coordinates of player's head
        // Move the player stepSize down when the Down key is pressed
        float playerFeetPositionY = super.getPosition().getY() + (float) super.getImage().getHeight()/2; // the y-coordinates of player's feet
        // Move the player stepSize left when the Left key is pressed
        float playerLeftHandPositionX = super.getPosition().getX() - (float)super.getImage().getHeight()/2; // the x coordinate of player's left hand
        // Move the player stepSize right when the Right key is pressed
        float playerRightHandPositionX = super.getPosition().getX() + (float)super.getImage().getHeight()/2; // the x coordinate of player's right hand

        if (input.isKeyPressed(Input.KEY_UP) && (playerHeadPositionY >= STEP_SIZE)) {
            // if there are at least one STEP_SIZE distance from player's head to the upper bound of the screen
            // take a step of STEP_SIZE
            nextStep.setY(super.getPosition().getY() - STEP_SIZE);
        }else if (input.isKeyPressed(Input.KEY_DOWN) && (App.SCREEN_HEIGHT - playerFeetPositionY >= STEP_SIZE)) {
            // if there are at least one STEP_SIZE distance from player's feet to the lower bound of the screen
            // take a step of STEP_SIZE
            nextStep.setY(super.getPosition().getY() + STEP_SIZE);
        }else if (input.isKeyPressed(Input.KEY_LEFT) && (playerLeftHandPositionX >= STEP_SIZE)){
            // if the distance from player's left hand to the left side of the screen is at least one STEP_SIZE, take
            // one STEP_SIZE left
            nextStep.setX(super.getPosition().getX()-STEP_SIZE);
        }else if (input.isKeyPressed(Input.KEY_RIGHT) && (App.SCREEN_WIDTH - playerRightHandPositionX >= STEP_SIZE)){
            // if the distance from player's right hand to the right side of the screen is at least one STEP_SIZE, take
            // one STEP_SIZE right
            nextStep.setX(super.getPosition().getX() + STEP_SIZE);
        }else{// if no directional key is pressed, the position of next step should be same as current Position
            nextStep = super.getPosition();
        }
        return nextStep;
    }

    /** Method signature: public BoundingBox getNextStepBB();
     *
     * no argument
     *
     * Description: The getter of NextStepBB
     * */
    public BoundingBox getNextStepBB() {
        return nextStepBB;
    }

    /** Method signature: public Position getNextStep();
     *
     * no argument
     *
     * Description: The getter of nextPosition
     * */
    public Position getNextStep() {
        return nextStep;
    }

    /**Method signature:public void restart(float x, float y);
     *
     * @param x => The given restart x position
     * @param y => The given restart y position
     *
     * Description: This method would reset the player's position to the given restart position.
     * */
    public void restart(float x, float y){
        super.getPosition().setX(x);
        super.getPosition().setY(y);
    }
}
