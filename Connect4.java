//Connect4.java
//Kiarash Kianidehkordi
//January,31,2021
//The following program will let the user to play a game of connect four and hold their number of wins and loses and 
//ties in a file with their name.
//..................................................................................................................

import java.util.*;
import java.io.*;
import java.lang.*;

//..................................................................................................................
public class Connect4
{
   final int COLUMN=7;
   final int ROW=6;
   public static void main(String[]args)
   {
      
      //*****************************************************************************************************************
      //The following do while will check to find the user file if they are a returning player or either they decide to 
      //start as a new player.
      final int COLUMN=7;
      final int ROW=6;
      
      int playAgain=1;
      int exit=0;
      int winlosetrack;
      int board [ ][ ];
      board=new int [ROW][COLUMN];
      int points [ ];
      points=new int [4];
      
      String name;
      String playerType;
      Scanner sc = new Scanner(System.in);
      //ask user name
      System.out.println("Please enter your first name in lower case letters:");
      name=sc.nextLine();
      //the following do while loop will do all the works with the user profile
      exit=0;
      do
      {
      //the following try catch block will ask the user if they are new or returning player until they answer correctly
         try{
            System.out.println("Are you a new player or a returning player enter n for new player r for returning one :");
            playerType=sc.nextLine();
            winlosetrack=2;
               //the following if will check if player is returning or new player
            if(playerType.equalsIgnoreCase("r"))
            {
               readUserFile(points,name);
               if(points[0]!=0||points[1]!=0||points[2]!=0)
               { 
                  exit=1;
                  printWinLose(points);
               }
            }
            else if(playerType.equalsIgnoreCase("n"))
            {
               points[0]=0;
               points[1]=0;
               points[2]=0;
               updateUserFile(points,name);
               printWinLose(points);
               exit=1;
            }
         }
         catch (InputMismatchException e)
         {
            System.out.println("Bad input :"+e);
         }
         
      }while(exit==0);
      do
      {
         
         //**************************************************************************
         //game starts here
         System.out.println("Your checker is X");
         setupBoard(board);
         printBoard(board);
         //the following do while is player and coputer turn
         do{
            do{
            
               board=playerTurn(board);
               winlosetrack=gameOver(board);
               if(winlosetrack==1)//check if the player won after they pick their column
               {
                  printBoard(board);
                  System.out.println("^^Congrajulations you win^^");
                  points[0]++;
                  updateUserFile(points,name);
                  printWinLose(points);
               }
               else if(winlosetrack==-1)//check if the player lost after they pick their column
               {
                  printBoard(board);
                  System.out.println("^^computer won this match^^");
                  points[1]++;
                  updateUserFile(points,name);
                  printWinLose(points);
               
               }
               else if(winlosetrack==0)//check if thats a tie  after player pick their column
               {
                  printBoard(board);
                  System.out.println("^^thats a tie^^");
                  points[2]++;
                  updateUserFile(points,name);
                  printWinLose(points);
               
               }
               //computer turn will only happen if player had their turn and didnt win 
               else if(winlosetrack==2)
               {
                  computerTurn1(board);
                  winlosetrack=gameOver(board);
                  if(winlosetrack==1)//check if the player won after they pick their column
                  {
                     printBoard(board);
                     System.out.println("^^Congrajulations you win^^");
                     points[0]++;
                     printWinLose(points);
                     updateUserFile(points,name);
                     printWinLose(points);
                  }
                  else if(winlosetrack==-1)//check if the player lost after they pick their column
                  {
                     printBoard(board);
                     System.out.println("^^computer won this match^^");
                     points[1]++;
                     printWinLose(points);
                     updateUserFile(points,name);
                     printWinLose(points);
                  
                  }
                  else if(winlosetrack==2)
                  {
                     printBoard(board);   
                  }
                  else if(winlosetrack==0)//check if thats a tie  after player pick their column
                  {
                     printBoard(board);
                     System.out.println("^^thats a tie^^");
                     points[2]++;
                     printWinLose(points);
                     updateUserFile(points,name);
                     printWinLose(points);
                  
                  }
               
               
               }
            }while(winlosetrack==2);
         //the  following do while will ask the user if they want to play again
            exit=0;
            do{
               try{
                  System.out.println("enter 1 to play again enter 2 to end the game : ");
                  playAgain=sc.nextInt();
                  if(playAgain==1||playAgain==2)
                  {
                     exit=1;
                     winlosetrack=1;
                  }
                  
               }
               catch (InputMismatchException m)
               {
                  System.out.println("bad input " +m);
               }
            }while(exit==0);
         }while(winlosetrack==2);
      
      }while(playAgain==1);
      //game ends here
      System.out.println("..........................................................................................................................");
      System.out.println("Good bye !");      
      
      
   }
   //Methods: 
   //................................................................................................................................
   //---------------------------------------------------------------------------------------------------------------------------------
   //printBoard method:
   //parameter of this method is 2d array of board from type int.
   //The following method will print the board.
   public static void printBoard(int board[][])
   {
      char userChecker='X';
      char computerChecker='O';
      //the following nested loop will print the board
      for(int j=5;j>=0;j--)
      {
         System.out.print("|");
         for(int i=0;i<=6;i++)
         {
            
            if(board[j][i]==1)
            {
               System.out.print(" "+userChecker);
            }
            else if(board[j][i]==-1)
            {
               System.out.print(" "+computerChecker);
            }
            else if(board[j][i]==0)
            {
               System.out.print("  ");
            }
         }
         System.out.print(" |\n");
      }
      System.out.println("| 1 2 3 4 5 6 7 |");
      
   }
   //----------------------------------------------------------------------------------------------------------------------------------
   //setupBoard method 
   //The parameter is the 2d array of board that will be setup to 0 each time we call this method|return the board after emptying it
   //The following method will empty the game board
   public static int[][] setupBoard(int [][] board)
   {
      final int COLUMN=7;
      final int ROW=6;
      for (int i=0;i<=(ROW-1);i++)
      {
         for (int j=0;j<=(COLUMN-1);j++)
         {
            board[i][j]=0;
         }
      }   
   
      return board;
   }   
   //---------------------------------------------------------------------------------------------------------------------------------
   //playerTurn
   //the parameters:the board and the playerC(that is the column they chose to put their checker in)|this method will return the board
   //The following method will update the board when its player turn and they should choose a column to put their checker in
   public static int[][] playerTurn(int [][]board)
   {
      int playerC;
      boolean inputB=true;//input boolean
      int full [ ];
      full=new int [7];//this array will check if the 
      do{
         try{
            Scanner sc = new Scanner(System.in);
            System.out.print("Please enter which column you want to put your checker in : ");
            playerC=sc.nextInt();
            //the following for loop will check if all of the houses of that column are full or not
            for(int i=0;i<=5;i++)
            {
               if(board[i][(playerC-1)]!=0)
               {
                  full[(playerC-1)]+=1;
               }
            }
            //the board will only update if that column is not full
            if(full[(playerC-1)]<7)
            {
            //the following for will update the board if the column that player chosed is not full
               for (int i=0;i<=5;i++)
               {
                  //this if block will end the loop when the checker is in its place
                  if(board[i][(playerC-1)]==0)
                  {
                     board[i][(playerC-1)]=1;
                     inputB=false;
                     break;
                  }
               }
            }
         } 
         //check if player enter column out of bond of 1-7
         //check if player enter wrong data type 
         catch (InputMismatchException e)
         {
            System.out.println("Bad input "+e);
         }
         catch (ArrayIndexOutOfBoundsException g)
         {
            System.out.println(g);
         }
         
      
      }while(inputB==true);
   
      return board;
   
   }
   //-----------------------------------------------------------------------------------------------------------------------------------
   //gameOver method
   //the parameter of this method is the board 2d array and this method will return a number if its 1 then its a win -1 is a lose and 0 is a tie
   //This method will check if the game is over so if the player won lost or if its a tie 
   public static int gameOver(int [][] board)
   {
      int winlost=2;
      int full=1;
      int fourCheckers=0;
      for(int i=0;i<=5;i++)
      {
         for(int j=0;j<=6;j++)
         {
         //This if block will check if there is a 4 checkers in a column horizontaly
            if((i+3)<=5)
            {
               fourCheckers=board[i+1][j]+board[i+2][j]+board[i+3][j]+board[i][j];
               if(fourCheckers==4)
               {
                  winlost=1;
               }
               else if(fourCheckers==-4)
               {
                  winlost=-1;
               }
            
            }
            //This if block will check if there is a 4 checkers in a row verticaly
            if((j+3)<=6)
            {            
               fourCheckers=board[i][j]+board[i][j+1]+board[i][j+2]+board[i][j+3];
               if(fourCheckers==4)
               {
                  winlost=1;
               }
               else if(fourCheckers==-4)
               {
                  winlost=-1;
               }
            
            }
            //This if block will check if there is a 4 checkers in a diagnol
            if((j+3)<=6&&(i+3)<=5)
            {
               fourCheckers=board[i][j]+board[i+1][j+1]+board[i+2][j+2]+board[i+3][j+3];
               if(fourCheckers==4)
               {
                  winlost=1;
               }
               else if(fourCheckers==-4)
               {
                  winlost=-1;
               }
            
            }
            //This if block will check if there is a 4 checkers in a diagnol
            if((j-3)>=0&&(i+3)<=5)
            {
               fourCheckers=board[i][j]+board[i+1][j-1]+board[i+2][j-2]+board[i+3][j-3];
               if(fourCheckers==4)
               {
                  winlost=1;
               }
               else if(fourCheckers==-4)
               {
                  winlost=-1;
               }
            
            }
         }  
      }
      //this means the game didnt have a winer or loser so it will check if the board is full or not
      if(winlost==2)
      {
         for(int i=0;i<=6;i++)
         {
            for(int j=0;j<=5;j++)
            {
               if (board[j][i]!=0)
                  full++;
            }
         }
      }
      //if the game has ended and the board is full then it will return thats a tie
      if(winlost==2 && full==42)
      {
         winlost=0;
      }
      return winlost;
   
   }
   //-----------------------------------------------------------------------------------------------------------------------------------
   //computerTurn1 method
   //The following method will get board as a parameter and return it 
   //The following method is the easy version of the game against computer that will happen after user chose a column and wont win 
   public static int [][] computerTurn1(int [][]board)
   {
      int computerC;
      boolean inputB=true;//input boolean
      int full [ ];
      full=new int [7];//this array will check if the
      int column;
      
       
      do{
         column=columnAi(board);
         if (column==-1)
         {
            computerC = (int)(Math.random() * 7)+1;
         }
         else
         {
            computerC=column+1;
         }
         //the following for loop will check if all of the houses of that column are full or not
         for(int i=0;i<=5;i++)
         {
            if(board[i][(computerC-1)]!=0)
            {
               full[(computerC-1)]+=-1;
            }
         }
         //the board will only update if that column is not full
         if(full[(computerC-1)]<7)
         {
            //the following for will update the board if the column that computer chosed is not full
            for (int i=0;i<=5;i++)
            {
               //this if block will end the loop when the checker is in its place
               if(board[i][(computerC-1)]==0)
               {
                  board[i][(computerC-1)]=-1;
                  i=6;
                  inputB=false;
               }
            }
         }
      } while(inputB==true);
   
      return board;
   }
   //-----------------------------------------------------------------------------------------------------------------------------------
   //printWinLose method
  //this method will read the number of win and lose and ties of the player and print it with the percentage
   //the only parameter of this method is points that is an array holding number of 
   public static void printWinLose(int [ ]points)
   {
      double  count=points[0]+points[1]+points[2];
      if(count==0)
      {
         count=1;
      }
      char per='%';
      System.out.println("Wins :"+points[0]);
      System.out.println("Loses :"+points[1]);
      System.out.println("Ties :"+points[2]);
      System.out.printf("Percentage of wins :%c%.2f",per,((double)(points[0]/count)));
      System.out.printf("%nPercentage of loses :%c%.2f%n",per,((double)(points[1]/count)));
   
   }

