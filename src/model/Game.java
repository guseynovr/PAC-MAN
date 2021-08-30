package model;

import controller.Driver;

/**
 * Created by gyseynov on 07.09.14.
 */
public class Game {
    private int numOfCoins;
    private static volatile Game game;
    private static volatile PacMan pacMan;
    private static volatile Enemy[] enemies= new Enemy[1];
    public static volatile Driver driver = new Driver();
    private static Thread guiThread;
    public boolean isActive;
    public static final int SLOWDOWN = 100;
    public static int score = 0;

    public static void main(String[] args) {
        game = new Game();
    }

    public static Enemy[] getEnemies() {
        return enemies;
    }

    public Game(){
        game  = this;
        initGame();
        startGui();
        synchronized (game) {
            try {
                game.wait();
            } catch (InterruptedException e) {
                System.out.println("Caught " + e.toString());
            }
            startGame();
        }
        finishGame();
    }

    public void initGame(){
        System.out.println("Подготовка игры, инициализация всех объектов и ссылок");
        Labyrinth labyrinth = new Labyrinth(driver);
        pacMan = new PacMan(labyrinth, driver);

        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy(labyrinth, driver);
        }
    }

    public void startGui(){
        System.out.println("Starting GUI thread");
        driver.startGui();
    }

    public void startGame (){
        isActive = true;
        for (int i = 0; i < enemies.length; i++) {
            enemies[i].startThread();
            enemies[i].enemyThread.start();
        }
        while(isActive) {
            if (pacMan.moveTo(pacMan.direction)) {
                for (int i = 0; i < 2; i++) {
                    driver.makeHalfMove(pacMan.direction);
                    driver.updateGui();
                    try {
                        Thread.sleep(SLOWDOWN);
                    } catch (InterruptedException e) {
                        System.out.println("Caught " + e.toString());
                    }
                    driver.labyrinth.get(pacMan.placeY).get(pacMan.placeX).coin = null;
                }
            }
            if (!(pacMan.direction == pacMan.direction2)) {
                switch (pacMan.direction2) {
                    case LEFT:
                        if (!driver.labyrinth.get(pacMan.getPlaceY()).get(pacMan.getPlaceX() - 1).isWall()) {
                            pacMan.direction = pacMan.direction2;
                        }
                        break;
                    case RIGHT:
                        if (!driver.labyrinth.get(pacMan.getPlaceY()).get(pacMan.getPlaceX() + 1).isWall()) {
                            pacMan.direction = pacMan.direction2;
                        }
                        break;
                    case UP:
                        if (!driver.labyrinth.get(pacMan.getPlaceY() - 1).get(pacMan.getPlaceX()).isWall()) {
                            pacMan.direction = pacMan.direction2;
                        }
                        break;
                    case DOWN:
                        if (!driver.labyrinth.get(pacMan.getPlaceY() + 1).get(pacMan.getPlaceX()).isWall()) {
                            pacMan.direction = pacMan.direction2;
                        }
                        break;
                }
            }
            if (Coin.coins.size() == 0) {
                isActive = false;
            }
        }
    }

    public void finishGame(){
        driver.showGameOverWindow();
        System.out.println("Game over");
        System.out.println("закрыть потоки?");
    }

    public int getNumOfCoins() {
        return numOfCoins;
    }

    public static Game getGame() {
        return game;
    }

    public static PacMan getPacMan() {
        return pacMan;
    }
}
