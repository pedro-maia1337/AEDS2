#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char nome[255];
    char sobrenome[255];
    char sexo;
} Participantes;


int main(void) {
    int len = 0;
    scanf("%d", &len);
    Participantes grupoParticipantes[len];
    char


    for(int i = 0; i < len; i = i + 1){
        scanf("%s %s %c", grupoParticipantes[i].nome, grupoParticipantes[i].sobrenome, &grupoParticipantes[i].sexo);
    }

    for (int i = 0; i < len; i++) {
        // Exemplo: imprimir os dados de cada pessoa
        printf("Nome: %s, Sobrenome: %s Sexo: %c\n", grupoParticipantes[i].nome, grupoParticipantes[i].sobrenome, grupoParticipantes[i].sexo);
    }

    for(int i = 0; i < len; i = i + 1){
        if(grupoParticipantes[i].sexo == 'F'){
            listaOrdenada[i].nome = grupoParticipantes[i].nome;
            listaOrdenada[i].sobrenome = grupoParticipantes[i].sobrenome;
            listaOrdenada[i].sexo = grupoParticipantes[i].sexo;
        } 
    }

    return 0;
}