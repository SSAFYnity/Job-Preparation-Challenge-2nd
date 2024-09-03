#include <iostream>
#include <string>
#include <math.h>
using namespace std;

int no_sosu[100001];
void make_no_sosu()
{
	no_sosu[0] = 1; // 소수 아님
	no_sosu[1] = 1; // 소수 아님
	double r = sqrt(100001);
	for (int i = 2; i < 100001; i++) {
		if (no_sosu[i] == 0) {
			for (int j = i + i; j < 100001; j += i) {
				no_sosu[j] = 1; // 소수아님
			}
		}
	}
}
string make_string(string s, int start, int end) {
	string now = "";
	for (int i = start; i <= end; i++) {
		now += s[i];
	}
	return now;
}
int find_max_sosu(string s) {
	int maxV = 0;
	for (int i = 0; i < s.size(); i++) {
		for (int j = i; j < s.size(); j++) {
			if (j - i > 5) continue;
			string now = make_string(s, i, j);
			int num = stoi(now);
			if (num > 100000) continue;
			if (no_sosu[num] == 1) continue;
			if (maxV < num) {
				maxV = num;
			}
		}
	}
	return maxV;
}

int main() {
	make_no_sosu();
	while (1) {
		string s;
		cin >> s;
		if (s == "0") {
			return 0;
		}
		int result = find_max_sosu(s);
		cout << result << endl;
	}
	return 0;

}