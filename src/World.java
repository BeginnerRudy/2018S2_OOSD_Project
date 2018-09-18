import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import java.io.*;
import java.util.ArrayList;

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


    // the csv identifier for water
    private static final String WATER = "water";
    // the csv identifier for water
    private static final String GRASS = "grass";
    // the csv identifier for water
    private static final String TREE = "tree";



    /*Define Sprites and Background*/
    // declare the player
    private Player player;
    // The ArrayList of Type Water to store Water Tile in the world
    private ArrayList<Water> waters = new ArrayList<Water>();
    // The ArrayList of Type Water to store Water Tile in the world
    private ArrayList<Grass> grasses = new ArrayList<Grass>();
    // The ArrayList of Type Water to store Water Tile in the world
    private ArrayList<Tree> trees = new ArrayList<Tree>();

	public World() throws SlickException {

		// Perform initialisation logic
        try (BufferedReader br = new BufferedReader(new FileReader(LEVEL0_REFERENCE))){
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
                    // create the Water Tile object
                    if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(WATER)){
                        waters.add(new Water(WATER_REFERENCE, x, y));
                    }// create the Grass Tile object
                    else if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(GRASS)){
                        grasses.add(new Grass(GRASS_REFERENCE, x, y));
                    }// create the Tree Tile object
                    if (description[INDEX_OF_OBJ_CLASS_IN_CSV].equals(TREE)){
                        trees.add(new Tree(TREE_REFERENCE, x, y));
                    }
                }// create Vehicle object, if the description contains 4 values
                else if (description.length == NUM_OF_VALUES_OF_VEHICLE){

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        //initialize the player
        player = new Player(PLAYER_REFERENCE, PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
	}
	
	public void update(Input input, int delta) {
		// Update all of the sprites in the game
        player.update(input);
        // update background


        // update the contact condition between player and water most south line


        // update the contact between player and obstacles


	}
	
	public void render(Graphics g) {
		// Draw all of the sprites in the game

        //draw the Water Tiles
        ObjectsRender(waters);

        //draw the Grass Tiles
        ObjectsRender(grasses);

        //draw the Tree Tiles
        ObjectsRender(trees);

        // draw the player
        player.render();
	}

	/**This function is to draw all the sprites in the Sprite ArrayList
     *
     * @param sprites => The array list of created Sprite objects
     * */
	private static <T extends Sprite> void ObjectsRender(ArrayList<T> sprites){
        // draw all the sprite of type T
        for (T sprite:sprites){
            sprite.render();
        }
    }
}
