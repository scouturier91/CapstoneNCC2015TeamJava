//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 1.0
//Minotaur Class
package Minotaur.JavaFiles;

import java.awt.EventQueue;
import javax.swing.JFrame;
/**
 *
 * @author mrswe_000
 */
public class Minotaur extends JFrame {
    public Minotaur() {
        JFrame jf = new JFrame("Minotaur");
        jf.add(new Grid(jf));      
        jf.setSize(Grid.getScreenSize() + 10, Grid.getScreenSize() + 40);
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);

    }

    public static void main(String[] args) {
   
        EventQueue.invokeLater(() -> {
            Minotaur min = new Minotaur();
        });
    }

}
