/*
 * File: GraphicsContest.java
 * --------------------------
 */

import acm.program.*;
import acm.util.*;
//import acm.graphics.*;
import java.applet.*;

public class GraphicsContest extends Program implements PianoConstants {

	public void run() {
		display = new PianoDisplay();
		add(display);
		
		AudioClip cFour = MediaTools.loadAudioClip("Marimba.yarn.ff.C4.stereo.aif");
		cFour.play();
	}
	
	/* add record feature, keyboard changes color, gliss? (connect button to key 
	 * with line, and go from there), samples */

}
