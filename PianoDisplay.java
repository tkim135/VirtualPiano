


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
		double widthKey = getWidth() / (WB_RATIO * NUM_WHITE);
		setUpFirstBlackSet(distBtwn, widthKey);
		setUpSecondBlackSet(distBtwn, widthKey);
		drawWhiteKeyLines();

		/*draw display with lines, GRect for black keys*/
	}

	private void setUpFirstBlackSet(double distBtwn, double widthKey) {
		// set up the black keys between C and D and between D and E
		for (int i = 0; i < 2; i++) {
			// location of first black key happens to equal widthKey
			double xBlackKey = widthKey + i * distBtwn;
			createKeys(xBlackKey, widthKey);
		}
	}
	
	private void setUpSecondBlackSet(double distBtwn, double widthKey) {
		for (int i = 0; i < 3; i++) {
			double xBlackKey = getWidth() / 2 - getWidth() / (3 * NUM_WHITE) + i * distBtwn;
			createKeys(xBlackKey, widthKey);
		}
	}
	
	private void createKeys(double xBlackKey, double widthKey) {
		GRect blackKey = new GRect(xBlackKey, 0, widthKey, getHeight() / 2);
		blackKey.setFilled(true);
		blackKey.setColor(Color.BLACK);
		add(blackKey);
	}
	
	private void drawWhiteKeyLines() {
		for (int i = 1; i <= NUM_WHITE - 1; i++) {
			double xLine = i * getWidth() / NUM_WHITE;
			GLine line = new GLine (xLine, getHeight() / 2, xLine, getHeight());
			add(line);
		}
		// exceptions for 3rd and 7th line; they must go full height
		double xThird = 3 * getWidth() / NUM_WHITE;
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
