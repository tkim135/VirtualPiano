/*
 * File: GraphicsContest.java
 * --------------------------
 */

import acm.program.*;
import acm.util.*;
//import acm.graphics.*;
import java.applet.*;
import java.awt.event.*;

public class GraphicsContest extends Program implements PianoConstants {

	public void init() {
		display = new PianoDisplay();
		add(display);
		
		getMouseListeners();
		
		//AudioClip cFour = MediaTools.loadAudioClip("Marimba.yarn.ff.C4.stereo.aif");
		//cFour.play();
	}
	
	public void mouseClicked(MouseEvent e) {
		createBottomHalfSounds(e.getX(), e.getY());
	}
	
	private void createBottomHalfSounds(int x, int y) {
		if (x > 0 && x < getWidth() / NUM_WHITE && y > getHeight() / 2 && y < getHeight()) {
			AudioClip cFour = MediaTools.loadAudioClip("Marimba.yarn.ff.C4.stereo.aif");
			cFour.play();
		}
	}
	
	/* add record feature, keyboard changes color, gliss? (connect button to key 
	 * with line, and go from there), samples */

	private PianoDisplay display;
	
}
