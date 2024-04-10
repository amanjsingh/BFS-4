import java.util.LinkedList;
import java.util.Queue;

// TC - O(6*N*N)
// SC - O(N*N)

public class SnakesAndLadders {

    class Solution {
        public int snakesAndLadders(int[][] board) {
            int length = board.length;
            int[] flatBoard = new int[length * length];
            Queue<Integer> queue = new LinkedList<>();
            int totalMoves = 0;
            int flatBoardIndex = 0;
            boolean flag = true;
            int i = length - 1;
            int j = 0;
            while (flatBoardIndex < flatBoard.length) { //O(N*N)
                if (board[i][j] == -1) {
                    flatBoard[flatBoardIndex] = -1;
                } else {
                    flatBoard[flatBoardIndex] = board[i][j] - 1; // flatBoard[0 -> 35]
                }

                flatBoardIndex++;

                if (flag) {
                    j++;
                } else {
                    j--;
                }
                if (j >= length) {
                    flag = false;
                    j--;
                    i--;
                } else if (j < 0) {
                    flag = true;
                    j++;
                    i--;
                }
            }

            // O(6*N*N)
            queue.add(0);
            flatBoard[0] = -2; // Mark visited
            while (!queue.isEmpty()) { //O(N*N)
                int size = queue.size();
                while (size > 0) { // Process Level
                    int curr = queue.poll();
                    if (curr == flatBoard.length - 1) {
                        return totalMoves;
                    }
                    for (i = 1; i <= 6 && (curr + i < flatBoard.length); i++) { //O(6)
                        int newPosition = curr + i;
                        if (flatBoard[newPosition] != -2) {
                            if (flatBoard[newPosition] == -1) {
                                queue.add(newPosition);
                                flatBoard[newPosition] = -2; // Mark visited
                            } else {
                                if (flatBoard[flatBoard[newPosition]] != -2) {
                                    queue.add(flatBoard[newPosition]);
                                    flatBoard[newPosition] = -2; // Mark visited
                                    // flatboard[flatBoard[newPosition]] = -2; This will eliminate any future
                                    // shorter path
                                }
                            }
                        }
                    }
                    size--;
                }
                totalMoves++; // Moving in one level
            }
            return -1;
        }
    }
}