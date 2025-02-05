// 탈출
// https://www.acmicpc.net/problem/3055

/*
    BOJ 7569 토마토
    BOJ 2644 촌수 계산
 */

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int R, C;
    static String[] Map;
    static int[][] dist_water, dist_hedgehog;
    static boolean[][] visited;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

    static void input() {
        Scanner scan = new Scanner(System.in);
        R = scan.nextInt();
        C = scan.nextInt();
        scan.nextLine();
        Map = new String[R];
        for(int i = 0; i < R; i++) {
            Map[i] = scan.nextLine();
        }
        visited = new boolean[R][C];
        dist_water = new int[R][C];
        dist_hedgehog = new int[R][C];

    }

    // 모든 물들을 시작으로 동시에 BFS 시작
    static void bfs_water() {
        Queue<Integer> Q = new LinkedList<>();
        // 모든 물의 위치를 Q에 전부 넣어주기
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                // dist_water와 visit배열 초기화
                dist_water[i][j] = -1;
                visited[i][j] = false;
                if(Map[i].charAt(j) == '*') {
                    Q.add(i);
                    Q.add(j);
                    dist_water[i][j] = 0;
                    visited[i][j] = true;
                }
            }
        }

        // BFS 과정 시작
        while(!Q.isEmpty()) {
            int x = Q.poll();
            int y = Q.poll();
            for(int k = 0; k < 4; k++) {
                int nx = x + dir[k][0], ny = y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= R || ny >= C ) continue;
                if(Map[nx].charAt(ny) != '.') continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                dist_water[nx][ny] = dist_water[x][y] + 1;
                Q.add(nx);
                Q.add(ny);
            }
        }

    }

    static void bfs_hedgehog() {
        Queue<Integer> Q = new LinkedList<>();
        // 고슴도치 위치를 Q에 넣어주기
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                // dist_hedgehog와 visit 배열 초기화
                dist_hedgehog[i][j] = -1;
                visited[i][j] = false;
                if(Map[i].charAt(j) == 'S') {
                    Q.add(i);
                    Q.add(j);
                    dist_hedgehog[i][j] = 0;
                    visited[i][j] = true;
                }
            }
        }

        while(!Q.isEmpty()) {
            int x = Q.poll();
            int y = Q.poll();
            for(int k=0; k<4; k++) {
                int nx = x + dir[k][0], ny = y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if(Map[nx].charAt(ny) != '.' && Map[nx].charAt(ny) != 'D') continue;
                if(dist_water[nx][ny] != -1 && dist_water[nx][ny] <= dist_hedgehog[x][y] + 1) continue;
                if(visited[nx][ny]) continue;
                Q.add(nx);
                Q.add(ny);
                visited[nx][ny] = true;
                dist_hedgehog[nx][ny] = dist_hedgehog[x][y] + 1;
            }
        }
    }

    static void proceed() {
        bfs_water();
        bfs_hedgehog();

        // 탈출구 'D'에 대한 결과를 통해 정답 출력하기
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(Map[i].charAt(j) == 'D') {
                    if(dist_hedgehog[i][j] == -1) System.out.print("KAKTUS");
                    else System.out.print(dist_hedgehog[i][j]);
                }
            }
        }

    }
}
