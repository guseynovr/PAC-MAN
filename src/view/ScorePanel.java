package view;

import controller.Driver;
import model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Администратор on 24.09.2014.
 */
public class ScorePanel extends JPanel {
    Driver driver = Game.driver;

    public ScorePanel(){
        driver.scorePanel = this;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(0, 20));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawScore(g);
    }

    public void drawScore(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("TimesRoman", Font.BOLD, 24));
        String score = "SCORE: " + Game.score;
        g2.drawString(score, 5, 20);
    }
}
