package jterminal.gui;

/**
 * STATE: OK
 */

/**
 * Startpoint of jTerminal.
 * Just a basic GUI-Application-Build.
 */



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyListener;
import java.util.ArrayDeque;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import jterminal.gui.area.TerminalTable;
import jterminal.gui.utils.TerminalDesign;
import jterminal.model.History;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TerminalFrame extends JFrame {
	private final static ArrayDeque<String> HISTORY = new ArrayDeque<String>();
	private JPanel contentPane;
	private TerminalTable table;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TerminalFrame frame = new TerminalFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TerminalFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		table = new TerminalTable(HISTORY);
		
		contentPane.add(table, BorderLayout.NORTH);
		contentPane.setBackground(table.getBackground());
		
		KeyListener keyListener = (KeyListener) new TerminalListener(table, HISTORY);
		table.addKeyListener(keyListener);
	}
	
	public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth, double... percentages) {
	    double total = 0;
	    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	        total += percentages[i];
	    }
	 
	    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	        TableColumn column = table.getColumnModel().getColumn(i);
	        column.setPreferredWidth((int)
	                (tablePreferredWidth * (percentages[i] / total)));
	    }
	}

}
