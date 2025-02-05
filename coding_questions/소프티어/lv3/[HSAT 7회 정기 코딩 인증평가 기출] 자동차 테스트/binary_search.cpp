// https://softeer.ai/practice/6247
// 이진 탐색을 활용한 방법
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, q;
    cin >> n >> q;

    vector<int> cars(n);
    for(int i = 0; i < n; i++) {
        cin >> cars[i];
    }

    // 정렬
    sort(cars.begin(), cars.end());

    while(q--) {
        int mi;
        cin >> mi;

        // 중앙값 mi의 위치 찾기
        auto upper = upper_bound(cars.begin(), cars.end(), mi);
        auto lower = lower_bound(cars.begin(), cars.end(), mi);

        // mi가 배열에 없는 경우
        if(upper == lower) {
            cout << "0\n";
            continue;
        }

        // mi보다 작은 수의 개수
        long long smaller = lower - cars.begin();
        // mi보다 큰 수의 개수
        long long larger = cars.end() - upper;

        cout << smaller * larger << "\n";
    }

    return 0;
}