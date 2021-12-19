public class pieceObj {
    String pieceName;
    boolean piecePosMove;
    int pieceValue;
    int pieceColor;// -1 = no piece; 0 = white; 1 = black
    boolean turn;
 
    public pieceObj(){
        pieceName = "-";
        piecePosMove = false;
        pieceValue = -1;
        pieceColor = -1;
        turn = false;
    }
 
    public void setPieceName(String name){
        pieceName = name;
    }
    public String getPieceName(){
        return pieceName;
    }
   
    public void setPiecePosMove(boolean piecePosMove){
        this.piecePosMove = piecePosMove;
    }
    public boolean getPiecePosMove(){
         return piecePosMove;
    }
 
    public void setPieceValue(int pieceValue){
        this.pieceValue = pieceValue;
    }
    public int getPieceValue(){
        return pieceValue;
    }

    public void setPieceColor(int pieceColor){
        this.pieceColor = pieceColor;
    }
    public int getPieceColor(){
        return pieceColor;
    }
    
    public void setTurn(boolean turn){
        this.turn = turn;
    }
    public boolean getTurn(){
        return turn;
    }

}


