// https://softeer.ai/practice/6249
// 제대로 이해하지 못함.

import java.io.*;
import java.util.*;

public class Main {
    // 문제에서 주어지는 패턴(좋은 염기서열)의 개수와 길이
    static int numPatterns, seqLength;
    // 각 패턴을 저장하는 배열
    static String[] goodSequences;

    // validSubset[mask]: mask에 해당하는 패턴들이 한 초염기서열(슈퍼 시퀀스)로 모두 커버 가능한지 여부
    static boolean[] validSubset;

    // dp[mask]: mask에 해당하는 패턴들을 커버하기 위한 최소 초염기서열(그룹)의 개수
    static int[] dp;

    // 전체 부분집합의 개수 (2^(패턴 개수))
    static int totalMasks;

    // 충분히 큰 수 (무한대를 대신)
    static final int INF = 1 << 28;

    public static void main(String[] args) throws Exception {
        // 입력 처리 (BufferedReader를 사용하여 빠르게 처리)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        numPatterns = Integer.parseInt(st.nextToken());
        seqLength = Integer.parseInt(st.nextToken());
        goodSequences = new String[numPatterns];
        for (int i = 0; i < numPatterns; i++){
            goodSequences[i] = br.readLine().trim();
        }

        // 총 부분집합의 개수는 2^(패턴의 개수)
        totalMasks = 1 << numPatterns;
        validSubset = new boolean[totalMasks];
        // 빈 집합은 편의상 유효(valid)하다고 간주
        validSubset[0] = true;

        // [1] 각 부분집합(mask)에 대해, 해당하는 패턴들이 하나의 초염기서열로 커버 가능한지 미리 판별한다.
        // 한 초염기서열이 해당 패턴들을 모두 만족하려면, 각 위치에서
        //   - 패턴이 '.'이면 아무 문자나 가능
        //   - 실제 문자가 있다면, 그룹 내의 모든 패턴에서 그 위치의 문자가 같아야 한다.
        for (int mask = 1; mask < totalMasks; mask++){
            boolean isValid = true;
            // 시퀀스의 각 위치를 검사
            for (int pos = 0; pos < seqLength && isValid; pos++){
                char requiredChar = 0; // 아직 자리의 문자 제약이 정해지지 않음 (0이면 미정)
                // mask에 포함된 각 패턴에 대해 검사
                for (int i = 0; i < numPatterns; i++){
                    if ((mask & (1 << i)) != 0) { // 패턴 i가 mask에 포함되어 있다면
                        char currentChar = goodSequences[i].charAt(pos);
                        if (currentChar != '.') {  // 와일드카드가 아니라면
                            if (requiredChar == 0) {
                                // 해당 위치의 제약을 설정
                                requiredChar = currentChar;
                            } else if (requiredChar != currentChar) {
                                // 이미 정해진 제약과 다른 문자가 등장하면 유효하지 않음
                                isValid = false;
                                break;
                            }
                        }
                    }
                }
            }
            validSubset[mask] = isValid;
        }

        // [2] dp 배열을 -1로 초기화 (메모이제이션: 아직 계산하지 않은 상태)
        dp = new int[totalMasks];
        Arrays.fill(dp, -1);

        // 전체 패턴을 커버하는 문제는 mask가 (totalMasks - 1)인 경우 (모든 패턴 포함)
        int result = rec(totalMasks - 1);
        System.out.println(result);
    }

    /**
     * rec(mask): mask에 해당하는 (아직 커버하지 않은) 패턴들을 커버하기 위한 최소 초염기서열 개수를 반환
     */
    static int rec(int mask) {
        // 기저 사례: 더 이상 커버할 패턴이 없으면 0을 반환
        if (mask == 0) return 0;
        // 이미 계산된 상태라면 바로 반환 (메모이제이션)
        if (dp[mask] != -1) return dp[mask];

        // 아직 커버하지 않은 패턴 중, 인덱스가 가장 작은 패턴을 선택하여,
        // 이 패턴이 포함된 서브집합만 고려한다.
        int firstPatternIndex = Integer.numberOfTrailingZeros(mask);
        int minGroups = INF;

        // mask에 포함된 모든 부분집합(submask)을 순회하는 반복문
        // 단, 반드시 firstPatternIndex가 포함되어 있어야 함
        for (int submask = mask; submask > 0; submask = (submask - 1) & mask) {
            // firstPatternIndex가 submask에 포함되어 있지 않으면 건너뜀
            if ((submask & (1 << firstPatternIndex)) == 0) continue;
            // 만약 submask에 해당하는 패턴들이 한 초염기서열로 커버 가능하지 않다면 건너뜀
            if (!validSubset[submask]) continue;
            // submask에 해당하는 패턴들을 한 그룹으로 처리한 후, 나머지(mask ^ submask)에 대해 재귀 호출
            minGroups = Math.min(minGroups, 1 + rec(mask ^ submask));
        }
        dp[mask] = minGroups;
        return minGroups;
    }
}
