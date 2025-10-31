#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

/*Criar a struct de Lista*/
typedef struct Celula {
    int elemento;
    struct Celula* prox;
    struct Celula* ant;
} Celula;


Celula* novaCelula(int x) {
    Celula* nova = (Celula*) malloc(sizeof(Celula));
    nova->elemento = x;
    nova->ant = nova->prox = NULL;
    return nova;
}

Celula* primeiro;
Celula* ultimo;

//Criando nó cabeça
void start() {
    primeiro = novaCelula(-1);
    ultimo = primeiro;
}

//se atentar nas atrib e na atualização dos valores, as variaveis não são estáticas
void inserirInicio(int x) {
    Celula* temp = novaCelula(x);
    temp->prox = primeiro->prox;
    temp->ant = primeiro;
    primeiro->prox = temp;
    if(primeiro == ultimo) {
        ultimo = temp;
    } else {
        temp->prox->ant = temp;
    }
    temp = NULL;
}

void inserirFim(int x) {
    ultimo->prox = novaCelula(x);
    ultimo = ultimo->prox;
}

int removerInicio(){
    int elemento = 0;
    if(primeiro == ultimo) {
        printf("Erro ao remover: lista vazia");
    } else {
        Celula* i = novaCelula(-1);
        i = primeiro->prox;
        elemento = i->elemento;
        primeiro->prox = i->prox;
        primeiro->prox->ant = primeiro;
        free(i);
        i = NULL;
    }

    return elemento;
}

int removerFim(){
    int elemento = 0;
    if(primeiro == ultimo) {
        printf("Erro ao remover: lista vazia");
    } else {
        Celula* i = novaCelula(-1);
        for(i = primeiro->prox; i->prox != ultimo; i = i->prox);
        elemento = i->prox->elemento;
        ultimo = i;
        i->ant = NULL;
        free(ultimo->prox);
        i = i->prox = NULL;
    }

    return elemento;
}

int getTamanho(){
    int len = 0;
    for(Celula* i = primeiro->prox; i != NULL; len++, i = i->prox);
    return len;
}

void inserir(int x, int pos) {
    int len = getTamanho();
    if(pos < 0 || pos > len) {
        printf("Posicao invalida");
    } else if(pos == 0) {
        inserirInicio(x);
    } else if(pos == len) {
        inserirFim(x);
    } else {
        Celula* i = primeiro->prox;
        Celula* temp = novaCelula(x);
        for(int j = 0; j < pos; j++, i = i->prox);
        temp->prox = i->prox;
        temp->ant = i;
        i->prox = temp;
        temp->prox->ant = temp;
    }
}

void mostrar(){
    Celula* i;
    printf("[ ");
    for(i = primeiro->prox; i != NULL; i = i->prox){
        printf("%d ", i->elemento);
    }
    printf("] \n");
}

int main(void) {
    start();

    printf("LISTA DINAMICA DUPLAMENTE ENCADEADA\n\n");

    inserirInicio(2);
    inserirInicio(1);
    inserirFim(3);
    inserirFim(4);
    inserirFim(5);


    printf("%d \n", getTamanho());

    inserir(100, 2);

    mostrar();

    return 0;
}
