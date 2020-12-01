package trebleCross;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TrebleCrossGame {

	public static void main(String[] args) {
		Scanner key = new Scanner(System.in); // create Scanner
		List<Integer> selections = new ArrayList<Integer>();
		
		// Ask user for game board size
		System.out.println("How big would you like your Treblecross Game to be?");
		int boardLength = key.nextInt();
		TrebleBoard gameBoard = new TrebleBoard(boardLength);
		
		// Print new game board
		gameBoard.printBoard();
		
		// Game Loop
		do {
			// Ask user for move, and print new board reflecting this
			System.out.println("\n\nWhich box would you like to select?");
			selections.add(key.nextInt());
			gameBoard.setSelected(selections);
			gameBoard.printBoard();
			
			if (gameBoard.gameOver()) {
				System.out.println("\nPlayer Wins!");
				break;
			}
			
			System.out.println("\n\nGame Score is: " + gameBoard.gameScore());
			
			// Calculate best move, and have computer make move, print new board
			selections.add(gameBoard.bestMove());
			System.out.println("Computer selects box " + selections.get(selections.size() - 1));
			gameBoard.setSelected(selections);
			gameBoard.printBoard();
			System.out.println("\n\nGameScore is: " + gameBoard.gameScore());
			
			if (gameBoard.gameOver()) {
				System.out.println("\n\nComputer Wins!");
			}
		} while (gameBoard.gameOver() == false);

	}
	

}
