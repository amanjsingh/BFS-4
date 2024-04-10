import java.util.LinkedList;
import java.util.Queue;

// TC - O(MN)
// SC - O(MN)

public class MinesweeperBFS {
    class Solution {
        private final int[][] dirs = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
                { 1, 1 } };

        public char[][] updateBoard(char[][] board, int[] click) {
            if (board[click[0]][click[1]] == 'M') {
                board[click[0]][click[1]] = 'X';
                return board;
            }

            Queue<int[]> queue = new LinkedList<>();
            queue.add(click);
            board[click[0]][click[1]] = 'B';
            while (!queue.isEmpty()) {
                int[] currCell = queue.poll();
                int currRow = currCell[0];
                int currCol = currCell[1];
                int countMines = countMines(board, currRow, currCol);
                if (countMines != 0) {
                    board[currRow][currCol] = (char) (countMines + '0');
                    continue;
                } else {
                    for (int[] dir : dirs) {
                        int newRow = currRow + dir[0];
                        int newCol = currCol + dir[1];
                        if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                            if (board[newRow][newCol] == 'E') {
                                board[newRow][newCol] = 'B';
                                queue.add(new int[] { newRow, newCol });
                            }
                        }
                    }
                }
            }
            return board;
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