   //-----------------------------------------------------------------------------------------------------------------------------------
   //readUserFile method
   //this method will get two parameters the file name and the points then it will read from the name.txt and fill the points and return
   //the points .
   public static int[]  readUserFile(int [] points, String name)
   {
      try{
         String line;
         BufferedReader in = new BufferedReader(new FileReader(name+".txt"));
         line=in.readLine();
         points[0]=Integer.parseInt(line);
         line=in.readLine();
         points[1]=Integer.parseInt(line);
         line=in.readLine();
         points[2]=Integer.parseInt(line);
         in.close();
      }
      catch (IOException p)
      {
         System.out.println("problem opening the file "+p);
         points[0]=0;
         points[1]=0;
         points[2]=0;
      }
      return points;
   }
   //-----------------------------------------------------------------------------------------------------------------------------------
   //The updateUserFile method 
   //this method have two parameter points and name, and it wont return anything 
   //this method will take array points and create a file for user with their name.txt that have the number of win in first line
   //number of loses in second line and number of ties in third line
   public static void updateUserFile(int points[ ], String name)
   {
      try{
         String line;
         BufferedWriter out = new BufferedWriter(new FileWriter(name+".txt",false));
         out.write(points[0]+"\n");
         out.write(points[1]+"\n");
         out.write(points[2]+"\n");
         out.close();
      }
      catch (IOException p)
      {
         System.out.println(p);
         
      }
   
   }
//---------------------------------------------------------------------------------------------------------------------------------------
//columnAi method
//this method will check best columns for computer to drop its checker that will make him win by making 4 checkers in a one column
//this method parameters is  board and it will return column number if its possible to win or -1 if its not possible 
   public static int columnAi(int [][]board)
   {
      int column=-1;
      int colSum;
      //the following for loop will check for possible 4 in a column
      for (int j=0;j<=6;j++)
      {
         for(int i=0;i<=2;i++)
         {
            colSum=board[i][j]+board[i+1][j]+board[i+2][j]+board[i+3][j];
            if(colSum==-3)
            {
               column=j;
               break;
            }
         }
      }
      //the following if will check to block the user move if they have 3 checker in a column
      if(column==-1)
      {
         for (int j=0;j<=6;j++)
         {
            for(int i=0;i<=2;i++)
            {
               colSum=board[i][j]+board[i+1][j]+board[i+2][j]+board[i+3][j];
               if(colSum==3)
               {
                  column=j;
                  break;
               }
            }
         }
      }
      //the following for will check for possible 3 in a column if there is no posssible 4 in a column
      if(column==-1)
      {
         for (int j=0;j<=6;j++)
         {
            for(int i=0;i<=3;i++)
            {
               colSum=board[i][j]+board[i+1][j]+board[i+2][j];
               if(colSum==-2)
               {
                  column=j;
                  break;
               }
            }
         }
      }
      return column;
   
   
   
   }


}