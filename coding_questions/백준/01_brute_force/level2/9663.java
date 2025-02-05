// N-Queen
// https://www.acmicpc.net/problem/9663

import java.io.*;
import java.util.*;

public class Main {

    static int N, ans;
    static int[] col; // col[i] : i번 행의 퀸은 col[i]번 열에 놓았다는 기록

    static StringBuilder sb = new StringBuilder();

    static boolean attackable(int r1, int c1, int r2, int c2) {
        if(c1 == c2) return true;
        if(r1 - c1 == r2 - c2) return true;
        if(r1 + c1 == r2 + c2) return true;
        return false;
    }

    static void rec_func(int row) {
        if( row == N + 1) { //
            ans++;
        } else { //
            for(int c = 1; c <= N; c++) {
                // row 행의 c 열에 놓을 수 있으면
                boolean possible = true;
                for(int i = 1; i <= row - 1; i++) {
                    // Ii, col[i])
                    if(attackable(row, c, i, col[i])) {
                        possible = false;
                        break;
                    }
                }
                if(possible) {
                    col[row] = c;
                    rec_func(row + 1);
                    col[row] = 0;
                }
            }

        }
    }

    public static void main(String[] args) {
        input();

        // 1 번째 원소부터 M 번째 원소를 조건에 맞는 모든 방법을 찾아줘
        rec_func(1);
        sb.append(ans).append('\n');
        System.out.println(sb.toString());
    }

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        col = new int[N+1];
    }
}


// -----------------------------------------------------------------------------------------

import java.util.*;

public class Main {

    static int N, ans;
    static ArrayList<Integer> candidate = new ArrayList<Integer>(); // candidate.get(i) : i번 행의 퀸은 col[i]번 열에 놓았다는 기록
    static StringBuilder sb = new StringBuilder();

    static boolean isAvailable(ArrayList<Integer> candidate, int currentCol) {
        int currentRow = candidate.size();
        for(int r = 0; r < currentRow; r++) {
            if(candidate.get(r) == currentCol || (Math.abs(candidate.get(r) - currentCol) == currentRow - r)) {
                return false;
            }
        }
        return true;
    }
    static void rec_func(int row, ArrayList<Integer> candidate) {
        if( row == N ) {
            ans++;
        } else {
            for(int currentCol = 0; currentCol < N; currentCol++) {
                if(isAvailable(candidate, currentCol)) {
                    candidate.add(currentCol);
                    rec_func(row + 1, candidate);
                    candidate.remove(candidate.size() - 1);
                }
            }
        }
    }
    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
    }
    public static void main(String[] args) {
        input();
        rec_func(0, candidate);
        sb.append(ans).append('\n');
        System.out.println(sb.toString());
    }
}