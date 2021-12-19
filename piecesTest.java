import java.util.*;
public class piecesTest {
    final static int ROW = 8;
    final static int COLUMN = 8;
    protected int[][] chessBoard;
    final int[][] resetedBoard = new int[ROW][COLUMN];
 
 
    public piecesTest(int[][] aBoard){
        chessBoard = aBoard;
 
    }// constructor
     
    public static char[] letterToNum(){
        char[] letToNum = new char[8];
        for(int i = 0; i < 8; i++){
             //letToNum[i] = (char) (i + (1-i) + (103-i)); convert inverse [h, g, f, e, d, c, b ,a]
             letToNum[i] = (char) (97+i);
        }
        return letToNum;
    }//method to convert letter to number
 
    public int[][] coordinate(char nameCol, int numRow){
        for(int i = 0; i < ROW; i++){
             if(nameCol == letterToNum()[i]){
                  chessBoard[numRow-1][i] = 1;
             }
        }
        return chessBoard;
    }//method to define coordinate
 
    public int[] coordIndex(char nameCol, int numRow){
        int[] index = new int[2];
        int i;
        for(i = 0; i < ROW; i++){
            if(nameCol == letterToNum()[i]){
                chessBoard[numRow-1][i] = 1;
                index[0] = numRow - 1;
                index[1] = i;
            }
        }
        return index;
    }
    public int[] candidateMove(String coord){
        int tempNum = Integer.parseInt(String.valueOf(coord.charAt(1)));//of character - ASCII value -> return numType
        int[] tempArr = this.coordIndex(coord.charAt(0), tempNum);//return the index of the needed
        return tempArr;
    }
   
   
    // public int[][] pawnPosMove(String coord){ //
    //     int tempNum = Integer.parseInt(String.valueOf(coord.charAt(1)));//of character - ASCII value -> return numType
    //     int[] tempArr = this.coordIndex(coord.charAt(0), tempNum);//return the index of the needed
    //     if(pawnFirstMove == true){
    //         chessBoard[tempArr[0] +1][tempArr[1]] = 1;
    //         chessBoard[tempArr[0] +2][tempArr[1]] = 1;
    //         pawnFirstMove = false;
    //     } else {
    //         chessBoard[tempArr[0] +1][tempArr[1]] = 1;
    //     }
    //     return chessBoard;
    // }//if out of bound??? - i dont know why i consider that
 
 
    public static void main(String [] args){
        final int ROW = 8;
        final int COLUMN = 8;
        int[][] chessBoard = new int[ROW][COLUMN];
        piecesTest obj = new piecesTest(chessBoard);
        System.out.println(Arrays.toString(obj.coordIndex('a', 6)));
        System.out.println(Arrays.toString(letterToNum()));
    }
}
