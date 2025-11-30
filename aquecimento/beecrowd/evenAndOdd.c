#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

void swap(int *arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

//Bom para ser utilizado para comparação de valores com dois ou mais critérios de ordenação utilizando o algoritmo de seleção 
bool vemAntes(int a, int b) {
    if(a % 2 == 0 && b % 2 != 0) return true;
    if(a % 2 != 0 && b % 2 == 0) return false;

    return a < b;
}
         
int main(void) {
    int n = 0;
    int menor = 0;
    int countPar = 0;

    scanf("%d", &n);

    int arr[n];

    for(int i = 0; i < n; i = i + 1) {
        scanf("%d", &arr[i]);
    }

    for(int i = 0; i < (n - 1); i = i + 1) {
        menor = i;
        for(int j = (i + 1); j < n; j = j + 1) {
            if(vemAntes(arr[j], arr[menor])) {
                menor = j;
            }
        }
        swap(arr, i , menor);
    }

    for(int i = 0; i < n ; i = i + 1) if(arr[i] % 2 == 0) countPar++;

    for(int i = countPar; i < (n - 1); i = i + 1) {
        menor = i;
        for(int j = (i + 1); j < n; j = j + 1) {
            if(arr[menor] < arr[j]) {
                menor = j;
            }
        }
        swap(arr, i , menor);
    }
    
    for(int i = 0; i < n; i = i + 1) {
        printf("%d\n", arr[i]);
    }

    return 0;
}