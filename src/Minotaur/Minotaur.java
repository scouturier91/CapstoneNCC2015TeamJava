/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minotaur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JFrame;
/**
 *
 * @author mrswe_000
 */
public class Minotaur
{   
    public static void main(String[] args) 
    {
	Frame game  = new Frame("Minotaur");
        
        Model world = new Model();
        Grid screen = new Grid(world);
        
        game.add(screen);
        game.pack();
        game.setVisible(true);

    }    
    
}