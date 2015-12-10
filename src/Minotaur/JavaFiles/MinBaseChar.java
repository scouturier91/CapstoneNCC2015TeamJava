/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minotaur.JavaFiles;

import java.awt.Graphics2D;
import java.util.ArrayList;

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
    
    //List of walls used to determine collisions
    private ArrayList<Wall> walls = new ArrayList<>();
    
    public abstract void move(Graphics2D g2d);
    
    public abstract void moveX();
    
    public abstract void moveY();

    //checks whether the sprite is colliding with a vertical wall inside the maze
    public boolean checkVertWalls(int x, int y) {
        for (int i = 0; i < walls.size(); i++) {
            if (!walls.get(i).isHorz()) {
                if (walls.get(i).getX() == x) {
                    if (y > walls.get(i).getY() - blockSize && y < walls.get(i).getY() + blockSize) {
                        //System.out.println("x " + walls.get(i).getX() + "y " + innerVertWallsY[i]);
                        return true;
                    }
                } else if (x > walls.get(i).getX() && x + blockSize < walls.get(i).getX()) {
                    return true;
                }
            }
        }
        return false;
    }

    //checks whether the sprite is colliding with a horizontal wall inside the maze
    public boolean checkHorWalls(int x, int y) {
        for (int i = 0; i < walls.size(); i++) {
            if (walls.get(i).isHorz()) {
                //check the y coordinates to see if there is a wall on that axis
                if (walls.get(i).getY() == y) {
                    //check the x coordinates to see if there is wall within the coordinates of the next move
                    if (x > walls.get(i).getX() - blockSize && x < walls.get(i).getX() + blockSize) {
                        //System.out.println("x " + innerHorWallsX[i] + "y " + innerHorWallsY[i]);
                        return true;
                    }
                } else if (y > walls.get(i).getY() && y + blockSize < walls.get(i).getY()) {
                    System.out.println("x " + walls.get(i).getX() + "y " + walls.get(i).getY());
                    return true;
                }
            }
        }
        return false;
    }
    
     public void setWalls(ArrayList<Wall> walls){
         this.walls = walls;
     }
 
}
