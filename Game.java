package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

	private Computer _computer;
	private Human _human;
	private Board _board;
	private String[][] _bArray;
	private boolean _isOver;
	private boolean _isStalemate;
	private List<Integer> _eXCoord;
	private List<Integer> _eYCoord;
	private List<Integer> _fXCoord;
	private List<Integer> _fYCoord;
	private List<String> _stringCoord;
	private int _hum;
	private int _com;
	private String _test = "test";
	private boolean humWin;
	private boolean comWin;

	public Game(Computer computer, Human human, Board b) {
		_computer = computer;
		_human = human;
		_board = b;
		_bArray = b.getArray();
		_isOver = false;
		_isStalemate = false;
		_eXCoord = new ArrayList<Integer>();
		_eYCoord = new ArrayList<Integer>();
		_fXCoord = new ArrayList<Integer>();
		_fYCoord = new ArrayList<Integer>();
		_stringCoord = new ArrayList<String>();
		_hum = 0;
		_com = 0;
		_stringCoord.add(_test);
		humWin = false;
		comWin = false;
	}

	public void play(Player turn) {
		int row = -1;
		int column = -1;
		String coord = _test;
		if (turn.equals(_human)) {
			Scanner s = new Scanner(System.in);
			// Collect input
			while (_stringCoord.contains(coord)) {
				row = -1;
				column = -1;
				while (row < 0 || row > 2) {
					System.out.println("Please enter a integer from 0 - 2 to choose the row in which you would like to play:");
					try {
						row = s.nextInt();
						if (row < 0 || row > 2) {System.out.println("Incorrect range");}
					} catch (Exception e) {
						System.out.println("Incorrect character. Please enter an ~integer~ between 0 - 2");
						row = 3;
					}
				}
				while (column < 0 || column > 2) {
					System.out.println("Please enter a integer from 0 - 2 to choose the column in which you would like to play:");
					try {
						column = s.nextInt();
						if (column < 0 || column > 2) {System.out.println("Incorrect range");}
					} catch (Exception e) {
						System.out.println("Incorrect character. Please enter an ~integer~ between 0 - 2");
						column = 3;
					}
				}
				coord = row + "," + column;
				if (_stringCoord.contains(coord)) {
					System.out.println("The coordinate has already been chosen, please select another tile");
				}
			}
			_stringCoord.add(coord);
			// Write input to the board
			_board.writeToBoard(row, column, _human.getMark());
			// Collect board data
			analyzeBoard();
			// Print board
			System.out.println("Updated Board:");
			_board.printBoard();
			if (humWin == true) {
				System.out.println("You have won! Congratulations!");
			} else if (_isStalemate) {
				System.out.println("The game ended in Stalmate!");
			}

		} else if (turn.equals(_computer)) {
			System.out.println("Computer Player's Turn:");
			Coordinate comMove = _computer.chooseMove(_eXCoord, _eYCoord, _fXCoord, _fYCoord, _hum, _com);
			_stringCoord.add(comMove.getY() + "," + comMove.getX());
			_board.writeToBoard(comMove.getY(), comMove.getX(), _computer.getMark());
			// Collect board data
			analyzeBoard();
			// Print board
			System.out.println("Updated Board:");
			_board.printBoard();
			if (comWin == true) {
				System.out.println("The computer was victorious. Better luck next time!");
			} else if (_isStalemate) {
				System.out.println("The game ended in Stalmate!");
			}
		}
	}

	public void analyzeBoard() {
		_eXCoord = new ArrayList<Integer>();
		_eYCoord = new ArrayList<Integer>();
		_fXCoord = new ArrayList<Integer>();
		_fYCoord = new ArrayList<Integer>();
		_hum = 0;
		_com = 0;
		int hum = 0;
		int com = 0;
		int space = 0;
		//Horizontal Check
		for (int i = 0; i < 3; i++) {
			hum = 0;
			com = 0;
			for (int j = 0; j < 3; j++) {
				if (_bArray[i][j].equals(_human.getMark())) {
					_eXCoord.add(j);
					_eYCoord.add(i);
					_hum++;
					hum++;
				} else if (_bArray[i][j].equals(_computer.getMark())) {
					_fXCoord.add(j);
					_fYCoord.add(i);
					_com++;
					com++;
				} else {
					space++;
				}
			}
			if (hum == 3) {humWin = true;_isOver = true;break;}
			if (com == 3) {comWin = true;_isOver = true;break;}
		}
		if (space <= 1) {_isOver = true;_isStalemate = true;}

		//Vertical Check
		space = 0;
		for (int i = 0; i < 3; i++) {
			hum = 0;
			com = 0;
			for (int j = 0; j < 3; j++) {
				if (_bArray[j][i].equals(_human.getMark())) {
					hum++;
				} else if (_bArray[j][i].equals(_computer.getMark())) {
					com++;
				} else {
					space++;
				}
			}
			if (hum == 3) {humWin = true;_isOver = true;break;}
			if (com == 3) {comWin = true;_isOver = true;break;}
		}
		if (space <= 1) {_isOver = true;_isStalemate = true;}

		//Diagonal Check

		//Left-Right Diagonal
		space = 0;
		hum = 0;
		com = 0;
		int i = 0;
		int j = 0;
		while (i < 3 && j < 3) {
			if (_bArray[i][j].equals(_human.getMark())) {
				hum++;
			} else if (_bArray[i][j].equals(_computer.getMark())) {
				com++;
			} else {
				space++;
			}
			i++;
			j++;
		}
		if (hum == 3) {humWin = true;_isOver = true;}
		if (com == 3) {comWin = true;_isOver = true;}
		//Right-Left Diagonal
		hum = 0;
		com = 0;
		space = 0;
		i = 2;
		j = 0;
		while (i >= 0 && j < 3) {
			if (_bArray[i][j].equals(_human.getMark())) {
				hum++;
			} else if (_bArray[i][j].equals(_computer.getMark())) {
				com++;
			} else {
				space++;

			}
			i--;
			j++;
		}
		if (hum == 3) {humWin = true;_isOver = true;}
		if (com == 3) {comWin = true;_isOver = true;}
	}

	public boolean isOver() {
		return _isOver;
	}

}
