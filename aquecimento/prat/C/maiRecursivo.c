/*Crie um método iterativo em Java que receba como parâmetro uma string e retorne seu número de caracteres maiúsculos. Em seguida, teste o método anterior usando redirecionamento de entrada e saída.
A entrada padrão é composta por várias linhas sendo que a última contém a palavra FIM. A saída padrão contém um número inteiro para cada linha de entrada.

Refaça a questão anterior de forma recursiva. Atente-se para os detalhes dos retornos de cada um dos métodos. */

/*Crie um método iterativo em C que receba como parâmetro uma string e retorne seu número de caracteres maiúsculos. Em seguida, teste o método anterior usando redirecionamento de entrada e saída.
A entrada padrão é composta por várias linhas sendo que a última contém a palavra FIM. A saída padrão contém um número inteiro para cada linha de entrada. */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int countM(char *str, int x, int count) {
    int len = strlen(str);

    if(x >= len) {
        return count;
    } else {
        if(str[x] >= 'A' && str[x] <= 'Z') count = count + 1;
        return countM(str, x + 1, count);
    }
}

int main(void){
    FILE *entry = fopen("entrada.txt", "rt"); // criar arquivo de entrada
    FILE *exit = fopen("saida.txt", "wt");

    char str[50];
    int flag = 0;

    if(entry && exit) {
        while(!flag && fgets(str, 50, entry)) 
        {
            if(strstr(str, "FIM") != NULL) 
            {
                flag = 1;
            } // end if
            else 
            {
                fprintf(exit, "%d\n", countM(str, 0, 0));
            } // end else
        } // end while
    } else {
        printf("Nao foi possivel ler o arquivo");
    } //end else
    
    fclose(entry);
    fclose(exit);
    
    return 0;
}