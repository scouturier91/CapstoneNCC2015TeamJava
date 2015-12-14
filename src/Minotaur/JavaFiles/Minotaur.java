//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 1.0
//Minotaur Class
package Minotaur.JavaFiles;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
/**
 *
 * @author mrswe_000
 */
public class Minotaur extends JFrame {
    public Minotaur() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame jf = new JFrame("Minotaur");
        jf.add(new Grid());
        jf.setLocationRelativeTo(null);
        jf.setSize(Grid.screenSize + 10, Grid.screenSize + 40);
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setVisible(true);

    }

    public static void main(String[] args) {
   
        EventQueue.invokeLater(() -> {
            Minotaur min = new Minotaur();
        });
    }

}
