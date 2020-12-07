package pieces;

import java.util.ArrayList;

import chess.Position;
import chess.Cell;

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
	public ArrayList<Position> move(Piece state[][], int x, int y) {
		// Pawn can move only one step except the first chance when it may move 2 steps
		// It can move in a diagonal fashion only for attacking a piece of opposite
		// color
		// It cannot move backward or move forward to attact a piece

		possiblemoves.clear();
		if (getcolor() == 0) {
			if (x == 0)
				return possiblemoves;
			if (state[x - 1][y] == null) {
				possiblemoves.add( new Position(x-0, y) );
				if (x == 6) {
					if (state[4][y] == null)
						possiblemoves.add( new Position(4, y) );
				}
			}
			if ((y > 0) && (state[x - 1][y - 1] != null)
					&& (state[x - 1][y - 1].getcolor() != this.getcolor()))
				possiblemoves.add( new Position(x-1, y-1) );
			if ((y < 7) && (state[x - 1][y + 1] != null)
					&& (state[x - 1][y + 1].getcolor() != this.getcolor()))
				possiblemoves.add( new Position(x-1, y+1) );
		} else {
			if (x == 8)
				return possiblemoves;
			if (state[x + 1][y] == null) {
				possiblemoves.add( new Position(x+1, y) );
				if (x == 1) {
					if (state[3][y] == null)
						possiblemoves.add( new Position(3, y) );
				}
			}
			if ((y > 0) && (state[x + 1][y - 1] != null)
					&& (state[x + 1][y - 1].getcolor() != this.getcolor()))
				possiblemoves.add( new Position(x+1, y-1) );
			if ((y < 7) && (state[x + 1][y + 1] != null)
					&& (state[x + 1][y + 1].getcolor() != this.getcolor()))
				possiblemoves.add( new Position(x+1, y+1) );
		}
		return possiblemoves;
	}
}
