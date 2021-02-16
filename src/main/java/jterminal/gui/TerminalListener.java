package jterminal.gui;

/**
 * STATE: Implementation
 */



import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import jterminal.gui.area.TerminalKeyEvent;
import jterminal.gui.area.TerminalTable;
import jterminal.model.History;

public class TerminalListener implements TextListener, KeyListener {

	
	private TerminalTable table;
	
	private ArrayDeque<String> HISTORY;
	private ArrayList<String> privateHistoryCopy;
	private int historypointer;
	
	private String userInput = "";
	
	public TerminalListener(TerminalTable table, ArrayDeque<String> staticHistory) {
		this.table = table;
		this.HISTORY = staticHistory;
		this.privateHistoryCopy = new ArrayList<String>();
		this.historypointer = -1;
	}
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == e.VK_ENTER) {
			userInput =table.getValueAt(table.getEditingRow(), table.EDITABLE_COL).toString();
			HISTORY.push(userInput);
			privateHistoryCopy.add(userInput);
			historypointer = 0;
			table.addNextLine();
			System.out.println(userInput);
			
			
		}
		else if (e.getKeyCode() == e.VK_UP) {
			if (historypointer < privateHistoryCopy.size()-1) {
				userInput = getReversHistoryCopy().get(++historypointer);				 
				System.out.println(userInput);	
			}
		}
		else if (e.getKeyCode() == e.VK_DOWN) {
			if (historypointer > 0) {
				userInput = getReversHistoryCopy().get(--historypointer);	
				System.out.println(userInput);
			} else if ( historypointer == 0) {
				userInput = "";	
				System.out.println(userInput);
			}
		}
	}

	public void textValueChanged(TextEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<String> getReversHistoryCopy() {
		ArrayList<String> reversHistory = new ArrayList<String>();
		reversHistory.addAll(privateHistoryCopy);
		Collections.reverse(reversHistory);
		
		return reversHistory;
	}

}
