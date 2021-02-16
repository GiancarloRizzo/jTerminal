package jterminal.gui.area;

/**
 * STATE: OK
 */

import java.awt.Component;
import java.awt.event.KeyEvent;

public class TerminalKeyEvent extends KeyEvent{

	public TerminalKeyEvent(Component source, int id, long when, int modifiers, int keyCode, char keyChar, int keyLocation) {
		super(source, id, when, modifiers, keyCode, keyChar, keyLocation);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isControlSpace() { // search options/flags
		if(getKeyCode() == VK_CONTROL && getKeyCode() == VK_SPACE) {
			return true;
		}
		return false;
	}
}
