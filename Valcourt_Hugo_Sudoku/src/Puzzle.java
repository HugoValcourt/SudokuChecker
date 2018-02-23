
public class Puzzle {
	public Cell cell;																						//Creates a public Cell object
	public static byte x,y = 0;																				//Creates a public x and y coordinate and initialize it to 0
	public static Cell[] cellArray;																			//Creates a public 2d Array of Cell

	public Puzzle(){
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	public Puzzle()
		//
		// Method return		:	constructor
		//
		// Synopsis				:   This constructor will make puzzle template
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2017-09-14		H. Valcourt			initial setup
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		cellArray = new Cell[81];																			//Initialize a 2d array of Cells
		
		for(int i = 0; i < 81; i++){																		//Will loop to create 81 cells
			cell = new Cell();																				//Creates a new Cell
			Puzzle.cellArray[i] = cell;																		//	Add the new created cell to the 2d array
			if(Puzzle.x == 8){																				//	If the grid has reach its max for one row
				Puzzle.x = 0;																				//	Reinitialize the x to 0
				Puzzle.y++;																					//	And add 1 to y to create another row under it
			}
			else
				Puzzle.x++;																					//Else, continue adding to the same row
		}
		
	}
	
}
