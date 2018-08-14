package tictactoe;

import java.util.Scanner;

public class Main {

	public static void main(String args[]) {

		//---------Begin Character Customization---------//
		Human human;
		Computer computer;
		boolean play = true;
		
		while (play == true) {
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter a single character to represent you player's mark");
		String mark1 = s.next();
		// Assign the human player's mark
		human = new Human(mark1);

		//Determine if the human player selected "x" or "X"
		if (!(mark1.equals("X")) && !(mark1.equals("x"))) {
			//Assign the computer player a mark
			computer = new Computer("X");
		} else {computer = new Computer("O");}

		System.out.println("Human Player's Chosen Mark:" + human.getMark());
		System.out.println("Computer Player's Chosen Mark:"+ computer.getMark());
		//---------End Character Customization---------//

		Board b = new Board();
		Game g = new Game(computer, human, b);
		System.out.println("Human player always begins first:");
		while (!g.isOver()) {
			g.play(human);
			if (g.isOver()) {break;}
			g.play(computer);
		}
		System.out.println("Would you like to play again? If so, enter 1, if not, enter 0:");
		Integer decision = s.nextInt();
		if (decision != 1) {play=false;}
		}
		System.out.println("Thanks for playing!");
	}
}
