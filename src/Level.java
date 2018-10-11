import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Level {
    // the reference for level0 source file
    public static final String LEVEL0_REFERENCE = "assets/levels/0.lvl";
    // the reference for level1 source file
    public static final String LEVEL1_REFERENCE = "assets/levels/1.lvl";

    // The symbol to indicate level 0
    public static final int LEVEL_0 = 0;
    // The symbol to indicate level 1
    public static final int LEVEL_1 = 1;
    // The symbol to indicate level 2
    public static final int LEVEL_2 = 2;

    // the number of values of an Tile objects in lvl file
    public static final int NUM_OF_VALUES_OF_TILE = 3;
    // the number of values of an Vehicle objects in lvl file
    public static final int NUM_OF_VALUES_OF_VEHICLE = 4;
    // the INDEX of Object class in lvl File
    public static final int INDEX_OF_OBJ_CLASS_IN_CSV = 0;
    // the INDEX of x position in lvl File
    public static final int INDEX_OF_X = 1;
    // the INDEX of y position in lvl File
    public static final int INDEX_OF_Y = 2;
    // the INDEX of Vehicle moving direction in lvl File
    public static final int INDEX_OF_DIRECTION = 3;

    // the csv identifier for water
    public static final String WATER = "water";
    // the csv identifier for water
    public static final String GRASS = "grass";
    // the csv identifier for water
    public static final String TREE = "tree";
    // the hashMap key for FinishedPLayer
    public static final String FINISHED_PLAYER = "FinishedPlayer";
    // the csv identifier for BUS
    public static final String BUS = "bus";
    // the csv identifier for RACECAR
    public static final String RACECAR = "racecar";
    // the csv identifier for BIKE
    public static final String BIKE = "bike";
    // the csv identifier for BULLDOZER
    public static final String BULLDOZER = "bulldozer";
    // the csv identifier for LOG
    public static final String LOG = "log";
    // the csv identifier for LONGLOG
    public static final String LONGLOG = "longLog";
    // the csv identifier for TURTLE
    public static final String TURTLE = "turtle";
    // the csv identifier for LONGLOG and LOG
    public static final String LOGS = "logs";

    // The key of sprites need to be updated the behaviour in order
    public static final String[] KEYS_OF_RIDEABLE_VEHICLE = new String[]{LOGS, TURTLE};
    public static final String[] KEYS_OF_SPRITES_BEHAVIOUR_NEED_TO_BE_UPDATED = new String[]{ TREE,
            FINISHED_PLAYER, BULLDOZER, BUS, RACECAR, BIKE, WATER};

    // The HashMap for storing objects in level
    private HashMap<String, ArrayList<Sprite>> level;
    // declare the current level of the world
    private int currentLevel;

    public Level(String levelSrc, int currentLevel){
        this.currentLevel = currentLevel;
        this.level = createTheBackground(levelSrc);
    }

    /**
     * Method signature:public void update(Player player, int delta);
     *
     * Description: This method update the position and behaviour of all sprite in current level.
     *
     * @param player The reference to the player
     * @param delta The milliseconds since last frame passed
     *
     * */
    public void update(Player player, int delta){
        // set all the bulldozer.isContactWthPlayerToBeFalse, since for each frame we need to check it.
        ArrayList<Sprite> bulldozers = this.level.get(BULLDOZER);
        if (bulldozers !=null){
            bulldozers.forEach(t->{
                SolidableVehicle bulldozer = (SolidableVehicle) t;
                bulldozer.setContactWithPlayer(false);
            });
        }

        // update the position of all sprites which are movable in the world of the current level
        this.level.values().forEach(t->t.forEach(i -> i.update(delta)));

        // update the behaviour of all sprites in the world of the current level
        // First, the rideable vehicles
        for (String key:KEYS_OF_RIDEABLE_VEHICLE){
            ArrayList<Sprite> sprites = this.level.get(key);
            // if the ArrayList of this Sprite as described by the given key is not null, then iterate over it
            if (sprites!=null) {
                sprites.forEach(t -> {
                            // if the player is not ridden on anything yet, then keep finding
                            if (!player.isRidden()) {
                                t.behaviour(player, delta);
                            }
                            // else if the player is already ridden on something, ok, since player could only ride on
                            // one thing each time, no need to detecting further, do nothing
                        }
                );
            }
        }


        for (String key:KEYS_OF_SPRITES_BEHAVIOUR_NEED_TO_BE_UPDATED){
            ArrayList<Sprite> sprites = this.level.get(key);
            // if the ArrayList of this Sprite as described by the given key is not null, then iterate over it
            if (sprites!=null){
                // update the behaviour for each object
                sprites.forEach(t->t.behaviour(player, delta));
            }
        }


        // update the condition that whether the player is in a finishing hole

        // if the nextStep of player does not contact with a solid tree object, check whether is the nextStep is in a hole.
        if (!player.isContactWithSolidSprite()) {
            for (Sprite sprite : level.get(Level.TREE)) {
                SoliableTile tree = (SoliableTile) sprite;
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
                        level.putIfAbsent(Level.FINISHED_PLAYER, new ArrayList<>());
                        level.get(Level.FINISHED_PLAYER).add(new FinishedPlayer(holeCenter.getX(), holeCenter.getY()));
                        // reset the player to the starting point
                        player.restart();
                    }// else => do nothing => since not in any new hole
                }
            }
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
                        this.addSpriteIntoBackground(WATER, output, KillableTile.createAWater(x,y ));
                    }// add the Grass Tile object
                    else if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(GRASS)){
                        this.addSpriteIntoBackground(GRASS, output, Tile.createAGrass(x, y));
                    }// add the Tree Tile object
                    else if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(TREE)){
                        this.addSpriteIntoBackground(TREE, output, SoliableTile.createATree(x, y));
                    }
                }// create Vehicle object, if the description contains 4 values
                else if (description.length == NUM_OF_VALUES_OF_VEHICLE){
                    boolean isMoveToRight = Boolean.parseBoolean(description[INDEX_OF_DIRECTION]);
                    // add the Bus object
                    if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(BUS)){
                        this.addSpriteIntoBackground(BUS, output, KillableVehicle.createABus(x, y, isMoveToRight));
                    }// add the Racecar object
                    else if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(RACECAR)){
                        this.addSpriteIntoBackground(RACECAR, output, KillableVehicle.createARacecar(x, y, isMoveToRight));
                    }// add the Bike object
                    else if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(BIKE)){
                        this.addSpriteIntoBackground(BIKE, output, KillableAndReversableVehicle.createABike(x, y, isMoveToRight));
                    }// add the Bulldozer object
                    else if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(BULLDOZER)){
                        this.addSpriteIntoBackground(BULLDOZER, output, SolidableVehicle.createABulldozer(x, y, isMoveToRight));
                    }// add the Log object
                    else if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(LOG)){
                        this.addSpriteIntoBackground(LOGS, output, RideableVehicle.createALog(x, y, isMoveToRight));
                    }// add the Longlog object
                    else if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(LONGLOG)){
                        this.addSpriteIntoBackground(LOGS, output, RideableVehicle.createALongLog(x, y, isMoveToRight));
                    }// add the Turtle object
                    else if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(TURTLE)){
                        this.addSpriteIntoBackground(TURTLE, output, RideableVehicle.createATurtle(x, y, isMoveToRight));
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return output;
    }


    /**Method signature: public void addSpriteIntoBackground(String key,  HashMap<String, ArrayList<Sprite>> background, Sprite sprite);
     *
     * @param key The key of value to add
     * @param background The HashMap to add
     * @param sprite  The Sprite object to be added
     *
     * Description: This method would add one Sprite objects according to given String key value.
     * */
    private void addSpriteIntoBackground(String key,  HashMap<String, ArrayList<Sprite>> background, Sprite sprite){
        // if there does not have Key called key in the background, then create one and add the sprite into it
        background.putIfAbsent(key, new ArrayList<>());
        background.get(key).add(sprite);
    }

    /** Method signature: public int getCurrentLevel()
     *
     * @return currentLevel
     * */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /** Method signature: public HashMap<String, ArrayList<Sprite>> getLevel()
     *
     * @return level
     * */
    public HashMap<String, ArrayList<Sprite>> getLevel() {
        return level;
    }

    /** Method signature: public void setCurrentLevel(int currentLevel)
     *
     * @param currentLevel The given current level to set.
     * */
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }


    /** Method signature: public Position holeCenter(Player player);
     *
     * @param player The reference to player
     *
     *Description: Return the center Position of the hole that the player current at.
     * */
    private Position holeCenter(Player player){
        // To create two instance for the left and right nearest tree, since it will be replaced by the tree in the level
        // definitely, thus I decide to give these two random positions.
        SoliableTile leftNearestTree = SoliableTile.createATree((float) Math.random(), (float) Math.random());
        SoliableTile rightNeatestTree = SoliableTile.createATree((float) Math.random(), (float) Math.random());
        float minLeftDistance = App.SCREEN_WIDTH; // since the min distance must less the SCREEN_WIDTH, it is safe to do this
        float minRightDistance = App.SCREEN_WIDTH;// same reason as above

        for (Sprite sprite:level.get(Level.TREE)){
            SoliableTile tree = (SoliableTile) sprite;
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

}