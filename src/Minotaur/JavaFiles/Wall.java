//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 1.0
//Class for Walls

package Minotaur.JavaFiles;

/**
 *
 * @author Steve
 */
public class Wall {
    //x and y coordinates
    private int x;
    private int y;
    //if true will designate a horizontal wall
    private boolean hor;
    
    public Wall(int xCoor, int yCoor, boolean horz){
        this.x = xCoor;
        this.y = yCoor;
        this.hor = horz;
    }
    
    //getters and setters
    public boolean isHorz(){
        return hor;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}
