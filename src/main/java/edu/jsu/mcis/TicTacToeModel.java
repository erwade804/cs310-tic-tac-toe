package edu.jsu.mcis;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */
        
        for (int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                board[i][j] = Mark.EMPTY;
            }
        }
        
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */
        
        // INSERT YOUR CODE HERE
        if((isValidSquare(row, col)) && !isSquareMarked(row, col)){
            if(xTurn){
                board[row][col] = Mark.X;
            }
            else{
                board[row][col] = Mark.O;
            }
            xTurn = !xTurn;
            return true;
        }
        else{
            return false;
        }

    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return TRUE if the specified location is within the bounds of the board */
        
        return row < width && col < width && row >= 0 && col >= 0;
        
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */

        return board[row][col] != Mark.EMPTY;
            
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */

        return board[row][col];
            
    }
	
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        if (isMarkWin(Mark.O)){
            return Result.O;
        }
        else if (isMarkWin(Mark.X)){
            return Result.X;
        }
        else if(isTie()) {
            return Result.TIE;
        }
        else{
            return Result.NONE;
        }
    }
	
    private boolean isMarkWin(Mark mark) {
        int diagUR = 0; // counter for diagonal up and to the right
        int diagDR = 0; // counter for diagonal down and to the right

        for(int i = 0; i < width; i++){ // for loop for rows
            int rowCount = 0; 
            int colCount = 0; 

            for(int j = 0; j < width; j++){ // loop for the collumns 
                if(board[i][j].equals(mark)){ // if the row is right, count it
                    rowCount ++;
                }
                if(board[j][i].equals(mark)){ // if the collumn is right, count it
                    colCount ++;
                }
            }

            if(rowCount == width || colCount == width){ // if the row or collumn was full, its a winner
                return true;
            }
            if(board[i][i].equals(mark)){ // if the diagonal is right, count it
                diagDR ++;
            }
            if(board[width-i-1][i].equals(mark)){ // if the other diagonal is right, count it
                diagUR ++;
            }
        }

        if(diagDR == width || diagUR == width){
            return true;
        }
        return false;
    }

	
    private boolean isTie() {
        
        if(isMarkWin(Mark.X)){return false;}
        if(isMarkWin(Mark.O)){return false;}

        /* Check the squares of the board to see if the game is a tie */

        for (int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                if (board[i][j] == Mark.EMPTY){
                    return false;
                }
            }
        }
        return true;   
    }

    public boolean isGameover() {
        
        /* Return TRUE if the game is over */
        
        return (Result.NONE != getResult());
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
    @Override
    public String toString() {
        
        StringBuilder output = new StringBuilder("  ");
        
        /* Output the board contents as a string (see examples) */
        
        String a = "";
        for(int i = 0; i < width; i++){
            a = a + i;
        }
        output.append(a+"\n");
        for(int i = 0; i < width; i++){
            String line = i +" ";
            for(int j = 0; j < width; j++){
                line = line+board[i][j];
            }
            output.append(line +"\n");
        }
        return output.toString();
    }
    
}
