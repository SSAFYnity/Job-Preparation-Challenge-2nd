import java.util.*;

public class 사라지는발판_김수빈 {
    public int playGame(int[][] board, int[] currentLoc, int[] opponentLoc) {
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};  // 방향
        int w = board.length;
        int h = board[0].length;

        int x = currentLoc[0];
        int y = currentLoc[1];

        // 현재 플레이어가 이동할 수 있는지 확인
        boolean canMove = false;
        for (int[] d : directions) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (0 <= nx && nx < w && 0 <= ny && ny < h && board[nx][ny] == 1) {
                canMove = true;
                break;
            }
        }
        // 현재 플레이어가 움직일 수 없는 경우 패배하며 이동 횟수 0을 반환
        if (!canMove) {
            return 0;
        }

        boolean win = false; // 이길 수 있는지 추적
        int maxMove = 0;     // 패배 시 최대 이동 횟수
        int minMove = Integer.MAX_VALUE; // 승리 시 최소 이동 횟수

        board[x][y] = 0;  // 현재 위치의 발판을 없앰

        // 4개의 방향으로 이동을 시도
        for (int[] d : directions) {
            int nx = x + d[0];
            int ny = y + d[1];

            // 이동 가능한 경우에만 처리
            if (0 <= nx && nx < w && 0 <= ny && ny < h && board[nx][ny] == 1) {
                // 상대방의 이동 결과를 받아옴 (재귀 호출)
                int moveCount = playGame(board, opponentLoc, new int[]{nx, ny});

                // 현재 플레이어가 이길 수 있는 경우
                if (!win) {
                    win = true;
                    minMove = moveCount + 1; // 이길 수 있는 상황이므로 minMove에 +1을 추가
                } else {
                    minMove = Math.min(minMove, moveCount + 1);
                }
                maxMove = Math.max(maxMove, moveCount + 1);
            }
        }

        board[x][y] = 1;  // 원래대로 발판을 복구

        // 이길 수 있는 경우에는 최소 이동 횟수, 그렇지 않은 경우 최대 이동 횟수를 반환
        return win ? minMove : maxMove;
    }

    // 주어진 함수에서 solution을 통해 호출되는 방식
    class Solution {
        public int solution(int[][] board, int[] aloc, int[] bloc) {
            return playGame(board, aloc, bloc);
        }
    }

    public static void main(String[] args) {
        사라지는발판_김수빈 outerClass = new 사라지는발판_김수빈();
        Solution solutionInstance = outerClass.new Solution();

        int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[] aloc = {1, 0};
        int[] bloc = {1, 2};

        int result = solutionInstance.solution(board, aloc, bloc);
        System.out.println("최종 이동 횟수: " + result);  // 기대하는 결과는 5
    }
}
