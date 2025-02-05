// 연산자 끼워넣기
// https://www.acmicpc.net/problem/14888

import java.io.*;
import java.util.*;

public class Main {

    static int N, max, min;
    static int[] nums, operators, order;

    static StringBuilder sb = new StringBuilder();

    // 완성된 식에 맞게 계산을 해서 정답에 갱신하는 작업
    static int calculator() {
        // nums, order
        int value = nums[1];
        for (int i = 1; i < N-1; i++) {
            // vaule, order[i], num[i+1]
            switch (order[i]) {
                case 1: value = value + nums[i+1]; break;
                case 2: value = value - nums[i+1]; break;
                case 3: value = value * nums[i+1]; break;
                case 4: value = value / nums[i+1]; break;
                default: break;
            }
        }
        return value;

    }

    // order[1...N-1]에 연산자들이 순서대로 저장될 것이다.
    static void rec_func(int k) {
        if( k == N) { // 모든 연산자들을 전부 나열하는 방법을 찾은 상태
            // 정한 연산자 순서대로 계산해서 정답을 갱신하기
            int value = calculator();
            max = Math.max(max, value);
            min = Math.min(min, value);
        } else { // k번째 연산자는 무엇을 선택할 것인가?
            // 4가지의 연산자들 중에 뭘 쓸 것인지 선택하고 재귀호출하기
            for(int cand = 1; cand <= 4; cand++) {
                if(operators[cand-1] >= 1) {
                    operators[cand]--;
                    order[k] = cand;

                    rec_func(k+1);

                    operators[cand]++;
                    order[k] = 0;
                }
            }
        }
    }



    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        nums = new int[N + 1];
        operators = new int[5];
        order = new int[N + 1];

        for(int i = 1; i <= N; i++) {nums[i] = scan.nextInt();}
        for(int i = 1; i <= 4; i++) {operators[i] = scan.nextInt();}

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        input();

        // 1 번째 원소부터 M 번째 원소를 조건에 맞는 모든 방법을 찾아줘
        rec_func(1);
        sb.append(max).append('\n').append(min);
        System.out.println(sb.toString());
    }

}

//

import java.util.*;

public class Main {

    static int N, max, min;
    static int[] nums, operators;

    static StringBuilder sb = new StringBuilder();

    // 피연산자 2개와 연산자가 주어졌을 때 계산해주는 함수
    static int calculator(int operand1, int operator, int operand2) {
        // nums, order
        /*
        switch (operator) {
            case 1: return operand1 +operand2;
            case 2: return operand1 - operand2;
            case 3: return operand1 * operand2;
            case 4: return operand1 / operand2;
            default: return 0;
        }
        */

        return switch (operator) {
            case 1 -> operand1 + operand2;
            case 2 -> operand1 - operand2;
            case 3 -> operand1 * operand2;
            case 4 -> operand1 / operand2;
            // 이런 일이 벌어질 일은 없음
            default -> operand1;
        };
    }

    // order[1...N-1]에 연산자들이 순서대로 저장될 것이다.
    static void rec_func(int k, int value) {
        if( k == N) { // 모든 연산자들을 전부 나열하는 방법을 찾은 상태
            max = Math.max(max, value);
            min = Math.min(min, value);
        } else { // k번째 연산자는 무엇을 선택할 것인가?
            // 4가지의 연산자들 중에 뭘 쓸 것인지 선택하고 재귀호출하기
            for(int cand = 1; cand <= 4; cand++) {
                if(operators[cand] >= 1) {
                    operators[cand]--;
                    rec_func(k+1, calculator(value, cand, nums[k+1]));
                    operators[cand]++;
                }
            }
        }
    }

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        nums = new int[N + 1];
        operators = new int[5];

        for(int i = 1; i <= N; i++) {nums[i] = scan.nextInt();}
        for(int i = 1; i <= 4; i++) {operators[i] = scan.nextInt();}

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        input();

        // 1 번째 원소부터 M 번째 원소를 조건에 맞는 모든 방법을 찾아줘
        rec_func(1, nums[1]);
        sb.append(max).append('\n').append(min);
        System.out.println(sb.toString());
    }

}