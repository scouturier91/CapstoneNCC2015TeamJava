/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minotaur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JFrame;
/**
 *
 * @author mrswe_000
 */
public class Minotaur extends JFrame 
{
    
    public Minotaur()
    {
        startUI();
    }
    
    public static void main(String[] args) 
    {
	EventQueue.invokeLater(new Runnable() 
        {

            @Override
            public void run()
            {
                Minotaur min = new Minotaur();
                min.setVisible(true);
            }
        });

    }    

        
    public void startUI()
    {
        add(new Grid());
        setTitle("Minotaur 0.1");
        setSize(500, 500);
        setBackground(Color.YELLOW);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
}
