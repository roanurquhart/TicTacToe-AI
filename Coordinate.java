package tictactoe;

public class Coordinate {

	private int _x;
	private int _y;
	
	public Coordinate(int y, int x) {
		_y = y;
		_x = x;
	}
	
	public int getX() {
		return _x;
	}
	
	public int getY() {
		return _y;
	}
}
