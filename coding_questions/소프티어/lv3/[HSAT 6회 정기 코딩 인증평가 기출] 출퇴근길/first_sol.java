// https://softeer.ai/practice/6248
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 1부터 n까지 정점을 사용 (1-indexed)
        List<Integer>[] graph = new ArrayList[n+1];
        List<Integer>[] rgraph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            rgraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            rgraph[v].add(u);
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        // === S → T 경로 관련 처리 (T를 중간에 만나면 안됨) ===
        // (1) G\{T}에서 S로부터 도달 가능한 정점들 (T는 제외)
        boolean[] reachableFromS = bfsSkip(S, T, graph, n);

        // (2) preT: 원래 그래프에서 T로 가는 간선 u->T가 존재하는 정점들 (u != T)
        ArrayList<Integer> preT = new ArrayList<>();
        for (int u = 1; u <= n; u++) {
            if(u == T) continue;
            for (int v : graph[u]) {
                if(v == T){
                    preT.add(u);
                    break;
                }
            }
        }
        // (3) rG\{T} (역방향 그래프에서 T 제외)에서 preT로부터 도달 가능한 정점들
        boolean[] canReachT = bfsSkipMultiple(preT, T, rgraph, n);

        // S→T 경로에 등장 가능한 중간 정점 집합은 reachableFromS ∩ canReachT
        // (T 자체는 제외됨)

        // === T → S 경로 관련 처리 (S를 중간에 만나면 안됨) ===
        // (4) G\{S}에서 T로부터 도달 가능한 정점들 (S 제외)
        boolean[] reachableFromT = bfsSkip(T, S, graph, n);

        // (5) preS: 원래 그래프에서 S로 가는 간선 u->S가 존재하는 정점들 (u != S)
        ArrayList<Integer> preS = new ArrayList<>();
        for (int u = 1; u <= n; u++) {
            if(u == S) continue;
            for (int v : graph[u]) {
                if(v == S){
                    preS.add(u);
                    break;
                }
            }
        }
        // (6) rG\{S}에서 preS로부터 도달 가능한 정점들
        boolean[] canReachS = bfsSkipMultiple(preS, S, rgraph, n);

        // T→S 경로에 등장 가능한 중간 정점 집합은 reachableFromT ∩ canReachS
        // (S 자체는 제외됨)

        // === 최종: 두 경로 모두에 등장 가능한 정점 (단, S와 T는 제외) ===
        int ans = 0;
        for (int v = 1; v <= n; v++) {
            if(v == S || v == T) continue;
            if(reachableFromS[v] && canReachT[v] && reachableFromT[v] && canReachS[v])
                ans++;
        }

        System.out.println(ans);
    }

    // 단일 시작점에서 BFS 진행 (skip 정점은 아예 방문하지 않음)
    static boolean[] bfsSkip(int start, int skip, List<Integer>[] graph, int n) {
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.offer(start);

        while(!q.isEmpty()){
            int cur = q.poll();
            for (int nxt : graph[cur]) {
                if(nxt == skip) continue;
                if(!visited[nxt]){
                    visited[nxt] = true;
                    q.offer(nxt);
                }
            }
        }

        return visited;
    }

    // 여러 시작점에서 BFS 진행 (skip 정점은 아예 방문하지 않음)
    static boolean[] bfsSkipMultiple(Collection<Integer> starts, int skip, List<Integer>[] graph, int n) {
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new ArrayDeque<>();

        for (int s : starts) {
            if(s == skip) continue;
            if(!visited[s]){
                visited[s] = true;
                q.offer(s);
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            for (int nxt : graph[cur]) {
                if(nxt == skip) continue;
                if(!visited[nxt]){
                    visited[nxt] = true;
                    q.offer(nxt);
                }
            }
        }

        return visited;
    }
}
