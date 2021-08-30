package view;

import controller.Driver;
import model.Game;
import model.GameCharacter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by gyseynov on 07.09.14.
 */
public class GameFrame extends JFrame {
    public JPanel labyrinthPanel;
    JPanel guiPanel = new JPanel();
    Driver driver = Game.driver;
    public ScorePanel scorePanel = new ScorePanel();

    public GameFrame() {
        synchronized (Game.getGame()) {
            initGui();
            Game.getGame().notify();
        }
    }

    public void initGui(){
        labyrinthPanel = new LabyrinthPanel();
        labyrinthPanel.setLayout(null);

        guiPanel.setLayout(new BoxLayout(guiPanel, BoxLayout.Y_AXIS));
        guiPanel.add(scorePanel);
        guiPanel.add(labyrinthPanel);
        setContentPane(guiPanel);

        labyrinthPanel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftKeyAction");
        labyrinthPanel.getActionMap().put("leftKeyAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driver.processKeys(GameCharacter.Direction.LEFT);
            }
        });

        labyrinthPanel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightKeyAction");
        labyrinthPanel.getActionMap().put("rightKeyAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driver.processKeys(GameCharacter.Direction.RIGHT);
            }
        });

        labyrinthPanel.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upKeyAction");
        labyrinthPanel.getActionMap().put("upKeyAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driver.processKeys(GameCharacter.Direction.UP);
            }
        });

        labyrinthPanel.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downKeyAction");
        labyrinthPanel.getActionMap().put("downKeyAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driver.processKeys(GameCharacter.Direction.DOWN);
            }
        });

        setStandartOptions();
    }

    public void setStandartOptions(){
        pack();
        setVisible(true);
        setName("Pac-Man");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}