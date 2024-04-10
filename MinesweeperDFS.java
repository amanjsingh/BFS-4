// TC - O(MN)
// SC - O(MN)

public class MinesweeperDFS {
    class Solution {
        private final int[][] dirs = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
                { 1, 1 } };

        public char[][] updateBoard(char[][] board, int[] click) {
            if (board[click[0]][click[1]] == 'M') {
                board[click[0]][click[1]] = 'X';
                return board;
            }

            dfs(board, click[0], click[1]);
            return board;
        }

        private void dfs(char[][] board, int row, int col) {
            if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
                return;
            }

            if (board[row][col] != 'E') {
                return;
            }

            board[row][col] = 'B';

            int countMines = countMines(board, row, col);
            if (countMines != 0) {
                board[row][col] = (char) (countMines + '0');
            } else {
                for (int[] dir : dirs) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    dfs(board, newRow, newCol);
                }
            }
        }

        private int countMines(char[][] board, int currRow, int currCol) {
            int count = 0;
            for (int[] dir : dirs) {
                int newRow = currRow + dir[0];
                int newCol = currCol + dir[1];
                if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                    if (board[newRow][newCol] == 'M') {
                        count++;
                    }
                }
            }
            return count;
        }
    }
}
