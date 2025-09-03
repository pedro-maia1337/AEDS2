/*
Crie um método recursivo que recebe um número inteiro como parâmetro e retorna a soma de seus dígitos. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com o resultado da soma dos dígitos. Por exemplo, se a entrada for 12345, a saída deve ser 15
*/

// gcc TP02Q04.c -o TP02Q04.exe
// ./TP02Q04.exe < pub.in > saida.out
// diff pub.out saida.out


#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int soma_recursiva(int num, int dig, int soma){
    if(num <= 0){ //Caso o número atinja zero retorna a soma(condição de parada)
        return soma; 
    } else {
        dig = num % 10; //Separa os digitos pegando o módulo da divisão por 10
        soma = soma + dig; //Realiza a soma dos dígitos
    }

    return soma_recursiva(num / 10, dig, soma); //Chama a função novamente repassando o número inicial divido por 10 para remover os digitos que já foram operados
} 

int main(void) {
    char str[255];
    int flag = 0;
    int num = 0;
    int result = 0;

    while(!flag) { // Ler enquanto FLAG diferente de 0
        fgets(str, 255, stdin);
        str[strcspn(str, "\n")] = 0; //Remove o \0

        if (strncmp(str, "FIM", 3) == 0 && strlen(str) == 3) { //Caso leia fim, muda flag para 1, parando o loop
            flag = 1;
        } else {
            num = atoi(str); // Converte a string para um inteiro

            result = soma_recursiva(num, 0, 0);

            printf("%d\n", result);
        } 
    }

    return 0;
}