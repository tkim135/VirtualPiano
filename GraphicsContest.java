/*
 * File: GraphicsContest.java
 * --------------------------
 */

import acm.program.*;
import acm.util.*;
import javafx.scene.media.AudioClip;
import acm.graphics.*;

public class GraphicsContest extends GraphicsProgram {

	public void run() {
		AudioClip cFour = MediaTools.loadAudioClip("Marimba.yarn.ff.C4.stereo.aif");
		cFour.play();
	}

}
