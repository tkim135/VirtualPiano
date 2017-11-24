


import acm.program.*;
import acm.graphics.*;
import java.awt.*;
import java.awt.event.*;

public class PianoDisplay extends GCanvas implements ComponentListener {

	public PianoDisplay() {
		addComponentListener(this);
	}
	
	public void update() {
		/*draw display with lines, GRect for black keys*/
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
