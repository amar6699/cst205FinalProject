//import Minim library
  import ddf.minim.*;
  
//for displaying the sound's frequency
  import ddf.minim.analysis.*;

  Minim minim;
  
//to make it play song files
  AudioPlayer song;
  
//for displaying the sound's frequency
  FFT fft;

void setup() {

  //sketch size
    size(800, 600);
 
    minim = new Minim(this);
  
  //load the song you want to play
  //drag the file into your sketch window
  //and replace "mysong.mp3" with the file name
    song = minim.loadFile("blackburner.mp3");
    song.play();
  
  //an FFT needs to know how 
  //long the audio buffers it will be analyzing are
  //and also needs to know 
  //the sample rate of the audio it is analyzing
    fft = new FFT(song.bufferSize(), song.sampleRate());
}
 
void draw() {

  //it's important to put the background in the draw loop
  //to make it animate rather than draw over itself 
    background(0);
 
  //first perform a forward fft on one of song's buffers
  //I'm using the mix buffer
  //but you can use any one you like
    fft.forward(song.mix);
 
  //line characteristics
    strokeWeight(1.3);
    stroke(255);
  
  //processing's transform tool
    pushMatrix();
    translate(200, 0); 
  
  //draw the frequency spectrum as a series of vertical lines
    for(int i = 0; i < 0+fft.specSize(); i++)
    {
      line(i, height*4/5, i, height*4/5 - fft.getBand(i)*4);   //The numbers are multiplied by 4 in order to amplify the lines. Otherwise they would just look like straight lines
    }
  
  //closing the transform tool
    popMatrix();
 
  //changing the line color
    stroke(#FF0000);
  
  //the waveform is drawn by connecting neighbor values with a line. 
  //The values in the buffers are between -1 and 1. 
  //If we don't scale them up our waveform will look like a straight line.
  //Thus each of the values is multiplied by 50 
    for(int i = 200; i < song.left.size() - 1; i++)
    {

      line(i, 250 + song.mix.get(i)*50, i+1, 250 + song.mix.get(i+1)*50);
    }
  
  //blue rectangle on the left
    noStroke();
    fill(209);
    rect(0, 0, 200, height);
  
  //text
    textSize(24);
    fill(255);

    text("amplitude", 0, 250); 
    text("frequency", 0, height*4/5); 

}
 
void stop()
{
  //close the AudioPlayer you got from Minim.loadFile()
    song.close();
  
    minim.stop();
 
  //this calls the stop method that 
  //you are overriding by defining your own
  //it must be called so that your application 
  //can do all the cleanup it would normally do
    super.stop();
}
