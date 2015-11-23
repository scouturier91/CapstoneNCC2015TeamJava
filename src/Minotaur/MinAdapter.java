/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minotaur;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author mrswe_000
 */
class MinAdapter extends KeyAdapter{
    public char keyDir; 
    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT)
        {
            keyDir = 'l';
        }
        else if (key == KeyEvent.VK_RIGHT)
        {
            keyDir = 'r';
        }
        else if (key == KeyEvent.VK_UP)
        {
            keyDir = 'u';
        }
        else if (key == KeyEvent.VK_DOWN)
        {
            keyDir = 'd';
        }
    }
    
    public char getKeyDir()
    {
        return keyDir;
    }
}
