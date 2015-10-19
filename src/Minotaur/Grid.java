//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 0.1
//The class for the map that everything will move within

package Minotaur;

import javax.swing.*;
import java.awt.*;
public class Grid extends JFrame{
        
        private Image hero;
        private Image minotaur;
	private static final int GRID_ROWS = 9;
        private static final int GRID_COLUMNS = 9;
	private static Container cont;
	private static JFrame jF;
        
	public static void main(String[] args) 
	{
		Grid mainGrid = new Grid();
                mainGrid.run();
		
	}
	
	
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
            jF.setBackground(Color.BLACK);
            centerWindow(jF);
            jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
            cont = jF.getContentPane();
            cont.setBackground(Color.black);
            cont.setLayout(new GridLayout(GRID_ROWS, GRID_COLUMNS, 0, 0));
            jF.setVisible(true);
            cont.setVisible(true);
        }
        
        public void paintComponent(Graphics g)
        {
            Graphics2D g2d = (Graphics2D) g;
            drawMaze(g2d);
            
        }
        
        public void loadImages()
        {
            //loads in the images from the resources folder
            hero = new ImageIcon("hero.jpg").getImage();
            minotaur = new ImageIcon("Minotaur").getImage();
        
        }
        public void drawMaze(Graphics2D g2d)
        {
            //will draw the initial maze
            g2d.drawImage(hero, 10, 10, this);
            
            
        }
}
      