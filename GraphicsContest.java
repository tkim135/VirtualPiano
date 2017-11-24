/*
 * File: GraphicsContest.java
 * --------------------------
 */

import acm.program.*;
import acm.util.*;
import acm.graphics.*;
import java.applet.*;
import java.awt.event.*;

public class GraphicsContest extends GraphicsProgram implements PianoConstants {

	public void init() {
		display = new PianoDisplay();
		add(display);
		
		getMouseListeners();
		
		AudioClip cFour = MediaTools.loadAudioClip("Marimba.yarn.ff.C4.stereo.aif");
		cFour.play();
	}
	
	public void mouseClicked(MouseEvent e) {
		if (getElementAt(e.getX(), e.getY()) != null) {
			
		}
	}
	
	/* add record feature, keyboard changes color, gliss? (connect button to key 
	 * with line, and go from there), samples */

	private PianoDisplay display;
	
}
