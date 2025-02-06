// https://softeer.ai/practice/6249

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static String[] patterns;
    static boolean[] valid;  // 각 부분집합(mask)이 한 초염기서열로 커버 가능한지 여부
    static int[] dp;         // dp[mask]: mask에 해당하는 패턴들을 커버하는 최소 그룹 수
    static int total;
    static final int INF = 1 << 28;

    public static void main(String[] args) throws Exception {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        patterns = new String[N];
        for (int i = 0; i < N; i++){
            patterns[i] = br.readLine().trim();
        }

        total = 1 << N;
        valid = new boolean[total];
        // valid[mask] : mask에 해당하는 모든 패턴이 한 초염기서열로 커버 가능한지 검사
        valid[0] = true;  // (빈 집합은 편의상 true)
        for (int mask = 1; mask < total; mask++){
            boolean ok = true;
            for (int pos = 0; pos < M && ok; pos++){
                char req = 0;  // 아직 결정되지 않은 상태
                for (int i = 0; i < N; i++){
                    if ((mask & (1 << i)) != 0) {
                        char c = patterns[i].charAt(pos);
                        if (c != '.'){
                            if (req == 0) {
                                req = c;  // 최초 지정
                            } else if (req != c) {
                                ok = false;
                                break;
                            }
                        }
                    }
                }
            }
            valid[mask] = ok;
        }

        // 재귀(dp)용 배열 초기화 (-1: 미방문)
        dp = new int[total];
        Arrays.fill(dp, -1);
        int ans = rec(total - 1);
        System.out.println(ans);
    }

    // rec(mask): mask에 해당하는(아직 커버하지 않은) 패턴들을 커버하기 위한 최소 그룹 수
    static int rec(int mask) {
        if (mask == 0) return 0;  // 모두 커버되었으면 0
        if (dp[mask] != -1) return dp[mask];

        // 아직 커버되지 않은 패턴 중 가장 낮은 번호의 패턴 선택
        int i = Integer.numberOfTrailingZeros(mask);
        int best = INF;
        // mask에 포함된 서브집합 s 중 반드시 i를 포함하는 s에 대해
        // (s가 valid해야만 한 그룹으로 커버 가능)
        for (int s = mask; s > 0; s = (s - 1) & mask) {
            if ((s & (1 << i)) == 0) continue;
            if (!valid[s]) continue;
            best = Math.min(best, 1 + rec(mask ^ s));  // s를 한 그룹으로 처리
        }
        dp[mask] = best;
        return best;
    }
}
