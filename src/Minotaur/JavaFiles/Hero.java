/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minotaur.JavaFiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Steve
 */
public class Hero extends MinBaseChar{
    
    private static final int blockSize = Grid.blockSize;
    //x and y coordinates of the hero
    private static int herox = blockSize * 4;
    private static int heroy = blockSize;
    //change in position of the hero in the x direction
    private int heroChangeInPosX;
     //change in position of the hero in the y direction
    private int heroChangeInPosY;
    //change in heros direction
    private static int heroChangeInDirX;
    private static int heroChangeInDirY;
    //speed of the hero
    private int heroSpeed = 1;
    
    Rectangle hero = new Rectangle(herox, heroy, blockSize, blockSize);
    Rectangle enemy = new Rectangle(0, 0, blockSize, blockSize);

    @Override
    public void move(Graphics2D g2d) {
        moveCheckBorders();
        moveCheckInnerWalls();
  
        //g2d.drawImage(hero, herox, heroy, blockSize, blockSize, this);
        g2d.setColor(Color.orange);
        g2d.fillRect(herox, heroy, blockSize, blockSize);
        //g2d.drawImage(hero, herox, heroy, null);
    }
    
    @Override
    public void moveCheckBorders() {
        heroChangeInPosX = heroChangeInDirX;
        heroChangeInPosY = heroChangeInDirY;
        
        if (herox + heroSpeed * heroChangeInPosX > Grid.getDimension().getWidth() - blockSize * 2) {
            herox = (int) Grid.getDimension().getWidth() - blockSize * 2 - 1;
        } else if (herox + heroSpeed * heroChangeInPosX < 0 + blockSize) {
            herox = 0 + blockSize;
        }else if (heroy + heroSpeed * heroChangeInPosY > Grid.getDimension().getHeight() - blockSize * 2) {
            heroy = (int) Grid.getDimension().getHeight() - blockSize * 2 - 1;
        } else if (heroy + heroChangeInPosY < 0 + blockSize) {
            heroy = 0 + blockSize;
        }      
    }

    @Override
    public void moveCheckInnerWalls() {
        if (!checkWalls(herox  + heroChangeInPosX, heroy)
                && !checkWalls(herox + heroChangeInPosX, heroy)) {
            herox = herox + heroChangeInPosX;
        }
        if (!checkWalls(herox, heroy + heroChangeInPosY)
                && !checkWalls(herox, heroy + heroChangeInPosY)) {
            heroy = heroy + heroSpeed * heroChangeInPosY;
        }
    }
   
    public void checkAlive(int enemyx, int enemyy) {
        
       hero.setLocation(herox, heroy);
       enemy.setLocation(enemyx, enemyy);
        if (enemy.intersects(hero)) {
                Grid.setDying(true);
        }
    }
 
    public boolean checkWin(Rectangle stairs) {
        if (stairs.intersects(hero)){
            return true;
        }
        return false;
    }
    
    public static int getHeroX(){
        return herox;
    }
    
    public static int getHeroY(){
        return heroy;
    }
    
    public void setHeroChangeX(int x) {
        Hero.heroChangeInDirX = x;
    }

    public void setHeroChangeY(int y) {
        Hero.heroChangeInDirY = y;
    }

}
