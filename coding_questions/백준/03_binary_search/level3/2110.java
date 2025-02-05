// 공유기 설치
// https://www.acmicpc.net/problem/2110
// *****

/*
1. 주어진 집들 정렬하기 => O(NlogN)
2. D를 정해서 결정 문제 한 번 풀기 => O(N)
3. 정답의 범위를 이분 탐색하면서 풀기 => O(logX)번 반복
4. 총 시간 복잡도 : O(NlogN + NlogX)
 */

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N, C;
    static int[] homes;

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        C = scan.nextInt();
        homes = new int[N];
        for(int iter = 0; iter < N; iter++) {
            nums[iter] = scan.nextInt();
        }
    }
    static boolean determination(int D) {
        // D만큼의 거리 차이를 둔다면 C개 만큼의 공유기를 설치할 수 있는가?

        // 제일 왼쪽 집부터 가능한 많이 설치해보자
        // D만큼의 거리를 두면서 최대로 설치한 개수와 C를 비교하자.
        int cnt = 1, last = homes[0];
        for(int i=1; i < N; i++) {
            // 이번에 A[i]에 설치가 가능한가?
             if(homes[i] - last >= D) {
                 cnt++;
                 last = homes[i];
             }
        }
        return cnt >= C;
    }
    static void proceed() {
        Arrays.sort(homes);

        int L = 1, R = 1000000000, ans = 0;
        // [L...R] 범위 안에 정답이 존재
        // 이분 탐색과 determination 문제를 이용해서 answer를 구하기
        while(L <= R) {
            int mid = (R + L) / 2;
            if(determination(mid)) {
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
