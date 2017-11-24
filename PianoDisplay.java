


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
		double distBtwn = getWidth() / NUM_WHITE;
		double widthKey = 2 * getWidth() / (3 * NUM_WHITE);
		setUpFirstBlackSet(distBtwn, widthKey);
		setUpSecondBlackSet(distBtwn, widthKey);

		/*draw display with lines, GRect for black keys*/
	}

	private void setUpFirstBlackSet(double distBtwn, double widthKey) {
		// set up the black keys between C and D and between D and E
		for (int i = 0; i < 2; i++) {
			// location of first black key
			double xBlackKey = 2 * getWidth() / (3 * NUM_WHITE);
			// width of keys happens to equal the value of xBlackKey above
			double widthKey = xBlackKey;
			// add distance between black keys for generalized form
			xBlackKey += i * getWidth() / NUM_WHITE;
			createKeys(xBlackKey, widthKey);
		}
	}
	
	private void setUpSecondBlackSet(double distBtwn, double widthKey) {
		for (int i = 0; i < 3; i++) {
			double xBlackKey = getWidth() / 2 - getWidth() / (3 * NUM_WHITE);
			xBlackKey += i * getWidth() / NUM_WHITE;
			double widthKey = 2 * getWidth() 
		}
	}
	
	private void createKeys(double xBlackKey, double widthKey) {
		GRect blackKey = new GRect(xBlackKey, 0, widthKey, getHeight() / 2);
		blackKey.setFilled(true);
		blackKey.setColor(Color.BLACK);
		add(blackKey);
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
