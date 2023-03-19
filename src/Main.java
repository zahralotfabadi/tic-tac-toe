import java.util.*;

public class Main {
    static Scanner input = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";


    public static void main(String[] args) {

        String[][] matrix = new String[4][4];
        System.out.println("enter number=> player 2 is human or computer :");
        int number = input.nextInt();
        if (number == 1) {
            System.out.println("player 2 is Human");
        } else if (number == 2) {
            System.out.println("player 2 is computer");
        }
        //Continue the game until the result of the game is determined
        //@param start Continue the game until the result of the game is determined
        String valuable = "R";
        while (valuable.equals("R")) {
            block(matrix, makeMatrix(matrix));
            print(matrix);
            int moveCount = 0;
            while (true) {
                moveCount++;
                System.out.println("enter player1: ");
                String player1 = input.next();
                input.nextLine();
                boolean found = false;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (player1.equals(matrix[i][j])) {
                            matrix[i][j] = ANSI_CYAN + "\tX" + ANSI_RESET;
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Invalid input! Try again.");
                    continue;
                }
                print(matrix);
                checkWin(matrix);
                if (checkWin(matrix) == 0) {
                    break;
                }
                if (moveCount == 7) {
                    System.out.println("it's a tie!");
                    break;
                }
                if (number == 1) {
                    human(matrix);
                } else if (number == 2) {
                    computer(matrix);
                }
                print(matrix);
                checkWin(matrix);
                if (checkWin(matrix) == 0) {
                    break;
                }
            }
            System.out.println("If you want to play again,enter R");
            valuable = input.next();
        }

        //@param end Continue the game until the result of the game is determined


    }

