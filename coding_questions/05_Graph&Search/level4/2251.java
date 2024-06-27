// 물통
// https://www.acmicpc.net/problem/2251
// ***

import java.util.*;

class State {
    int[] X;
    State(int[] _X) {
        X = new int[3];
        for(int i = 0; i < 3; i++) X[i] = _X[i];
    }
    State move(int from, int to, int[] Limit) {
        int[] nX = new int[]{X[0], X[1], X[2]};
        if(X[from] + X[to] >= Limit[to]) {
            nX[from] -= Limit[to] - X[to];
            nX[to] = Limit[to];
        } else {
            nX[to] += nX[from];
            nX[from] = 0;
        }
        return new State(nX);
    }
}

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int[] Limit;
    static boolean[] possible;
    static boolean[][][] visit;

    static void input() {
        Scanner scan = new Scanner(System.in);
        Limit = new int[3];
        for(int i = 0; i < 3; i++) {
            Limit[i] = scan.nextInt();
        }
        visit = new boolean[201][201][201];
        possible = new boolean[201];
    }

    static void bfs(int x1, int x2, int x3) {
        Queue<State> q = new LinkedList<>();
        visit[x1][x2][x3] = true;
        q.add(new State(new int[] {x1,x2,x3}));

        while(!q.isEmpty()) {
            State st = q.poll();
            if(st.X[0] == 0) possible[st.X[2]] = true;
            // ***
            for(int from=0; from < 3; from++) {
                for(int to=0; to < 3; to++) {
                    if(from == to) continue;
                    State nxt = st.move(from, to, Limit);

                    if(!visit[nxt.X[0]][nxt.X[1]][nxt.X[2]]) {
                        visit[nxt.X[0]][nxt.X[1]][nxt.X[2]] = true;
                        q.add(nxt);
                    }
                }
            }
        }
    }

    static void proceed() {
        bfs(0,0,Limit[2]);
        for(int i=0;i<=Limit[2];i++) {
            if(possible[i]) sb.append(i).append(' ');
        }
        System.out.println(sb);
    }
}
