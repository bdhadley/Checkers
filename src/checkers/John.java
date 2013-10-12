package checkers;

import java.util.Random;

public class John implements Player{
    int playerColor;
    
    @Override
    public String getName(){
        return "John";
    }
    
    @Override
    public void setColor(int color){
        playerColor = color;
    }

    @Override
    public CheckersMove getMove(CheckersData board) {
        Random random = new Random();
        CheckersMove[] moves = board.getLegalMoves(playerColor);
        return moves[random.nextInt(moves.length)];
    }
}
