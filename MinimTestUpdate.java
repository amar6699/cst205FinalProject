/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AudioVisualizer;

/**
 *
 * @author Meza
 */

import processing.core.*;     //These libraries are imported from the processing language. 
import ddf.minim.*;
import ddf.minim.signals.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;


public class MinimTest extends PApplet {    //PApplet has all the components to work with Minim
	  Minim minim;      //minim and AudioPlayer objects must be created
	  AudioPlayer player;
	  AudioInput input;
          AudioVisualizer getAudio = new AudioVisualizer();
          
	  public void setup() {  //this methods sets up the processing method and is necessary for minim to work properly
		size(800, 600);
                minim = new Minim(this);
                //player = minim.loadFile("soundCopy.wav"); loading of the file through Processing
                player = getAudio.getAudioFile();   //need to convert file to AudioPlayer 1
                player.play();
                
                input = minim.getLineIn();
	    
	  }
	  
	  public void draw() {
             //where the drawing will take place
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

