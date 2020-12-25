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
	// who can go, 0 is white, 1 is black
	private int chance = 0;
	private Player White = null, Black = null;

	private JPanel board = new JPanel(new GridLayout(8, 8));
	private JSplitPane split;

	private ControlPanel controlPanel;
	private JPanel temp;
	private Container content;
	private BufferedImage image;
	public static int timeRemaining = 60;

	public String wname = null, bname = null, winner = null;
	// new
	private BoardView boardView = new BoardView();
	private Cell selectedCell;

	// Constructor
	Controller() {
		// variable initialization
		chessGame = new ChessGame();
		chance = 0;
		controlPanel = new ControlPanel();
		controlPanel.timeSlider.addChangeListener(new TimeChange());

		board = new JPanel(new GridLayout(8, 8));

		board.setMinimumSize(new Dimension(800, 700));
		ImageIcon img = new ImageIcon(this.getClass().getResource("img/icon.png"));
		this.setIconImage(img.getImage());

		// Cell cell;
		board.setBorder(BorderFactory.createLoweredBevelBorder());
		// pieces.Piece P;
		content = getContentPane();
		setSize(Width, Height);
		setTitle("Chess");

		content.setBackground(Color.black);

		content.setLayout(new BorderLayout());
		controlPanel.wselect.addActionListener(new SelectHandler(0));
		controlPanel.bselect.addActionListener(new SelectHandler(1));

		controlPanel.WNewPlayer.addActionListener(new Handler(0));
		controlPanel.BNewPlayer.addActionListener(new Handler(1));

		// new
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board.add(boardView.board_block[i][j]);
				boardView.board_block[i][j].addMouseListener(this);
			}
		}
		refleshCells();
		controlPanel.startButton.addActionListener(new START());
		board.setMinimumSize(new Dimension(800, 700));

		// The Left Layout When Game is inactive
		temp = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				try {
					image = ImageIO.read(this.getClass().getResource("img/clash.jpg"));
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

	// put picture on the cells
	public void refleshCells() {
		Piece board[][] = chessGame.getBoard().board;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				boardView.board_block[i][j].setPiece(board[i][j]);
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void gameEnd() {
		String winner;
		// cleandestinations(destinationlist);
		controlPanel.displayTime.disable();
		controlPanel.timer.countdownTimer.stop();
		// if (previous != null)
		// previous.removePiece();
		if (chance == 0) {
			White.updateGamesWon();
			White.Update_Player();
			winner = White.name();
		} else {
			Black.updateGamesWon();
			Black.Update_Player();
			winner = Black.name();
		}
		JOptionPane.showMessageDialog(board, "Checkmate!!!\n" + winner + " wins");
		controlPanel.WhitePlayer.remove(controlPanel.wdetails);
		controlPanel.BlackPlayer.remove(controlPanel.bdetails);
		controlPanel.displayTime.remove(controlPanel.label);

		controlPanel.displayTime.add(controlPanel.startButton);
		controlPanel.showPlayer.remove(controlPanel.mov);
		controlPanel.showPlayer.remove(controlPanel.CHNC);
		controlPanel.showPlayer.revalidate();
		controlPanel.showPlayer.add(controlPanel.timeSlider);

		split.remove(board);
		split.add(temp);
		controlPanel.WNewPlayer.enable();
		controlPanel.BNewPlayer.enable();
		controlPanel.wselect.enable();
		controlPanel.bselect.enable();
		controlPanel.end = true;
		this.disable();
		this.dispose();
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		Cell cell = (Cell) e.getSource();
		System.out.println(String.valueOf(cell.x) + String.valueOf(cell.y));
		// Board board = chessGame.getboard();
		Piece board[][] = chessGame.getBoard().board;
		// no selected
		if (selectedCell == null) {
			if (board[cell.x][cell.y] == null) {
				return;
			} else {
				if (board[cell.x][cell.y].getcolor() == chance) {
					cell.select();
					// get the valid position it can go
					ArrayList<Coordinate> validCoordinates = chessGame.getBoard()
							.validCoordinates(new Coordinate(cell.x, cell.y));
					// mark the valid position
					for (int i = 0; i < validCoordinates.size(); i++) {
						boardView.board_block[validCoordinates.get(i).getX()][validCoordinates.get(i).getY()]
								.setpossibledestination();
					}
					selectedCell = cell;
				} else {
					return;
				}
			}
			// selected a chess
		} else {
			// remove the valid position color
			ArrayList<Coordinate> validCoordinates = chessGame.getBoard()
					.validCoordinates(new Coordinate(selectedCell.x, selectedCell.y));
			for (int i = 0; i < validCoordinates.size(); i++) {
				boardView.board_block[validCoordinates.get(i).getX()][validCoordinates.get(i).getY()]
						.removepossibledestination();
			}
			// try to move the chess
			if (chessGame.getBoard().move(new Coordinate(selectedCell.x, selectedCell.y),
					new Coordinate(cell.x, cell.y))) {
				if (chessGame.board.checkmate(chance ^ 1)) {
					gameEnd();
				}
				selectedCell.deselect();
				selectedCell = null;
				refleshCells();
				if (chessGame.board.whoWin() != -1) {
					gameEnd();
				}
				chance = chance ^ 1;

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
			String n = (color == 0) ? wname : bname;
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
			String n = (color == 0) ? wname : bname;
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
}