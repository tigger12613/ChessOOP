package chess;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ControlPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public JPanel board = new JPanel(new GridLayout(8, 8));
	public JPanel wdetails = new JPanel(new GridLayout(3, 3));
	public JPanel bdetails = new JPanel(new GridLayout(3, 3));
	public JPanel wcombopanel = new JPanel();
	public JPanel bcombopanel = new JPanel();
	public JPanel WhitePlayer, BlackPlayer, displayTime, showPlayer, time;
	
	public JLabel label, mov;
    public JLabel CHNC;
    
    public Time timer;
    
    public boolean selected = false, end = false;

    public ArrayList<Player> wplayer, bplayer;
	public ArrayList<String> Wnames = new ArrayList<String>();
	public ArrayList<String> Bnames = new ArrayList<String>();
	public JComboBox<String> wcombo, bcombo;
	
	public String move;
	public Player tempPlayer;
	public JScrollPane wscroll, bscroll;
	public String[] WNames = {}, BNames = {};
	public JSlider timeSlider;
	
    public Button startButton, wselect, bselect, WNewPlayer, BNewPlayer;
    
    ControlPanel(){
        timeSlider = new TimeSlider();
		
		move = "White";
		board = new JPanel(new GridLayout(8, 8));
		wdetails = new JPanel(new GridLayout(3, 3));
		bdetails = new JPanel(new GridLayout(3, 3));
		bcombopanel = new JPanel();
		wcombopanel = new JPanel();
		Wnames = new ArrayList<String>();
		Bnames = new ArrayList<String>();
        
        wplayer = Player.fetch_players();
		Iterator<Player> witr = wplayer.iterator();
		while (witr.hasNext())
			Wnames.add(witr.next().name());

		bplayer = Player.fetch_players();
		Iterator<Player> bitr = bplayer.iterator();
		while (bitr.hasNext())
			Bnames.add(bitr.next().name());
		WNames = Wnames.toArray(WNames);
        BNames = Bnames.toArray(BNames);
        
        this.setLayout(new GridLayout(3, 3));
		this.setBorder(BorderFactory.createTitledBorder(null, "Statistics", TitledBorder.TOP,
                TitledBorder.CENTER, new Font("Lucida Calligraphy", Font.PLAIN, 20), Color.ORANGE));
                
        WhitePlayer = new JPanel();
		WhitePlayer.setBorder(BorderFactory.createTitledBorder(null, "White Player", TitledBorder.TOP,
				TitledBorder.CENTER, new Font("times new roman", Font.BOLD, 18), Color.RED));
		WhitePlayer.setLayout(new BorderLayout());

		BlackPlayer = new JPanel();
		BlackPlayer.setBorder(BorderFactory.createTitledBorder(null, "Black Player", TitledBorder.TOP,
				TitledBorder.CENTER, new Font("times new roman", Font.BOLD, 18), Color.BLUE));
        BlackPlayer.setLayout(new BorderLayout());
        
        JPanel whitestats = new JPanel(new GridLayout(3, 3));
		JPanel blackstats = new JPanel(new GridLayout(3, 3));
		wcombo = new JComboBox<String>(WNames);
		bcombo = new JComboBox<String>(BNames);
		wscroll = new JScrollPane(wcombo);
		bscroll = new JScrollPane(bcombo);
		wcombopanel.setLayout(new FlowLayout());
		bcombopanel.setLayout(new FlowLayout());

		wselect = new Button("Select");
		bselect = new Button("Select");
		// wselect.addActionListener(new SelectHandler(0));
		// bselect.addActionListener(new SelectHandler(1));

		WNewPlayer = new Button("New Player");
		BNewPlayer = new Button("New Player");
		// WNewPlayer.addActionListener(new Handler(0));
		// BNewPlayer.addActionListener(new Handler(1));

		wcombopanel.add(wscroll);
		wcombopanel.add(wselect);
		wcombopanel.add(WNewPlayer);
		bcombopanel.add(bscroll);
		bcombopanel.add(bselect);
		bcombopanel.add(BNewPlayer);
		WhitePlayer.add(wcombopanel, BorderLayout.NORTH);
		BlackPlayer.add(bcombopanel, BorderLayout.NORTH);
		whitestats.add(new JLabel("Name   :"));
		whitestats.add(new JLabel("Played :"));
		whitestats.add(new JLabel("Won    :"));
		blackstats.add(new JLabel("Name   :"));
		blackstats.add(new JLabel("Played :"));
		blackstats.add(new JLabel("Won    :"));
		WhitePlayer.add(whitestats, BorderLayout.WEST);
		BlackPlayer.add(blackstats, BorderLayout.WEST);
		this.add(WhitePlayer);
        this.add(BlackPlayer);
        
        // the panel of game setting
		showPlayer = new JPanel(new FlowLayout());
		showPlayer.add(timeSlider);

		JLabel setTimeLabel = new JLabel("Set Timer(in mins):");
		// the start_button
		startButton = new Button("Start");
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		// startButton.addActionListener(new START());
		startButton.setPreferredSize(new Dimension(120, 40));
		setTimeLabel.setFont(new Font("Arial", Font.BOLD, 16));
		label = new JLabel("Time Starts now", JLabel.CENTER);
		label.setFont(new Font("SERIF", Font.BOLD, 30));
		displayTime = new JPanel(new FlowLayout());
		time = new JPanel(new GridLayout(3, 3));
		time.add(setTimeLabel);
		time.add(showPlayer);
		displayTime.add(startButton);
		time.add(displayTime);
		this.add(time);

    }

    
}
