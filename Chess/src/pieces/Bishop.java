package pieces;

import java.util.ArrayList;

import chess.Board;
import chess.Coordinate;

/**
 * This is the Bishop Class. The Move Function defines the basic rules for
 * movement of Bishop on a chess board
 * 
 *
 */
public class Bishop extends Piece {

	// Constructor
	public Bishop(String i, String p, int c) {
		setId(i);
		setPath(p);
		setColor(c);
	}

	// move function defined. It returns a list of all the possible destinations of
	// a Bishop
	// The basic principle of Bishop Movement on chess board has been implemented
	public ArrayList<Coordinate> move(Board board, Coordinate coordinate) {
		// Bishop can Move diagonally in all 4 direction (NW,NE,SW,SE)
		// This function defines that logic
		possiblemoves.clear();

		int tempx = coordinate.getX() + 1, 
			tempy = coordinate.getY() - 1;
		while (tempx < 8 && tempy >= 0) {
			Coordinate c = new Coordinate(tempx, tempy);
			if (board.getPiece(c) == null) {
				possiblemoves.add(c);
			} else if (board.getPiece(c).getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add(c);
				break;
			}
			tempx++;
			tempy--;
		}

		tempx = coordinate.getX() - 1;
		tempy = coordinate.getY() + 1;
		while (tempx >= 0 && tempy < 8) {
			Coordinate c = new Coordinate(tempx, tempy);
			if (board.getPiece(c) == null)
				possiblemoves.add(c);
			else if (board.getPiece(c).getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add(c);
				break;
			}
			tempx--;
			tempy++;
		}

		tempx = coordinate.getX() - 1;
		tempy = coordinate.getY() - 1;
		while (tempx >= 0 && tempy >= 0) {
			Coordinate c = new Coordinate(tempx, tempy);
			if (board.getPiece(c) == null)
				possiblemoves.add(c);
			else if (board.getPiece(c).getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add(c);
				break;
			}
			tempx--;
			tempy--;
		}

		tempx = coordinate.getX() + 1;
		tempy = coordinate.getY() + 1;
		while (tempx < 8 && tempy < 8) {
			Coordinate c = new Coordinate(tempx, tempy);
			if (board.getPiece(c) == null)
				possiblemoves.add(c);
			else if (board.getPiece(c).getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add(c);
				break;
			}
			tempx++;
			tempy++;
		}
		return possiblemoves;
	}
}