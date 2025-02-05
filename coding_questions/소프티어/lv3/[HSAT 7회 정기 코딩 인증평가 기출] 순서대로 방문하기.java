import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] grid;
    static int[][] targets;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 격자 입력
        grid = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 목표 지점 입력
        targets = new int[m][2];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            targets[i][0] = Integer.parseInt(st.nextToken()) - 1;
            targets[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        // DFS 시작
        boolean[][] visited = new boolean[n][n];
        visited[targets[0][0]][targets[0][1]] = true;
        dfs(targets[0][0], targets[0][1], 0, visited);

        System.out.println(answer);
    }

    static void dfs(int x, int y, int targetIdx, boolean[][] visited) {
        if(x == targets[targetIdx][0] && y == targets[targetIdx][1]) {
            if(targetIdx == m-1) {
                answer++;
                return;
            }
            dfs(x, y, targetIdx+1, visited);
            return;
        }

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if(grid[nx][ny] == 1 || visited[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, targetIdx, visited);
            visited[nx][ny] = false;
        }
    }
}