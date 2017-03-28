/**
 * This class represents a tic tac toe table.
 * 
 * @author Aaiz Ahmed <aaiznahmed@gmail.com>
 * @version March 28, 2017
 */
package TicTacToe_AI;

public class TicTacToe {

	private char [][] board;	

	/**
	 * Constructor:
	 */
	public TicTacToe () 
	{
		board = new char [3][3];

		for ( int i = 0; i < 3; i++ ) 
		{
			for (int j = 0; j < 3; j++) 
			{
				board[i][j] = '-';				
			}			
		}
	}

	/**
	 * Copy Constructor
	 * @param t
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
		if (board[row][col] == '-' ) 
		{
		   board[row][col] = sign;
		  
			return true;			
		}
		
		return false;		
	}

	/**
	 * Checks the draw condition
	 * @return true or false
	 */
	public boolean isDraw() 
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
	
	public boolean isWin()
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
	 * Determines if game is over or nor
	 * @return true or false
	 */
   public boolean isOver() 
   {
      if ( isWin() || isDraw() ) 
      {
         return true;
      }

      return false;
   }

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
