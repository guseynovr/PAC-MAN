package model;

/**
 * Created by gyseynov on 07.09.14.
 */
public class LevelBlock {
    private int[] placeXY = new int[2];
    private boolean isWall;
    public Coin coin = null;

    public LevelBlock(int placeX, int placeY, boolean isWall) {
        this.placeXY[0] = placeX;
        this.placeXY[1] = placeY;
        this.isWall = isWall;
    }

    public int[] getPlaceXY() {
        return placeXY;
    }

    public boolean isWall() {
        return isWall;
    }
}
