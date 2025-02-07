// https://softeer.ai/practice/6250
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // BufferedReader와 StringTokenizer를 사용하여 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄: 참가자 수 N
        int N = Integer.parseInt(br.readLine());

        // 3개의 대회 점수를 저장할 배열: scores[대회번호][참가자번호]
        int[][] scores = new int[3][N];
        for (int contest = 0; contest < 3; contest++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                scores[contest][i] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 대회별 등수를 저장할 배열
        int[][] contestRanks = new int[3][N];

        // 각 대회별로 점수에 따른 등수 계산
        for (int contest = 0; contest < 3; contest++) {
            // Score 객체 배열: 각 객체는 (점수, 참가자 인덱스)를 저장
            Score[] arr = new Score[N];
            for (int i = 0; i < N; i++) {
                arr[i] = new Score(scores[contest][i], i);
            }

            // 내림차순 정렬: 높은 점수가 앞쪽에 오도록 정렬
            Arrays.sort(arr, new Comparator<Score>() {
                public int compare(Score a, Score b) {
                    return b.score - a.score;
                }
            });

            // "나보다 높은 점수의 수 + 1"로 등수를 결정
            contestRanks[contest][arr[0].index] = 1;  // 첫 번째 참가자는 1등
            for (int i = 1; i < N; i++) {
                if (arr[i].score == arr[i - 1].score) {
                    // 동점인 경우 이전 참가자와 같은 등수
                    contestRanks[contest][arr[i].index] = contestRanks[contest][arr[i - 1].index];
                } else {
                    // 앞에 i명의 참가자가 있으므로 등수는 i+1
                    contestRanks[contest][arr[i].index] = i + 1;
                }
            }
        }

        // 최종 점수(세 대회의 합)를 계산하여 저장
        int[] finalScores = new int[N];
        for (int i = 0; i < N; i++) {
            finalScores[i] = scores[0][i] + scores[1][i] + scores[2][i];
        }

        // 최종 등수를 계산하기 위한 Score 객체 배열 생성 (최종 점수와 참가자 인덱스)
        Score[] finalArr = new Score[N];
        for (int i = 0; i < N; i++) {
            finalArr[i] = new Score(finalScores[i], i);
        }
        Arrays.sort(finalArr, new Comparator<Score>() {
            public int compare(Score a, Score b) {
                return b.score - a.score;
            }
        });

        // 최종 등수 배열 생성
        int[] finalRanks = new int[N];
        finalRanks[finalArr[0].index] = 1;
        for (int i = 1; i < N; i++) {
            if (finalArr[i].score == finalArr[i - 1].score) {
                finalRanks[finalArr[i].index] = finalRanks[finalArr[i - 1].index];
            } else {
                finalRanks[finalArr[i].index] = i + 1;
            }
        }

        // 결과 출력 (StringBuilder를 사용하여 출력 문자열 구성)
        StringBuilder sb = new StringBuilder();
        // 각 대회의 등수 출력
        for (int contest = 0; contest < 3; contest++) {
            for (int i = 0; i < N; i++) {
                sb.append(contestRanks[contest][i]);
                if (i < N - 1) sb.append(" ");
            }
            sb.append("\n");
        }
        // 최종 등수 출력
        for (int i = 0; i < N; i++) {
            sb.append(finalRanks[i]);
            if (i < N - 1) sb.append(" ");
        }

        System.out.print(sb.toString());
    }

    // 점수와 참가자의 원래 인덱스를 저장하는 클래스
    static class Score {
        int score, index;
        Score(int score, int index) {
            this.score = score;
            this.index = index;
        }
    }
}
