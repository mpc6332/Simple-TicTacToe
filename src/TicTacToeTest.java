import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
*  This program has the user compete in
*  a match of tic tac toe by a computer.
*  After each round is over, the user
*  has the option to either terminate
*  the game or continue for as long as
*  they want while also keeping score.
*/
public class TicTacToeTest
{
   /**
   * Establishes User's placements in the form of an array list. Their placements
   * are recorded in an array list to check be for the game's winning conditions later on.
   */ 
   static ArrayList<Integer> userSlots = new ArrayList<>();
   
   /**
   * Establishes CPU's placements in the form of An array list. Its placements
   * are recorded in an array list to check be for the game's winning conditions later on.
   */ 
   static ArrayList<Integer> cpuSlots = new ArrayList<>();
   
   /**
   * The core of the code. Sets up the gameboard board and cycles
   * through each party's turn. Checks the winning conditions
   * after each placement to see if an outcome was decided. If
   * there is, the board shows the results and gives the option
   * to the user to either terminate the game or keep playing.
   * @param args default main string parameters
   */   
   public static void main(String [] args)
   
   {  
      // Sets up each party's score
      int playerScore = 0;
      int cpuScore = 0;
      
      // Sets up gameboard with a 2D array 
      char [][] gameBoard = {{' ', '|', ' ', '|' , ' '}, 
                             {'-', '+', '-', '+' , '-'}, 
                             {' ', '|', ' ', '|' , ' '}, 
                             {'-', '+', '-', '+' , '-'},
                             {' ', '|', ' ', '|' , ' '}};
      
      // Welcomes the player into the game along with displaying the instructions and current score
      System.out.println("Welcome to tic-tac-toe!");
      System.out.println("Instructions: Here are the number placements that correspond to the position on the game board:");
      System.out.println("[1], [2], [3]\n[4], [5], [6]\n[7], [8], [9]\n");
      System.out.print("Player Score: " + playerScore + "\tCPU Score: " + cpuScore + "\n");
      
      // Calls the printGameBoard method to print out the gameboard
      printGameBoard(gameBoard);

      /*
      * While loop starts here. It won't stop until the player decides they don't
      * want to play anymore by inputting N or n after a round is over. This would
      * cause a break statement to trigger to get the loop to stop.
      */
      while(true)
      {
      
      // Allows the user to input anything when prompted to within parts of the program.
      Scanner keyboard = new Scanner(System.in); 
      
      /*
      *  Prompts the user to pick out any number from 1 to 9 for placement. Number placement goes like this in accordance to the board:
      *  Top Row:      [1], [2], [3]
      *  Middle Row:   [4], [5], [6]
      *  Bottom Row:   [7], [8], [9]
      *  Then, the program repeatedly prompts the user until they input a number that isn't already taken up by themselves or the cpu.
      */     
      System.out.println("Player's turn! Please enter placement between 1 - 9");
      
      /*
      *  After the program asks the user for input, the user puts in a number in that specific range.
      *  Then it is checked through the playerMarkCheck method to see if the number is actually in the
      *  range. Not to mention, it also checks if the user's attempted placement is already on a spot
      *  taken by the user themselves or the CPU. After all conditions are cleared, a result is sent
      *  back to the main that is assigned to the user's placement.
      */
      int userPlacement = keyboard.nextInt();
      userPlacement = playerMarkCheck(userPlacement);
      
      
         //Calls moveTurn to print out the player's mark after they made an input.
         moveTurn(gameBoard, userPlacement, "player");
         
         //Prints the updated game board with the gameBoard method, which shows the player's latest successful input.
         printGameBoard(gameBoard); 
          
         /*
         * For the player's end, the if loop checks to see if the player had won after they had made their latest placement.
         * The winnerCondition method is called and its return value is placed within a variable. After the returned value of
         * the winnerCondition method is printed out, it will check to see if the initial return value "" was changed. If the
         * return method's value is returned as "", meaning that no outcome was decided, then the if statement is bypassed and
         * the game resumes as normal. However, If the return value is not "", meaning  that an outcome was determined, then it
         * will ask the user if they want to continue the game. Before continuing a new game, the program adds a 1 to either
         * of the player's or CPU's score depending on the returning winning condition.
         */
         String result1 = winnerCondition();
         System.out.print(result1);
         
         if (!result1.equals(""))
         {
            if (result1.equals("You won!"))
            {
            playerScore++;
            }
            else if (result1.equals("CPU won!"))
            {
            cpuScore++;
            }
         System.out.println("\nWould you like to play again? (Y / y for yes or N / n for No) ");
        
         /*
         * After an outcome in the round was decided, it asks the user if they want to play again through the
         * resetGameBoard method, which resets the board when a player wants to play a new round. If replied
         * with N or n, the method will send back a string, "Gamestop", to break the gameloop, which stops
         * the game and calls out a method that prints of the scores and determines who won the most amount
         * of rounds. If replied with Y or y, it will send back a string, "GameContinue", alongside with
         * resetting the board that will start a new round with the player going first.
         *
         */
            String resetResponse1 = resetGameBoard(gameBoard);
            if (resetResponse1.equals("GameStop"))
            {
            programEndResults(playerScore, cpuScore);
            break;
            }
            
            else if (resetResponse1.equals("GameContinue"))
            {
            System.out.print("Player Score: " + playerScore + "\tCPU Score: " + cpuScore + "\n");
            printGameBoard(gameBoard);
            System.out.println("Player's turn! Please enter placement between 1 - 9");
            userPlacement = keyboard.nextInt();
            userPlacement = playerMarkCheck(userPlacement);
            moveTurn(gameBoard, userPlacement, "player");

            }
           }

      
       
      /*
      *  Calls the getRandomNumber method, which will return a random number calculated
      *  by the method for a slot that the CPU can potentially take up. Likewise with
      *  the player, the program will repeatedly loop until it generate a
      *  number slot that isn't already taken up by the CPU itself and
      *  the player. 
      */
         System.out.println("CPU's turn!");
         int cpuPlacement = getRandomNumber();
         while(userSlots.contains(cpuPlacement) || cpuSlots.contains(cpuPlacement))
         {
         cpuPlacement = getRandomNumber();
         }
       
       //Calls moveTurn to print out the CPU's mark after it successfully took up a slot.
       moveTurn(gameBoard, cpuPlacement, "CPU");
      
       //Prints the updated game board with the gameBoard method, which shows the CPU's latest successful input.
       printGameBoard(gameBoard); 
         
         
         /*
         * For the CPU's end, the if loop checks to see if the CPU had won after it made its latest placement.
         * The winnerCondition method is called and its return value is placed within a variable. After the returned value of
         * the winnerCondition method is printed out, it will check to see if the initial return value "" was changed. If the
         * return method's value is returned as "", meaning that no outcome was decided, then the if statement is bypassed and
         * the game resumes as normal. However, If the return value is not "", meaning  that an outcome was determined, then it
         * will ask the user if they want to continue the game. Before continuing a new game, the program adds a 1 to either
         * of the player's or CPU's score depending on the returning winning condition.
         */
         String result2 = winnerCondition();
         System.out.print(result2);
            if (!result2.equals(""))
            {
               if (result2.equals("You won!"))
               {
               playerScore++;
               }
               else if (result2.equals("CPU won!"))
               {
               cpuScore++;
               }
            System.out.println("\nWould you like to play again? (Y / y for yes or N / n for No) ");
            String resetResponse2 = resetGameBoard(gameBoard);
         
         /*
         * After an outcome in the round was decided, it asks the user if they want to play again through the
         * resetGameBoard method, which resets the board when a player wants to play a new round. If replied
         * with N or n, the method will send back a string, "Gamestop", to break the gameloop, which stops
         * the game and calls out a method that prints of the scores and determines who won the most amount
         * of rounds. If replied with Y or y, it will send back a string, "GameContinue", alongside with
         * resetting the board that will start a new round with the player going first. No need for extra
         * code to be in the "GameContinue" section as the program loops to the beginning of the while
         * true loop, which segways into the player's turn anyways (We wanted the player to go first
         * regardless of the outcome). This is possible as the breakstatement wasn't activated yet.
         */            
               if (resetResponse2.equals("GameStop"))
               {
               programEndResults(playerScore, cpuScore);
               break;
               }
               if (resetResponse2.equals("GameContinue"))
               {
               System.out.print("Player Score: " + playerScore + "\tCPU Score: " + cpuScore + "\n");
               printGameBoard(gameBoard);
               }

         
         }

      }
   }
   //End of the main method. Below are the outside methods called from within the main method
      
   
   /**
   * This method compares the player's and cpu's score to determine
   * who has won the most amount of rounds. After printing out the final
   * scores, the program compares the two parameters to make a determination
   * that would be printed out. Although the method accepts arguments, it
   * doesn't return anything.
   * @param playerScore The player's score count
   * @param cpuScore The CPU's score count
   */
   public static void programEndResults(int playerScore, int cpuScore)
   {
   System.out.print("Final player Score: " + playerScore + "\tFinal CPU Score: " + cpuScore + "\n");
   if (playerScore > cpuScore)
      System.out.println("The player has won the most amount of rounds! You win!");
   else if (playerScore < cpuScore)
      System.out.println("The CPU has won the most amount of rounds! You lose!");
   else if (playerScore == cpuScore)
      System.out.println("Each party has won an equal amount of rounds! It's a tie!");
   
   }
   
   
   
   
   /**
   *  After the program asks the user for input, the user puts in a number in that specific range from
   *  the main. Then it is passed on as a parameter to the playerMarkCheck method to see if it is actually
   *  within the range. If the number isn't within that range, the program will loop itself until the user
   *  does that. Afterwards, it which is then checked via while loop if the user's placement 
   *  was on a spot previously taken up by either the player themselves or the cpu. If those
   *  spots were previously taken up, the user is asked to to make another input. The input
   *  is forced to go through a range check again, via a nested while loop, before it can be checked to see if it is
   *  attempting to place a mark on a previously taken up spot. The loop continues until the user 
   *  takes a spot that isn't taken by either of the two parties. It then returns the integer back
   *  to the main for that number representing a slot on the board to be printed out.
   *  @param userPlacement the user's number placement from the main method
   *  @return the user's placement after going through the necessary checks
   */
   public static int playerMarkCheck(int userPlacement)
   {
   Scanner keyboard = new Scanner(System.in);
   while ((userPlacement < 1) || (userPlacement > 9))
   {
   System.out.println("Please only type in any number within the 1 - 9 range");
   userPlacement = keyboard.nextInt();
   }
   while(userSlots.contains(userPlacement) || cpuSlots.contains(userPlacement))
   {
   System.out.println("Spot taken! Try again");
   userPlacement = keyboard.nextInt();
      while ((userPlacement < 1) || (userPlacement > 9))
      {
      System.out.println("Please only type in any number within the 1 - 9 range");
      userPlacement = keyboard.nextInt();
      }

    }
    return userPlacement;
    }
   
