/*
 * File: VirtualPiano.java
 * -----------------------
 * Main file for a virtual piano keyboard application. Functions include resizing 
 * of window whenever prompted by the user, playing individual notes, recording a 
 * melody, playing the recorded melody, manipulating the playing speed of the 
 * melody, and clearing the memory for the previous recording. Credits to the 
 * University of Iowa Electronic Music Studios online archive for the instrument 
 * sound files.
 */

import acm.program.*;
import acm.util.*;
import acm.graphics.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class VirtualPiano extends GraphicsProgram implements ComponentListener, PianoConstants {

	/* Method: init() */
	/** Sets up the program's interactors. */
	public void init() {
		addComponentListener(this);

		addMouseListeners();

		recording = false;
		record = new JButton("Record");
		add(record, NORTH);

		initPlaySpeedButtons();

		play = new JButton("Play");
		add(play, NORTH);

		clear = new JButton("Clear Memory");
		add(clear, NORTH);

		addActionListeners();
	}

	/* Method: mouseClicked(MouseEvent e) */
	/** Specifies what happens when mouse is clicked, depending on whether the 
	 * mouse is clicked on the bottom half or top half of the graphics window 
	 * (the keyboard) and whether the "Record" button is clicked. */
	public void mouseClicked(MouseEvent e) {
		createBottomHalfSounds(e.getX(), e.getY());
		createTopHalfSounds(e.getX(), e.getY());
		if (recording) {
			noteList.add(new GPoint(e.getX(), e.getY()));
		}
	}

	/* Method: initPlaySpeedButtons() */
	/** Sets up the radio buttons for controlling play speed. */
	public void initPlaySpeedButtons() {
		ButtonGroup speedBG = new ButtonGroup();
		slowRB = new JRadioButton("Slow");
		medRB = new JRadioButton("Medium");
		fastRB = new JRadioButton("Fast");
		speedBG.add(slowRB);
		speedBG.add(medRB);
		speedBG.add(fastRB);
		medRB.setSelected(true);
		add(slowRB, NORTH);
		add(medRB, NORTH);
		add(fastRB, NORTH);
	}

	/* Method: actionPerformed(ActionEvent e) */
	/** Specifies what happens when any of the buttons are pressed. */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == record) {
			// recording boolean behaves like a switch when button is pressed
			recording = !recording;
		} else if (e.getSource() == play) {
			// sets recording mode off when play is pressed
			recording = false;
			for (int i = 0; i < noteList.size(); i++) {
				createBottomHalfSounds(noteList.get(i).getX(), noteList.get(i).getY());
				createTopHalfSounds(noteList.get(i).getX(), noteList.get(i).getY());
				playAccordingToSpeed();
			}
		} else if (e.getSource() == clear) {
			// sets recording mode off when play is pressed
			recording = false;
			noteList.clear();
		}
	}

	/* Method: createBottomHalfSounds(double x, double y) */
	/** When user clicks mouse in bottom half of the window, differentiates what 
	 * note is played. */
	private void createBottomHalfSounds(double x, double y) {
		// iterates for first five white keys from the left
		for (int i = 0; i < 5; i++) {
			generalBottomSounds(x, y, i, "Marimba.yarn.ff." + (char)('C' + i) + "4.stereo.aif");
		}
		// then for the next two keys
		for (int i = 5; i < NUM_WHITE - 1; i++) {
			generalBottomSounds(x, y, i, "Marimba.yarn.ff." + (char)('A' + i - 5) + "4.stereo.aif");
		}
		// rightmost key
		generalBottomSounds(x, y, NUM_WHITE - 1, "Marimba.yarn.ff.C5.stereo.aif");
	}

	/* Method: generalBottomSounds(double x, double y, double xNum, String filename) */
	/** General form for checking notes in the bottom half. */
	private void generalBottomSounds(double x, double y, double xNum, String filename) {
		if (x > xNum * getWidth() / NUM_WHITE && x < (xNum + 1) * getWidth() / NUM_WHITE && y > getHeight() / 2 && y < getHeight()) {
			playNote(filename);
		}
	}
	
	/* Method: playNote(String filename) */
	/** Plays a note given the filename. */
	private void playNote(String filename) {
		AudioClip note = MediaTools.loadAudioClip(filename);
		note.play();
	}

	/* Method: createTopHalfSounds(double x, double y) */
	/** When user clicks mouse in top half of the window, differentiates what 
	 * note is played. */
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

	/* Method: generalTopSounds(double x, double y, double xLeftNum, double xRightNum, String filename) */
	/** General form for checking notes in the top half. */
	private void generalTopSounds(double x, double y, double xLeftNum, double xRightNum, String filename) {
		if (x > xLeftNum * getWidth() / (3 * NUM_WHITE) && x < xRightNum * getWidth() / (3 * NUM_WHITE) && y > 0 && y < getHeight() / 2) {
			playNote(filename);
		}
	}

	/* Method: playAccordingToSpeed() */
	/** A recording is played to the speed that has been selected by the user. */
	private void playAccordingToSpeed() {
		if (slowRB.isSelected()) {
			pause(SLOW_DELAY);
		} else if (medRB.isSelected()) {
			pause(MED_DELAY);
		} else if (fastRB.isSelected()) {
			pause(FAST_DELAY);
		}
	}

	/* Method: update() */
	/** Updates canvas (the keyboard) based on the resizing of the window. */
	public void update() {
		removeAll();
		double distBtwn = getWidth() / NUM_WHITE;
		double widthKey = getWidth() / (WB_RATIO * NUM_WHITE);
		setUpFirstBlackSet(distBtwn, widthKey);
		setUpSecondBlackSet(distBtwn, widthKey);
		drawWhiteKeyLines();
	}

	/* Method: setUpFirstBlackSet(double distBtwn, double widthKey) */
	/** Sets up the black keys between C (the first white key) and E (the third 
	 * white key). */
	private void setUpFirstBlackSet(double distBtwn, double widthKey) {
		for (int i = 0; i < 2; i++) {
			// location of first black key happens to equal widthKey
			double xBlackKey = widthKey + i * distBtwn;
			createKeys(xBlackKey, widthKey);
		}
	}

	/* Method: setUpSecondBlackSet(double distBtwn, double widthKey) */
	/** Sets up the black keys between F (the fourth white key) and B (the seventh 
	 * white key). */
	private void setUpSecondBlackSet(double distBtwn, double widthKey) {
		for (int i = 0; i < 3; i++) {
			double xBlackKey = getWidth() / 2 - getWidth() / (2 * WB_RATIO * NUM_WHITE) + i * distBtwn;
			createKeys(xBlackKey, widthKey);
		}
	}

	/* Method: createKeys(double xBlackKey, double widthKey) */
	/** Creates the actual GRect for a black key given the x-location and the width. */
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

	/** Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }

	/** Private instance variables */
	private JButton record;
	private JButton play;
	private JButton clear;
	private JTextField playSpeed;
	private JRadioButton slowRB;
	private JRadioButton medRB;
	private JRadioButton fastRB;
	private boolean recording;
	private ArrayList<GPoint> noteList = new ArrayList<GPoint>();

}
