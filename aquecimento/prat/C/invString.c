/* Crie um método recursivo que recebe uma string como parâmetro e retorna a string invertida. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com a string invertida. Por exemplo, se a entrada for abcde, a saída deve ser edcba.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *reverse_string(char *str, int x, int len) {
    char* aux = (char*)malloc(50 * sizeof(char));

    if(x >= strlen(str)) {
        return aux;
    } else {
        aux[x] = str[len];
        return reverse_string(str, x + 1, len - 1);
    }
}


int main(void) {
    FILE *entry = fopen("entrada.txt", "rt");
    FILE *exit = fopen("saida.txt", "wt");

    char str[50];
    int flag = 0;

    if(entry && exit) {

        while(!flag && fgets(str, 50, entry)) {
            if(strstr(str, "FIM") != NULL) {
                flag = 1;
            } else {
                fprintf(exit, "%s\n", reverse_string(str, 0, strlen(str)));
            }
        }
    } else {
        printf("Nao foi possivel ler o arquivo: ");
    }

    fclose(entry);
    fclose(exit);

    return 0;
}