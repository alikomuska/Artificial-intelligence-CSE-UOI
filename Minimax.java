//O paikths mporei na kineitai mono ena block gyrw tou

import java.util.Arrays;
import java.util.Scanner;

public class Minimax
{
    // class gia thn kinhsh tou kathe paikth
    static class Move { 
        int row = -1;
        int col = -1;
        char symbol; 
        int move_value;

        public String toString() {
            return "row:" + row + ", col:" + col + ", symbol: " + symbol + " Value: " + move_value;
        }
    };

    static char A = 'A', B = 'B';
    static boolean Max = true;

    // Vohthitikh methodos gia na vlepoume an h kinhsh enws paikth einai ektos tou
    // board
    static Boolean outOfBounds(char arr[][], int row, int col) { 
        boolean tooSmall = (row < 0 || col < 0);
        boolean tooBig = (row > (arr.length - 1) || (col > arr[0].length - 1));

        return (tooBig || tooSmall);
    }
    
    //Methodos pou koitaei an o paikths exei eleutheres kinhseis perimetrika tou
    static Boolean movesLeft(char board[][], int place[]) { 
        if (board[0].length - 1 < place[0] || board[1].length - 1 < place[1]) {
            System.exit(0);
        }
        int offsetRow, offsetCol;
        int row = place[0];
        int col = place[1];
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            offsetRow = row + rowOffset;
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                offsetCol = col + colOffset;
                if (!outOfBounds(board, offsetRow, offsetRow) && !(rowOffset == 0 && colOffset == 0)
                        && !(offsetRow > board[0].length - 1 || offsetCol > board[1].length - 1)
                        && !(offsetRow < 0 || offsetCol < 0)) {
                    if ((board[offsetRow][offsetCol] == '+')||(board[offsetRow][offsetCol] == B))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Methodos pou elegxei an kapoios apo tous dyo paiktes den exei ypoloipomenes kinhseis
    //tote kerdizei o allos
    static int evaluate(char board[][], int aplace[], int bplace[])
    {
        if(!movesLeft(board, bplace))
        {
            return +10;
        }
        if(!movesLeft(board, aplace))
        {
            return -10;
        }
        return 0;
    }

    //Methodos ylopoihshs tou minimax, anadromika
    static int minimax(char board[][], int depth, Boolean isMax, int aplace[], int bplace[])
    {
        int score = evaluate(board, aplace, bplace);

        if (score == 10)
            return score;

        if (score == -10)
            return score;

        if (((movesLeft(board,aplace))&& (movesLeft(board, bplace))) == false)
            return 0;//gfgdfgd

        // If this max move
        if (isMax) {
            int best = -1000;

            // Traverse cells adjacent to max
            int offsetRow, offsetCol;
            int row = aplace[0];
            int col = aplace[1];
            for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
                offsetRow = row + rowOffset;
                for (int colOffset = -1; colOffset <= 1; colOffset++) {
                    offsetCol = col + colOffset;
                    if (!outOfBounds(board, offsetRow, offsetRow) && !(rowOffset == 0 && colOffset == 0)
                            && !(offsetRow > board[0].length - 1 || offsetCol > board[1].length - 1)
                            && !(offsetRow < 0 || offsetCol < 0)) {
                        if (board[offsetRow][offsetCol] == '+') {
                            // Make the move
                            board[offsetRow][offsetCol] = A;

                            best = Math.min(best, minimax(board, depth + 1, !isMax, aplace, bplace));
 
                            // Undo the move
                            board[offsetRow][offsetCol] = '+';
                        }
                    }
                }
            }
            return best;
        }

        // If this min move
        else {
            int best = -1000;

            // Traverse cells adjacent to max
            int offsetRow, offsetCol;
            int row = bplace[0];
            int col = bplace[1];
            for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
                offsetRow = row + rowOffset;
                for (int colOffset = -1; colOffset <= 1; colOffset++) {
                    offsetCol = col + colOffset;
                    if (!outOfBounds(board, offsetRow, offsetRow) && !(rowOffset == 0 && colOffset == 0)
                            && !(offsetRow > board[0].length - 1 || offsetCol > board[1].length - 1)
                            && !(offsetRow < 0 || offsetCol < 0)) {
                        if (board[offsetRow][offsetCol] == '+') {
                            // Make the move
                            board[offsetRow][offsetCol] = B;

                            best = Math.min(best, minimax(board, depth + 1, !isMax, aplace, bplace));

                            // Undo the move
                            board[offsetRow][offsetCol] = '+';
                        }
                    }
                }
            }
            return best;
        }
    }

