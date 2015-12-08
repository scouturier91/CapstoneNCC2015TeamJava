//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 0.1
//The class for the map that everything will move within
package Minotaur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Grid extends JPanel implements ActionListener, KeyListener {

    private boolean levelTime = true;
    private Timer time;
    //images for the hero and minotaur 
    public Image hero;
    public ImageIcon hIcon = new ImageIcon("C:\\Users\\mrswe_000\\Desktop\\code\\CapstoneNCC2015TeamJava\\src\\Minotaur");
    
    private Image minotaur;

    //dimensiosn for the map size
    private static final int blockSize = 50;
    private static final int numOfBlocks = 9;
    public static final int screenSize = numOfBlocks * blockSize;
    private Dimension dim = new Dimension(screenSize, screenSize);
    
    //checks to see whether the hero is still alive
    private boolean dying = false;
    private boolean inGame = false;
    
    //the x and y coordinates of the inner walls
    private int innerWallsX[] = new int[50];
    private int innerWallsY[] = new int[50];
    
    //x and y coordinates of the hero
    private int herox = blockSize * 4;
    private int heroy = blockSize;
    //change in position of the hero in the x direction
    private int heroChangeInPosX;
     //change in position of the hero in the y direction
    private int heroChangeInPosY;
    //change in heros direction
    private int heroChangeInDirX;
    private int heroChangeInDirY;
    //speed of the hero
    private int heroSpeed = 1;
    
    //x and y coordinates of the enemy
    private int enemyx = blockSize * 4;
    private int enemyy = blockSize * 5;
    //change in position of the enemy in the x direction
    private int enemyChangeInPosX;
     //change in position of the enemy in the y direction
    private int enemyChangeInPosY;
    //change in enemys direction
    private int enemyChangeInDirX;
    private int enemyChangeInDirY;
    //speed of the enemy
    private int enemySpeed = 1;
    
    private final int stairsX = blockSize * 4;
    private final int stairsY = blockSize * 4;

    private final short[][] screenData = new short[numOfBlocks][numOfBlocks];
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
    public Grid() 
    {        
        loadImages();
        addKeyListener(this);
        
        setFocusable(true);
        inGame = true;
        time = new Timer(60, (ActionEvent evt) -> 
        {
            if (!time.isRunning())
            {
                levelTime = false;
            }
        });
        time.start();
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
        getInnerWalls();
    }
    
    //puts the inner walls in arrays 
    private void getInnerWalls() 
    {
        int k = 0;
        for (int i = 0; i < screenData.length; i++) 
        {
            for (int j = 0; j < screenData.length; j++) 
            {
                if (screenData[j][i] == 2) 
                {
                    //adds any walls that arent on the outside 
                    if (j != 0 && i != 0 && i != 9 && j != 9)
                    {
                        innerWallsX[k] = j * blockSize;
                        innerWallsY[k] = i * blockSize; 
                        k++;
                    }      
                }
            }
        }
         
    }

    //draws the map
    public void drawMap(Graphics2D g) 
    {
        for (int i = 0; i < screenData.length; i++) 
        {
            for (int j = 0; j < screenData.length; j++) 
            {
                if (screenData[j][i] == 2) 
                {
                        g.setColor(Color.green);
                        g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                }
            }
        }
    }

    //loads in the images from the resources folder
    public void loadImages() 
    {
     
        //try {
            //FileInputStream input = new FileInputStream("hero.jpg");
            //input.read();
            hero = hIcon.getImage();
            //hero = ImageIO.read(getClass().getResourceAsStream("C:\\Users\\mrswe_000\\Desktop\\code\\CapstoneNCC2015TeamJava\\src\\Minotaur"));
       // } catch (IOException ex) {
          //  Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
        //}
      

    }

    public void playGame(Graphics2D g2d) throws InterruptedException 
    {        
        if (dying) 
        {
            death();
        } else 
        {
            moveHero(g2d);
            moveEnemy(g2d);
            checkAlive();
            repaint();
            Thread.sleep(6);
        }
    }
    
    private void moveHero(Graphics2D g2d) {
        heroChangeInPosX = heroChangeInDirX;
        heroChangeInPosY = heroChangeInDirY;

        //deals with wall collisions and moves coordinates of hero in x 
        if (herox + heroSpeed * heroChangeInPosX > dim.getWidth() - blockSize * 2) {
            herox = (int) dim.getWidth() - blockSize * 2 - 1;
            //heroChangeInPosX = -heroChangeInPosX;
        } else if (herox + heroSpeed * heroChangeInPosX < 0 + blockSize) {
            herox = 0 + blockSize;
            //heroChangeInPosX = -heroChangeInPosX;
        } else {
            if (!checkBoxes(herox + heroSpeed * heroChangeInPosX, heroy))
            {
                herox = herox + heroSpeed * heroChangeInPosX;
            }  
        }

        //deals with wall collisions and moves coordinates of hero in y
        if (heroy + heroSpeed * heroChangeInPosY > dim.getHeight() - blockSize * 2) {
            heroy = (int) dim.getHeight() - blockSize * 2 - 1;
            //heroChangeInPosY = -heroChangeInPosY;
        } else if (heroy + heroSpeed * heroChangeInPosY < 0 + blockSize) {
            heroy = 0 + blockSize;
            //heroChangeInPosY = -heroChangeInPosY;
        } else {
            if (!checkBoxes(heroy, heroy + heroSpeed * heroChangeInPosY))
            {
                heroy = heroy + heroSpeed * heroChangeInPosY;
            }
        }
        
        g2d.drawImage(hero, herox, heroy, blockSize, blockSize, this);
        //g2d.setColor(Color.orange);
        //g2d.drawOval(herox, heroy, blockSize, blockSize);

        //g2d.drawImage(hero, herox, heroy, null);
    }
    
    //checks whether the sprite is colliding with a wall inside the maze
    public boolean checkBoxes(int x, int y) 
    {
        for (int i = 0; i < innerWallsX.length; i++) {

            if (y > innerWallsY[i] && y < innerWallsY[i] + blockSize) {
                if (x > innerWallsX[i] && x < innerWallsX[i] + blockSize) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            drawBoard((Graphics2D)g);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public void drawBoard(Graphics2D g2d) throws InterruptedException
    {
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, dim.width, dim.height);
        drawMap(g2d);
        playGame(g2d);
        if (!levelTime)
        {
            drawStairs(g2d);
        }
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        repaint();
    }

    private void death() {
        time.stop();
        inGame = false;
    }

    private void moveEnemy(Graphics2D g2d) {
        if (heroChangeInDirX == 1) {
            enemyChangeInDirX = 1;
        }
        else if (heroChangeInDirX == -1) {
            enemyChangeInDirX = -1;
        }
        else if (heroChangeInDirY == 1) {
            enemyChangeInDirY = 1;
        }
        else if (heroChangeInDirY == -1) {
            enemyChangeInDirY = -1;
        }

        enemyChangeInPosX = enemyChangeInDirX;
        enemyChangeInPosY = enemyChangeInDirY;
        
        //deals with wall collisions and moves coordinates of enemy in x 
        if (enemyx + enemySpeed * enemyChangeInPosX > dim.getWidth() - blockSize * 2)
        {
            enemyx = (int) dim.getWidth() - blockSize * 2 -1;
            //enemyChangeInPosX = -enemyChangeInPosX;
        }
        else if (enemyx + enemySpeed * enemyChangeInPosX < 0 + blockSize)
        {
            enemyx = 0 + blockSize;
            //enemyChangeInPosX = -enemyChangeInPosX;
        }
        else
        {
            if (!checkBoxes(enemyx + enemySpeed * enemyChangeInPosX, enemyy))
            {
                enemyx = enemyx + enemySpeed * enemyChangeInPosX;
            }
        }
        
        //deals with wall collisions and moves coordinates of hero in y
        if (enemyy + enemySpeed * enemyChangeInPosY > dim.getHeight() - blockSize * 2)
        {
            enemyy = (int) dim.getHeight() - blockSize * 2 - 1;
            //enemyChangeInPosY = -enemyChangeInPosY;
        }
        else if (enemyy + enemySpeed * enemyChangeInPosY < 0 + blockSize)
        {
            enemyy = 0 + blockSize;
            //enemyChangeInPosY = -enemyChangeInPosY;
        }
        else
        {
            if (!checkBoxes(enemyx, enemyy + enemySpeed * enemyChangeInPosY))
            {
                enemyy = enemyy + enemySpeed * enemyChangeInPosY;
            }
        }
        
        g2d.setColor(Color.red);
        g2d.drawOval(enemyx, enemyy, blockSize, blockSize);
        enemyChangeInDirX = heroChangeInDirX;
        enemyChangeInDirY = heroChangeInDirY;
    }

    private void checkAlive() {
        if (herox < enemyx + blockSize && herox > enemyx + blockSize) {
            if (heroy < enemyy + blockSize && heroy > enemyy + blockSize) {
                dying = true;
            }
        }
    }
    
    private void drawStairs(Graphics2D g2d)
    {
        g2d.setColor(Color.blue);
        g2d.drawRect(stairsX, stairsY, blockSize, blockSize);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
