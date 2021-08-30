package controller;

import model.*;
import view.GameFrame;
import view.LabyrinthPanel;
import view.ScorePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by gyseynov on 07.09.14.
 */
public class Driver {
    Thread guiThread;
    public GameFrame gameFrame;
    public LabyrinthPanel labyrinthPanel;
    public ScorePanel scorePanel;
    public volatile PacMan pacMan;
    public Labyrinth labyrinth;
    public volatile boolean isDirNotChanged = true;

    public void startGui(){
        guiThread = new Thread(new Runnable() {
            @Override
            public void run() {
                gameFrame = new GameFrame();
            }
        });
        guiThread.start();
    }

    public void makeHalfMove(GameCharacter.Direction direction) {
        switch(direction) {
            case LEFT:
                if ((labyrinthPanel.pacManX == 0) && (labyrinthPanel.pacManY == (14 * LabyrinthPanel.LEVEL_BLOCK_SIZE))) {
                    labyrinthPanel.pacManX = 27 * LabyrinthPanel.LEVEL_BLOCK_SIZE;
                }
                labyrinthPanel.pacManX -= LabyrinthPanel.HALF_STEP;
                break;
            case RIGHT:
                if ((labyrinthPanel.pacManX == (27 * LabyrinthPanel.LEVEL_BLOCK_SIZE)) && (labyrinthPanel.pacManY == (14 * LabyrinthPanel.LEVEL_BLOCK_SIZE))) {
                    labyrinthPanel.pacManX = 0;
                }
                labyrinthPanel.pacManX += LabyrinthPanel.HALF_STEP;
                break;
            case UP:
                labyrinthPanel.pacManY -= LabyrinthPanel.HALF_STEP;
                break;
            case DOWN:
                labyrinthPanel.pacManY += LabyrinthPanel.HALF_STEP;
                break;
        }
        labyrinthPanel.isMouthOpened = !labyrinthPanel.isMouthOpened;
    }

    public void processKeys(GameCharacter.Direction direction) {
        switch (direction) {
            case LEFT:
                pacMan.direction2 = GameCharacter.Direction.LEFT;
                break;
            case RIGHT:
                pacMan.direction2 = GameCharacter.Direction.RIGHT;
                break;
            case UP:
                pacMan.direction2 = GameCharacter.Direction.UP;
                break;
            case DOWN:
                pacMan.direction2 = GameCharacter.Direction.DOWN;
                break;
        }
    }

    public void showGameOverWindow(){
        String message;
        if (pacMan.isAlive) {
            message = "Поздравляю! Ты победил.";
        } else {
            message = "PacMan убит :(";
        }
        labyrinthPanel.optionPane.showMessageDialog(labyrinthPanel, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    public void updateGui() {
        labyrinthPanel.repaint();
        scorePanel.repaint();
    }
}
