import org.lwjgl.Sys;
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
        // if the Tree object contacts with the BoundingBox of the next position of the player, then make player cannot move further.
        if (super.getBoundingBox().intersects(player.getNextStepBB())){
            player.setContactWithTree(true);
            System.out.println(1);
            System.out.println(player.getNextStepBB().getTop());
            System.out.println(super.getPosition().getY());
        }else{
            System.out.println(0);
            System.out.println(player.getNextStepBB().getTop());
            System.out.println(super.getPosition().getY());
        } // else case which is that no tree contacts with the player is
        // handled by setting the player.isContactWithTree to false each time update in the update of Class world
        System.out.println(player.isContactWithTree());
    }
}
