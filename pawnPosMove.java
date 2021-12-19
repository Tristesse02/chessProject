public class pawnPosMove extends readFEN{
    protected boolean pawnFirstMove;
    protected boolean existPawn;

    public pawnPosMove(pieceObj[][] chessBoard2) {
        super(chessBoard2);
        //TODO Auto-generated constructor stub
        pawnFirstMove = true;
        existPawn = false;
    }
    
    public void checkPawnFirstMove(){
        for(int i = 0; i < ROW; i++){
            if(chessBoard[1][i].getPieceName() == "P"){
                chessBoard[2][i].setPiecePosMove(true);
                chessBoard[3][i].setPiecePosMove(true);
            }
            if(chessBoard[6][i].getPieceName() == "p"){
                chessBoard[5][i].setPiecePosMove(true);
                chessBoard[4][i].setPiecePosMove(true);
            }
        }
    }

    /*
    protocol:
    1.select the square that the piece currently is
    2.check if there is a piece or not
    3.if there is, check their possible move
    */

    //supporting methods
    public void replace(pieceObj charisticOrig, pieceObj charisticNew){
        charisticNew.setPieceName(charisticOrig.getPieceName());
        charisticNew.setPiecePosMove(charisticOrig.getPiecePosMove());
        charisticNew.setPieceValue(charisticOrig.getPieceValue());
    }

//move pawn
    public pieceObj[][] pawnPossibleMove(int[] coordIndex){//ex: a8 [7,0] reverse index!
        if(chessBoard[coordIndex[0]][coordIndex[1]].getPieceName().equals("P") == true){//check existence of whitePawn
            existPawn = true;
            if(coordIndex[0] == 1){//for white pawns
                chessBoard[coordIndex[0] + 1][coordIndex[1]].setPiecePosMove(true);
                chessBoard[coordIndex[0] + 2][coordIndex[1]].setPiecePosMove(true);
            } else if(coordIndex[0] > 1 && coordIndex[0] < 6){
                chessBoard[coordIndex[0] + 1][coordIndex[1]].setPiecePosMove(true);
            } else if(coordIndex[0] == 6){
                chessBoard[coordIndex[0] + 1][coordIndex[1]].setPiecePosMove(true);
                System.out.println("Queen, Knight, Bishop, or rook?");
            } else {
                System.out.println("not feasible for piece to stand there!");
                return chessBoard;
            }
        } //white pawns

        if(chessBoard[coordIndex[0]][coordIndex[1]].getPieceName().equals("p") == true){//check existence of whitePawn
            if(coordIndex[0] == 6){//for black pawns
                chessBoard[coordIndex[0] - 1][coordIndex[1]].setPiecePosMove(true);
                chessBoard[coordIndex[0] - 2][coordIndex[1]].setPiecePosMove(true);
            } else if(coordIndex[0] < 6 && coordIndex[0] > 1){
                chessBoard[coordIndex[0] - 1][coordIndex[1]].setPiecePosMove(true);
            } else if(coordIndex[0] == 1){
                chessBoard[coordIndex[0] - 1][coordIndex[1]].setPiecePosMove(true);
                System.out.println("Queen, Knight, Bishop, or rook?");
            } else {
                System.out.println("not feasible for piece to stand there!");
                return chessBoard;
            }
        } 
        return chessBoard;
    }

    public void pawnMove(String coord){//check the whole column to find if there is pawn - exceptional: tốt chồng!
        int[] index = super.candidateMove(coord);//target square
        for(int i = 0; i < COLUMN; i++){//run the loop then meet P twice!
            int[] tempArr = new int[2];
            tempArr[0] = i;
            tempArr[1] = index[1];
            this.pawnPossibleMove(tempArr);
        }
        if(existPawn == false){
            System.out.println("There is no pawn on " + String.valueOf((char) (index[1] + 97)) + "-file");
            return;
        }
        if(existPawn == true && !chessBoard[index[0]][index[1]].getPiecePosMove()){
            System.out.println("Move not feasible!");
            existPawn = false;// like a switch, u turn on then u have to turn off
            return;
        } 
        if(chessBoard[index[0]][index[1]].getPiecePosMove()){ //making the pawn move
            for(int i = index[0] - 1; i > index[0] - 3; i--){
                if(chessBoard[i][index[1]].getPieceName().equals("P")){
                    this.replace(chessBoard[i][index[1]], chessBoard[index[0]][index[1]]);
                    chessBoard[i][index[1]] = new pieceObj();
                    existPawn = false;// like a switch, u turn on then u have to turn off
                    break;
                }
            }
        }
        
        for(int j = 0; j < COLUMN; j++){
            chessBoard[j][index[1]].setPiecePosMove(false);
        }
    } 
//end Moving methods

    

//capturing! 
//- must know whose turn is it!!!
/*PROTOCOL:
check the target square first
from the target square, set the possible original square to true
if its real true, capture!.
*/
    public pieceObj[][] checkPosCapture(int[] coordIndex){//target square
        if(!chessBoard[coordIndex[0]][coordIndex[1]].getPieceName().equals("-")){
            chessBoard[coordIndex[0] + 1][coordIndex[1] + 1].setPiecePosMove(true);
        } else if(!chessBoard[coordIndex[0] + 1][coordIndex[1] - 1].getPieceName().equals("-")){
            chessBoard[coordIndex[0] + 1][coordIndex[1] - 1].setPiecePosMove(true);
        }
        
        if(chessBoard[coordIndex[0]][coordIndex[1]].getPieceName().equals("P") == true){
            if(!chessBoard[coordIndex[0] - 1][coordIndex[1] - 1].getPieceName().equals("-")){
                chessBoard[coordIndex[0] - 1][coordIndex[1] - 1].setPiecePosMove(true);
            } else if(!chessBoard[coordIndex[0] - 1][coordIndex[1] + 1].getPieceName().equals("-")){
                chessBoard[coordIndex[0] - 1][coordIndex[1] + 1].setPiecePosMove(true);
            } else {
                System.out.println("capture not feasible!");
            }
        }
        return chessBoard;

        // if(!chessBoard[coordIndex[0]][coordIndex[1]].getPieceName().equals("-")){

        // } FIX ME!!!
    }

    public void capture(String coord){//target square
        int[] index = super.candidateMove(coord);
        int indexOrg = eachLetterToIndex(coord.charAt(0));
        int indexCOLUMN = charToInt(coord.charAt(3));
        for(int i = 0; i < COLUMN; i++){//run the loop then meet P twice!
            int[] tempArr = new int[2];
            tempArr[0] = i;
            tempArr[1] = index[1];
            this.checkPosCapture(tempArr);
        }
        if(chessBoard[indexCOLUMN - 1][indexOrg].getPiecePosMove() == true && chessBoard[indexCOLUMN - 1][indexOrg].getPieceName().equals("P")){
            replace(chessBoard[indexCOLUMN - 1][indexOrg], chessBoard[index[1]][index[0]]);
            chessBoard[indexCOLUMN - 1][indexOrg] = new pieceObj();
        } else {
            System.out.println("there is no pawn there");
        }
        
        // for(int j = 0; j < COLUMN; j++){
        //     chessBoard[j][index[1]].setPiecePosMove(false);
        // }        
    }
//end capturing method

    //case: only 1 pawn!
    public static void printPosMoveCoord(){
        for(int i = chessBoard.length - 1; i >= 0; i--){
            for(int j = 0; j < ROW; j++){
                System.out.print(chessBoard[i][j].getPiecePosMove() + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        final int ROW = 8;
        final int COLUMN = 8;
        pieceObj[][] chessBoard = new pieceObj[ROW][COLUMN];
        pawnPosMove test = new pawnPosMove(chessBoard);
        test.transFEN("8/8/8/5p2/4P3/8/8/8 w - - 0 1");
        printCoordinate();
        test.capture("exf5");
        printPosMoveCoord();
        printCoordinate();
        // printPosMoveCoord();
        // System.out.println(chessBoard[1][3].getPieceName());
    }
}

