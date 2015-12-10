/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minotaur.JavaFiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author mrswe_000
 */
public class Minotaur extends JFrame
{   
    JFrame jf = new JFrame("Minotaur");
    scorePanel scorePanel = new scorePanel();
    public Minotaur()
    {   
        jf.add(new Grid());
        jf.setSize(Grid.screenSize + 10, Grid.screenSize + 75);
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        jf.add(scorePanel, BorderLayout.SOUTH);
        
        jf.setVisible(true);
        
        
    }
    public static void main(String[] args) 
    {
        HighScore score = new HighScore();
        //score.showScore();
        
        for (int i = 0; i < 1005; i++){
            score.currentScore = score.currentScore+10;
        }
        
        EventQueue.invokeLater(() -> {
            Minotaur min = new Minotaur();          
        });
    }      

    private static class scorePanel extends JPanel {

        public scorePanel() {
        this.setPreferredSize(new Dimension(50,50));
        this.setBackground(Color.red);
        this.add(new JLabel("Score: " + HighScore.currentScore));
        }
    }
}
