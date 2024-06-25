// 부분합
// https://www.acmicpc.net/problem/1806

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N, S;
    static int[] nums;

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        S = scan.nextInt();
        nums = new int[N];
        for(int iter = 0; iter < N; iter++) {
            nums[iter] = scan.nextInt();
        }
    }
    static int getSum(int start, int end) {
        int sum = 0;
        for(int i = start; i <= end; i++) {
            sum += nums[i];
        }
        return sum;
    }
    static int getOptSum() {
        int minSum = 0;
        int lp=0, rp=1;
        while(lp < N && rp < N) {
            int tempSum = getSum(lp, rp);
            if(tempSum < S) {
                rp++;
            } else {
                minSum = tempSum;
                lp++;
            }
        }
        return minSum;
    }
    static void proceed() {
        int answer = getOptSum();
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
