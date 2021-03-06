//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 1.0
//

package Minotaur.JavaFiles;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 *
 * @author Steve
 */
public abstract class MinBaseChar {

    /**
     * the move interface for the sprites
     * @param g2d the graphics object 
     */
    
    //List of walls used to determine collisions
    private ArrayList<Wall> walls = new ArrayList<>();
    
    public abstract int getX();
    
    public abstract int getY();
    
    public abstract void move(Graphics2D g2d);
    
    public abstract void moveCheckBorders();
    
    public abstract void moveCheckInnerWalls();
    
    public abstract void increaseSpeed();
    
    public abstract void resetPos();
    
    public abstract void loadImage();

    //checks whether the sprite is colliding with a vertical wall inside the maze
    public boolean checkWalls(int x, int y) {
        Rectangle sprite = new Rectangle(x, y, Grid.BLOCK_SIZE, Grid.BLOCK_SIZE);
        for (int i = 0; i < walls.size(); i++) {
            if (walls.get(i).isHorz()) {
                Line2D line = new Line2D.Float(walls.get(i).getX(), walls.get(i).getY(),
                        walls.get(i).getX() + Grid.BLOCK_SIZE, walls.get(i).getY());
                if (line.intersects(sprite)) {
                    return true;
                }
            }
            if(!walls.get(i).isHorz()){
                Line2D line2 = new Line2D.Float(walls.get(i).getX()+ Grid.BLOCK_SIZE/2, walls.get(i).getY(),
                        walls.get(i).getX() + Grid.BLOCK_SIZE/2, walls.get(i).getY() + Grid.BLOCK_SIZE);
                if (line2.intersects(sprite)) {
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
