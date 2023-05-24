#include <iostream>
#include <chrono>
using namespace std;

void fibonacciIterative(int n){
    int l = 0, h = 1, t;
    if (n == 1){
        cout << l;
    }
    else{
        cout << l << " " << h << " ";
        for (int i = 1; i <= n - 2; i++){
            t = l + h;
            cout << t << " ";
            l = h;
            h = t;
        }
    }
}

int fibonacciRecursive(int n){
    if(n==1) return 0;
    else if(n==2) return 1;
    else return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
}

int main(){
    int n;
    cout << "Enter number of terms: ";
    cin >> n;
    if (n < 1){
        cout << "Invalid Number of Terms" << endl;
        return 0;
    }
    cout << "Iterative method: ";
    auto start = std::chrono::high_resolution_clock::now();
    fibonacciIterative(n);
    auto stop = std::chrono::high_resolution_clock::now();
    auto duration = std::chrono::duration_cast<std::chrono::microseconds>(stop - start);
    // printf("\nTime measured: %.3f microseconds.\n", duration.count() * 1e-3);
    cout << endl << "Time measured " << duration.count() << " microseconds" << endl;
    cout << endl << "Recursive method: ";
    start = std::chrono::high_resolution_clock::now();
    for(int i=1; i<=n; i++){
        cout << fibonacciRecursive(i) << " ";
    }
    stop = std::chrono::high_resolution_clock::now();
    duration = std::chrono::duration_cast<std::chrono::microseconds>(stop - start);
    // printf("\nTime measured: %.3f nanoseconds.\n", duration.count() * 1e-3);
    cout << endl << "Time measured " << duration.count() << " microseconds" << endl;
}