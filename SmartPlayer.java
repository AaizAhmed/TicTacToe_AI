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
   
   /**
    * Constructor: Takes 2 chars i.e. AI sign and Human sign.
    * 
    * @param   firstSign
    * @param   secondSign
    */
   public SmartPlayer(char firstSign, char secondSign)
   {
      mySign = firstSign;
      opponentSign = secondSign;
   }
   
   /**
    * This method calls other internal methods to find the best move
    * for the given board.
    * 
    * @param   Tic Tac Toe board
    * @return  Row and Col of the best move as an int array.
    */
   public int[] findBestMove(char[][] board)
   {
      int[] bestMove = new int[2];
      bestMove[0] = -1;
      bestMove[1] = -1;
      
      // Give an initial arbitrary low value.
      int bestValue = -1000;
      
      for (int row = 0; row < 3; row++)
      {  for (int col = 0; col < 3; col++)
         {
            // Check if cell is empty
            if (board[row][col] == '-')
            {
               // Make the move.
               board[row][col] = mySign;
                              
               // Use min max method/algorithm to get the value of this move.
               int moveValue = minMax(board, 0, false);
               
               // Undo the move
               board[row][col] = '-';
               
               if (moveValue > bestValue)
               {
                  bestMove[0] = row;
                  bestMove[1] = col;
                  
                  // Since value of this move is larger, make best move 
                  // equals this move.
                  bestValue = moveValue;
               }               
            }            
         }
      }
      
      System.out.println("Value of best move is: " + bestValue);
      return bestMove;
   }
   
   /**
    * Use min max algorithm to return a value.
    * 
    * @param   Tic Tac Toe board
    * @param   Depth of this step
    * @param   isMax ==> a boolean to tell if max is playing or not
    * @return  An int value  
    */
   private int minMax (char[][] board, int depth, boolean isMax)
   {
      int score = evaluate(board, depth);
      
      if (TicTacToe.isOver(board))
      {
//         System.out.println("Score is: " + score);
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
                                 
                  // Pick larger of two numbers.
                  best = Math.max(best, minMax(board, depth++, !isMax));
                  
                  // Undo the move
                  board[row][col] = '-';      
               }            
            }
         }
         
//         System.out.println("Max best: " + best);
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
                                 
                  // Pick smaller of two numbers.
                  best = Math.min(best, minMax(board, depth++, !isMax));
                  
                  // Undo the move
                  board[row][col] = '-';      
               }           
            }
         }
         
//         System.out.println("Min best: " + best);
         return best;
      }
      
   }
   
   /**
    * This method evaluates the given board and returns a score
    * 
    * @param   Tic Tac Toe board
    * @param   Depth of this step
    * @return  An int value
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
    * @param   Tic Tac Toe board
    * @param   Sign of the player
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
   
   //Unit testing
   public static void main (String [] args) 
   {     
      SmartPlayer AI = new SmartPlayer('X', 'O');

      char[][] board =
         {
               { '-', '-', '-' },
               { '-', '-', '-' },
               { '-', '-', '-' }
         };

      int[] bestMove = AI.findBestMove(board);

      System.out.println("The Optimal Move is :");
      System.out.println("ROW: " + bestMove[0] + "\tCOL: " + bestMove[1] );
     
   }
	
}

