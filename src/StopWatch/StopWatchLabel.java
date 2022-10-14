package StopWatch;

import java.awt.event.*;
import java.time.*;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.*;
import java.text.SimpleDateFormat;

/**
 * A custom component that acts as a simple stop-watch. When the user clicks
 * on it, this componet starts timing. When the user clicks again,
 * it displays the time between the two clicks. Clicking a third time
 * starts another timer, etc. While it is timing, the label just
 * displays the message "Timing....".
 */
public class StopWatchLabel extends JLabel implements MouseListener {

    private long startTime = -1; // Start time of timer.
                            // (Time is measured in milliseconds.)

    private boolean running = false; // True when the timer is running.

    private Timer timer;

    /**
     * Constructor sets initial text on the label to
     * "Click to start timer." and sets up a mouse listener
     * so the label can respond to clicks.
     */
    public StopWatchLabel() {
        super("  Click to start timer.  ", JLabel.CENTER);
        addMouseListener(this);
    }

    /**
     * Tells whether the timer is currently running.
     */
//    public boolean isRunning() {
//        return running;
//    }

    /**
     * React when the user presses the mouse by starting
     * or stopping the timer and changing the text that
     * is shown on the label.
     */
    //7boolean running = false;
    Timer t = new Timer(200, new ActionListener() {
        private double seconds = -1;

        //I moved here the view update
        @Override
        public void actionPerformed(ActionEvent e) {
            /*I am using the getWhen() of e to initiate the chronometration.
             * The program understand that is a new start because the global var startTime is -1*/
            if (startTime == -1)
                startTime = e.getWhen();
            /*I also use the e.getWhen to obtain the current time*/
            long endTime = e.getWhen();
            seconds = (endTime - startTime) / 1000.0;
            setText("Running: " + seconds + " sec.");
        }
    });
 
    
    public void mousePressed(MouseEvent evt) { 
        
        if (!t.isRunning()) {
          //This is to reset the time
            startTime = -1;
            t.start();
        } else {
           t.stop();
        }

    }

    public void mouseReleased(MouseEvent evt) {
    }

    public void mouseClicked(MouseEvent evt) {
    }

    public void mouseEntered(MouseEvent evt) {
    }

    public void mouseExited(MouseEvent evt) {
    }

}
