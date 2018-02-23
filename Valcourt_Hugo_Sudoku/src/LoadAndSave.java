import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LoadAndSave {

	private static BufferedWriter writer;																	//Declares a private BufferedWriter
	private static BufferedReader reader;																	//Declares a private BufferedReader
	private static byte id;																					//Declares a private private byte	
	private static byte lineCounter;																		//Declares a private private byte													
	private static int i,j;																					//Declares a private int
	private static String tempstore = "";																	//Declares and initiate private String
	
	public static byte sudokuNum = 3;																		//Declares and initiate private byte
	
	public static void Load(){
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	public static  void Load()
		//
		// Method return		:	void
		//
		// Synopsis				:   This method will load sudokus from a file and display them
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2017-09-14		H. Valcourt			initial setup
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		ApplicationMain.textArea.setText(null);																				//Resets the textArea to null

		
		id = 0;																								//Initialize the bytes
		lineCounter = 0;																					//Reset the counter
		try {
			reader = new BufferedReader(new FileReader("src/Data/Sudoku"));									//Hook the BufferedReader to the file
			
			String characters;																				//Create a local String
	
			try {
				
				if(sudokuNum == 1)																			//If we're reading the first sudoku,
					reader.lines();																			//skip 1 line
				
				for(int i = 0; i < (sudokuNum - 1) * 10; i++)												//Will skip the desired amount of line for the correct sudoku
					characters = reader.readLine();
				
				characters = "";																			//Initiate the string
				
				while ((characters = reader.readLine()) != null 
						&& lineCounter <= 9) {																//Will read every line of the file until 
																											//there is nothing left to read		
					
					lineCounter++;
					
					for(int i = 0; i < characters.length(); i++){											//Will loop over every character in the string
	
						Puzzle.cellArray[id].SetNumToField(String.valueOf(characters.charAt(i)));			//Calls a function from Cell and use the char 
																											//(sets a number from the file to the cell's JTextfield)
						
						id++;																				//Add one to go to the next id
						
					}					
				}
				reader.close();																				//Close the reader
			} 
			catch (IOException e) {
				e.printStackTrace();
			}

		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void tempStore(){
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	private static void tempReader()
		//
		// Method return		:	void
		//
		// Synopsis				:   This method will temporarily get everything from the txt file 
		//							and store it in a string
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2017-09-20		H. Valcourt			initial setup
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		try {
			reader = new BufferedReader(new FileReader("src/Data/Sudoku"));									//Hook the BufferedReader to the file
			
			reader.readLine();																				//Skip a line
			
			for(i = 0; i < 20; i++)																			//Store the 20 first line of the file in the string
				tempstore += reader.readLine();																	//(it basicaly gets the first 2 sudoku)
	
			reader.close();																					//Close the BufferedReader
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void Save(){
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	public static void Save()
		//
		// Method return		:	void
		//
		// Synopsis				:   This method will write the current sudoku in the first place in the txt file.
		//							And then, write the write the 2 remaining sudoku under it
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2017-09-20		H. Valcourt			initial setup
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		String temp2 = "";																					//Declare and initiate a local string

		tempStore();																						//Calls a function to temporarily store the txt file
		
		try {
			
			writer = new BufferedWriter(new FileWriter(
					"src/Data/Sudoku"));																	//Hook the writer to the BufferedWriter
				
			writer.newLine();																				//Skip a line
			
			for(i = 0; i < 81; i++){																		//Loop over the whole current sudoku
				
				if(i != 0 && i % 9 == 0)																	//For every 9 digit written, drop a line
					writer.newLine();																		
				
				writer.write(String.valueOf(Puzzle.cellArray[i].GetNumFromField()));						//Write the cell's number
			}		
			writer.newLine();																				//Skip a line
			
			for(i = 0; i < 18; i++){																		//Loop over the 2 remaining sudoku (temporarily stored)
				temp2 = "";																					//Resets the string
				writer.newLine();																			//Skip a line

				for(j = i*9; j < (i*9) + 9; j++)
					temp2 += tempstore.charAt(j);																//Get line by line (9 numbers per line)
				
				if(i > 0 && i % 9 == 0)																		//For every 9 digit written, drop a line
					writer.newLine();
				
				writer.write(temp2);																		//Write the line			
			}
			writer.close();																					//Close the BufferedWriter
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
