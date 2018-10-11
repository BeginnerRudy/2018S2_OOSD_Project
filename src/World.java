import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import java.util.ArrayList;
import java.util.Random;


public class World {
    /*Define CONSTANT*/
    // Layer order means the things has smaller index will be drawn first.
    String[] KEYS_IN_LAYER_ORDER = new String[]{Level.GRASS, Level.WATER, Level.TREE,
            Level.BUS, Level.BULLDOZER, Level.RACECAR, Level.BIKE,
            Level.LOGS, Level.FINISHED_PLAYER};
    public static final int NUM_OF_RUNS_TO_NEXT_LEVEL = 5;
    // The convert between second to millisecond
    public static final int SECOND_TO_MILLISECOND = 1000;

    // The period that the turtle should be in the water
    public static final int TIME_OF_TURTLE_SHOULD_DISAPPEAR = 7*SECOND_TO_MILLISECOND;
    // The period that the turtle should be on the water
    public static final int TIME_OF_TURTLE_SHOULD_APPEAR = 2*SECOND_TO_MILLISECOND;
//    //The period that extra life appear
//    public static final int TIME_OF_EXTRA_LIFE_APPEAR = 5000;
//    // The period that extra life should disappear
//    public static final int TIME_OF_EXTRALIFE_DISAPPEAR = SECOND_TO_MILLISECOND*14;
//    // The period that extra life should move
//    public static final int TIME_OF_EXTRALIFE_MOVE = SECOND_TO_MILLISECOND*2;



    /*Define Sprites and Background*/
    // declare the player
    private Player player;
    // declare the level of the world
    private Level level;

    // the total time of the world (in milliseconds)
    private int time;
//    // the extra life
//    private ExtraLife extraLife;


	public World(){
        //initialize the attribute of world
        this.level = new Level(Level.LEVEL1_REFERENCE, Level.LEVEL_0);
        //initialize the player
        player = new Player();
        //initialize the lives of player
        this.time = 0;
	}
	
