package BridgeBuilderAdv;

/**
 * This class is for creating Player object in which it contains several methods when playing on the board game
 *
 * @author Aryan Farhang
 */

public class Player {

private char token;
private int score; // Better to make it static?

    /**
     * This construction wil initialize the distance variable of the class
     */

    public Player() {
        token = '+';
        score = 0;
    }

    /**
     * This method allows the user to place their token down on to the board
     *
     * @param board it uses the board parameter since wee need to access the placeToken method
     * @param row input row number to specify the chosen row
     * @param col input col number to specify the chosen col
     */

    public void makeMove(GameBoard board, int row, int col){
        board.placeToken(row, col, token);
    }

    /**
     *
     * @return returns the Player token.
     */
    public char getToken(){
        return token;
    }

    /**
     *
     * @return returns player score
     */

    public int getScore(){
        return score;
    }

    /**
     *
     * @param increment it takes an int type argument to increment it to the score
     */
    public void addScore(int increment){
        score += increment;
    }



}
