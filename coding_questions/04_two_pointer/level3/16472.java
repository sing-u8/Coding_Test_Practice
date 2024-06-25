// 고냥이
// https://www.acmicpc.net/problem/16472

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N;
    static int kind = 0;
    static String str;
    static int[] cnt = new int[26];

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        str = scan.next();
    }

    static void add(char x) {
        cnt[x - 'a']++;
        if(cnt[x - 'a'] == 1) kind++; //
    }
    static void erase(char x) {
        cnt[x - 'a']--;
        if(cnt[x - 'a'] == 0) kind--; //
    }

    static void proceed() {
        int len = str.length(), ans = 0;
        for(int R = 0, L = 0; R < len; R++) {
            // R번째 문자를 오른쪽에 추가
            add(str.charAt(R));
            while(kind > N) {
                erase(str.charAt(L++));
            }
            // 정답 갱신
            ans = Math.max(ans, R - L + 1);
        }
        sb.append(ans);
        System.out.println(sb);
    }
}

//

import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        input();
        proceed();
    }
    static int N;
    static int kind = 0;
    static String str;
    static int[] cnt = new int[26];

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        str = scan.next();
    }

    static void add(char x) {
        cnt[x - 'a']++;
    }
    static void erase(char x) {
        cnt[x - 'a']--;
    }

    static void proceed() {
        int len = str.length(), ans = 0;
        for(int R = 0, L = 0; R < len; R++) {
            // R번째 문자를 오른쪽에 추가
            add(str.charAt(R));
            // 불가능하면, 가능할 때까지 L을 이동
            while(true) {
                kind = 0;
                for(int i=0;i<26;i++) {
                    if(cnt[i] != 0) kind++;
                }
                if(kind <= N) {
                    break;
                }
                erase(str.charAt(L));
                L++;
            }
            // 정답 갱신
            ans = Math.max(ans, R - L + 1);
        }
        sb.append(ans);
        System.out.println(sb);
    }
}