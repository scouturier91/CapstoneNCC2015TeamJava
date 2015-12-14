//Capstone Project 2015 
//Authors: Stephen Couturier, Jeremy Peck, Mike Guilmette
//Version: 1.0
//Class for handling and diplaying the high score

package Minotaur.JavaFiles;

import java.awt.Graphics2D;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HighScore {
    LinkedList<String> scores = new LinkedList<String>();
    private int currentScore;
    
    // Constructor creates a File f then checks to see if it exists
    // Also initializes the currentScore variable to 0
    public HighScore (){
        File f = new File("scores.txt");
        try {
            this.checkFile(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Minotaur.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentScore = 0;
    }

    //Checks to see if scores.txt exists, if not createFile is called	
    private static void checkFile(File f) throws FileNotFoundException{
        if (!f.exists()){
            createFile(f);
	}
	else {
            System.out.println("File Exists...");
	}
    }

    //Creates a blank (ten 0s) scores.txt
    private static void createFile(File f) throws FileNotFoundException{
        try{
	PrintWriter outFile = new PrintWriter(f);
	for (int i = 0; i < 10; i++){
            outFile.println("0");
	}
	outFile.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    // Create the Linked List using the contents of C:\scores.txt	
    private static void file2list(LinkedList<String> scores) throws IOException{
		
        //Initialize fileRead
        BufferedReader fileRead = null;
			
        try {
            fileRead = new BufferedReader(new FileReader("scores.txt"));
            System.out.println("Read Successful");
        } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
		
        for (String line = fileRead.readLine(); line != null; line = fileRead.readLine()) {
            scores.add(line);       
        }
    } 
    
    //shows the high scores when death is reached
    public void showHighScores(Graphics2D g2d) {
        BufferedWriter out = null;
        try {
            //Initialize fileRead
            BufferedReader fileRead = null;
            try {
                fileRead = new BufferedReader(new FileReader("scores.txt"));
                System.out.println("Read Successful");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // ADD THE CONTENTS OF THE TEXT FILE TO THE LINKEDLIST
            try {
                for (String line = fileRead.readLine(); line != null; line = fileRead.readLine()) {
                    scores.add(line);
                }
            } catch (IOException ex) {
                Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
            }
            scores.add(Integer.toString(currentScore));
            // SORTS THE LIST THEN DISPLAYS IT
            Collections.sort(scores, new AlphanumComparator());
            Collections.reverse(scores);
            g2d.drawString("TOP 10 SCORES", Grid.getBLOCK_SIZE() * 4, Grid.getBLOCK_SIZE() + 80);
            int score_pos = Grid.getBLOCK_SIZE() + 100;
            for (int i = 0; i < 10; i++) {
                g2d.drawString(i + 1 + ": " + scores.get(i), Grid.getBLOCK_SIZE() * 4, score_pos);
                score_pos = score_pos + 20;
            }
            out = new BufferedWriter(new FileWriter("scores.txt"));
            for (int i = 0; i < 10; i++) {
                out.write(scores.get(i) + '\n');
            }
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void clearHighScores(File f){
        f.delete();
    }

    public void addToCurrentScore(int score){
        currentScore += score;
    }
    
    public int getCurrentScore(){
        return currentScore;
    }
}
