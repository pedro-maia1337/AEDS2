#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

//crescente por módulo
    //empate entre impar e par
        //impar primeiro
    //empate entre dois impares 
        //maior primeiro
    //empate entra dois pares
        //menor primeiro


void swap(int *arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

//Função com multiplos critérios de ordenação
bool vemAntes(int a, int b, int mod) {
    int modA = a % mod;
    int modB = b % mod;
    
    //empate entre impar e par (impar precede)
    if(modA == modB && a % 2 != 0 && b % 2 == 0) return true;
    if(modA == modB && a % 2 == 0 && b % 2 != 0) return false;

    //empate entre dois impares (maior precede)
    if(modA == modB && a % 2 != 0 && b % 2 != 0 && a > b) return true;
    if(modA == modB && a % 2 != 0 && b % 2 != 0 && b < b) return false;

    //empate entra dois pares (menor primeiro)
    if(modA == modB && a % 2 == 0 && b % 2 == 0 && a < b) return true;
    if(modA == modB && a % 2 == 0 && b % 2 == 0 && b < b) return false;

    return (a % mod) < (b % mod);
}


int main(void) {
    int mod = 0;
    int n = 0;
    int menor = 0;
    bool flag = false;

    while(!flag) {
        scanf("%d %d", &n, &mod);

        printf("%d %d\n", n, mod);

        if(n == 0 && mod == 0) {
            flag = true;
        } else {
            int arr[n];

            for(int i = 0; i < n; i = i + 1) {
                scanf("%d", &arr[i]);
            }

            for(int i = 0; i < (n - 1); i = i + 1) {
                menor = i;
                for(int j = (i + 1); j < n; j = j + 1) {
                    if(vemAntes(arr[j], arr[menor], mod)) {
                        menor = j;
                    }
                }

                swap(arr, i, menor);
            }

            for(int i = 0; i < n; i = i + 1) {
                printf("%d\n", arr[i]);
            }
        }  
    }

    return 0;
}