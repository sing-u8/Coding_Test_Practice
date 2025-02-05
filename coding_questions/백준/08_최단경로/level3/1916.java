// 최소비용 구하기
// https://www.acmicpc.net/problem/1916

/*

 */

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N, M;
    static ArrayList<Integer>[] adj;
    static int[] indeg;

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();

    }

    static void dijkstra(int start) {
        // 모든 정점까지에 대한 거리를 무한대로 초기화하기
        for(int i=i;i<=N;i++) dist[i] = Interger.MAX_VALUE;
        // 최소 힙 생성

        // 시작점에 대한 정보(Inforation)을 기록에 추가하고, 거리 배열(dist)에 갱신하기

        // 거리 정보들이 모두 소진될 때까지 거리 갱신을 반복하기
    }


    static void proceed() {

        System.out.println(sb);
    }
}