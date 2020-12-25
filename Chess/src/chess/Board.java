package chess;
import pieces.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class Board {
    public Piece board[][] = new Piece[8][8];
    public King bk, wk;

    //constructor
    public Board(){
        board[7][0] = new Rook("WR01", "White_Rook.png", 0);
		board[7][7] = new Rook("WR02", "White_Rook.png", 0);
		board[0][0] = new Rook("BR01", "Black_Rook.png", 1);
		board[0][7] = new Rook("BR02", "Black_Rook.png", 1);
		board[7][1] = new Knight("WK01", "White_Knight.png", 0);
		board[7][6] = new Knight("WK02", "White_Knight.png", 0);
		board[0][1] = new Knight("BK01", "Black_Knight.png", 1);
		board[0][6] = new Knight("BK02", "Black_Knight.png", 1);
		board[7][2] = new Bishop("WB01", "White_Bishop.png", 0);
		board[7][5] = new Bishop("WB02", "White_Bishop.png", 0);
		board[0][2] = new Bishop("BB01", "Black_Bishop.png", 1);
		board[0][5] = new Bishop("BB02", "Black_Bishop.png", 1);
		board[7][4] = new Queen("WQ", "White_Queen.png", 0);
		board[0][4] = new Queen("BQ", "Black_Queen.png", 1);
		wk = new King("WK", "White_King.png", 0, 7, 3);
		bk = new King("BK", "Black_King.png", 1, 0, 3);
        board[7][3] = wk;
		board[0][3] = bk;
		for (int j = 0; j < 8; j++) {
			board[6][j] = new Pawn("WP0" + (j + 1), "White_Pawn.png", 0);
			board[1][j] = new Pawn("BP0" + (j + 1), "Black_Pawn.png", 1);
		}
    }

    public Piece getPiece(Coordinate c){
        return board[c.getX()][c.getY()];
	}

	public ArrayList<Coordinate> validCoordinates(Coordinate coordinate){
		ArrayList<Coordinate> destList = board[coordinate.getX()][coordinate.getY()].move(this, coordinate);
		ArrayList<Coordinate> newList = incheckfilter(destList, coordinate, board[coordinate.getX()][coordinate.getY()].getcolor());
		return newList;
	}

	public boolean move(Coordinate a,Coordinate b){
		ArrayList<Coordinate> validList = validCoordinates(a);
		for(int i=0; i<validList.size(); i++){
			if( (validList.get(i).getX() == b.getX()) && (validList.get(i).getY() == b.getY()) ){
				board[b.getX()][b.getY()] =  board[a.getX()][a.getY()];
				if(board[b.getX()][b.getY()] == board[a.getX()][a.getY()]){
					board[a.getX()][a.getY()] = null;
					return true;
				}
			}
		}
		return false;	
	}

	public int isGameEnd(){
		if(isCheck(0) || isCheck(1)){
			return 1;
		}
		else{
			return -1;
		}
	}

	public boolean isCheck(int color){
		if(color == 0){
			return wk.isindanger(board);
		}
		else{
			return bk.isindanger(board);
		}
		
	}
	public boolean checkmate(int color){
		ArrayList<Coordinate> destList = new ArrayList<Coordinate>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null && board[i][j].getcolor() == color) {
					Coordinate c = new Coordinate(i, j);
					destList.clear();
					destList = board[i][j].move(this, c);
					destList = incheckfilter(destList, c, color);
					if (destList.size() != 0)
						return false;
				}
			}
		}
		return true;
	}

	private ArrayList<Coordinate> incheckfilter(ArrayList<Coordinate> destList, Coordinate source, int color) {
		ArrayList<Coordinate> newList = new ArrayList<Coordinate>();
		Piece newboardstate[][] = new Piece[8][8];
		ListIterator<Coordinate> it = destList.listIterator();
		while (it.hasNext()) {
			for (int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++) {
					try {
						newboardstate[i][j] = board[i][j].getcopy();
					} catch (CloneNotSupportedException e) {
						newboardstate[i][j] = null;
					} catch (NullPointerException e) {
						newboardstate[i][j] = null;
					}
				}
			}
			Coordinate destination = it.next();
			newboardstate[destination.getX()][destination.getY()] = newboardstate[source.getX()][source.getY()];
			newboardstate[source.getX()][source.getY()] = null;
			if (getKing(color).isindanger(newboardstate) == false){
				newList.add(destination);
			}
		}
		return newList;
	}

	private King getKing(int color) {
		if (color == 0) {
			return wk;
		}
		else {
			return bk;
		}
	}
	// no lose -1, white win return 0, black 1
	public int whoWin(){
		boolean iswk = false ,isbk = false;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++) {
				if(board[i][j]==bk){
					isbk = true;
				}
				if(board[i][j]==wk){
					iswk = true;
				}
			}
		}
		if(isbk == false){
			return 0;
		}else if(iswk == false){
			return 1;
		}else{
			return -1;
		}
	}
}
