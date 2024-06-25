// 화살표 그리기
// https://www.acmicpc.net/problem/15970

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    static class Elem implements Comparable<Elem> {

        int point, color;

        public Elem(int point,int color) {
            this.point = point;
            this.color = color;
        }

        @Override
        public int compareTo(Elem other) {
            if(this.color == other.color) return this.point - other.point;
            return this.color - other.color;
        }

        @Override
        public String toString() {
            return "("+this.point+", "+this.color+")";
        }
    }

    public static void main(String[] args) {
        input();
        proceed();
    }

    static int N;
    static Elem[] points;
    static int total = 0;

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        points = new Elem[N];
        for(int i = 0; i < N; i++) {
            points[i] = new Elem(scan.nextInt(), scan.nextInt());
        }
    }

    static void proceed() {
        Arrays.sort(points);
        for(int i=0; i < N; i++) {
            int ld=0, rd = 0;
            //left side
            if(i != 0 && points[i-1].color == points[i].color) {
                ld = Math.abs(points[i-1].point - points[i].point);
            }
            //right side
            if(i != N-1 && points[i+1].color == points[i].color) {
                rd = Math.abs(points[i+1].point - points[i].point);
            }
            total += ld == 0 ? rd : rd == 0 ? ld : Math.min(ld, rd);
        }
        sb.append(total);
        System.out.println(sb.toString());
    }
}
