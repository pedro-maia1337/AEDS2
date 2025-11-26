#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAX_EMERGENCIA 5
#define MAX_PERIODICA 5
#define MAX_BACKGROUND 30

typedef struct {
    int id;
    int prioridade; //0 - 10 (0 maxima prioridade)
} Tarefa;

typedef struct { //Pilha de tarefas
    Tarefa pilha[MAX_EMERGENCIA];
    int n; //número de tarefas na lista
} PilhaEmergencia;

typedef struct {
    Tarefa fila[MAX_PERIODICA]; //fila de tarefas
    int primeiro;
    int ultimo;
} FilaPeriodica;

typedef struct {
    Tarefa lista[MAX_BACKGROUND]; // lista de tarefas
    int n; // número de tarefas na lista
} ListaBackground;

Tarefa processarTarefa(PilhaEmergencia* p, FilaPeriodica* f, ListaBackground* l) {
    Tarefa proximaTarefa;
    int maiorPrioridade = 0;

    //Validando pilha
    for(int i = 0; i < p->n; i = i + 1) {
        if(p->pilha[i].prioridade > p->pilha[maiorPrioridade].prioridade) {
            maiorPrioridade = i;
        }
    }
    proximaTarefa = p->pilha[maiorPrioridade];

    return proximaTarefa;
}

int main(void) {
  


  return 0;
}
