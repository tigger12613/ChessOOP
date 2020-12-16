package chess;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//import org.w3c.dom.events.MouseEvent;

import pieces.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author Ashish Kedia and Adarsh Mohata
 *
 */

/**
 * This is the Main Class of our project. All GUI Elements are declared,
 * initialized and used in this class itself. It is inherited from the JFrame
 * Class of Java's Swing Library.
 * 
 */

public class Controller extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;

	// Variable Declaration
	private static final int Height = 700;
	private static final int Width = 1110;
	private ChessGame chessGame;
	//private Cell c, previous;
	// who can go, 0 is white, 1 is black
	private int chance = 0;
	// private Cell boardState[][];
	private ArrayList<Cell> destinationlist = new ArrayList<Cell>();
	private Player White = null, Black = null;

	private JPanel board = new JPanel(new GridLayout(8, 8));

	// private JPanel wdetails = new JPanel(new GridLayout(3, 3));
	// private JPanel bdetails = new JPanel(new GridLayout(3, 3));
	// private JPanel wcombopanel = new JPanel();
	// private JPanel bcombopanel = new JPanel();
	// private JPanel controlPanel, WhitePlayer, BlackPlayer, temp, displayTime, showPlayer, time;
	private JSplitPane split;
	// private JLabel label, mov;
	// private static JLabel CHNC;
	// private Time timer;

	private ControlPanel controlPanel;
	private JPanel temp;
	// private boolean selected = false, end = false;

	private Container content;

	// private ArrayList<Player> wplayer, bplayer;
	// private ArrayList<String> Wnames = new ArrayList<String>();
	// private ArrayList<String> Bnames = new ArrayList<String>();
	// private JComboBox<String> wcombo, bcombo;

	// private String wname = null, bname = null, winner = null;
	// private String move;
	// private Player tempPlayer;
	// private JScrollPane wscroll, bscroll;
	// private String[] WNames = {}, BNames = {};
	// private JSlider timeSlider;
	private BufferedImage image;
	// private Button startButton, wselect, bselect, WNewPlayer, BNewPlayer;
	public static int timeRemaining = 60;

	// new
	// private Cell board_block[][] = new Cell[8][8];
	private BoardView boardView = new BoardView();

	private Cell selectedCell;

	// Constructor
	Controller() {
		// variable initialization
		chessGame = new ChessGame();
		chance = 0;
		controlPanel = new ControlPanel();
		// timeRemaining = 60;
		// timeSlider = new TimeSlider();
		controlPanel.timeSlider.addChangeListener(new TimeChange());
		// move = "White";
		// wname = null;
		// bname = null;
		// winner = null;

		board = new JPanel(new GridLayout(8, 8));

		// wdetails = new JPanel(new GridLayout(3, 3));
		// bdetails = new JPanel(new GridLayout(3, 3));
		// bcombopanel = new JPanel();
		// wcombopanel = new JPanel();
		// Wnames = new ArrayList<String>();
		// Bnames = new ArrayList<String>();

		board.setMinimumSize(new Dimension(800, 700));
		ImageIcon img = new ImageIcon(this.getClass().getResource("icon.png"));
		this.setIconImage(img.getImage());

		// // Time Slider Details
		// timeSlider.setMinimum(1);
		// timeSlider.setMaximum(15);
		// timeSlider.setValue(1);
		// timeSlider.setMajorTickSpacing(2);
		// timeSlider.setPaintLabels(true);
		// timeSlider.setPaintTicks(true);
		// timeSlider.addChangeListener(new TimeChange());

		// Fetching Details of all Players
		// wplayer = Player.fetch_players();
		// Iterator<Player> witr = wplayer.iterator();
		// while (witr.hasNext())
		// 	Wnames.add(witr.next().name());

		// bplayer = Player.fetch_players();
		// Iterator<Player> bitr = bplayer.iterator();
		// while (bitr.hasNext())
		// 	Bnames.add(bitr.next().name());
		// WNames = Wnames.toArray(WNames);
		// BNames = Bnames.toArray(BNames);

		// Cell cell;
		board.setBorder(BorderFactory.createLoweredBevelBorder());
		// pieces.Piece P;
		content = getContentPane();
		setSize(Width, Height);
		setTitle("Chess");

		content.setBackground(Color.black);
		
		
		content.setLayout(new BorderLayout());
		// controlPanel.setLayout(new GridLayout(3, 3));
		// controlPanel.setBorder(BorderFactory.createTitledBorder(null, "Statistics", TitledBorder.TOP,
		// 		TitledBorder.CENTER, new Font("Lucida Calligraphy", Font.PLAIN, 20), Color.ORANGE));

		// Defining the Player Box in Control Panel
		// WhitePlayer = new JPanel();
		// WhitePlayer.setBorder(BorderFactory.createTitledBorder(null, "White Player", TitledBorder.TOP,
		// 		TitledBorder.CENTER, new Font("times new roman", Font.BOLD, 18), Color.RED));
		// WhitePlayer.setLayout(new BorderLayout());

		// BlackPlayer = new JPanel();
		// BlackPlayer.setBorder(BorderFactory.createTitledBorder(null, "Black Player", TitledBorder.TOP,
		// 		TitledBorder.CENTER, new Font("times new roman", Font.BOLD, 18), Color.BLUE));
		// BlackPlayer.setLayout(new BorderLayout());

		// JPanel whitestats = new JPanel(new GridLayout(3, 3));
		// JPanel blackstats = new JPanel(new GridLayout(3, 3));
		// wcombo = new JComboBox<String>(WNames);
		// bcombo = new JComboBox<String>(BNames);
		// wscroll = new JScrollPane(wcombo);
		// bscroll = new JScrollPane(bcombo);
		// wcombopanel.setLayout(new FlowLayout());
		// bcombopanel.setLayout(new FlowLayout());

		// wselect = new Button("Select");
		// bselect = new Button("Select");
		 controlPanel.wselect.addActionListener(new SelectHandler(0));
		 controlPanel.bselect.addActionListener(new SelectHandler(1));

		// WNewPlayer = new Button("New Player");
		// BNewPlayer = new Button("New Player");
		controlPanel.WNewPlayer.addActionListener(new Handler(0));
		controlPanel.BNewPlayer.addActionListener(new Handler(1));

		// wcombopanel.add(wscroll);
		// wcombopanel.add(wselect);
		// wcombopanel.add(WNewPlayer);
		// bcombopanel.add(bscroll);
		// bcombopanel.add(bselect);
		// bcombopanel.add(BNewPlayer);
		// WhitePlayer.add(wcombopanel, BorderLayout.NORTH);
		// BlackPlayer.add(bcombopanel, BorderLayout.NORTH);
		// whitestats.add(new JLabel("Name   :"));
		// whitestats.add(new JLabel("Played :"));
		// whitestats.add(new JLabel("Won    :"));
		// blackstats.add(new JLabel("Name   :"));
		// blackstats.add(new JLabel("Played :"));
		// blackstats.add(new JLabel("Won    :"));
		// WhitePlayer.add(whitestats, BorderLayout.WEST);
		// BlackPlayer.add(blackstats, BorderLayout.WEST);
		// controlPanel.add(WhitePlayer);
		// controlPanel.add(BlackPlayer);
		// new
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.add(boardView.board_block[i][j]);
				boardView.board_block[i][j].addMouseListener(this);
			}
		}
		refleshCells();
		//
		// // the panel of game setting
		// showPlayer = new JPanel(new FlowLayout());
		// showPlayer.add(timeSlider);

		// JLabel setTimeLabel = new JLabel("Set Timer(in mins):");
		// // the start_button
		// startButton = new Button("Start");
		// startButton.setBackground(Color.black);
		// startButton.setForeground(Color.white);
		controlPanel.startButton.addActionListener(new START());
		// startButton.setPreferredSize(new Dimension(120, 40));
		// setTimeLabel.setFont(new Font("Arial", Font.BOLD, 16));
		// label = new JLabel("Time Starts now", JLabel.CENTER);
		// label.setFont(new Font("SERIF", Font.BOLD, 30));
		// displayTime = new JPanel(new FlowLayout());
		// time = new JPanel(new GridLayout(3, 3));
		// time.add(setTimeLabel);
		// time.add(showPlayer);
		// displayTime.add(startButton);
		// time.add(displayTime);
		// controlPanel.add(time);
		board.setMinimumSize(new Dimension(800, 700));

		// The Left Layout When Game is inactive
		temp = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				try {
					image = ImageIO.read(this.getClass().getResource("clash.jpg"));
				} catch (IOException ex) {
					System.out.println("not found");
				}

				g.drawImage(image, 0, 0, null);
			}
		};

		temp.setMinimumSize(new Dimension(800, 700));
		controlPanel.setMinimumSize(new Dimension(285, 700));
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, temp, controlPanel);
		controlPanel.getX();
		content.add(split);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// A function to clean the highlights of possible destination cells
	private void cleandestinations(ArrayList<Cell> destlist) // Function to clear the last move's destinations
	{
		ListIterator<Cell> it = destlist.listIterator();
		while (it.hasNext())
			it.next().removepossibledestination();
	}

	// A function that indicates the possible moves by highlighting the Cells
	private void highlightdestinations(ArrayList<Cell> destlist) {
		ListIterator<Cell> it = destlist.listIterator();
		while (it.hasNext())
			it.next().setpossibledestination();
	}

	// Other Irrelevant abstract function. Only the Click Event is captured.

	class START implements ActionListener {

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			if (White == null || Black == null) {
				JOptionPane.showMessageDialog(controlPanel, "Fill in the details");
				return;
			}
			White.updateGamesPlayed();
			White.Update_Player();
			Black.updateGamesPlayed();
			Black.Update_Player();
			controlPanel.WNewPlayer.disable();
			controlPanel.BNewPlayer.disable();
			controlPanel.wselect.disable();
			controlPanel.bselect.disable();
			
			split.remove(temp);
			split.add(board);

			controlPanel.showPlayer.remove(controlPanel.timeSlider);
			controlPanel.mov = new JLabel("Move:");
			controlPanel.mov.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			controlPanel.mov.setForeground(Color.red);
			controlPanel.showPlayer.add(controlPanel.mov);
			controlPanel.CHNC = new JLabel(controlPanel.move);
			controlPanel.CHNC.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
			controlPanel.CHNC.setForeground(Color.blue);
			controlPanel.showPlayer.add(controlPanel.CHNC);
			controlPanel.displayTime.remove(controlPanel.startButton);
			controlPanel.displayTime.add(controlPanel.label);
			controlPanel.timer = new Time(controlPanel.label);
			controlPanel.timer.start();
		}
	}

	class TimeChange implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent arg0) {
			timeRemaining = controlPanel.timeSlider.getValue() * 60;
		}
	}

	class SelectHandler implements ActionListener {
		private int color;

		SelectHandler(int i) {
			color = i;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			controlPanel.tempPlayer = null;
			String n = (color == 0) ? controlPanel.wname : controlPanel.bname;
			JComboBox<String> jc = (color == 0) ? controlPanel.wcombo : controlPanel.bcombo;
			JComboBox<String> ojc = (color == 0) ? controlPanel.bcombo : controlPanel.wcombo;
			ArrayList<Player> pl = (color == 0) ? controlPanel.wplayer : controlPanel.bplayer;
			// ArrayList<Player> otherPlayer=(color==0)?bplayer:wplayer;
			ArrayList<Player> opl = Player.fetch_players();
			if (opl.isEmpty())
				return;
			JPanel det = (color == 0) ? controlPanel.wdetails : controlPanel.bdetails;
			JPanel PL = (color == 0) ? controlPanel.WhitePlayer : controlPanel.BlackPlayer;
			if (controlPanel.selected == true)
				det.removeAll();
			n = (String) jc.getSelectedItem();
			Iterator<Player> it = pl.iterator();
			Iterator<Player> oit = opl.iterator();
			while (it.hasNext()) {
				Player p = it.next();
				if (p.name().equals(n)) {
					controlPanel.tempPlayer = p;
					break;
				}
			}
			while (oit.hasNext()) {
				Player p = oit.next();
				if (p.name().equals(n)) {
					opl.remove(p);
					break;
				}
			}

			if (controlPanel.tempPlayer == null)
				return;
			if (color == 0)
				White = controlPanel.tempPlayer;
			else
				Black = controlPanel.tempPlayer;
				controlPanel.bplayer = opl;
			ojc.removeAllItems();
			for (Player s : opl)
				ojc.addItem(s.name());
			det.add(new JLabel(" " + controlPanel.tempPlayer.name()));
			det.add(new JLabel(" " + controlPanel.tempPlayer.gamesplayed()));
			det.add(new JLabel(" " + controlPanel.tempPlayer.gameswon()));

			PL.revalidate();
			PL.repaint();
			PL.add(det);
			controlPanel.selected = true;
		}

	}

	// add new player
	class Handler implements ActionListener {
		private int color;

		Handler(int i) {
			color = i;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String n = (color == 0) ? controlPanel.wname : controlPanel.bname;
			JPanel j = (color == 0) ? controlPanel.WhitePlayer : controlPanel.BlackPlayer;
			ArrayList<Player> Players = Player.fetch_players();
			Iterator<Player> it = Players.iterator();
			JPanel det = (color == 0) ? controlPanel.wdetails : controlPanel.bdetails;
			n = JOptionPane.showInputDialog(j, "Enter your name");

			if (n != null) {
				// check whether the player is exist
				while (it.hasNext()) {
					if (it.next().name().equals(n)) {
						JOptionPane.showMessageDialog(j, "Player exists");
						return;
					}
				}

				if (n.length() != 0) {
					Player tem = new Player(n);
					tem.Update_Player();
					if (color == 0)
						White = tem;
					else
						Black = tem;
				} else
					return;
			} else
				return;
			det.removeAll();
			det.add(new JLabel(" " + n));
			det.add(new JLabel(" 0"));
			det.add(new JLabel(" 0"));
			j.revalidate();
			j.repaint();
			j.add(det);
			controlPanel.selected = true;
		}
	}

	// put picture on the cells
	public void refleshCells() {
		Piece board[][] = chessGame.getboard().board;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				boardView.board_block[i][j].setPiece(board[i][j]);
			}
		}
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		Cell cell = (Cell) e.getSource();
		System.out.println(String.valueOf(cell.x) + String.valueOf(cell.y));
		// Board board = chessGame.getboard();
		Piece board[][] = chessGame.getboard().board;
		// no selected
		if (selectedCell == null) {
			if (board[cell.x][cell.y] == null) {
				return;
			} else {
				if (board[cell.x][cell.y].getcolor() == chance) {
					cell.select();
					//get the valid position it can go
					Coordinate[] validCoordinates = chessGame.validCoordinates(new Coordinate(cell.x,cell.y));
					//mark the valid position
					for(int i=0;i<validCoordinates.length;i++){
						boardView.board_block[validCoordinates[i].getX()][validCoordinates[i].getY()].setpossibledestination();
					}
					selectedCell = cell;
				} else {
					return;
				}
			}
		//selected a chess
		} else {
			//remove the valid position color
			Coordinate[] validCoordinates = chessGame.validCoordinates(new Coordinate(selectedCell.x,selectedCell.y));
			for(int i=0;i<validCoordinates.length;i++){
				boardView.board_block[validCoordinates[i].getX()][validCoordinates[i].getY()].removepossibledestination();
			}
			// try to move the chess
			if (chessGame.move(new Coordinate(selectedCell.x, selectedCell.y), new Coordinate(cell.x, cell.y))) {
				chance = chance & 0x0001;
				selectedCell.deselect();
				selectedCell = null;
				refleshCells();

			} else {
				selectedCell.deselect();
				selectedCell = null;
				return;
			}
		}

	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// TODO: move to ChessGame
	// A function to change the chance from White Player to Black Player or vice
	// verse
	// It is made public because it is to be accessed in the Time Class
	// public void changechance() {
	// if (chessGame.ischeck(chance)) {
	// chance ^= 1;
	// gameend();
	// }
	// if (destinationlist.isEmpty() == false)
	// cleandestinations(destinationlist);
	// if (previous != null)
	// previous.deselect();
	// previous = null;
	// chance ^= 1;
	// if (!end && timer != null) {
	// timer.reset();
	// timer.start_button();
	// showPlayer.remove(CHNC);
	// if (this.move == "White")
	// this.move = "Black";
	// else
	// this.move = "White";
	// CHNC.setText(this.move);
	// showPlayer.add(CHNC);
	// }
	// }

	// TODO: move to ChessGame
	// A function to eliminate the possible moves that will put the King in danger
	// private ArrayList<Cell> filterdestination(ArrayList<Cell> destlist, Cell
	// fromcell) {
	// ArrayList<Cell> newlist = new ArrayList<Cell>();
	// Cell newboardstate[][] = new Cell[8][8];
	// ListIterator<Cell> it = destlist.listIterator();
	// int x, y;
	// while (it.hasNext()) {
	// for (int i = 0; i < 8; i++)
	// for (int j = 0; j < 8; j++) {
	// try {
	// newboardstate[i][j] = new Cell(chessGame.boardState[i][j]);
	// } catch (CloneNotSupportedException e) {
	// e.printStackTrace();
	// }
	// }

	// Cell tempc = it.next();
	// if (newboardstate[tempc.x][tempc.y].getpiece() != null)
	// newboardstate[tempc.x][tempc.y].removePiece();
	// newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
	// x = chessGame.getKing(chance).getx();
	// y = chessGame.getKing(chance).gety();
	// if (newboardstate[fromcell.x][fromcell.y].getpiece() instanceof King) {
	// ((King) (newboardstate[tempc.x][tempc.y].getpiece())).setx(tempc.x);
	// ((King) (newboardstate[tempc.x][tempc.y].getpiece())).sety(tempc.y);
	// x = tempc.x;
	// y = tempc.y;
	// }
	// newboardstate[fromcell.x][fromcell.y].removePiece();
	// if ((((King) (newboardstate[x][y].getpiece())).isindanger(newboardstate) ==
	// false))
	// newlist.add(tempc);
	// }
	// return newlist;
	// }

	// @SuppressWarnings("deprecation")
	// private void gameend() {
	// cleandestinations(destinationlist);
	// displayTime.disable();
	// timer.countdownTimer.stop();
	// if (previous != null)
	// previous.removePiece();
	// if (chance == 0) {
	// White.updateGamesWon();
	// White.Update_Player();
	// winner = White.name();
	// } else {
	// Black.updateGamesWon();
	// Black.Update_Player();
	// winner = Black.name();
	// }
	// JOptionPane.showMessageDialog(board, "Checkmate!!!\n" + winner + " wins");
	// WhitePlayer.remove(wdetails);
	// BlackPlayer.remove(bdetails);
	// displayTime.remove(label);

	// displayTime.add(start_button);
	// showPlayer.remove(mov);
	// showPlayer.remove(CHNC);
	// showPlayer.revalidate();
	// showPlayer.add(timeSlider);

	// split.remove(board);
	// split.add(temp);
	// WNewPlayer.enable();
	// BNewPlayer.enable();
	// wselect.enable();
	// bselect.enable();
	// end = true;
	// this.disable();
	// this.dispose();
	// // Mainboard = new Controller();
	// // Mainboard.setVisible(true);
	// // Mainboard.setResizable(false);
	// }

	// These are the abstract function of the parent class. Only relevant method
	// here is the On-Click Fuction
	// which is called when the user clicks on a particular cell
	// @Override
	// public void mouseClicked(MouseEvent arg0) {
	// // TODO Auto-generated method stub
	// c = (Cell) arg0.getSource();
	// if (previous == null) {
	// if (c.getpiece() != null) {
	// if (c.getpiece().getcolor() != chance)
	// return;
	// c.select();
	// previous = c;
	// destinationlist.clear();
	// destinationlist = c.getpiece().move(chessGame.boardState, c.x, c.y);
	// if (c.getpiece() instanceof King)
	// destinationlist = filterdestination(destinationlist, c);
	// else {
	// if (chessGame.ischeck(chance))
	// destinationlist = new ArrayList<Cell>(filterdestination(destinationlist, c));
	// else if (destinationlist.isEmpty() == false &&
	// chessGame.willkingbeindanger(c, destinationlist.get(0),chance))
	// destinationlist.clear();
	// }
	// highlightdestinations(destinationlist);
	// }
	// } else {
	// if (c.x == previous.x && c.y == previous.y) {
	// c.deselect();
	// cleandestinations(destinationlist);
	// destinationlist.clear();
	// previous = null;
	// } else if (c.getpiece() == null || previous.getpiece().getcolor() !=
	// c.getpiece().getcolor()) {
	// if (c.ispossibledestination()) {
	// if (c.getpiece() != null)
	// c.removePiece();
	// c.setPiece(previous.getpiece());
	// if (previous.ischeck())
	// previous.removecheck();
	// previous.removePiece();
	// if (chessGame.getKing(chance ^ 1).isindanger(chessGame.boardState)) {
	// chessGame.boardState[chessGame.getKing(chance ^
	// 1).getx()][chessGame.getKing(chance ^ 1).gety()].setcheck();
	// if (chessGame.checkmate(chessGame.getKing(chance ^ 1).getcolor())) {
	// previous.deselect();
	// if (previous.getpiece() != null)
	// previous.removePiece();
	// gameend();
	// }
	// }
	// if (chessGame.getKing(chance).isindanger(chessGame.boardState) == false)
	// chessGame.boardState[chessGame.getKing(chance).getx()][chessGame.getKing(chance).gety()].removecheck();
	// if (c.getpiece() instanceof King) {
	// ((King) c.getpiece()).setx(c.x);
	// ((King) c.getpiece()).sety(c.y);
	// }
	// changechance();
	// if (!end) {
	// timer.reset();
	// timer.start_button();
	// }
	// }
	// if (previous != null) {
	// previous.deselect();
	// previous = null;
	// }
	// cleandestinations(destinationlist);
	// destinationlist.clear();
	// } else if (previous.getpiece().getcolor() == c.getpiece().getcolor()) {
	// previous.deselect();
	// cleandestinations(destinationlist);
	// destinationlist.clear();
	// c.select();
	// previous = c;
	// destinationlist = c.getpiece().move(chessGame.boardState, c.x, c.y);
	// if (c.getpiece() instanceof King)
	// destinationlist = filterdestination(destinationlist, c);
	// else {
	// if
	// (chessGame.boardState[chessGame.getKing(chance).getx()][chessGame.getKing(chance).gety()].ischeck())
	// destinationlist = new ArrayList<Cell>(filterdestination(destinationlist, c));
	// else if (destinationlist.isEmpty() == false &&
	// chessGame.willkingbeindanger(c, destinationlist.get(0),chance))
	// destinationlist.clear();
	// }
	// highlightdestinations(destinationlist);
	// }
	// }
	// if (c.getpiece() != null && c.getpiece() instanceof King) {
	// ((King) c.getpiece()).setx(c.x);
	// ((King) c.getpiece()).sety(c.y);
	// }
	// }
}