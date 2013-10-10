package checkers;

public class Controller {
    public void run(){
        Board.initialize();
        Boolean notDone=true;
        int[] moveSequence=null;
        while(notDone){
            Board.drawBoard();
            Board.resolveTurn(moveSequence, "red");
            Board.drawBoard();
            Board.resolveTurn(moveSequence, "black");
        }
    }
}