    /**
    * The code that determines the CPU's number placement
    * that is to be passed back to the main. It is determined
    * by an equation that multiplies the value of Math.Random
    * , which could be from anywhere between 0 and 1, by the range
    * of the acceptable input range. Then, it is added by the minimum
    * number you can put in the range. Afterwards, the result is sent
    * back to the main. 
    */
    public static int getRandomNumber()
    {
    int min = 1;
    int max = 9;
    return (int) Math.round(((Math.random() * (max - min)) + min));
    }

   
   
   
    /**
    * This method prints out the Game board layout. First, it sets the outer
    * for loop to run for the amount of rows in the double array, which is five
    * in this case. Then it transitions to the nested loop, which prints out all of 
    * the symbols within the row (which is 5 symbols for each row). Then, it 
    * transitions to the outer loop to print out a blank line for the next set of
    * symbols to be printed on. This process is repeated until it is done printing
    * out all of the rows. No return value is present since it is a void method.
    * @param gameBoard The 2D array game board
    */
    public static void printGameBoard ( char[][] gameBoard)
    {

     for(char [] row : gameBoard) 
       {
         for(char columns : row)
         {
         System.out.print(columns);
         }     
         System.out.println();
       }
    }
    
    
    /**
    * This method places a player's mark on a gameBoard. It checks to see if the method
    * being called is after a player's or CPU's turn is done through the parameter. After
    * that, their respective symbol, either an X or O, is placed on the gameboard.
    * Does not return anything since it is void.
    * @param gameBoard The 2D array game board
    * @param placement The placement of either the player or CPU 
    * @param user The identity of either the player or CPU 
    */
    public static void moveTurn (char [][] gameBoard, int placement, String user)
    { 
      // Initializes the mark   
      char mark = ' ';
      
      /* 
      *  Gives user the X mark and cpu the O depending on input
      *  of the string parameter when called. Then, it adds the
      *  placement parameter to a list that contains other positions
      *  already taken by either the player or CPU. This is important
      *  as it will later be used in the winnerCondition method to
      *  see if a set of numbers is present within a list that
      *  can determine the outcome of the game.
      */
    
      if(user.equals("player")){  
         mark = 'X';
         userSlots.add(placement);
       }else if (user.equals("CPU")){
         mark = 'O';
         cpuSlots.add(placement);
       } 
       
       
       
       /*
       * Placement within the gameboard. The switch statement
       * doesn't break and add a mark onto the board until the
       * placement number matches that of the case number.
       * For example, if the player enter in the number 9
       * as a position, the switch statement will search
       * through every case until its case number matches
       * up with the user's input, which is 9, that will
       * the execute the code within that case branch.
       */
        switch(placement)
      {
         case 1:
            gameBoard [0][0] = mark;
            break;
         case 2:
            gameBoard [0][2] = mark;
            break;
         case 3:
            gameBoard [0][4] = mark;
            break;
         case 4:
            gameBoard [2][0] = mark;
            break;
         case 5:
            gameBoard [2][2] = mark;
            break;
         case 6:
            gameBoard [2][4] = mark;
            break;
         case 7:
            gameBoard [4][0] = mark;
            break;
         case 8:
            gameBoard [4][2] = mark;
            break;
         case 9:
            gameBoard [4][4] = mark;
            break;
         default:
            break;
      }
    
    
    }
   
