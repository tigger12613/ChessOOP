package pieces;

import java.util.ArrayList;


import chess.Position;
import chess.Cell;

public class King extends Piece {

	private boolean isCheck; // Extra variables for King class to keep a track of king's check state

	// King Constructor
	public King(String i, String p, int c, int x, int y) {
		setId(i);
		setPath(p);
		setColor(c);
		setXPosition(x);
		setYPosition(y);
	}


	// Move Function for King Overridden from Pieces
	public ArrayList<Position> move(Piece state[][], int x, int y) {
		// King can move only one step. So all the adjacent 8 cells have been
		// considered.
		possiblemoves.clear();
		int posx[] = { x, x, x + 1, x + 1, x + 1, x - 1, x - 1, x - 1 };
		int posy[] = { y - 1, y + 1, y - 1, y, y + 1, y - 1, y, y + 1 };
		for (int i = 0; i < 8; i++)
			if ((posx[i] >= 0 && posx[i] < 8 && posy[i] >= 0 && posy[i] < 8))
				if ((state[ posx[i] ][ posy[i] ] == null
						|| state[posx[i]][posy[i]].getcolor() != this.getcolor()))
					possiblemoves.add(new Position(posx[i], posy[i]));
		return possiblemoves;
	}

	// Function to check if king is under threat
	// It checks whether there is any piece of opposite color that can attack king
	// for a given board state
	public boolean isindanger(Piece state[][]) {

		// Checking for attack from left,right,up and down
		int x = this.getXPosition(),
		    y = this.getYPosition();
		
		for (int i = x + 1; i < 8; i++) {
			if (state[i][y] == null)
				continue;
			else if (state[i][y].getcolor() == this.getcolor())
				break;
			else {
				if ((state[i][y] instanceof Rook) || (state[i][y] instanceof Queen))
					return true;
				else
					break;
			}
		}
		for (int i = x - 1; i >= 0; i--) {
			if (state[i][y] == null)
				continue;
			else if (state[i][y].getcolor() == this.getcolor())
				break;
			else {
				if ((state[i][y]instanceof Rook) || (state[i][y] instanceof Queen))
					return true;
				else
					break;
			}
		}
		for (int i = y + 1; i < 8; i++) {
			if (state[x][i] == null)
				continue;
			else if (state[x][i].getcolor() == this.getcolor())
				break;
			else {
				if ((state[x][i] instanceof Rook) || (state[x][i] instanceof Queen))
					return true;
				else
					break;
			}
		}
		for (int i = y - 1; i >= 0; i--) {
			if (state[x][i] == null)
				continue;
			else if (state[x][i].getcolor() == this.getcolor())
				break;
			else {
				if ((state[x][i] instanceof Rook) || (state[x][i] instanceof Queen))
					return true;
				else
					break;
			}
		}

		// checking for attack from diagonal direction
		int tempx = x + 1, tempy = y - 1;
		while (tempx < 8 && tempy >= 0) {
			if (state[tempx][tempy] == null) {
				tempx++;
				tempy--;
			} else if (state[tempx][tempy].getcolor() == this.getcolor())
				break;
			else {
				if (state[tempx][tempy] instanceof Bishop || state[tempx][tempy] instanceof Queen)
					return true;
				else
					break;
			}
		}
		tempx = x - 1;
		tempy = y + 1;
		while (tempx >= 0 && tempy < 8) {
			if (state[tempx][tempy] == null) {
				tempx--;
				tempy++;
			} else if (state[tempx][tempy].getcolor() == this.getcolor())
				break;
			else {
				if (state[tempx][tempy] instanceof Bishop || state[tempx][tempy] instanceof Queen)
					return true;
				else
					break;
			}
		}
		tempx = x - 1;
		tempy = y - 1;
		while (tempx >= 0 && tempy >= 0) {
			if (state[tempx][tempy] == null) {
				tempx--;
				tempy--;
			} else if (state[tempx][tempy].getcolor() == this.getcolor())
				break;
			else {
				if (state[tempx][tempy] instanceof Bishop || state[tempx][tempy] instanceof Queen)
					return true;
				else
					break;
			}
		}
		tempx = x + 1;
		tempy = y + 1;
		while (tempx < 8 && tempy < 8) {
			if (state[tempx][tempy] == null) {
				tempx++;
				tempy++;
			} else if (state[tempx][tempy].getcolor() == this.getcolor())
				break;
			else {
				if (state[tempx][tempy] instanceof Bishop || state[tempx][tempy] instanceof Queen)
					return true;
				else
					break;
			}
		}

		// Checking for attack from the Knight of opposite color
		int posx[] = { x + 1, x + 1, x + 2, x + 2, x - 1, x - 1, x - 2, x - 2 };
		int posy[] = { y - 2, y + 2, y - 1, y + 1, y - 2, y + 2, y - 1, y + 1 };
		for (int i = 0; i < 8; i++)
			if ((posx[i] >= 0 && posx[i] < 8 && posy[i] >= 0 && posy[i] < 8))
				if (state[posx[i]][posy[i]] != null
						&& state[posx[i]][posy[i]].getcolor() != this.getcolor()
						&& (state[posx[i]][posy[i]] instanceof Knight)) {
					return true;
				}

		// Checking for attack from the Pawn of opposite color
		int pox[] = { x + 1, x + 1, x + 1, x, x, x - 1, x - 1, x - 1 };
		int poy[] = { y - 1, y + 1, y, y + 1, y - 1, y + 1, y - 1, y };
		{
			for (int i = 0; i < 8; i++)
				if ((pox[i] >= 0 && pox[i] < 8 && poy[i] >= 0 && poy[i] < 8))
					if (state[pox[i]][poy[i]] != null
							&& state[pox[i]][poy[i]].getcolor() != this.getcolor()
							&& (state[pox[i]][poy[i]] instanceof King)) {
						return true;
					}
		}
		if (getcolor() == 0) {
			if (x > 0 && y > 0 && state[x - 1][y - 1] != null
					&& state[x - 1][y - 1].getcolor() == 1
					&& (state[x - 1][y - 1] instanceof Pawn))
				return true;
			if (x > 0 && y < 7 && state[x - 1][y + 1] != null
					&& state[x - 1][y + 1].getcolor() == 1
					&& (state[x - 1][y + 1] instanceof Pawn))
				return true;
		} else {
			if (x < 7 && y > 0 && state[x + 1][y - 1] != null
					&& state[x + 1][y - 1].getcolor() == 0
					&& (state[x + 1][y - 1] instanceof Pawn))
				return true;
			if (x < 7 && y < 7 && state[x + 1][y + 1] != null
					&& state[x + 1][y + 1].getcolor() == 0
					&& (state[x + 1][y + 1] instanceof Pawn))
				return true;
		}
		return false;
	}
	
	public void setCheck() {
		isCheck = true;
	}
	public boolean checkState() {
		return isCheck;
	}
}