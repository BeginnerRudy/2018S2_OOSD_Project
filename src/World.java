import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


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

    // the csv identifier for water
    private static final String WATER = "water";


    /*Define Sprites and Background*/
    // declare the player
    private Player player;

	public World() throws SlickException {

		// Perform initialisation logic



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

        //draw the background

        // draw the player
        player.render();
	}
}
