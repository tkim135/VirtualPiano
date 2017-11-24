/*
 * File: GraphicsContest.java
 * --------------------------
 */

import acm.program.*;
import acm.util.*;
import acm.graphics.*;
import java.applet.*;
import java.awt.Color;
import java.awt.event.*;

public class GraphicsContest extends GraphicsProgram implements ComponentListener, PianoConstants {

	public void init() {
		/*display = new PianoDisplay();
		add(display);*/
		
		addComponentListener(this);
		
		addMouseListeners();
		
		
	}
	
	public void mouseClicked(MouseEvent e) {
		createBottomHalfSounds(e.getX(), e.getY());
	}
	
	private void createBottomHalfSounds(double x, double y) {
		// iterates for first five keys from the left
		for (int i = 0; i < 5; i++) {
			generalBottomSounds(x, y, i, "Marimba.yarn.ff." + (char)('C' + i) + "4.stereo.aif");
		}
		/*if (x > 0 && x < getWidth() / NUM_WHITE && y > getHeight() / 2 && y < getHeight()) {
			AudioClip cFour = MediaTools.loadAudioClip("Marimba.yarn.ff.C4.stereo.aif");
			cFour.play();
		}*/
	}
	
	private void generalBottomSounds(double x, double y, double xNum, String filename) {
		if (x > xNum * getWidth() / NUM_WHITE && x < (xNum + 1) * getWidth() / NUM_WHITE && y > getHeight() / 2 && y < getHeight()) {
			AudioClip note = MediaTools.loadAudioClip(filename);
			note.play();
		}
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
			double xBlackKey = getWidth() / 2 - getWidth() / (2 * WB_RATIO * NUM_WHITE) + i * distBtwn;
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
		double xThird = FIRST_FULL * getWidth() / NUM_WHITE;
		GLine thirdLine = new GLine(xThird, 0, xThird, getHeight() / 2);
		add(thirdLine);
		double xSeventh = (NUM_WHITE - 1) * getWidth() / NUM_WHITE;
		GLine seventhLine = new GLine(xSeventh, 0, xSeventh, getHeight() / 2);
		add(seventhLine);
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	/* add record feature, keyboard changes color, gliss? (connect button to key 
	 * with line, and go from there), samples */

	private PianoDisplay display;
	
}
