#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct Celula {
  struct Celula* prox;
  int prioridade;
  int chegada;
} Celula;

Celula* novaCelula(int p, int c) { 
  Celula* celula = (Celula*) malloc(sizeof(Celula));
  celula->prioridade = p;
  celula->chegada = c;
  celula->prox = NULL;
  return celula;
}

Celula* primeiro;
Celula * ultimo;

void start() {
  primeiro = novaCelula(0, 0);
  ultimo = primeiro;
}

void inserir(int p, int c) {
  Celula* temp = novaCelula(p, c);
  temp->prox = primeiro->prox;
  primeiro->prox = temp;
  if(primeiro == ultimo) {
    ultimo = temp;
  }
  temp = NULL;
}

int remover() {
  int resp = 0;
  if(primeiro == ultimo) {
    printf("Fila vazia");
  } else {
      Celula *temp = primeiro;
      primeiro = primeiro->prox;
      resp = primeiro->chegada;
      temp->prox = NULL;
      free(temp);
      temp = NULL;
    }
  return resp;
}

void mostrar() {
  Celula* i = primeiro;
  for(i = i->prox; i != NULL; i = i->prox) {
    printf("%d %d\n", i->prioridade, i->chegada);
  }
}

int main(void) {
  start();
  
  int n = 0;
  int p = 0;
  int c = 0;

  scanf("%d", &n);

  scanf("%d %d", &p, &c);

  inserir(p, c);

  for(int i = 0; i < n; i = i + 1) {
    
  }

  inserir(3, 2);
  inserir(2, 3);
  inserir(1, 1);
  mostrar();
  return 0;
}
