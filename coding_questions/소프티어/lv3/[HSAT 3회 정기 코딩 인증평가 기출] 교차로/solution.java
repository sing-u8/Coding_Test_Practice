// https://softeer.ai/practice/6256

import java.io.*;
import java.util.*;

public class Main {
    // 차량 정보를 저장할 클래스
    static class Vehicle {
        int time, lane, idx;
        Vehicle(int time, int lane, int idx) {
            this.time = time;
            this.lane = lane;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 차량의 수 N
        int N = Integer.parseInt(br.readLine().trim());
        Vehicle[] vehicles = new Vehicle[N];

        // 각 차량의 도착 시각, 차선(문자) 및 입력 순번을 읽는다.
        // 차선 문자는 A, B, C, D이며 내부적으로 A->0, B->1, C->2, D->3 로 매핑한다.
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char w = st.nextToken().charAt(0);
            int lane = convert(w);
            vehicles[i] = new Vehicle(t, lane, i);
        }

        // 결과 배열, 기본값 -1 (교통 통과하지 못한 차량은 -1)
        int[] ans = new int[N];
        Arrays.fill(ans, -1);

        // 4개의 차선을 위한 큐 (A:0, B:1, C:2, D:3)
        Queue<Vehicle>[] laneQueues = new ArrayDeque[4];
        for (int i = 0; i < 4; i++) {
            laneQueues[i] = new ArrayDeque<>();
        }

        // vehicles는 도착시각 오름차순임
        int arrivalIndex = 0;
        long currentTime = 0;

