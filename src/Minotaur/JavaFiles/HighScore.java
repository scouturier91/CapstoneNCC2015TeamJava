package Minotaur.JavaFiles;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HighScore {
    LinkedList<String> scores = new LinkedList<String>();
    public static int currentScore;
    
    // Constructor creates a File f then checks to see if it exists
    // Also initializes the currentScore variable to 0
    public HighScore (){
        File f = new File("C:\\scores.txt");
        try {
            this.checkFile(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Minotaur.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentScore = 0;
    }

    //Checks to see if scores.txt exists, if not createFile is called	
    public static void checkFile(File f) throws FileNotFoundException{
        if (!f.exists()){
            createFile(f);
	}
	else {
            System.out.println("File Exists...");
	}
    }

    //Creates a blank (ten 0s) scores.txt
    public static void createFile(File f) throws FileNotFoundException{
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
    public static void file2list(LinkedList<String> scores) throws IOException{
		
        //Initialize fileRead
        BufferedReader fileRead = null;
			
        try {
            fileRead = new BufferedReader(new FileReader("C:\\scores.txt"));
            System.out.println("Read Successful");
        } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
		
        for (String line = fileRead.readLine(); line != null; line = fileRead.readLine()) {
            scores.add(line);
        }
    }
}
