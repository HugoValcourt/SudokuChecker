import java.util.ArrayList;

public class Get {

	public static ArrayList<Byte> tempArray = new ArrayList<Byte>();										//Declare and initiate a public ArrayList of byte
	public static byte row,col,box = 0;																		//Declare and initiate public bytes
	
	private static byte cellIndex = 0;																		//Declare and initiate a private byte
	private static int i, j = 0;																			//Declare and initiate a private int
	
	public static void GetArray(byte AddAmount, byte type, String s_type, 
			boolean isBox, boolean isColumn){
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	public static void GetArray(byte AddAmount, byte type, String s_type, 
		//							boolean isBox, boolean isColumn)
		//
		// Method return		:	void
		//
		// Parameters			: 	byte AddAmount, byte type, String s_type, 
		//							boolean isBox, boolean isColumn
		//
		// Synopsis				:   This method will check every row, column, and box
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2017-09-17		H. Valcourt			initial setup
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		byte skipRows = 0;																					//Declare and initiate the byte
		cellIndex = 0;																						//Resets the cellIndex index
		for(i = 0; i < 9; i++){																				//Will loop for 9 row, column and box
			
																											//This is a column exception
			if(isColumn)																					//Check if it is currently checking for column
				cellIndex = type;																			//Sets the cellIndex index to type (This is for the current column's index)
			
			for(j = 0; j < 9; j++){																			//Will loop over every number in the row, column and box
				
				tempArray.add(Puzzle.cellArray[cellIndex].num);												//Add the row, column or box in an arrayList
				cellIndex += AddAmount;																		//Increases the cellIndex index by the specific amount
																												
																											//This is for box exception
				if(isBox)																					//Check if it is currently checking for box																				
					if(cellIndex % 3 == 0)																	//Check if the current cellIndex index is a multiple of 3
						cellIndex+=6;																		//If yes, go down a row
			}
			
			type++;																							//Increase the type amount (which one has just been done)
				
																											//This is for box exception
			if(isBox && type % 3 == 0){																		//Check if we are currently checking for box and
																											//if the box we just finished checking is a multiple of 3
																											//(which means we need to go down a row of box)
				
				skipRows = (byte) (type * 9);																//If so, do math to keep a skipping row amount
				cellIndex = skipRows;																		//Sets the cellIndex index to the skipping row amount
			}
			
			else if(isBox){																					//This is for box exception
				
				cellIndex = skipRows += (byte)(3);															//Just keep adding 3 for the next box on the right
			}
					
			Checker.Check(s_type, type);																	//Calls the Check function with parameters to tell its type and which we're at
		}
		
	}
		
}
