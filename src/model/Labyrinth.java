package model;

import controller.Driver;

import java.util.ArrayList;

/**
 * Created by gyseynov on 07.09.14.
 */
public class Labyrinth extends ArrayList<ArrayList<LevelBlock>> {
    public static final int WIDTH = 28;
    public static final int HEIGHT = 31;
    private Driver driver;
    Labyrinth(Driver driver){
        this.driver = driver;
        driver.labyrinth = this;
        fillAndInitBlocks();
    }

    private void fillAndInitBlocks(){
        boolean isWall;
        for (int y = 0; y < HEIGHT; y++) {
            add(new ArrayList<LevelBlock>());
            for (int x = 0; x < WIDTH; x++) {
                isWall = false;
                switch (y) {
                    case 0:
                        isWall = true;
                        break;
                    case 1:
                        if ((x == 0) || (x == 13) || (x == 14) || (x == 27)){
                            isWall = true;
                        }
                        break;
                    case 2:
                        isWall = true;
                        if ((x == 1) || (x == 6) || (x == 12) || (x == 15) || (x == 21) || (x == 26)) {
                            isWall = false;
                        }
                        break;
                    case 3:
                        isWall = true;
                        if ((x == 1) || (x == 6) || (x == 12) || (x == 15) || (x == 21) || (x == 26)) {
                            isWall = false;
                        }
                        break;
                    case 4:
                        isWall = true;
                        if ((x == 1) || (x == 6) || (x == 12) || (x == 15) || (x == 21) || (x == 26)) {
                            isWall = false;
                        }
                        break;
                    case 5:
                        if ((x == 0) || (x == 27)){
                            isWall = true;
                        }
                        break;
                    case 6:
                        isWall = true;
                        if ((x == 1) || (x == 6) || (x == 9) || (x == 18) || (x == 21) || (x == 26)) {
                            isWall = false;
                        }
                        break;
                    case 7:
                        isWall = true;
                        if ((x == 1) || (x == 6) || (x == 9) || (x == 18) || (x == 21) || (x == 26)) {
                            isWall = false;
                        }
                        break;
                    case 8:
                        if ((x == 0) || (x == 7) || (x == 8) || (x == 13) || (x == 14) || (x == 19) || (x == 20)|| (x == 27)) {
                            isWall = true;
                        }
                        break;
                    case 9:
                        isWall = true;
                        if ((x == 6) || (x == 12) || (x == 15) || (x == 21)) {
                            isWall = false;
                        }
                        break;
                    case 10:
                        isWall = true;
                        if ((x == 6) || (x == 12) || (x == 15) || (x == 21)) {
                            isWall = false;
                        }
                        break;
                    case 11:
                        isWall = true;
                        if ((x == 6) || ((x > 8) && (x < 19)) || (x == 21)) {
                            isWall = false;
                        }
                        break;
                    case 12:
                        isWall = true;
                        if ((x == 6) || (x == 9) || (x == 18) || (x == 21)) {
                            isWall = false;
                        }
                        break;
                    case 13:
                        isWall = true;
                        if ((x == 6) || (x == 9) || ((x > 10) && (x < 17)) || (x == 18) || (x == 21)) {
                            isWall = false;
                        }
                        break;
                    case 14:
                        if ((x == 10) || (x == 17)) {
                            isWall = true;
                        }
                        break;
                    case 15:
                        isWall = true;
                        if ((x == 6) || (x == 9) || ((x > 10) && (x < 17)) || (x == 18) || (x == 21)) {
                            isWall = false;
                        }
                        break;
                    case 16:
                        isWall = true;
                        if ((x == 6) || (x == 9) || (x == 18) || (x == 21)) {
                            isWall = false;
                        }
                        break;
                    case 17:
                        isWall = true;
                        if ((x == 6) || ((x > 8) && (x < 19)) || (x == 21)) {
                            isWall = false;
                        }
                        break;
                    case 18:
                        isWall = true;
                        if ((x == 6) || (x == 9) || (x == 18) || (x == 21)) {
                            isWall = false;
                        }
                        break;
                    case 19:
                        isWall = true;
                        if ((x == 6) || (x == 9) || (x == 18) || (x == 21)) {
                            isWall = false;
                        }
                        break;
                    case 20:
                        if ((x == 0) || (x == 13) || (x == 14) || (x == 27)){
                            isWall = true;
                        }
                        break;
                    case 21:
                        isWall = true;
                        if ((x == 1) || (x == 6) || (x == 12) || (x == 15) || (x == 21) || (x == 26)) {
                            isWall = false;
                        }
                        break;
                    case 22:
                        isWall = true;
                        if ((x == 1) || (x == 6) || (x == 12) || (x == 15) || (x == 21) || (x == 26)) {
                            isWall = false;
                        }
                        break;
                    case 23:
                        if ((x == 0) || (x == 4) || (x == 5) || (x == 22) || (x == 23) || (x == 27)) {
                            isWall = true;
                        }
                        break;
                    case 24:
                        isWall = true;
                        if ((x == 3) || (x == 6) || (x == 9) || (x == 18) || (x == 21) || (x == 24)) {
                            isWall = false;
                        }
                        break;
                    case 25:
                        isWall = true;
                        if ((x == 3) || (x == 6) || (x == 9) || (x == 18) || (x == 21) || (x == 24)) {
                            isWall = false;
                        }
                        break;
                    case 26:
                        if ((x == 0) || (x == 7) || (x == 8) || (x == 13) || (x == 14) || (x == 19) || (x == 20) || (x == 27)) {
                            isWall = true;
                        }
                        break;
                    case  27:
                        isWall = true;
                        if ((x == 1) || (x == 12) || (x == 15) || (x == 26)) {
                            isWall = false;
                        }
                        break;
                    case 28:
                        isWall = true;
                        if ((x == 1) || (x == 12) || (x == 15) || (x == 26)) {
                            isWall = false;
                        }
                        break;
                    case 29:
                        if ((x == 0) || (x == 27)) {
                            isWall = true;
                        }
                        break;
                    case 30:
                        isWall = true;
                }
                get(y).add(new LevelBlock(x, y, isWall));
                if (!get(y).get(x).isWall() && (!((x > 6) && (x < 21) && (y > 8) && (y < 20)))) {
                    get(y).get(x).coin = new Coin();
                }
/*                if (isWall){
                    System.out.print("█");
                } else {
                    System.out.print(".");
                }*/
            }
            System.out.println();
        }
    }
}

