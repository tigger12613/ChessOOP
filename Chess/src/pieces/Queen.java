package pieces;

import java.util.ArrayList;


import chess.Position;
import chess.Cell;

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
	public ArrayList<Position> move(Piece state[][], int x, int y) {
		// Queen has most number of possible moves
		// Queen can move any number of steps in all 8 direction
		// The possible moves of queen is a combination of Rook and Bishop
		possiblemoves.clear();

		// Checking possible moves in vertical direction
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

		// Checking possible moves in horizontal Direction
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

		// Checking for possible moves in diagonal direction
		tempx = x + 1;
		tempy = y - 1;
		while (tempx < 8 && tempy >= 0) {
			if (state[tempx][tempy] == null)
				possiblemoves.add( new Position(tempx, tempy) );
			else if (state[tempx][tempy].getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add( new Position(tempx, tempy) );
				break;
			}
			tempx++;
			tempy--;
		}
		tempx = x - 1;
		tempy = y + 1;
		while (tempx >= 0 && tempy < 8) {
			if (state[tempx][tempy] == null)
				possiblemoves.add( new Position(tempx, tempy) );
			else if (state[tempx][tempy].getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add( new Position(tempx, tempy) );
				break;
			}
			tempx--;
			tempy++;
		}
		tempx = x - 1;
		tempy = y - 1;
		while (tempx >= 0 && tempy >= 0) {
			if (state[tempx][tempy] == null)
				possiblemoves.add( new Position(tempx, tempy) );
			else if (state[tempx][tempy].getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add( new Position(tempx, tempy) );
				break;
			}
			tempx--;
			tempy--;
		}
		tempx = x + 1;
		tempy = y + 1;
		while (tempx < 8 && tempy < 8) {
			if (state[tempx][tempy] == null)
				possiblemoves.add( new Position(tempx, tempy) );
			else if (state[tempx][tempy].getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add( new Position(tempx, tempy) );
				break;
			}
			tempx++;
			tempy++;
		}
		return possiblemoves;
	}
}