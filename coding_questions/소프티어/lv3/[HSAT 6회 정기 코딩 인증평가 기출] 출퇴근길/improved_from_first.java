import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력 처리를 위한 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 정방향과 역방향 그래프 초기화 (1-indexed 사용)
        List<Integer>[] graph = new ArrayList[n+1];
        List<Integer>[] rgraph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            rgraph[i] = new ArrayList<>();
        }

        // T로 직접 연결되는 정점들과 S로 직접 연결되는 정점들을 저장할 리스트
        ArrayList<Integer> preT = new ArrayList<>();
        ArrayList<Integer> preS = new ArrayList<>();

        // 간선 정보 입력과 동시에 preT, preS 정보 수집
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            rgraph[v].add(u);
        }

        // 시작점과 도착점 입력
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        // preT와 preS 정보 수집 (그래프가 완성된 후 한 번에 처리)
        for (int u = 1; u <= n; u++) {
            if (u != T && graph[u].contains(T)) {
                preT.add(u);
            }
            if (u != S && graph[u].contains(S)) {
                preS.add(u);
            }
        }

        // S→T 경로 분석
        // 1. T를 제외하고 S에서 도달 가능한 정점들
        boolean[] reachableFromS = bfs(S, T, graph, n);
        // 2. T로 가는 간선을 가진 정점들로부터 역방향으로 도달 가능한 정점들
        boolean[] canReachT = bfsMultipleStarts(preT, T, rgraph, n);

        // T→S 경로 분석
        // 1. S를 제외하고 T에서 도달 가능한 정점들
        boolean[] reachableFromT = bfs(T, S, graph, n);
        // 2. S로 가는 간선을 가진 정점들로부터 역방향으로 도달 가능한 정점들
        boolean[] canReachS = bfsMultipleStarts(preS, S, rgraph, n);

        // 모든 조건을 만족하는 정점 수 계산
        int answer = 0;
        for (int v = 1; v <= n; v++) {
            if (v == S || v == T) continue;
            if (reachableFromS[v] && canReachT[v] &&
                    reachableFromT[v] && canReachS[v]) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    // 단일 시작점에서의 BFS (특정 정점 제외)
    static boolean[] bfs(int start, int skip, List<Integer>[] graph, int n) {
        boolean[] visited = new boolean[n+1];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : graph[current]) {
                if (next == skip) continue;
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        return visited;
    }

    // 다중 시작점에서의 BFS (특정 정점 제외)
    static boolean[] bfsMultipleStarts(Collection<Integer> starts, int skip,
                                       List<Integer>[] graph, int n) {
        boolean[] visited = new boolean[n+1];
        Queue<Integer> queue = new ArrayDeque<>();

        // 모든 시작점을 큐에 넣고 방문 처리
        for (int start : starts) {
            if (start == skip) continue;
            if (!visited[start]) {
                visited[start] = true;
                queue.offer(start);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : graph[current]) {
                if (next == skip) continue;
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        return visited;
    }
}