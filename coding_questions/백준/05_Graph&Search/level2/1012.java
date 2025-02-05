// 유기농 배추
// https://www.acmicpc.net/problem/1012

// --------------------------- 인접리스트 이용 --------------------------------
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static Scanner scan = new Scanner(System.in);
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int TestCase, Row, Col, CabbageNum, WormNum;
    static boolean[][] Cabbage;
    static boolean[][] visited;

    public static void main(String[] args) {
        TestCase = scan.nextInt();
        for(int i=0; i<TestCase; i++) {
            input();
            proceed();
            sb.append(WormNum).append("\n");
        }
        System.out.println(sb);
    }

    static void input() {
        WormNum = 0;
        Row = scan.nextInt();
        Col = scan.nextInt();
        CabbageNum = scan.nextInt();
        Cabbage = new boolean[Row][Col];
        visited = new boolean[Row][Col];
        for( int j = 0; j < CabbageNum; j++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            Cabbage[x][y] = true;
        }
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;
        for(int k=0; k<4; k++) {
            int nx = x + dir[k][0];
            int ny = y + dir[k][1];
            if(nx < 0 || ny < 0 || nx >= Row || ny >= Col) continue;
            if(visited[nx][ny]) continue;
            if(!Cabbage[nx][ny]) continue;
            dfs(nx, ny);
        }
    }
    static void bfs(int start) {
    }
    static void proceed() {
        for(int r=0; r<Row; r++) {
            for(int c=0; c<Col; c++) {
                if(!visited[r][c] && Cabbage[r][c]) {
                    dfs(r, c);
                    WormNum++;
                }
            }
        }
    }
}


