package jterminal.gui.area;

/**
 * STATE: Implementation
 * ISSUE: Currently marking/copying text of rows above the editingRow is not possible. --> TerminalCellEditor-class
 * TODO:  If TerminalController-obj gives output it, TerminalTable-obj has to display it
 */

/**
 * The jTerminal-GUI implements a extended JTable (this TerminalTable). Every userinput fired by executing a KeyEvent on Key==ENTER is effecting the following algorithm:
 * 1. call TerminalController to handle userinput
 * 2. push userinput into history
 * 3. add a new empty line (currently its not empty but contains "default")
 * 
 * The TerminalTable contains two columns, but just the second one is editable. 
 * The first one is containing the Prompt.
 */


import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.CellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import jterminal.gui.utils.TerminalDesign;
import jterminal.model.History;

public class TerminalTable extends JTable{
	private ArrayDeque<String> HISTORY;
	
	public final int NOT_USED = -999;
    public final int EDITABLE_COL = 1;
    
	DefaultTableModel model = new DefaultTableModel();
	
	private final String DEFAULT_PROMT = " jTerminal >> ";
	private final int DEFAULT_PROMT_WIDTH = 100;
	
	private final Object[] DEFAULT_ROW = {DEFAULT_PROMT, "default"};
	
	public TerminalTable(ArrayDeque<String> staticHistory) {
		HISTORY = staticHistory;
		
		model.setColumnCount(2);
		setModel(model);
		
		int screenwidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		getColumnModel().getColumn(0).setPreferredWidth(DEFAULT_PROMT_WIDTH);
		getColumnModel().getColumn(0).setMaxWidth(DEFAULT_PROMT_WIDTH);
		getColumnModel().getColumn(0).setMinWidth(DEFAULT_PROMT_WIDTH);
		
        getColumnModel().getColumn(EDITABLE_COL).setPreferredWidth(screenwidth-DEFAULT_PROMT_WIDTH);
        getColumnModel().getColumn(EDITABLE_COL).setMaxWidth(screenwidth-DEFAULT_PROMT_WIDTH);
		getColumnModel().getColumn(EDITABLE_COL).setMinWidth(screenwidth-DEFAULT_PROMT_WIDTH);
		
		setLookAndFeel(TerminalDesign.base01, TerminalDesign.base0a);
		
		model.addRow(DEFAULT_ROW); // firstRow
        
        TerminalCellEditor celleditor = new TerminalCellEditor();
        getColumnModel().getColumn(1).setCellEditor(celleditor);
        
	}
	
	public void setLookAndFeel(Color bg, Color fg) {
		setBackground(bg);
		setForeground(fg);
		setSelectionBackground(getBackground());
		setSelectionForeground(getForeground());
		setShowGrid(false);
		
		setFont(TerminalDesign.FONT);
	}
	
	public void addNextLine() {
//		System.out.println("HISTORYSIZE: "+HISTORY.size());
//		System.out.println("HISTORY: "+HISTORY.get(HISTORY.size()-1));
		
		model.addRow(DEFAULT_ROW);
		changeSelection(NOT_USED, NOT_USED, cellSelectionEnabled, autoCreateColumnsFromModel);
	}
	
	// make read only fields except column 1
	@Override
    public boolean isCellEditable(int row, int column){
        return column == EDITABLE_COL;
    }
	
	
	@Override
	public boolean isCellSelected(int row, int column) {
		return column == EDITABLE_COL;
	}
	
	// sets lookAndFeel when editing a cell
	@Override
	public Component prepareEditor(TableCellEditor editor, int row, int col) {
	    Component c = super.prepareEditor(editor, row, col);
	    c.setBackground(getBackground());
	    c.setForeground(getForeground());
	    ((JComponent) c).setBorder(BorderFactory.createEmptyBorder());
	    
	    return c;
	}
	
	
	// sets lookAndFeel when a cell gets the focus
	@Override
	public Component prepareRenderer(TableCellRenderer editor, int row, int col) {
	    Component c = super.prepareRenderer(editor, row, col);
	    c.setBackground(getBackground());
	    c.setForeground(getForeground());
	    ((JComponent) c).setBorder(BorderFactory.createEmptyBorder());
	    
	    return c;
	}
	

	
    @Override
    public void changeSelection(int row, int col, boolean toggle, boolean expand) {
    	//TODO: currently you cant copy content from last rows. to allow this, i have to 
    	// guarantee that the method changeSelection is just used when i use addNextLine()
    	
    	
    	//dont use params. instead use just the last line
    	setRowSelectionInterval(getRowCount()-1, getRowCount()-1);
    	setColumnSelectionInterval(EDITABLE_COL, EDITABLE_COL);
    	editCellAt(getRowCount()-1, EDITABLE_COL);
    }
}
