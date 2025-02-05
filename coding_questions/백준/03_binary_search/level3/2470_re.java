// 두 용액
// https://www.acmicpc.net/problem/2470
// 다양한 풀이가 있음

// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 종종  시간초과가 뜬다....

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }

    static int N;
    static int[] nums;
    static int[] answer;

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        nums = new int[N];
        for(int iter = 0; iter < N; iter++) {
            nums[iter] = scan.nextInt();
        }
    }
    //!! 이 부분 다시 고민해서 구현해보기
    static int lower_bound(int[] A, int L, int R, int X) {
        // A[L...R]에서 X 이상의 수 중 왼쪽 인덱스를 return 하는 함수
        // 그런 게 없다면 R + 1을 return 한다.
        int result = R + 1;
        while(L <= R) {
            int mid = (R + L) / 2;
            if(A[mid] >= X) {
                result = mid;
                R = mid - 1;
            } else if(A[mid] < X) {
                L = mid + 1;
            }
        }
        return result;
    }

    static void proceed() {
        // B 배열에 대해 이분탐색을 할 예정, 정렬하자.
        Arrays.sort(nums);
        int best_sum = Integer.MAX_VALUE;
        int v1=0, v2=0;
        for(int left = 0; left < N-1; left++) {
            // A[left] 용액을 쓸 것이다. 고로 -A[left]와 가장 가까운 용액을 자신의 오른쪽 구간에서 찾자.
            int index = lower_bound(nums, left + 1, N-1, -nums[left]);
            // A[candidate - 1]와 A[candidate]중에 A[left]와 섞었을 때의 정보를 정답에 갱신시킨다.
            if(left+1 <= index-1 && index-1 < N && best_sum > Math.abs(nums[left] + nums[index-1])) {
                best_sum = Math.abs(nums[left] + nums[index-1]);
                v1 = nums[left];
                v2 = nums[index-1];
            }
            if(left+1 <= index && index < N && best_sum > Math.abs(nums[left] + nums[index])) {
                best_sum = Math.abs(nums[left] + nums[index]);
                v1 = nums[left];
                v2 = nums[index];
            }
        }

        sb.append(v1).append(" ").append(v2);
        System.out.println(sb.toString());
    }
}

// chat gpt solution

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 받기
        int N = scanner.nextInt();
        int[] solutions = new int[N];
        for (int i = 0; i < N; i++) {
            solutions[i] = scanner.nextInt();
        }

        // 용액의 특성값 정렬
        Arrays.sort(solutions);

        int closestSum = Integer.MAX_VALUE;
        int finalLeft = 0;
        int finalRight = 0;

        // 각 용액에 대해 이분 탐색 적용
        for (int i = 0; i < N - 1; i++) {
            int target = -solutions[i];
            int idx = binarySearch(solutions, target, i + 1, N - 1);

            // 이분 탐색 결과와 인접한 값들로 합을 계산하여 0에 가장 가까운 값 찾기
            if (idx != i && Math.abs(solutions[i] + solutions[idx]) < Math.abs(closestSum)) {
                closestSum = solutions[i] + solutions[idx];
                finalLeft = solutions[i];
                finalRight = solutions[idx];
            }
            if (idx > i + 1 && Math.abs(solutions[i] + solutions[idx - 1]) < Math.abs(closestSum)) {
                closestSum = solutions[i] + solutions[idx - 1];
                finalLeft = solutions[i];
                finalRight = solutions[idx - 1];
            }
            if (idx < N - 1 && idx != i && Math.abs(solutions[i] + solutions[idx + 1]) < Math.abs(closestSum)) {
                closestSum = solutions[i] + solutions[idx + 1];
                finalLeft = solutions[i];
                finalRight = solutions[idx + 1];
            }
        }

        // 결과 출력
        if (finalLeft > finalRight) {
            int temp = finalLeft;
            finalLeft = finalRight;
            finalRight = temp;
        }
        System.out.println(finalLeft + " " + finalRight);
    }

    // 이분 탐색 함수
    private static int binarySearch(int[] arr, int target, int left, int right) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}



// 정렬 후, 순차탐색 // 시간초과
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
        int left = 0;
        int right = N - 1;
        int closestSum = Integer.MAX_VALUE;
        int finalLeft = 0;
        int finalRight = N - 1;

        Arrays.sort(nums);

        while(left < right) {
            int sum = nums[left] + nums[right];

            if(Math.abs(sum) < Math.abs(closestSum)) {
                closestSum = sum;
                finalLeft = left;
                finalRight = right;
            }
            if(sum > 0) right--;
            if(sum < 0) left++;
        }

        sb.append(nums[finalLeft]).append(" ").append(nums[finalRight]);
        System.out.println(sb.toString());
    }
}