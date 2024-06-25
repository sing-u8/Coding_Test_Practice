// DFS와 BFS
// https://www.acmicpc.net/problem/1260

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N, M, V;
    static ArrayList<Integer>[] nums;
    static boolean[] bfs_visited;
    static boolean[] dfs_visited;

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        V = scan.nextInt();
        dfs_visited = new boolean[N+1];
        bfs_visited = new boolean[N+1];
        nums = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++) {
            nums[i] = new ArrayList<Integer>();
        }
        for(int iter = 1; iter <= M; iter++) {
            int x = scan.nextInt();
            int y = scan.nextInt();

            nums[x].add(y);
            nums[y].add(x);
        }
        for(int i = 0; i < N+1; i++) {
            Collections.sort(nums[i]);
        }
    }

    static void dfs(int x) {
        dfs_visited[x] = true;
        sb.append(x).append(" ");
        for(int y : nums[x]) {
            if(dfs_visited[y]) continue;
            dfs(y);
        }
    }
    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        bfs_visited[start] = true;

        while(!queue.isEmpty()) {
            int x = queue.poll();
            sb.append(x).append(" ");

            for(int y : nums[x]) {
                if(bfs_visited[y]) continue;
                queue.add(y);
                bfs_visited[y] = true;
            }
        }
    }
    static void proceed() {

        dfs(V);
        sb.append('\n');
        bfs(V);
        System.out.println(sb);
    }
}


/*
    static void dfs(int x) {
        dfs_visited[x] = true;
        sb.append(x).append(" ");
        for(int y = 1; y <= N; y++) {
            if(nums[x][y] == 0 || dfs_visited[y]) continue;
            dfs(y);
        }
    }
    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        bfs_visited[start] = true;

        while(!queue.isEmpty()) {
            int x = queue.poll();
            sb.append(x).append(" ");
            for(int y = 1; y <= N; y++) {
                if(nums[x][y] == 0 || bfs_visited[y]) continue;

                queue.add(y);
                bfs_visited[y] = true;
            }
        }
    }


 */