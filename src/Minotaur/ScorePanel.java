/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minotaur;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Michael.Guilmette
 */
public class ScorePanel extends JPanel{
    
    public ScorePanel(){
        this.setPreferredSize(new Dimension(400,75));
      //  this.setLocation(0, 4);
        this.setBackground(Color.red);
    }
}
