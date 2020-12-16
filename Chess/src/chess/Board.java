package chess;
import pieces.*;

public class Board {
    public Piece board[][] = new Piece[8][8];
    private static Rook wr01, wr02, br01, br02;
	private static Knight wk01, wk02, bk01, bk02;
	private static Bishop wb01, wb02, bb01, bb02;
	private static Pawn wp[], bp[];
	private static Queen wq, bq;
    private static King wk, bk;
    Board(){
        wr01 = new Rook("WR01", "White_Rook.png", 0);
		wr02 = new Rook("WR02", "White_Rook.png", 0);
		br01 = new Rook("BR01", "Black_Rook.png", 1);
		br02 = new Rook("BR02", "Black_Rook.png", 1);
		wk01 = new Knight("WK01", "White_Knight.png", 0);
		wk02 = new Knight("WK02", "White_Knight.png", 0);
		bk01 = new Knight("BK01", "Black_Knight.png", 1);
		bk02 = new Knight("BK02", "Black_Knight.png", 1);
		wb01 = new Bishop("WB01", "White_Bishop.png", 0);
		wb02 = new Bishop("WB02", "White_Bishop.png", 0);
		bb01 = new Bishop("BB01", "Black_Bishop.png", 1);
		bb02 = new Bishop("BB02", "Black_Bishop.png", 1);
		wq = new Queen("WQ", "White_Queen.png", 0);
		bq = new Queen("BQ", "Black_Queen.png", 1);
		wk = new King("WK", "White_King.png", 0, 7, 3);
		bk = new King("BK", "Black_King.png", 1, 0, 3);
		wp = new Pawn[8];
		bp = new Pawn[8];
		for (int i = 0; i < 8; i++) {
			wp[i] = new Pawn("WP0" + (i + 1), "White_Pawn.png", 0);
			bp[i] = new Pawn("BP0" + (i + 1), "Black_Pawn.png", 1);
		}
        board[0][0] = br01;
        board[0][1] = bk01;
        board[0][2] = bb01;
        board[0][3] = bq;
        board[0][4] = bk;
        board[0][5] = bb02;
        board[0][6] = bk02;
        board[0][7] = br02;
        for (int i = 0; i < 8; i++) {
            board[1][i] = bp[i];
        }
        board[7][0] = wr01;
        board[7][1] = wk01;
        board[7][2] = wb01;
        board[7][3] = wq;
        board[7][4] = wk;
        board[7][5] = wb02;
        board[7][6] = wk02;
        board[7][7] = wr02;
        for (int i = 0; i < 8; i++) {
            board[6][i] = wp[i];
        }
    }
}
