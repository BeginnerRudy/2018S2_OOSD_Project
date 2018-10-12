import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import utilities.BoundingBox;

import java.util.ArrayList;

/**
 * Class: public class Player extends Sprite
 *
 * Description: This Player class represent the player object in this game.
 *              It takes the responsibility of update the position of the player
 *              and all the other attributes of the player.
 * */
public class Player extends Sprite{
    /** define the image reference of player*/
    public static final String PLAYER_REFERENCE = "assets/frog.png";
    /** PLAYER_INITIAL_X is the initial x coordinator of player*/
    public static final float PLAYER_INITIAL_X = 512;
    /** PLAYER_INITIAL_Y is the initial y coordinator of player*/
    public static final float PLAYER_INITIAL_Y = 720;
    /** The step size that a player should take for each move*/
    public static final float STEP_SIZE = 48;
    /** The number of lives that the player should have at the beginning of the game*/
    public static final int INITIAL_NUMBER_OF_LIVES = 3;
    /** The minimum number of lives needed to play*/
    public static final int MIN_NUM_OF_LIFE_TO_PLAY = 0;

    /** The private attributes of player */

    /** The attribute to indicates that whether the player is contacting with a solid sprite*/
    private boolean isContactWithSolidSprite;
    /** The attribute to indicates that whether the player is in a hole*/
    private boolean isInAHole;
    /** The attribute to indicates that whether the player is killed*/
    private boolean isKilled;
    /** The attribute to indicates that whether the game is over*/
    private boolean isGameOver;
    /** The attribute to indicates that whether the player is riding on rideable sprite*/
    private boolean isRidden;
    /** The attribute to represent the next step position that the player would make*/
    private Position nextStep;
    /** The attribute to represent the next step BoundingBox that the player would be*/
    private BoundingBox nextStepBB;
    /** The attribute to represent the live status of the player*/
    private ArrayList<Life> lives;

    /* ********************************* Methods ******************************************/
    /**
     * Method signature: public Player()
     *
     * Description: This is the constructor for the class Player, it initialize the attribute of the player.
     * */
    public Player()  {
        // call the super class's to initialize parts of the attributes
        super(PLAYER_REFERENCE, PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
        // Initialize the next step Position to the start position,
        // due to that there is no information about next step as game just started
        nextStep = new Position(PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
        // At the start of the game, all the boolean attributes are false.
        isContactWithSolidSprite = false;
        isInAHole = false;
        isKilled = false;
        isGameOver = false;
        isRidden = false;
        // initialize the bb of the next step
        try {
            nextStepBB = new BoundingBox(new Image(PLAYER_REFERENCE), PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        // Initialize the lives
        lives = new ArrayList<>();
        // Give the player the initial number of lives, that is 3 lives at beginning.
        for (int i = 0; i<INITIAL_NUMBER_OF_LIVES; i++) {
            lives.add(new Life(Life.LIVES_REFERENCE,
                    Life.INITIAL_X_POSITION +lives.size() * Life.THE_GAP,
                    Life.INITIAL_Y_POSITION));
        }
    }

    /**
     * Method signature: public void behaviour(Player player);
     * Description: This is an empty method, since the player has no behaviour on itself.
     *
     *
     * @param player The Player object to make behaviour on
     * @param delta The milliseconds since last frame is passed. It does not make se here,
     *                         but may be used in future development.
     * */
    @Override
    public void behaviour(Player player, int delta){
        // empty, do nothing.
    }

    /**
     * Method signature: public void render()
     *
     * Description: This method overloading the render method here,  to draw the player image on the screen and its lives.
     * */
    @Override
    public void render(){
        super.getImage().drawCentered(super.getPosition().getX(),super.getPosition().getY());
        // draw the lives
        if (lives!=null) {
            for (Life life : lives) {
                life.render();
            }
        }
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



        // update the position and life of player if it is killed
        if (this.isKilled()) {
            if (this.getLives() != null) {
                if (this.getLives().size() > Player.MIN_NUM_OF_LIFE_TO_PLAY) {
                    // remove the leftmost life
                    this.getLives().remove(this.getLives().size() - 1);
                    //restart the player's position
                    this.restart();
                }else{// not enough life to play, GameOver
                    this.setGameOver(true);
                }
            }
        }
    }

    /**
     * Method Signature:  private Position move(Input input);
     *
     * @param input => the input from the keyboard, especially the directional keys
     * @return The position of the player's next step depending on the input.
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
     * @retu
     * Description: The setter for the attribute isRidden
     * */
    public void setRidden(boolean ridden) {
        isRidden = ridden;
    }


    /** Method signature: public BoundingBox getNextStepBB();
     *
     * @return The BoundingBox of next step of the player.
     *
     * Description: The getter of NextStepBB
     * */
    public BoundingBox getNextStepBB() {
        return nextStepBB;
    }

    /** Method signature: public Position getNextStep();
     *
     * @return The position of the player's next step.
     *
     * Description: The getter of nextPosition
     * */
    public Position getNextStep() {
        return nextStep;
    }

    /** Method signature: public boolean isContactWithSolidSprite();
     *
     * @return The boolean attributes of whether the player is contact with any solid sprites
     *
     * Description: The getter of isContactWithSolidSprite
     * */
    public boolean isContactWithSolidSprite() {
        return isContactWithSolidSprite;
    }

    /** Method signature: public boolean isInAHole();
     *
     * @return The boolean attributes of whether the player is in a hole.
     *
     * Description: The getter of isInAHole
     * */
    public boolean isInAHole() {
        return isInAHole;
    }

    /** Method signature: public boolean isKilled();
     *
     * @return The boolean attributes of whether the player is killed.
     *
     * Description: The getter of isKilled
     * */
    public boolean isKilled() {
        return isKilled;
    }

    /** Method signature: public boolean isGameOver();
     *
     * @return The boolean attributes of whether the player is game over.
     *
     * Description: The getter of isGameOver
     *
     * @return The boolean value of is game over.
     * */
    public boolean isGameOver() {
        return isGameOver;
    }

    /** Method signature: public ArrayList<Life> getLives();
     *
     * @return The live ArrayList of the player.
     *
     * Description: The getter of lives
     * */
    public ArrayList<Life> getLives() {
        return lives;
    }

    /** Method signature: public boolean isRidden();
     *
     * @return The boolean attributes of whether the player is riding on any rideablr objects.
     *
     * Description: The getter of isRidden
     * */
    public boolean isRidden() {
        return isRidden;
    }

    /**Method signature:public void restart(float x, float y);
     *
     *
     * Description: This method would reset the player's position to the given restart position.
     * */
    public void restart(){
        super.getPosition().setX(PLAYER_INITIAL_X);
        super.getPosition().setY(PLAYER_INITIAL_Y);
    }

    /**
     * Method signature: public void resetPlayerBooleanBeforeUpdate()
     *
     * Description: This method set the isRidden, isKilled, isContactWithSprite and isInHole to false. Because for each
     *              update in the world, we need to detect these boolean and decide whether to set to true.
     * */
    public void resetPlayerBooleanBeforeUpdate(){
        this.isRidden = false;
        this.isKilled = false;
        this.isContactWithSolidSprite = false;
        this.isInAHole = false;
    }
}