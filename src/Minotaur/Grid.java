//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 0.1
//The class for the map that everything will move within
package Minotaur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grid extends Canvas {

    private Dimension dim;
    //images for the hero and minotaur 
    private Image hero;
    private Image minotaur;
    private Image img;

    private final int blockSize = 40;
    private final int numOfBlocks = 9;
    private final int screenSize = numOfBlocks * blockSize;

    //checks to see whether the hero is still alive
    private boolean dying = false;
    private boolean inGame = false;

    private short[][] screenData = new short[9][9];

    private int[] dimX;
    private int[] dimY;

    private final short boardData[]
            = {
                //will currently generate a blank map
                2, 2, 2, 2, 2, 2, 2, 2, 2,
                2, 0, 0, 0, 0, 0, 0, 0, 2,
                2, 0, 0, 2, 2, 2, 0, 0, 2,
                2, 2, 0, 0, 0, 2, 0, 2, 2,
                2, 0, 0, 2, 0, 0, 0, 0, 2,
                2, 0, 0, 2, 0, 2, 0, 0, 2,
                2, 2, 0, 2, 0, 2, 0, 2, 2,
                2, 0, 0, 0, 0, 0, 0, 0, 2,
                2, 2, 2, 2, 2, 2, 2, 2, 2
            };

    //default constuctor called on game startup
    public Grid(Model world) {
        setSize(screenSize, screenSize);
        startLevel();
        loadImages();
        
    }

    
    private void startLevel() 
    {
        int z = 0;
        for(int i = 0;i < numOfBlocks; i++)
        {
            for(int j = 0; j <numOfBlocks; j++)
            {
               screenData[i][j] = boardData[z];
               z++;
            }
           
        }
    }
    
    public void paint(Graphics g) {
       g.setColor(Color.black);
       g.fillRect(0, 0,screenSize, screenSize);
       g.setColor(Color.yellow);
       
       for(int i = 0; i < screenData.length; i++)
       {
           for(int j = 0; j < screenData.length; j++)
           {
               int num = screenData[i][j];
               switch(num)
               {
                   case 0: break;
                   case 1: g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize); break;
                   
                   case 2: g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize); break;
                   default: System.err.println("invalid character");
                       
               }
           }
       }

    }

    //loads in the images from the resources folder
    public void loadImages() {
        hero = new ImageIcon("hero.jpg").getImage();
        minotaur = new ImageIcon("Minotaur").getImage();

    }





}
