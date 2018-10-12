import org.lwjgl.Sys;

import javax.swing.text.LabelView;
import java.util.ArrayList;
import java.util.Random;

public class ExtraLife extends Sprite{
    // The speed of Extra life
    private static final float EXTRA_LIFE_SPEED = Tile.TILE_SIZE;
    // The initial direction of Extra Life
    private static final boolean EXTRA_LIFE_IS_MOVE_TO_RIGHT_INITIAL = true;

    // After occurring this amount of time, the extra life object should disappear
    public static final long TIME_OF_EXTRALIFE_DISAPPEAR = 14*Level.SECOND_TO_MILLISECOND;
    // After each amount of this time, the extra object should move, if it is appeared.
    public static final long WAITING_TIME_OF_EXTRALIFE_TO_MOVE = 2*Level.SECOND_TO_MILLISECOND;
    // The max millisecond passed that the extra life should appear
    public static final long MAX_TIME_OF_EXTRA_LIFE_TO_APPEAR = 35*Level.SECOND_TO_MILLISECOND;
    // The min millisecond passed that the extra life should appear
    public static final long MIN_TIME_OF_EXTRA_LIFE_TO_APPEAR = 25*Level.SECOND_TO_MILLISECOND;
    // The start of the time to record
    private static final long TIME_START_TO_RECORD = 0;
    // The initial position of extra life, since at the game start the extra life does not appear immediately
    // Thus, the initial position of the extra life does not mean anything, and I set it to (0, 0).
    public static final int EXTRA_LIFE_INITIAL_X = 0;
    public static final int EXTRA_LIFE_INITIAL_Y = 0;

    // The initial relative position of x of the extra life relative to the center of the log
    public static final float INITIAL_RELATIVE_X = 0f;

    // define the image reference of extra life
    private static final String EXTRALIFE_REFERENCE = "assets/extralife.png";
    private float speed;
    private boolean extraLifeIsMoveToRight;
    private RideableVehicle theLog;
    private float extraLifePositionRelativeToTheLogCenter;
    // The boolean value for whether extra life appear
    private boolean available;
    // The boolean value for whether the extra life has an vehicle to ride
    private boolean isRidden;
    // The time of the extra life object should appear
    private Timer waitingTimeOfExtraLifeToAppear;
    // The time that the extra life object has waited to appear
    private Timer timeHasWaitedToAppear;
    // The time that the extra life object has appeared
    private Timer timeHasOccured;
    // The time that the extra life has waited to move,
    private Timer timeHasWaitedToMove;

    public ExtraLife() {
        // since the extra life is relatively static to its log
        // In y direction, it is always static, thus, set its y to the log's y
        // In x direction, it may not always static, thus set its x to log's, to represent
        // that at the beginning of the game the extra life is static in x direction.

        super(EXTRALIFE_REFERENCE, EXTRA_LIFE_INITIAL_X, EXTRA_LIFE_INITIAL_Y);

        // since at the beginning,  the extra life cannot move, thus it is relatively static to log's center in x direction
        // Therefore, set it to 0.
        this.extraLifePositionRelativeToTheLogCenter = INITIAL_RELATIVE_X;
        this.speed = EXTRA_LIFE_SPEED;
        this.extraLifeIsMoveToRight = EXTRA_LIFE_IS_MOVE_TO_RIGHT_INITIAL;
        // At the beginning, the extra life does not have a log to ride on.
        this.theLog = null;
        this.available = false;
        this.isRidden = false;
        // Generate a random time between 25 and 35 of the extra life should appear.
        this.waitingTimeOfExtraLifeToAppear = new Timer(MIN_TIME_OF_EXTRA_LIFE_TO_APPEAR
        + (long) (Math.random() * (MAX_TIME_OF_EXTRA_LIFE_TO_APPEAR - MIN_TIME_OF_EXTRA_LIFE_TO_APPEAR)));
        this.timeHasOccured = new Timer(TIME_START_TO_RECORD);
        this.timeHasWaitedToAppear = new Timer(TIME_START_TO_RECORD);
        this.timeHasWaitedToMove = new Timer(TIME_START_TO_RECORD);
    }


    /**
     * Method signature: public void behaviour(Player player);
     * Description: This is an empty method, since ... do noting
     *
     * @param player The Player object to make behaviour on
     * @param delta The milliseconds since last frame is passed. It does not make se here,
     *                   but may be used in future development.
     * */
    @Override
    public void behaviour(Player player, int delta){
        // If the player meet the extra life, add one live to player and make the extra unavailable
        if (this.contactSprite(player)){
            this.makeExtraLifeDisappear();
            // add one extra life to player
            player.getLives().add(new Life(Life.LIVES_REFERENCE,
                    Life.INITIAL_X_POSITION +player.getLives().size() * Life.THE_GAP,
                    Life.INITIAL_Y_POSITION));
        }
    }

