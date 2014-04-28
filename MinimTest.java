package AudioVisualizer;

/*Name: Juan Amaral
 *Date: April 28, 2014
 *Title: MinimTest
 *Abstract: This class gets the file chosen from AudioVisualizer class 
            and will make a skecthboard in which the waves of the file will be drawn. 
 */

import processing.core.*;     //These libraries are imported from the processing language. 
import ddf.minim.spi.*;
import ddf.minim.signals.*;
import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.ugens.*;
import ddf.minim.effects.*;


public class MinimTest extends PApplet {    //PApplet has all the components to work with Minim
	  Minim minim;      //minim and AudioPlayer objects must be created
	  AudioPlayer player;
	  AudioInput input;
          AudioVisualizer getAudio = new AudioVisualizer();
          
	  public void setup() {                           //this methods sets up the processing method and is necessary for minim to work properly
		size(800, 600);                           //The size of the "sketchboard" in which the audio waves will be drawn
                minim = new Minim(this);
                player = minim.loadFile(getAudio.getAudioFile());   //attempt to get the path and use this to load the file
                //player = getAudio.getAudioFile();         //need to convert file to AudioPlayer
                player.play();
                
                input = minim.getLineIn();
	    
	  }
	  
	  public void draw() {                             //where the drawing will take place
             background(0);
             stroke(255);
                                                           // draw the waveforms so we can see what we are monitoring
             for(int i = 0; i < input.bufferSize() - 1; i++)
             {
                    line( i, 50 + input.left.get(i)*50, i+1, 50 + input.left.get(i+1)*50 );
                    //the lines are multiplied by 50 so that they are actually seen. without the amplification it would be very hard for the liens to actually be seen.
             }
 
             String monitoringState = input.isMonitoring() ? "enabled" : "disabled";
                    text( "Input monitoring is currently " + monitoringState + ".", 5, 15 );
             }
          
          public void stop() {
              
              player.close();
              
          }
	}

