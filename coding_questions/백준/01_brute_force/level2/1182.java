// 부분 수열의 합
// https://www.acmicpc.net/problem/1182

import java.util.*;

public class Main {

    static int N, S, ans;
    static int[] nums; // col[i] : i번 행의 퀸은 col[i]번 열에 놓았다는 기록

    static StringBuilder sb = new StringBuilder();

    static void rec_func(int count, int value, int numOfPart) {
        if( count == N ) {
            if(S == value && numOfPart > 0) {
                ans++;
            }
        } else {
            rec_func(count+1,value, numOfPart);
            rec_func(count+1,value + nums[count], numOfPart+1);
        }
    }

    public static void main(String[] args) {
        input();

        // 1 번째 원소부터 M 번째 원소를 조건에 맞는 모든 방법을 찾아줘
        rec_func(0, 0, 0);
        sb.append(ans).append('\n');
        System.out.println(sb.toString());
    }

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        S = scan.nextInt();
        nums = new int[N];
        for(int i = 0; i < N; i++) {
            nums[i] = scan.nextInt();
        }
    }
}

// -------------------------------------------------------------------------------------------------
