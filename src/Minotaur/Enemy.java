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
public class Enemy extends MinBaseChar{
    
    private final int blockSize = Grid.blockSize;
    //x and y coordinates of the enemy
    private int enemyx = blockSize * 4;
    private int enemyy = blockSize * 5;
    //change in position of the enemy in the x direction
    private int enemyChangeInPosX;
    //change in position of the enemy in the y direction
    private int enemyChangeInPosY;
    //change in enemys direction
    private int enemyChangeInDirX;
    private int enemyChangeInDirY;
    //speed of the enemy
    private int enemySpeed = 1;
    
    public void move(Graphics2D g2d) {
        enemyChangeInDirX = 0;
        enemyChangeInDirY = 0;

        if (enemyChangeInDirY == 0) {
            if (Hero.getHeroX() > enemyx) {
                enemyChangeInDirX = 1;
            } else if (Hero.getHeroX() < enemyx) {
                enemyChangeInDirX = -1;
            }
        }
        if (enemyChangeInDirX == 0) {
            if (Hero.getHeroY() > enemyy) {
                enemyChangeInDirY = 1;
            } else if (Hero.getHeroY() < enemyy) {
                enemyChangeInDirY = -1;
            }
        }

        enemyChangeInPosX = enemyChangeInDirX;
        enemyChangeInPosY = enemyChangeInDirY;

        //deals with wall collisions and moves coordinates of enemy in x 
        if (enemyx + enemySpeed * enemyChangeInPosX > Grid.getDimension().getWidth() - blockSize * 2) {
            enemyx = (int) Grid.getDimension().getWidth() - blockSize * 2 - 1;
        } else if (enemyx + enemySpeed * enemyChangeInPosX < 0 + blockSize) {
            enemyx = 0 + blockSize;
        } else if (!checkVertWalls((enemyx + blockSize / 2) + 1 * enemyChangeInPosX, enemyy)
                && !checkVertWalls((enemyx - blockSize / 2) + 1 * enemyChangeInPosX, enemyy)) {
            enemyx = enemyx + enemySpeed * enemyChangeInPosX;
        } else {
            enemyy = enemyy + enemySpeed * enemyChangeInPosX;
        }

        //deals with wall collisions and moves coordinates of hero in y
        if (enemyy + enemySpeed * enemyChangeInPosY > Grid.getDimension().getHeight() - blockSize * 2) {
            enemyy = (int) Grid.getDimension().getHeight() - blockSize * 2 - 1;
        } else if (enemyy + enemySpeed * enemyChangeInPosY < 0 + blockSize) {
            enemyy = 0 + blockSize;
        } else {
            if (!checkHorWalls(enemyx, enemyy + enemySpeed * enemyChangeInPosY)
                    && !checkHorWalls(enemyx, (enemyy + blockSize) + enemySpeed * enemyChangeInPosY)) {
                enemyy = enemyy + enemySpeed * enemyChangeInPosY;
            } else {
                {
                    enemyx = enemyx + enemySpeed * enemyChangeInPosY;
                }
            }

            g2d.setColor(Color.red);
            g2d.drawOval(enemyx, enemyy, blockSize, blockSize);
        }

    }
    
    public int getEnemyX(){
        return enemyx;
    }
    public int getEnemyY(){
        return enemyy;
    }
}
