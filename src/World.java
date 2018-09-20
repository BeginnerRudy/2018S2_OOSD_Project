import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

public class World {
    /*Define CONSTANT*/

    // define the image reference of grass
    private static final String GRASS_REFERENCE = "assets/grass.png";
    // define the image reference of water
    private static final String WATER_REFERENCE = "assets/water.png";
    // define the image reference of tree
    private static final String TREE_REFERENCE = "assets/tree.png";
    // define the image reference of player
    private static final String PLAYER_REFERENCE = "assets/frog.png";
    // define the image reference of bus
    private static final String BUS_REFERENCE = "assets/bus.png";
    // define the image reference of bike
    private static final String BIKE_REFERENCE = "assets/bike.png";
    // define the image reference of bulldozer
    private static final String BULLDOZER_REFERENCE = "assets/bulldozer.png";
    // define the image reference of racecar
    private static final String RACECAR_REFERENCE = "assets/racecar.png";
    // define the image reference of log
    private static final String LOG_REFERENCE = "assets/log.png";
    // define the image reference of longlog
    private static final String LONGLOG_REFERENCE = "assets/longlog.png";
    // define the image reference of turtles
    private static final String TURTLES_REFERENCE = "assets/turtles.png";
    // define the image reference of lives
    private static final String LIVES_REFERENCE = "assets/lives.png";
    // define the image reference of extra life
    private static final String EXTRALIFE_REFERENCE = "assets/extralife.png";
    // PLAYER_INITIAL_X is the initial x coordinator of player
    private static final float PLAYER_INITIAL_X = 512;
    // PLAYER_INITIAL_Y is the initial y coordinator of player
    private static final float PLAYER_INITIAL_Y = 720;

    // the reference for level0 source file
    private static final String LEVEL0_REFERENCE = "assets/levels/0.lvl";
    // the reference for level1 source file
    private static final String LEVEL1_REFERENCE = "assets/levels/1.lvl";
    // the number of values of an Tile objects in lvl file
    private static final int NUM_OF_VALUES_OF_TILE = 3;
    // the number of values of an Vehicle objects in lvl file
    private static final int NUM_OF_VALUES_OF_VEHICLE = 4;
    // the INDEX of Object class in lvl File
    private static final int INDEX_OF_OBJ_CLASS_IN_CSV = 0;
    // the INDEX of x position in lvl File
    private static final int INDEX_OF_X = 1;
    // the INDEX of y position in lvl File
    private static final int INDEX_OF_Y = 2;
    // the INDEX of Vehicle moving direction in lvl File
    private static final int INDEX_OF_DIRECTION = 3;
    // The number of runs need to finished to get to next level
    private static final int NUM_OF_RUNS_TO_NEXT_LEVEL = 5;
    // The symbol to indicate level 0
    private static final int LEVEL_0 = 0;
    // The symbol to indicate level 1
    private static final int LEVEL_1 = 1;
    // The symbol to indicate level 2
    private static final int LEVEL_2 = 2;


    // the csv identifier for water
    private static final String WATER = "water";
    // the csv identifier for water
    private static final String GRASS = "grass";
    // the csv identifier for water
    private static final String TREE = "tree";
    // the hashMap key for FinishedPLayer
    private static final String FINISHED_PLAYER = "FinishedPlayer";
    // the csv identifier for BUS
    private static final String BUS = "bus";
    // the csv identifier for RACECAR
    private static final String RACECAR = "racecar";
    // the csv identifier for BIKE
    private static final String BIKE = "bike";
    // the csv identifier for BULLDOZER
    private static final String BULLDOZER = "bulldozer";


    // The speed of vehicle bus
    private static final float BUS_SPEED = 0.15f;
    // The speed of vehicle racecar
    private static final float RACECAR_SPEED = 0.5f;
    // The speed of vehicle bike
    private static final float BIKE_SPEED = 0.2f;
    // The speed of vehicle bulldozer
    private static final float BULLDOZER_SPEED = 0.05f;

    /*Define Sprites and Background*/
    // declare the player
    private Player player;
    // declare the current level of the world
    private int currentLevel;
    // The HashMap with (String, ArrayList<Sprite>) to represent the "background" for level
    private HashMap<String, ArrayList<Sprite>> background = new HashMap<>();

