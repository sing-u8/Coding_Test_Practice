// 카드
// https://www.acmicpc.net/problem/11652
// 추가 연습문제 20291

import java.util.*;

// 정렬 알고리즘 활용
public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        input();
        proceed();
    }


    static int N;
    static long[] nums;
    static long mode, modeCnt, curModeCnt; // (현재)최빈값, 최빈값 횟수, 현재 최빈값 횟수
    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        nums = new long[N];
        for(int i=0; i < N; i++) {
            nums[i] = scan.nextLong();
        }
    }

    static void proceed() {
        // sort 정렬하기
        Arrays.sort(nums);
        // mode: 최빈값, modeCnt: 최빈값의 등장 횟수, curCnt: 현재값의 등장 횟수
        mode = nums[0];
        modeCnt = 1;
        curModeCnt = 1;
        // 2번 원소부터 차례대로 보면서, 같은 숫자가 이어서 나오고 있는지,
        // 새로운 숫자가 나왔는지를 판단하여 curCnt를 갱신해주고, 최빈값을 갱신하는 작업.
        for(int i=1; i < N; i++) {
            if(nums[i-1] == nums[i]) {
                curModeCnt += 1;
            } else {
                curModeCnt = 1;
            }
            if(curModeCnt > modeCnt) {
                modeCnt = curModeCnt;
                mode = nums[i];
            }
        }
        // 정답 출력
        sb.append(mode);
        System.out.println(sb.toString());
    }
}


// 해쉬 테이블 활용