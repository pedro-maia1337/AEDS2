/* Imprimir números em sequência é uma tarefa relativamente simples. Mas e quando se trata de uma sequência espelho? Essa é uma sequência que possui um número inicial e um número final, e todos os números entre eles, inclusive, são dispostos em ordem crescente, sem espaços, e então essa sequência é refletida de forma invertida, como um reflexo no espelho. Por exemplo, se a sequência for de 7 a 12, o resultado seria 789101112211101987.

Escreva um programa que, dados dois números inteiros, imprima a respectiva sequência espelho. */


#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {
    int inicio = 7;
    int final = 12;

    for(int i = inicio; i <= final; i = i + 1) {
        printf("%d", i);
    }

    for(int i = final; i >= inicio; i = i - 1) {
        printf("%d", i);
    }
    
    return 0;
}