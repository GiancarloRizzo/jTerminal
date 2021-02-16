package jterminal.controll;

/**
 * STATE: OK
 */

/**
 * SuperClass of each callable TerminalFunction to implement validation of arguments 
 * @param args
 * @return
 */

public abstract class Function implements TerminalInterface{
	
	private boolean areValidArguments(String... args) {
		for (String argument : args) {
			if (!check(argument)) {
				return false;
			}
		}
		return true;
	}
	
	abstract boolean check(String arg);
}
