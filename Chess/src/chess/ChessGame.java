package chess;

import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JPanel;

import pieces.*;


public class ChessGame {

    public Cell boardState[][];
	// public Piece board[][];
	private Board board = new Board();
    
    ChessGame(){}

    // A function to retrieve the Black King or White King
	public King getKing(int color) {
		if (color == 0)
			return wk;
		else
			return bk;
    }

    // Function to check if the king will be in danger if the given move is made
	// public boolean willkingbeindanger(Cell fromcell, Cell tocell, int chance) {
	// 	Cell newboardstate[][] = new Cell[8][8];
	// 	for (int i = 0; i < 8; i++)
	// 		for (int j = 0; j < 8; j++) {
	// 			try {
	// 				newboardstate[i][j] = new Cell(boardState[i][j]);
	// 			} catch (CloneNotSupportedException e) {
	// 				e.printStackTrace();
	// 				System.out.println("There is a problem with cloning !!");
	// 			}
	// 		}

	// 	if (newboardstate[tocell.x][tocell.y].getpiece() != null)
	// 		newboardstate[tocell.x][tocell.y].removePiece();

	// 	newboardstate[tocell.x][tocell.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
	// 	if (newboardstate[tocell.x][tocell.y].getpiece() instanceof King) {
	// 		((King) (newboardstate[tocell.x][tocell.y].getpiece())).setx(tocell.x);
	// 		((King) (newboardstate[tocell.x][tocell.y].getpiece())).sety(tocell.y);
	// 	}
	// 	newboardstate[fromcell.x][fromcell.y].removePiece();
	// 	if (((King) (newboardstate[getKing(chance).getx()][getKing(chance).gety()].getpiece()))
	// 			.isindanger(newboardstate) == true)
	// 		return true;
	// 	else
	// 		return false;
    // }
    
    // A Function to filter the possible moves when the king of the current player
	// is under Check
	// private ArrayList<Cell> incheckfilter(ArrayList<Cell> destlist, Cell fromcell, int color) {
	// 	ArrayList<Cell> newlist = new ArrayList<Cell>();
	// 	Cell newboardstate[][] = new Cell[8][8];
	// 	ListIterator<Cell> it = destlist.listIterator();
	// 	int x, y;
	// 	while (it.hasNext()) {
	// 		for (int i = 0; i < 8; i++)
	// 			for (int j = 0; j < 8; j++) {
	// 				try {
	// 					newboardstate[i][j] = new Cell(boardState[i][j]);
	// 				} catch (CloneNotSupportedException e) {
	// 					e.printStackTrace();
	// 				}
	// 			}
	// 		Cell tempc = it.next();
	// 		if (newboardstate[tempc.x][tempc.y].getpiece() != null)
	// 			newboardstate[tempc.x][tempc.y].removePiece();
	// 		newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
	// 		x = getKing(color).getx();
	// 		y = getKing(color).gety();
	// 		if (newboardstate[tempc.x][tempc.y].getpiece() instanceof King) {
	// 			((King) (newboardstate[tempc.x][tempc.y].getpiece())).setx(tempc.x);
	// 			((King) (newboardstate[tempc.x][tempc.y].getpiece())).sety(tempc.y);
	// 			x = tempc.x;
	// 			y = tempc.y;
	// 		}
	// 		newboardstate[fromcell.x][fromcell.y].removePiece();
	// 		if ((((King) (newboardstate[x][y].getpiece())).isindanger(newboardstate) == false))
	// 			newlist.add(tempc);
	// 	}
	// 	return newlist;
    // }
    // A function to check if the King is check-mate. The Game Ends if this function
	// returns true.
	// public boolean checkmate(int color) {
	// 	ArrayList<Cell> dlist = new ArrayList<Cell>();
	// 	for (int i = 0; i < 8; i++) {
	// 		for (int j = 0; j < 8; j++) {
	// 			if (boardState[i][j].getpiece() != null && boardState[i][j].getpiece().getcolor() == color) {
	// 				dlist.clear();
	// 				dlist = boardState[i][j].getpiece().move(boardState, i, j);
	// 				dlist = incheckfilter(dlist, boardState[i][j], color);
	// 				if (dlist.size() != 0)
	// 					return false;
	// 			}
	// 		}
	// 	}
	// 	return true;
    // }
    
    // public boolean ischeck(int chance){
    //     return boardState[getKing(chance).getx()][getKing(chance).gety()].ischeck();
    // }
    
    //TODO:
    //startGame;
    public startGame();
    // //select a coordinate and return the valid cordinates the chess can move
    // public ArrayList<Coordinate> validCoordinates(Coordinate coordinate){};
    //get the full board;
    public Board getBoard(){
		return board;
	}
    // //move chess at Coordinate a to coordinate b. if the move is valid return true else return false
    // public boolean move(Coordinate a,Coordinate b){};
    // // //return if the color king is be checkmate
    // public boolean checkmate(int color);
    // // return 0 if white win, 1 if black win, -1 is the game is not end.
    // public int isGameEnd(){};
    // // return if the color is be check
    // public boolean isCheck(int color){};
	
    
}
