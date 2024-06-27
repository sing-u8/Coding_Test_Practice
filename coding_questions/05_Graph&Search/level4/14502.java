// 연구소
// https://www.acmicpc.net/problem/14502
// ******
// multisource BFS

// 벽을 놓았을 때, 최대 안전지역을 알 수 있는 특정 방법을 알 수 없다.
// 벽을 일일히 놓으면서 최대값을 구해야할 것 같음 --> 완전 탐색

// 벽을 3개 놓고 나서 바이러스가 퍼질 수 있는 영역을 채우고
// 안전지대 영역 계산하기

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N, M, B=0, ans=0;
    static int[][] Map;
    static int[][] blank;
    static boolean[][] visit;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        blank = new int[N*M][2];
        Map = new int[N][M];
        visit = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                Map[i][j] = scan.nextInt();
            }
        }
    }

    // 바이러스 퍼뜨리기
    static void bfs() {
        Queue<Integer> Q = new LinkedList<>();
        // 모든 바이러스가 시작점으로 가능하니까, 전부 큐에 넣어준다.
        // bfs를 호출할 때마다 실행되야하니 따로 뺴는 것도 괜찮을 듯
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visit[i][j] = false;
                if(Map[i][j] == 2) {
                    Q.add(i);
                    Q.add(j);
                    visit[i][j] = true;
                }
            }
        }

        // BFS 과정
        while(!Q.isEmpty()) {
            int x = Q.poll(), y = Q.poll();
            for(int k = 0; k < 4; k++) {
                int nx = x + dir[k][0], ny = y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(Map[nx][ny] != 0) continue;
                if(visit[nx][ny]) continue;
                visit[nx][ny] = true;
                Q.add(nx);
                Q.add(ny);
            }
        }

        // 탐색이 종료된 시점이니, 안전 영역의 넓이를 계산하고, 정답을 갱신한다.
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(Map[i][j] == 0 && !visit[i][j]) cnt++;
            }
        }
        ans = Math.max(ans, cnt);
    }

    // idx 번째 빈 캄에 벽을 세울지 말지 결정해야하고, 이전까지 selected_cnt개의 벽을 세웠다.
    static void dfs(int idx, int selected_cnt) {
        if(selected_cnt == 3) {
            bfs();
            return;
        }
        if(idx >= B) return; // 더 이상 세울 수 있는 벽이 없는 상태

        Map[blank[idx][0]][blank[idx][1]] = 1;
        dfs(idx+1, selected_cnt+1);

        Map[blank[idx][0]][blank[idx][1]] = 0;
        dfs(idx+1, selected_cnt);
    }

    static void proceed() {
        // 모든 가능한 영역의 위치를 먼저 모으기
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(Map[i][j] == 0) {
                    blank[B][0] = i;
                    blank[B][1] = j;
                    B++;
                }
            }
        }

        dfs(0, 0);
        System.out.println(ans);
    }
}
