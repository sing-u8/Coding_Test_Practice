// https://softeer.ai/practice/6257

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 빠른 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long answer = 0;
        // 각 인덱스 i에 대해 i < j < k를 만족하는 valid triple을 세기
        for (int i = 0; i < n; i++) {
            int cnt = 0;  // i와 사이에 나온, a[i]보다 큰 값의 개수
            int base = arr[i];
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > base) {
                    // j는 "중간" 역할을 할 수 있음 → 카운트 증가
                    cnt++;
                } else if (arr[j] < base) {
                    // j는 "k" 역할을 하며, 지금까지 나온 중간 후보(cnt)와 함께 valid triple 형성
                    answer += cnt;
                }
            }
        }
        System.out.println(answer);
    }
}