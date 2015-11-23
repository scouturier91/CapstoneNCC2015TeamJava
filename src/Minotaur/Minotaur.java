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
public class Minotaur extends JFrame
{   
    private static final int blockSize = 40;
    private static final int numOfBlocks = 9;
    private static final int screenSize = numOfBlocks * blockSize;
    
    public static void main(String[] args) 
    {
        JFrame jF = new JFrame();
        jF.setSize(screenSize, screenSize);
	jF.add(new Grid());
        jF.pack();
        jF.setVisible(true);

    }    
    
}
