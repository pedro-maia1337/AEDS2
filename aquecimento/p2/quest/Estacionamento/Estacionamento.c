#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct Pilha {
    struct Pilha* prox;
    int chegada;
    int saida;
} Pilha;

Pilha* criarPilha(int c, int s){
    Pilha* novaPilha = (Pilha*) malloc(sizeof(Pilha));
    novaPilha->prox = NULL;
    novaPilha->chegada = c;
    novaPilha->saida = s;
    return novaPilha;
}

Pilha* topo;

void start() {
    topo = NULL;
}

void inserir(int c, int s) {
    Pilha* temp = criarPilha(c, s);
    temp->prox = topo;
    topo = temp;
    temp = NULL;
}

int remover() {
    int elemento = 0;
    if(topo == NULL) {
        printf("Pilha vazia");
    } else {
        Pilha *temp = criarPilha(-1, - 1);
        temp = topo;
        elemento = temp->saida;
        topo = temp->prox;
        temp->prox = NULL;
        free(temp);
        temp = NULL;
    }

    return elemento;
}

void mostrar() {
    if(topo == NULL) printf("esvaziou");
    for(Pilha* i = topo; i != NULL; i = i->prox) printf("%d %d\n", i->chegada, i->saida);
}

bool esvaziar() {
    bool flag = true;
    for(Pilha* i = topo; i != NULL; i = i->prox) {
        if(topo->saida > topo->prox->saida) {
            flag = false;
        }
    }
    return flag;
}


int main(void) {
    int k = 0; // número de motoristas
    int n = 0; // número de vagas que o estacionamento suporta;
    bool flag = true;

    int qtd = 0;

    while(flag) {
        scanf("%d %d", &k, &n);

        if(k == 0 && n == 0) {
            flag = false;
        } else {
            start();

            int c = 0;
            int s = 0;

            inserir(c, s); 

            for(int i = 0; i < k; i = i + 1){
                scanf("%d %d", &c, &s);
                if(c > topo->saida) {
                    remover();
                    inserir(c, s); 
                } else {
                    inserir(c, s); 
                   
                }
            }

            if(esvaziar()){
                printf("Sim\n");
            } else {
                printf("Nao\n");
            }
        }
    }
   
    return 0;
}