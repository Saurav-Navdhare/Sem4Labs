#include <iostream>
using namespace std;

/**
 * Best case: O(n^2), when the array is already sorted
 * Worst case: O(n^2), when the array is reverse sorted
*/


void swap(int *xp, int *yp){        // function to swap two elements
    int temp = *xp;
    *xp = *yp;
    *yp = temp;
}

void selectionSort(int arr[], int n){   // function to sort the array using selection sort
    int i, j, min_idx;  // min_idx is the index of the minimum element, i is the index of the element to be compared with the minimum element, j is the index of the element to be inserted
    for (i = 0; i < n-1; i++){
        min_idx = i;    // set the minimum element to the first element
        for (j = i+1; j < n; j++)
          if (arr[j] < arr[min_idx])    // if the element to be compared is smaller than the minimum element, set the minimum element to the element to be compared
            min_idx = j;
        swap(arr[min_idx], arr[i]);    // swap the minimum element with the element to be inserted
    }
}

void printArray(int arr[], int n){    // function to print the array
    int i;
    for (i = 0; i < n; i++)
        cout << arr[i] << " ";
    cout << endl;
}

int main() {    // main function
    int arr[] = {64, 25, 12, 22, 11};
    int n = sizeof(arr)/sizeof(arr[0]);
    selectionSort(arr, n);
    cout << "Sorted array: \n";
    printArray(arr, n);
    return 0;
}