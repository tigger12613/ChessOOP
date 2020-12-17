package pieces;

import java.util.ArrayList;

import chess.Board;
import chess.Coordinate;

/**
 * This is the Rook class inherited from abstract Piece class
 *
 */
public class Rook extends Piece {

	// Constructor
	public Rook(String i, String p, int c, int x, int y) {
		setId(i);
		setPath(p);
		setColor(c);
		setXPosition(x);
		setYPosition(y);
	}

	// Move function defined
	public ArrayList<Coordinate> move(Board board, Coordinate coordinate) {
		// Rook can move only horizontally or vertically
		possiblemoves.clear();
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
		return possiblemoves;
	}
}
