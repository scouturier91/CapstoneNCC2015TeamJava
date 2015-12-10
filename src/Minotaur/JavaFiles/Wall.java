/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
