import java.util.*;
public class chess{
     final static int ROW = 8;
     final static int COLUMN = 8;
     private int[][] chessBoard;
     final int[][] resetedBoard = new int[ROW][COLUMN];

     public chess(int[][] aBoard){
          chessBoard = aBoard;
     }// constructor
     
     public static char[] letterToNum(){
          char[] letToNum = new char[8];
          for(int i = 0; i < 8; i++){
               //letToNum[i] = (char) (i + (1-i) + (103-i)); convert inverse [h, g, f, e, d, c, b ,a]
               letToNum[i] = (char) (97+i);//convert to [a,b,c,d,e,f,g,h]
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

     public void printCoordinate(){
          for(int i = chessBoard.length - 1; i >= 0; i--){
               for(int j = 0; j < ROW; j++){
                    System.out.print(chessBoard[i][j] + " ");
               }
               System.out.println();
          }
     }//print the whole board out

     public int[][] resetBoard(){
          for(int i = 0; i < COLUMN; i++){
               for(int j = 0; j < ROW; j++){
                    chessBoard[i][j] = 0;
               }
          }
          return chessBoard;
     }// reset the whole board to 0

     public int[][] UICoordinate(){
          Scanner UICoord = new Scanner(System.in);
          String temp = UICoord.nextLine();
          while (!temp.equals("no")){
               int tempNum = Integer.parseInt(String.valueOf(temp.charAt(1)));//return character's - ASCII value
               this.coordinate(temp.charAt(0), tempNum);//return whole board after the conversion
               this.printCoordinate();
               Scanner newUICoord = new Scanner(System.in);
               temp = newUICoord.next();
          }
          return chessBoard;
     }

     //FIX ME: tmr try to do user input!
     public static void main(String [] args){
          final int ROW = 8;
          final int COLUMN = 8;
          int[][] chessBoard = new int[ROW][COLUMN];//declaring the size of board
          chess board = new chess(chessBoard);
          board.UICoordinate();
          System.out.println(Arrays.deepToString(chessBoard)); 
          
          
          //System.out.println(Arrays.deepToString(chessBoard));
          System.out.println(Arrays.toString(letterToNum()));
          //System.out.println(Arrays.deepToString(board.coordinate('h', 8)));*/
          //board.printCoordinate();
          
     }
     
     
}// end class