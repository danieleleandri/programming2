package StopWatch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Tester {

    public static void main(String[] args) {
        StopWatchLabel timerLabel = new StopWatchLabel();
        JFrame a = new JFrame();  
        a.setSize(500,500);
        
        a.add(timerLabel);
        a.show();

    }

    
    }
