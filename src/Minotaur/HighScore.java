package Minotaur;

import java.util.*;
import java.io.*;

public class HighScore {

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
			PrintWriter outFile = new PrintWriter(f);
			for (int i = 0; i < 10; i++){
				outFile.println("0");
			}
			outFile.close();
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

		
	//Main	
		public static void main(String args[]) throws IOException {
			File f = new File("C:\\scores.txt");
			LinkedList<String> scores = new LinkedList<String>();
			
			checkFile(f);
			file2list(scores);
			Collections.sort(scores, Collections.reverseOrder());
			
			for (int i = 0; i < 10; i++){
				System.out.println(scores.get(i));
		}	
	 }
	}