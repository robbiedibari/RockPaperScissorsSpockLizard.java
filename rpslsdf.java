import java.util.Random;
import java.util.Scanner;

public class rpslsdf {

    private static String playerWonId = "";
    private static int[] rounds = new int[3];
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int choice;
        int roundNumber = 0;
        String Player1Name, Player2Name, Player3Name;

        System.out.println("Enter the Name of the 3 Players: ");
        Player1Name = input.nextLine();
        Player2Name = input.nextLine();
        Player3Name = input.nextLine();
        System.out.println("Player 1 is  " + Player1Name);
        System.out.println("Player 2 is  " + Player2Name);
        System.out.println("Player 3 is  " + Player3Name);

        System.out.println("--------------------------------------------");

        do {
            System.out.println(" ");
            System.out.println("-----------------MAIN MENU-----------------------");
            System.out.println("1. See the Rules \t \t 2. Play Game ");
            System.out.println("3. Shows Stats \t \t 4. Quit");

            choice = input.nextInt();

            switch (choice) {
                case 1:
                    showTheRules();
                    break;
                case 2:
                    roundNumber++;
                    System.out.println("--------------------------------------------");
                    System.out.println("Let's Play!");
                    System.out.println("Round Number : " + roundNumber);
                    System.out.println("--------------------------------------------");
                    playGame(Player1Name, Player2Name, Player3Name);
                    break;
                case 3:
                    System.out.println("Shows stats:");
                    System.out.println(Player1Name + " won "+ rounds[0] + " rounds");
                    System.out.println(Player2Name + " won "+ rounds[1] + " rounds");
                    System.out.println(Player3Name + " won "+ rounds[2] + " rounds");
                    break;
                default:
                    System.out.println("See you later!");
            }

        } while (choice != 4);

    }

    public static void showTheRules() {
        System.out.println(" The players are arranged in a circle.\n " +
                "Each player will play against the players on his right and on his left with the use of both hands.\n " +
                "Any player who loses to  the throws on both sides is eliminated from the circle of play.\n " +
                "This continues until only two players are left. Then a regular RPS match(es) will be played by the surviving two players to declare who is victorious and who loses.\n " +
                "A Double Fisting RPS match usually requires a referee who will monitor the game and prevent any  form of cheating.\n " +
                "This is because it has been discovered to be very easy for a player to falsify his throws or leverage the carelessness or split the attention of another player.\n " +
                "Your game is going to have 3 players. One is going to be the program user and the other 2 has to be  simulated by as automatic players with random moves.\n " +
                "Even in the event that the player loses in the first part, you should allow the 2 computer simulated players to play the final round. ");
    }

    public static void playGame(String Player1Name, String Player2Name, String Player3Name) {

        int Player1lossNum = 0, Player2lossNum = 0, Player3lossNum = 0, Player1tieNum = 0, Player3tieNum = 0, Player2tieNum = 0, Player1winNum = 0, Player2winNum = 0, Player3winNum = 0, nullGame = 0, Eliminated = 5;

        do {
            int player1 = PlayerPlay(Player1Name);
            int player2 = ComputerPlay(Player2Name);
            int player3 = ComputerPlay(Player3Name);


            // tie
            if (player3 == player1 && player3 == player2 && player1 == player3) {   // 1 == Rock  2 == Scissors  3 == Paper 4 == Lizard 5 == Spock
                Player1tieNum++;
                Player2tieNum++;
                Player3tieNum++;
                System.out.println("It's a tie game!! ");
                System.out.println("--------------------------------------------");

            } else if ((player1 == 1 && player2 == 2 && player3 == 3) || (player1 == 2 && player2 == 3 && player3 == 1) || (player1 == 3 && player2 == 1 && player3 == 2) || (player1 == 3 && player2 == 2 && player3 == 1) || (player1 == 1 && player2 == 4 && player3 == 5) || (player1 == 5 && player2 == 1 && player3 == 4) || (player1 == 4 && player2 == 5 && player3 == 1) || (player1 == 5 && player2 == 2 && player3 == 3) || (player1 == 3 && player2 == 5 && player3 == 2) || (player1 == 2 && player2 == 3 && player3 == 5)) {
                nullGame++;
                System.out.println("Nobody wins!");
                System.out.println("--------------------------------------------");
            }
            // ONLY PLAYER 1 WINNING CASE
            else if ((player1 == 1 && player2 == 2 && player3 == 2) || (player1 == 1 && player2 == 4 && player3 == 2) || (player1 == 1 && player2 == 2 && player3 == 4) || (player1 == 1 && player2 == 4 && player3 == 4) || (player1 == 3 && player2 == 1 && player3 == 1) || (player1 == 3 && player2 == 5 && player3 == 5) || (player1 == 3 && player2 == 5 && player3 == 1) || (player1 == 3 && player2 == 1 && player3 == 5) || (player1 == 2 && player2 == 3 && player3 == 3) || (player1 == 2 && player2 == 4 && player3 == 4) || (player1 == 2 && player2 == 4 && player3 == 3) || (player1 == 2 && player2 == 3 && player3 == 4) || (player1 == 4 && player2 == 5 && player3 == 5) || (player1 == 4 && player2 == 3 && player3 == 3) || (player1 == 4 && player2 == 5 && player3 == 3) || (player1 == 4 && player2 == 3 && player3 == 5) || (player1 == 5 && player2 == 1 && player3 == 1) || (player1 == 5 && player2 == 2 && player3 == 2) || (player1 == 5 && player2 == 1 && player3 == 2) || (player1 == 5 && player2 == 2 && player3 == 1)) {
                Player1winNum++;
                Player2lossNum++;
                Player3lossNum++;
                rounds[0]++;
                System.out.println(Player1Name + " Wins!!");
                System.out.println("--------------------------------------------");
            }
            // ONLY PLAYER 2 WINNING CASE
            else if ((player1 == 2 && player2 == 1 && player3 == 2) || (player1 == 4 && player2 == 1 && player3 == 2) || (player1 == 2 && player2 == 1 && player3 == 4) || (player1 == 4 && player2 == 1 && player3 == 4) || (player1 == 1 && player2 == 3 && player3 == 1) || (player1 == 5 && player2 == 3 && player3 == 5) || (player1 == 5 && player2 == 3 && player3 == 1) || (player1 == 1 && player2 == 3 && player3 == 5) || (player1 == 3 && player2 == 2 && player3 == 3) || (player1 == 4 && player2 == 2 && player3 == 4) || (player1 == 4 && player2 == 2 && player3 == 3) || (player1 == 3 && player2 == 2 && player3 == 4) || (player1 == 5 && player2 == 4 && player3 == 5) || (player1 == 3 && player2 == 4 && player3 == 3) || (player1 == 5 && player2 == 4 && player3 == 3) || (player1 == 3 && player2 == 4 && player3 == 5) || (player1 == 1 && player2 == 5 && player3 == 1) || (player1 == 2 && player2 == 5 && player3 == 2) || (player1 == 1 && player2 == 5 && player3 == 2) || (player1 == 2 && player2 == 5 && player3 == 1)) {
                Player2winNum++;
                Player1lossNum++;
                Player3lossNum++;
                System.out.println(Player2Name + " Wins!!");
                System.out.println("--------------------------------------------");
                rounds[1]++;
            }
            // ONLY PLAYER 3 WINNING CASE.
            else if ((player1 == 2 && player2 == 2 && player3 == 1) || (player1 == 4 && player2 == 2 && player3 == 1) || (player1 == 2 && player2 == 4 && player3 == 1) || (player1 == 4 && player2 == 4 && player3 == 1) || (player1 == 1 && player2 == 1 && player3 == 3) || (player1 == 5 && player2 == 5 && player3 == 3) || (player1 == 5 && player2 == 1 && player3 == 3) || (player1 == 1 && player2 == 5 && player3 == 3) || (player1 == 3 && player2 == 3 && player3 == 2) || (player1 == 4 && player2 == 4 && player3 == 2) || (player1 == 4 && player2 == 3 && player3 == 2) || (player1 == 3 && player2 == 4 && player3 == 2) || (player1 == 5 && player2 == 5 && player3 == 4) || (player1 == 3 && player2 == 3 && player3 == 4) || (player1 == 5 && player2 == 3 && player3 == 4) || (player1 == 3 && player2 == 5 && player3 == 4) || (player1 == 1 && player2 == 1 && player3 == 5) || (player1 == 2 && player2 == 2 && player3 == 5) || (player1 == 1 && player2 == 2 && player3 == 5) || (player1 == 2 && player2 == 1 && player3 == 5)) {
                Player3winNum++;
                Player2lossNum++;
                Player1lossNum++;
              rounds[2]++;
                System.out.println(Player3Name + " Wins!!");
                System.out.println("--------------------------------------------");
            }

            // PLAYER 1 AND 2 WINNING CASE
            else if ((player1 == 1 && player2 == 1 && player3 == 2) || (player1 == 3 && player2 == 3 && player3 == 1) || (player1 == 2 && player2 == 2 && player3 == 3) || (player1 == 2 && player2 == 2 && player3 == 4) || (player1 == 1 && player2 == 1 && player3 == 4) || (player1 == 3 && player2 == 3 && player3 == 5) || (player1 == 4 && player2 == 4 && player3 == 5) || (player1 == 4 && player2 == 4 && player3 == 3) || (player1 == 5 && player2 == 5 && player3 == 2) || (player1 == 5 && player2 == 5 && player3 == 1)) {
                Player1winNum++;
                Player2winNum++;
                Player3lossNum++;
                System.out.println(Player1Name + " & " + Player2Name + " Win!!");
                rounds[0]++;
                rounds[1]++;
                System.out.println("--------------------------------------------");
            }
            // PLAYER 1 AND 3 WINNING CASE
            else if ((player1 == 1 && player2 == 2 && player3 == 1) || (player1 == 3 && player2 == 1 && player3 == 3) || (player1 == 2 && player2 == 3 && player3 == 2) || (player1 == 2 && player2 == 4 && player3 == 2) || (player1 == 1 && player2 == 4 && player3 == 1) || (player1 == 3 && player2 == 5 && player3 == 3) || (player1 == 4 && player2 == 5 && player3 == 4) || (player1 == 4 && player2 == 3 && player3 == 4) || (player1 == 5 && player2 == 2 && player3 == 5) || (player1 == 5 && player2 == 1 && player3 == 5)) {
                Player1winNum++;
                Player3winNum++;
                Player2lossNum++;
                rounds[0]++;
                rounds[2]++;
                System.out.println(Player1Name + " & " + Player3Name + " Win!!");
                System.out.println("--------------------------------------------");

            }
            //PLAYER 2 AND 3 WINNING CASE
            else if ((player1 == 2 && player2 == 1 && player3 == 1) || (player1 == 1 && player2 == 3 && player3 == 3) || (player1 == 3 && player2 == 2 && player3 == 2) || (player1 == 4 && player2 == 2 && player3 == 2) || (player1 == 4 && player2 == 1 && player3 == 1) || (player1 == 5 && player2 == 3 && player3 == 3) || (player1 == 5 && player2 == 4 && player3 == 4) || (player1 == 3 && player2 == 4 && player3 == 4) || (player1 == 2 && player2 == 5 && player3 == 5) || (player1 == 1 && player2 == 5 && player3 == 5)) {
                Player2winNum++;
                Player3winNum++;
                Player1lossNum++;
                rounds[2]++;
                rounds[1]++;
                System.out.println(Player2Name + " & " + Player3Name + " Win!!");
                System.out.println("--------------------------------------------");

            } else {
                System.out.println(" It's a NULL GAME");
                System.out.println("--------------------------------------------");
            }

        } while (!(Player1lossNum == Eliminated || Player2lossNum == Eliminated || Player3lossNum == Eliminated));

        System.out.println(Player1Name + " lost " + Player1lossNum + " games");
        System.out.println(Player2Name + " lost " + Player2lossNum + " games");
        System.out.println(Player3Name + " lost " + Player3lossNum + " games");

        if (Player1lossNum == 5) {
            System.out.println(Player1Name + " is ELIMINATED");
            System.out.println(Player2Name + " & " + Player3Name + " go to the Final Round!");
            System.out.println(" ");
            FinalRound(Player2Name, Player3Name);

        } else if (Player1lossNum == 5 && Player2lossNum == 5) {
            System.out.println(Player1Name + " & " + Player2Name + " are ELIMINATED!");
            System.out.println(Player3Name + " is the Winner!!");
        } else if (Player2lossNum == 5) {
            System.out.println(Player2Name + " is ELIMINATED");
            System.out.println(Player1Name + " & " + Player3Name + " go to the Final Round!");
            System.out.println(" ");
            FinalRound(Player1Name, Player3Name);
        } else if (Player2lossNum == 5 && Player3lossNum == 5) {
            System.out.println(Player2Name + " & " + Player3Name + " are ELIMINATED!");
            System.out.println(Player1Name + " is the Winner!!");

        } else if (Player3lossNum == 5 && Player1lossNum == 5) {
            System.out.println(Player3Name + " & " + Player1Name + " are ELIMINATED!");
            System.out.println(Player2Name + " is the Winner!!");

        } else if (Player3lossNum == 5) {
            System.out.println(Player3Name + " is ELIMINATED");
            System.out.println(Player1Name + " & " + Player2Name + " go to the Final Round!");
            System.out.println(" ");
            FinalRound(Player1Name, Player2Name);

        }

    }

    //SCANNER INPUT FOR USER PLAY
    private static int PlayerPlay(String playerName) {
        Scanner input = new Scanner(System.in);
        int PlayerPlay;
        System.out.println(playerName + " chose your play!\t 1: Rock\t 2: Scissor\t 3: Paper\t 4: Lizard\t 5: Spock ");
        PlayerPlay = input.nextInt();
        while (PlayerPlay < 0 || PlayerPlay >= 6) {
            System.out.println("Invalid Value");
            System.out.println("Please enter a number from 1 to 5");
            PlayerPlay = input.nextInt();
        }
        if (PlayerPlay == 1) {
            System.out.println(playerName + " chose : Rock");
            return 1;
        } else if (PlayerPlay == 2) {
            System.out.println(playerName + " chose: Scissor");
            return 2;
        } else if (PlayerPlay == 3) {
            System.out.println(playerName + " chose : Paper");
            return 3;
        } else if (PlayerPlay == 4) {
            System.out.println(playerName + " chose : Lizard");
            return 4;
        } else {
            System.out.println(playerName + " chose : Spock");
            return 5;
        }


    }

    // RANDOMIZE PLAY FOR OTHER TWO PLAYERS.
    private static int ComputerPlay(String playerName) {
        int computerPlay;
        computerPlay = (int) ((Math.random() * 5) + 1);


        if (computerPlay == 1) {
            System.out.println(playerName + " chose : Rock");
            return 1;
        } else if (computerPlay == 2) {
            System.out.println(playerName + " chose: Scissor");
            return 2;
        } else if (computerPlay == 3) {
            System.out.println(playerName + " chose : Paper");
            return 3;
        } else if (computerPlay == 4) {
            System.out.println(playerName + " chose : Lizard");
            return 4;
        } else {
            System.out.println(playerName + " chose : Spock");
            return 5;
        }
    }

    // FINAL ROUND.
    public static void FinalRound(String Player1, String Player2) {
        int Player1WinningRound = 0, Player2WinningRound = 0;
        int Player = ComputerPlay(Player1);
        int Player0 = ComputerPlay(Player2);

        // TIE GAMES POSSIBILITIES
            if (Player == 1 && Player0 == 1) {
                System.out.println("Null Game");
                System.out.println("Play Again");
                ComputerPlay(Player1);
                ComputerPlay(Player2);
            }
            if (Player == 2 && Player0 == 2) {
                System.out.println("Null Game");
                System.out.println("Play Again");
                ComputerPlay(Player1);
                ComputerPlay(Player2);
            }
            if (Player == 3 && Player0 == 3) {
                System.out.println("Null Game");                                                // 1 == Rock  2 == Scissors  3 == Paper
                System.out.println("Play Again");
                ComputerPlay(Player1);
                ComputerPlay(Player2);
            }
            if (Player == 4 && Player0 == 4) {
                System.out.println("Null Game");
                System.out.println("Play Again");
                ComputerPlay(Player1);
                ComputerPlay(Player2);
            }
            if (Player == 5 && Player0 == 5) {
                System.out.println("Null Game");
                System.out.println("Play Again");
                ComputerPlay(Player1);
                ComputerPlay(Player2);
            }

            // PLAYER 1 WINNING OPPORTUNITIES.
            if (Player == 1 && Player0 == 2 || Player == 1 && Player0 == 4) { // Rock winning chances
                System.out.println(Player1 + " is The ABSOLUTE WINNER!!");

            } else if (Player == 5 && Player0 == 1 || Player == 5 && Player0 == 2) { // Spock winning chances
                System.out.println(Player1 + " is the ABSOLUTE WINNER!!");

            } else if (Player == 2 && Player0 == 3 || Player == 2 && Player0 == 4) { // Scissors winning chances.
                System.out.println(Player1 + " is The ABSOLUTE WINNER!!");

            } else if (Player == 3 && Player0 == 1 || Player == 3 && Player0 == 5) {  // Paper winning chances
                System.out.println(Player1 + " is The ABSOLUTE WINNER!!");


            } else if (Player == 4 && Player0 == 5 || Player == 4 && Player0 == 3) {  // Lizard winning chances
                System.out.println(Player1 + " is The ABSOLUTE WINNER!!");

            } else {
                System.out.println(Player2 + " is The ABSOLUTE WINNER!!");
                playerWonId = Player1;
            }
            playerWonId = Player2;
        }

    }
