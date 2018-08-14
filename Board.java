package tictactoe;

public class Board {

	private String[][] _board = new String[3][3];

	public Board() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				_board[i][j] = " ";
			}
		}
	}

	public void writeToBoard(int row, int column, String mark) {
		_board[row][column] = mark;
	}

	public void printBoard() {
			System.out.println("    " + 0 + "   " + 1 + "   " + 2 + "  ");
			System.out.println("   ___________");
			System.out.println(0 + " |" + " " + _board[0][0] + " " + "|" + " "  + _board[0][1] + " " + "|" + " " + _board[0][2] + " " + "|");
			System.out.println("   ___________");
			System.out.println(1 + " |" + " " + _board[1][0] + " " + "|" + " "  + _board[1][1] + " " + "|" + " " + _board[1][2] + " " + "|");
			System.out.println("   ___________");
			System.out.println(2 + " |" + " " + _board[2][0] + " " + "|" + " "  + _board[2][1] + " " + "|" + " " + _board[2][2] + " " + "|");
			System.out.println("   ___________");
			
	}
	public String[][] getArray() {
		return _board;
	}
}
