// https://softeer.ai/practice/6249

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static String[] sequences;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sequences = new String[N];
        for(int i = 0; i < N; i++) {
            sequences[i] = br.readLine();
        }

        answer = N; // 최악의 경우 N개가 필요

        // 모든 가능한 조합을 시도
        backtrack(0, new boolean[N], new ArrayList<>());

        System.out.println(answer);
    }

    // 백트래킹으로 모든 가능한 그룹핑을 시도
    static void backtrack(int idx, boolean[] used, List<List<Integer>> groups) {
        // 현재 그룹 개수가 이미 찾은 최소값보다 크거나 같으면 중단
        if(groups.size() >= answer) return;

        // 모든 시퀀스가 사용되었는지 확인
        boolean allUsed = true;
        for(boolean u : used) if(!u) allUsed = false;

        if(allUsed) {
            answer = Math.min(answer, groups.size());
            return;
        }

        // 아직 사용되지 않은 첫 번째 시퀀스 찾기
        int start = -1;
        for(int i = 0; i < N; i++) {
            if(!used[i]) {
                start = i;
                break;
            }
        }

        // 기존 그룹에 추가 시도
        for(int i = 0; i < groups.size(); i++) {
            if(canAddToGroup(groups.get(i), start)) {
                used[start] = true;
                groups.get(i).add(start);
                backtrack(start + 1, used, groups);
                groups.get(i).remove(groups.get(i).size() - 1);
                used[start] = false;
            }
        }

        // 새 그룹 생성
        List<List<Integer>> newGroups = new ArrayList<>(groups);
        List<Integer> newGroup = new ArrayList<>();
        newGroup.add(start);
        newGroups.add(newGroup);
        used[start] = true;
        backtrack(start + 1, used, newGroups);
        used[start] = false;
    }

    // 주어진 그룹에 새로운 시퀀스를 추가할 수 있는지 확인
    static boolean canAddToGroup(List<Integer> group, int newSeq) {
        for(int seq : group) {
            if(!isCompatible(sequences[seq], sequences[newSeq])) {
                return false;
            }
        }
        return true;
    }

    // 두 시퀀스가 호환되는지 확인
    static boolean isCompatible(String s1, String s2) {
        for(int i = 0; i < M; i++) {
            if(s1.charAt(i) != '.' && s2.charAt(i) != '.' && s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}