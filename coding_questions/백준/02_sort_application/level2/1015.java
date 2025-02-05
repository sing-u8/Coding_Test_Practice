// 국영수
// https://www.acmicpc.net/problem/1015

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    static class Elem implements Comparable<Elem> {
        public int num, idx;

        @Override
        public int compareTo(Elem other) {
            // TODO : 1. nums의 비내림차순 2. num이 같으면 idx 오름차순
            if(num != other.num) return (num < other.num) ? -1 : 1;
            return idx - other.idx;

            //return num - other.num
        }
    }

    public static void main(String[] args) {
        input();
        proceed();
    }

    static int N;
    static int[] P;
    static Elem[] B;

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        P = new int[N];
        B = new Elem[N];
        for(int i = 0; i < N; i++) {
            B[i] = new Elem();
            B[i].num = scan.nextInt();
            B[i].idx = i;
        }
    }

    static void proceed() {

        // TODO: B 배열 정렬하기
        Arrays.sort(B);
        // TODO: B 배열의 값을 이용해서 P 배열 채우기
        for(int b_idx = 0; b_idx < N; b_idx++) {
            P[B[b_idx].idx] = b_idx;
        }
        // TODO: P 배열 출력하기
        for(int i = 0; i < N; i++) {
            sb.append(P[i]).append(' ');
        }
        System.out.println(sb.toString());
    }
}