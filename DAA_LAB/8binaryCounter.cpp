#include <iostream>
#include <vector>
#include <cmath>
using namespace std;
// binary counter function
void binaryCounter(int n)
{
    if(n==0){
        cout << 0 << endl;
        return;
    }
    int bits = log2(n) + 1;
    vector<int> counter(bits);
    for (int i = 0; i <= n; i++)
    {
        for (int j = 0; j < bits; j++)
        {
            cout << counter[j];
        }
        for (int j = bits - 1; j >= 0; j--)
        {
            if (counter[j] == 0)
            {
                counter[j] = 1;
                break;
            }
            else
            {
                counter[j] = 0;
            }
        }
        cout << endl;
    }
}
// main function
int main()
{
    int n;
    cout << "Enter the number of times you want to increment the counter: ";
    cin >> n;
    // call the binary counter function
    cout << "The binary counter is: " << endl;
    binaryCounter(n);
    return 0;
}
