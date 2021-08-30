package view;

import controller.Driver;
import model.Game;
import model.PacMan;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gyseynov on 14.09.14.
 */
public class LabyrinthPanel extends JPanel{
    Driver driver = Game.driver;
    public static final int LEVEL_BLOCK_SIZE = 20;
    private static final int LABYRINTH_HEIGHT = 30;
    private static final int LABYRINTH_WIDTH = 27;
    private static final int[] WALLS_PLACE_X = {2, 7, 13, 16, 22, 2, 7, 7, 10, 13,     //1-10
                                                  19, 16, 22, -1, 22, -1, 22, 7, 19, 10, //11-20
                                                  13, 2, 4, 7, 16, 22, 22, -1, 25, 2,   //21-30
                                                  7, 10, 13, 16, 19};                  //31-35
    private static final int[] WALLS_PLACE_Y = {2, 2, -1, 2, 2, 6, 6, 9, 6, 6,             //1-10
                                                  6, 9, 6, 9, 9, 15, 15, 15, 15, 18,      //11-20
                                                  18, 21, 21, 21, 21, 21, 21, 24, 24, 27, //21-30
                                                  24, 24, 24, 27, 24};                    //31-35
    private static  final int[] WALLS_WIDTH = {3, 4, 1, 4, 3, 3, 1, 4, 7, 1, //1-10
                                               1, 4, 3, 6, 6, 6, 6, 1, 1, 7, //11-20
                                               1, 3, 1, 4, 4, 3, 1, 3, 3, 9, //21-30
                                               1, 7, 1, 9, 1};               //31-35
    private static final int[] WALLS_HEIGHT = {2, 2, 5, 2, 2, 1, 7, 1, 1, 4, //1-10
                                               7, 1, 1, 4, 4, 4, 4, 4, 4, 1, //11-20
                                               4, 1, 4, 1, 1, 1, 4, 1, 1, 1, //21-30
                                               4, 1, 4, 1, 4};               //31-35
    public static final int HALF_STEP = 10;
    public boolean isMouthOpened = false;
    private Image oLeft = new ImageIcon("src/oLeft.png").getImage();
    private Image cLeft = new ImageIcon("src/cLeft.png").getImage();
    private Image oRight = new ImageIcon("src/oRight.png").getImage();
    private Image cRight = new ImageIcon("src/cRight.png").getImage();
    private Image oUp = new ImageIcon("src/oUp.png").getImage();
    private Image cUp = new ImageIcon("src/cUp.png").getImage();
    private Image oDown = new ImageIcon("src/oDown.png").getImage();
    private Image cDown = new ImageIcon("src/cDown.png").getImage();
    private Image[] oS = {oLeft, oRight, oUp, oDown};
    private Image[] cS = {cLeft, cRight, cUp, cDown};
    private Image inkyLeft = new ImageIcon("src/inkyLeft.png").getImage();
    private Image inkyRight = new ImageIcon("src/inkyRight.png").getImage();
    private Image inkyUp = new ImageIcon("src/inkyUp.png").getImage();
    private Image inkyDown = new ImageIcon("src/inkyDown.png").getImage();
    private Image[] deadPacMans = {new ImageIcon("src/deadLeft.png").getImage(), new ImageIcon("src/deadRight.png").getImage(),
            new ImageIcon("src/deadUp.png").getImage(), new ImageIcon("src/deadDown.png").getImage()};
    private PacMan pacMan = Game.getPacMan();
    public int pacManX = pacMan.getPlaceX() * LEVEL_BLOCK_SIZE;
    public int pacManY = pacMan.getPlaceY() * LEVEL_BLOCK_SIZE;
    public JOptionPane optionPane = new JOptionPane();

    LabyrinthPanel() {
        setBackground(Color.black);
        setPreferredSize(new Dimension(LABYRINTH_WIDTH * LEVEL_BLOCK_SIZE, LABYRINTH_HEIGHT * LEVEL_BLOCK_SIZE));
        setBorder(BorderFactory.createLineBorder(Color.blue, 3, true));
        driver.labyrinthPanel = this;
    }

    public void drawWalls(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.blue);
//        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

        for (int i = 0; i < WALLS_PLACE_X.length; i++) {
            g2.fillRoundRect(WALLS_PLACE_X[i] * LEVEL_BLOCK_SIZE, WALLS_PLACE_Y[i] * LEVEL_BLOCK_SIZE,
                    WALLS_WIDTH[i] * LEVEL_BLOCK_SIZE, WALLS_HEIGHT[i] * LEVEL_BLOCK_SIZE, 7, 7);
        }

        g2.fillRect(10 * LEVEL_BLOCK_SIZE, 12 * LEVEL_BLOCK_SIZE, 7 * LEVEL_BLOCK_SIZE, 4 * LEVEL_BLOCK_SIZE);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_OUT));
        g2.fillRect(12 * LEVEL_BLOCK_SIZE, 12 * LEVEL_BLOCK_SIZE, 3 * LEVEL_BLOCK_SIZE, 8);

        g2.setColor(Color.black);

        for (int i = 0; i < WALLS_PLACE_X.length; i++) {
            g2.fillRoundRect(WALLS_PLACE_X[i] * LEVEL_BLOCK_SIZE + 2, WALLS_PLACE_Y[i] * LEVEL_BLOCK_SIZE + 2,
                    WALLS_WIDTH[i] * LEVEL_BLOCK_SIZE - 4, WALLS_HEIGHT[i] * LEVEL_BLOCK_SIZE - 4, 7, 7);
        }
        g2.fillRect(10 * LEVEL_BLOCK_SIZE + 4, 12 * LEVEL_BLOCK_SIZE + 4, 7 * LEVEL_BLOCK_SIZE - 8, 4 * LEVEL_BLOCK_SIZE - 8);
    }

    public void drawPacMan(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        int index = -1;
        Image[] images = new Image[4];
        if (pacMan.isAlive) {
            images = deadPacMans;
        } else {
            if (isMouthOpened) {
                images = oS;
            } else {
                images = cS;
            }
        }
        switch (driver.pacMan.direction) {
            case LEFT:
                index = 0;
                break;
            case RIGHT:
                index = 1;
                break;
            case UP:
                index = 2;
                break;
            case DOWN:
                index = 3;
                break;
        }
        g2.drawImage(images[index], pacManX - 15, pacManY - 15, 30, 30, null);
}

    public void drawCoins(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        g2.setColor(new Color(254, 213, 131, 255));
        for (int i = 0; i < LABYRINTH_HEIGHT; i++) {
            for (int j = 0; j < LABYRINTH_WIDTH; j++) {
                if (!(driver.labyrinth.get(i).get(j).coin == null)) {
                    g2.fillOval(j * LEVEL_BLOCK_SIZE - 3, i * LEVEL_BLOCK_SIZE - 3, 6, 6);
                }
            }
        }
    }

    public void drawEnemies(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        Image img = null;
        for (int i = 0; i < Game.getEnemies().length; i++) {
            switch (Game.getEnemies()[i].direction){
                case LEFT:
                    img = inkyLeft;
                    break;
                case RIGHT:
                    img = inkyRight;
                    break;
                case UP:
                    img = inkyUp;
                    break;
                case DOWN:
                    img = inkyDown;
                    break;
            }
            g2.drawImage(img, Game.getEnemies()[i].graphicPlaceX - 15, Game.getEnemies()[i].graphicPlaceY - 15, 30, 30, null);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawWalls(g);
        drawPacMan(g);
        drawCoins(g);
        drawEnemies(g);
    }
}
