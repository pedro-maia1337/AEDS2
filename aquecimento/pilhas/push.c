#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int dia;
    int mes;
    int ano;
} Data;

typedef struct {
    char nome[50];
    Data data;
} Pessoa;

typedef struct no {
    Pessoa p;
    struct no *proximo;
} No;

Pessoa adicionar_pessoa() {
    Pessoa p;   

    printf("\nDigite o nome e data de nascimento da pessoa: ");
    scanf("%49[^\n]%d%d%d", p.nome, &p.data.dia, &p.data.mes, &p.data.ano);
    return p;
}

void imprimir_pessoa(Pessoa p) {
    printf("\nNome: %s\nData: %2d/%2d/%4d\n", p.nome, p.data.dia, p.data.mes, p.data.ano);
}

No* push(No *topo) {
    No *novo = malloc(sizeof(No));

    if(novo) {
        novo->p = adicionar_pessoa(); 
        novo->proximo = topo;
        return novo;
    } 
    else {
        printf("Erro ao alocar memoria ");
    }

    return NULL;
}

No* pop(No **topo) {
    if(*topo != NULL) {
        No *remover = *topo;
        *topo = remover->proximo;
        return remover;

    } else {
        printf("Pilha vazia!\n");
    }

    return NULL;
}

void print(No *topo) {
    printf("\n---------------------PILHA---------------------\n");
    while(topo) {
        imprimir_pessoa(topo->p);
        topo = topo->proximo;
    }
    printf("\n---------------------FIM PILHA-----------------\n");
}

int main() {

    No *topo = NULL;
    No *remover = NULL;
    int op;

    do {
        printf("\n1 - Empilhar\n");
        printf("2 - Desempilhar\n");
        printf("3 - Imprimir\n");
        printf("0 - Sair\n");

        printf("Selecione: ");
        scanf("%d", &op); getchar();

        switch (op) {
            case 1:
                topo = push(topo);
                break;

            case 2:
                remover = pop(&topo);
                if(remover) {
                    printf("\nElemento removido com sucesso!\n");
                    imprimir_pessoa(remover->p);
                } else {
                    printf("Sem NO a remover.\n");
                }
                break;

            case 3:
                print(topo);
                break;

            case 0:
                printf("Pressione Enter para sair "); getchar();
                break;    
            
            default:
                printf("Opcao invalida");
                break;
        }

    } while(op != 0);

    return 0;
}