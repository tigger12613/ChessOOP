package pieces;

import java.util.ArrayList;

import chess.Board;
import chess.Coordinate;

/**
 * This is the Pawn Class inherited from the piece
 *
 */
public class Pawn extends Piece {

	// COnstructors
	public Pawn(String i, String p, int c, int x, int y) {
		setId(i);
		setPath(p);
		setColor(c);
		setXPosition(x);
		setYPosition(y);
	}

	// Move Function Overridden
	public ArrayList<Coordinate> move(Board board, Coordinate coordinate) {
		// Pawn can move only one step except the first chance when it may move 2 steps
		// It can move in a diagonal fashion only for attacking a piece of opposite
		// color
		// It cannot move backward or move forward to attact a piece
        
		possiblemoves.clear();
		int x = coordinate.getX(),
			y = coordinate.getY();
		Coordinate c = null;	
		if (getcolor() == 0) {
			if (x == 0){
				return possiblemoves;
			}

			c = new Coordinate(x-1, y);
			if (board.getPiece(c) == null) {
				possiblemoves.add(c);
				if (x == 6) {
					c = new Coordinate(4, y);
					if (board.getPiece(c) == null){
						possiblemoves.add(c);
					}
				}
			}

			c = new Coordinate(x-1, y-1);
			if ( (y > 0) && (board.getPiece(c) != null) && (board.getPiece(c).getcolor() != this.getcolor()) ){
				possiblemoves.add(c);
			}
				
			c = new Coordinate(x-1, y+1);
			if ( (y < 7) && (board.getPiece(c) != null) && (board.getPiece(c).getcolor() != this.getcolor()) ){
				possiblemoves.add(c);
			}
		} 
		else {
			if (x == 8){
				return possiblemoves;
			}
			
			c = new Coordinate(x+1, y);
			if (board.getPiece(c) == null) {
				possiblemoves.add(c);
				if (x == 1) {
					c = new Coordinate(3, y);
					if (board.getPiece(c) == null){
						possiblemoves.add(c);
					}
				}
			}

			c = new Coordinate(x+1, y-1);
			if ( (y > 0) && (board.getPiece(c) != null) && (board.getPiece(c).getcolor() != this.getcolor()) ){
				possiblemoves.add(c);
			}

			c = new Coordinate(x+1, y+1);
			if ( (y < 7) && (board.getPiece(c) != null) && (board.getPiece(c).getcolor() != this.getcolor()) ){
				possiblemoves.add(c);
			}
		}
		return possiblemoves;
	}
}