    // print table
    public static void print(String[][] matrix) {
        System.out.println("  ________________________________ ");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%4s\t|", matrix[i][j]);
            }
            System.out.println(" \n ________________________________ ");
        }
    }

    public static ArrayList<Integer> makeMatrix(String[][] matrix) {
        ArrayList<Integer> list1 = new ArrayList<>();
        int numbers = 1;
        // creating a table with a matrix
        /*
        @param start creating a table with a matrix
        */
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String str = String.valueOf(numbers);
                matrix[i][j] = str;
                numbers++;
                list1.add(numbers - 1);
            }
        }
        /*
          @param end creating a table with a matrix
         */
        return list1;
    }


    //block randomly
    public static void block(String[][] matrix, ArrayList<Integer> list1) {
        Random generator = new Random();
        int num1, num2;
        int count = 0;
        //Random until three houses in the table are blocked
        while (count < 3) {
            num1 = generator.nextInt(4);
            num2 = generator.nextInt(4);
            if (!Objects.equals(matrix[num1][num2], ANSI_RED + "\t#" + ANSI_RESET)) {
                matrix[num1][num2] = ANSI_RED + "\t#" + ANSI_RESET;
                count++;
            } else {
                int inter = Integer.parseInt(matrix[num1][num2]);
                list1.remove((inter - 1));
            }
        }
    }

    // This function is used if the second player is a human
    public static void human(String[][] matrix) {
        int counter = 1;
        while (counter != 0) {
            System.out.println("enter player2: ");
            String player2 = input.nextLine();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (matrix[i][j].equals(player2)) {
                        matrix[i][j] = ANSI_PURPLE + "\tO" + ANSI_RESET;
                        counter = 0;
                        break;
                    }
                }
                if (counter == 0) {
                    break;
                }
            }
            if (counter == 1) {
                System.out.println("Invalid input! Try again.");
            }
        }
    }

    // This function is used if the second player is a computer
    public static void computer(String[][] matrix) {
        Random playerTwo = new Random();
        int count1 = 1;
        while (count1 != 0) {
            String player2 = String.valueOf(playerTwo.nextInt(16) + 1);
            System.out.println("player 2 is :" + player2);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (matrix[i][j].equals(player2)) {
                        matrix[i][j] = ANSI_PURPLE + "\tO" + ANSI_RESET;
                        count1 = 0;
                        break;
                    }
                }
                if (count1 == 0) {
                    break;
                }
            }
            if (count1 == 1) {
                System.out.println("Invalid input! Try again computer");
            }
        }
    }

    //Determines the outcome of the game and checks the win or loss
    public static int checkWin(String[][] matrix) {
        int count2 = 1;
        //check the board horizontally
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j < 4; j++) {
                if (matrix[i][j - 2].equals(matrix[i][j]) && matrix[i][j - 1].equals(matrix[i][j]) && matrix[i][j].equals(ANSI_CYAN + "\tX" + ANSI_RESET)) {
                    System.out.println("The player1 wins");
                    count2 = 0;
                    break;
                } else if (matrix[i][j - 2].equals(matrix[i][j]) && matrix[i][j - 1].equals(matrix[i][j]) && matrix[i][j].equals(ANSI_PURPLE + "\tO" + ANSI_RESET)) {
                    System.out.println("The player2 wins");
                    count2 = 0;
                    break;
                }
            }
        }
        //check the board vertically
        for (int i = 2; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (matrix[i - 2][j].equals(matrix[i][j]) && matrix[i - 1][j].equals(matrix[i][j]) && matrix[i][j].equals(ANSI_CYAN + "\tX" + ANSI_RESET)) {
                    System.out.println("The player1 wins");
                    count2 = 0;
                    break;
                } else if (matrix[i - 2][j].equals(matrix[i][j]) && matrix[i - 1][j].equals(matrix[i][j]) && matrix[i][j].equals(ANSI_PURPLE + "\tO" + ANSI_RESET)) {
                    System.out.println("The player2 wins");
                    count2 = 0;
                    break;
                }
            }
        }
        //check the main diameter and its parallel
        if (matrix[1][1].equals(matrix[2][2]) && (matrix[1][1].equals(matrix[3][3]) || matrix[1][1].equals(matrix[0][0]))) {
            if (matrix[1][1].equals(ANSI_CYAN + "\tX" + ANSI_RESET)) {
                System.out.println("The player1 wins");
            } else if (matrix[1][1].equals(ANSI_PURPLE + "\tO" + ANSI_RESET)) {
                System.out.println("The player2 wins");
            }
            count2 = 0;
        } else if (matrix[1][0].equals(matrix[2][1]) && matrix[2][1].equals(matrix[3][2])) {
            if (matrix[1][0].equals(ANSI_CYAN + "\tX" + ANSI_RESET)) {
                System.out.println("The player1 wins");
            } else if (matrix[1][0].equals(ANSI_PURPLE + "\tO" + ANSI_RESET)) {
                System.out.println("The player2 wins");
            }
            count2 = 0;
        } else if (matrix[0][1].equals(matrix[1][2]) && matrix[1][2].equals(matrix[2][3])) {
            if (matrix[0][1].equals(ANSI_CYAN + "\tX" + ANSI_RESET)) {
                System.out.println("The player1 wins");
            } else if (matrix[0][1].equals(ANSI_PURPLE + "\tO" + ANSI_RESET)) {
                System.out.println("The player2 wins");
            }
            count2 = 0;
        }


        //Check the sub diameter and its parallel

        if (matrix[1][2].equals(matrix[2][1]) && (matrix[1][2].equals(matrix[0][3]) || matrix[1][2].equals(matrix[3][0]))) {
            if (matrix[1][2].equals(ANSI_CYAN + "\tX" + ANSI_RESET)) {
                System.out.println("The player1 wins");
            } else if (matrix[1][2].equals(ANSI_PURPLE + "\tO" + ANSI_RESET)) {
                System.out.println("The player2 wins");
            }
            count2 = 0;
        }
        if (matrix[1][3].equals(matrix[2][2]) && matrix[2][2].equals(matrix[3][1])) {
            if (matrix[2][2].equals(ANSI_CYAN + "\tX" + ANSI_RESET)) {
                System.out.println("The player1 wins");
            } else if (matrix[2][2].equals(ANSI_PURPLE + "\tO" + ANSI_RESET)) {
                System.out.println("The player2 wins");
            }
            count2 = 0;
        }
        if (matrix[0][2].equals(matrix[1][1]) && matrix[1][1].equals(matrix[2][0])) {
            if (matrix[1][1].equals(ANSI_CYAN + "\tX" + ANSI_RESET)) {
                System.out.println("The player1 wins");
            } else if (matrix[1][1].equals(ANSI_PURPLE + "\tO" + ANSI_RESET)) {
                System.out.println("The player2 wins");
            }
            count2 = 0;
        }
        return count2;

    }

}