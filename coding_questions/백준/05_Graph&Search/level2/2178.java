// 미로 탐색
// https://www.acmicpc.net/problem/2178

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N, M;
    static int[] Map;
    static boolean[][] visit;
    static int[][] dist;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        scan.nextLine();
        Map = new String[N];
        for (int i = 0; i < N; i++) {
            Map[i] = scan.nextLine();
        }
        visit = new boolean[N][N];
        dist = new int[N][N];
        if(int i=0; i<N; i++) Arrays.fill(dist[i], -1);
    }

    static void bfs(int x, int y) {
        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[]{x,y});
        dist[x][y] = 0;

        while(!Q.isEmpty()) {
            int[] cur = Q.poll();

            for(int k=0; k<4; k++) {
                int nx = cur[0] + dir[k][0];
                int ny = cur[1] + dir[k][1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visit[nx][ny] || Map[nx][ny] == 0) continue;

                visit[nx][ny] = true;
                dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                Q.add(new int[]{nx,ny});
            }
        }

    }


    static void proceed() {

        bfs(0,0);
        System.out.println(sb);
    }
}
