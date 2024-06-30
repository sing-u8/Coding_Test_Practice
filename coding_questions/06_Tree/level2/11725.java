// 미로 탐색
// https://www.acmicpc.net/problem/11725

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N;
    static ArrayList<Integer>[] Tree;
    static int[] parent;

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        Tree = new ArrayList[N+1];
        parent = new int[N+1];
        for(int i = 1; i <= N; i++) Tree[i] = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            int v1 = scan.nextInt();
            int v2 = scan.nextInt();

            Tree[v1].add(v2);
            Tree[v2].add(v1);
        }
    }

    // dfs(x, par) := 정점 x의 부모가 par였고, x의 children들을 찾아주는 함수
    static void dfs(int x, int par) {
        for(int y: Tree[x]) {
            if(y == par) continue;
            parent[y] = x;
            dfs(y, x);
        }
    }

    static void proceed() {
        // 1번 정점이 루트이므로, 여기서 시작해서 Tree의 구조를 파악

        dfs(1,0);
        for(int i = 2; i <= N; i++) {
            sb.append(parent[i]).append("\n");
        }
        System.out.println(sb);
    }
}
