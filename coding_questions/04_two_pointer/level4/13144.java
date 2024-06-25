// List of Unique Numbers
// https://www.acmicpc.net/problem/13144
// ****

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N;
    static int[] nums;
    static Map<Integer, Boolean> cnt;

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        nums = new int[N];
        for(int iter = 0; iter < N; iter++) {
            nums[iter] = scan.nextInt();
        }
        cnt = new HashMap<Integer,Boolean>();
    }

    static void proceed() {
        long answer = 0;
        for(int L=0, R=-1; L < N; L++) { // L마다 R을 최대한 옮겨 줄 계획
            // R을 옮길 수 있는 만틈 옮긴다.
            while(R+1 < N && (cnt.get(nums[R+1]) != Boolean.TRUE || !cnt.containsKey(nums[R+1]))) {
                R++;
                cnt.put(nums[R], Boolean.TRUE);
            }
            // 정답을 갱신한다.
            answer += R - L + 1;
            // L을 옮겨주면서 A[L]의 개수를 감소시킨다.
            cnt.put(nums[L], Boolean.FALSE);
        }
        sb.append(answer);
        System.out.println(sb);
    }
}

//

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 처리
        int N = scanner.nextInt();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = scanner.nextInt();
        }

        // 투 포인터 및 집합을 사용한 중복 없는 부분 수열 계산
        Set<Integer> set = new HashSet<>();
        int left = 0;
        long count = 0; // 결과를 저장할 변수

        for (int right = 0; right < N; right++) {
            // 중복이 발생하면 중복이 없어질 때까지 left 포인터 이동
            while (set.contains(array[right])) {
                set.remove(array[left]);
                left++;
            }
            set.add(array[right]);

            // 중복이 없는 경우의 수 계산
            count += (right - left + 1);
        }

        // 결과 출력
        System.out.println(count);
    }
}

//

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 처리
        int N = scanner.nextInt();
        int[] array = new int[N];
        ArrayList<Integer> list = new ArrayList<>();
        boolean[] checked  = new boolean[100000+1];
        for (int i = 0; i < N; i++) {
            array[i] = scanner.nextInt();
        }

        int count = 0;
        int start = 0, end = N-1;
        while(start <= end) {
            if(!checked[array[start]]) {
                checked[array[start]] = true;
                list.add(array[start]);
                start+=1;
            } else {
                count += list.size();
                int removed_number = list.remove(0);
                checked[removed_number] = false;
            }
        }
        int remained = list.size();
        count += remained * (remained + 1) / 2;
        // 결과 출력
        System.out.println(count);
    }
}