    /** Method signature: public void updatePlayNextMove(Input input);
     *
     * @param delta The milliseconds since last frame passed.
     *
     * Description: This methods only updates the extraLifePositionRelativeToTheLogCenter.
     * */
    public void updateExtraLifeRelativeXPosition(int delta){
        // if the extra life has already waiting enough time, the let it take a step
        if (this.timeHasWaitedToMove.getTime() > WAITING_TIME_OF_EXTRALIFE_TO_MOVE){
            // reset the waiting time of move of the extra life object
            this.timeHasWaitedToMove.setTime(TIME_START_TO_RECORD);

            // decide whether the next step of the extra life would fall from the log
            // if it does, reverse the moving direction.
            if (this.extraLifeIsMoveToRight){//move to right
                this.getBoundingBox().setX(this.getPosition().getX() + this.speed + this.extraLifePositionRelativeToTheLogCenter);
                // if it would move off the log, make it move to left
                if (!this.contactSprite(this.theLog)){
                    this.extraLifeIsMoveToRight = false;
                }
            }else{// move to left
                this.getBoundingBox().setX(this.getPosition().getX() - this.speed + this.extraLifePositionRelativeToTheLogCenter);
                // if it would move off the log, make it move to right
                if (!this.contactSprite(this.theLog)){
                    this.extraLifeIsMoveToRight = true;
                }
            }
            // Testing finish, set bounding box back.
            this.getBoundingBox().setX(this.getPosition().getX());

            // now update the relative location of the extra life relative to the center of the log.
            if (this.extraLifeIsMoveToRight){//move to right
                this.extraLifePositionRelativeToTheLogCenter+=this.speed;
            }else{// move to left
                this.extraLifePositionRelativeToTheLogCenter-=this.speed;
            }
        }else{// The extra life need to keep waiting to make a move
            this.timeHasWaitedToMove.setTime(this.timeHasWaitedToMove.getTime() + delta);
        }

    }
    /**
     *
     *
     * */
    public void update(int delta, Player player, ArrayList<Sprite> logs){
        // if the extra life is not available.
        if (!this.available){
            // if the extra life has already waiting enough time, then make it available
            if(this.timeHasWaitedToAppear.getTime() > this.waitingTimeOfExtraLifeToAppear.getTime()){
                this.available = true;
                // reset it waited time to appear to TIME_START_TO_RECORD, that is 0 millisecond.
                this.timeHasWaitedToAppear.setTime(TIME_START_TO_RECORD);
                // choose a log for the extra life to ride on.
                Random random = new Random();
                this.theLog = (RideableVehicle) logs.get(random.nextInt(logs.size() - 1));
            }else{// if not, keep waiting.
                this.timeHasWaitedToAppear.setTime(this.timeHasWaitedToAppear.getTime() + delta);
            }
        }

        // if the extra life is available
        if (this.available){
            // decide whether the extra life has already appeared enough time to disappear.
            // if the time is enough to disappear, then make it not available
            if (this.timeHasOccured.getTime() > TIME_OF_EXTRALIFE_DISAPPEAR){
                this.makeExtraLifeDisappear();
            }else {// if not, keep occurring and record the time.
                this.timeHasOccured.setTime(this.timeHasOccured.getTime() + delta);
                // update the position of the extra life

                // First, give it the position or the moving of the log that it ride on.
                this.getPosition().setX(this.theLog.getPosition().getX());
                this.getPosition().setY(this.theLog.getPosition().getY());
                this.getBoundingBox().setX(this.getPosition().getX());
                this.getBoundingBox().setY(this.getPosition().getY());

                // Then, update the move of the extra life
                this.updateExtraLifeRelativeXPosition(delta);

                // Finally, update the move of extra life to its position
                this.getPosition().setX(this.getPosition().getX() + this.extraLifePositionRelativeToTheLogCenter);
                this.getBoundingBox().setX(this.getPosition().getX());
            }
        }
    }

    /**
     * Method signature: public boolean isAvailable()
     *
     * Description: The getter for available attribute.
     *
     * @return The boolean value that indicates whether the extra life is available.
     * */
    public boolean isAvailable() {
        return available;
    }


    /**
     * Method signature: public void makeExtraLifeDisappear()
     *
     * Description: This method set the condition when the extra life becoming not available.
     *
     * */
    public void makeExtraLifeDisappear(){
        this.available = false;
        this.extraLifeIsMoveToRight = EXTRA_LIFE_IS_MOVE_TO_RIGHT_INITIAL;
        this.theLog = null;
        this.extraLifePositionRelativeToTheLogCenter = INITIAL_RELATIVE_X;
        this.timeHasOccured.setTime(TIME_START_TO_RECORD);
        this.timeHasWaitedToMove.setTime(TIME_START_TO_RECORD);
        // reset the random time of waiting to appear for the extra life.
        this.waitingTimeOfExtraLifeToAppear.setTime(MIN_TIME_OF_EXTRA_LIFE_TO_APPEAR
                + (long) (Math.random() * (MAX_TIME_OF_EXTRA_LIFE_TO_APPEAR - MIN_TIME_OF_EXTRA_LIFE_TO_APPEAR)));
    }
}