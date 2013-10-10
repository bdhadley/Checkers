package checkers;

public class Board {
    protected static int[][] board;
    private static int initialColumn;
    private static int initialRow;
    private static int finalColumn;
    private static int finalRow;
    private static String playerColor;
    
    protected static void initialize(){
        board = new int[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                board[i][j] = ((i+j)%2==0 && i<3)? Pieces.RED_MAN:Pieces.EMPTY;
                if (i>2 && i<5) board[i][j] = Pieces.EMPTY;
                if (i>4) board[i][j] = ((i+j)%2==0)? Pieces.BLACK_MAN:Pieces.EMPTY;
            }
        }
    }
    
    protected static void drawBoard(){
        for(int i=0; i<8; i++){
            System.out.println();
            for(int j=0; j<8; j++){
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
        }
    }
    //might consider null check
    protected static Boolean resolveTurn(int[] moveSequence, String playerColor){
        Board.playerColor = playerColor;

        int[][] savedBoard = new int[8][8];
        for(int i=0; i<8; i++){
            System.arraycopy(board[i], 0, savedBoard[i], 0, 8);
        }
        
        Boolean isFirstMove = true;
        int numberOfMoves = moveSequence.length/2-1;
        for(int i=0; i<moveSequence.length-3; i+=2){
            Board.initialRow = moveSequence[i];
            Board.initialColumn = moveSequence[i+1];
            Board.finalRow = moveSequence[i+2];
            Board.finalColumn = moveSequence[i+3];
            
            if(isValidMove(isFirstMove, i/2+1, numberOfMoves)){
                for(int j=0; j<8; j++){
                    System.arraycopy(savedBoard[j], 0, board[j], 0, 8);
                }
                return false;
            }
            resolveMove();
            isFirstMove = false;
        }
        return true;
    }
    
    private static Boolean isValidMove(Boolean isFirstMove, int moveNumber, int numberOfMoves){
        if(!onBoard()) return false;
        if(!ownsPiece()) return false;
        if(!isFinalPositionEmpty()) return false;
        if(!(isStep()||isJump())) return false;
        if(!isValidDirection(isFirstMove)) return false;
        if(!doesKingContinue(moveNumber, numberOfMoves)) return false;
        return true;
    }
    
    private static Boolean onBoard(){
        int[] positions = {initialRow, finalRow, initialColumn, finalColumn};
        for(int position: positions){
            if (position<0 || position>7) return false;
        }
        return true;
    }
    
    private static Boolean ownsPiece(){
        int pieceType = board[initialRow][initialColumn];
        if(playerColor.equals("black") && (pieceType==Pieces.BLACK_MAN || pieceType==Pieces.BLACK_KING)) return true;
        if(playerColor.equals("red") && (pieceType==Pieces.RED_MAN || pieceType==Pieces.RED_KING)) return true;
        return false;
    }
    
    private static Boolean isFinalPositionEmpty(){
        if(board[finalRow][finalColumn]==Pieces.EMPTY) return true;
        return false;
    }
    
    private static Boolean isStep(){
        int rowDistance = Math.abs(initialRow-finalRow);
        int columnDistance = Math.abs(initialColumn-finalColumn);
        if (rowDistance==1 && columnDistance==1) return true;
        return false;
    }
    
    private static Boolean isJump(){
        int rowDistance = Math.abs(initialRow-finalRow);
        int columnDistance = Math.abs(initialColumn-finalColumn);
        int pieceType;
        if (rowDistance==2 && columnDistance==2){
            if (initialRow-finalRow==2){//going up
                pieceType = (initialColumn-finalColumn==2)? board[initialRow-1][initialColumn-1]:board[initialRow-1][initialColumn+1];
            }
            else {//going down
                pieceType = (initialColumn-finalColumn==2)? board[initialRow+1][initialColumn-1]:board[initialRow+1][initialColumn+1];
            }
            if(pieceType==Pieces.BLACK_MAN || pieceType==Pieces.BLACK_KING && playerColor.equals("red")) return true;
            if(pieceType==Pieces.RED_MAN || pieceType==Pieces.RED_KING && playerColor.equals("black")) return true;
        }
        return false;
    }
    
    private static Boolean isValidDirection(Boolean isFirstMove){
        int pieceType = board[initialRow][initialColumn];
        if(pieceType==Pieces.BLACK_MAN && initialRow < finalRow && isFirstMove) return false;
        if(pieceType==Pieces.RED_MAN && initialRow > finalRow && isFirstMove) return false;
        return true;
    }
    
    private static Boolean doesKingContinue(int moveNumber, int numberOfMoves){
        if (board[initialRow][initialColumn]==Pieces.BLACK_MAN 
            && finalRow==0 
            && moveNumber!=numberOfMoves) return false;
        if (board[initialRow][initialColumn]==Pieces.RED_MAN 
            && finalRow==7
            && moveNumber!=numberOfMoves) return false;
        return true;
    }
    
    private static void resolveMove(){
        movePiece();
        resolveJump();
        kingPieces();
    }

    private static void movePiece(){
        changeSpace(finalRow,finalColumn, board[initialColumn][initialRow]);
        changeSpace(initialRow, initialColumn, Pieces.EMPTY); 
    }
    
    private static void resolveJump(){
        if (initialRow-finalRow==2){//going up
            if (initialColumn-finalColumn==2) removePiece(initialRow-1, initialColumn-1);
            if (initialColumn-finalColumn==-2) removePiece(initialRow-1, initialColumn+1);
        }
        if (initialRow-finalRow==-2){//going down
            if (initialColumn-finalColumn==2) removePiece(initialRow+1, initialColumn-1);
            if (initialColumn-finalColumn==-2) removePiece(initialRow+1, initialColumn+1);
        }
    }
    
    private static void kingPieces(){
        if(finalRow==0 && board[finalRow][finalColumn]==Pieces.BLACK_MAN) changeSpace(finalColumn, finalRow, Pieces.BLACK_KING);
        if(finalRow==7 && board[finalRow][finalColumn]==Pieces.RED_MAN) changeSpace(finalColumn, finalRow, Pieces.RED_KING);
    }
 
    private static void removePiece(int row, int column){
        changeSpace(row, column, Pieces.EMPTY);
    }
    
    private static void changeSpace(int row, int column, int pieceType){
        board[row][column] = pieceType;
    }
}