//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 1.0
//The class for the grid that displays on the screen

package Minotaur.JavaFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Duration;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grid extends JPanel implements ActionListener, KeyListener {
    //Regex to used to set time then gets passed to Duration 
    private final String timeString = "PT10S";
    //keeps track of the time
    private Duration time = Duration.parse(timeString);
    //keeps track of times main loop has been run
    private int ticks = 0; 
   
    //image for the stairs
    private Image stairspic;
    ImageIcon stairsIcon = new ImageIcon(this.getClass().getClassLoader().getResource("res/stairs.jpg"));
    
    //character objects
    private Hero hero;
    private Enemy enemy;
    
    //used to limit max amount scored per round
    private int levelScore = 0;

    //standardizes values for screen and block sizes and screen dimensions
    public static final int BLOCK_SIZE = 50;
    private static final int numOfBlocks = 9;
    private static final int screenSize = numOfBlocks * BLOCK_SIZE;
    private static Dimension dim = new Dimension(screenSize, screenSize);
    
    //checks to see whether the hero is still alive
    private static boolean dying = false;
    private boolean inGame = false;
    private boolean delay = false;
    
    //array that holds all inner walls
    ArrayList<Wall> walls = new ArrayList<>();

    //Rectangle object used for stairs
    Rectangle stairs = new Rectangle(BLOCK_SIZE * 4, BLOCK_SIZE * 4, BLOCK_SIZE, BLOCK_SIZE);
   
    //High score object used to hold and sort high scores
    HighScore score = new HighScore();
    
    //arrays used to hold map data
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
        for (int i = 0; i < screenData.length; i++) {
            for (int j = 0; j < screenData.length; j++) {
                if (screenData[j][i] == 1) {
                    Wall wall = new Wall(i * BLOCK_SIZE, j * BLOCK_SIZE , false);
                    walls.add(wall);
                    
                } else if (screenData[j][i] == 3) {
                    Wall wall = new Wall(i * BLOCK_SIZE, j * BLOCK_SIZE , true);
                    walls.add(wall);
                }
            }
        }
        
        hero.setWalls(walls);
        enemy.setWalls(walls);
    }

    //draws the map
    private void drawMap(Graphics2D g) {
        for (int i = 0; i < screenData.length; i++) {
            for (int j = 0; j < screenData.length; j++) {
                g.setColor(Color.green);
                switch (screenData[j][i]) {
                    case 2:
                        g.setColor(Color.red);
                        g.fillRect(i * BLOCK_SIZE, j * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                        break;
                    case 1:
                        g.setColor(Color.green);
                        g.drawLine((i * BLOCK_SIZE) + BLOCK_SIZE / 2, j * BLOCK_SIZE, (i * BLOCK_SIZE) + BLOCK_SIZE / 2, (j * BLOCK_SIZE) + BLOCK_SIZE);
                        break;
                    case 3:
                        g.setColor(Color.green);
                        g.drawLine(i * BLOCK_SIZE, j * BLOCK_SIZE, (i * BLOCK_SIZE) + BLOCK_SIZE, j * BLOCK_SIZE);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    //loads in the images from the resources folder
    private void loadImages() {
        hero.loadImage();
        enemy.loadImage();
        stairspic = stairsIcon.getImage();

    }

    //main loop that runs the inner game mechanics
    private void playGame(Graphics2D g2d) throws InterruptedException {
        if (dying) {
            death(g2d);
        } else {          
            if (ticks > 60) {
                time = time.minusSeconds(1);
                ticks = 0;
                if (levelScore < 1200) {
                    score.addToCurrentScore(100);
                    levelScore += 100;
                }
            }
            paintScore(g2d);
            ticks++;
            if (time.getSeconds() <= 0) {
                drawStairs(g2d);
                if (hero.checkWin(stairs)) {
                    goToNextLevel();
                }
            }
            hero.move(g2d);
            enemy.move(g2d);
            hero.checkAlive(enemy.getX(), enemy.getY());

            repaint();
            Thread.sleep(20);
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            if(delay){
                Thread.sleep(3000);
                delay = false;
            }
            if (inGame == true ){
                drawBoard((Graphics2D) g);
            } else {
                showSplash((Graphics2D) g);
                delay = true;
            }
        } catch (InterruptedException ex) {

            ex.printStackTrace();
        }
    }

    //main loop that draws all components
    private void drawBoard(Graphics2D g2d) throws InterruptedException {
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, dim.width, dim.height);
        drawMap(g2d);
        playGame(g2d);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    //method to handle death
    private void death(Graphics2D g2d) {
        inGame = false;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, dim.width, dim.height);
        g2d.setColor(Color.white);
        g2d.drawString("Game Over ", BLOCK_SIZE * 4, BLOCK_SIZE);
        g2d.drawString("Your Score: " + score.getCurrentScore(), BLOCK_SIZE * 4, BLOCK_SIZE + 20);
        score.showHighScores(g2d);
    }
    
    //draws the stairs once the time is up
    private void drawStairs(Graphics2D g2d) {
        g2d.drawImage(stairspic, stairs.x, stairs.y, BLOCK_SIZE, BLOCK_SIZE, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            inGame = true;
            System.out.println("str");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        //System.out.println(e.getKeyCode());
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

    //draws the score on the screen
    private void paintScore(Graphics2D g2d) {
        g2d.setColor(Color.white);
        if (time.getSeconds() >= 0) {
            g2d.drawString("Score: " + score.getCurrentScore() + "pts         Time: " + time.getSeconds() + "s", BLOCK_SIZE, BLOCK_SIZE/2);
        } else {          
            g2d.drawString("Score: " + score.getCurrentScore() + "pts         Time: 0s", BLOCK_SIZE, BLOCK_SIZE/2);
        }
    }

    //shows the splash screen bfore entering the game
    private void showSplash(Graphics2D g2d) throws InterruptedException {
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
        g2d.setColor(Color.red);
        Font fontText = new Font("Helvetica", Font.PLAIN, 15);
        Font fontTitle = new Font("Helvetica", Font.BOLD, 30);
        g2d.setFont(fontTitle);
        g2d.drawString("MINOTAUR", BLOCK_SIZE * 3, BLOCK_SIZE * 2);
        g2d.setFont(fontText);
        g2d.drawString("A Team JAVA Production", BLOCK_SIZE * 3, BLOCK_SIZE * 4);
        repaint();    
        inGame = true;
    }

    //starts the next level once the level has been won
    private void goToNextLevel() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
        }
        time = Duration.parse(timeString);
        levelScore = 0;
        hero.resetPos();
        enemy.resetPos();
        hero.increaseSpeed();
        enemy.increaseSpeed();
        hero.setHeroChangeX(0);
        hero.setHeroChangeY(0);
    }
    
    //getter and setters
    public static Dimension getDimension() {
        return dim;
    }

    public static void setDying(boolean dying) {
        Grid.dying = dying;
    }
    
    public static int getBLOCK_SIZE(){
        return BLOCK_SIZE;
    }
    
    public static int getScreenSize(){
        return screenSize;
    }
}
