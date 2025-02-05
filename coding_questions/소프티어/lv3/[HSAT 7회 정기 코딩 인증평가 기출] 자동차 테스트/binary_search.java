// https://softeer.ai/practice/6247
// 이진 탐색을 활용한 방법
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        // 자동차 연비 입력 및 정렬
        int[] cars = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            cars[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cars);

        StringBuilder sb = new StringBuilder();

        while(q-- > 0) {
            int mi = Integer.parseInt(br.readLine());

            // 이진 탐색으로 mi의 위치 찾기
            int lower = lowerBound(cars, mi);
            int upper = upperBound(cars, mi);

            // mi가 배열에 없는 경우
            if(lower == upper) {
                sb.append("0\n");
                continue;
            }

            // mi보다 작은 수의 개수와 큰 수의 개수
            long smaller = lower;
            long larger = n - upper;

            sb.append(smaller * larger).append("\n");
        }

        System.out.print(sb);
    }

    // lower bound 구현
    static int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while(left < right) {
            int mid = (left + right) / 2;
            if(arr[mid] >= target) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    // upper bound 구현
    static int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while(left < right) {
            int mid = (left + right) / 2;
            if(arr[mid] > target) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}