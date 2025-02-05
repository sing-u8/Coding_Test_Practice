// 먹을 것인가 먹힐 것인가
// https://www.acmicpc.net/problem/7795
// 추가 연습문제 1920, 1764, 3273, 10816

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }

    static int N;
    static ArrayList<Integer>[] A, B;
    static int[] answer;

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        A = new ArrayList[N];
        B = new ArrayList[N];
        answer = new int[N];

        for(int iter = 0; iter < N; iter++) {
            int num_a = 0, num_b = 0;
            num_a = scan.nextInt();
            num_b = scan.nextInt();
            A[iter] = new ArrayList<Integer>();
            B[iter] = new ArrayList<Integer>();
            for(int ai = 0; ai < num_a; ai++) {
                A[iter].add(scan.nextInt());
            }
            for(int bi = 0; bi < num_b; bi++) {
                B[iter].add(scan.nextInt());
            }
        }
    }

    //!! 이 부분 다시 고민해서 구현해보기
    static int lower_bound(ArrayList<Integer> B, int L, int R, int X) {
        // A[L...R]에서 X 미만의 수(X보다 작은 수) 중 제일 오른쪽 인덱스를 return 하는 함수
        // 그런 게 없다면 L - 1을 return 한다.
        int result = L - 1;
        while(L <= R) {
            int mid = (L + R) / 2;
            if(B.get(mid) < X) {
                result = mid;
                L = mid + 1;
            } else if(B.get(mid) >= X) {
                R = mid - 1;
            }
        }
        return result + 1;
    }
    static void sort() {
        for(int i = 0; i < N; i++) {
            Collections.sort(B[i]);
            for(int ai = 0; ai < A[i].size(); ai++) {
                answer[i] += lower_bound(B[i],0, B[i].size() - 1, A[i].get(ai));
            }
        }
    }
    static void proceed() {
        // B 배열에 대해 이분탐색을 할 예정, 정렬하자.
        sort();
        int ans = 0;
        for(int i = 0; i < N; i++) {
            // A[i]를 선택했을 때, B에서는 A[i]보다 작은 게 몇 개나 있는지 count하기
            System.out.println(answer[i]);
        }

    }
}