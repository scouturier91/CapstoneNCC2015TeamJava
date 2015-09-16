//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 0.1
//The class for the map that everything will move within

package Minotaur;

import javax.swing.*;
import java.awt.*;
public class Grid {

	
	private static Container cont;
	private static JFrame jF;
	public static void main(String[] args) 
	{
		jF = new JFrame();
		jF.setFocusable(true);
		jF.setResizable(false);
		jF.setTitle("Minotaur 0.1");
		jF.setPreferredSize(new Dimension(500,500));
		jF.setSize(500, 500);
		jF.setBackground(Color.BLACK);
		centerWindow(jF);
		int row = 9;
		int col = 9;
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cont = jF.getContentPane();
		cont.setBackground(Color.black);
		cont.setLayout(new GridLayout(row, col, 0, 0));
		jF.setVisible(true);
		
	}
	
	
	public static void centerWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
}
