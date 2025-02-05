import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        // 자동차 연비 입력 받기
        int[] cars = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            cars[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬된 배열 생성
        int[] sortedCars = cars.clone();
        Arrays.sort(sortedCars);

        // 각 값보다 작은 수의 개수를 저장하는 Map
        Map<Integer, Integer> smallerCount = new HashMap<>();
        // 각 값의 등장 횟수를 저장하는 Map
        Map<Integer, Integer> valueCount = new HashMap<>();

        // 각 값의 등장 횟수와 작은 수의 개수 계산
        for(int i = 0; i < n; i++) {
            valueCount.put(sortedCars[i], valueCount.getOrDefault(sortedCars[i], 0) + 1);
            smallerCount.put(sortedCars[i], i);
        }

        // 결과 출력을 위한 StringBuilder
        StringBuilder sb = new StringBuilder();

        // 각 쿼리 처리
        for(int i = 0; i < q; i++) {
            int mi = Integer.parseInt(br.readLine());

            // mi가 배열에 없으면 0 출력
            if(!valueCount.containsKey(mi)) {
                sb.append("0\n");
                continue;
            }

            // mi보다 작은 수의 개수
            int smaller = smallerCount.get(mi);
            // mi보다 큰 수의 개수
            int larger = n - smaller - valueCount.get(mi);

            // 결과 계산: smaller개 중 1개 * larger개 중 1개
            long result = (long)smaller * larger;

            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }
}