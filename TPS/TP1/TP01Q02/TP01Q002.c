/*Crie um método RECURSIVO que recebe uma string como parâmetro e retorna true se essa é um ``Palíndromo''. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com SIM/NÃO indicando se a linha é um palíndromo. Destaca-se que uma linha de entrada pode ter caracteres não letras.
*/

//gcc TP01Q002.c -o TP01Q002.exe
//./TP01Q002.exe < pub.in > saida.out
//diff pub.out saida.out

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <wchar.h> // tratativa para lidar com problema de encode 
#include <locale.h>

int is_palindromo_recursivo(wchar_t *str, int count, int init, int end) {
    if(init >= end) return count; 
    
    if(str[end] != str[init]) count = 1; //Caso encontre diferença count = 1, sinalizando que não é um palindromo
    
    //Chama a função novamente, deslocando o inicio para direita e o final para esquerda
    return is_palindromo_recursivo(str, count, init + 1, end - 1);
}

int main(void) {
    setlocale(LC_ALL, ""); // UTF-8

    //Tive que trocar os métodos da biblioteca string.h pela wchar para lidar com UTF-8
    //Não entendi muito bem o L nos metódos mas na documentação diz que é para indicar o tipo da string que é literal

    wchar_t str[255];
    int flag = 0;
    int len = 0;
    int test = 0;

    while(!flag) { // Ler enquanto FLAG diferente de 0

        fgetws(str, 255, stdin); //Leitura padrão e corrigindo o encode 
        str[wcslen(str) - 1] = L'\0'; //Remove o \0

        if(wcscmp(str, L"FIM") == 0) { //Caso encontre FIM, flag recebe 1 e sai do loop
            flag = 1;
        } else {
            len = wcslen(str);

            test = is_palindromo_recursivo(str, 0, 0, len - 1);
            //Verifica o resultado da função, printando o resultado 
            if(test == 0) { 
                wprintf(L"SIM\n");
            } else {
                wprintf(L"NAO\n");
            }
        }
    }

    return 0;
}