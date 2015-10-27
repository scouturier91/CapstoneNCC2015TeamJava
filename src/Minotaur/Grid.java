//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 0.1
//The class for the map that everything will move within

package Minotaur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grid extends JFrame implements ActionListener
{
        //images for the hero and minotaur 
        private Image hero;
        private Image minotaur;
        
	private final int blockSize = 24;
        private final int numOfBlocks = 15;
        private final int screenSize = numOfBlocks * blockSize; 
	private static Container cont;
	private static JFrame jF;
        
        //checks to see whether the hero is still alive
        private boolean alive = true;
        private boolean playing = true;
        
        
        /**
        the data for the board describing where and what each map piece will be the sum of the number will generate the right cell
        * 0 = blank space
        * 1 = left corner
        * 2 = top corner
        * 4 = right corner
        * 8 = bottom corner
        * ex: top left corner = 3 = 1 +2
        * */
        private final int boardData[] =
        {
            //will currently generate a blank map
            3, 2, 2, 2, 2, 2, 2, 6,
            1, 0, 0, 0, 0, 0, 0, 4,
            1, 0, 0, 0, 0, 0, 0, 4,
            1, 0, 0, 0, 0, 0, 0, 4,
            1, 0, 0, 0, 0, 0, 0, 4,
            1, 0, 0, 0, 0, 0, 0, 4,
            1, 0, 0, 0, 0, 0, 0, 4,
            9, 8, 8, 8, 8, 8, 8, 12
        };       
	public static void main(String[] args) 
	{
		Grid mainGrid = new Grid();
                mainGrid.run();
        }
	
        //centers the window on the computer screen
	public static void centerWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
        
        public void run()
        {
            initializeJFrame();
            loadImages();
        }
        
        public void initializeJFrame()
        {
            jF = new JFrame();
            jF.setFocusable(true);
            jF.setResizable(false);
            jF.setTitle("Minotaur 0.1");
            jF.setPreferredSize(new Dimension(500,500));
            jF.setSize(500, 500);
            jF.setBackground(Color.YELLOW);
            centerWindow(jF);
            jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
            cont = jF.getContentPane();
            cont.setBackground(Color.GREEN);

            cont.setVisible(true);
        }
        
        public void paintComponent(Graphics g)
        {
            super.paintComponents(g);
            
        }
        
        public void loadImages()
        {
            //loads in the images from the resources folder
            hero = new ImageIcon("hero.jpg").getImage();
            minotaur = new ImageIcon("Minotaur").getImage();
        
        }
        
        //Draws the maze based of the gameboard array
        public void drawMaze(Graphics2D g2d)
        {
            //will draw the initial maze
            int i = 0;
            int x;
            int y;
            
            for(y = 0;y<screenSize; y+=blockSize)
            {
                for(x = 0; x<screenSize; x+=blockSize)
                {
                    g2d.setStroke(new BasicStroke(2));
                    
                    if((boardData[i] & 1) != 0)
                    {
                        g2d.drawLine(x, y, x, y + blockSize - 1);
                    }
                    
                    if((boardData[i] & 2) != 0)
                    {
                        g2d.drawLine(x, y, x + blockSize - 1, y);
                    }
                    
                    if((boardData[i] & 4) != 0)
                    {
                        g2d.drawLine(x + blockSize - 1, y, x + blockSize - 1 , y + blockSize - 1);
                    }
                    
                    if((boardData[i] & 8) != 0)
                    {
                        g2d.drawLine(x, y + blockSize - 1, x + blockSize - 1, y + blockSize - 1);
                    }
                    i++;
                }
            }
            

        }
        
    public void runGame(Graphics2D g2d)
    {
            if(alive == true)
            {
                moveHero();
                drawHero(g2d);
                moveEnemy(g2d);
                checkMap();
            }    
            else
            {
                death();
            }
    }

    private void death() 
    {
        playing = false;
    }

    private void moveHero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void drawHero(Graphics2D g2d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void moveEnemy(Graphics2D g2d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void drawEnemy(Graphics2D g2d, int x, int y)
    {
        g2d.drawImage(minotaur, x, y, rootPane);
    }

    private void checkMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    

}
     