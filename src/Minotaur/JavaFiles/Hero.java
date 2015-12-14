//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 1.0
//Hero Class

package Minotaur.JavaFiles;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Steve
 */
public class Hero extends MinBaseChar{
    
    //images for the hero and minotaur 
    private Image heropic;
    //InputStream heroIS = this.getClass().getClassLoader().getResourceAsStream("src/Minotaur/Resources/hero.jpg");
    ImageIcon heroIcon = new ImageIcon(this.getClass().getClassLoader().getResource("res/hero.jpg"));
    
    private static final int blockSize = Grid.blockSize;
    
    private static final int startCoordinateX =  blockSize * 4;
    private static final int startCoordinateY =  blockSize;
    
    //x and y coordinates of the hero
    private static int herox = startCoordinateX;
    private static int heroy = startCoordinateY;
    
    //change in position of the hero in the x direction
    private int heroChangeInPosX;
     //change in position of the hero in the y direction
    private int heroChangeInPosY;
    //change in heros direction
    private static int heroChangeInDirX;
    private static int heroChangeInDirY;
    //speed of the hero
    private int heroSpeed = 4;
    
    Rectangle hero = new Rectangle(herox, heroy, blockSize, blockSize);
    Rectangle enemy = new Rectangle(0, 0, blockSize, blockSize);

    @Override
    public void move(Graphics2D g2d) {
        moveCheckBorders();
        moveCheckInnerWalls();
  
        g2d.drawImage(heropic, herox, heroy, blockSize, blockSize, null);
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
        if (!checkWalls(herox  + heroChangeInPosX + heroSpeed, heroy)
                && !checkWalls(herox + heroChangeInPosX, heroy)) {
            herox = herox + heroChangeInPosX * heroSpeed;
        }
        if (!checkWalls(herox, heroy + heroChangeInPosY + heroSpeed)
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

    @Override
    public void increaseSpeed() {
       this.heroSpeed++;
    }

    @Override
    public void resetPos() {
        herox = startCoordinateX;
        heroy = startCoordinateY;
    }

    @Override
    public void loadImage(){
        heropic = heroIcon.getImage();
    }
    
}