   /**
   *  This method resets the game board depending if the player wants to continue playing or not.
   *  After being asked that question in the main method, it checks to see if the user's input is
   *  anything different than the following. If the input is different than expect, the program
   *  will keep asking to player to enter in an acceptable input until they do. If they do want
   *  to continue playing, It will clear all of the slots on the board along with the lists 
   *  containing the user's and cpu's placements so the decision from the last round isn't
   *   accidentally printed out. From there, it will send back the GameContinue string to
   *  the main to start off a new game. If they don't want to continue, the GameStop
   *  string will be sent back to the main to let it know that the program has to stop.
   *  @param gameBoard The 2D array game board
   */
   public static String resetGameBoard ( char[][] gameBoard)
   {
      char clearMark = ' ';
   Scanner input = new Scanner(System.in);
   String userResponse = input.nextLine();

   
   while ((!userResponse.equals("N")) && (!userResponse.equals("Y")) && (!userResponse.equals("n")) && (!userResponse.equals("y")))
      {
      System.out.println(userResponse);
      System.out.println("Please input Y / y for yes or N / n for No");
      userResponse = input.nextLine();
      }
      
         if (userResponse.equals("Y") || userResponse.equals("y"))
         {
         gameBoard [0][0] = clearMark;
         gameBoard [0][2] = clearMark;
         gameBoard [0][4] = clearMark;
         gameBoard [2][0] = clearMark;
         gameBoard [2][2] = clearMark;
         gameBoard [2][4] = clearMark;
         gameBoard [4][0] = clearMark;
         gameBoard [4][2] = clearMark;
         gameBoard [4][4] = clearMark;
         userSlots.clear();
         cpuSlots.clear();
      
         }
         else if (userResponse.equals("N") || userResponse.equals("n"))
         {
         return "GameStop";
         }
      
      return "GameContinue";
   }

