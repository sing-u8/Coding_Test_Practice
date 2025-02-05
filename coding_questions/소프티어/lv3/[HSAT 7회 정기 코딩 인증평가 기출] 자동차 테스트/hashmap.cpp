#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, q;
    cin >> n >> q;

    // 자동차 연비 입력
    vector<int> cars(n);
    for(int i = 0; i < n; i++) {
        cin >> cars[i];
    }

    // 정렬된 배열 생성
    vector<int> sortedCars = cars;
    sort(sortedCars.begin(), sortedCars.end());

    // 각 값의 작은 수 개수와 등장 횟수 저장
    unordered_map<int, int> smallerCount;
    unordered_map<int, int> valueCount;

    // 각 값에 대한 정보 계산
    for(int i = 0; i < n; i++) {
        valueCount[sortedCars[i]]++;
        if(smallerCount.find(sortedCars[i]) == smallerCount.end()) {
            smallerCount[sortedCars[i]] = i;
        }
    }

    // 쿼리 처리
    while(q--) {
        int mi;
        cin >> mi;

        // mi가 배열에 없는 경우
        if(valueCount.find(mi) == valueCount.end()) {
            cout << "0\n";
            continue;
        }

        // mi보다 작은 수의 개수
        int smaller = smallerCount[mi];
        // mi보다 큰 수의 개수
        int larger = n - smaller - valueCount[mi];

        // 결과 계산: smaller개 중 1개 * larger개 중 1개
        long long result = (long long)smaller * larger;

        cout << result << "\n";
    }

    return 0;
}