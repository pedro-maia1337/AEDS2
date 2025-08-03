/*Implemente um programa denominado Combinador, que recebe duas strings e deve combiná-las, alternando as letras de cada string,
começando com a primeira letra da primeira string, seguido pela primeira letra da segunda string, em seguida pela segunda letra da primeira string,
e assim sucessivamente. As letras restantes da cadeia mais longa devem ser adicionadas ao fim da string resultante e retornada.*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {
    char str1[50] = "Ola";
    char str2[50] = "Mundo";
    char strR[50];

    int len1 = strlen(str1);
    int len2 = strlen(str2);
    int lenR = len1 + len2;

    int glen = 0;
    int mlen = 0;

    int count = 0;

    if(len1 > len2) {
        glen = len1;
        mlen = len2;
    }

    if(len2 > len1) {
        glen = len2;
        mlen = len1;
    }

    for(int i = 0; i < lenR; i++) {
        strR[count] = str1[i];
        count = count + 1;
        strR[count] = str2[i];
    }

    printf("%s", strR);
    
    return 0;
}