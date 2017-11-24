/*
 * File: GraphicsContest.java
 * --------------------------
 */

import acm.program.*;
import acm.util.*;
//import acm.graphics.*;
import java.applet.*;

public class GraphicsContest extends Program implements PianoConstants {

	public void init() {
		display = new PianoDisplay();
		add(display);
		
		addMouseListeners();
		
		AudioClip cFour = MediaTools.loadAudioClip("Marimba.yarn.ff.C4.stereo.aif");
		cFour.play();
	}
	
	/* add record feature, keyboard changes color, gliss? (connect button to key 
	 * with line, and go from there), samples */

	private PianoDisplay display;
	
}
