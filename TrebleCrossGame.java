package trebleCross;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TrebleCrossGame {

	public static void main(String[] args) {
		Scanner key = new Scanner(System.in); // create Scanner
		boolean start = true;
		while(start) {
			boolean restart = playGame();
			if(!restart) {
				System.out.println("\nThank you for playing!");
				start = false;
			}
		}	

	}
	
	public static boolean playGame() {
		boolean gameplay = true;
		Scanner key = new Scanner(System.in); // create Scanner
		List<Integer> aSelect = new ArrayList<Integer>();
		List<Integer> bSelect = new ArrayList<Integer>();
		List<Integer> cSelect = new ArrayList<Integer>();
		// Ask user for game board size
		System.out.println("Setting up Treblecross Game of 3 Rows... ");
		int rowCount = 3; 
		int[] rows = new int[rowCount];
		for(int i = rowCount; i > 0; i--) {
			System.out.println("Length of "+ i +" row in your Treblecross game?");
			rows[i-1] = key.nextInt();
		}
		int a = rows[0]; int b = rows[1]; int c = rows[2]; //Row sizes
		System.out.println("Row A of length: "+a);
		TrebleBoard gameBoard_a = new TrebleBoard(a);
		System.out.println("Row B of length: "+b);
		TrebleBoard gameBoard_b = new TrebleBoard(b);
		System.out.println("Row C of length: "+c);
		TrebleBoard gameBoard_c = new TrebleBoard(c);
		System.out.println("");
		// Print new game board
		gameBoard_a.printBoard();
		System.out.println("\n");
		gameBoard_b.printBoard();
		System.out.println("\n");
		gameBoard_c.printBoard();
		System.out.println("\n");
		// Game Loop
		boolean gameOverall = false;
		do {
			boolean aOver = false;
			boolean bOver = false;
			boolean cOver = false;
			
			// Ask user for move, and print new board reflecting this
			System.out.println("\n\nSelect Row(A,B,C): ");
			String sRow = key.next();
			System.out.println("\n\nSelect Position Number on Row "+sRow.toUpperCase()+" :");
			int selected = key.nextInt();
			if( sRow.equalsIgnoreCase("A")) {
				// Error Checking for box
				if(selected > a || selected == 0) {
					System.out.println("\nSelected Box is not on gameboard, please try again!");
					break;
				}
				aSelect.add(selected);
				gameBoard_a.setSelected(aSelect);
				
				
			}
			if (sRow.equalsIgnoreCase("B")) {
				// Error Checking for box
				if(selected > b || selected == 0) {
					System.out.println("\nSelected Box is not on gameboard, please try again!");
					break;
				}
				bSelect.add(selected);
				gameBoard_b.setSelected(bSelect);
				
			}
			if(sRow.equalsIgnoreCase("C")) {
				// Error Checking for box
				if(selected > c || selected == 0) {
					System.out.println("\nSelected Box is not on gameboard, please try again!");
					break;
				}
				cSelect.add(selected);
				gameBoard_c.setSelected(cSelect);
				
			}
			gameBoard_a.printBoard();
			System.out.println("\n");
			gameBoard_b.printBoard();
			System.out.println("\n");
			gameBoard_c.printBoard();
			System.out.println("\n");
			if(gameBoard_a.gameOver()) {
				aOver = true;
			}
			if(gameBoard_b.gameOver()) {
				bOver = true;
			}
			if(gameBoard_c.gameOver()) {
				cOver = true;
			}
			if (gameBoard_a.gameOver() && gameBoard_b.gameOver() && gameBoard_c.gameOver()) {
				System.out.println("\nPlayer Wins!");
				gameOverall = true;
			}
			
			System.out.println("Game A Score is: " + gameBoard_a.gameScore());
			System.out.println("Game B Score is: " + gameBoard_b.gameScore());
			System.out.println("Game C Score is: " + gameBoard_c.gameScore());
			System.out.println("");
			System.out.println("A Move "+gameBoard_a.bestMove());
			System.out.println("B Move "+gameBoard_b.bestMove());
			System.out.println("C Move "+gameBoard_c.bestMove());
			
			System.out.println("A WMove "+gameBoard_a.winningMove());
			System.out.println("B WMove "+gameBoard_b.winningMove());
			System.out.println("C WMove "+gameBoard_c.winningMove());
			
			/* Calculate best move, and have computer make move, print new board 
			 * First determine if there are any row specific winning moves,
			 * If not, then choose the best move on the game with the largest
			 * game value. 
			 * 
			 */
//			if( (aOver == false) && (gameBoard_a.hasWinningMove() && ((!gameBoard_b.hasWinningMove() || bOver) && (!gameBoard_c.hasWinningMove() || cOver)))  ){
//				
//			}
			
			if(((gameBoard_a.hasWinningMove() && aOver == false)) && ((bOver && cOver)) || 
					(((aOver == false) && ( (gameBoard_b.hasWinningMove() == false || bOver) && (gameBoard_c.hasWinningMove() == false || cOver)))&&
							(gameBoard_a.gameScore() >= gameBoard_c.gameScore()) && (gameBoard_a.gameScore() >= gameBoard_b.gameScore())) ){
				
						aSelect.add(gameBoard_a.bestMove());
						System.out.println("COMPUTER selects Row A Box " + aSelect.get(aSelect.size() - 1));
						gameBoard_a.setSelected(aSelect);
			}
			
			else if(((gameBoard_b.hasWinningMove() && bOver == false) && ( (bOver) && (gameBoard_c.hasWinningMove() == false || cOver)) ) || 
					(((bOver == false) && ( (gameBoard_a.hasWinningMove() == false || aOver) && (gameBoard_c.hasWinningMove() == false || cOver)))  && 
							(gameBoard_b.gameScore() >= gameBoard_c.gameScore()) && (gameBoard_b.gameScore() >= gameBoard_a.gameScore()))){
				
						bSelect.add(gameBoard_b.bestMove());
						System.out.println("COMPUTER selects Row B Box " + bSelect.get(bSelect.size() - 1));
						gameBoard_b.setSelected(bSelect);
			}
			
			else if(((gameBoard_c.hasWinningMove() && cOver == false) && ( (gameBoard_b.hasWinningMove() == false || bOver) && (gameBoard_a.hasWinningMove() == false || aOver)) ) || 
					(((cOver == false) && ( (gameBoard_b.hasWinningMove() == false || bOver) && (gameBoard_a.hasWinningMove() == false || aOver)))  && 
							(gameBoard_c.gameScore() >= gameBoard_a.gameScore()) && (gameBoard_c.gameScore() >= gameBoard_b.gameScore()))){
				
						cSelect.add(gameBoard_c.bestMove());
						System.out.println("COMPUTER selects Row C Box " + cSelect.get(cSelect.size() - 1));
						gameBoard_c.setSelected(cSelect);
				
			}
			else if(aOver == false) {
				aSelect.add(gameBoard_a.bestMove());
				System.out.println("COMPUTER selects Row A Box " + aSelect.get(aSelect.size() - 1));
				gameBoard_a.setSelected(aSelect);
				
			}
			else if (bOver == false) {
				bSelect.add(gameBoard_b.bestMove());
				System.out.println("COMPUTER selects Row B Box " + bSelect.get(bSelect.size() - 1));
				gameBoard_b.setSelected(bSelect);
			}
			else if (cOver == false) {
				cSelect.add(gameBoard_c.bestMove());
				System.out.println("COMPUTER selects Row C Box " + cSelect.get(cSelect.size() - 1));
				gameBoard_c.setSelected(cSelect);
				
			}
			
			gameBoard_a.printBoard();
			System.out.println("\n");
			gameBoard_b.printBoard();
			System.out.println("\n");
			gameBoard_c.printBoard();
			System.out.println("\n");
			System.out.println("Game A Score is: " + gameBoard_a.gameScore());
			System.out.println("Game B Score is: " + gameBoard_b.gameScore());
			System.out.println("Game C Score is: " + gameBoard_c.gameScore());
			if(gameBoard_a.gameOver()) {
				aOver = true;
			}
			if(gameBoard_b.gameOver()) {
				bOver = true;
			}
			if(gameBoard_c.gameOver()) {
				cOver = true;
			}
			
			// Calculate all potential game value comparisons.
//			selections.add(gameBoard.bestMove()); DONE
//			System.out.println("Computer selects box " + selections.get(selections.size() - 1)); DONE
//			gameBoard.setSelected(selections); DONE 
			
			
			if (gameBoard_a.gameOver() && gameBoard_b.gameOver() && gameBoard_c.gameOver()) {
				System.out.println("\n\nComputer Wins!");
				gameOverall = true;
			}
		} while (gameOverall == false);
		//(gameBoard_a.gameOver() == false) && (gameBoard_b.gameOver() == false) && (gameBoard_c.gameOver() == false)
		System.out.println("\nWould you like to play again?[Y,N]");
		String again = key.next();
		if( (again.toLowerCase()).equals("y") ) {
			gameplay = true;
		}
		else {
			gameplay = false;
		}
		return gameplay;
			
			
	}
}
