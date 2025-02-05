// 1, 2, 3 더하기
// https://www.acmicpc.net/problem/9095

/*

 */

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static Scanner scan = new Scanner(System.in);

    static int[] Dy;
    static int MaxN = 12;

    static void preprocess(){
        Dy = new int[MaxN];
        // 초기값 구하기
        Dy[1] = 1; // 1
        Dy[2] = 2; // 1+1, 2
        Dy[3] = 4; // 1+1+1, 1+2, 2+1, 3

        //Dy[4]      // 1+1+1+1, 1+2+1, 2+1+1, 3+1  = Dy[i-1]
        // 1+1+2, 2+2  = Dy[i-2]
        // 1+3  = Dy[i-3] -->  total = 7
        // 점화식을 토대로 Dy 배열 채우기
        for (int i = 4; i <= 11; i++){
            Dy[i] = Dy[i - 1] + Dy[i - 2] + Dy[i - 3];
        }
    }

    static void pro() {
        int T = scan.nextInt();
        for (int tt = 1; tt <= T; tt++){
            int N = scan.nextInt();
            sb.append(Dy[N]).append('\n');
        }
        System.out.print(sb);
    }

    public static void main(String[] args) {
        preprocess();
        pro();
    }

}