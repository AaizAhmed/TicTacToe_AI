/**
 * This class contains a SmartPlayer and a Tic Tac Toe object.
 * It manages the game being played. 
 * 
 * @author Aaiz Ahmed <aaiznahmed@gmail.com>
 * @version March 28, 2017
 */
package TicTacToe_AI;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayGame
{
   private static Scanner scan = new Scanner(System.in);
   private static TicTacToe board = new TicTacToe();
   
   private static Pattern p = Pattern.compile("^\\d\\s\\d$");
   private static Matcher m;
   
   /**
    * This method checks if the input given by the user is a number or not
    * @param   input
    * @return  true or false
    */
   private static boolean isNumber(String input)
   {
      m = p.matcher(input);
      return m.matches();
   }
   
   /**
    * This method checks if the column given by the user to make a move is valid.
    * 
    * @return  A valid row and column number as an integer array.
    */
   private static int[] isValid()
   {
      String input = "";
      int[] rowCol = new int[2];

      while(true)
      {                 
         System.out.println ("Enter a valid row and column number between 1 and 3. Seperete the two by a space.");
         input = scan.nextLine();

         if (isNumber(input)) 
         {
            rowCol[0] =  Integer.parseInt(input.substring(0, 1));
            rowCol[1] =  Integer.parseInt(input.substring(2, 3));
            
            return rowCol;
         }
      }     
   }
   
   private static void playGame(SmartPlayer AI, char signAI, char signHuman)
   {
      boolean humanTurn = false;
      
      if (signHuman == 'x')
      {
         humanTurn = true;
      }
      
      for (int moves = 0; moves < 9; moves++)
      {
         if (humanTurn)
         {
            System.out.println("What is your choice?");
            
            int[] rowCol = isValid();
            board.move(rowCol[0], rowCol[1], signHuman);
            humanTurn = false;
            
            System.out.println(board.toString());
            
            if ( TicTacToe.isWin(board.getBoard()) )
            {
               System.out.println("Congratulations!! You won!"); 
               return;
            }
         }
         else
         {
            System.out.println("Computer's Turn");
            
            int[] rowCol = AI.findBestMove( board.getBoard() );            
            board.move(rowCol[0], rowCol[1], signAI);
            System.out.println(board.toString());
            humanTurn = true;
            
            if ( TicTacToe.isWin(board.getBoard()) )
            {
               System.out.println("Computer Won! Try again."); 
               return;
            }
         }
      }
      
      if ( TicTacToe.isDraw(board.getBoard()) )
      {
         System.out.println("It was a draw!");
      }
   }
   
   public static void main(String[] args)
   {
      char signAI, signHuman;
      
      System.out.println ("Welcome to Tic Tac Toe!");      
      
      while (true)
      {
         System.out.println ("Do you want to play as X or O?");
         String input = scan.nextLine();
          
         if (input.equals("x") || input.equals("o"))
         {
            if (input.equals("x"))
            {
               signHuman = 'x';
               signAI = 'o';
            }
            else
            {
               signHuman = 'o';
               signAI = 'x';
            }
            
            break;
         }
      }
          
      SmartPlayer AI = new SmartPlayer(signAI, signHuman);      
      playGame(AI, signAI, signHuman);      
   }

}
