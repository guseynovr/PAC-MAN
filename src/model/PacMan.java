package model;

import controller.Driver;

/**
 * Created by gyseynov on 07.09.14.
 */
public class PacMan extends GameCharacter{
    public volatile Direction direction2 = Direction.LEFT;

    public PacMan(Labyrinth labyrinth, Driver driver){
        super(labyrinth, driver);
        this.placeX = 14;
        this.placeY = 23;
        this.driver = driver;
        driver.pacMan = this;
        direction = Direction.LEFT;
    }

    @Override
    public void setPlaceX(int placeX) {
        if (!labyrinth.get(this.placeY).get(placeX).isWall()) { // обрати внимание на "!"
            this.placeX = placeX;
            if (labyrinth.get(this.placeY).get(this.placeX).coin != null) {
                Coin.coins.remove(labyrinth.get(this.placeY).get(this.placeX).coin);
                Game.score += 100;
            }
//            labyrinth.get(this.placeY).get(this.placeX).coin = null;
        }
    }

    @Override
    public void setPlaceY(int placeY) {
        if (!labyrinth.get(placeY).get(this.placeX).isWall()) { // обрати внимание на "!"
            this.placeY = placeY;
            if (labyrinth.get(this.placeY).get(this.placeX).coin != null) {
                Coin.coins.remove(labyrinth.get(this.placeY).get(this.placeX).coin);
                Game.score += 100;
            }
//            labyrinth.get(this.placeY).get(this.placeX).coin = null;
        }
    }

    public void getKilled(){
        isAlive = false;
        System.out.println("Убит");
        Game.getGame().isActive = false;
        driver.updateGui();
    }
}
