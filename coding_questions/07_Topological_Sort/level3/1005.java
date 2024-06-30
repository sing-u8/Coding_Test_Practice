// ACM Craft
// https://www.acmicpc.net/problem/1005
// **

/*
    1516 게임개발
    2056 작업
    2637 장난감 조립
 */

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int Try = scan.nextInt();
        while(Try > 0) {
            Try--;
            input();
            proceed();
        }
    }

    static int N, K;
    static int[] indeg, T_done, T;
    static ArrayList<Integer>[] adj;

    static void input() {
        N = scan.nextInt();
        K = scan.nextInt();
        adj = new ArrayList[N+1];
        indeg = new int[N+1];
        T = new int[N+1];
        T_done = new int[N+1];
        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            T[i] = scan.nextInt();
        }
        for(int i = 0; i < K; i++) {
            int x = scan.nextInt(), y = scan.nextInt();
            adj[x].add(y);
            indeg[y]++;
        }

    }


    static void proceed() {
        Deque<Integer> queue = new LinkedList<>();
        // 제일 앞에 '정렬될 수 있는 정점 찾기'
        for(int i = 1; i <= N; i++) {
            if(indeg[i] == 0) {
                queue.add(i);
                T_done[i] = T[i];
            }
        }

        // 정렬될 수 있는 정점이 있다면?
        // 1. 정렬 결과에 추가하기
        // 2. 정점과 연결된 간선 제거하기
        // 3. 새롭게 '정렬될 수 있는' 정점
        while(!queue.isEmpty()) {
            int x = queue.poll();
            for(int y: adj[x]) {
                indeg[y]--;
                if(indeg[y] == 0) {
                    queue.add(y);
                }
                T_done[y] = Math.max(T_done[y], T_done[x] + T[y]);
            }
        }
        int W = scan.nextInt();
        System.out.println(T_done[W]);
    }
}