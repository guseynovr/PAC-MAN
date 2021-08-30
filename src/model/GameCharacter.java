package model;

import controller.Driver;

/**
 * Created by gyseynov on 07.09.14.
 */
public class GameCharacter {
    protected volatile int placeX;
    protected volatile int placeY;
    protected Labyrinth labyrinth;
    public boolean isAlive;
    public Driver driver;
    public enum Direction {LEFT, RIGHT, UP, DOWN};
    public volatile Direction direction = Direction.LEFT;


    public GameCharacter(Labyrinth labyrinth, Driver driver) {
        this.labyrinth = labyrinth;
        this.driver = driver;
    }

    public int getPlaceX() {
        return placeX;
    }

    public void setPlaceX(int placeX) {
        if (!labyrinth.get(this.placeY).get(placeX).isWall() && (placeX < Labyrinth.WIDTH)) { // обрати внимание на "!"
            this.placeX = placeX;
        }
    }

    public int getPlaceY() {
        return placeY;
    }

    public void setPlaceY(int placeY) {
        if (!labyrinth.get(placeY).get(this.placeX).isWall() && (placeY < Labyrinth.HEIGHT)) { // обрати внимание на "!"
            this.placeY = placeY;
        }
    }

    public boolean moveTo(Direction direction) {
        switch (direction) {
            case LEFT:
                if ((placeX == 0) && (placeY == 14)) {
                    setPlaceX(27);
                }
                if (!labyrinth.get(placeY).get(this.placeX - 1).isWall()) {
                    setPlaceX(this.placeX - 1);
                    return true;
                } else {
                    return false;
                }
            case RIGHT:
                if ((placeX == 27) && (placeY == 14)) {
                    setPlaceX(0);
                }
                if (!labyrinth.get(placeY).get(this.placeX + 1).isWall()) { // обрати внимание на "!"
                    setPlaceX(this.placeX + 1);
                    return true;
                } else {
                    return false;
                }
            case UP:
                if (!labyrinth.get(placeY - 1).get(this.placeX).isWall()) { // обрати внимание на "!"
                    setPlaceY(this.placeY - 1);
                    return true;
                } else {
                    return false;
                }
            case DOWN:
                if (!labyrinth.get(placeY + 1).get(this.placeX).isWall()) { // обрати внимание на "!"
                    setPlaceY(this.placeY + 1);
                    return true;
                } else {
                    return false;
                }
        }
        return false;
    }
}