// https://softeer.ai/practice/6249

import java.io.*;
import java.util.*;

public class Main {
    // 패턴(좋은 염기서열)의 개수와 각 패턴의 길이
    static int numPatterns, seqLength;
    // 입력으로 주어진 패턴들을 저장하는 배열
    static String[] patterns;
    // 모든 패턴을 커버하기 위해 필요한 최소 그룹(초염기서열)의 수
    static int bestSolution;

    public static void main(String[] args) throws Exception {
        // 입력 처리: BufferedReader와 StringTokenizer를 사용하여 빠르게 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        numPatterns = Integer.parseInt(st.nextToken());
        seqLength = Integer.parseInt(st.nextToken());
        patterns = new String[numPatterns];
        for (int i = 0; i < numPatterns; i++){
            patterns[i] = br.readLine().trim();
        }

        // 최악의 경우: 각 패턴을 개별 그룹에 배정해야 하므로 최대 그룹 수는 numPatterns
        bestSolution = numPatterns;

        // 그룹 리스트: 각 그룹은 길이 seqLength의 char 배열로 관리됨.
        // 각 그룹의 배열은 해당 그룹의 '제약'을 나타내며,
        // 아직 제약이 없는 자리는 '*'로 표현 (즉, 아무 문자나 들어갈 수 있음)
        ArrayList<char[]> currentGroups = new ArrayList<>();

        // 백트래킹 시작: 패턴 0부터 numPatterns-1까지 배정해 나간다.
        backtrack(0, currentGroups);

        System.out.println(bestSolution);
    }

    /**
     * 백트래킹 메서드: 주어진 인덱스의 패턴부터 마지막 패턴까지를 현재 그룹들에 배정하는 모든 경우를 탐색합니다.
     *
     * @param patternIndex 현재 배정할 패턴의 인덱스
     * @param groups       현재까지 만들어진 그룹들의 리스트
     *                     (각 그룹은 길이 seqLength의 char 배열로, 각 위치에 제약 조건이 저장됨)
     */
    static void backtrack(int patternIndex, ArrayList<char[]> groups) {
        // 기저 사례: 모든 패턴을 그룹에 배정한 경우
        if (patternIndex == numPatterns) {
            bestSolution = Math.min(bestSolution, groups.size());
            return;
        }

        // 가지치기: 현재 사용한 그룹의 수가 이미 bestSolution 이상이면 더 이상 진행할 필요가 없음.
        if (groups.size() >= bestSolution) return;

        // 현재 배정할 패턴
        String currentPattern = patterns[patternIndex];

        // [1] 기존 그룹들에 현재 패턴을 추가할 수 있는지 시도합니다.
        for (int groupIndex = 0; groupIndex < groups.size(); groupIndex++) {
            char[] groupConstraints = groups.get(groupIndex);
            boolean canPlaceInGroup = true;
            // 그룹 상태를 변경하기 전 백업: 재귀 호출 후 상태를 복원하기 위함
            char[] backupConstraints = groupConstraints.clone();

            // 각 위치에 대해 현재 패턴과 그룹의 제약 조건이 호환되는지 확인
            for (int pos = 0; pos < seqLength; pos++) {
                char patternChar = currentPattern.charAt(pos);
                if (patternChar != '.') { // 패턴에 구체적인 문자가 있는 경우만 검사
                    if (groupConstraints[pos] == '*') {
                        // 아직 제약이 없다면, 현재 패턴의 문자를 제약으로 설정
                        groupConstraints[pos] = patternChar;
                    } else if (groupConstraints[pos] != patternChar) {
                        // 이미 설정된 제약과 불일치 → 이 그룹에 배정할 수 없음.
                        canPlaceInGroup = false;
                        break;
                    }
                }
            }

            // 만약 현재 그룹에 넣을 수 있다면, 다음 패턴으로 진행
            if (canPlaceInGroup) {
                backtrack(patternIndex + 1, groups);
            }
            // 재귀 호출 후 그룹의 상태를 원래대로 복원
            groups.set(groupIndex, backupConstraints);
        }

        // [2] 새로운 그룹을 생성하여 현재 패턴을 넣는 경우
        // 새로운 그룹의 제약 배열은 현재 패턴의 구체적인 문자로 초기화하고, '.'인 자리는 '*'로 표시
        char[] newGroupConstraints = new char[seqLength];
        for (int pos = 0; pos < seqLength; pos++) {
            char patternChar = currentPattern.charAt(pos);
            newGroupConstraints[pos] = (patternChar == '.') ? '*' : patternChar;
        }
        groups.add(newGroupConstraints);
        backtrack(patternIndex + 1, groups);
        groups.remove(groups.size() - 1);
    }
}
