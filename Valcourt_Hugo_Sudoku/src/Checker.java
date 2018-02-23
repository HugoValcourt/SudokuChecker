import java.awt.Color;

public class Checker {
    
	public static byte error = 0;																			//Declare and initiate a public byte
	private static boolean boxNeeded = false;
	
	public static void Check(String type, byte where){
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	public static void Check(String type, byte where)
		//
		// Method return		:	void
		//
		// Parameters			: 	String type, byte where
		//
		//
		// Synopsis				:   This function checks for duplicates
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2017-09-13		H. Valcourt			initial setup
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		Get.tempArray.sort(null);																			//Sort the ArrayList
		
		for(int i = 0; i < 9; i++){																			//Loop over every element in the ArrayList
			
			if(i > 0 && Get.tempArray.get(i) == Get.tempArray.get(i - 1)){									//Check if their is no duplicates
								
				ApplicationMain.textArea.append("There is multiple " + Get.tempArray.get(i) +
						" in the " + type + " " + where + "\n\n");											//If there is, display what and where
				
				SetVisible(type, (byte) (where - 1), Get.tempArray.get(i));									//Calls the function to set visible what is wrong 
				error++;																					//Increase the error counter by 1
			}
			
		}
		
		Get.tempArray.clear();																				//Clear the ArrayList

	}
	
	private static void SetVisible(String s_type, byte which, byte num){
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	private static void SetVisible(String s_type, byte which, byte num)
		//
		// Method return		:	void
		//
		// Parameters			: 	String s_type, byte which, byte num
		//
		//
		// Synopsis				:   This function set rows and columns' background red to indicate error
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2017-09-14		H. Valcourt			initial setup
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		byte i,temp;																						//Declare locals byte
		
		
		switch(s_type){																						//Switch on the what it is(row or column)
		
		case "box":																							//If the string is box

			if(which % 3 == 0)																				//Check if we need to go back at the very left side
				which = (byte) (which*9);																	//And go down to wich box we're at
			else{
				if(which == 4 ||
						which == 5 ||
						which == 7 ||
						which == 8){																		//Check if it's one of the 4 box in the right corner
					
					if(which == 7 ||
							which == 8){																	//Check if its the last 2 box in the last row of box
						which = (byte) ((which * 3) + 36);													//Get the position of the first cell of the box
					}
					else
						which = (byte) ((which * 3) + 18);													//If not box 7 or 8, get the corresponding first cell of the box 4 and 5
				}
				else
					which = (byte)(which*3);																//And if not one of the 4 right corner box, just switch to the box on the right

			}
			temp = (byte) (which + 21);																		//Sets the last cell to work with
			
			for(i = which; i < temp; i++){																	//Loop over every cell in the row
				if(i != which && i % 3 == 0)
					i += 6;
				Puzzle.cellArray[i].textField.setBackground(Color.red);										//And set its background to red
			}
			
			break;

		}	
	}
}





























/*case "row":																						//If row
			which = (byte)(which*9);																		//Get the first cell index of the row
			temp = (byte) (which + 9);																		//Add 9 to get the end or the row
				
			for(i = which; i < temp; i++)																	//Loop over every cell in the row
				Puzzle.cellArray[i].textField.setBackground(Color.red);										//And set its background to red
			break;
			
		case "column":																						//If column
			for(i = which; i < 81; i += 9)																	//Loop over every cell in the column
				Puzzle.cellArray[i].textField.setBackground(Color.red);										//And sets its background to red
			break;*/