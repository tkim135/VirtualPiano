/*
 * File: PianoConstants.java
 * -------------------------
 * 
 */

public interface PianoConstants {
	
	/** The width of the application window */
	public static final int APPLICATION_WIDTH = 800;
	
	/** The height of the application window */
	public static final int APPLICATION_HEIGHT = 400;
	
	/** Number of white keys */
	public static final int NUM_WHITE = 8;
	
	/** Width ratio between white and black keys */
	public static final double WB_RATIO = 1.5;
	
	/** Represents spot number of first line going from 0 to getHeight() */
	public static final int FIRST_FULL = 3;
	
	/** Slow play speed delay */
	public static final int SLOW_DELAY = 1000;
	
	/** Medium play speed delay */
	public static final int MED_DELAY = 500;
	
	/** Fast play speed delay */
	public static final int FAST_DELAY = 250;
	
}