	public void update(Input input, int delta){
	    // check is it the time to turn to next level
        int numOfRunsFinished = 0;
        if (level.getLevel().get(Level.FINISHED_PLAYER)!=null) {
            numOfRunsFinished = level.getLevel().get(Level.FINISHED_PLAYER).size();
        }
        boolean isFinishedAll = (numOfRunsFinished  == NUM_OF_RUNS_TO_NEXT_LEVEL);

        // if current level is finished, turn to next level
        if (isFinishedAll) {
            level.setCurrentLevel(this.updateLevel(isFinishedAll));

            // swap to new level according to current level
            if (this.level.getCurrentLevel() == Level.LEVEL_2) {
                System.exit(0);
            } else if (this.level.getCurrentLevel() == Level.LEVEL_1) {
                // reinitialize all the Tiles, Vehicles, FinishedPlayers and ExtraLife from current "background"
                level = new Level(Level.LEVEL1_REFERENCE, Level.LEVEL_1);
                // extraLife = null;
                time = 0;

            } else if (this.level.getCurrentLevel() == Level.LEVEL_0) {
                // do nothing, since we start with level 0.
            }
        }

        // reset the boolean values of player at the beginning of updating
        player.resetPlayerBooleanBeforeUpdate();


        // update the position and boundingbox of player's next position depends on input
        player.updatePlayNextMove(input);

        // update the position and behaviour of sprites in the current level of the world
        level.update(player, delta);
//
//
//        // update the position of rideable vehicles and check whether the player ride on any thing, if it does give it initial speed.
//
//
//        // update all Log and Longlog objects
//        if (level.getLevel().get(LOGS)!=null) {
//            for (Sprite sprite : level.getLevel().get(LOGS)) {
//                RideableVehicle log = (RideableVehicle) sprite;
//                log.update(delta);
//                if (!player.isRidden()){//check whether is it on a rideable vehicle if it does not ride yet
//                    log.updateRideableBehaviour(player, delta);
//                }
//            }
//        }
//
//        // update all Turtle objects
//        if (level.getLevel().get(TURTLE)!=null) {
//            for (Sprite sprite : level.getLevel().get(TURTLE)) {
//                RideableVehicle turtle = (RideableVehicle) sprite;
//                turtle.update(delta);
//                if (!player.isRidden()
//                        &&(time%(TIME_OF_TURTLE_SHOULD_DISAPPEAR+TIME_OF_TURTLE_SHOULD_APPEAR) > 0
//                        &&time%(TIME_OF_TURTLE_SHOULD_DISAPPEAR+TIME_OF_TURTLE_SHOULD_APPEAR) < TIME_OF_TURTLE_SHOULD_DISAPPEAR)){
//                    //check whether is it on a rideable vehicle if it does not ride yet and not disappear
//                    turtle.updateRideableBehaviour(player, delta);
//                }
//            }
//        }
//
//        // check whether to create an extra life
//        if ((time%(TIME_OF_EXTRA_LIFE_APPEAR+TIME_OF_EXTRALIFE_DISAPPEAR))>TIME_OF_EXTRA_LIFE_APPEAR
//                && !ExtraLifeIsAppear && ! extraLifeIsEaten){
//            System.out.println(TIME_OF_EXTRA_LIFE_APPEAR);
//            ExtraLifeIsAppear = true;
//            Random random = new Random();
//            extraLife = new ExtraLife((RideableVehicle) level.getLevel().get(LOGS).get(random.nextInt(level.getLevel().get(LOGS).size()-1)));
//        }else if (!((time%(TIME_OF_EXTRA_LIFE_APPEAR+TIME_OF_EXTRALIFE_DISAPPEAR))>TIME_OF_EXTRA_LIFE_APPEAR)){// need to disappear
//            ExtraLifeIsAppear = false;
//            extraLifeIsEaten = false; // there will be a new one.
//            extraLife = null;// make it none existent
//        }

//        // update the extra life's position
//        if (ExtraLifeIsAppear) {
//            extraLife.setKilled(false);
//
//            // update the next move bb of the extra life
//            extraLife.updateExtraLifeRelativeXPosition(time, delta);
//            extraLife.getNextStepBB().setX(extraLife.getTheLog().getPosition().getX() + extraLife.getExtraLifePositionRelativeToTheLogCenter());
//
//            // check whether the next step of extra life is off the chosen log
//            if (!(extraLife.getNextStepBB().intersects(extraLife.getTheLog().getBoundingBox()))){
//                extraLife.setExtraLifeIsMoveToRight(!extraLife.extraLifeIsMoveToRight());
//                // since it is out of the log, it need take two more step back
//
//                extraLife.updateExtraLifeRelativeXPosition(time, delta);
//                extraLife.updateExtraLifeRelativeXPosition(time, delta);
//            }
//
//
//            extraLife.getPosition().setX(extraLife.getTheLog().getPosition().getX() + extraLife.getExtraLifePositionRelativeToTheLogCenter());
//            extraLife.getBoundingBox().setX(extraLife.getTheLog().getPosition().getX() + extraLife.getExtraLifePositionRelativeToTheLogCenter());
//
//        }
//
//        //update the Tree Tile for checking contacting with player
//        for (Sprite sprite:level.getLevel().get(TREE)){
//            if (!player.isContactWithSolidSprite()) { // If the player is not contacted with solidable objects yet, check it out.
//                SoliableTile tree = (SoliableTile) sprite;
//                tree.update(player);
//            }
//        }
//
//        // Update all the FinishedPlayer objects for checking contacting with player
//        if (level.getLevel().get(FINISHED_PLAYER)!=null) {
//            for (Sprite sprite : level.getLevel().get(FINISHED_PLAYER)) {
//                if (!player.isContactWithSolidSprite()) { // If the player is not contacted with solidable objects yet, check it out.
//                    FinishedPlayer finishedPlayer = (FinishedPlayer) sprite;
//                    finishedPlayer.update(player);
//                }
//            }
//        }
//
//        // update all Bulldozer objects
//        if (level.getLevel().get(BULLDOZER)!=null) {
//            for (Sprite sprite : level.getLevel().get(BULLDOZER)) {
//                SolidableVehicle bulldozer = (SolidableVehicle) sprite;
//                bulldozer.update(delta);
//                if (!player.isContactWithSolidSprite() && !player.isRidden()) {
//                    // If the player is not contacted with solidable objects yet, check it out.
//                    // If it is ridden on an object, stop the off screen checking in bulldozer.updateSolidBehaviour
//                    bulldozer.updateSolidBehaviour(player, delta);
//                }
//            }
//        }


//        //update whether the player is killed
//        //update killable waters
//        for (Sprite sprite:level.getLevel().get(WATER)){
//            if (!player.isKilled()) { // If the player is not killed yet, check it out.
//                KillableTile water = (KillableTile) sprite;
//                water.update(player);
//            }
//        }
//
//        // update all Bus objects
//        if (level.getLevel().get(BUS)!=null) {
//            for (Sprite sprite : level.getLevel().get(BUS)) {
//                KillableVehicle bus = (KillableVehicle) sprite;
//                bus.update(delta);
//                if (!player.isKilled()) { // If the player is not killed yet, check it out.
//                   bus.updateKillableBehaviour(player);
//                }
//            }
//        }
//
//        // update all Racecar objects
//        if (level.getLevel().get(RACECAR)!=null) {
//            for (Sprite sprite : level.getLevel().get(RACECAR)) {
//                KillableVehicle racecar = (KillableVehicle) sprite;
//                racecar.update(delta);
//                // update the direction
//                if (!player.isKilled()) { // If the player is not killed yet, check it out.
//                    racecar.updateKillableBehaviour(player);
//                }
//            }
//        }
        // update all Bike objects
        // due to bike step
//        if (level.getLevel().get(Level.BIKE)!=null) {
//            for (Sprite sprite : level.getLevel().get(Level.BIKE)) {
//                KillableVehicle bike = (KillableVehicle) sprite;
//                // detect whether next step would out of the bound, if it does then reverse the direction of the bike
//                if ((bike.isMoveToRight() && bike.getPosition().getX() + delta*KillableAndReversableVehicle.BIKE_SPEED > BIKE_REVERSE_1000)
//                || (!bike.isMoveToRight() && bike.getPosition().getX() - delta*KillableAndReversableVehicle.BIKE_SPEED < BIKE_REVERSE_24)){
//                    bike.setMoveToRight(!bike.isMoveToRight());
//                }
//
//                bike.update(delta);
//
//                if (!player.isKilled()) { // If the player is not killed yet, check it out.
//                    bike.behaviour(player, delta);
//                }
//            }
//        }


        // Update all of the sprites in the game
        player.update();
//
//    // Update whether the player get the extra life
//        if (ExtraLifeIsAppear && player.getBoundingBox().intersects(extraLife.getBoundingBox())){
//            // add one more extra life to player
//            this.addLives(1);
//            ExtraLifeIsAppear = false;
//            extraLifeIsEaten = true;
//            extraLife = null; // make it disappear
//        }




        // update the contact condition between player and water most south line


        // update the contact between player and obstacles

        // update the time
        time += delta;

	}
	
