package jterminal.controll;

/**
 * STATE: Implementation
 */

/**
 * Checks if UserInputs are valid commands. 
 * If valid --> callCommand
 * else     --> throwException to Terminal-GUI
 */



public class TerminalController {

	public TerminalController() {
	}
	
	public void get(String userInput){
		if (analyze(userInput)) {
			
		} else {
			//no guilty command --> fire exception to output
		}
	}
	
	private boolean analyze(String userInput) {
		//divide string and put in sorted list
		String[] array = userInput.split(" ");
		if (!isTerminalCommand(array[0])) {return false;}
		else
		//firsfor command
		
		for (String string : array) {
			if (!isTerminalCommand(string)) {
				return false;
			}
		}

		// search for next command
		return false;
	}
	
	
	private boolean isTerminalCommand(String string) {
		//TODO
	}
}
