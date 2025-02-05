// 단지번호붙이기
// https://www.acmicpc.net/problem/2667

/*
 격자형
 boj 1012 - 유기농 배추
 boj 11724 - 연결 요소의 개수
 boj 4963 - 섬의 개수
 boj 3184 - 양
 boj 1937 - 욕심쟁이 판다
 boj 7576 - 토마토

 일반 그래프
 boj 2606 - 바이러스
 boj 11403 - 경로 찾기
 boj 11725 - 트리의 부모 찾기

 */



import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N, group_cnt;
    static String[] array;
    static boolean [][] visit;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static ArrayList<Integer> group;

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        scan.nextLine();
        array = new String[N];
        for (int i = 0; i < N; i++) {
            array[i] = scan.nextLine();
        }
        visit = new boolean[N][N];
    }

    // x, y를 갈 수 있다는 걸 알고 방문한 상태
    static void dfs(int x, int y) {
        // 단지에 속한 집의 개수 증가, visit 체크하기
        group_cnt++;
        visit[x][y] = true;

        // 인접한 집으로 새로운 방문하기
        for(int k=0;k<4;k++) {
            int nx = x + dir[k][0], ny = y + dir[k][1];
            if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if(visit[nx][ny] || array[nx].charAt(ny) == '0') continue;
            dfs(nx, ny);
        }
    }

    static void proceed() {
        group = new ArrayList<Integer>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visit[i][j] && array[i].charAt(j) == '1') {
                    // 갈 수 있는 칸인데, 이미 방문처리된, 즉 새롭게 만난 단지인 경우
                    group_cnt = 0;
                    dfs(i,j);
                    group.add(group_cnt);
                }
            }
        }

        Collections.sort(group);
        sb.append(group.size()).append('\n');
        for(int i = 0; i < group.size(); i++) {
            sb.append(group.get(i)).append('\n');
        }
        System.out.println(sb);
    }
}
