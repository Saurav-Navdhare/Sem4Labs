#include <iostream>
using namespace std;

void swap(int *xp, int *yp){ // function to swap two elements
    int temp = *xp;
    *xp = *yp;
    *yp = temp;
}

void quickSort(int arr[], int low, int high){   // function to sort the array
    if(low < high){     // if the array has more than one elements
        int pivot = arr[high];  // pivot is the last element of the array
        int i = low-1;  // i is the index of the last element of the first array
        for(int j=low; j<=high-1; j++){ // j is the index of the current element
            if(arr[j] < pivot){ // if the current element is smaller than the pivot, swap it with the last element of the first array
                i++;
                swap(&arr[i], &arr[j]); // swap the current element with the last element of the first array
            }
        }
        swap(&arr[i+1], &arr[high]);    // swap the pivot with the first element of the second array
        int pi = i+1;   // pi is the index of the pivot
        quickSort(arr, low, pi-1);  // sort the first array
        quickSort(arr, pi+1, high);
    }
}

int main(){ // main function
    int arr[] = {9, 4, 3, 5, 7, 1, 8};
    quickSort(arr, 0, 6);
    for(int i=0; i<7; i++){
        cout << arr[i] << " ";
    }
}

/*
Worst case : List Already Sorted
Best case: Pivot element exactly divide list in 2 parts as it saves Recursive calls
*/