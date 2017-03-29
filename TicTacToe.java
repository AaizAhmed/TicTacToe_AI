/**
 * This class represents a Tic Tac Toe table.
 * 
 * @author Aaiz Ahmed <aaiznahmed@gmail.com>
 * @version March 28, 2017
 */
package TicTacToe_AI;

public class TicTacToe {

   private char [][] board;	

   /**
    * Constructor: Instantiates a Tic Tac Toe board.
    */
   public TicTacToe () 
   {
      this.board = new char [3][3];

      for (int row = 0; row < 3; row++)
      {  for (int col = 0; col < 3; col++)
         {
            this.board[row][col] = '-';				
         }			
      }
   }

   /**
    * This method resets the board to play the game again.
    */
   public void reset()
   {
      this.board = new char [3][3];

      for (int row = 0; row < 3; row++)
      {  for (int col = 0; col < 3; col++)
         {
            this.board[row][col] = '-';            
         }        
      }
   }

   /**
    * Copy Constructor
    * @param TicTacToe object to be copied
    */
   public TicTacToe (TicTacToe t) 
   {
      this.board = t.getBoard();
   } 

   /**
    * This method makes a move and add into the board.
    * @param row
    * @param col
    * @return true or false
    */
   public boolean move (int row, int col, char sign) 
   {
      if (this.board[row][col] == '-' ) 
      {
         this.board[row][col] = sign;

         return true;			
      }

      return false;		
   }

   /**
    * This method checks the draw condition
    * 
    * @param   board to be checked
    * @return  true or false
    */
   public static boolean isDraw(char[][] board) 
   {
      for (int row = 0; row < 3; row++)
      {  for (int col = 0; col < 3; col++)
         {
            if (board[row][col] == '-')
            {   return false;   }         
         }
      }

      return true;
   }
   
   /**
    * This method checks the win conditions
    * 
    * @param   board to be checked
    * @return  true or false
    */
   public static boolean isWin(char[][] board)
   {
      for (int index = 0; index < 3; index++)
      {
         // Horizontal Win condition
         if (board[index][0] == board[index][1] && board[index][1] == board[index][2])
         {
            if (board[index][0] != '-' )
            {
               return true;
            }
         }

         // Vertical win condition
         if (board[0][index] == board[1][index] && board[1][index] == board[2][index])
         {
            if (board[0][index] != '-' )
            {
               return true;
            }
         }
      }

      // Checks diagonal win conditions
      if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) 
      { 
         if (board[1][1] != '-')
         {
            return true;
         }
      }

      if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) 
      { 
         if (board[1][1] != '-')
         {
            return true;
         }
      }

      return false;
   }

   /**
    * This method determines if the game is over or not.
    * 
    * @param   board to be checked
    * @return  true or false
    */
   public static boolean isOver(char[][] board) 
   {
      if ( isWin(board) || isDraw(board) ) 
      {
         return true;
      }

      return false;
   }

   /**
    * This method return a copy of the board.
    * 
    * @return  copy of the board 
    */
   public char[][] getBoard () 
   {
      char temp [][] = board;				

      return temp;
   }

   /**
    * This method prints the board/object. 
    */
   public String toString () {

      String str = "";

      for (int row = 0; row < 3; row++)
      {  for (int col = 0; col < 3; col++)
         {
            str += board [row][col];
         }
         str += "\n";
      }
      return str;
   }	

   //Unit testing
   public static void main (String [] args) 
   {		

   }
}
