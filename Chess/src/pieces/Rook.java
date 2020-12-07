package pieces;

import java.util.ArrayList;


import chess.Position;
import chess.Cell;

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
	public ArrayList<Position> move(Piece state[][], int x, int y) {
		// Rook can move only horizontally or vertically
		possiblemoves.clear();
		int tempx = x - 1;
		while (tempx >= 0) {
			if (state[tempx][y] == null)
				possiblemoves.add( new Position(tempx, y) );
			else if (state[tempx][y].getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add( new Position(tempx, y) );
				break;
			}
			tempx--;
		}
		tempx = x + 1;
		while (tempx < 8) {
			if (state[tempx][y] == null)
				possiblemoves.add( new Position(tempx, y) );
			else if (state[tempx][y].getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add( new Position(tempx, y) );
				break;
			}
			tempx++;
		}
		int tempy = y - 1;
		while (tempy >= 0) {
			if (state[x][tempy] == null)
				possiblemoves.add( new Position(x, tempy) );
			else if (state[x][tempy].getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add( new Position(x, tempy) );
				break;
			}
			tempy--;
		}
		tempy = y + 1;
		while (tempy < 8) {
			if (state[x][tempy] == null)
				possiblemoves.add( new Position(x, tempy) );
			else if (state[x][tempy].getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add( new Position(x, tempy) );
				break;
			}
			tempy++;
		}
		return possiblemoves;
	}
}