	public World() throws SlickException {
        // initialize the background of the world of level1
        // This background contains all the objects used in this game.
        // More details in the description  of method createTheBackground
        background = this.createTheBackground(LEVEL0_REFERENCE);

        // begin with level 0
        this.currentLevel = LEVEL_0;
        //initialize the player
        player = new Player(PLAYER_REFERENCE, PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
        //initialize the lives of player
        for (int i=0; i<Player.INITIAL_NUMBER_OF_LIVES; i++){
            player.getLives().add(new Life(LIVES_REFERENCE, Life.INITIAL_X_POSITION+i*Life.THE_GAP, Life.INITIAL_Y_POSITION));
        }

	}
	
	public void update(Input input, int delta)  throws SlickException {
	    // check is it the time to turn to next level
        int numOfRunsFinished = 0;
        if (background.get(FINISHED_PLAYER)!=null) {
            numOfRunsFinished = background.get(FINISHED_PLAYER).size();
        }
        boolean isFinishedAll = (numOfRunsFinished == NUM_OF_RUNS_TO_NEXT_LEVEL);

        // if current level is finished, turn to next level
        if (isFinishedAll) {
            this.currentLevel = this.updateLevel(isFinishedAll);

            // swap to new level according to current level
            if (this.currentLevel == LEVEL_2) {
                System.exit(0);
            } else if (this.currentLevel == LEVEL_1) {
                // reinitialize all the Tiles, Vehicles, FinishedPlayers and ExtraLife from current "background"
                background = new HashMap<>();
                background = this.createTheBackground(LEVEL1_REFERENCE);
            } else if (this.currentLevel == LEVEL_0) {
                // do nothing, since we start with level 0.
            }
        }
	    // set the player.isContactWithTree to false, in order to handle the case no tree contacts the player
        // while if there is a tree contacts with the player, this attribute will be set to true by that tree.update(Player player);
        player.setContactWithSolidSprite(false);
        // set the player.isInAHole to be false, since for each frame we need to check it .
        player.setInAHole(false);
        // set the player.isKilled to be false, since for each frame we need to check it .
        player.setKilled(false);
        // set all the bulldozer.isContactWthPlayerToBeFalse, since for each frame we need to check it.
        if (background.get(BULLDOZER)!=null){
            for (Sprite sprite:background.get(BULLDOZER)){
                Bulldozer bulldozer = (Bulldozer) sprite;
                bulldozer.setContactWithPlayer(false);
            }

        }

        // update the position and boundingbox of player's next position depends on input
        player.updatePlayNextMove(input);

        //update the Tree Tile for checking contacting with player
        for (Sprite sprite:background.get(TREE)){
            if (!player.isContactWithSolidSprite()) { // If the player is not contacted with solidable objects yet, check it out.
                Tree tree = (Tree) sprite;
                tree.update(player);
            }
        }

        // Update all the FinishedPlayer objects for checking contacting with player
        if (background.get(FINISHED_PLAYER)!=null) {
            for (Sprite sprite : background.get(FINISHED_PLAYER)) {
                if (!player.isContactWithSolidSprite()) { // If the player is not contacted with solidable objects yet, check it out.
                    FinishedPlayer finishedPlayer = (FinishedPlayer) sprite;
                    finishedPlayer.update(player);
                }
            }
        }

        // update all Bulldozer objects
        if (background.get(BULLDOZER)!=null) {
            for (Sprite sprite : background.get(BULLDOZER)) {
                Bulldozer bulldozer = (Bulldozer) sprite;
                bulldozer.update(delta);
                if (!player.isContactWithSolidSprite()) { // If the player is not contacted with solidable objects yet, check it out.
                    bulldozer.updateSolidBehaviour(player, delta);
                }
            }
        }

        // if the nextStep of player does not contact with a solid tree object, check whether is the nextStep is in a hole.
        if (!player.isContactWithSolidSprite()) {
            for (Sprite sprite : background.get(TREE)) {
                Tree tree = (Tree) sprite;
                /* check whether the player's nextStep position is in any hole
                 * if it is, add a FinishedPlayer to the ArrayList finishedPlayers
                 * I did this by checking whether the y coordinate of player is same as
                 * the y-coordinate of any tree, since it means they are in the same horizontal
                 * line. Because trees are assumed to be solid, thus if they are in same line,
                 * this implies that the player is in the hole.
                 */
                if (!player.isInAHole()) { // if the player is not in the hole, check it
                    if (tree.getPosition().equalsY(player.getNextStep())) {
                        player.setInAHole(true);// yes in this time it is in a hole
                        // find the Position of center of current hole
                        Position holeCenter = this.holeCenter(player);
                        // add the new Finished player at the player's current hole and draw it at the center of it
                        background.putIfAbsent(FINISHED_PLAYER, new ArrayList<>());
                        background.get(FINISHED_PLAYER).add(new FinishedPlayer(PLAYER_REFERENCE, holeCenter.getX(), holeCenter.getY()));
                        // reset the player to the starting point
                        player.restart(PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
                    }// else => do nothing => since not in any new hole
                }
            }
        }

        //update whether the player is killed
        //update killable waters
//        for (Sprite sprite:background.get(WATER)){
//            if (!player.isKilled()) { // If the player is not killed yet, check it out.
//                Water water = (Water) sprite;
//                water.update(player);
//            }
//        }

        // update all Bus objects
        if (background.get(BUS)!=null) {
            for (Sprite sprite : background.get(BUS)) {
                if (!player.isKilled()) { // If the player is not killed yet, check it out.
                    Bus bus = (Bus) sprite;
                    bus.update(delta);
                }
            }
        }

        // update all Racecar objects
        if (background.get(RACECAR)!=null) {
            for (Sprite sprite : background.get(RACECAR)) {
                if (!player.isKilled()) { // If the player is not killed yet, check it out.
                    Racecar racecar = (Racecar) sprite;
                    racecar.update(delta);
                }
            }
        }
        // update all Bike objects
        if (background.get(BIKE)!=null) {
            for (Sprite sprite : background.get(BIKE)) {
                if (!player.isKilled()) { // If the player is not killed yet, check it out.
                    Bike bike = (Bike) sprite;
                    bike.update(delta);
                }
            }
        }


        // update the position of player if it is killed
        if (player.isKilled()) {
            if (player.getLives() != null) {
                if (player.getLives().size() > Player.MIN_NUM_OF_LIFE_TO_PLAY) {
                    // remove the leftmost life
                    player.getLives().remove(player.getLives().size() - 1);
                    //restart the player's position
                    player.restart(PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
                }else{// not enough life to play, GameOver
                    player.setGameOver(true);
                    System.exit(0);
                }
            }
        }


        // Update all of the sprites in the game
        player.update();





        // update the contact condition between player and water most south line


        // update the contact between player and obstacles


	}
	
	public void render(Graphics g) {
		// Draw all of the sprites in the game
        //draw the Water Tiles
        SpritesRender(background.get(WATER));

        //draw the Grass Tiles
        SpritesRender(background.get(GRASS));

        //draw the Tree Tiles
        SpritesRender(background.get(TREE));

        //draw the FinishedPlayer sprites
        SpritesRender(background.get(FINISHED_PLAYER));

        // draw the lives
        if (player.getLives()!=null) {
            for (Life life : player.getLives()) {
                life.render();
            }
        }

        // draw the buses
        SpritesRender(background.get(BUS));

        // draw the racecars
        SpritesRender(background.get(RACECAR));

        // draw the bikes
        SpritesRender(background.get(BIKE));

        // draw the bulldozers
        SpritesRender(background.get(BULLDOZER));

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

    /**Name: private void createTheBackground(String LEVEL_REFERENCE);
     *
     * @param LEVEL_REFERENCE => The reference of the level CSV file.
     *
     * Description: This method is use to create all the objects described in the level CSV file.
     * */
    private HashMap<String, ArrayList<Sprite>> createTheBackground(String LEVEL_REFERENCE){
        HashMap<String, ArrayList<Sprite>> output = new HashMap<>();
        // Perform initialisation logic
        try (BufferedReader br = new BufferedReader(new FileReader(LEVEL_REFERENCE))){
            // create an array of string to store each line in the format of String
            String[] description;
            // String line to store the raw data
            String line;
            while((line = br.readLine() )!= null){
                // load data into description
                description = line.split(",");
                float x = Float.parseFloat(description[INDEX_OF_X]);
                float y = Float.parseFloat(description[INDEX_OF_Y]);

                // create Tile object, if the description contains 3 values
                if (description.length == NUM_OF_VALUES_OF_TILE){
                    // add the Water Tile object
                    if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(WATER)){
                        this.addSpriteIntoBackground(WATER, output, new Water(WATER_REFERENCE, x, y));
                    }// add the Grass Tile object
                    else if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(GRASS)){
                        this.addSpriteIntoBackground(GRASS, output, new Grass(GRASS_REFERENCE, x, y));
                    }// add the Tree Tile object
                    else if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(TREE)){
                        this.addSpriteIntoBackground(TREE, output, new Tree(TREE_REFERENCE, x, y));
                    }
                }// create Vehicle object, if the description contains 4 values
                else if (description.length == NUM_OF_VALUES_OF_VEHICLE){
                    boolean isMoveToRight = Boolean.parseBoolean(description[INDEX_OF_DIRECTION]);
                    // add the Bus object
                    if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(BUS)){
                        this.addSpriteIntoBackground(BUS, output, new Bus(BUS_REFERENCE, x, y, BUS_SPEED, isMoveToRight));
                    }// add the Racecar object
                    else if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(RACECAR)){
                        this.addSpriteIntoBackground(RACECAR, output, new Racecar(RACECAR_REFERENCE, x, y, RACECAR_SPEED, isMoveToRight));
                    }// add the Bike object
                    else if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(BIKE)){
                        this.addSpriteIntoBackground(BIKE, output, new Bike(BIKE_REFERENCE, x, y, BIKE_SPEED, isMoveToRight));
                    }// add the Bulldozer object
                    else if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(BULLDOZER)){
                        this.addSpriteIntoBackground(BULLDOZER, output, new Bulldozer(BULLDOZER_REFERENCE, x, y, BULLDOZER_SPEED, isMoveToRight));
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return output;
    }

    /** Method signature: public Position holeCenter(Player player);
     *
     * @param player The reference to player
     *
     *Description: Return the center Position of the hole that the player current at.
     * */
    public Position holeCenter(Player player) throws SlickException{
        // The initial tree object has no effect on selecting tree later, only the
        // minLeftDistance and  minRightDistance does, thus set the position to same random number
        Tree leftNearestTree = new Tree(TREE_REFERENCE,
                (float) Math.random(), (float) Math.random());
        Tree rightNeatestTree = new Tree(TREE_REFERENCE,
                (float) Math.random(), (float) Math.random());
        float minLeftDistance = App.SCREEN_WIDTH; // since the min distance must less the SCREEN_WIDTH, it is safe to do this
        float minRightDistance = App.SCREEN_WIDTH;// same reason as above

        for (Sprite sprite:background.get(TREE)){
            Tree tree = (Tree) sprite;
            // only find trees in the domain of tree has same y-coordinate with player's nextStep
            if (tree.getPosition().equalsY(player.getNextStep())){
                // get the current distance between this tree and play's nextStep
                float distance = tree.getPosition().getX() - player.getNextStep().getX();
                // negative means tree is on the left of the player
                if (distance < 0){
                    if ((distance = Math.abs(distance)) < minLeftDistance){
                        minLeftDistance = distance;
                        leftNearestTree = tree;
                    }
                }
                // positive means tree is on the right of the player
                else if (distance >0){
                    if (distance < minRightDistance){
                        minRightDistance = distance;
                        rightNeatestTree = tree;
                    }
                }
            }
        }
        return leftNearestTree.getPosition().midPoint(rightNeatestTree.getPosition());
    }

    /**Method signature:public int updateLevel(int numOfRunsFinished);
     *
     * @param isFinished => The boolean for whether current level is finished
     *
     * Description: This method update the currentLevel of the world according to the number of runs the player finish
     * */
    public int updateLevel(boolean isFinished){
        int levelOutPut = LEVEL_0;
        // If now meet the requirement for turning to next level
        if (isFinished){
            if (this.currentLevel == LEVEL_0) {
                levelOutPut = LEVEL_1;
            }
            if (this.currentLevel == LEVEL_1) {
                levelOutPut = LEVEL_2;
            }
        }
        return levelOutPut;
    }

    /**Method signature: public void addSpriteIntoBackground(String key,  HashMap<String, ArrayList<Sprite>> background, Sprite sprite);
     *
     * @param key The key of value to add
     * @param background The HashMap to add
     * @param sprite  The Sprite object to be added
     *
     * Description: This method would add one Sprite objects according to given String key value.
     * */
    public void addSpriteIntoBackground(String key,  HashMap<String, ArrayList<Sprite>> background, Sprite sprite){
        // if there does not have Key called key in the background, then create one and add the sprite into it
        background.putIfAbsent(key, new ArrayList<>());
        background.get(key).add(sprite);
    }
}