    //Methodos pou kanei thn kalyterh kinhsh pou vrihke o minimax
    static Move findBestMove(char[][] board, Boolean isMax, int aplace[], int bplace[]) {

        Move bestMove = new Move();
        int bestVal;

        if (isMax)
            bestVal = -1000;
        else 
            bestVal = 1000; 

        // Traverse cells adjacent to max
            int offsetRow, offsetCol;
            int row = aplace[0];
            int col = aplace[1];
            for (int rowOffset = -1; rowOffset <= 1; rowOffset++)
            {
                offsetRow = row + rowOffset;
                for (int colOffset = -1; colOffset <= 1; colOffset++)
                {
                    offsetCol = col + colOffset;
                    if (!outOfBounds(board, offsetRow, offsetRow) && !(rowOffset == 0 && colOffset == 0)
                            && !(offsetRow > board[0].length - 1 || offsetCol > board[1].length - 1)
                            && !(offsetRow < 0 || offsetCol < 0))
                    {

                        if (board[offsetRow][offsetCol] == '+')
                        {

                            board[offsetRow][offsetCol] = A;
                            int move_value =  minimax(board, 0, isMax, aplace, bplace);

                            board[offsetRow][offsetCol] = '+';

                            if (isMax)
                            {
                                if (move_value >= bestVal)
                                {
                                    bestMove.row = offsetRow;
                                    bestMove.col = offsetCol;
                                    board[aplace[0]][aplace[1]] = '*';
                                    bestMove.symbol = A;
                                    bestMove.move_value = move_value;
                                    bestVal = move_value;
                                }
                            }
                            else
                            {
                                if (move_value < bestVal)
                                {
                                    bestMove.row = offsetRow;
                                    bestMove.col = offsetCol;
                                    board[aplace[0]][aplace[1]] = '*';
                                    bestMove.symbol = A;
                                    bestMove.move_value = move_value;
                                    bestVal = move_value;
                                }
                            }
                        }
                    }
                }
            }
        return bestMove;
    }

    public static void main(String[] args) {
        Scanner board_input = new Scanner(System.in);
        System.out.println("Please give the size of the board separated with comma e.g. 1,2");
        String inp = board_input.nextLine();
        String[] coords = inp.split(",");
        int N = Integer.parseInt(coords[0]);
        int M = Integer.parseInt(coords[1]);
        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                board[i][j]='+';
            }
        }
        printBoard(board);
        int[] Aplace = new int[]{0,0};
        board[0][0] = A;
        int[] Bplace = new int[2];
        Scanner playerB_input = new Scanner(System.in);
        System.out.println("Please give starting point of player B separated with comma e.g. 1,2");
        String b_inp = playerB_input.nextLine();
        String[] b_coords = b_inp.split(",");
        int bi = Integer.parseInt(b_coords[0]);
        int bj = Integer.parseInt(b_coords[1]);
        board[bi][bj] = B;
        Bplace[0] = bi;
        Bplace[1] = bj;
        board[Bplace[0]][Bplace[1]] = '*';
        while ( (movesLeft(board,Aplace))&& (movesLeft(board, Bplace)) ) {

            if (Max) {
                System.out.println("Computer's Turn: ");
                Move bestMove = findBestMove(board, true, Aplace, Bplace);
                board[bestMove.row][bestMove.col] = bestMove.symbol;
                Aplace[0]=bestMove.row;
                Aplace[1] = bestMove.col;

                printBoard(board);
                if (evaluate(board, Aplace, Bplace) == 10) {
                    System.out.println("You Lose. :(");
                    return;
                }
                Max = false;

            } else {
                System.out.println("Player's Turn: ");
                Scanner player_input = new Scanner(System.in);
                System.out.println("Please give the coordinates for your move separated with comma e.g. 1,2");
                String input = player_input.nextLine();
                String[] coordinates = input.split(",");
                int i = Integer.parseInt(coordinates[0]);
                int j = Integer.parseInt(coordinates[1]);
                char symbol = B;
                board[Bplace[0]][Bplace[1]] = '*';
                board[i][j] = symbol;
                Bplace[0] = i;
                Bplace[1] = j;

                printBoard(board);
                if (evaluate(board, Aplace, Bplace) == -10) {
                    System.out.println("You Win!");
                    return;
                }
                Max = true;
            }
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println("____BOARD____");
        for (char[] row : board)
            System.out.println(Arrays.toString(row));
        System.out.println("\n");
    }
}