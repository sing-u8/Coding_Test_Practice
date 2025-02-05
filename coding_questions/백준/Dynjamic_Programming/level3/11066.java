// https://www.acmicpc.net/problem/11066
// 파일 합치기
// *****
/*
    11049 행렬 곱셈 순서
    10942 팰린드롬?
    1509 팰린드롬 분할
 */

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    static int N, Q;
    static int[] num;
    static int[][] Dy, sum;

    static void input() {
        N = scan.nextInt();
        num = new int[N + 1];
        sum = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            num[i] = scan.nextInt();
        }
    }

    static void preprocess() {
        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j++) {
                sum[i][j] = sum[i][j - 1] + num[j];
            }
        }
    }

    static void pro() {
        preprocess();
        Dy = new int[N + 1][N + 1];

        for (int len = 2; len <= N; len++) {
            for (int i = 1; i <= N - len + 1; i++) {
                int j = i + len - 1;
                Dy[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    Dy[i][j] = Math.min(Dy[i][j], Dy[i][k] + Dy[k + 1][j] + sum[i][j]);
                }
            }
        }

        System.out.println(Dy[1][N]);
    }

    public static void main(String[] args) {
        Q = scan.nextInt();
        for (int rep = 1; rep <= Q; rep++) {
            input();
            pro();
        }
    }
}