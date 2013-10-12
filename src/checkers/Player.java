package checkers;

public interface Player {
    String getName();
    void setColor(int color);
    CheckersMove getMove(CheckersData board);//send whole board object
}
