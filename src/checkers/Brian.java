package checkers;

public class Brian implements Player{
    int playerColor;
    
    @Override
    public void setColor(int color){
        playerColor = color;
    }

    @Override
    public CheckersMove getMove(CheckersData board) {
        return board.getLegalMoves(playerColor)[0]; //Should'a been random.
    }
}
