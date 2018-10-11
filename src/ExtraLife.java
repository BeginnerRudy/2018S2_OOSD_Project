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
    public static final long TIME_OF_EXTRALIFE_MOVE = 2*Level.SECOND_TO_MILLISECOND;
    // The max millisecond passed that the extra life should appear
    public static final long MAX_TIME_OF_EXTRA_LIFE_TO_APPEAR = 35*Level.SECOND_TO_MILLISECOND;
    // The min millisecond passed that the extra life should appear
    public static final long MIN_TIME_OF_EXTRA_LIFE_TO_APPEAR = 25*Level.SECOND_TO_MILLISECOND;
    // The initial position of extra life, since at the game start the extra life does not appear immediately
    // Thus, the initial position of the extra life does not mean anything, and I set it to (0, 0).
    public static final int EXTRA_LIFE_INITIAL_X = 0;
    public static final int EXTRA_LIFE_INITIAL_Y = 0;

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
    private long timeOfExtraLifeToAppear;

    public ExtraLife() {
        // since the extra life is relatively static to its log
        // In y direction, it is always static, thus, set its y to the log's y
        // In x direction, it may not always static, thus set its x to log's, to represent
        // that at the beginning of the game the extra life is static in x direction.

        super(EXTRALIFE_REFERENCE, EXTRA_LIFE_INITIAL_X, EXTRA_LIFE_INITIAL_Y);

        // since at the beginning,  the extra life cannot move, thus it is relatively static to log's center in x direction
        // Therefore, set it to 0.
        this.extraLifePositionRelativeToTheLogCenter = 0f;
        this.speed = EXTRA_LIFE_SPEED;
        this.extraLifeIsMoveToRight = EXTRA_LIFE_IS_MOVE_TO_RIGHT_INITIAL;
        // At the beginning, the extra life does not have a log to ride on.
        this.theLog = null;
        this.available = false;
        this.isRidden = false;
        // Generate a random time between 25 and 35 of the extra life should appear.
//        this.timeOfExtraLifeToAppear = MIN_TIME_OF_EXTRA_LIFE_TO_APPEAR
//        + (long) (Math.random() * (MAX_TIME_OF_EXTRA_LIFE_TO_APPEAR - MIN_TIME_OF_EXTRA_LIFE_TO_APPEAR));
        this.timeOfExtraLifeToAppear = 1000;
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
            this.available = false;
        }
    }

    /** Method signature: public void updatePlayNextMove(Input input);
     *
     * @param time => The time of the world.
     *
     * Description: This methods only updates the extraLifePositionRelativeToTheLogCenter.
     * */
    public void updateExtraLifeRelativeXPosition(Timer time){
        if (time.isTimeForExtraLifeToMove(this.available, this.timeOfExtraLifeToAppear)){
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
        }
        // else => do nothing
    }
    /**
     *
     *
     * */
    public void update(Timer time, Player player, ArrayList<Sprite> logs){
        // decide whether the extra life is available
        this.available = time.isExtraLifeAvailable(timeOfExtraLifeToAppear);
        // if the extralife is available, make it appear.
        if (this.available){
            // if the extra life does not has a object to ride, give it one.
            if (!this.isRidden){
                // chose the log to ride on randomly from the logs
                Random random = new Random();
                this.theLog = (RideableVehicle) logs.get(random.nextInt(logs.size() - 1));
                this.isRidden = true;
            }

            // update the position of the extra life
            // update the position of player due to log, that is the player should move with the log first.
            if (theLog!=null){
                this.getPosition().setX(this.theLog.getPosition().getX());
                this.getPosition().setY(this.theLog.getPosition().getY());
                this.getBoundingBox().setX(this.getPosition().getX());
                this.getBoundingBox().setY(this.getPosition().getY());
            }
            // update its x position according to the log's center.
            this.updateExtraLifeRelativeXPosition(time);
            // update the position of extra life with respect to it relative location to the center of the log
            this.getPosition().setX(this.getPosition().getX() + this.extraLifePositionRelativeToTheLogCenter);
            this.getBoundingBox().setX(this.getPosition().getX());

        }else{
            // Since the extra life is not available, thus it does not ride on any thing.
            this.isRidden = false;
            this.extraLifeIsMoveToRight = EXTRA_LIFE_IS_MOVE_TO_RIGHT_INITIAL;
            this.extraLifePositionRelativeToTheLogCenter = 0f;
            this.theLog = null;
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
     * Method signature:  public void setTimeOfExtraLifeToAppear(long timeOfExtraLifeToAppear)
     *
     * Description: The setter for timeOfExtraLifeToAppear
     *
     * @param timeOfExtraLifeToAppear The given value of timeOfExtraLifeToAppear.
     *
     * */
    public void setTimeOfExtraLifeToAppear(long timeOfExtraLifeToAppear) {
        this.timeOfExtraLifeToAppear = timeOfExtraLifeToAppear;
    }
}
