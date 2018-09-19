import org.newdawn.slick.SlickException;

public class Tree extends Tile implements Solidable{
    public Tree(String imgSrc, float x, float y) throws SlickException {
        super(imgSrc, x, y);
    }

    public void update(Player player){
        this.solidPlayer(player, this);
    }

}
