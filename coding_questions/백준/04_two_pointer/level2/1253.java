// 좋다
// https://www.acmicpc.net/problem/1253
// boj 2473 세 용액

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N;
    static int[] nums;

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        nums = new int[N];
        for(int iter = 0; iter < N; iter++) {
            nums[iter] = scan.nextInt();
        }

    }

    // target_idx번째 원소가 서로 다른 두 수의 합으로 표현되는가?
    static boolean func(int target_idx) {
        int L = 0, R = N - 1;
        int target = nums[target_idx];
        while(L < R) {
            if(L == target_idx) L++;
            else if(R == target_idx) R--;
            else {
                if(nums[L] + nums[R] == target) return true;
                else if(nums[L] + nums[R] > target) R--;
                else L++;
            }
        }
        return false;
    }

    static void proceed() {
        Arrays.sort(nums);

        long answer = 0;
        for(int i=0; i<N; i++) {
            // i번째 원소가 서로 다른 두 수의 합으로 표현이 되는가?
            if(func(i)) {
                answer ++;
            }
        }

        sb.append(answer);
        System.out.println(sb);
    }
}