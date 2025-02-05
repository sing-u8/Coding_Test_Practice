// N과 M (1)
// https://www.acmicpc.net/problem/15649
import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[] selected;
    static boolean[] used;

    static void rec_func(int k) {
        if( k == M + 1) {
            for(int i=1; i <= M; i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
        } else {
            for(int cand=1; cand <= N; cand++) {
                if(!used[cand]) {
                    used[cand] = true;
                    selected[k]=cand;
                    rec_func(k+1);
                    used[cand]=false;
                }
            }
        }
    }

    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M + 1];
        used = new boolean[N + 1];
    }

    public static void main(String[] args) {
        input();

        // 1 번째 원소부터 M 번째 원소를 조건에 맞는 모든 방법을 찾아줘
        rec_func(1);
        System.out.println(sb.toString());
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }

    }
}