        // 시뮬레이션: 현재 대기 차량이 있거나 앞으로 도착할 차량이 있는 동안 반복
        while(arrivalIndex < N ||
                !laneQueues[0].isEmpty() || !laneQueues[1].isEmpty() ||
                !laneQueues[2].isEmpty() || !laneQueues[3].isEmpty()) {

            // 만약 4개 차선 모두 비어있다면, 다음 도착 시각으로 점프
            if(laneQueues[0].isEmpty() && laneQueues[1].isEmpty() &&
                    laneQueues[2].isEmpty() && laneQueues[3].isEmpty()){
                currentTime = vehicles[arrivalIndex].time;
            }

            // currentTime에 도착하는 차량들을 각 차선 큐에 추가
            while(arrivalIndex < N && vehicles[arrivalIndex].time == currentTime) {
                Vehicle v = vehicles[arrivalIndex];
                laneQueues[v.lane].offer(v);
                arrivalIndex++;
            }

            // deadlock 체크: 모든 차선에 최소 1대씩 있다면
            if( !laneQueues[0].isEmpty() && !laneQueues[1].isEmpty() &&
                    !laneQueues[2].isEmpty() && !laneQueues[3].isEmpty() ){
                // 교착 상태 → 현재 대기 중인 차량과 앞으로 도착할 차량 모두 통과할 수 없으므로
                // 기본값 -1을 유지한 후 시뮬레이션 종료
                break;
            }

            // 각 차선의 맨 앞 차량이 출발할 수 있는지 체크
            // 오른쪽 차선 매핑: A(0)->D(3), B(1)->A(0), C(2)->B(1), D(3)->C(2)
            int[] right = {3, 0, 1, 2};
            boolean[] canDepart = new boolean[4];
            for(int i = 0; i < 4; i++){
                if(!laneQueues[i].isEmpty()){
                    // 해당 차선의 오른쪽 차선이 비어있으면 출발 가능
                    if(laneQueues[right[i]].isEmpty()){
                        canDepart[i] = true;
                    }
                }
            }

            // 동시에 출발하는 차량 처리
            for(int i = 0; i < 4; i++){
                if(canDepart[i]){
                    Vehicle v = laneQueues[i].poll();
                    ans[v.idx] = (int)currentTime;
                }
            }

            // 1초 경과
            currentTime++;
        }

        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++){
            bw.write(ans[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    // 문자 A, B, C, D를 각각 0,1,2,3으로 매핑
    static int convert(char w) {
        switch(w) {
            case 'A': return 0;
            case 'B': return 1;
            case 'C': return 2;
            case 'D': return 3;
        }
        return -1;
    }
}


/*
문제 해설 요약
교차로에는 4개의 차선(위치) A, B, C, D가 있으며, 각 차선에 차량들이 도착하면 그 차선의 맨 뒤에 줄을 섭니다.
매 초, 먼저 모든 도착 차량이 해당 차선에 추가된 후 각 차선의 맨 앞 차량이 “자신의 오른쪽” 차선에 차량이 있는지 확인합니다.
각 차선의 오른쪽은 다음과 같이 정해집니다.
A의 오른쪽 → D
B의 오른쪽 → A
C의 오른쪽 → B
D의 오른쪽 → C
만약 오른쪽 차선에 차량이 없으면 그 차량은 즉시(당초 시각에) 교차로를 통과합니다.
단, “A, B, C, D 위치에 동시에 차량이 한 대 이상씩 있다면” 교착 상태에 빠져 어떤 차량도 통과하지 못합니다. (즉, 그 후로도 한 대도 출발하지 못하므로 도로에 대기한 차량 및 이후 도착 차량은 모두 -1이 됩니다.)
한 차선에서는 1초에 단 한 대의 차량만 통과할 수 있습니다.
입력 차량들은 도착 시간이 오름차순이며, 같은 시간에 한 차선에는 최대 한 대만 도착합니다.
시뮬레이션 아이디어
4개의 큐(차선 A,B,C,D)를 준비합니다. 각 차량은 (도착시각, 차선, 입력 순번)을 기록합니다.
현재 시각(currentTime)에서 먼저 모든 도착 차량(도착시각 == currentTime)을 각 큐에 넣습니다.
만약 모든 차선에 최소 1대씩 있다면 deadlock 발생 – 더 이상 어떤 차량도 통과할 수 없으므로 남은 차량과 이후 도착 차량 모두 -1로 처리하고 시뮬레이션을 종료합니다.
deadlock이 아니라면, 각 차선의 맨 앞 차량에 대해 “오른쪽 차선이 비어있는지”를 동시에 체크합니다.
조건을 만족하면 해당 차량은 현재 시각에 교차로를 통과(큐에서 제거하고 결과에 currentTime 기록)합니다.
차량이 없을 경우(모든 큐가 비어있다면) 현재 시각을 다음 도착 시각으로 뛰어넘습니다.
이 과정을 모든 차량이 처리되거나 deadlock 상태가 될 때까지 진행합니다.
 */

/*
코드 설명
입력 처리 및 차량 저장

각 차량의 도착 시각, 해당 차선(문자 → 정수 매핑) 및 입력 순번을 Vehicle 객체에 저장합니다.
차량들은 입력 순으로 도착시각이 오름차순임을 이용합니다.
시뮬레이션

4개의 Queue를 각각 A, B, C, D 차선으로 사용합니다.
매 시각 currentTime에서 먼저 해당 시각에 도착하는 모든 차량을 해당 차선의 큐에 넣습니다.
만약 모든 차선에 최소 한 대씩 있다면 deadlock 상태이므로, 더 이상 어떤 차량도 통과하지 못하므로 시뮬레이션을 종료합니다.
그렇지 않으면, 각 차선의 맨 앞 차량이 “오른쪽 차선이 비어있는지” 체크하여 조건을 만족하면 해당 차량은 현재 시각에 교차로를 통과(큐에서 제거하고 결과 배열에 기록)합니다.
만약 대기 차량이 없으면 다음 도착 시각으로 currentTime을 뛰어넘습니다.
출력

각 차량의 통과 시각(또는 통과하지 못한 경우 -1)을 입력 순서대로 출력합니다.
 */