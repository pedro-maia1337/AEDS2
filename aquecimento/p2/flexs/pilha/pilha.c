#include <stdio.h>
#include <stdlib.h>

typedef struct Pilha {
    struct Pilha* prox;
    int elemento;
} Pilha;

Pilha* novaPilha(int x){
    Pilha* novaPilha = (Pilha*) malloc(sizeof(Pilha));
    novaPilha->elemento = x;
    novaPilha->prox = NULL;
    return novaPilha;
}

Pilha* topo;

void start() {
    topo = NULL;
}


void inserir(int x) {
    Pilha* temp = novaPilha(x);
    temp->prox = topo;
    topo = temp;
    temp = NULL;
}

int remover(){
    int elemento = 0;
    if(topo == NULL){
        printf("Erro ao remover");
    } else {
        elemento = topo->elemento;
        Pilha* temp = topo;
        topo = topo->prox;
        temp->prox = NULL;
        free(temp);
        temp = NULL;
    }

    return elemento;
}

void mostrar(){
    for(Pilha* i = topo; i != NULL; i = i->prox) printf("%d \n", i->elemento);
}


int main(void) {
    int elemento = 0;

    inserir(1);
    inserir(2);
    inserir(3);
    inserir(4);

    mostrar();

    printf("\n");

    elemento = remover();

    printf("Elemento removido: %d", elemento);
    printf("\n");
    
    mostrar();

    return 0;
}