package edu.jsu.mcis;

import java.util.Scanner;

public class TicTacToeView {
    
    private final Scanner keyboard;
    
    /* CONSTRUCTOR */
	
    public TicTacToeView() {
        
        /* Initialize scanner (for console keyboard) */
        
        keyboard = new Scanner(System.in);
        
    }
	
    public TicTacToeMove getNextMove(boolean isXTurn) {
        
        /* Prompt the player to enter the row and the column of their next move.
           Return as a TicTacToeMove object. */
        
           String nextLine = "Player ";
           if (xTurn){
               nextLine = nextLine + "1 (X) Move:";
               nextLine = nextLine +"\nEnter the row and column numbers, separated by a space: ";
           }
           System.out.print(nextLine);

           String input = keyboard.nextLine();
           System.out.println(input);
           String[] parts = input.split(" ");
           int[] intParts = {String.toInteger(parts[0]), String.toInteger(parts[1])};
           System.out.println(intParts);

        return new TicTactoeMove(intParts[0], intParts[1]);

    }

    public void showInputError() {

        System.out.println("Entered location is invalid, already marked, or out of bounds.");

    }

    public void showResult(String r) {

        System.out.println(r + "!");

    }
    
    public void showBoard(String board) {
        
        System.out.println("\n\n" + board);
        
    }
	
}
