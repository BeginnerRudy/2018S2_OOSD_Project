import org.newdawn.slick.SlickException;

public class SolidableVehicle extends Vehicle implements Solidable {
    // define the image reference of bulldozer
    private static final String BULLDOZER_REFERENCE = "assets/bulldozer.png";
    // The speed of vehicle bulldozer
    private static final float BULLDOZER_SPEED = 0.05f;
    private boolean isContactWithPlayer;
    public SolidableVehicle(String imageSrc, float x, float y, float speed, boolean isMoveToRight) {
        super(imageSrc, x, y, speed, isMoveToRight);
        isContactWithPlayer = false;
    }

    /**Method signature: public SolidableVehicle createABulldozer(float x, float y, float speed, boolean isMoveToRight);
     *
     * @param x The given x-coordinate
     * @param y The given y-coordinate
     * @param speed The given speed
     * @param isMoveToRight The given moving direction
     *
     * Description: This methods returns a Solidable Vehicle called bulldozer.
     * */
    public SolidableVehicle createABulldozer(float x, float y, float speed, boolean isMoveToRight){
        return new SolidableVehicle(BULLDOZER_REFERENCE, x, y, speed, isMoveToRight);
    }


    /** Method signature: public void updateSolidBehaviour(Player player), int delta;
     *
     * @param player => The reference to player
     * @param delta The milliseconds since last frame passed
     *
     * Description: This method update the player.isContactWithSolidSprite and solidableVehicle.isContactWithPlayer
     *              according to whether solidableVehicle contact with the player.
     *              If it does, set this attribute to true.
     *              Otherwise, do nothing.
     * */

    public void updateSolidBehaviour(Player player, int delta){
        this.solidPlayer(player, this);
        if (this.isContactWithPlayer) {
            if (super.isMoveToRight()) {//push player to the right
                player.getPosition().setX(player.getPosition().getX() + super.getSpeed() * (delta));
            } else {// push player to the left
                player.getPosition().setX(player.getPosition().getX() - super.getSpeed() * (delta));
            }
        }

        // Determine whether the player is on the screen
        float playerLeft = player.getPosition().getX()-(float)player.getImage().getWidth()/2;
        float playerRight = player.getPosition().getX()+(float)player.getImage().getWidth()/2;
        if (playerRight>App.SCREEN_WIDTH || playerLeft<0){
            // is it does, kill the player
            player.setKilled(true);
        }
    }

    /**Method signature: default void solidPlayer(Player player, Sprite solidSprite){
     *
     * @param player => The reference to player
     * @param solidSprite => The reference to a solid Sprite (implements Solidable interface)
     *
     * Description: This default would check whether solidSprite contacts with player.
     *                    If it does, set player.isContactWithSolidSprite to true.
     *                    Otherwise, do nothing.
     * */

    @Override
    public void solidPlayer(Player player, Sprite solidSprite){
        // if the solidSprite contacts with the BoundingBox of the next position of the player, then make player cannot move further.
        // Then, assign the bulldozer that contact with player's bulldozer.isContactWithPlayer to be true
        if (solidSprite.getBoundingBox().intersects(player.getNextStepBB())){
            player.setContactWithSolidSprite(true);
            this.setContactWithPlayer(true);
        }
        // else case which is that no solidSprite contacts with the player is
        // handled by setting the player.isContactWithSolidSprite to false each time update in the update of Class world
    }

    /** Method signature: public boolean isContactWithPlayer();
     *
     * no argument
     *
     * Description: The getter of isContactWithPlayer
     * */
    public boolean isContactWithPlayer() {
        return isContactWithPlayer;
    }

    /** Method signature: public void setContactWithPlayer(boolean contactWithPlayer);
     *
     * @param contactWithPlayer The given boolean value to set
     *
     * Description: The setter for the attribute isContactWithPlayer
     * */
    public void setContactWithPlayer(boolean contactWithPlayer) {
        isContactWithPlayer = contactWithPlayer;
    }
}
