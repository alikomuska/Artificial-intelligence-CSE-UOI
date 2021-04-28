public class Minimax
{
    static class Move {
        int row = -1;
        int col = -1;
        char symbol; // can be s or o
        int move_value;

        public String toString() {
            return "row:" + row + ", col:" + col + ", symbol: " + symbol + " Value: " + move_value;
        }
    };

    static char A = 'A', B = 'B';
    static int[] Aplace, Bplace = new int[2];

    static Boolean movesLeft(char board[][], int place[])
    {
        if (place[0] == 0 && place[1] == 0)
        {
            if ((board[place[0]][place[1] + 1] == 'w') || (board[place[0] + 1][place[1] + 1] == 'w')
                    || (board[place[0] + 1][place[1]] == 'w'))
            {
                return true;
            }
        }
        if (place[0] == 0 && place[1] == board[1].length)
        {
            if ((board[place[0]][place[1] - 1] == 'w') || (board[place[0] + 1][place[1] - 1] == 'w')
                    || (board[place[0] + 1][place[1]] == 'w'))
                    
            {
                return true;
            }
        }
        if (place[0] == board[0].length && place[1] == 0)
        {
            if ((board[place[0] - 1][place[1]] == 'w') || (board[place[0] - 1][place[1] + 1] == 'w')
                    || (board[place[0]][place[1] + 1] == 'w'))
            {
                return true;
            }
        }
        if (place[0] == 0 && place[1] == 0)
        {
            if ((board[place[0] - 1][place[1]] == 'w') || (board[place[0] - 1][place[1] - 1] == 'w')
                    || (board[place[0]][place[1] - 1] == 'w'))
            {
                return true;
            }
        }

        if(place[1]==0)
        {
            if((board[place[0]-1][place[1]]=='w')|| (board[place[0] - 1][place[1]+1] == 'w')
                    || (board[place[0]][place[1] + 1] == 'w') || (board[place[0] + 1][place[1] + 1] == 'w')
                    || (board[place[0] + 1][place[1]] == 'w'))
            {
                return true;
            }
        }
        if(place[1]==board[1].length)
        {
            if((board[place[0]-1][place[1]]=='w')|| (board[place[0] + 1][place[1]] == 'w') 
                    || (board[place[0] + 1][place[1] - 1] == 'w') || (board[place[0]][place[1] - 1] == 'w')
                    || (board[place[0] - 1][place[1] - 1] == 'w'))
            {
                return true;
            }
        }
        if(place[0]==0)
        {
            if((board[place[0]][place[1] + 1] == 'w') || (board[place[0] + 1][place[1] + 1] == 'w')
                    || (board[place[0] + 1][place[1]] == 'w') || (board[place[0] + 1][place[1] - 1] == 'w')
                    || (board[place[0]][place[1] - 1] == 'w'))
            {
                return true;
            }
        }
        if(place[0]==board[0].length)
        {
            if((board[place[0]-1][place[1]]=='w')|| (board[place[0] - 1][place[1]+1] == 'w')
                    || (board[place[0]][place[1] + 1] == 'w') || (board[place[0]][place[1] - 1] == 'w') 
                    || (board[place[0] - 1][place[1] - 1] == 'w'))
            {
                return true;
            }
        }
        
        if ((board[place[0] - 1][place[1]] == 'w') || (board[place[0] - 1][place[1] + 1] == 'w')
                || (board[place[0]][place[1] + 1] == 'w') || (board[place[0] + 1][place[1] + 1] == 'w')
                || (board[place[0] + 1][place[1]] == 'w') || (board[place[0] + 1][place[1] - 1] == 'w')
                || (board[place[0]][place[1] - 1] == 'w') || (board[place[0] - 1][place[1] - 1] == 'w'))
        {
            return true;
        }
        
        return false;
    }

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

    static int minimax(char board[][], int depth, Boolean isMax, int aplace[], int bplace[])
    {
        int score = evaluate(board, aplace, bplace);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (((movesLeft(board,aplace))&& (movesLeft(board, bplace))) == false)
            return 0;

        // If this maximizer's move
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    // Check if cell is empty
                    if (board[i][j] == 'w') {
                        // Make the move
                        board[i][j] = A;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board, depth + 1, !isMax, aplace,bplace));

                        // Undo the move
                        board[i][j] = 'w';
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    // Check if cell is empty
                    if (board[i][j] == 'w') {
                        // Make the move
                        board[i][j] = B;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board, depth + 1, !isMax, aplace, bplace));

                        // Undo the move
                        board[i][j] = 'w';
                    }
                }
            }
            return best;
        }
    }
}