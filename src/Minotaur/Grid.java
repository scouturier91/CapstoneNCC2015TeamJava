//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 0.1
//The class for the map that everything will move within
package Minotaur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Grid extends JPanel implements ActionListener, KeyListener {

    private boolean levelTime = true;
    private Timer time;
    //images for the hero and minotaur 
    public Image heropic;
    public ImageIcon hIcon = new ImageIcon("C:\\Users\\mrswe_000\\Desktop\\code\\CapstoneNCC2015TeamJava\\src\\Minotaur");
    
    private Image minotaur;
    private Hero hero;
    private Enemy enemy;

    public static final int blockSize = 50;
    private static final int numOfBlocks = 9;
    public static final int screenSize = numOfBlocks * blockSize;
    private static Dimension dim = new Dimension(screenSize, screenSize);
    
    //checks to see whether the hero is still alive
    private static boolean dying = false;
    private boolean inGame = false;
    
    //the x and y coordinates of the vertical inner walls
    private static int innerVertWallsX[] = new int[50];
    private static int innerVertWallsY[] = new int[50];
    
    //the x and y coordinates of the vertical inner walls
    private static int innerHorWallsX[] = new int[50];
    private static int innerHorWallsY[] = new int[50];
    
    private final int stairsX = blockSize * 4;
    private final int stairsY = blockSize * 4;

    private final short[][] screenData = new short[numOfBlocks][numOfBlocks];
    private final short boardData[]
            = {
                //
                2, 2, 2, 2, 2, 2, 2, 2, 2,
                2, 0, 0, 0, 0, 0, 0, 0, 2,
                2, 0, 0, 0, 0, 0, 0, 0, 2,
                2, 3, 0, 0, 0, 0, 0, 3, 2,
                2, 0, 0, 1, 0, 1, 0, 0, 2,
                2, 0, 0, 1, 0, 1, 0, 0, 2,
                2, 3, 0, 0, 0, 0, 0, 3, 2,
                2, 0, 0, 0, 0, 0, 0, 0, 2,
                2, 2, 2, 2, 2, 2, 2, 2, 2
            };

    //default constuctor called on game startup
    public Grid() {
        hero = new Hero();
        enemy = new Enemy();
        loadImages();
        addKeyListener(this);

        setFocusable(true);
        inGame = true;
        time = new Timer(60, (ActionEvent evt)
                -> {
            if (!time.isRunning()) {
                levelTime = false;
            }
        });
        time.start();
        startLevel();
        setDoubleBuffered(true);

    }

    //loads the array into a bucket
    private void startLevel() {
        int z = 0;
        for (int i = 0; i < numOfBlocks; i++) {
            for (int j = 0; j < numOfBlocks; j++) {
                screenData[i][j] = boardData[z];
                z++;
            }
        }
        getWalls();
    }

    //puts the inner walls in arrays 
    private void getWalls() {
        int k = 0;
        int p = 0;
        for (int i = 0; i < screenData.length; i++) {
            for (int j = 0; j < screenData.length; j++) {
                if (screenData[j][i] == 1) {
                    innerVertWallsX[k] = i * blockSize;
                    innerVertWallsY[k] = j * blockSize;
                    k++;
                } else if (screenData[j][i] == 3) {
                    innerHorWallsX[p] = i * blockSize;
                    innerHorWallsY[p] = j * blockSize;
                    p++;
                }
            }
        }
    }

    //draws the map
    public void drawMap(Graphics2D g) {
        for (int i = 0; i < screenData.length; i++) {
            for (int j = 0; j < screenData.length; j++) {
                g.setColor(Color.green);
                switch (screenData[j][i]) {
                    case 2:
                        g.setColor(Color.red);
                        g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                        break;
                    case 1:
                        g.setColor(Color.green);
                        g.drawLine((i * blockSize) + blockSize / 2, j * blockSize, (i * blockSize) + blockSize / 2, (j * blockSize) + blockSize);
                        break;
                    case 3:
                        g.setColor(Color.green);
                        g.drawLine(i * blockSize, j * blockSize, (i * blockSize) + blockSize, j * blockSize);
                        //g.drawLine(i * blockSize, j * blockSize , i * blockSize, j * blockSize);
                        //g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);  
                        break;
                    default:
                        break;
                }
            }
        }
    }

    //loads in the images from the resources folder
    public void loadImages() {

        //try {
        //FileInputStream input = new FileInputStream("hero.jpg");
        //input.read();
        heropic = hIcon.getImage();
        //hero = ImageIO.read(getClass().getResourceAsStream("C:\\Users\\mrswe_000\\Desktop\\code\\CapstoneNCC2015TeamJava\\src\\Minotaur"));
        // } catch (IOException ex) {
        //  Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
        //}

    }

    public void playGame(Graphics2D g2d) throws InterruptedException {
        if (inGame = true) {
            if (dying) {
                death(g2d);
            } else {
                hero.move(g2d);
                enemy.move(g2d);
                hero.checkAlive(enemy.getEnemyX(), enemy.getEnemyY());
                repaint();
                Thread.sleep(12);
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            drawBoard((Graphics2D) g);
        } catch (InterruptedException ex) {

            ex.printStackTrace();
        }
    }

    public void drawBoard(Graphics2D g2d) throws InterruptedException {
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, dim.width, dim.height);
        drawMap(g2d);
        playGame(g2d);
        if (!levelTime) {
            drawStairs(g2d);
        }
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private void death(Graphics2D g2d) {
        time.stop();
        inGame = false;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, dim.width, dim.height);

    }

    private void drawStairs(Graphics2D g2d) {
        g2d.setColor(Color.blue);
        g2d.drawRect(stairsX, stairsY, blockSize, blockSize);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT:
                hero.setHeroChangeX(-1);
                hero.setHeroChangeY(0);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setHeroChangeX(1);
                hero.setHeroChangeY(0);
                break;
            case KeyEvent.VK_UP:
                hero.setHeroChangeX(0);
                hero.setHeroChangeY(-1);
                break;
            case KeyEvent.VK_DOWN:
                hero.setHeroChangeX(0);
                hero.setHeroChangeY(1);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT
                || key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            hero.setHeroChangeX(0);
            hero.setHeroChangeY(0);
        }
    }

    public static Dimension getDimension() {
        return dim;
    }
    
    public static int[] getVertX(){
        return innerVertWallsX;
    }
    
    public static int[] getVertY(){
        return innerVertWallsY;
    }
    
    public static int[] getHorX(){
        return innerHorWallsX;
    }
    
    public static int[] getHorY(){
        return innerHorWallsY;
    }
    
    public static void setDying(boolean dying){
        Grid.dying = dying;
    }
}
