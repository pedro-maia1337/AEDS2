//Isso aqui precisa ser em java

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int calc_dig(int x, int dig) {
    if(x < 0) {

    }
    return calc_dig(x / 10;)
}

int main(void) {
    int len = 0;
    int dif = 0;
    int dig = 0;

    scanf("%d", &len);

    int arr[len];

    for(int i = 0; i < len; i = i + 1){
        scanf("%d", &arr[i]);
    }

    for(int i = 1; i < len; i = i + 1){
        for(int j = 0; i < sizeof(arr[i]))
        dig = arr[i] % 10;
        arr[i] = arr[i] / 10;
        printf("%d", dig);
    }

    return 0;
}