package checkers;

public interface Player {
    void setColor(int color);
    CheckersMove getMove(CheckersData board);//send whole board object
}
