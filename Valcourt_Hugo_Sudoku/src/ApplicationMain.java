import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTextArea;

public class ApplicationMain extends JFrame {

	public static JPanel contentPane;																		
	
	public static JTextArea textArea;																		//Declare a private JTextArea
	
	private static Puzzle puzzle;																			//Declare a private Puzzle
	private static JComboBox comboBox;																		//Declare a private JComboBox
	private static byte inputCounter = 0;																	//Declare and initiate a private byte
	private static boolean checked = false;																	//Declare and initiate a private boolean
	private static String[] dropMenu = {"1", "2", "3"}; 													//Declare and initiate a private String array for the comboBox
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationMain frame = new ApplicationMain();
					frame.setVisible(true);
					frame.setResizable(false);																//Set the frame not resizable
					frame.setLocationRelativeTo(null);														//Set the frame in the center of the window
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	

	}

	/**
	 * Create the frame.
	 */
	public ApplicationMain() {

		setTitle("Sudoku Validator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 404, 655);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 181));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		//This button validate if the current sudoku is valid
		JButton btnValidate = new JButton("Validate");
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				validateButton();																			//Calls the button's function
			}
		});		
		contentPane.setLayout(null);
		btnValidate.setBounds(29, 399, 89, 23);
		contentPane.add(btnValidate);
			
		comboBox = new JComboBox(dropMenu);																	//Sets the comboBox to the string array
		comboBox.setBounds(322, 400, 48, 20);
		contentPane.add(comboBox);

		//This button loads a selected sudoku from the txt file
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				LoadAndSave.sudokuNum = Byte.parseByte((String) comboBox.getSelectedItem());				//Sets which sudoku to load 
			
				LoadAndSave.Load();																			//Calls the method to load it
			}
		});
		btnLoad.setBounds(128, 399, 89, 23);
		contentPane.add(btnLoad);
		
		//This button will close the application
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int option = JOptionPane.showConfirmDialog(null, "Game in progress will be lost."
						+ "\nAre you sure?");																//Ask the player if he really wants to quit
				
				if(option == JOptionPane.YES_OPTION)
					System.exit(0);																			//If yes, close the application
				
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnExit.setBounds(227, 431, 89, 23);
		contentPane.add(btnExit);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ApplicationMain.class.getResource("/images/grid2.png")));
		label.setBounds(11, 11, 370, 370);
		contentPane.add(label);
		
		JLabel lblLoadSudoku = new JLabel("Load sudoku #");
		lblLoadSudoku.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoadSudoku.setBounds(227, 400, 93, 17);
		contentPane.add(lblLoadSudoku);
		
		//This button handles the saving
		JButton btnTest = new JButton("Save");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				saveButton();																				//Calls the button's function
			}
		});
		btnTest.setBounds(128, 431, 89, 23);
		contentPane.add(btnTest);
		
		//This button will reset the whole sudoku to null
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				resetButton();																				//Calls the button's function
			}
		});
		btnReset.setBounds(29, 429, 89, 23);
		contentPane.add(btnReset);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 465, 368, 140);
		contentPane.add(textArea);
		textArea.scrollRectToVisible(getBounds());
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JScrollPane scrollPane = new JScrollPane(textArea);													//Hooks the JScrollPanel to the JTextArea
		scrollPane.setBounds(10, 465, 368, 140);
		contentPane.add(scrollPane);

		
		puzzle = new Puzzle();																				//Creates the new Puzzle
		
	}	

	private static void resetButton(){
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	private static void resetButton()
		//
		// Method return		:	void
		//
		// Synopsis				:   This method handles reseting of the sudoku
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2017-09-20		H. Valcourt			initial setup
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		textArea.setText(null);																				//Resets the textArea to null
		
		for(int i = 0; i < 81; i++)																			//Will loop over the entire sudoku
			Puzzle.cellArray[i].SetNumToField("");															//and set it to null
	}
	
	private static void saveButton(){
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	private static void saveButton()
		//
		// Method return		:	void
		//
		// Synopsis				:   This method handles the saving
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2017-09-20		H. Valcourt			initial setup
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		if(checked){																						//If the sudoku has been checked
																											//(which means it's complete, maybe not valid but complete)
			LoadAndSave.Save();																				//Calls the save function

			checked = false;																				//Resets the boolean back to false;
			
			textArea.setText(null);																			//Resets the textArea to null

			textArea.setText("Successfully saved the sudoku!");												//Display a confirmation message
			
		}
		else{
			textArea.setText(null);																			//Resets the textArea to null

			textArea.setText("Need validation or sudoku is incomplete");									//Display an error message
		}
	}
	
	private static void validateButton(){
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	private static void validateButton()
		//
		// Method return		:	void
		//
		// Synopsis				:   This method handles the validation
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2017-09-20		H. Valcourt			initial setup
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		textArea.setText(null);																				//Resets the textArea to null
		
		if(!invalidInput()){																				//If all of the input in the sudoku are valid (1-9)
			Get.GetArray((byte)1, Get.row, "row", false, false);											//Then check rows
			Get.GetArray((byte)9, Get.col, "column", false, true);											//columns
			Get.GetArray((byte)1, Get.box, "box", true, false);												//and boxes
			
			checked = true;																					//Set a flag to tell that is as done checking (sudoku valid or not)
			
			if(Checker.error == 0){																			//If the sudoku has no error in it
				textArea.setText(null);																		//Resets the textArea to null

				textArea.setText("This sudoku is VALID!");													//Print a validation message
			}
			Checker.error = 0;																				//Reset the error count to 0 for next validation
		}
	}
	
	private static boolean invalidInput(){
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	private static boolean invalidInput()
		//
		// Method return		:	boolean
		//
		// Synopsis				:   This method checks if the inputs are all 1-9 only
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2017-09-18		H. Valcourt			initial setup
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		inputCounter = 0;																					//Reset the counter to 0
		
		while(inputCounter < Puzzle.cellArray.length){														//While it as not done checking the entire sudoku

			if(!Puzzle.cellArray[inputCounter].textField.getText().equals("")			
					&& !Puzzle.cellArray[inputCounter].textField.getText().equals("0")){					//Check each cell's text if not null or 0
				
			Puzzle.cellArray[inputCounter].SetNumToField(String.valueOf(Puzzle.cellArray[inputCounter].GetNumFromField()));//Gets the number from the cell
			
				if(Puzzle.cellArray[inputCounter].num < 1 ||
						Puzzle.cellArray[inputCounter].num > 9){											//If the number entered is not from 1 to 9 only
					textArea.setText(null);																		//Resets the textArea to null

					textArea.setText("Missing or invalid input!"											//Print an error message
							+ "\n(Only 1 to 9)");															//Display an error message
					return true;																			//Loop out
				}
				inputCounter++;																				//Increase the counter				
			}
			else{
				textArea.setText(null);																		//Resets the textArea to null

				textArea.setText("Missing or invalid input!"												//Print an error message
						+ "\n(Only 1 to 9)");																//Display an error message				
				return true;																				//Loop out
			}
		}
		
		return false;																						//Return false (everything is valid)		
	}
}
