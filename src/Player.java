import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import utilities.BoundingBox;

import java.util.ArrayList;

public class Player extends Sprite{
    public static final float STEP_SIZE = 48;
    public static final int INITIAL_NUMBER_OF_LIVES = 3; //The number of lives that player has while starting game
    public static final int MIN_NUM_OF_LIFE_TO_PLAY = 1; //The minimun number of lives needed to play
    private boolean isContactWithSolidSprite;
    private boolean isInAHole;
    private boolean isKilled;
    private boolean isGameOver;
    private boolean isRidden;
    private Position nextStep;
    private BoundingBox nextStepBB;
    private ArrayList<Life> lives;

    // The constructor of Player
    public Player(String imgSrc, float x, float y)  {
        super(imgSrc, x, y);
        nextStep = new Position(x, y); // Initialize the next step Position to the start position
                                        // due to that there is no information about next step as game just started
        isContactWithSolidSprite = false;
        isInAHole = false;
        isKilled = false;
        isGameOver = false;
        isRidden = false;

        try {
            nextStepBB = new BoundingBox(new Image(imgSrc), x, y);
        } catch (SlickException e) {
            e.printStackTrace();
        }


        lives = new ArrayList<>();
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
     * none
     *
     * Description: This method update player's current position and the boundingBox
     * */
    public void update (){
        //Take next step if player is not contact with any solid Sprite and not in a hole and not get killed
        if (!this.isContactWithSolidSprite&&!this.isInAHole&&!this.isKilled) {
            super.getPosition().setX(nextStep.getX());
            super.getPosition().setY(nextStep.getY());
        }//else do nothing, stop here since it is a tree! LOL


        // update the bounding box
        super.getBoundingBox().setX(super.getPosition().getX());
        super.getBoundingBox().setY(super.getPosition().getY());
    }

    /** Method signature: public void setContactWithSolidSprite(boolean isContactWithSolidSprite);
     *
     * @param isContactWithSolidSprite The given boolean value to set
     *
     * Description: The setter for the attribute isContactWithTree
     * */
    public void setContactWithSolidSprite(boolean isContactWithSolidSprite) {
        this.isContactWithSolidSprite = isContactWithSolidSprite;
    }

    /** Method signature: public void setInAHole(boolean inAHole)
     *
     * @param inAHole The given boolean value to set
     *
     * Description: The setter for the attribute isInAHole
     * */

    public void setInAHole(boolean inAHole) {
        isInAHole = inAHole;
    }

    /** Method signature: public void setKilled(boolean killed)
     *
     * @param killed The given boolean value to set
     *
     * Description: The setter for the attribute isKilled
     * */
    public void setKilled(boolean killed) {
        isKilled = killed;
    }

    /** Method signature: public void setGameOver(boolean gameOver)
     *
     * @param gameOver The given boolean value to set
     *
     * Description: The setter for the attribute isGameOver
     * */
    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    /** Method signature: public void setRidden(boolean gameOver)
     *
     * @param ridden The given boolean value to set
     *
     * Description: The setter for the attribute isRidden
     * */
    public void setRidden(boolean ridden) {
        isRidden = ridden;
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

    /** Method signature: public boolean isContactWithSolidSprite();
     *
     * no argument
     *
     * Description: The getter of isContactWithSolidSprite
     * */
    public boolean isContactWithSolidSprite() {
        return isContactWithSolidSprite;
    }

    /** Method signature: public boolean isInAHole();
     *
     * no argument
     *
     * Description: The getter of isInAHole
     * */
    public boolean isInAHole() {
        return isInAHole;
    }

    /** Method signature: public boolean isKilled();
     *
     * no argument
     *
     * Description: The getter of isKilled
     * */
    public boolean isKilled() {
        return isKilled;
    }

    /** Method signature: public ArrayList<Life> getLives();
     *
     * no argument
     *
     * Description: The getter of lives
     * */
    public ArrayList<Life> getLives() {
        return lives;
    }


    /** Method signature: public boolean isGameOver();
     *
     * no argument
     *
     * Description: The getter of isGameOver
     * */
    public boolean isGameOver() {
        return isGameOver;
    }

    /** Method signature: public boolean isRidden();
     *
     * no argument
     *
     * Description: The getter of isRidden
     * */
    public boolean isRidden() {
        return isRidden;
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
