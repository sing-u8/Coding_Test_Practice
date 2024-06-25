// 두 용액
// https://www.acmicpc.net/problem/2470
// 얘도 시간 초과 뜬다 그지같은...
// 두 용액
// https://www.acmicpc.net/problem/2470

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
    static void proceed() {
        Arrays.sort(nums);
        int lp=0, rp=N-1, v1=0, v2=0;
        int best_sum = Integer.MAX_VALUE;
        while(lp < rp) {
            int sum = nums[lp] + nums[rp];
            if(Math.abs(sum) < best_sum) {
                best_sum = Math.abs(sum);
                v1 = nums[lp];
                v2 = nums[rp];
            }
            if(sum > 0) {
                rp--;
            } else {
                lp++;
            }
        }
        sb.append(v1).append(" ").append(v2);
        System.out.println(sb);
    }
}


//

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
    static void proceed() {
        Arrays.sort(nums);
        int lp=0, rp=N-1, v1=0, v2=0;
        int best_sum = Integer.MAX_VALUE;
        int tempSum = 0;
        while(lp < rp) {
            tempSum = nums[lp] + nums[rp];
            if(Math.abs(best_sum) > Math.abs(tempSum)) {
                v1 = nums[lp];
                v2 = nums[rp];
                best_sum = tempSum;
            }
            if(tempSum < 0) {
                lp++;
            } else if(tempSum > 0) {
                rp--;
            }else {
                break;
            }
        }
        sb.append(v1).append(" ").append(v2);
        System.out.println(sb.toString());
    }
}
