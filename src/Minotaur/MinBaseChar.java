/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minotaur;

import java.awt.Graphics2D;

/**
 *
 * @author Steve
 */
public abstract class MinBaseChar {

    private final int blockSize = Grid.blockSize;
    /**
     * the move interface for the sprites
     * @param g2d the graphics object 
     */
    public abstract void move(Graphics2D g2d);

    //checks whether the sprite is colliding with a vertical wall inside the maze
    public boolean checkVertWalls(int x, int y) {
        for (int i = 0; i < Grid.getVertY().length; i++) {
            if (Grid.getVertX()[i] == x) {
                if (y > Grid.getVertY()[i] - blockSize && y < Grid.getVertY()[i] + blockSize) {
                    //System.out.println("x " + innerVertWallsX[i] + "y " + innerVertWallsY[i]);
                    return true;
                }
            } else if (x > Grid.getVertX()[i] && x + blockSize < Grid.getVertX()[i]) {
                return true;
            }
        }
        return false;
    }

    //checks whether the sprite is colliding with a horizontal wall inside the maze
    public boolean checkHorWalls(int x, int y) {
        for (int i = 0; i < Grid.getHorX().length; i++) {
            //check the y coordinates to see if there is a wall on that axis
            if (Grid.getHorY()[i] == y) {
                //check the x coordinates to see if there is wall within the coordinates of the next move
                if (x > Grid.getHorY()[i] - blockSize && x < Grid.getHorY()[i] + blockSize) {
                    //System.out.println("x " + innerHorWallsX[i] + "y " + innerHorWallsY[i]);
                    return true;
                }
            } else if (y < Grid.getHorX()[i] && y + blockSize > Grid.getHorX()[i]) {
                System.out.println("x " + Grid.getHorX()[i] + "y " + Grid.getHorY()[i]);
                return true;
            }
        }
        return false;
    }
    
     
 
}
