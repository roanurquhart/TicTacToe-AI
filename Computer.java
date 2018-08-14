package tictactoe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Computer implements Player{

	private String _mark;
	private List<Integer> _enemyX;
	private List<Integer> _enemyY;
	private List<Integer> _friendlyX;
	private List<Integer> _friendlyY;
	private int[][] probBoard;

	public Computer(String mark){
		_mark = mark;
		probBoard = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				probBoard[i][j] = -1;
			}
		}
	}

	@Override
	public String getMark() {
		return _mark;
	}

	public Coordinate chooseMove(List<Integer> enemyX, List<Integer> enemyY, List<Integer> friendlyX, List<Integer> friendlyY, int enemy, int friend) {
		_enemyX = enemyX;
		_enemyY = enemyY;
		_friendlyX = friendlyX;
		_friendlyY = friendlyY;
		fillBoard();
		return determinePlay();
	}

	public void fillBoard() {

		Iterator<Integer> exCoord = _enemyX.iterator();
		Iterator<Integer> eyCoord = _enemyY.iterator();
		Iterator<Integer> fxCoord = _friendlyX.iterator();
		Iterator<Integer> fyCoord = _friendlyY.iterator();
		while (exCoord.hasNext()) {
			probBoard[eyCoord.next()][exCoord.next()] = 1;
		}
		while (fxCoord.hasNext()) {
			probBoard[fyCoord.next()][fxCoord.next()] = 0;
		}
	}

	public Coordinate determinePlay() {
		int en = 0;
		int fr = 0;
		int space = 0;
		boolean enAtt = false;
		boolean frAtt = false;
		Coordinate enCoo = new Coordinate(-1,-1);
		Coordinate frCoo = new Coordinate(-1,-1);
		if (_enemyX.size() == 1) {
			if (_enemyX.get(0) == 1 && _enemyY.get(0) == 1) {
				return new Coordinate(0,2);
			} else {
				return new Coordinate (1,1);
			}
		}
		Coordinate sp = checkDiagonal();
		if (sp.getX() >= 0 && sp.getY() >= 0) {
			return sp;
		}

		// Horizontal
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (probBoard[i][j] == 1) {
					en++;
				} else if (probBoard[i][j] == 0) {
					fr++;
				} else if (probBoard[i][j] < 0) {
					space++;
					sp = new Coordinate(i,j);
				}
			}
			if (fr == 2 && space == 1) {frCoo = sp; frAtt = true;}
			if (en == 2 && space == 1) {enCoo = sp; enAtt = true;}
			en = 0;
			fr = 0;
			space = 0;
		}
		if (enAtt && frAtt) {
			return frCoo;
		}
		if (enAtt) {
			return enCoo;
		} else if (frAtt) {
			return frCoo;
		}

		//Vertical
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (probBoard[j][i] == 1) {
					en++;
				} else if (probBoard[j][i] == 0) {
					fr++;
				} else if (probBoard[j][i] < 0) {
					space++;
					sp = new Coordinate(j,i);
				}
			}
			if (fr == 2 && space == 1) {frCoo = sp; frAtt = true;}
			if (en == 2 && space == 1) {enCoo = sp; enAtt = true;}
			en = 0;
			fr = 0;
			space = 0;
		}
		if (enAtt && frAtt) {
			return frCoo;
		}
		if (enAtt) {
			return enCoo;
		} else if (frAtt) {
			return frCoo;
		}

		// If no defensive position was found, choose an offensive position to return
		//Horizontal Check
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (probBoard[i][j] == 1) {
					en++;
				} else if (probBoard[i][j] == 0) {
					fr++;
				} else if (probBoard[i][j] < 0) {
					space++;
					sp = new Coordinate(i,j);
				}
			}
			if (fr == 1 && space == 2) {return sp;}
			en = 0;
			fr = 0;
			space = 0;
		}
		//Vertical Check
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (probBoard[j][i] == 1) {
					en++;
				} else if (probBoard[j][i] == 0) {
					fr++;
				} else if (probBoard[j][i] < 0) {
					space++;
					sp = new Coordinate(j,i);
				}
			}
			if (fr == 1 && space == 2) {return sp;}
			en = 0;
			fr = 0;
			space = 0;
		}
		return sp;
	}

	public Coordinate checkDiagonal() {
		int i = 0;
		int j = 0;
		int en = 0;
		int fr = 0;
		int space = 0;
		Coordinate sp = new Coordinate(-1,-1);
		while (i < 3 && j < 3) {
			if (probBoard[i][j] == 1) {
				en++;
			} else if (probBoard[i][j] == 0) {
				fr++;
			} else if (probBoard[i][j] < 0) {
				space++;
				sp = new Coordinate(i,j);
			}
			i++;
			j++;
		}
		if (en == 2 && space == 1) {
			return sp;
		}
		if (fr == 2 && space == 1) {
			return sp;
		}
		i = 2;
		j = 0;
		en = 0;
		fr = 0;
		space = 0;
		while (i >= 0 && j < 3) {
			if (probBoard[i][j] == 1) {
				en++;
			} else if (probBoard[i][j] == 0) {
				fr++;
			} else if (probBoard[i][j] < 0) {
				space++;
				sp = new Coordinate(i,j);
			}
			i--;
			j++;
		}
		if (en == 2 && space == 1) {
			return sp;
		}
		if (fr == 2 && space == 1) {
			return sp;
		}
		return new Coordinate(-1,-1);
	}
}
