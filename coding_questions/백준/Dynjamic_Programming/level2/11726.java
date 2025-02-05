// 2×n 타일링
// https://www.acmicpc.net/problem/11726

/*
   1003 피보나치 함수
   10870 피보나치 수 5
   15988 1,2,3 더하기 3
   15991 1,2,3 더하기 6
   11052 카드 구매하기
   2011 암호코드
 */

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static Scanner scan = new Scanner(System.in);

    static int N;
    static int[] Dy;

    static void input(){
        N = scan.nextInt();
    }

    static void pro() {
        Dy = new int[1005];
        // 초기값 구하기
        Dy[1] = 1;
        Dy[2] = 2;

        // 점화식을 토대로 Dy 배열 채우기
        for (int i = 3; i <= N; i++){
            Dy[i] = (Dy[i - 1] + Dy[i - 2]) % 10007;
        }
        System.out.println(Dy[N]);
    }

    public static void main(String[] args) {
        input();
        pro();
    }


}