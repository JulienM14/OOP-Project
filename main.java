import java.util.*;

class Main {
  static Scanner sc = new Scanner(System.in);
  static int playerHearts = 3;
  
  public static void main(String[] args) 
  {
    clear();
    boolean userStart = startMenu();
    if (userStart) 
    {
      exit();
    }
    else 
    {
      System.out.println("Goodbye!");
    }
  }

  public static boolean startMenu() 
  {
    clear();
    System.out.println(ColorConstants.RED_BOLD + "Welcome!" + ColorConstants.RESET);
    System.out.print("Would you like to start the maze? > ");
    String userInput = sc.nextLine();
    if (userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y"))
    {
      return true;
    }
    else
    {
      return false;
    }
    
  }

  
  public static String gameMenu(String hallType) 
  {
    switch (hallType) {
      case "T":
        tIntersection();
        while (true) {
          System.out.print("You can turn left or turn right. Which do you wish to do? > ");
          String userChoice = sc.nextLine();
          if (userChoice.equalsIgnoreCase("left")) {
            return "Left";
          }
          else if (userChoice.equalsIgnoreCase("right")) {
            return "Right";
          }
        }
        
      case "Cross":
        crossIntersection();
        while (true) {
          System.out.print("You can go straight, turn left, or turn right. Which do you wish to do? > ");
          String userChoice = sc.nextLine();
          if (userChoice.equalsIgnoreCase("straight")) {
            return "Straight";
          }
          else if (userChoice.equalsIgnoreCase("left")) {
            return "Left";
          }
          else if (userChoice.equalsIgnoreCase("right")) {
            return "Right";
          }
        }

      case "LeftT":
        leftTIntersection();
        while (true) {
          System.out.print("You can walk straight or turn left. Which do wish to do?");
          String userChoice = sc.nextLine();
          if (userChoice.equalsIgnoreCase("left")) {
            return "Left";
          }
          else if (userChoice.equalsIgnoreCase("straight")) {
            return "Straight";
          }
        }
        

      case "RightT":
        rightTIntersection();
        System.out.println("You can walk straight, or turn right. Which do wish to do?");
        while (true) {
          System.out.println("You can walk straight, or turn right. Which do wish to do?");
          String userChoice = sc.nextLine();
          if (userChoice.equalsIgnoreCase("right")) {
            return "Right";
          }
          else if (userChoice.equalsIgnoreCase("straight")) {
            return "Straight";
          }
        }
    
      case "Hallway":
        hallway();
        System.out.println("You can walk forward. You do so.");
        return "Straight";

      case "Exit":
        exit();
        System.out.println("You have reached the end of the maze! Congrats!");
        return "Win";

      default:
        deadend();
        System.out.println("You have reached a dead end :(");
        return "Lose";
  
    }
      
    
  }


  public static void clear() 
  {
     System.out.print("\033[H\033[2J");
  }

  public static void maze()
  {
    gameMenu("Hallway");
    String choice = gameMenu("T");
    if (choice.equals("Left")) 
    {
      gameMenu("Dead End");
    }
    else //Right
    {
      choice = gameMenu("Cross");
      if (choice.equals("Left")) 
      {
        choice = gameMenu("T");
        if(choice.equals("Left")){
          gameMenu("Dead End");
        }
        else{
          gameMenu("Hallway");
          gameMenu("Hallway");
          gameMenu("Hallway");
          choice = gameMenu("T");
          if(choice.equals("Left")){
            gameMenu("Hallway");
            //TODO: Secret ending
          }
          else{
            gameMenu("Hallway");
            gameMenu("Hallway");
            gameMenu("Exit");
          }
        }
          
        
      }
      else if (choice.equals("Straight"))
      {
        choice = gameMenu("LeftT");
        if(choice.equals("Left")){
          gameMenu("Hallway");
          gameMenu("Hallway");
          gameMenu("Exit");
        }
        else{
          choice = gameMenu("T");
          if(choice.equals("Left")){
            choice = gameMenu("T");
            if(choice.equals("Left")){
              deadend();
            }
            else{
              gameMenu("Hallway");
              choice = gameMenu("RightT");
              if(choice.equals("Right")){
                gameMenu("Exit");
              }
              else{
                gameMenu("Hallway");
                gameMenu("Hallway");
                gameMenu("Hallway");
                //TODO:Secret endings 
              }
            }
              
            
          }
          else{
            deadend();
          }
        }
      }
      else //Right
      {
        choice = gameMenu("RightT");
        if (choice.equals("Straight"))
        {
          gameMenu("Hallway");
          gameMenu("Hallway");
          gameMenu("Exit");
        }
        else //Right
        {
          choice = gameMenu("T");
          if(choice.equals("Left")){
            choice = gameMenu("T");
            if(choice.equals("Left")){
              gameMenu("Dead End");
            }
            else{
              choice = gameMenu("RightT");
              if(choice.equals("Right")){
                gameMenu("Exit");
              }
              else{
                gameMenu("Hallway");
                gameMenu("Hallway");
                gameMenu("Hallway");
                //TODO: Secret ending
              }
            }
          }
          else{
            gameMenu("Dead End");
          }
        }
      }
    }
  }

  public static String rps(String enemyName) {
    Random rand = new Random();
    System.out.println("You are playing Rock Paper Scissors against " + ColorConstants.RED_BOLD + enemyName + ColorConstants.RESET);
    System.out.print("Would you like to throw rock, paper, or scissors? > ");
    String userChoice = sc.nextLine();
    int randomChoice = rand.nextInt(3);
    String enemyThrow;
    switch (randomChoice) {
      case 0:
        enemyThrow = "Rock";
        break;
      case 1:
        enemyThrow = "Paper";
        break;
      case 2:
        enemyThrow = "Scissors";
        break;
      default:
        enemyThrow = "Null";
        break;
    }
    
    if (userChoice.equalsIgnoreCase(enemyThrow)) {
      return "Tie";
    }
    else if ((userChoice.equalsIgnoreCase("rock") && enemyThrow.equals("Scissors")) || (userChoice.equalsIgnoreCase("paper") && enemyThrow.equals("Rock")) || (userChoice.equalsIgnoreCase("scissors") && enemyThrow.equals("Paper"))) {
      return "Win";
    }
    else if ((enemyThrow.equals("Rock") && userChoice.equalsIgnoreCase("Scissors")) || (enemyThrow.equals("Paper") && userChoice.equalsIgnoreCase("rock")) || (enemyThrow.equals("Scissors") && userChoice.equalsIgnoreCase("paper"))) {
      return "Lose";
    }
    else {
      return "Broke";
    }
  }

  public static void sleep(int seconds) {
    int miliseconds = (int) (seconds * 1000);
    try {
      Thread.sleep(miliseconds);
    } 
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void hallway()
  {
    clear();
    System.out.println("+---------------------------------------------------------+");
    System.out.println("|\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%|");
    System.out.println("|%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%|");
    System.out.println("|%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%|");
    System.out.println("|%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%|");
    System.out.println("|%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%\\%%%%%%%%%%%%%%%%%%/%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|_________________|%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%_/                   \\%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%_/     ^          ^       \\_%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%_/                            \\_%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%_/      ^                         \\_%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%_/                  *        ^        \\_%%%%%%%%%|");
    System.out.println("|%%%%%%_/                                        \\_%%%%%%%|");
    System.out.println("|%%%%_/           *                                \\_%%%%%|");
    System.out.println("|%%_/     *                            *             \\_%%%|");
    System.out.println("|%/                                              *     \\_%|");
    System.out.println("+---------------------------------------------------------+");
    
    
  }

  public static void leftTIntersection() {
    clear();
    System.out.println("+---------------------------------------------------------+");
    System.out.println("|\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%|");
    System.out.println("|%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%|");
    System.out.println("|%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%|");
    System.out.println("|%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%|");
    System.out.println("|%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%\\%%%%%%%%%%%%%%%%%%/%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%/      \\%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%|       |%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%|       |%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%|       |%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%|       |%%%%|_________________|%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%|       |%_/                   \\%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%|       _/     ^          ^       \\_%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%|     _/                            \\_%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%|   _/      ^                         \\_%%%%%%%%%%%|");
    System.out.println("|%%%%%%| _/                  *        ^        \\_%%%%%%%%%|");
    System.out.println("|%%%%%%_/                                        \\_%%%%%%%|");
    System.out.println("|%%%%_/           *                                \\_%%%%%|");
    System.out.println("|%%_/     *                            *             \\_%%%|");
    System.out.println("|%/                                              *     \\_%|");
    System.out.println("+---------------------------------------------------------+");
    
  }

  public static void rightTIntersection() {
    clear();
    System.out.println("+---------------------------------------------------------+");
    System.out.println("|\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%|");
    System.out.println("|%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%|");
    System.out.println("|%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%|");
    System.out.println("|%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%|");
    System.out.println("|%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%\\%%%%%%%%%%%%%%%%%%/%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%/       \\%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|_________________|%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%_/                   \\%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%_/     ^          ^       \\_|       |%%%%%%|");
    System.out.println("|%%%%%%%%%%%%_/                            \\_      |%%%%%%|");
    System.out.println("|%%%%%%%%%%_/      ^                         \\_    |%%%%%%|");
    System.out.println("|%%%%%%%%_/                  *        ^        \\_  |%%%%%%|");
    System.out.println("|%%%%%%_/                                        \\_%%%%%%%|");
    System.out.println("|%%%%_/           *                                \\_%%%%%|");
    System.out.println("|%%_/     *                            *             \\_%%%|");
    System.out.println("|%/                                              *     \\_%|");
    System.out.println("+---------------------------------------------------------+");
    
  }

  public static void tIntersection() {
    clear();
    System.out.println("+---------------------------------------------------------+");
    System.out.println("|\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%|");
    System.out.println("|%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%|");
    System.out.println("|%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%|");
    System.out.println("|%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%|");
    System.out.println("|%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%\\%%%%%%%%%%%%%%%%%%/%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|XXXXXXXXXXXXXXXXX|%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|XXXXXXXXXXXXXXXXX|%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|XXXXXXXXXXXXXXXXX|%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%/       \\%%%%|XXXXXXXXXXXXXXXXX|%%%%/       \\%%%%%%|");
    System.out.println("|%%%%%%|       |%%%%|XXXXXXXXXXXXXXXXX|%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%|       |%%%%|XXXXXXXXXXXXXXXXX|%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%|       |%%%%|XXXXXXXXXXXXXXXXX|%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%|       |%%%%|XXXXXXXXXXXXXXXXX|%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%|       |%%%%|XXXXXXXXXXXXXXXXX|%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%|       |%_/                   \\%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%|       _/     ^          ^       \\_|       |%%%%%%|");
    System.out.println("|%%%%%%|     _/                            \\_      |%%%%%%|");
    System.out.println("|%%%%%%|   _/      ^                         \\_    |%%%%%%|");
    System.out.println("|%%%%%%| _/                  *        ^        \\_  |%%%%%%|");
    System.out.println("|%%%%%%_/                                        \\_%%%%%%%|");
    System.out.println("|%%%%_/           *                                \\_%%%%%|");
    System.out.println("|%%_/     *                            *             \\_%%%|");
    System.out.println("|%/                                              *     \\_%|");
    System.out.println("+---------------------------------------------------------+");
  }

  public static void deadend() {
    clear();
    System.out.println("+---------------------------------------------------------+");
    System.out.println("|\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%|");
    System.out.println("|%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%|");
    System.out.println("|%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%|");
    System.out.println("|%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%|");
    System.out.println("|%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%\\%%%%%%%%%%%%%%%%%%/%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|XXXXXXXXXXXXXXXXX|%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|XXXXXXXXXXXXXXXXX|%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|XXXXXXXXXXXXXXXXX|%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|XXXXXXXXXXXXXXXXX|%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|XXXXXXXXXXXXXXXXX|%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|XXXXXXXXXXXXXXXXX|%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|XXXXXXXXXXXXXXXXX|%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|XXXXXXXXXXXXXXXXX|%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|XXXXXXXXXXXXXXXXX|%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%_/                   \\%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%_/     ^          ^       \\_%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%_/                            \\_%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%_/      ^                         \\_%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%_/                  *        ^        \\_%%%%%%%%%|");
    System.out.println("|%%%%%%_/                                        \\_%%%%%%%|");
    System.out.println("|%%%%_/           *                                \\_%%%%%|");
    System.out.println("|%%_/     *                            *             \\_%%%|");
    System.out.println("|%/                                              *     \\_%|");
    System.out.println("+---------------------------------------------------------+");
  }

  public static void crossIntersection() {
    clear();
    System.out.println("+---------------------------------------------------------+");
    System.out.println("|\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%|");
    System.out.println("|%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%|");
    System.out.println("|%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%|");
    System.out.println("|%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%|");
    System.out.println("|%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%\\%%%%%%%%%%%%%%%%%%/%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|                 |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%/       \\%%%%|                 |%%%%/       \\%%%%%%|");
    System.out.println("|%%%%%%|       |%%%%|                 |%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%|       |%%%%|                 |%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%|       |%%%%|                 |%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%|       |%%%%|                 |%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%|       |%%%%|_________________|%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%|       |%_/                   \\%%%%|       |%%%%%%|");
    System.out.println("|%%%%%%|       _/     ^          ^       \\_|       |%%%%%%|");
    System.out.println("|%%%%%%|     _/                            \\_      |%%%%%%|");
    System.out.println("|%%%%%%|   _/      ^                         \\_    |%%%%%%|");
    System.out.println("|%%%%%%| _/                  *        ^        \\_  |%%%%%%|");
    System.out.println("|%%%%%%_/                                        \\_%%%%%%%|");
    System.out.println("|%%%%_/           *                                \\_%%%%%|");
    System.out.println("|%%_/     *                            *             \\_%%%|");
    System.out.println("|%/                                              *     \\_%|");
    System.out.println("+---------------------------------------------------------+");
  }

  public static void exit() {
    clear();
    System.out.println("+---------------------------------------------------------+");
    System.out.println("|\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%|");
    System.out.println("|%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%|");
    System.out.println("|%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%|");
    System.out.println("|%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%|");
    System.out.println("|%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%\\_%%%%%%%%%%%%%%%%%%%%_/%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%\\%%%%%%%%%%%%%%%%%%/%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|  |  |  |  |  |  |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|  |  |  |  |  |  |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|  |  |  |  |  |  |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|  |  |  |  |  |  |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|  |  |  |  |  |()|%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|  |  |  |  |  |  |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|  |  |  |  |  |  |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|  |  |  |  |  |  |%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%%%%|__|__|__|__|__|__|%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%%%_/                   \\%%%%%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%%%_/     ^          ^       \\_%%%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%%%_/                            \\_%%%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%%%_/      ^                         \\_%%%%%%%%%%%|");
    System.out.println("|%%%%%%%%_/                  *        ^        \\_%%%%%%%%%|");
    System.out.println("|%%%%%%_/                                        \\_%%%%%%%|");
    System.out.println("|%%%%_/           *                                \\_%%%%%|");
    System.out.println("|%%_/     *                            *             \\_%%%|");
    System.out.println("|%/                                              *     \\_%|");
    System.out.println("+---------------------------------------------------------+");  
  }

  public static void knight() {
    System.out.println("               ___ ");
    System.out.println("              |===|");
    System.out.println("              |___|");
    System.out.println("             /#####\\  _____");
    System.out.println("            //#####\\\\|     |  ");
    System.out.println("           // ##### \\| (D) |  ");
    System.out.println("<=========]&& ^^^^^  |     |");
    System.out.println("              |#|#|  |     |   ");
    System.out.println("              |_|_|  \\_____/      ");
    System.out.println("              [ | ]        ");
  }  

}

final class ColorConstants {
  public static final String RESET = "\u001B[0m";
  public static final String BLACK = "\u001B[30m";
  public static final String RED = "\u001B[31m";
  public static final String GREEN = "\u001B[32m";
  public static final String YELLOW = "\u001B[33m";
  public static final String BLUE = "\u001B[34m";
  public static final String PURPLE = "\u001B[35m";
  public static final String CYAN = "\u001B[36m";
  public static final String WHITE = "\u001B[37m";
  public static final String BLACK_BOLD = "\033[1;30m"; 
  public static final String RED_BOLD = "\033[1;31m";  
  public static final String GREEN_BOLD = "\033[1;32m"; 
  public static final String YELLOW_BOLD = "\033[1;33m"; 
  public static final String BLUE_BOLD = "\033[1;34m";  
  public static final String PURPLE_BOLD = "\033[1;35m"; 
  public static final String CYAN_BOLD = "\033[1;36m";   
  public static final String WHITE_BOLD = "\033[1;37m";  
}