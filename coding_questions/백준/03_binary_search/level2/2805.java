// 나무 자르기
// https://www.acmicpc.net/problem/2805


import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }

    static int N, M;
    static int[] nums;

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        nums = new int[N];
        for(int iter = 0; iter < N; iter++) {
            nums[iter] = scan.nextInt();
        }
    }
    static int find_optimal_height(int[] ar, int min, int max, int purpose) {
        int optval = 0;
        while(max >= min) {
            int mid = (max + min) / 2;
            long tempTotal = getSum(ar, mid);
            if(tempTotal == purpose) {
                return mid;
            }else if(tempTotal < purpose) {
                max = mid -1;
            }else {
                min = mid + 1;
                optval = mid;
            }
        }
        return optval;
    }
    // 높이 height만큼 잘랐을 때, 구할 수 있는 나무 높이의 합
    static long getSum(int[] ar, int height) {
        long sum = 0;
        for(int i=0; i<ar.length; i++) {
            int temp = ar[i] - height;
            sum += Math.max(temp, 0);
        }
        return sum;
    }

    static void proceed() {
        // 먼저 정렬
        Arrays.sort(nums);

        // 최댓값을 기준으로 M만큼 뺀 높이(H)가 적어도 M을 얻을 수 있는
        // 최솟값이므로 [최댓값-H ~ 최댓값]범위로 좁힐 수 있다.
        int min = nums[nums.length - 1] - M;
        int max = nums[nums.length - 1];
        // 높이 범위를 기준으로 이분 탐색을 하며 최댓값을 구하기
        int answer = find_optimal_height(nums, min, max, M);

        sb.append(answer);
        System.out.println(sb.toString());
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

    static int N, M;
    static int[] nums;

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        nums = new int[N];
        for(int iter = 0; iter < N; iter++) {
            nums[iter] = scan.nextInt();
        }
    }
    static boolean determination(int H) {
        //H 높이로 나무들을 잘랐을 때, M 만큼을 얻을 수 있으면 true, 없으면 false를 return하는 함수
        long sum = 0;
        for(int i=0; i<N; i++) {
            sum += Math.max(nums[i] - H,0);
        }
        return sum >= M;
    }

    static void proceed() {
        long L = 0, R = 2000000000, ans = 0;
        // [L ... R] 범위 안에 정답이 존재
        // 이분 탐색과 determination 문제를 이용해서 answer를 빠르게 구하기
        // Arrays.sort(nums);
        while(L <= R) {
            long mid = (L + R) / 2;
            if(determination((int)mid)){
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        sb.append(ans);
        System.out.println(sb.toString());
    }
}