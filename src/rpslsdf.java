import java.util.*;
import java.util.stream.*;

public class rpslsdf {
    private static final String[] MOVES = {"Rock", "Paper", "Scissors", "Lizard", "Spock"};
    private static final Map<String, List<String>> WINNING_MOVES = new HashMap<String, List<String>>() {{
        put("Rock", Arrays.asList("Scissors", "Lizard"));
        put("Paper", Arrays.asList("Rock", "Spock"));
        put("Scissors", Arrays.asList("Paper", "Lizard"));
        put("Lizard", Arrays.asList("Spock", "Paper"));
        put("Spock", Arrays.asList("Scissors", "Rock"));
    }};
    private static final int NUM_PLAYERS = 3;
    private static final int ELIMINATION_THRESHOLD = 5;
    private static int[] playerWins = new int[NUM_PLAYERS];
    private static String[] playerNames = new String[NUM_PLAYERS];
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the names of the 3 players:");
        for (int i = 0; i < NUM_PLAYERS; i++) {
            playerNames[i] = input.nextLine();
        }

        System.out.println("--------------------------------------------");

        int choice;
        do {
            System.out.println();
            System.out.println("-----------------MAIN MENU-------------------");
            System.out.println("1. See the Rules \t \t 2. Play Game");
            System.out.println("3. Show Stats \t \t 4. Quit");

            while (!input.hasNextInt()) {
                System.out.println("Invalid choice. Please enter a number.");
                input.next();
            }
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    showTheRules();
                    break;
                case 2:
                    playGame();
                    break;
                case 3:
                    showStats();
                    break;
                case 4:
                    System.out.println("See you later!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);
    }

    public static void showTheRules() {
        System.out.println("The players are arranged in a circle.");
        System.out.println("Each player will play against the players on their right and left with the use of both hands.");
        System.out.println("Any player who loses to the throws on both sides is eliminated from the circle of play.");
        System.out.println("This continues until only two players are left.");
        System.out.println("Then a regular RPS match(es) will be played by the surviving two players to declare the winner.");
        System.out.println("A Double Fisting RPS match usually requires a referee to monitor the game and prevent cheating.");
        System.out.println("Your game has 3 players: one user and two computer-controlled players.");
    }

    public static void playGame() {
        int[] playerLosses = new int[NUM_PLAYERS];
        int roundNumber = 0;

        do {
            roundNumber++;
            System.out.println("--------------------------------------------");
            System.out.println("Let's Play!");
            System.out.println("Round Number: " + roundNumber);
            System.out.println("--------------------------------------------");

            int[] playerMoves = new int[NUM_PLAYERS];

            // Get user's move
            playerMoves[0] = getPlayerMove(playerNames[0]);

            // Generate computer moves
            for (int i = 1; i < NUM_PLAYERS; i++) {
                playerMoves[i] = generateComputerMove(playerNames[i]);
            }

            // Determine the winner(s)
            int winnerIndex = determineWinner(playerMoves);
            if (winnerIndex == -1) {
                System.out.println("It's a tie game!!");
            } else {
                System.out.println(playerNames[winnerIndex] + " wins!!");
                playerWins[winnerIndex]++;
            }

            System.out.println("--------------------------------------------");

            // Update player losses
            for (int i = 0; i < NUM_PLAYERS; i++) {
                if (i != winnerIndex) {
                    playerLosses[i]++;
                    if (playerLosses[i] == ELIMINATION_THRESHOLD) {
                        System.out.println(playerNames[i] + " is ELIMINATED");
                    }
                }
            }

        } while (!isGameFinished(playerLosses));

        // Determine the remaining players for the final round
        String[] remainingPlayers = getRemainingPlayers(playerLosses);

        if (remainingPlayers.length == 1) {
            System.out.println(remainingPlayers[0] + " is the Winner!!");
        } else {
            System.out.println(remainingPlayers[0] + " & " + remainingPlayers[1] + " go to the Final Round!");
            System.out.println();
            finalRound(remainingPlayers[0], remainingPlayers[1]);
        }
    }

    private static int getPlayerMove(String playerName) {
        int move;
        System.out.println(playerName + ", choose your move:");
        for (int i = 0; i < MOVES.length; i++) {
            System.out.println((i + 1) + ": " + MOVES[i]);
        }
        move = input.nextInt();
        while (move < 1 || move > MOVES.length) {
            System.out.println("Invalid move. Please enter a number between 1 and " + MOVES.length);
            move = input.nextInt();
        }
        System.out.println(playerName + " chose: " + MOVES[move - 1]);
        return move - 1;
    }

    private static int generateComputerMove(String playerName) {
        int move = new Random().nextInt(MOVES.length);
        System.out.println(playerName + " chose: " + MOVES[move]);
        return move;
    }

    private static int determineWinner(int[] playerMoves) {
        int winnerIndex = -1;
        for (int i = 0; i < NUM_PLAYERS; i++) {
            boolean won = true;
            boolean lost = true;
            for (int j = 0; j < NUM_PLAYERS; j++) {
                if (i != j) {
                    int result = compareMoves(playerMoves[i], playerMoves[j]);
                    if (result > 0) {
                        lost = false;
                    } else if (result < 0) {
                        won = false;
                    }
                }
            }
            if (won && !lost) {
                winnerIndex = i;
                break;
            }
        }
        return winnerIndex;
    }

    private static int compareMoves(int move1, int move2) {
        String m1 = MOVES[move1];
        String m2 = MOVES[move2];

        if (m1.equals(m2)) {
            return 0;
        }
        return WINNING_MOVES.get(m1).contains(m2) ? 1 : -1;
    }

    private static boolean isGameFinished(int[] playerLosses) {
        int countNotEliminated = 0;
        for (int i = 0; i < NUM_PLAYERS; i++) {
            if (playerLosses[i] < ELIMINATION_THRESHOLD) {
                countNotEliminated++;
            }
        }
        return countNotEliminated <= 2;
    }

    private static String[] getRemainingPlayers(int[] playerLosses) {
        return IntStream.range(0, NUM_PLAYERS)
                .filter(i -> playerLosses[i] < ELIMINATION_THRESHOLD)
                .mapToObj(i -> playerNames[i])
                .toArray(String[]::new);
    }

    private static void finalRound(String player1, String player2) {
        System.out.println(player1 + " and " + player2 + ", get ready for the final round!!");
        int move1 = getPlayerMove(player1);
        int move2 = getPlayerMove(player2);
        int result = compareMoves(move1, move2);
        if (result > 0) {
            System.out.println(player1 + " wins the final round!!");
            playerWins[Arrays.asList(playerNames).indexOf(player1)]++;
        } else if (result < 0) {
            System.out.println(player2 + " wins the final round!!");
            playerWins[Arrays.asList(playerNames).indexOf(player2)]++;
        } else {
            System.out.println("It's a tie in the final round!!");
        }
    }

    public static void showStats() {
        System.out.println("Player Stats:");
        for (int i = 0; i < NUM_PLAYERS; i++) {
            System.out.println(playerNames[i] + " wins: " + playerWins[i]);
        }
    }
}
