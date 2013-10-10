/*package checkers;

public class Rules {
    private int[] board;
    private int initialRow;
    private int initialColumn;
    private int finalRow;
    private int finalColumn;
    private Boolean isFirstMove;
    
    public Rules(){
        this.board = Board.board;
    }
    
    protected Boolean isValidMoveSequence(int[] moveSequence, String playerColor){
        for(int i=0; i<moveSequence.length-1; i++){
            isFirstMove = (i==0)? true:false;
            initialPosition = moveSequence[i];
            finalPosition = moveSequence[i+1];
            if (!isValidMove(playerColor)) return false;
            if (!isKingLastMove(i, moveSequence.length-1)) return false;
        }
        return false;
    }
    
    private Boolean isValidMove(String playerColor){
        if(!onBoard()) return false;
        if(!ownsPiece(playerColor)) return false;
        if(!finalPositionEmpty()) return false;
        if(!(isStep()||isJump(playerColor))) return false;
        if(!isValidDirection()) return false;
        return true;
    }
    
    private Boolean onBoard(){
        if(initialRow<0 || initialPosition>31) return false;
        if(finalPosition<0 || finalPosition>31) return false;
        return true;
    }
    
    private Boolean ownsPiece(String playerColor){
        int pieceType = board[initialPosition];
        if(playerColor.equals("black") && (pieceType==Pieces.BLACK_MAN || pieceType==Pieces.BLACK_KING)) return true;
        if(playerColor.equals("red") && (pieceType==Pieces.RED_MAN || pieceType==Pieces.RED_KING)) return true;
        return false;
    }
    
    private Boolean finalPositionEmpty(){
        if(board[finalPosition]==Pieces.EMPTY) return true;
        return false;
    }
    
    private Boolean isStep(){
        String direction = getDirection();
        int distance = initialPosition-finalPosition;
        if(direction.equals("down") && (distance==3 || distance==4)) return true;
        if(direction.equals("up") && (distance==-4 || distance==-5)) return true;
        return false;
    }
    
    private Boolean isJump(String playerColor){
        int distance = Math.abs(initialPosition-finalPosition);
        String direction = getDirection();
        int pieceType;
        if(distance==7){ //  /
            if(direction.equals("down")) pieceType = board[initialPosition+3];
            else pieceType = board[initialPosition-4];
            
            if(pieceType==Pieces.BLACK_MAN || pieceType==Pieces.BLACK_KING && playerColor.equals("red")) return true;
            if(pieceType==Pieces.RED_MAN || pieceType==Pieces.RED_KING && playerColor.equals("black")) return true;
        }
        if(distance==9){ //  \
            if(direction.equals("down")) pieceType = board[initialPosition+4];
            else pieceType = board[initialPosition-5];
            
            if(pieceType==Pieces.BLACK_MAN || pieceType==Pieces.BLACK_KING && playerColor.equals("red")) return true;
            if(pieceType==Pieces.RED_MAN || pieceType==Pieces.RED_KING && playerColor.equals("black")) return true;
        }
        return false;
    }
    
    private Boolean isValidDirection(){
        int pieceType = board[initialPosition];
        if(pieceType==Pieces.BLACK_MAN && initialPosition < finalPosition && isFirstMove) return false;
        if(pieceType==Pieces.RED_MAN && initialPosition > finalPosition && isFirstMove) return false;
        return true;
    }
    
    private String getDirection(){
        if(initialPosition < finalPosition) return "down";
        return "up";
    }
    
    private Boolean isKingLastMove(int moveNumber, int numberOfMoves){
        if (board[initialPosition]==Pieces.BLACK_MAN 
            && finalPosition<4 
            && moveNumber!=numberOfMoves) return false;
        if (board[initialPosition]==Pieces.RED_MAN 
            && finalPosition>27
            && moveNumber!=numberOfMoves) return false;
        return true;
    }
}*/