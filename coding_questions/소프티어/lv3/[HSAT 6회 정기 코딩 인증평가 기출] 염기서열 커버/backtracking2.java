import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static String[] patterns;
    // best: 모든 패턴을 커버하기 위해 필요한 최소 그룹 수 (초염기서열의 수)
    static int best;

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

        // 최악의 경우: 각 패턴을 각각의 그룹에 넣어야 하므로 그룹 수는 N
        best = N;
        // 그룹 리스트: 각 그룹은 길이 M의 char[]로, 아직 제약이 없으면 '*' (자유로운 상태)로 표현
        ArrayList<char[]> groups = new ArrayList<>();
        backtrack(0, groups);
        System.out.println(best);
    }

    // backtrack(index, groups):
    // index: 지금까지 0 ~ index-1번 패턴은 모두 그룹에 배정되었음.
    // groups: 지금까지 만들어진 그룹들의 리스트(각 그룹은 길이 M의 제약 배열)
    static void backtrack(int index, ArrayList<char[]> groups) {
        // 모든 패턴 배정 완료시 최솟값 갱신
        if (index == N) {
            best = Math.min(best, groups.size());
            return;
        }
        // 이미 사용한 그룹 수가 best 이상이면 더 진행할 필요 없음 (가지치기)
        if (groups.size() >= best) return;

        String pat = patterns[index];
        // 기존 그룹들에 현재 패턴을 추가할 수 있는지 시도
        for (int g = 0; g < groups.size(); g++) {
            char[] group = groups.get(g);
            boolean canAdd = true;
            // 현재 그룹의 상태를 백업(복사본)
            char[] backup = group.clone();
            // 각 위치마다 제약 조건을 만족하는지 확인하며 업데이트
            for (int j = 0; j < M; j++) {
                char c = pat.charAt(j);
                if (c != '.') { // 패턴에 실제 염기가 있다면
                    if (group[j] == '*') {
                        // 아직 제약이 없으므로 현재 문자로 제약 설정
                        group[j] = c;
                    } else if (group[j] != c) {
                        // 이미 설정된 제약과 불일치 → 이 그룹에는 추가할 수 없음.
                        canAdd = false;
                        break;
                    }
                }
            }
            if (canAdd) {
                backtrack(index + 1, groups);
            }
            // 재귀 후 그룹 상태 복원
            groups.set(g, backup);
        }

        // 새로운 그룹을 생성하여 현재 패턴을 넣는 경우
        char[] newGroup = new char[M];
        for (int j = 0; j < M; j++) {
            char c = pat.charAt(j);
            newGroup[j] = (c == '.') ? '*' : c;
        }
        groups.add(newGroup);
        backtrack(index + 1, groups);
        groups.remove(groups.size() - 1);
    }
}
