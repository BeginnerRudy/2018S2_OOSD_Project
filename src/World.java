import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import java.util.ArrayList;
import java.util.Random;


public class World {
    /*********************************Define Sprites and Background********************************/
    // declare the player
    private Player player;
    // declare the level of the world
    private Level level;


	public World(){
        //initialize the attribute of world
        // initialize the level of the world
        this.level = new Level(Level.LEVEL0_REFERENCE, Level.LEVEL_0);
        //initialize the player
        this.player = new Player();
	}
	
	public void update(Input input, int delta){
        // Check whether the game is over. If it is over, then exit the game
        if (player.isGameOver()) System.exit(0);
	    // check is it the time to turn to next level, if it is then swap to next level.
        level.checkLevel(player);
        // reset the boolean values of player at the beginning of updating
        player.resetPlayerBooleanBeforeUpdate();
        // update the position and BoundingBox of player's next position depends on input of the user.
        player.updatePlayNextMove(input);
        // update the position and behaviour of all sprites in the current level of the world depending on the player's next move.
        level.update(player, delta);
        // Update the player, include its position, lives and whether killed
        player.update();
	}
	
	public void render(Graphics g) {
	    // draw the level
        level.render();
        // draw the player
        player.render();
	}
}