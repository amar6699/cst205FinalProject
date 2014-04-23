package cst205FinalProject;
import processing.core.*;
import ddf.minim.*;
import ddf.minim.signals.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;

public class MinimTest extends PApplet {    //PApplet has all the components to work with Minim
	  Minim minim;      //minim and AudioPlayer objects must be created
	  AudioPlayer player;
	  AudioInput input;
	  public void setup() {
		size(800, 600);
	    minim = new Minim(this);
	    
	    
	  }
	}
