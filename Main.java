package tictactoe;

/*

This is a functional TicTacToe app which can be used to facilitate a 2 player match.

This has been created as a part of my learning experience @ Hyperskill. Some of the methods
in the source code (ex: Analyzer()) served the purpose of passing specific stages of the
test based curriculum. Detailed explanations have been provided along with the methods.

Regards,

@author - Shubham Dasgupta


*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        ticTacToe.DisplayEmpty();
        ticTacToe.Move(2); // 2 is used to provide an initial seed to determine which player starting the game.
    }
}

class TicTacToe {
    private char[][] charArray = new char[3][3];
    private int[][] intArray = new int[3][3];

    /*Constructor to set the initial values of the char array to empty (representing empty fields on the TicTacToe board)*/
    public TicTacToe() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                charArray[i][j] = ' ';
            }
        }
        Converter();
    }

    /* Function to initialize values of the int array on the basis of the values of the char array
    Empty value -> 0, X -> -1 and O -> 1*/
    public void Converter() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (charArray[i][j] == 'X' || charArray[i][j] == 'x') {
                    intArray[i][j] = -1;
                } else if (charArray[i][j] == 'O' || charArray[i][j] == 'o') {
                    intArray[i][j] = 1;
                } else if (charArray[i][j] == ' '){
                    intArray[i][j] = 0;
                }
            }
        }
    }
    /*Given a string of character sequences containing X,O and empty values, initialize the
    char array. Used to pass a Hyperskill test.*/
    public void LineToField(String inputString) {
        int counter = 0;
        boolean checkWin = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                charArray[i][j] = inputString.charAt(counter);
                counter++;
            }
        }
        Converter();
    }


    /*Function to check a specific condition in the LineToField method where the input string is
    incorrect and both players have won. This is not supported by the laws of the game and this
    case is checked in this method.*/
    public boolean DualWinnerCheck() {


        boolean masterResult = false;

        boolean winForX = false;
        boolean winForY = false;

        if (intArray[0][0] + intArray[1][1] + intArray[2][2] == 3 || intArray[0][2] + intArray[1][1] + intArray[2][0] == 3) {
            winForY = true;
        }

        if (intArray[0][0] + intArray[1][1] + intArray[2][2] == -3 || intArray[0][2] + intArray[1][1] + intArray[2][0] == -3) {
            winForX = true;
        }

        for (int i = 0; i < 3; i++) {
            int sumCol = 0;
            int sumRow = 0;
            for (int j = 0; j < 3; j++) {

                sumRow += intArray[i][j];
                sumCol += intArray[j][i];

                if (sumCol == 3 || sumRow == 3) {
                    winForY = true;
                }

                if (sumCol == -3 || sumRow == -3) {
                    winForX = true;
                }
            }
        }

        if (winForX && winForY) {
            masterResult = true;
            return masterResult;
        } else {
            return false;
        }

    }

    /*Suuplementary method of LineToField method to check the state of the board. There can
    * be a maximum difference of 1 between the number of Xs and Os on the board at any point
    * of time in the game. */
    public boolean LegibilityCheck() {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                sum += intArray[i][j];

            }
        }
        if (sum == -1 || sum == 1 || sum == 0) {
            return true;
        } else {
            return false;
        }
    }


    /*Method to check if there are empty spaces on the board. Empty spaces imply that the
    * game may still be in progress ( implying that the winner has not been decided yet).*/
    public boolean CheckProgression() {

        boolean progression = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (charArray[i][j] == ' ') {
                    progression = true;
                    break;
                }
            }
        }
        return progression;
    }



    /*Method to display the board.*/
    public void Display() {
        System.out.println("---------");


        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(charArray[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.print("---------");
    }

    /*Method to determine if a winner has been decided in the game. If yes, prints the name
    * of thr winner.*/
    public boolean CheckWinner() {

        boolean hasWinner = false;

        if (intArray[0][0] + intArray[1][1] + intArray[2][2] == 3) {
            System.out.println("O wins");
            hasWinner = true;
            return hasWinner;
        } else if (intArray[0][0] + intArray[1][1] + intArray[2][2] == -3) {
            System.out.println("X wins");
            hasWinner = true;
            return hasWinner;
        } else if (intArray[0][2] + intArray[1][1] + intArray[2][0] == -3) {
            System.out.println("X wins");
            hasWinner = true;
            return hasWinner;
        } else if (intArray[0][2] + intArray[1][1] + intArray[2][0] == 3) {
            System.out.println("O wins");
            hasWinner = true;
            return hasWinner;
        }

        for (int i = 0; i < 3; i++) {
            int sumCol = 0;
            int sumRow = 0;
            for (int j = 0; j < 3; j++) {

                sumRow += intArray[i][j];
                sumCol += intArray[j][i];

                if (sumCol == 3 || sumRow == 3) {
                    System.out.println();
                    System.out.println("O wins");
                    hasWinner = true;
                    return hasWinner;
                }

                if (sumCol == -3 || sumRow == -3) {
                    System.out.println();
                    System.out.println("X wins");
                    hasWinner = true;
                    return hasWinner;
                }
            }
        }
        return hasWinner;
    }

    /*Method to get the next move in the game from the corresponding player.*/
    public void Move(int n) {

        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println();

            System.out.print("Enter the cordinates : ");

            try {

                int i1 = scanner.nextInt();
                int j1 = scanner.nextInt();

                if (false) {
                    System.out.println("Breaking");

                }

                if (i1 > 3 || j1 > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                j1--;
                i1--;
                int i = 3 - j1 - 1;
                int j = i1;


                if (charArray[i][j] == ' ') {

                    if (n % 2 == 0) {
                        charArray[i][j] = 'X';
                        Converter();
                        break;
                    } else {
                        charArray[i][j] = 'O';
                        Converter();
                        break;
                    }

                } else {

                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }
            } catch (Exception e) {

                System.out.println("You should enter numbers!");
                Move(n);
            }
        }



        System.out.println();
        Display();

        if (CheckWinner()) {
            return;
        }

        if (CheckProgression()) {
            Move(++n); // Important, recursive call for the next player's turn.
        } else {
            System.out.println();
            /*DisplayIntArray();
            System.out.println();*/
            System.out.println("Draw");
            return;
        }
    }

    /*Method to display the initial empty state of the board.*/
    public void DisplayEmpty() {
        System.out.println("---------");
        System.out.println("|       |");
        System.out.println("|       |");
        System.out.println("|       |");
        System.out.println("---------");
    }



    /*NOTE : This method was used to pass a specific stage in the curriculum.
    Not used in the working of the game.*/
    public void GetNextMove() {


        Scanner scanner = new Scanner(System.in);


        while (true) {

            System.out.print("Enter the cordinates : ");

            try {

                int i1 = scanner.nextInt();
                int j1 = scanner.nextInt();

                if (i1 > 3 || j1 > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                j1--;
                i1--;
                int i = 3 - j1 - 1;
                int j = i1;


                if (charArray[i][j] == ' ') {

                    charArray[i][j] = 'X';
                    break;


                } else {

                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }
            } catch (NumberFormatException e) {

                System.out.println("You should enter numbers!");
                continue;
            }
        }
    }


    /*NOTE : This method was used to pass a specific stage in the curriculum.
    Not used in the working of the game.*/
    public void Analyzer() {

        boolean alreadyWon = DualWinnerCheck();

        boolean isPossible = true;

        isPossible = LegibilityCheck();

        if (!isPossible || alreadyWon) {
            System.out.println("Impossible");
            return;
        }

        boolean hasWinner = CheckWinner();

        if (!hasWinner) {


            boolean inProgress = true;

            inProgress = CheckProgression();

            if (inProgress) {

                System.out.println("Game not finished");
                return;
            } else {
                System.out.println("Draw");
                return;
            }

        }
    }
}

