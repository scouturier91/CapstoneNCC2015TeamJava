//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 0.1
//The class for the map that everything will move within
package Minotaur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Grid extends JPanel implements ActionListener {

    private Dimension dim;
    //images for the hero and minotaur 
    private Image hero;
    private Image minotaur;

    private final int blockSize = 40;
    private final int numOfBlocks = 9;
    private final int screenSize = numOfBlocks * blockSize;

    //checks to see whether the hero is still alive
    private boolean dying = false;
    private boolean inGame = false;

    private final short[][] screenData = new short[9][9];

    private int herox;
    private int heroy;

    private int enemyx;
    private int enemyy;

    private final short boardData[]
            = {
                //will currently generate a blank map
                2, 2, 2, 2, 2, 2, 2, 2, 2,
                2, 0, 0, 0, 0, 0, 0, 0, 2,
                2, 0, 0, 2, 2, 2, 0, 0, 2,
                2, 2, 0, 0, 0, 2, 0, 2, 2,
                2, 0, 0, 2, 0, 0, 3, 0, 2,
                2, 0, 0, 2, 0, 2, 0, 0, 2,
                2, 2, 0, 2, 0, 2, 0, 2, 2,
                2, 0, 0, 0, 0, 0, 1, 0, 2,
                2, 2, 2, 2, 2, 2, 2, 2, 2
            };

    //default constuctor called on game startup
    public Grid() {
        setSize(screenSize, screenSize);
        loadImages();
        addKeyListener(new MinAdapter());

        startLevel();

    }

    //loads the array into a bucket
    private void startLevel() {
        int z = 0;
        for (int i = 0; i < numOfBlocks; i++) {
            for (int j = 0; j < numOfBlocks; j++) {
                screenData[i][j] = boardData[z];
                z++;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
    }

    //initially draws the map
    public void drawMap(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, screenSize, screenSize);
        for (int i = 0; i < screenData.length; i++) {
            for (int j = 0; j < screenData.length; j++) {
                int num = screenData[j][i];
                switch (num) {
                    case 0:
                        break;
                    case 1:
                        g.setColor(Color.blue);
                        g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                        herox = i;
                        heroy = j;
                        break;

                    case 2:
                        g.setColor(Color.yellow);
                        g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                        break;
                    case 3:
                        g.setColor(Color.red);
                        g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                        enemyx = i;
                        enemyy = j;
                        break;
                    default:
                        System.err.println("invalid character");

                }
            }
        }
        playGame((Graphics2D) g);
    }

    //loads in the images from the resources folder
    public void loadImages() {
        hero = new ImageIcon("hero.jpg").getImage();

    }

    public void playGame(Graphics2D g2d) {
        if (dying) {
            death();
        } else {
            moveHero(g2d);
            //moveEnemy(g2d);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private void death() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void moveHeroLeft(Graphics2D g2d) {
        if (screenData[heroy][herox - 1] != 2) {
            g2d.setColor(Color.black);
            g2d.fillRect(herox * blockSize, heroy * blockSize, blockSize, blockSize);
            g2d.setColor(Color.blue);
            g2d.fillRect(herox - 1 * blockSize, heroy * blockSize, blockSize, blockSize);
        }
    }

    private void moveHeroRight(Graphics2D g2d) {
        if (screenData[heroy][herox + 1] != 2) {
            g2d.setColor(Color.black);
            g2d.fillRect(herox * blockSize, heroy * blockSize, blockSize, blockSize);
            g2d.setColor(Color.blue);
            g2d.fillRect(herox + 1 * blockSize, heroy * blockSize, blockSize, blockSize);
        }
    }

    private void moveHeroDown(Graphics2D g2d) {
        if (screenData[heroy + 1][herox] != 2) {
            g2d.setColor(Color.black);
            g2d.fillRect(herox * blockSize, heroy * blockSize, blockSize, blockSize);
            g2d.setColor(Color.blue);
            g2d.fillRect(herox * blockSize, heroy + 1 * blockSize, blockSize, blockSize);
        }
    }

    private void moveHeroUp(Graphics2D g2d) {
        if (screenData[heroy - 1][herox] != 2) {
            g2d.setColor(Color.black);
            g2d.fillRect(herox * blockSize, heroy * blockSize, blockSize, blockSize);
            g2d.setColor(Color.blue);
            g2d.fillRect(herox * blockSize, heroy - 1 * blockSize, blockSize, blockSize);
        }
    }

    private void moveEnemyLeft(Graphics2D g2d) {
        if (screenData[enemyy][enemyx - 1] != 2) {
            g2d.setColor(Color.black);
            g2d.fillRect(enemyx * blockSize, enemyy * blockSize, blockSize, blockSize);
            g2d.setColor(Color.blue);
            g2d.fillRect(enemyx - 1 * blockSize, enemyy * blockSize, blockSize, blockSize);
        }
    }

    private void moveEnemyRight(Graphics2D g2d) {
        if (screenData[enemyy][enemyx + 1] != 2) {
            g2d.setColor(Color.black);
            g2d.fillRect(enemyx * blockSize, enemyy * blockSize, blockSize, blockSize);
            g2d.setColor(Color.blue);
            g2d.fillRect(enemyx + 1 * blockSize, enemyy * blockSize, blockSize, blockSize);
        }
    }

    private void moveEnemyDown(Graphics2D g2d) {
        if (screenData[enemyy + 1][enemyx] != 2) {
            g2d.setColor(Color.black);
            g2d.fillRect(enemyx * blockSize, enemyy * blockSize, blockSize, blockSize);
            g2d.setColor(Color.blue);
            g2d.fillRect(enemyx * blockSize, enemyy + 1 * blockSize, blockSize, blockSize);
        }
    }

    private void moveEnemyUp(Graphics2D g2d) {
        if (screenData[enemyy - 1][enemyx] != 2) {
            g2d.setColor(Color.black);
            g2d.fillRect(enemyx * blockSize, enemyy * blockSize, blockSize, blockSize);
            g2d.setColor(Color.blue);
            g2d.fillRect(enemyx * blockSize, enemyy - 1 * blockSize, blockSize, blockSize);
        }
    }

    private void moveEnemy(Graphics2D g2d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void checkMaze() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void moveHero(Graphics2D g2d) {
        char key = MinAdapter.keyDir;
        switch (key) {
            case 'l':
                moveHeroLeft(g2d);
            case 'r':
                moveHeroRight(g2d);
            case 'u':
                moveHeroUp(g2d);
            case 'd':
                moveHeroDown(g2d);
        }
    }
}
