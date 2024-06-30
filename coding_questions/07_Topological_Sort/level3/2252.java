// 줄 세우기
// https://www.acmicpc.net/problem/2252

/*
    2623 음악 프로그램
    9470 Strahler 순서
    14676 영우는 사기꾼?
 */

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N, M;
    static ArrayList<Integer>[] adj;
    static int[] indeg;

    static void input() {
        // Adjacent List 생성 및 indegree 계산하기
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        adj = new ArrayList[N+1];
        indeg = new int[N+1];
        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++) {
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
            }
        }

        // 정렬될 수 있는 정점이 있다면?
        // 1. 정렬 결과에 추가하기
        // 2. 정점과 연결된 간선 제거하기
        // 3. 새롭게 '정렬될 수 있는' 정점
        while(!queue.isEmpty()) {
            int x = queue.poll();
            sb.append(x).append(" ");
            for(int y: adj[x]) {
                indeg[y]--;
                if(indeg[y] == 0) {
                    queue.add(y);
                }
            }
        }
        System.out.println(sb);
    }
}