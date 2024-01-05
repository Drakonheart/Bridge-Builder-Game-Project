package BridgeBuilderAdv;


import java.util.Random;

/**
 * This class is for creating Engineer object in which it contains several methods when playing on the board game
 *
 * @author Aryan Farhang
 */
public class Engineer {

private char token;
private boolean hardMode;

    /**
     * This is the constructor wherethe iinstacne variable are being intlilized
     * @param hardMode Indicate a boolean type statement where the user will input either true for hard mode or false for none hard mode
     */
    public Engineer(boolean hardMode) {
        this.token = '0';
        this.hardMode = hardMode;
    }

    /**
     * This is a helper method in which it will be used in makeMove method
     * In easy mode the engineer will pace their token randomly on the board so:
     * This method will make temporary object of type Random to create random numbers with respect to the size of the board game
     * There will be two randomly germinated number one for column and one for row.
     *
     * @param gameBoard we will use the gameBoard parameter to access the getSize method as we need the size of the board to generate the correct numbers in the correct range
     */

    private void easyMode(GameBoard gameBoard){

        Random randomNum = new Random();
        boolean notFound = true;

        while (notFound){ // This while loop will occur until a position is found on board game
            int row = randomNum.nextInt(gameBoard.getSize()); // Random number for row
            int col = randomNum.nextInt(gameBoard.getSize()); // Random number for col
                if (gameBoard.isPositionEmpty(row, col)) { // Until a position is found!
                    gameBoard.placeToken(row, col, this.token);
                    notFound = false;

                }
            }
    }

    /** TODO Clarify with TA or PROF
     * This is a helper method in which it will be used in makeMove method
     * In hard mode the engineer will place their token based on a algorithmic strategy designed in this method :
     *
     * @param gameBoard We will use the gameBoard parameter to access the getSize method as we need the size of the board to generate the correct numbers in the correct range
     *
     * @param playerLastRow This method will use the players last row position
     * @param playerLastCol This method will use the players last COL position
     */
    private void hardMode(GameBoard gameBoard, int playerLastRow, int playerLastCol){
        int size = gameBoard.getSize(); // Stores the size of the game board.
        int opponentPositionCol = playerLastCol + 1;// The next column which is the Engineer column.
        boolean found = false;

        for ( int i = 0; i < size; i++){ // This loop will find if there are empty spots on same row of the player's token
            if (i > playerLastCol && gameBoard.isPositionEmpty(playerLastRow, i)){ // Check only to the right of the player token
                gameBoard.placeToken(playerLastRow, i, token);
                found =true; // Found empty spot on the row to the right of the player token.
                break;
            }
        }


        if (!found && (opponentPositionCol >= size || !gameBoard.isPositionEmpty(playerLastRow, opponentPositionCol))){ // this if statement is for moving vertically (column wise)
            boolean nextFound = false; // Temp variable for finding another empty spot
            for (int opponentPositionRow = 0; opponentPositionRow < size; opponentPositionRow++ ){ // For loop iterating in the column to find an empty spot for Engineer token.

                if (gameBoard.isPositionEmpty(opponentPositionRow, playerLastCol)){ // If empty : (down)
                    gameBoard.placeToken(opponentPositionRow, playerLastCol, token); // Token placed
                    nextFound = true; // Spot found
                    break;
                }

            }
            if ( !nextFound && playerLastCol -1 >= 0 && gameBoard.isPositionEmpty(playerLastRow, playerLastCol - 1)){ // If a spot was not found then the next position is to search  one position to the left of the Player token in the same row.
                gameBoard.placeToken( playerLastRow , playerLastCol -1, token); // Place token if empty
            }

            else if (!nextFound && playerLastCol - 1 >= 0 && gameBoard.isPositionEmpty(playerLastRow -1, playerLastCol - 1)) { // If the previous condition was not invoked then search one position below
                gameBoard.placeToken( playerLastRow , playerLastCol -1, token); // Place token if empty
            }

            else if (!nextFound){ // Else place the engineer token somewhere random
                easyMode(gameBoard); // Just invoke the easyMode() method
            }

        }

    }


    /**
     * This method will use private helper methods: (hardMode and easyMode) to set the engineers position
     * If hardMode is set to true then the private helper method hardMode is invoked else the easyMode() method is invoked.
     *
     * @param board We will use the gameBoard parameter to access the getSize method as we need the size of the board to generate the correct numbers in the correct range
     * @param playerLastRow This method will use the players last row position
     * @param playerLastCol This method will use the players last COL position
     */
    public void makeMove(GameBoard board, int playerLastRow, int playerLastCol) {
        if(hardMode){ // If hard mode is selected
            hardMode(board, playerLastRow, playerLastCol);

        }
        else { // else its easy mode
            easyMode(board);
        }

    }

    /**
     * A very complex method that is out of this world and returns an engineer TOKEN :D!
     * @return Returns the engineer token.
     */

    public char getToken(){ // Returns token

        return token;
    }

}
