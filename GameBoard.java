package BridgeBuilderAdv;

/**
 * This class is for creating game board in which it contains several methods which are used in the Player, Engineer, and Main class!
 *
 * @author Aryan Farhang
 *
 */

public class GameBoard {
    private char[][] board; // Two-dimensional array that essentially depict our board game.
    private int size; // This is the size of the board game (TODO NOTE since our board game is a square the sides are equal thus the size variable will indicate either side)



    /**
     *This is the constructor where the instance variables are initialized
     * @param size is taken as the argument which is in type int is used to set the correct size of the two-dimensional array set by the user.
     */
    public GameBoard(int size) {
        this.board = new char[size][size];
        this.size = size;

        // The nested for loops are used to access each element in the (two-dimensional array) and assign '.' type char to signify an empty spot in the board game.

        for (int i = 0; i< size; i++){
            for (int j = 0; j < size; j++){
                this.board[i][j] = '.';


            }
        }
    }

    /**
     * This method allows the Engineer or Player place a token on a position on the game board if the  appropriate parameters are provided
     *
     * @param row The player or engineer can input an int number to indicate which row they are choosing on the game board.
     * @param col The player or engineer can input an int number to indicate which column they are choosing on the game board.
     * @param token The player or engineer can input a char input to choose their token of choice when indicating their position.
     */
    public void placeToken(int row, int col, char token){ // this method will place a token down
        this.board[row][col] = token;
    }

    /**
     * This method will check if a current position selected is empty or full. Note in the constructor, an empty position is indicated by a single '.' type char.
     *
     * @param row Selecting a row type int to check
     * @param col Selecting a col type int to check
     * @return If the selected position is empty meaning/== '.' the method will return TRUE else FALSE
     */
    public boolean isPositionEmpty(int row, int col){

        if (board[row][col] == '.') { // If empty is when the position on the board is '.'
            return true;
        }
        return false;

    }

    /**
     * This method will return the size of the game board
     * @return Returning the size of the game board
     */
    public int getSize(){
        return size;
    }

    /**
     * This method will display the game board with the appropriate row numbers and column character:
     */
    public void displayBoard(){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // There are 26 characters
        for (int character = 0; character < size; character++){ // In this for loop we will display the characters with respect to the size of the board
            System.out.print("  " + letters.charAt(character) );
        }

        System.out.println(); // Next line
        // In this nested for loop we will display all the elements in  board game format
        for (int row = 0; row < size; row++){
            System.out.print(row); //Starting with each row the number associated will be displayed

            for (int col = 0; col < size; col++){
                if (col == size - 1){ // Here when it reaches the last column of the game board it will go to the next line
                    System.out.println( " " + board[row][col] + " ");
                } else { // Else print on the same line
                    System.out.print( " " + board[row][col] + " ");
                }
            }
        }



    }

    /**
     * This is method is a private helper method created to make the code more organized and readable.
     * This method will check to see if the player has won successfully by creating VERTICAL bridge on the game board.
     * NOTE: This helper method will be used in the upcoming checkForWinDirection method.
     *
     * @param player The ,method will use the player type Player object to detect the players token.
     * @return if the player has successfully created a VERTICAL bridge it will return the "won" variable assigned as 1 indicating they have created a bridge. If not, "won" will be assigned as 0 indicating they haven't .
     */
    private int verticalBridge(Player player){
        int won = 0; // Temporary value

        for (int col = 0; col < size; col++) { // this nested loop will iterate vertically to check for a vertical player win
            if (won == 1){
                return 1;
            }
            for (int row = 0; row < size; row++) {
                won = 1;
                if (board[row][col] != player.getToken()){ // checks that the vertical row are all player tokens
                    won = 0;
                    break;
                }
            }
        }
        return won;
    }

    /**
     * This is method is a private helper method created to make the code more organized and readable.
     * This method will check to see if the player has won successfully by creating HORIZONTAL bridge on the game board.
     * NOTE: This helper method will be used in the upcoming checkForWinDirection method.
     *
     * @param player The ,method will use the player type Player object to detect the players token.
     * @return if the player has successfully created a HORIZONTAL bridge it will return the "won" variable assigned as 1 indicating they have created a bridge. If not, "won" will be assigned as 0 indicating they haven't .
     */
    private int horizontalBridge(Player player){
        int won = 0;
        for (int row = 0; row < size; row++){ // this nested loop will iterate horizontally to check for a horizontal player win
            if (won == 1){
                return 1;
            }
            for (int col = 0; col< size; col++){
                won = 1;
                if (board[row][col] != player.getToken()) { //checks that the horizontal row are all player tokens
                    won = 0;
                    break;
                }
            }
        }
        return won;
    }

    /**
     * This is method is a private helper method created to make the code more organized and readable.
     * This method will check to see if the player has won successfully by creating (TOP LEFT TO BOTTOM RIGHT) DIAGONAL bridge on the game board.
     * NOTE: This method will be used in the upcoming checkForWinDirection.
     *
     * @param player The ,method will use the player type Player object to detect the players token.
     * @return if the player has successfully created a (TOP LEFT TO BOTTOM RIGHT) DIAGONAL bridge it will return the "won" variable assigned as 1 indicating they have created a bridge. If not, "won" will be assigned as 0 indicating they haven't .
     */

    private int topLeftToBotRightDiagonal(Player player) {
        for (int sameIndex = 0; sameIndex < size; sameIndex++) { // Since we know that a diagonal win must start from top left then we can easily start from board[0][0] and reduce down by one to board [1][1] and so on...
            if (board[sameIndex][sameIndex] != player.getToken()) {
                return 0;
            }
        }
        int won = 1;
        return won;
    }

    /**
     * This method will check 4 cases with respect the helper methods from 1) tp 4). 1) taking priority since it has the highest score and 4) being least important one:
     * 1) TopLeftToBotRightDiagonal method will be checked first. If true it will return 3 which is associated with the score  of 10 with respect to the main method
     * 2) HorizontalBridge method will be checked second. If true it will return 2 which is associated with the score of 7 with respect to the main method
     * 3) VerticalBridge method will be checked third. If true it will return 1 which is associated with the score of 5 with respect to the main method
     * 4) If none of the previous methods were true then it will return zero and nothing will happen
     *
     * @param player This method will use the player type Player object to detect the players token.
     * @return Will either return the four case: 1), 2), 3), or 4) with their associated returns
     */
    public int checkForWinDirection(Player player){

        if (topLeftToBotRightDiagonal(player) == 1) { // The higher score will take priority: topLeftToBotRightDiagonal() = 10 points
            return 3;
        }
        else if (verticalBridge(player) == 1){ // The higher score will take priority: verticalBridge() = 7 points
            return 2;
        }
        else if (horizontalBridge(player) == 1) {  // The higher score will take priority: horizontalBridge() = 5 points
            return 1;
        }


        return 0; // return nothing
    }

    /**
     * This method will check if the game has ended in a tie.
     * If the player has not won the game and the all the positions are taken by both Player and the Engineer then it's a tie!
     * @return It will return TRUE if the game has ended in a tie else it will return FALSE as long as there is an empty spot '.'
     */
    public boolean checkForTie(){ // TODO This method will not work bc main method is being weird and only works for odd matrix. I'll have to ask prof or TA !!!

        for(int row = 0; row < size; row++){ //The nested for loop will check every element
            for (int col = 0; col < size; col++){
                if (board[row][col] == '.'){ // If there still is an empty spot it will return false as in it's not a tie
                    return false;
                }
            }
        }
        return true; // it is a tie!
    }


}
