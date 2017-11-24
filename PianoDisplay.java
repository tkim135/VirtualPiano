


import acm.program.*;
import acm.graphics.*;
import java.awt.*;
import java.awt.event.*;

public class PianoDisplay extends GCanvas implements ComponentListener, PianoConstants {

	public PianoDisplay() {
		addComponentListener(this);
	}
	
	public void update() {
		removeAll();
		// set up the black keys between C and D and between D and E
		for (int i = 0; i < 2; i++) {
			// location of first black key
			double xBlackKey = 2 * getWidth() / (3 * NUM_WHITE);
			// width of keys happens to equal the value of xBlackKey above
			double widthKey = xBlackKey;
			// add distance between black keys for generalized form
			xBlackKey += i * getWidth() / NUM_WHITE;
			GRect blackKey = new GRect(xBlackKey, 0, widthKey, getHeight() / 2);
			add(blackKey);
		}
		
		/*draw display with lines, GRect for black keys*/
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
