package pieces;

import java.util.ArrayList;

import chess.Board;
import chess.Coordinate;
/**
 * This is the Queen Class inherited from the abstract Piece class
 *
 */
public class Queen extends Piece {

	// Constructors
	public Queen(String i, String p, int c, int x, int y) {
		setId(i);
		setPath(p);
		setColor(c);
		setXPosition(x);
		setYPosition(y);
	}

	// Move Function Defined
	public ArrayList<Coordinate> move(Board board, Coordinate coordinate) {
		// Queen has most number of possible moves
		// Queen can move any number of steps in all 8 direction
		// The possible moves of queen is a combination of Rook and Bishop
		possiblemoves.clear();

		// Checking possible moves in vertical direction
		int x = coordinate.getX(),
			y = coordinate.getY();
			
		int tempx = x - 1;
		while (tempx >= 0) {
			Coordinate c = new Coordinate(tempx, y);
			if (board.getPiece(c) == null)
				possiblemoves.add(c);
			else if (board.getPiece(c).getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add(c);
				break;
			}
			tempx--;
		}

		tempx = x + 1;
		while (tempx < 8) {
			Coordinate c = new Coordinate(tempx, y);
			if (board.getPiece(c) == null)
				possiblemoves.add(c);
			else if (board.getPiece(c).getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add(c);
				break;
			}
			tempx++;
		}

		// Checking possible moves in horizontal Direction
		int tempy = y - 1;
		while (tempy >= 0) {
			Coordinate c = new Coordinate(x, tempy);
			if (board.getPiece(c) == null)
				possiblemoves.add(c);
			else if (board.getPiece(c).getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add(c);
				break;
			}
			tempy--;
		}
		tempy = y + 1;
		while (tempy < 8) {
			Coordinate c = new Coordinate(x, tempy);
			if (board.getPiece(c) == null)
				possiblemoves.add(c);
			else if (board.getPiece(c).getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add(c);
				break;
			}
			tempy++;
		}

		// Checking for possible moves in diagonal direction
		tempx = x + 1;
		tempy = y - 1;
		while (tempx < 8 && tempy >= 0) {
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
			tempy--;
		}
		tempx = x - 1;
		tempy = y + 1;
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
		tempx = x - 1;
		tempy = y - 1;
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
		tempx = x + 1;
		tempy = y + 1;
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