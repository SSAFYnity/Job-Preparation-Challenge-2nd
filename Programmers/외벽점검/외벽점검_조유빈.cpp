#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int Answer = 2e9;
bool Select[8];
vector<int> V;

int Min(int A, int B) {
    if (A < B) return A;
    return B;
}

bool Cmp(int A, int B) {
    if (A > B) return true;
    return false;
}

void Check(vector<int> Weak, vector<int> Dist, int N) {
    for (int i = 0; i < Weak.size() - 1; i++) {
        int Weak_Index = 0;
        int Cnt;
        bool Flag = false;
        
        for (int j = 0; (j < V.size()) && (j + 1 < Answer) && (Flag == false); j++) {
            int End = Weak[Weak_Index] + Dist[V[j]];
            
            while (End >= Weak[Weak_Index]) {
                Weak_Index++;
                
                if (Weak_Index == Weak.size()) {
                    Cnt = j + 1;
                    Flag = true;
                    break;
                }
            }
        }
        
        if (Flag == true) Answer = Min(Answer, Cnt);
        
        int Start_Value = Weak[0];
        for (int j = 1; j < Weak.size(); j++) Weak[j - 1] = Weak[j];
        Weak[Weak.size() - 1] = Start_Value + N;
    }
}

void DFS(int Cnt, vector<int> Weak, vector<int> Dist, int N) {
    if (Cnt == Dist.size()) {
        Check(Weak, Dist, N);
        return;
    }
    
    for (int i = 0; i < Dist.size(); i++) {
        if (Select[i] == true) continue;
        Select[i] = true;
        V.push_back(i);
        DFS(Cnt + 1, Weak, Dist, N);
        V.pop_back();
        Select[i] = false;
    }
}

int solution(int n, vector<int> weak, vector<int> dist) {
    Answer = 2e9;
    sort(dist.begin(), dist.end(), Cmp);
    vector<int> Temp = weak;
    
    for (int i = 0; i < Temp.size() - 1; i++) {
        int Start = Temp[0];
        int End = Temp[Temp.size() - 1];
        if (End - Start <= dist[0]) return 1;
        
        int Start_Value = Temp[0];
        for (int j = 1; j < Temp.size(); j++) Temp[j - 1] = Temp[j];
        Temp[Temp.size() - 1] = Start_Value + n;
    }
    
    DFS(0, weak, dist, n);
    if (Answer == 2e9) return -1;
    return Answer;
}
