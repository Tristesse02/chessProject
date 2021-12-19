import java.util.*;
import java.lang.*;
public class readFEN{
        int pawn = 1;
        int rook = 2;
        int knight = 3;
        int queen = 4;
        int king = 5;
       
        String wPawn = "P";
        String wRook = "R";
        String wKnight = "N";
        String wQueen = "Q";
        String wKing = "K";
       
        String bPawn = "p";
        String bRook = "r";
        String bKnight = "n";
        String bQueen = "q";
        String bKing = "k";
 
        protected static pieceObj[][] chessBoard;
        final static int COLUMN = 8;
        final static int ROW = 8;
        public static boolean whiteTurn;
        public static boolean blackTurn;
 
 
    public readFEN(pieceObj[][] chessBoard2){
        chessBoard = chessBoard2;
        for(int i = 0; i < COLUMN; i++){
            for(int j = 0; j < ROW; j++){
                chessBoard[i][j] = new pieceObj();
            }
        }
        whiteTurn = true;
        blackTurn = false;
    }//constructor

//read FEN
    public pieceObj[] logicFEN(String subFEN, pieceObj arr[]){
        int count = 0;
        for(int i = 0; i < subFEN.length(); i++){//Đoạn này chỉ là check từng từ một trong FEN
            if(Character.isDigit(subFEN.charAt(i)) == true){// Đoạn này là check xem trong FEN là số hay chữ
                int numVoidBox = Integer.parseInt(String.valueOf(subFEN.charAt(i)));//return the number!
                int j;
                for(j = 0; j < numVoidBox; j++){//fill in the array
                    arr[j + count].setPieceName("-");
                }
                count += j;
            } else if (!Character.isDigit(subFEN.charAt(i))){
                count += 1;
                arr[count - 1].setPieceName(String.valueOf(subFEN.charAt(i)));
            }
        }
        return arr;
    }//read the single FEN line
   
    public void transFEN(String FEN){
        Scanner scnr = new Scanner(FEN).useDelimiter("[/ ]");// something new
        int i = 7;
        while(scnr.hasNext()){
            if(i >= 0){
                this.logicFEN(scnr.next(), chessBoard[i]);
                i -= 1;
            }
            else {
                String tempStr = scnr.next();
                if(tempStr.equals("w")){
                    System.out.println("white turns");
                    whiteTurn();
                } else if(tempStr.equals("b")){
                    System.out.println("black turns");
                    blackTurn();
                } else {
                    System.out.println("FIX LATER");
                }
            }
           
        }
        scnr.close();
    }
    //supporting method from chess.java

    public static char[] letterToNum(){
        char[] letToNum = new char[8];
        for(int i = 0; i < 8; i++){
            letToNum[i] = (char) (97+i);
        }
        return letToNum;//convert to [a,b,c,d,e,f,g,h]
    }// method to define coordinate

    public static int eachLetterToIndex(char aLetter){
        int tempNum = aLetter - 97;
        return tempNum;
    }//convert a letter on the board to its index

    public static int charToInt(char aLetter){
        int tempNum = Integer.parseInt(String.valueOf(aLetter));
        return tempNum;
    }//convert a number type char to int 
//defining piece color
    public static void determineColor(){
        for(int i = 0; i < COLUMN; i++){
            for(int j = 0; j < ROW; j++){
                if(!Character.isLetter(chessBoard[i][j].getPieceName().charAt(0))){
                    chessBoard[i][j].setPieceColor(-1);
                } else if(Character.isUpperCase(chessBoard[i][j].getPieceName().charAt(0))){
                    chessBoard[i][j].setPieceColor(0);
                } else if (Character.isUpperCase(chessBoard[i][j].getPieceName().charAt(0))){
                    chessBoard[i][j].setPieceColor(1);
                }
            }
        }
    }

//playing turn    
    public static void turn(){
        whiteTurn = !whiteTurn;
        blackTurn = !blackTurn;
    }//turn change every round

    public static void whiteTurn(){
        whiteTurn = true;
        blackTurn = !whiteTurn;
    }

    public static void blackTurn(){
        blackTurn = true;
        whiteTurn = !blackTurn;
    }

    public static void setWhiteTurn(){
        for(int j = 0; j < COLUMN; j++){
            for(int k = 0; k < ROW; k++){
                if(Character.isUpperCase(chessBoard[j][k].getPieceName().charAt(0))){
                    chessBoard[j][k].setTurn(true);
                    chessBoard[j][k].setPieceColor(0);
                }
            }
        }
    }
    
    public static void setBlackTurn(){
        for(int j = 0; j < COLUMN; j++){
            for(int k = 0; k < ROW; k++){
                if(Character.isLowerCase(chessBoard[j][k].getPieceName().charAt(0))){
                    chessBoard[j][k].setTurn(true);
                    chessBoard[j][k].setPieceColor(1);
                }
            }
        }
    }

//printing coordinate
    public static void printCoordinate(){
        for(int i = chessBoard.length - 1; i >= 0; i--){
             for(int j = 0; j < ROW; j++){
                  System.out.print(chessBoard[i][j].getPieceName() + " ");
             }
             System.out.println();
        }
        System.out.println();
    }//print the whole board out
 
    //methods from pieceTest
    public int[] coordIndex(int numRow, char nameCol){//a8 -> [7,0]
        int[] index = new int[2];
        int i;
        for(i = 0; i < ROW; i++){
            if(nameCol == letterToNum()[i]){
                index[0] = numRow - 1;
                index[1] = i;
            }
        }
        return index;
    }//return location of coordinate through index of the array
 
    public int[] candidateMove(String coord){
        if(coord.length() == 2){
            int tempNum = Integer.parseInt(String.valueOf(coord.charAt(1)));//of character - ASCII value -> return numType
            int[] tempArr = this.coordIndex(tempNum, coord.charAt(0));//return the index of the needed
            return tempArr;
        } else if(coord.length() == 4 && coord.charAt(1) == 'x'){
            int tempNum = Integer.parseInt(String.valueOf(coord.charAt(3)));
            int[] tempArr = this.coordIndex(tempNum, coord.charAt(2));
            return tempArr;
        }
        return null;
    }
    //what if pieces blocking eachother!!!!!!!!
   
    public static void main(String [] args){
        final int ROW = 8;
        final int COLUMN = 8;
        pieceObj[][] chessBoard = new pieceObj[ROW][COLUMN];
        readFEN test = new readFEN(chessBoard);
        // pieceObj[] board = new pieceObj[8];
        // pieceObj anObj = new pieceObj();
        // for(int i = 0; i < COLUMN; i++){
        //     board[i] = anObj;
        // }
 
        //     //READ FROM HERE PLS
        //     test.logicFEN("1r2qr1K", board);
        //     for(int j = 0; j < ROW; j++){
        //         System.out.println(board[j].getPieceName());
        //     }
 
        test.transFEN("r1b1r1k1/pp3ppp/1b1p1q2/2p1N3/2P5/2N1P3/PPB2PPP/R2Q1RK1 b - - 0 13");
        System.out.println(Arrays.toString(test.coordIndex(8, 'a')));
        System.out.println(Arrays.toString(test.candidateMove("a4")));
        //System.out.println(Arrays.deepToString(chessBoard));
        // movePieces("p", "c5");
        printCoordinate();
 
 
    }
}

