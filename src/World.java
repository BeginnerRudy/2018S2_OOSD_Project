import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class World {

    /*Define CONSTANT*/

    // define the image reference of grass
    private static final String GRASS_REFERENCE = "assets/grass.png";
    // define the image reference of water
    private static final String WATER_REFERENCE = "assets/water.png";
    // define the image reference of player
    private static final String PLAYER_REFERENCE = "assets/frog.png";
    // define the image reference of obstacle
    private static final String OBSTACLE_REFERENCE = "assets/bus.png";
    // PLAYER_INITIAL_X is the initial x coordinator of player
    private static final float PLAYER_INITIAL_X = 512;
    // PLAYER_INITIAL_Y is the initial y coordinator of player
    private static final float PLAYER_INITIAL_Y = 720;


    /*Define Sprites and Background*/
    // declare the Background
    private Background background;
    // declare the player
    private Player player;

	public World() throws SlickException {
		// Perform initialisation logic

        // initialize the background
        background = new Background(GRASS_REFERENCE, WATER_REFERENCE, OBSTACLE_REFERENCE);

        //initialize the player
        player = new Player(PLAYER_REFERENCE, PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
	}
	
	public void update(Input input, int delta) {
		// Update all of the sprites in the game
        player.update(input);
        // update background
        background.update(delta);

        // update the contact condition between player and water most south line
        for (Tile water: background.getWaters()[0]){
            player.contactSprite(water);
        }

        // update the contact between player and obstacles
        for (Obstacle[] obstacles:background.getObstacles()){
            for (Obstacle obstacle:obstacles){
                player.contactSprite(obstacle);
            }
        }

	}
	
	public void render(Graphics g) {
		// Draw all of the sprites in the game

        //draw the background
        background.render();
        // draw the player
        player.render();
	}
}
