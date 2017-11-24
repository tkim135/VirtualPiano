/*
 * File: GraphicsContest.java
 * --------------------------
 * Credits to University of Iowa Electronic Music Studios for the instrument 
 * sound files.
 */

import acm.program.*;
import acm.util.*;
import acm.graphics.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphicsContest extends GraphicsProgram implements ComponentListener, PianoConstants {

	public void init() {
		/*display = new PianoDisplay();
		add(display);*/
		
		addComponentListener(this);
		
		addMouseListeners();
		
		inPedal = false;
		pedal = new JButton("Pedal");
		
		addActionListeners();
	}
	
	public void mouseClicked(MouseEvent e) {
		createBottomHalfSounds(e.getX(), e.getY());
		createTopHalfSounds(e.getX(), e.getY());
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	private void createBottomHalfSounds(double x, double y) {
		// iterates for first five white keys from the left
		for (int i = 0; i < 5; i++) {
			generalBottomSounds(x, y, i, "Marimba.yarn.ff." + (char)('C' + i) + "4.stereo.aif");
		}
		for (int i = 5; i < NUM_WHITE - 1; i++) {
			generalBottomSounds(x, y, i, "Marimba.yarn.ff." + (char)('A' + i - 5) + "4.stereo.aif");
		}
		generalBottomSounds(x, y, NUM_WHITE - 1, "Marimba.yarn.ff.C5.stereo.aif");
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
	
	private void createTopHalfSounds(double x, double y) {
		generalTopSounds(x, y, 0, 2, "Marimba.yarn.ff.C4.stereo.aif");
		generalTopSounds(x, y, 2, 4, "Marimba.yarn.ff.Db4.stereo.aif");
		generalTopSounds(x, y, 4, 5, "Marimba.yarn.ff.D4.stereo.aif");
		generalTopSounds(x, y, 5, 7, "Marimba.yarn.ff.Eb4.stereo.aif");
		generalTopSounds(x, y, 7, 9, "Marimba.yarn.ff.E4.stereo.aif");
		generalTopSounds(x, y, 9, 11, "Marimba.yarn.ff.F4.stereo.aif");
		generalTopSounds(x, y, 11, 13, "Marimba.yarn.ff.Gb4.stereo.aif");
		generalTopSounds(x, y, 13, 14, "Marimba.yarn.ff.G4.stereo.aif");
		generalTopSounds(x, y, 14, 16, "Marimba.yarn.ff.Ab4.stereo.aif");
		generalTopSounds(x, y, 16, 17, "Marimba.yarn.ff.A4.stereo.aif");
		generalTopSounds(x, y, 17, 19, "Marimba.yarn.ff.Bb4.stereo.aif");
		generalTopSounds(x, y, 19, 21, "Marimba.yarn.ff.B4.stereo.aif");
		generalTopSounds(x, y, 21, 24, "Marimba.yarn.ff.C5.stereo.aif");
	}
	
	private void generalTopSounds(double x, double y, double xLeftNum, double xRightNum, String filename) {
		if (x > xLeftNum * getWidth() / (3 * NUM_WHITE) && x < xRightNum * getWidth() / (3 * NUM_WHITE) && y > 0 && y < getHeight() / 2) {
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
	
	/* add record feature (slow, fast), keyboard changes color, gliss? (connect button to key 
	 * with line, and go from there), samples, auto-playing code */

	//private PianoDisplay display;
	
	private JButton pedal;
	private boolean inPedal;
	
}
