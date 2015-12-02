/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minotaur;

import java.awt.EventQueue;
import javax.swing.JFrame;
/**
 *
 * @author mrswe_000
 */
public class Minotaur extends JFrame
{   
    public Minotaur()
    {     
        JFrame jf = new JFrame("Minotaur");
        jf.add(new Grid());
        jf.setSize(Grid.screenSize + 20, Grid.screenSize + 40);
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
        jf.setVisible(true);
    }
    public static void main(String[] args) 
    {
        HighScore score = new HighScore();
        score.showScore();
        
        EventQueue.invokeLater(() -> {
            Minotaur min = new Minotaur();
            min.setVisible(true);
        });
    }      
}
