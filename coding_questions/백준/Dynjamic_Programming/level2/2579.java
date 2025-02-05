// https://www.acmicpc.net/problem/2579
// 계단 오르기
// ***

/*
     1149 RGB 거리
     2156 포도주 시식
     2193 이친수
     9465 스티커
     1309 동물원
     2688 줄어들지 않아
 */

import java.io.*;
import java.util.*;
import java.lang.Math;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] Dy;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        Dy = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
    }

    static void pro() {
        // 초기값 구하기
        Dy[1][0] = A[1];
        Dy[1][1] = 0;

        if (N >= 2) {
            Dy[2][0] = A[2];
            Dy[2][1] = A[1] + A[2];
        }

        // 점화식을 토대로 Dy 배열 채우기
        for (int i = 3; i <= N; i++) {
            Dy[i][0] = Math.max(Dy[i - 2][0] + A[i], Dy[i - 2][1] + A[i]);
            Dy[i][1] = Dy[i - 1][0] + A[i];
        }
        System.out.println(Math.max(Dy[N][0], Dy[N][1]));
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}