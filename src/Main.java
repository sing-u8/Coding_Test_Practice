// https://www.acmicpc.net/problem/1051
// 트리와 쿼리

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static String[] rect;
    static int[] lhp, llp, rhp, rlp;

    static void pointInit() {
        lhp = new int[2];
        llp = new int[2];
        rhp = new int[2];
        rlp = new int[2];
    }

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        rect = new String[N];
        scan.nextLine();
        for (int i = 0; i < N; i++) {
            rect[i] = scan.nextLine();
        }
        pointInit();
    }

    static int getSize() {
        if((lhp[0] == llp[0] && lhp[0] == rhp[0] && lhp[0] == rlp[0])
                && (lhp[1] == llp[1] && lhp[1] == rhp[1] && lhp[1] == rlp[1])
        ) {
            return 1;
        }
    }

    static void pro() {
        System.out.println(rect);
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}