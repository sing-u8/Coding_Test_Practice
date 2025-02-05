// 트리
// https://www.acmicpc.net/problem/1068

/*
 15681 - 트리와 쿼리
 14267 - 회사 문화 1
 */

// 이거 어떻게 만들었지...
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N, leafs=0;
    static int del_node;
    static int[] parentOf;
    static ArrayList<Integer>[] childOf;
    static boolean[] deleted;

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        deleted = new boolean[N];
        parentOf = new int[N];
        childOf = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            childOf[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            int parent = scan.nextInt();
            parentOf[i] = parent;
        }
        del_node = scan.nextInt();
        deleted[del_node] = true;
    }

    static void dfs(int dn) {
        for(int i=0; i<childOf[dn].size(); i++) {
            int v = childOf[dn].get(i);
            deleted[v] = true;

            dfs(v);
        }
    }

    static void proceed() {
        for(int i = 0; i < N; i++) {
            if(parentOf[i] == -1) continue;
            childOf[parentOf[i]].add(i);
        }
        dfs(del_node);
        for(int i = 0; i < N; i++) {
            if(!deleted[i] && childOf[i].isEmpty()) leafs++;
            if(!deleted[i] && !childOf[i].isEmpty()) {
                int count = 0;
                for(int child : childOf[i]) {
                    if(!deleted[child]) count++;
                }
                if(count == 0) leafs++;
            }
        }
        System.out.println(leafs);
    }
}

// Node class를 이용하여 트리를 형성하고
// 삭제할 노드를 삭제하여 DFS 탐색하여 leaf node를 계산
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N, root;
    static Node[] Nodes;
    static int[] parentOf;
    static int del_node;

    static class Node {
        public int value;
        public HashMap<Integer, Node> children;

        Node() {
            this.value = -1;
            this.children = new HashMap<>();
        }
        Node(int value) {
            this.value = value;
            this.children = new HashMap<>();
        }
        Node(int value, Node[] children) {
            this.value = value;
            this.children = new HashMap<>();
            for (Node child : children) {
                this.children.put(child.value, child);
            }
        }

        public Node addChild(Node child) {
            this.children.put(child.value, child);
            return this;
        }
        public Node removeChild(int childValue) {
            this.children.remove(childValue);
            return this;
        }
        public boolean isChildrenEmpty(){
            return this.children.isEmpty();
        }
    }

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        Nodes = new Node[N];
        parentOf = new int[N];
        for(int i=0;i<N;i++) Nodes[i] = new Node(i);
        for (int i = 0; i < N; i++) {
            int parent = scan.nextInt();
            parentOf[i] = parent;
            if(parent == -1) {
                root = i;
                continue;
            }
            Nodes[parent].addChild(Nodes[i]);
        }
        del_node = scan.nextInt();

    }

    static int dfs(int dn) {
        int count = 0;
        if(Nodes[dn].isChildrenEmpty()) return 1;
        for(Node child: Nodes[dn].children.values()) {
            count += dfs(child.value);
        }
        return count;
    }

    static void proceed() {
        int leafs = 0;
        if(parentOf[del_node] != -1) {
            Nodes[parentOf[del_node]].removeChild(del_node);
            leafs = dfs(root);
        }

        System.out.println(leafs);
    }
}

// Node class없이 구현하는 방법

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N, root, erased;
    static ArrayList<Integer>[] child;
    static int[] leaf;


    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        child = new ArrayList[N];
        leaf = new int[N];
        for(int i = 0; i < N; i++) child[i] = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            int par = scan.nextInt();
            if(par == -1){
                root = i; continue;
            }
            child[par].add(i);
        }
        erased = scan.nextInt();
    }

    static void dfs(int x) {
        if(child[x].isEmpty()) {
            leaf[x] = 1;
        }
        for(int y: child[x]) {
            dfs(y);
            leaf[x] += leaf[y];
        }
    }

    static void proceed() {

        // erased와 그의 부모 사이의 연결을 끊어주기
        for(int i = 0; i < N; i++) {
            if(child[i].contains(erased)) {
                child[i].remove(child[i].indexOf(erased));
            }
        }
        if(root != erased) dfs(root);
        System.out.println(leaf[root]);


        // erased가 root인 예외 처리하기

        // 정답출력하기
        System.out.println();
    }
}

