// https://www.acmicpc.net/problem/15681
// 트리와 쿼리

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, R, Q;
    static ArrayList<Integer>[] con;
    static int[] Dy;

    static void input() {
        N = scan.nextInt();
        R = scan.nextInt();
        Q = scan.nextInt();
        con = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            con[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            int x = scan.nextInt(), y = scan.nextInt();
            con[x].add(y);
            con[y].add(x);
        }
    }

    // Dy[x] 를 계산하는 함수
    static void dfs(int x, int prev) {
        Dy[x] = 1;
        for (int y : con[x]) {
            if (y == prev) continue;
            dfs(y, x);
            Dy[x] += Dy[y];
        }
    }

    static void pro() {
        Dy = new int[N + 1];

        dfs(R, -1);

        for (int i = 1; i <= Q; i++) {
            int q = scan.nextInt();
            sb.append(Dy[q]).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}