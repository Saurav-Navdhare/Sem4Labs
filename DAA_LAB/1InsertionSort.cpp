#include <iostream>
using namespace std;

/**
 * Best Case: O(n), when the array is already sorted
 * Worst Case: O(n^2), when the array is reverse sorted
*/
void insertionSort(int arr[], int n){
    int i, key, j;  // key is the element to be inserted, j is the index of the element to be compared with key, i is the index of the element to be inserted
    for (i = 1; i < n; i++){
        key = arr[i];
        j = i - 1;
        while (j >= 0 && arr[j] > key){ // if the element to be compared is greater than key, move it one position ahead of its current position
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;   // insert the key at the correct position
    }
}

void printArray(int arr[], int n){      // function to print the array
    int i;
    for (i = 0; i < n; i++)
        cout << arr[i] << " ";
    cout << endl;
}

int main(){     // main function
    int arr[] = {12, 11, 13, 5, 6};
    int n = sizeof(arr)/sizeof(arr[0]);
    insertionSort(arr, n);
    cout << "Sorted array: \n";
    printArray(arr, n);
    return 0;
}