   /**
   * The i integer is meant to be used as an incremental loop
   * in the winnerCondition method to check if a filled up
   * game board has a winner after the last placement. At the
   * point in which the integer surpasses 8, it means that a
   * a tie is decided. This basically means that if any of
   * the parties' slot lists don't satisfy the winning 
   * critea conditions while the board is filled up,
   * then no one wins.
   */   
   private static int i;
   
   /** 
   * Establishes the winning condition. Checks to see if certain inputs prompted by either party, which 
   * is stored in their own slot list, matches those of that of the winning conditions, which is also
   * stored within its own list. Basically, it is comparing each parties' placements to see if it contains
   * a specific number group that matches a winning condition that also contains that group of numbers.
   * If a winning outcome is decided, it returns that result back to the main to be printed out. If not,
   * the game sends back a blank string result that will cause the program to move onto the next party's
   * turn. No parameters, but it does return a result back to the main that is not assigned to a variable.
   */  
   public static String winnerCondition()
   {
      List<Integer> topRow = Arrays.asList(1, 2, 3);
      List<Integer> midRow = Arrays.asList(4, 5, 6);
      List<Integer> bottomRow = Arrays.asList(7, 8, 9);
      List<Integer> leftColumn = Arrays.asList(1, 4, 7);
      List<Integer> midColumn = Arrays.asList(2, 5, 8);
      List<Integer> rightColumn = Arrays.asList(3, 6, 9);
      List<Integer> topLeftToBottomRightCross = Arrays.asList(1, 5, 9);
      List<Integer> bottomLeftToTopRightCross = Arrays.asList(3, 5, 7);
      
      List<List> winSlots = new ArrayList<>();
      winSlots.add(topRow);
      winSlots.add(midRow);
      winSlots.add(bottomRow);
      winSlots.add(leftColumn);
      winSlots.add(midColumn);
      winSlots.add(rightColumn);
      winSlots.add(topLeftToBottomRightCross);
      winSlots.add(bottomLeftToTopRightCross);
      
      for(List l : winSlots)
         {
            if (userSlots.containsAll(l))
            {
               if ((userSlots.size() + cpuSlots.size() == 9))
               {
           
               return "You won!";
               }
            return "You won!";
            }
            
            else if (cpuSlots.containsAll(l))
            {
               if ((userSlots.size() + cpuSlots.size() == 9))
               {
               return "CPU won!";
               }
            return "CPU won!";
            }
            
            else if ((userSlots.size() + cpuSlots.size() == 9))
            {
               for(List t : winSlots)
               {
                  if (userSlots.containsAll(t))
                  {
                  return "You won!";
                  }
                  else if (cpuSlots.containsAll(t))
                  {
                  return "CPU won!";
                  }
                  else if (i == 8)
                  {
                  return "TIE!";
                  }
                  i++;
               }
            }

            

         }
   return "";
    }
}


