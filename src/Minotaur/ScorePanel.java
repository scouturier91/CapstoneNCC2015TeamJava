/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minotaur;

import static Minotaur.HighScore.currentScore;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Michael.Guilmette
 */
public class ScorePanel extends JPanel{
    
    public ScorePanel(){
        this.setPreferredSize(new Dimension(50,50));
        this.setBackground(Color.red);
        this.add(new JLabel("Score: " + currentScore));
    }
}