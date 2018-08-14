package tictactoe;

public class Human implements Player{

	private String _mark;
	
	public Human(String mark) {
		_mark = mark;
	}

	@Override
	public String getMark() {
		return _mark;
	}
}
