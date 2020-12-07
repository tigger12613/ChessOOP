package pieces;

import java.util.ArrayList;


import chess.Position;
import chess.Cell;

/**
 * This is the Knight Class inherited from the Piece abstract class
 * 
 *
 */
public class Knight extends Piece {

	// Constructor
	public Knight(String i, String p, int c, int x, int y) {
		setId(i);
		setPath(p);
		setColor(c);
		setXPosition(x);
		setYPosition(y);
	}

	// Move Function overridden
	// There are at max 8 possible moves for a knight at any point of time.
	// Knight moves only 2(1/2) steps
	public ArrayList<Position> move(Piece state[][], int x, int y) {
		possiblemoves.clear();
		int posx[] = { x + 1, x + 1, x + 2, x + 2, x - 1, x - 1, x - 2, x - 2 };
		int posy[] = { y - 2, y + 2, y - 1, y + 1, y - 2, y + 2, y - 1, y + 1 };
		for (int i = 0; i < 8; i++)
			if ((posx[i] >= 0 && posx[i] < 8 && posy[i] >= 0 && posy[i] < 8))
				if ((state[posx[i]][posy[i]] == null
						|| state[posx[i]][posy[i]].getcolor() != this.getcolor())) {
					possiblemoves.add( new Position(posx[i], posy[i]) );
				}
		return possiblemoves;
	}
}