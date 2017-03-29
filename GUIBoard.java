package TicTacToe_AI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GUIBoard extends JFrame
{
   private JButton btnA1, btnA2, btnA3, btnB1, btnB2, btnB3, btnC1, btnC2, btnC3;

   private JButton signX = new JButton("X");
   private JButton signO = new JButton("O");

   private JTextPane welcomeText = new JTextPane();    
   private JPanel boardPanel = new JPanel();

   private char signHuman, signAI;

   private TicTacToe board;
   private SmartPlayer AI;
   
   private JFrame message = new JFrame();
   private int screenWidth, screenHeight;

   private boolean humanTurn = false;

   public GUIBoard()
   {      
      // This will be used to display messages when game ends
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      screenWidth = (int) screenSize.getWidth() /2;
      screenHeight = (int) screenSize.getHeight() /2;      
      message.setLocation(screenWidth-50, screenHeight-300);
      
      // Set up the grid
      this.setSize(400, 350);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("Tic-Tac-Toe");

      //This will center the JFrame in the middle of the screen
      this.setLocationRelativeTo(null);


      Font font = new Font("Garamond", Font.PLAIN, 24);
      welcomeText.setFont(font);

      String text = "\t\t  Hello!\n"
            + "\tWelcome to Tic Tac Toc!\n"
            + "       Do you want to play as X or O?";

      welcomeText.setText( text );

      this.add(welcomeText, BorderLayout.NORTH);	    

      signX.setName("X");
      signO.setName("O");

      signX.addActionListener(e -> btnSign(e) );
      signO.addActionListener(e -> btnSign(e) );

      JPanel inputPanel = new JPanel();
      inputPanel.add(signX);
      inputPanel.add(signO); 

      this.add(inputPanel);		

      boardPanel.setSize(300,300);
      boardPanel.setLayout(new GridLayout(3,3));

      btnA1 = createButton();
      btnA2 = createButton();
      btnA3 = createButton();
      btnB1 = createButton();
      btnB2 = createButton();
      btnB3 = createButton();
      btnC1 = createButton();
      btnC2 = createButton();
      btnC3 = createButton();

      btnA1.setName("00"); 
      btnA2.setName("01"); 
      btnA3.setName("02"); 
      btnB1.setName("10");
      btnB2.setName("11");
      btnB3.setName("12");
      btnC1.setName("20");
      btnC2.setName("21");
      btnC3.setName("22");

      boardPanel.add(btnA1);
      boardPanel.add(btnA2);
      boardPanel.add(btnA3);
      boardPanel.add(btnB1);
      boardPanel.add(btnB2);
      boardPanel.add(btnB3);
      boardPanel.add(btnC1);
      boardPanel.add(btnC2);
      boardPanel.add(btnC3);

      this.setVisible(true);

      // Start the game
      board = new TicTacToe();
   }

   private void btnSign (ActionEvent e)
   {
      JButton btn = (JButton)e.getSource();
      String sign = btn.getName();

      if (sign.equals("X"))
      {
         signHuman = 'X';
         signAI = 'O';

         humanTurn = true;
      }
      else
      {
         signHuman = 'O';
         signAI = 'X';        
      }    	 

      this.add(boardPanel);

      welcomeText.setVisible(false);
      signX.setVisible(false);
      signO.setVisible(false);

      AI = new SmartPlayer(signAI, signHuman);
      
      if (!humanTurn)
      {
         moveAI();
      }     
   }

   private JButton createButton()
   {
      JButton btn = new JButton();
      btn.setPreferredSize(new Dimension(50, 50));
      Font f = new Font("Dialog", Font.PLAIN, 72);
      btn.setFont(f);
      btn.addActionListener(e -> btnClick(e));
      return btn;
   }
   
   private void moveAI()
   {
      if (!humanTurn)
      {
         int[] rowCol = AI.findBestMove( board.getBoard() );            

         if (rowCol[0] >= 0 && rowCol[1] >= 0)
         {
            board.move(rowCol[0], rowCol[1], signAI);   

            String computerMove = rowCol[0] + "" +rowCol[1];

            //System.out.println(computerMove);

            String sign = Character.toString(signAI);

            switch (computerMove)
            {
               case "00":
                  btnA1.setText(sign);
                  break;
               case "01":
                  btnA2.setText(sign);
                  break;
               case "02":
                  btnA3.setText(sign);
                  break;
               case "10":
                  btnB1.setText(sign);
                  break;
               case "11":
                  btnB2.setText(sign);
                  break;
               case "12":
                  btnB3.setText(sign);
                  break;
               case "20":
                  btnC1.setText(sign);
                  break;
               case "21":
                  btnC2.setText(sign);
                  break;
               case "22":
                  btnC3.setText(sign);
                  break;
            }

            humanTurn = true;

            if (TicTacToe.isWin( board.getBoard() ))
            {               
               message.setVisible(true);             
               JOptionPane.showMessageDialog(message,
                  "I beat you!", "Game Over",
                  JOptionPane.INFORMATION_MESSAGE);               
               message.setVisible(false);
               
               resetGame();
               return;
            }
         }
      }

   }

   private void btnClick(ActionEvent e)
   {
      if (humanTurn)
      {
         JButton btn = (JButton)e.getSource();
         btn.setText( Character.toString(signHuman) );

         int row = Integer.parseInt( btn.getName().substring(0, 1) );
         int col = Integer.parseInt( btn.getName().substring(1, 2) );

         board.move(row, col, signHuman);

         humanTurn = false;

         if (TicTacToe.isWin( board.getBoard() ))
         {
            message.setVisible(true);             
            JOptionPane.showMessageDialog(message,
               "You beat me!", "Game Over",
               JOptionPane.INFORMATION_MESSAGE);               
            message.setVisible(false);

            resetGame();
            return;
         }
      }
      
      moveAI();
      
      if (TicTacToe.isDraw( board.getBoard() ))
      {            
         message.setVisible(true);             
         JOptionPane.showMessageDialog(message,
            "It's a draw!", "Game Over",
            JOptionPane.INFORMATION_MESSAGE);               
         message.setVisible(false);
         
         resetGame();
         return;
      }
   }

   private void resetGame()
   {
      board.reset();
      btnA1.setText("");
      btnA2.setText("");
      btnA3.setText("");
      btnB1.setText("");
      btnB2.setText("");
      btnB3.setText("");
      btnC1.setText("");
      btnC2.setText("");
      btnC3.setText("");
      
      if (signHuman == 'X')
      {
         humanTurn = true;
      }
      else
      {
         humanTurn = false;
         moveAI();
      }
   }
   
   public static void main(String [] args)
   {
      new GUIBoard();
   }
}
