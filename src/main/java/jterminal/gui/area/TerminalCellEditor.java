package jterminal.gui.area;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 * STATE: Implementation
 * ISSUE: Currently marking/copying text of rows above the editingRow is not possible. --> TerminalCellEditor-class
 */

/**
 * TerminalCellEditor-class is used for focus- and selection-handling in Terminal
 */



public class TerminalCellEditor extends AbstractCellEditor implements TableCellEditor {

    private JTextField field;
    private TerminalCaret caret = new TerminalCaret();
    
    public TerminalCellEditor(){
        field = new JTextField();
        field.setCaret(caret);
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        if (e instanceof MouseEvent) { 
           return ((MouseEvent)e).getClickCount() >= 1;   //1x klick
        }
        return true;
    }

    public Object getCellEditorValue() {
        return field.getText();
    }

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		field.setText((String)value);
        return field;
	}
    

}
