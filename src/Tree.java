import org.newdawn.slick.SlickException;

public class Tree extends Tile implements Solidable{
    public Tree(String imgSrc, float x, float y) throws SlickException {
        super(imgSrc, x, y);
    }

    public void update(Player player){
        this.solidPlayer(player);
    }


    @Override
    public void solidPlayer(Player player){
        // if the Tree object contacts with the player, then make player cannot move further.
        if (this.contactSprite(player)){
            player.setContactWithTree(true);
        }else{
            // do nothing, if no contact happen.
            // player.setContactWithTree(false);
        }
    }
}