	public void render(Graphics g) {
		// Draw all of the sprites in the background in the layer order.
        for (String key:KEYS_IN_LAYER_ORDER){
            SpritesRender(level.getLevel().get(key));
        }

        // draw the Turtle
        if (!(time%(TIME_OF_TURTLE_SHOULD_DISAPPEAR+TIME_OF_TURTLE_SHOULD_APPEAR) >= TIME_OF_TURTLE_SHOULD_DISAPPEAR)){
            SpritesRender(level.getLevel().get(Level.TURTLE));
        }

        // draw the lives
        if (player.getLives()!=null) {
            for (Life life : player.getLives()) {
                life.render();
            }
        }

//        // check whether to create an extra life
//        if (extraLife != null&& (time%(TIME_OF_EXTRA_LIFE_APPEAR+TIME_OF_EXTRALIFE_DISAPPEAR))>TIME_OF_EXTRA_LIFE_APPEAR){
//            extraLife.render();
//        }

        // draw the player
        player.render();
	}

	/** Method signature: private static <T extends Sprite> void SpritesRender(ArrayList<T> sprites);
     *
     * @param sprites => The array list of created Sprite objects
     *
     *  Description: This is a generic methods that calls the render method for each sprite in an arrayList of Sprite
     * */
	private static <T extends Sprite> void SpritesRender(ArrayList<Sprite> sprites){
	    if (sprites!=null) {
            // draw all the sprite of type T
            for (Sprite sprite : sprites) {
                T subclassOfSprite = (T) sprite;
                subclassOfSprite.render();
            }
        }else{
	        // do nothing, since nothing to draw
        }
    }
    /**Method signature:public int updateLevel(int numOfRunsFinished);
     *
     * @param isFinished => The boolean for whether current level is finished
     *
     * Description: This method update the currentLevel of the world according to the number of runs the player finish
     * */
    private int updateLevel(boolean isFinished){
        int levelOutPut = Level.LEVEL_0;
        // If now meet the requirement for turning to next level
        if (isFinished){
            if (level.getCurrentLevel() == Level.LEVEL_0) {
                levelOutPut = Level.LEVEL_1;
            }
            if (level.getCurrentLevel() == Level.LEVEL_1) {
                levelOutPut = Level.LEVEL_2;
            }
        }
        return levelOutPut;
    }
}