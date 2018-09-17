import org.newdawn.slick.SlickException;

public class SolidTile extends Tile implements Solidable{
    public SolidTile(String imgSrc, float x, float y) throws SlickException {
        super(imgSrc, x, y);
    }

    @Override
    public void solidPlayer(Player player){
        // make player stop
    }
}
