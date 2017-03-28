/**
 * This class plays the tic tac toe game in a smart way. 
 * 
 * @author Aaiz Ahmed <aaiznahmed@gmail.com>
 * @version March 28, 2017
 */
package TicTacToe_AI;

public class SmartPlayer
{
   private char mySign, opponentSign;
   
   public SmartPlayer(char firstSign, char secondSign)
   {
      mySign = firstSign;
      opponentSign = secondSign;
   }
   
   public int[] findBestMove(char[][] board)
   {
      int[] bestMove = new int[2];
      bestMove[0] = -1;
      bestMove[1] = -1;
      
      int bestValue = -1000;
      
      for (int row = 0; row < 3; row++)
      {  for (int col = 0; col < 3; col++)
         {
            // Check if cell is empty
            if (board[row][col] == '-')
            {
               // Make the move.
               board[row][col] = mySign;
                              
               int moveValue = minMax(board, 0, false);
               
               // Undo the move
               board[row][col] = '-';
               
               if (moveValue > bestValue)
               {
                  bestMove[0] = row;
                  bestMove[1] = col;
                  
                  // Since value of this move is larger make best move 
                  // equals this move.
                  bestValue = moveValue;
               }               
            }            
         }
      }
      
      System.out.println("Value of best move is: " + bestValue);
      return bestMove;
   }
   
   private int minMax (char[][] board, int depth, boolean isMax)
   {
      int score = evaluate(board, depth);
      
      if (TicTacToe.isOver(board))
      {
         return score;
      }
      
      if (isMax)
      {
         int best = -1000;
         
         for (int row = 0; row < 3; row++)
         {  for (int col = 0; col < 3; col++)
            {
               // Check if cell is empty
               if (board[row][col] == '-')
               {
                  // Make the move.
                  board[row][col] = mySign;
                                 
                  best = Math.max(best, minMax(board, depth++, !isMax));
                  
                  // Undo the move
                  board[row][col] = '-';      
               }            
            }
         }
         
         return best;
      }
      else
      {
         int best = 1000;
         
         for (int row = 0; row < 3; row++)
         {  for (int col = 0; col < 3; col++)
            {
               // Check if cell is empty
               if (board[row][col] == '-')
               {
                  // Make the move.
                  board[row][col] = opponentSign;
                                 
                  best = Math.min(best, minMax(board, depth++, !isMax));
                  
                  // Undo the move
                  board[row][col] = '-';      
               }           
            }
         }
         
         return best;
      }
      
   }
   
   /**
    * This method evaluates the current board and return a score
    * @param board
    * @param depth
    * @return
    */
   private int evaluate(char[][] board, int depth)
   {
      if (isWin(board, mySign))
      {
         return 10 - depth;
      }
      else if (isWin(board, opponentSign))
      {
         return depth - 10;
      }
      else
      {
         return 0;
      }
   }
   
   /**
    * This method checks if the given player has won the game.
    * 
    * @param   board
    * @param   sign
    * @return  true or false
    */
   public boolean isWin(char[][] board, char sign)
   {
      for (int index = 0; index < 3; index++)
      {
         // Horizontal Win condition
         if (board[index][0] == board[index][1] && board[index][1] == board[index][2])
         {
            if (board[index][0] == sign)
            {
               return true;
            }
         }
         
         // Vertical win condition
         if (board[0][index] == board[1][index] && board[1][index] == board[2][index])
         {
            if (board[0][index] == sign)
            {
               return true;
            }
         }
      }
      
      // Checks diagonal win conditions
      if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) 
      { 
         if (board[1][1] == sign)
         {
            return true;
         }
      }
      
      if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) 
      { 
         if (board[1][1] == sign)
         {
            return true;
         }
      }
      
      return false;
   }
	
}

