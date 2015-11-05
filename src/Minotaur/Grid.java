//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 0.1
//The class for the map that everything will move within
package Minotaur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grid extends JPanel implements ActionListener {

    private Dimension dim;
    //images for the hero and minotaur 
    private Image hero;
    private Image minotaur;
    private Image img;

    private final int blockSize = 4;
    private final int numOfBlocks = 9;
    private final int screenSize = numOfBlocks * blockSize;

    //checks to see whether the hero is still alive
    private boolean dying = false;
    private boolean inGame = false;

    private short[] screenData;

    private int[] dimX;
    private int[] dimY;

    /**
     * the data for the board describing where and what each map piece will be
     * the sum of the number will generate the right cell 0 = blank space 1 =
     * left corner 2 = top corner 4 = right corner 8 = bottom corner ex: top
     * left corner = 3 = 1 +2
        *
     */
    private final short boardData[]
            = {
                //will currently generate a blank map
                3, 2, 2, 2, 2, 2, 2, 6,
                1, 0, 0, 0, 0, 0, 0, 4,
                1, 0, 0, 0, 0, 0, 0, 4,
                1, 0, 0, 2, 0, 0, 0, 4,
                1, 0, 0, 0, 0, 0, 0, 4,
                1, 0, 0, 0, 0, 0, 0, 4,
                1, 0, 0, 0, 0, 0, 0, 4,
                9, 8, 8, 8, 8, 8, 8, 12
            };

    //default constuctor called on game startup
    public Grid() {
        loadImages();
        initVar();

        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);
    }

    //creates and initializes the needed variables
    private void initVar() {
        screenData = new short[numOfBlocks * numOfBlocks];
        dim = new Dimension(400, 400);
        dimX = new int[4];
        dimY = new int[4];

    }
    
    private void startLevel() 
    {
        for(int i = 0;i < numOfBlocks * numOfBlocks; i++)
        {
            screenData[i] = boardData[i];
        }
        cont();
    }
    
    private void cont() {
       
    } 
    

    //calls the super class method paint component then calls the draw method
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        draw(g);

    }

    //loads in the images from the resources folder
    public void loadImages() {
        hero = new ImageIcon("hero.jpg").getImage();
        minotaur = new ImageIcon("Minotaur").getImage();

    }

    //Draws the maze based of the gameboard array
    public void drawMaze(Graphics2D g2d) {
        //will draw the initial maze
        int i = 0;
        int x;
        int y;

        for (y = 0; y < screenSize; y += blockSize) {
            for (x = 0; x < screenSize; x += blockSize) {
                
                g2d.setColor(Color.black);
                g2d.setStroke(new BasicStroke(2));

                if ((boardData[i] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + blockSize - 1);
                }

                if ((boardData[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + blockSize - 1, y);
                }

                if ((boardData[i] & 4) != 0) {
                    g2d.drawLine(x + blockSize - 1, y, x + blockSize - 1, y + blockSize - 1);
                }

                if ((boardData[i] & 8) != 0) {
                    g2d.drawLine(x, y + blockSize - 1, x + blockSize - 1, y + blockSize - 1);
                }
                i++;
            }
        }

    }

    //runs the required functions to continue the game    
    public void runGame(Graphics2D g2d) {
        if (dying != true) {
            moveHero();
            drawHero(g2d);
            moveEnemy(g2d);
            checkMap();
        } else {
            death();
        }
    }

    //if true exits game and returns to the title screen
    private void death() {
        inGame = false;
    }

    //repostions the hero and moves him accordingly
    private void moveHero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //draws the hero on the map
    private void drawHero(Graphics2D g2d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //repositions the enmey
    private void moveEnemy(Graphics2D g2d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //draws the enemy on the map
    public void drawEnemy(Graphics2D g2d, int x, int y) {
        //g2d.drawImage(minotaur, x, y, rootPane);
    }

    private void checkMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.red);
        g2d.fillRect(0, 0, dim.width, dim.height);

        drawMaze(g2d);

        if (inGame != false) {
            runGame(g2d);
        } else {
            //code to run title screen
        }

        g2d.drawImage(img, 5, 5, this);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }



}
