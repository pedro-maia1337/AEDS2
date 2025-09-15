/*
Crie um método recursivo que recebe uma string e retorna true se a mesma é composta somente por vogais.
Crie outro método recursivo que recebe uma string e retorna true se a mesma é composta somente por consoantes.
Crie um terceiro método recursivo que recebe uma string e retorna true se a mesma corresponde a um número inteiro.
Crie um quarto método recursivo que recebe uma string e retorna true se a mesma corresponde a um número real.
Na saída padrão, para cada linha de entrada, escreva outra de saída da seguinte forma X1 X2 X3 X4 onde cada Xi é um booleano indicando se a é entrada é: composta somente por vogais (X1); composta somente somente por consoantes (X2); um número inteiro (X3); um número real (X4). Se Xi for verdadeiro, seu valor será SIM, caso contrário, NÃO.
*/

// gcc TP03Q01.c -o TP03Q01.exe
// ./TP03Q01.exe < pub.in > saida.out
// diff pub.out saida.out

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
int is_vogal(char *str, int len) {
    //Percorre o arranjo de char e valida se as letras são diferente das vogais, caso seja retorna falso
    for(int i = 0; i < len; i = i + 1) {
        if(!((str[i] == 'A' || str[i] == 'E' || str[i] == 'I' || str[i] == 'O' || str[i] == 'U') || 
             (str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u'))) {
            return 0;
        }
    }

    return 1;
}
*/

int is_vogal(char *str, int len) {
    if(!((str[i] == 'A' || str[i] == 'E' || str[i] == 'I' || str[i] == 'O' || str[i] == 'U') || 
         (str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u'))) {
        return 0;
    }

    return is_vogal()
}



int is_consoante(char *str, int len) {
    //Percorre o arranjo garantindo que sejam somente letras
    for(int i = 0; i < len; i = i + 1) {
        if (!((str[i] >= 'A' && str[i] <= 'Z') || (str[i] >= 'a' && str[i] <= 'z'))) {
            return 0;
        }
    }

    //Valida se o char é vogal se for retorna falso
    for(int i = 0; i < len; i = i + 1) {
        if (str[i] == 'A' || str[i] == 'E' || str[i] == 'I' || str[i] == 'O' || str[i] == 'U' || 
            str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u') {
            return 0;
        }
    }

    //Retorna 1 garantindo que seja somente consoantes
    return 1;
}

int is_int(char *str, int len) {
    //Percorre o arranjo de char e valida se está no range entre 0 e 9, essa comparação é com base na tabela ascii
    for(int i = 0; i < len; i = i + 1) {
        if(!(str[i] >= '0' && str[i] <= '9')){
            return 0;
        }       
    }

    return 1;
}

int is_double(char *str, int len) {
    int flag = 0;
    for(int i = 0; i < len; i = i + 1) { //Percorre o array em busca do ponto ou da virgula
        if(str[i] == '.' || str[i] == ',') {
            flag = flag + 1; // Caso encontre altera o resultado  de flag para diferente de zero
        }    
    }
    

    for(int i = 0; i < len; i = i + 1) {
        //Valida se tem somente números e se flag é 0 ou 1, o que significa que tem ponto ou virgula e somente uma ocorrencia
        if((str[i] >= '0' && str[i] <= '9') && (flag == 1 || flag == 0)) {
            return 1;
        } 

    }

    return 0;
}

int main(void) {
    char str[255];
    int len = 0;
    int flag = 0;

    while(!flag) { // Ler enquanto FLAG diferente de 0
        fgets(str, 255, stdin); //Leitura padrão e corrigindo o encode 
        str[strcspn(str, "\n")] = 0; //Remove o \0

        if(strcmp(str, "FIM") == 0) { //Caso encontre  FIM, flag recebe 1 e sai do loop
            flag = 1;
        } else {
            len = strlen(str);

            if(is_vogal(str, len)) printf("SIM ");
                else printf("NAO ");

            if(is_consoante(str, len)) printf("SIM ");
                else printf("NAO ");

            if(is_int(str, len)) printf("SIM ");
                else printf("NAO ");

            if(is_double(str, len)) printf("SIM\n");
                else printf("NAO\n");
                
        }
    }

    return 0;
}