package chess;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TimeSlider extends JSlider {
    public static int timeRemaining = 60;
    
    TimeSlider(){
        // Time Slider Details
		this.setMinimum(1);
		this.setMaximum(15);
		this.setValue(1);
		this.setMajorTickSpacing(2);
		this.setPaintLabels(true);
		this.setPaintTicks(true);

    }
}
