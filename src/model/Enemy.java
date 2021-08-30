package model;

import controller.Driver;
import view.LabyrinthPanel;

import java.util.Random;

/**
 * Created by gyseynov on 07.09.14.
 */
public class Enemy extends GameCharacter {
    public int indexInEnemies;
    static int EnemyCounter = 0;
    public Thread enemyThread;
    public Direction[] dirs = {Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN};
    public int graphicPlaceX;
    public int graphicPlaceY;

    public Enemy(Labyrinth labyrinth, Driver driver){
        super(labyrinth, driver);
        indexInEnemies = EnemyCounter;
        EnemyCounter++;
        isAlive = true;
        placeX = 14;
        placeY = 11;
        graphicPlaceX = placeX * LabyrinthPanel.LEVEL_BLOCK_SIZE;
        graphicPlaceY = placeY * LabyrinthPanel.LEVEL_BLOCK_SIZE;
//        startThread();
    }

    public void startThread(){
        enemyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;
                while (isAlive) {
                    if (counter % 3 == 0) {
                        setDirection();
                    }
                    pursuitPacMan();
                    if (moveTo(direction)) {
                        for (int i = 0; i < 2; i++) {
                            makeHalfMove(direction);
                            driver.updateGui();
                            try{
                                Thread.sleep(Game.SLOWDOWN - 10);
                            } catch (InterruptedException e) {
                                System.out.println("Caught " + e.toString());
                            }
                            killPacMan();
                        }
                    }
                    counter++;
                }
            }
        });
    }

    public void makeHalfMove(GameCharacter.Direction direction) {
        switch(direction) {
            case LEFT:
                if ((graphicPlaceX == 0) && (graphicPlaceY == (14 * LabyrinthPanel.LEVEL_BLOCK_SIZE))) {
                    graphicPlaceX = 27 * LabyrinthPanel.LEVEL_BLOCK_SIZE;
                }
                graphicPlaceX -= LabyrinthPanel.HALF_STEP;
                break;
            case RIGHT:
                if ((graphicPlaceX == (27 * LabyrinthPanel.LEVEL_BLOCK_SIZE)) && (graphicPlaceY == (14 * LabyrinthPanel.LEVEL_BLOCK_SIZE))) {
                    graphicPlaceX = 0;
                }
                graphicPlaceX += LabyrinthPanel.HALF_STEP;
                break;
            case UP:
                graphicPlaceY -= LabyrinthPanel.HALF_STEP;
                break;
            case DOWN:
                graphicPlaceY += LabyrinthPanel.HALF_STEP;
                break;
        }
    }

    public void setDirection(){
        Random rand = new Random();
        int index = rand.nextInt(4) + 1;
        switch (index) {
            case 1:
                if (!labyrinth.get(placeY).get(placeX - 1).isWall()) {
                    direction = Direction.LEFT;
                }
                break;
            case 2:
                if (!labyrinth.get(placeY).get(placeX + 1).isWall()) {
                    direction = Direction.RIGHT;
                }
                break;
            case 3:
                if (!labyrinth.get(placeY - 1).get(placeX).isWall()) {
                    direction = Direction.UP;
                }
                break;
            case 4:
                if (!labyrinth.get(placeY + 1).get(placeX).isWall()) {
                    direction = Direction.DOWN;
                }
                break;
        }
    }

    public void pursuitPacMan() {
        boolean isWayFree = false;
        int change = 0;
        Direction newDir = null;
        if (placeX == Game.getPacMan().getPlaceX()) {
            isWayFree = true;
            if (placeY - Game.getPacMan().getPlaceY() > 0) {
                change = -1;
                newDir = Direction.UP;
            } else {
                change = 1;
                newDir = Direction.DOWN;
            }
            for (int i = 1; i < Math.abs(placeY - Game.getPacMan().getPlaceY()); i++) {
                if (labyrinth.get(placeY + (i * change)).get(placeX).isWall()) {
                    isWayFree = false;
                }
            }
        } else if (placeY == Game.getPacMan().getPlaceY()) {
            isWayFree = true;
            if (placeX - Game.getPacMan().getPlaceX() > 0) {
                change = -1;
                newDir = Direction.LEFT;
            } else {
                change = 1;
                newDir = Direction.RIGHT;
            }
            for (int i = 1; i < Math.abs(placeX - Game.getPacMan().getPlaceX()); i++) {
                if (labyrinth.get(placeY).get(placeX  + (i * change)).isWall()) {
                    isWayFree = false;
                }
            }
        }
        if (isWayFree) {
            direction = newDir;
        }
    }

    public void killPacMan(){
        if ((Math.abs(this.graphicPlaceX - driver.labyrinthPanel.pacManX) < 30) && (Math.abs(this.graphicPlaceY - driver.labyrinthPanel.pacManY) < 30)) {
            Game.getPacMan().getKilled();
            isAlive = false;
            System.out.println("Killed PAC-MAN");
        }
    }
}
