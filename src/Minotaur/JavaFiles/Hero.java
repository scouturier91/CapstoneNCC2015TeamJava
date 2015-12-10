/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minotaur.JavaFiles;

import Minotaur.JavaFiles.MinBaseChar;
import java.awt.Color;
import java.awt.Graphics2D;

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

    @Override
    public void move(Graphics2D g2d) {
        moveX();
        moveY();
        
        //g2d.drawImage(hero, herox, heroy, blockSize, blockSize, this);
        g2d.setColor(Color.orange);
        g2d.drawOval(herox, heroy, blockSize, blockSize);

        //g2d.drawImage(hero, herox, heroy, null);
    }
    
    @Override
    public void moveX() {
        heroChangeInPosX = heroChangeInDirX;
        heroChangeInPosY = heroChangeInDirY;

        //deals with wall collisions and moves coordinates of hero in x 
        if (herox + heroSpeed * heroChangeInPosX > Grid.getDimension().getWidth() - blockSize * 2) {
            herox = (int) Grid.getDimension().getWidth() - blockSize * 2 - 1;
        } else if (herox + heroSpeed * heroChangeInPosX < 0 + blockSize) {
            herox = 0 + blockSize;
        } else if (!checkVertWalls((herox + blockSize / 2) + heroChangeInPosX, heroy)
                && !checkVertWalls((herox - blockSize / 2) + heroChangeInPosX, heroy)) {
            herox = herox + heroSpeed * heroChangeInPosX;
        }
    }

    @Override
    public void moveY() {
        //deals with wall collisions and moves coordinates of hero in y
        if (heroy + heroSpeed * heroChangeInPosY > Grid.getDimension().getHeight() - blockSize * 2) {
            heroy = (int) Grid.getDimension().getHeight() - blockSize * 2 - 1;
        } else if (heroy + heroChangeInPosY < 0 + blockSize) {
            heroy = 0 + blockSize;
        } else if (!checkHorWalls(herox, heroy + heroChangeInPosY)
                && !checkHorWalls(herox, (heroy + blockSize) + heroChangeInPosY)) {
            heroy = heroy + heroSpeed * heroChangeInPosY;
        }
    }
   
    public void checkAlive(int enemyx, int enemyy) {
        if (enemyy > heroy && enemyy < heroy + blockSize) {
            if(enemyx > herox && enemyx <herox + blockSize){
                Grid.setDying(true);
            }
        }
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
