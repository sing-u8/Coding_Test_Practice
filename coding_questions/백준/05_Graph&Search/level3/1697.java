// 숨바꼭질
// https://www.acmicpc.net/problem/1697

/*
    12851번. 숨바꼭질 2
    13549번. 숨바꼭질 3
    13913번. 숨바꼭질 4
    1389번 케빈 베이컨의 6단계 법칙
    5567번 결혼식
 */

// visit을 이용해서 방문한 것들을 제외하는 방식도 가능

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N, K, count = 0, ans = Integer.MAX_VALUE;
    static Map<Integer, Integer> Map = new HashMap<Integer, Integer>();
    static int[] dir = {1, 1, 2};

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        K = scan.nextInt();
    }

    static void bfs(int start, int end) {
        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[]{start, 0});
        Map.put(start, 0);

        while(!Q.isEmpty()) {
            int[] cur = Q.poll();

            if(cur[0] == end) {
                ans = Math.min(ans, cur[1]);
            }

            if(Map.containsKey(cur[0])) count = Map.get(cur[0]) + 1;
            else count ++;

            for(int k=0; k<3; k++) {
                int np = 0;
                switch (k) {
                    case 0: np = cur[0] - dir[k]; break;
                    case 1: np = cur[0] + dir[k]; break;
                    case 2: np = cur[0] * dir[k]; break;
                    default: break;
                };
                /*
                int np = switch (k) {
                    case 0 -> cur[0] - dir[k];
                    case 1 -> cur[0] + dir[k];
                    case 2 -> cur[0] * dir[k];
                    default -> 0;
                };
                 */
                if(np < 0 || np > 100000) continue;
                if(Map.containsKey(np) && Map.get(np) <= count) continue;

                Q.add(new int[]{np, count});
                Map.put(np, count);
            }
        }
    }

    static void proceed() {
        bfs(N,K);
        System.out.println(ans);
    }
}
