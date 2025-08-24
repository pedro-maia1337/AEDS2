/*Crie um método RECURSIVO que recebe uma string como parâmetro e retorna true se essa é um ``Palíndromo''. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com SIM/NÃO indicando se a linha é um palíndromo. Destaca-se que uma linha de entrada pode ter caracteres não letras.
*/

//gcc TTP01Q02.c -o TP01Q02.exe
//./TP01Q02.exe < pub.in > saida.out
//diff pub.out saida.out

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int is_palindromo_recursivo(char *str, int count, int init, int end) {
    if(init >= end) return count;
    
    if(str[end] != str[init]) count = 0;
    
    return is_palindromo_recursivo(str, count, init + 1, end - 1);
}

int main(void) {
    char str[255];
    int flag = 0;
    int len = 0;
    int test = 0;

    while(!flag) { // Ler enquanto FLAG diferente de 0
        fgets(str, 255, stdin); //Leitura padrão
        str[strcspn(str, "\n")] = 0; //Remove o \0

        getchar();

        if(strstr(str, "FIM") != NULL) { //Caso encontre FIM, flag recebe 1 e sai do loop
            flag = 1;
        } else {
            len = strlen(str);
            
            test = is_palindromo_recursivo(str, 1, 0, len - 1);
            //Verifica se o contador é igual ao tamanho da string se for a palavra é um palindromo
            if(test == 1) { 
                printf("SIM\n");
            } else {
                printf("NAO\n");
            }
        }
    }

    return 0;
}