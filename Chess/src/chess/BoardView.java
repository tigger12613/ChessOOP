package chess;

public class BoardView {
    public Cell board_block[][] = new Cell[8][8];
    BoardView(){
        for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				Cell cell = new Cell(i, j);
				board_block[i][j] = cell;
			}
		}
    }
}
