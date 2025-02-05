// 국영수
// https://www.acmicpc.net/problem/10825

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    static class Elem implements Comparable<Elem> {
        public String name;
        public int korean, english, math;

        @Override
        public int compareTo(Elem other) {
            // 국어 점수 내림차순
            if(korean != other.korean) return other.korean - korean;
            // 영어 점수 오름차순
            if(english != other.english) return english - other.english;
            // 수학 점수 내림차순
            if(math != other.math) return other.math - math;
            // 이름순
            return name.compareTo(other.name);
        }
    }

    public static void main(String[] args) {
        input();
        sort();
    }

    static int N;
    static Elem[] elems;

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        elems = new Elem[N];
        for(int i = 0; i < N; i++) {
            elems[i] = new Elem();
            elems[i].name = scan.next();
            elems[i].korean = scan.nextInt();
            elems[i].english = scan.nextInt();
            elems[i].math = scan.nextInt();
        }
    }

    static void sort() {
        Arrays.sort(elems);
        for(int i = 0; i < elems.length ; i++) {
            sb.append(elems[i].name).append('\n');
        }
        System.out.println(sb.toString());
    }
}