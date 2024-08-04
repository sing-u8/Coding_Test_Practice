// https://www.acmicpc.net/problem/11057
// 오르막 수
// ***

/*
    2688 줄어들지 않아
    1562 계단 수
    2096 내려가기
    5557 1학년
    1495 기타리스트
    9095 1,2,3 더하기
    15988 1,2,3 더하기 3
    15990 1,3,3 더하기 5
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
        Dy = new int[N + 1][10];
    }

    static void pro() {
        // 초기값 구하기
        for (int num = 0; num <= 9; num++) {
            Dy[1][num] = 1;
        }

        // 점화식을 토대로 Dy 배열 채우기
        for (int len = 2; len <= N; len++) {
            for (int num = 0; num <= 9; num++) {
                // 길이가 len이고 num으로 끝나는 개수를 계산하자 == Dy[len][num] 을 계산하자.
                for (int prev = 0; prev <= num; prev++) {
                    Dy[len][num] += Dy[len - 1][prev];
                    Dy[len][num] %= 10007; //
                }
            }
        }

        // Dy배열로 정답 계산하기
        int ans = 0;
        for (int num = 0; num <= 9; num++) {
            ans += Dy[N][num];
            ans %= 10007;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}