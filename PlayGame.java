/**
 * This class contains a SmartPlayer and a Tic Tac Toe object.
 * It manages the game being played. 
 * 
 * @author Aaiz Ahmed <aaiznahmed@gmail.com>
 * @version March 28, 2017
 */
package TicTacToe_AI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
      char signAI = '-';
      char signHuman = '-';
      
      JButton buttons[][] = new JButton[3][3];
      
      JFrame guiFrame = new JFrame("Tic Tac Toa");
      
      //make sure the program exits when the frame closes
      guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
      
      JTextPane welcomeText = new JTextPane();        
      Font font = new Font("Garamond", Font.PLAIN, 24);
      welcomeText.setFont(font);

      String text = "\t\t  Hello!\n"
            + "\tWelcome to Tic Tac Toc!\n"
            + "       Do you want to play as X or O?";
      
      welcomeText.setText( text );
             
      guiFrame.add(welcomeText, BorderLayout.NORTH);
      
      JButton signX = new JButton("X");
      JButton signO = new JButton("O");
      
      JPanel inputPanel = new JPanel();
      inputPanel.add(signX);
      inputPanel.add(signO); 
      
      guiFrame.add(inputPanel, BorderLayout.CENTER);        
      
      // Creating a panel with a box like a tic tac toe board.
      JPanel boardPanel = new JPanel(); 
      boardPanel.setLayout (new GridLayout (3, 3));
      boardPanel.setBorder (BorderFactory.createLineBorder (Color.gray, 3));
      boardPanel.setBackground (Color.white);
                     
      //This will center the JFrame in the middle of the screen
      guiFrame.setLocationRelativeTo(null);
      
      guiFrame.pack();
      guiFrame.setVisible(true);
      guiFrame.setSize(400, 350);    
      

      ActionListener listener = new ActionListener() 
      {
         char signOne, signTwo;
         
         public void setSignAI (char sign)
         {
            signOne = sign;
         }
         
         public char getsignAI ()
         {
            return signOne;
         }
         
         @Override
         public void actionPerformed(ActionEvent e) 
         {
             if (e.getSource() instanceof JButton) 
             {
                 System.out.println( ((JButton) e.getSource()).getName() );
                 
                 if ( signOne == 'x')
                 {
                    ((JButton) e.getSource()).setText("X");
                 }                 
             }
         }
     };
     
     for (int row = 0; row < 3; row++)
     {  for (int col = 0; col < 3; col++)
        {
           // Placing the button onto the board
           buttons[row][col] = new JButton();
           buttons[row][col].setName(row+ " " + col);
           buttons[row][col].addActionListener(listener);
           
           boardPanel.add(buttons[row][col]);
        }
     }
      
      signX.addActionListener(new ActionListener()
      {
          @Override
          public void actionPerformed(ActionEvent event)
          {
             signHuman = 'x';
             signAI = 'o';
             
             guiFrame.add(boardPanel);            
             
             welcomeText.setVisible(false);
             signX.setVisible(false);
             signO.setVisible(false);
          }
      });
      
      signO.addActionListener(new ActionListener()
      {
          @Override
          public void actionPerformed(ActionEvent event)
          {
             System.out.println("O was selected!");
             
             guiFrame.add(boardPanel, BorderLayout.CENTER);
             
             welcomeText.setVisible(false);
             signX.setVisible(false);
             signO.setVisible(false);
          }
      });
      
      
      
      
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
