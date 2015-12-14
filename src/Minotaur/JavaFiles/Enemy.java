//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 1.0
//Enemy Class

package Minotaur.JavaFiles;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Steve
 */
public class Enemy extends MinBaseChar{
    
    //images for the hero and minotaur 
    private Image enemypic;
    ImageIcon enemyIcon = new ImageIcon(this.getClass().getClassLoader().getResource("res/Minotaur.jpg"));
    
    private final int blockSize = Grid.BLOCK_SIZE;
    
    private final int startCoordinateX = blockSize * 4;
    private final int startCoordinateY = blockSize * 7;
    //x and y coordinates of the enemy
    private int enemyx = startCoordinateX;
    private int enemyy = startCoordinateY;
    //change in position of the enemy in the x direction
    private int enemyChangeInPosX;
    //change in position of the enemy in the y direction
    private int enemyChangeInPosY;
    //change in enemys direction
    private int enemyChangeInDirX;
    private int enemyChangeInDirY;
    //speed of the enemy
    private int enemySpeed = 1;
    
    @Override
    public void move(Graphics2D g2d) {
        
        determineDir();
        moveCheckBorders();
        moveCheckInnerWalls();
        
        g2d.drawImage(enemypic, enemyx, enemyy, blockSize, blockSize, null);
    }
    
    public void determineDir(){
        enemyChangeInDirX = 0;
        enemyChangeInDirY = 0;

        if (Hero.getStaticX() > enemyx) {
            enemyChangeInDirX = 1;
        } else if (Hero.getStaticX() < enemyx) {
            enemyChangeInDirX = -1;
        }

        if (enemyChangeInDirX == 0) {
            if (Hero.getStaticY() > enemyy) {
                enemyChangeInDirY = 1;
            } else if (Hero.getStaticY() < enemyy) {
                enemyChangeInDirY = -1;
            }
        }

        enemyChangeInPosX = enemyChangeInDirX;
        enemyChangeInPosY = enemyChangeInDirY;
    }
    
    public void moveCheckBorders(){
        //deals with wall collisions and moves coordinates of enemy in x 
        if (enemyx + enemySpeed * enemyChangeInPosX > Grid.getDimension().getWidth() - blockSize * 2) {
            enemyx = (int) Grid.getDimension().getWidth() - blockSize * 2 - 1;
        } else if (enemyx + enemySpeed * enemyChangeInPosX < 0 + blockSize) {
            enemyx = 0 + blockSize;
        } else if (enemyy + enemySpeed * enemyChangeInPosY > Grid.getDimension().getHeight() - blockSize * 2) {
            enemyy = (int) Grid.getDimension().getHeight() - blockSize * 2 - 1;
        } else if (enemyy + enemySpeed * enemyChangeInPosY < 0 + blockSize) {
            enemyy = 0 + blockSize;
        }
    }

    public void moveCheckInnerWalls() {
        if (!checkWalls(enemyx + enemyChangeInPosX, enemyy)
                && !checkWalls(enemyx + enemyChangeInPosX, enemyy)) {
            enemyx = enemyx + enemyChangeInPosX * enemySpeed;

        } else {
            enemyChangeInPosX = 0;
            if (enemyy <= Hero.getStaticY()){
                enemyy = enemyy + 1 * enemySpeed;
            }else {
                enemyy = enemyy -1 * enemySpeed;
            }

        }
        if (!checkWalls(enemyx, enemyy + enemyChangeInPosY)
                && !checkWalls(enemyx, enemyy + enemyChangeInPosY)) {
            enemyy = enemyy + enemySpeed * enemyChangeInPosY;
        } else {
            enemyChangeInPosY = 0;
            if (enemyx <= Hero.getStaticX()){
                enemyx = enemyx + 1 * enemySpeed;
            }else {
                enemyx = enemyx - 1 * enemySpeed;
            }
        }
    }
    
    @Override
    public int getX(){
        return enemyx;
    }
    @Override
    public int getY(){
        return enemyy;
    }

    @Override
    public void increaseSpeed() {
        this.enemySpeed++;
    }

    @Override
    public void resetPos() {
        enemyx = startCoordinateX;
        enemyy = startCoordinateY;
    }
    
    public void loadImage(){
        enemypic = enemyIcon.getImage();
    }
}
