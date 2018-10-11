import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import java.util.ArrayList;
import java.util.Random;


public class World {
    /*Define CONSTANT*/
    // The convert between second to millisecond
    public static final int SECOND_TO_MILLISECOND = 1000;

    // The period that the turtle should be in the water
    public static final int TIME_OF_TURTLE_SHOULD_DISAPPEAR = 7*SECOND_TO_MILLISECOND;
    // The period that the turtle should be on the water
    public static final int TIME_OF_TURTLE_SHOULD_APPEAR = 2*SECOND_TO_MILLISECOND;


    /*Define Sprites and Background*/
    // declare the player
    private Player player;
    // declare the level of the world
    private Level level;
    // the total time of the world (in milliseconds)
    private int time;


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
        level.checkLevel();
        // reset the boolean values of player at the beginning of updating
        player.resetPlayerBooleanBeforeUpdate();
        // update the position and BoundingBox of player's next position depends on input
        player.updatePlayNextMove(input);
        // update the position and behaviour of sprites in the current level of the world
        level.update(player, delta);
        // Update all of the sprites in the game
        player.update();
        // update the time
        time += delta;
	}
	
	public void render(Graphics g) {
	    // draw the level
        level.render();
        // draw the player
        player.render();
	}
}