package AudioVisualizer;

/*Name: Juan Amaral
 *Date: April 9, 2014
 *Title: Project UI
 *Abstract: This section will prompt the user to choose a file whith which to work with
 */

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import javax.swing.JFileChooser;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class AudioVisualizer extends JFrame implements ActionListener
{
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;
    private JLabel label1;
    private JLabel output;
    private File audioFile;
    private static AudioFormat inputFile;
    private int totalFramesRead;
    
    public AudioVisualizer( )
    {
        super( );
        setSize(WIDTH, HEIGHT);
        setTitle("File Dialog");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout( ));
        
        label1 = new JLabel("Choose a file ");
        add(label1);
        
        output = new JLabel("total frames:");
        
        JPanel newPanel = new JPanel();
        JPanel mainPanel = new JPanel();
        
        newPanel.setLayout(new FlowLayout());
        mainPanel.setLayout(new BorderLayout());
        
        add(newPanel, BorderLayout.SOUTH);
        
        JButton button1 = new JButton("Open");
        button1.addActionListener(this);
        add(button1);
        
        JButton button3 = new JButton("Exit");
        button3.addActionListener(this);
        add(button3);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String buttonString = e.getActionCommand( );
    
        if (buttonString.equals("Exit"))
        {
             System.exit(0);
        }
        else if (buttonString.equals("Open"))
        {
        	 final JFileChooser fc = new JFileChooser();
        	 int returnVal = fc.showOpenDialog(this);
        	 if (returnVal == JFileChooser.APPROVE_OPTION) {
                 audioFile = fc.getSelectedFile();                     //file is chosen based on the file dialog
                 System.out.println("File has been inputed");          //for testing purposes only
        	 }
        	 
        	 audioReader(audioFile);
        }
    }
    
    public void audioReader(File file)
    {
    	try{
    		  AudioInputStream audioInputStream = 
    		    AudioSystem.getAudioInputStream(file);
    		  int bytesPerFrame = audioInputStream.getFormat().getFrameSize();
    		    if (bytesPerFrame == AudioSystem.NOT_SPECIFIED) {
    		    // some audio formats may have unspecified frame size
    		    // in that case we may read any amount of bytes
    		    bytesPerFrame = 1;
    		}
    		 // Set an arbitrary buffer size of 1024 frames.
    		    int numBytes = 1024 * bytesPerFrame; 
    		    byte[] audioBytes = new byte[numBytes];
    		    try {
    		      int numBytesRead = 0;
    		      int numFramesRead = 0;
    		      // Try to read numBytes bytes from the file.
    		      while ((numBytesRead = 
    		        audioInputStream.read(audioBytes)) != -1) {
    		        // Calculate the number of frames actually read.
    		        numFramesRead = numBytesRead / bytesPerFrame;
    		        totalFramesRead += numFramesRead;
                        
    		        System.out.println("number of bytes:"+ numBytes);
    		        System.out.println("total number of frames read: " + totalFramesRead);
    		      }
    		    } catch (Exception ex) { 
    		      System.out.println("Error 1");   // error handling
    		    }
    		  } catch (Exception e) {
    		     System.out.println("Audio File not supported");    // error handling
    		  }
    	}
    
        public String getAudioFile()
        {
            return audioFile.getPath();
        }
    }

