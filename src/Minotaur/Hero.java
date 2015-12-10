/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minotaur;

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

    public void move(Graphics2D g2d) {
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

        //deals with wall collisions and moves coordinates of hero in y
        if (heroy + heroSpeed * heroChangeInPosY > Grid.getDimension().getHeight() - blockSize * 2) {
            heroy = (int) Grid.getDimension().getHeight() - blockSize * 2 - 1;
        } else if (heroy + heroChangeInPosY < 0 + blockSize) {
            heroy = 0 + blockSize;
        } else if (!checkHorWalls(herox, heroy + heroChangeInPosY)
                && !checkHorWalls(herox, (heroy + blockSize) + heroChangeInPosY)) {
            heroy = heroy + heroSpeed * heroChangeInPosY;
        }

        //g2d.drawImage(hero, herox, heroy, blockSize, blockSize, this);
        g2d.setColor(Color.orange);
        g2d.drawOval(herox, heroy, blockSize, blockSize);

        //g2d.drawImage(hero, herox, heroy, null);
    }
    
    public void checkAlive(int enemyx, int enemyy) {
        if (herox < enemyx + blockSize && herox > enemyx + blockSize) {
            if (heroy < enemyy + blockSize && heroy > enemyy + blockSize) {
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
    
    public static void setHeroChangeX(int x) {
        Hero.heroChangeInDirX = x;
    }

    public static void setHeroChangeY(int y) {
        Hero.heroChangeInDirY = y;
    }
}
