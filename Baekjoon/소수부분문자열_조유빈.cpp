#include <iostream>
#include <algorithm>
#include <cstring>
#include <string>
#include <set>

using namespace std;

set<string> sub;
int isPrime[100001];
int maxNum = 0;

void setPrime() {
    fill(isPrime, isPrime + 100001, 1);
    isPrime[0] = isPrime[1] = 0;

    for (int i = 2; i <= 100000; i++) {
        if (isPrime[i] == 1) {
            for (int j = i * 2; j <= 100000; j += i) {
                isPrime[j] = 0;
            }
        }
    }
}

int main() {
    setPrime();

    while (true) {
        string str;
        cin >> str;
        if (str == "0") break;

        sub.clear();
        int l = str.length();
        for (int i = 0; i < l; i++) {
            for (int j = i; j < l; j++) {
                sub.insert(str.substr(i, j - i + 1));
            }
        }

        maxNum = 0;
        for (const string& s : sub) {
            try {
                long long num = stoll(s);
                if (num <= 100000 && isPrime[num] == 1) {
                    maxNum = max(maxNum, (int)num);
                }
            }
            catch (out_of_range& e) {
                continue;
            }
        }

        cout << maxNum << endl;
    }
    return 0;
}