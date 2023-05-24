#include <iostream>
using namespace std;


void merge(int arr[], int left, int mid, int right){    // function to merge two sorted arrays
    int i, j, k;    // i is the index of the first array, j is the index of the second array, k is the index of the merged array
    int n1 = mid - left + 1;    // size of the first array
    int n2 = right - mid;   // size of the second array
    int L[n1], R[n2];   // two arrays to store the elements of the first and second arrays
    for (i = 0; i < n1; i++)    // copy the elements of the first array to L
        L[i] = arr[left + i];   // left is the index of the first element of the first array
    for (j = 0; j < n2; j++)    // copy the elements of the second array to R
        R[j] = arr[mid + 1 + j];    // mid+1 is the index of the first element of the second array
    i = 0;
    j = 0;
    k = left;   // k is the index of the merged array
    while (i < n1 && j < n2){   // merge the two arrays
        if (L[i] <= R[j]){  // if the element of the first array is smaller than the element of the second array, insert the element of the first array to the merged array
            arr[k] = L[i];
            i++;
        }
        else{
            arr[k] = R[j];  // insert the element of the second array to the merged array
            j++;
        }
        k++;
    }
    while (i < n1){ // if there are still elements in the first array, insert them to the merged array
        arr[k] = L[i];
        i++;
        k++;
    }
    while (j < n2){ // if there are still elements in the second array, insert them to the merged array
        arr[k] = R[j];
        j++;
        k++;
    }
}

void mergeSort(int arr[], int left, int right){   // function to sort the array
    if(left >= right) return;   // if the array has only one or no element, return
    int mid = (left+right)/2;   // find the middle index of the array
    mergeSort(arr, left, mid);  // sort the first half of the array
    mergeSort(arr, mid+1, right);   // sort the second half of the array
    merge(arr, left, mid, right);   // merge the two sorted arrays
}

void printArray(int arr[], int size){    // function to print the array
    for(int i=0; i<size; i++){
        cout << arr[i] << " ";
    }
    cout << endl;
}

int main(){ // main function
    int arr[] = {9, 4, 3, 5, 7, 1, 8, 1};
    mergeSort(arr, 0, 7);
    printArray(arr, 8);
}