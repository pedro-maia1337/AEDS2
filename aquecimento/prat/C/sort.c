#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

int search_array_seq(int *arr, int n, int num) {
    for(int i = 0; i < n; i = i + 1) {
        if(arr[i] == num) {
            return 1;
        } 
    }

    return 0;
}

int search_array_bin(int *arr, int n, int num) {
    bool resp = false;
    int dir = n - 1; int esq = 0; int mid = 0; int dif = 0;

    while(esq <= dir) {
        mid = (dir + esq) / 2;
        dif = (num - arr[mid]);
        if(dif == 0) {
            resp = true;
            esq = num;
        } else if (dif > 0) {
            esq = mid + 1;
        } else {
            dir = mid - 1;
        }
    }

    return resp;
}


int main(void) {
    bool result = false;
    int arr[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    result = search_array_bin(arr, 10, 11);

    if(result == false) printf("Nao Encontrado.");
    if(result) printf("Encontrado.");


    return 0;
}