import org.newdawn.slick.SlickException;

public class Water extends Tile implements Killable{
    public Water(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }

    /**
     * The function to make player die if it contacts with Grass tile
     *
     * */
    @Override
    public void killPlayer(Player player){
        // kill the player => -1 life from player
    }
}
