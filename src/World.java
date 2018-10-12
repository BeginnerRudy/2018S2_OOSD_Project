import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
/**
 * Class: public class World;
 *
 * @author Renjie (Rudy) Meng
 *
 * Description: This World class represent the world that contains all the objects in game.
 *              This class also take the responsibility of update itself and draw itself.s
 * */

public class World {
    /* ********************************Define attributes********************************/
    /** declare the player attribute of the world*/
    private Player player;
    /** declare the level attribute of the world*/
    private Level level;

    /* ********************************Define method********************************/

    /**
     * Method signature: public World()
     *
     * Description: The constructor for the class World.
     * */
	public World(){
        //initialize the attribute of world
        // initialize the level of the world
        this.level = new Level(Level.LEVEL0_REFERENCE, Level.LEVEL_0);
        //initialize the player
        this.player = new Player();
	}

	/**
     * Method signature: public void update(Input input, int delta)
     *
     * @param input The input from the user, mainly use the input of the directional keys.
     * @param delta The milliseconds since last frame passed.
     *
     * Description: This method update the level and player of the world according to the input and delta.
     *              More detail happens in the class of level and player.
     * */
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

	/**
     * Method signature: public void render(Graphics g)
     *
     * @param g The Graphics g to control the graphic representation of the world
     *
     * Description: This method takes the responsibility of drawing all the objects that should by shown at the screen.
     * */
	public void render(Graphics g) {
	    // draw the level
        level.render();
        // draw the player
        player.render();
	}
}