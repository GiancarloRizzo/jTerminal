package jterminal.gui.area;

/**
 * STATE: Implementation
 * ISSUE: Caret isnt displayed as it should 
 */

/**
 * TerminalCaret is used for drawing a nicer and terminal-specific caret
 */



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

import jterminal.gui.utils.TerminalDesign;

public class TerminalCaret extends DefaultCaret{
	
	private int caretposition;
	private Rectangle2D rect = null;
	private char dotChar;
	private final char EXAMPLE = 'w';
	
	protected synchronized void damage(Rectangle rect) {
		if (rect == null) {return;}

		x = rect.x;
		y = rect.y;
		width = getComponent().getWidth();
		height = rect.height;
		
		repaint(); // calls getComponent().repaint(x, y, width, height)
	}

	public void paint(Graphics g) {
		JTextComponent comp = getComponent();
		caretposition = getDot();
				
		try {
			rect = comp.modelToView2D(caretposition);
			if (rect == null) {return;}

			dotChar = comp.getText(caretposition, 1).charAt(0);
		} catch (BadLocationException e) {
			return;
		}

		width = g.getFontMetrics(new Font(Font.MONOSPACED, Font.PLAIN, 12)).charWidth(EXAMPLE);
		int height = (int) rect.getHeight();
		int xpos = (int) rect.getX();
		int ypos = (int) rect.getY();

		if ((x != xpos) || (y != ypos)) {
			repaint(); // erase previous location of caret
			x = xpos;  // update dimensions (width gets set later in this method)
			y = ypos;
		} 
		
		g.setColor(TerminalDesign.base0a);

		if (isVisible())
			g.fillRect(xpos, ypos, width, height);
	}
}