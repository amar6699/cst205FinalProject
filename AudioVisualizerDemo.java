/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AudioVisualizer;

/**
/*Name: Juan Amaral
 *Date: April 28, 2014
 *Title: Audio visualizer demo (main)
 *Abstract: This class uses its main method to run the Audio visualizer 
 *          and the MinimTest which will draw the waves
 */
public class AudioVisualizerDemo
{
    public static void main(String[] args)
    {
        AudioVisualizer gui = new AudioVisualizer();
        gui.setVisible(true);
        
        MinimTest testPlayer = new MinimTest();
        testPlayer.setVisible(true);
        
    }
}


