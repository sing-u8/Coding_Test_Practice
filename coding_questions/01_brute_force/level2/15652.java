// N과 M (4)
// https://www.acmicpc.net/problem/15652
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] selected;

    static void rec_func(int k) {
        if( k == M + 1) {
            for(int i=1; i <= M; i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
        } else {
            int start = selected[k-1];
            if(start == 0) start = 1;
            for(int cand=start; cand <= N; cand++) {
                selected[k]=cand;
                rec_func(k+1);
            }
        }
    }

    static StringBuilder sb = new StringBuilder();

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M + 1];
    }

    public static void main(String[] args) {
        input();

        // 1 번째 원소부터 M 번째 원소를 조건에 맞는 모든 방법을 찾아줘
        rec_func(1);
        System.out.println(sb.toString());
    }

}