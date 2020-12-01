package trebleCross;
import java.util.ArrayList;
import java.util.List;

public class TrebleBoard {
	
	List<Integer> board = new ArrayList<Integer>();
	
	// constructor
	public TrebleBoard(int length) {
		this.setGame(length);
	}
	
	// mutators
	public void setGame(int xLength) {
		for (int i = 0; i < xLength + 2; i++) {
			board.add(i, 0);
		}
	}
	
	public void setSelected(List<Integer> xSelected) {
		for (int i = 0; i < xSelected.size(); i++) {
			board.set(xSelected.get(i), 1);
		}
	}
	
	// print Board
	public void printBoard() {
		for (int i = 1; i < board.size() - 1; i++) {
				System.out.print(i + " ");
		}
		System.out.print("\n");
		for (int i = 1; i < board.size() - 1; i++) {
			if (i < 10) {
				System.out.print(board.get(i) + " ");
			} else {
				System.out.print(board.get(i) + "  ");
			}
		}
	}
	
	// check if a player has won
	public boolean gameOver() {
		for (int i = 3; i < board.size() - 1; i++) {
			if (board.get(i) + board.get(i - 1) + board.get(i - 2) == 3) {
				return true;
			}
		}
		return false;
	}
	
	// calculate game score of board
	public int gameScore() {
		List<Integer> heapSizes = new ArrayList<Integer>();
		int tempHeap = 0;
		int score = 0;
		
		// iterate through board, calculating relevant heap sizes, add to array list
		for (int i = 1; i < board.size() - 1; i++) {
			if (board.get(i - 1) == 0 && board.get(i) == 0 && board.get(i + 1) == 0) {
				tempHeap = tempHeap + 1;
			} else {
				heapSizes.add(tempHeap);
				tempHeap = 0;
			}
		}
		heapSizes.add(tempHeap);
		
		// if necessary, add 1 to the first and last heaps
		if (board.get(1) == 0 && board.get(2) == 0) {
			heapSizes.set(0, heapSizes.get(0) + 1);
		}
		if (board.get(board.size() - 2) == 0 && board.get(board.size() - 3) == 0) {
			heapSizes.set(heapSizes.size() - 1, heapSizes.get(heapSizes.size() - 1) + 1);
		}
		
		// calculate score with nim addition, return answer
		for (int j = 0; j < heapSizes.size(); j++) {
			score = score ^ heapSizes.get(j);
		}
		return score;
	}
	
	// calculate if there is a winning move
	public int winningMove() {
		// check for a block of a 1 0 1 block of boxes
		for (int i = 1; i < board.size() - 2; i++) {
			if (board.get(i) == 0) {
				if (board.get(i - 1) + board.get(i + 1) == 2) {
					return i;
				}
			}
		}
		
		// check for a block of 1 1 0 block of boxes
		for (int j = 2; j < board.size() - 1; j++) {
			if (board.get(j) == 0) {
				if (board.get(j - 1) + board.get(j - 2) == 2) {
					return j;
				}
			}
		}
		
		// check for a block of 0 1 1 block of boxes
		for (int k = 1; k < board.size() - 2; k++) {
			if (board.get(k) == 0) {
				if (board.get(k + 1) + board.get(k + 2) == 2) {
					return k;
				}
			}
		}
		
		// if there is no move that immediately results in a win
		return 0;
	}
	
	// calculate best move
	public int bestMove() {
		int tempIndex = 0;
		int tempScore;
		int bestScore = 100;
		int loopCount = 0;
		
		if (this.winningMove() > 0) {
			return this.winningMove();
		}
		
		//check if first and last box are possible moves
		if (board.get(1) == 0 && board.get(2) + board.get(3) == 0) {
			board.set(1, 1);
			tempScore = this.gameScore();
			if (tempScore < bestScore) {
				bestScore = tempScore;
				tempIndex = 1;
			}
			board.set(1, 0);
			loopCount = loopCount + 1;
		} else if (board.get(board.size() - 2) == 0 && board.get(board.size() - 3) + board.get(board.size() - 4) == 0) {
			board.set(board.size() - 2, 1);
			tempScore = this.gameScore();
			System.out.println(tempScore);
			if (tempScore < bestScore) {
				bestScore = tempScore;
				tempIndex = board.size() - 2;
			}
			board.set(board.size() - 2, 0);
			loopCount = loopCount + 1;
		}
		
		// check if a middle box is a better move
		for (int i = 2; i < board.size() - 2; i++) {
			if (board.get(i - 2) == 0 && board.get(i - 1) == 0 && board.get(i) == 0 && board.get(i + 1) == 0 && board.get(i + 2) == 0) {
				System.out.println("Index Checked: " + i);
				board.set(i, 1);
				tempScore = this.gameScore();
				System.out.println("Game Score calculated: " + this.gameScore());
				if (tempScore < bestScore) {
					bestScore = tempScore;
					tempIndex = i;
				}
				board.set(i, 0);
				loopCount = loopCount + 1;
			}
		}
		
		// check if no "possible" move was found
		if (loopCount == 0) {
			for (int j = 1; j < board.size() - 1; j++) {
				if (board.get(j) == 0) {
					return j;
				}
			}
		}
		return tempIndex;
	}

}
