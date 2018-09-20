public class ExtraLife extends Player{
    private float speed;
    private boolean isMoveToRight;
    public ExtraLife(String imgSrc, float x, float y, float speed, boolean isMoveToRight) {
        super(imgSrc, x, y);
        this.speed =speed;
        this.isMoveToRight = isMoveToRight;
    }

    public boolean isMoveToRight() {
        return isMoveToRight;
    }

    public void setMoveToRight(boolean moveToRight) {
        isMoveToRight = moveToRight;
    }
}
