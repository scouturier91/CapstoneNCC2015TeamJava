//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 0.1
//The class for the map that everything will move within
package Minotaur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grid extends JPanel implements ActionListener {

    //images for the hero and minotaur 
    private Image hero;
    private Image minotaur;

    private static final int blockSize = 50;
    private static final int numOfBlocks = 9;
    public static final int screenSize = numOfBlocks * blockSize;
    private Dimension dim = new Dimension(screenSize, screenSize);
    //checks to see whether the hero is still alive
    private boolean dying = false;
    private boolean inGame = false;

    private final short[][] screenData = new short[numOfBlocks][numOfBlocks];

    private boolean isRunning = true;
    
    //x and y coordinates of the hero
    private int herox = blockSize;
    private int heroy = blockSize;
    //change in position of the hero in the x direction
    private int heroChangeInPosX;
     //change in position of the hero in the y direction
    private int heroChangeInPosY;
    //change in heros direction
    private int heroChangeInDirX;
    private int heroChangeInDirY;
    
    private int heroSpeed = 1;
    

    private int enemyx;
    private int enemyy;

    private final short boardData[]
            = {
                //will currently generate a blank map
                2, 2, 2, 2, 2, 2, 2, 2, 2,
                2, 0, 0, 0, 0, 0, 0, 0, 2,
                2, 0, 0, 2, 2, 2, 0, 0, 2,
                2, 2, 0, 0, 0, 0, 0, 2, 2,
                2, 0, 0, 2, 0, 2, 0, 0, 2,
                2, 0, 0, 2, 0, 2, 0, 0, 2,
                2, 2, 0, 2, 0, 2, 0, 2, 2,
                2, 0, 0, 0, 0, 0, 0, 0, 2,
                2, 2, 2, 2, 2, 2, 2, 2, 2
            };

    //default constuctor called on game startup
    public Grid() {
        
        loadImages();
        addKeyListener(new MinAdapter());

        setFocusable(true);
        startLevel();
        setDoubleBuffered(true);

    }

    //loads the array into a bucket
    private void startLevel() 
    {
        int z = 0;
        for (int i = 0; i < numOfBlocks; i++) {
            for (int j = 0; j < numOfBlocks; j++) {
                screenData[i][j] = boardData[z];
                z++;
            }
        }  
    }

    //initially draws the map
    public void drawMap(Graphics2D g) 
    {
        for (int i = 0; i < screenData.length; i++) 
        {
            for (int j = 0; j < screenData.length; j++) 
            {
                int num = screenData[j][i];
                switch (num) 
                {
                    case 0: break;
                    case 2:
                        g.setColor(Color.yellow);
                        g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                        break;
                    default:
                        System.err.println("invalid character");

                }
            }
        }
        
    }

    
    
    //loads in the images from the resources folder
    public void loadImages() 
    {
        hero = new ImageIcon("hero.jpg").getImage();

    }

    public void playGame(Graphics2D g2d) throws InterruptedException 
    {        
        if (dying) 
        {
            death();
        } else 
        {
            moveHero(g2d);
            //moveEnemy(g2d);
            repaint();
            Thread.sleep(4);
        }
    }
    
    private void moveHero(Graphics2D g2d) {
        int pos;
        short ch;

        if (heroChangeInDirX == -heroChangeInPosX && heroChangeInDirY == -heroChangeInPosY) {
            heroChangeInPosX = heroChangeInDirX;
            heroChangeInPosY = heroChangeInDirY;
        }

        if (herox % blockSize == 0 && heroy % blockSize == 0) {
            pos = herox / blockSize + numOfBlocks * (int) (heroy / blockSize);
            ch = boardData[pos];


            if (heroChangeInDirX != 0 || heroChangeInDirY != 0) {
                if (ch != 2) {
                    heroChangeInPosX = heroChangeInDirX;
                    heroChangeInPosY = heroChangeInDirY;
                }
            }

        }
       
        herox = herox + heroSpeed * heroChangeInPosX;
        heroy = heroy + heroSpeed * heroChangeInPosY;
        
        g2d.setColor(Color.blue);
        g2d.fillRect( herox + 1, heroy + 1, blockSize, blockSize);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            drawBoard((Graphics2D)g);
        } catch (InterruptedException ex) {
            Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void drawBoard(Graphics2D g2d) throws InterruptedException
    {
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, dim.width, dim.height);
        drawMap(g2d);
        playGame(g2d);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        repaint();
    }

    private void death() {
        inGame = false;
    }

    private void moveEnemy(Graphics2D g2d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void checkMaze() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class MinAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    heroChangeInDirX = -1;
                    heroChangeInDirY = 0;
                    break;
                case KeyEvent.VK_RIGHT:
                    heroChangeInDirX = 1;
                    heroChangeInDirY = 0;
                    break;
                case KeyEvent.VK_UP:
                    heroChangeInDirX = 0;
                    heroChangeInDirY = -1;
                    break;
                case KeyEvent.VK_DOWN:
                    heroChangeInDirX = 0;
                    heroChangeInDirY = 1;
                    break;
                default:
                    break;
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT
                    || key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
                heroChangeInDirX = 0;
                heroChangeInDirY = 0;
            }
            
        }
    }

}
