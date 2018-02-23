import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

public class Cell {
	
	public JTextField textField;																			//Makes a private JTextField

	public byte num = 0;																					//Makes and initialize a public byte
	
	public Cell(){
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	public Cell()
		//
		// Method return		:	constructor
		//
		// Synopsis				:   This constructor will make a cell
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2017-09-14		H. Valcourt			initial setup
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

		textField = new JTextField();	
		textField.setBounds(20 + Puzzle.x * 40, 20 + Puzzle.y * 40, 30, 30);								//Simple math for its x and y position
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ApplicationMain.contentPane.add(textField);
		
	}
	
	public void SetNumToField(String s_num){														
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	public void SetNumToField(String s_num)
		//
		// Method return		:	void
		//
		// Parameters			: 	String s_num
		//
		// Synopsis				:   This method will set and assign a number to the cell's
		//							JTextField and its num data
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2017-09-14		H. Valcourt			initial setup
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

			textField.setText(s_num);																		//Sets text to the JTextField
			textField.setBackground(Color.white);
			
			if(!s_num.matches(""))
				num = Byte.parseByte(s_num);																//Sets the data with the string			
	}
	
	public byte GetNumFromField(){														
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	public void SetNumToField(String s_num)
		//
		// Method return		:	void
		//
		// Parameters			: 	String s_num
		//
		// Synopsis				:   This method will set and assign a number to the cell's
		//							JTextField and its num data
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2017-09-14		H. Valcourt			initial setup
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

		try{
			
			num = Byte.parseByte(textField.getText());														//Gets the data with the string
			if(num >= 1 && num <= 9)																		//Check if its valid (1 to 9 only)
				return num;																					//If yes, return it
		}
		catch(NumberFormatException e){
			return 0;																						//Else, return 0
		}
		return num;																							//Return the num
		
	}

}
